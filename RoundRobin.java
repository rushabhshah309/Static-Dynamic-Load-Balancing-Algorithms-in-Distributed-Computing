import java.util.ArrayList;

public class CloudComputingRR {
    public static void main(String[] args) {
        int[] arrivalTime = {0, 2, 4, 5, 7}; // arrival time of processes
        int[] burstTime = {5, 3, 1, 2, 4}; // burst time of processes
        int timeQuantum = 2; // time quantum for round robin algorithm
        
        roundRobin(arrivalTime, burstTime, timeQuantum);
    }
    
    public static void roundRobin(int[] arrivalTime, int[] burstTime, int timeQuantum) {
        ArrayList<Integer> queue = new ArrayList<>(); // queue to store processes
        int n = arrivalTime.length; // number of processes
        int[] remainingTime = new int[n]; // remaining time for each process
        int[] turnaroundTime = new int[n]; // turnaround time for each process
        int[] waitingTime = new int[n]; // waiting time for each process
        
        for (int i = 0; i < n; i++) {
            remainingTime[i] = burstTime[i];
            queue.add(i);
        }
        
        int time = 0; // current time
        int count = 0; // count of completed processes
        
        while (count < n) {
            int process = queue.get(0); // get the first process in the queue
            queue.remove(0); // remove the process from the queue
            
            if (remainingTime[process] <= timeQuantum) {
                time += remainingTime[process];
                remainingTime[process] = 0;
                count++;
                turnaroundTime[process] = time - arrivalTime[process];
                waitingTime[process] = turnaroundTime[process] - burstTime[process];
            } else {
                time += timeQuantum;
                remainingTime[process] -= timeQuantum;
            }
            
            // add new processes to the queue
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= time && !queue.contains(i) && remainingTime[i] > 0) {
                    queue.add(i);
                }
            }
            
            // move the current process to the end of the queue if it still has remaining time
            if (remainingTime[process] > 0) {
                queue.add(process);
            }
        }
        
        // calculate average turnaround time and waiting time
        double avgTurnaroundTime = 0;
        double avgWaitingTime = 0;
        for (int i = 0; i < n; i++) {
            avgTurnaroundTime += turnaroundTime[i];
            avgWaitingTime += waitingTime[i];
        }
        avgTurnaroundTime /= n;
        avgWaitingTime /= n;
        
        System.out.println("Process\tArrival Time\tBurst Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
    }
}
