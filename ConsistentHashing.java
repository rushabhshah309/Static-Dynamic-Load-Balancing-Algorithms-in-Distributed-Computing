package algorithms;

import models.Node;
import models.Request;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class ConsistentHashing implements Router {
    // map of node to list of positions on the ring
    private final Map<Node, List<Long>> nodePositions;

    // map of positions on the ring to node
    private final ConcurrentSkipListMap<Long, Node> nodeMappings;

    // function used to hash keys to positions on the ring
    private final Function<String, Long> hashFunction;

    // multiplier used to determine number of positions per node on the ring
    private final int pointMultiplier;


    public ConsistentHashing(final Function<String, Long> hashFunction,
                             final int pointMultiplier) {

	// check that point multiplier is not zero
        if (pointMultiplier == 0) {
            throw new IllegalArgumentException();
        }
        this.pointMultiplier = pointMultiplier;
        this.hashFunction = hashFunction;
        this.nodePositions = new ConcurrentHashMap<>();
        this.nodeMappings = new ConcurrentSkipListMap<>();
    }

    public void addNode(Node node) {

        // create list of positions for node
        nodePositions.put(node, new CopyOnWriteArrayList<>());
        for (int i = 0; i < pointMultiplier; i++) {
            for (int j = 0; j < node.getWeight(); j++) {

		// compute position on the ring
                final var point = hashFunction.apply((i * pointMultiplier + j) + node.getId());
                nodePositions.get(node).add(point);
                nodeMappings.put(point, node);
            }
        }
    }

    public void removeNode(Node node) {
	
	// remove all positions for this node from the ring
        for (final Long point : nodePositions.remove(node)) {
            nodeMappings.remove(point);
        }
    }

    public int getNodeCount() {
        return nodePositions.size();
    }
}