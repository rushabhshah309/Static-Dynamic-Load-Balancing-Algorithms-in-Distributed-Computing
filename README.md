# Static-Dynamic-Load-Balancing-Algorithms-in-Distributed-Computing

This repository contains three Java programs related to load balancing and hashing algorithms. These programs are designed to be used as libraries and are not standalone applications.

## Program 1: Consistent Hashing
The implementation of the Consistent Hashing algorithm uses a map of nodes to lists of positions on the ring, and a map of positions on the ring to nodes. The addNode method computes the positions on the ring for the node, and adds them to the maps. The removeNode method removes all positions for the node from the maps. The getNodeCount method returns the size of the node map.

## Program 2: Honey Bee Algorithm Load Balancer
The HoneyBeeAlgorithmLoadBalancer class is an implementation of the honey bee algorithm, which is used to balance load across multiple servers. This class takes a list of Server objects, a maximum number of iterations, and an abandonment threshold as input and provides a method for balancing the load across the servers.

## Program 3: Cloud Computing Round Robin Scheduler
The program takes in the arrival time and burst time of each process as well as the time quantum for the algorithm. It then calculates the turnaround time and waiting time for each process and outputs the results to the console.
The main method initializes the input parameters and calls the roundRobin method.
The roundRobin method implements the Round Robin algorithm and calculates the turnaround time and waiting time for each process, as well as the average turnaround time and waiting time for all processes. It also outputs the results.

# Requirements
Java 8 or later
