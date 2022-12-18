import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Main {
    public static void main(String[] args) {
        System.out.println("\nThis is a Java implementation of the Producer-Consumer Problem\n");
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        int MAX_CAPACITY = 5; // Queue Size

        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer(MAX_CAPACITY, queue);
            Thread producerThread = new Thread(producer, "_" + (i + 1));
            producerThread.start();
        }
        for (int j = 0; j < 3; j++) {
            Consumer consumer = new Consumer(queue);
            Thread consumerThread = new Thread(consumer, "Consumer_" + (j + 1));
            consumerThread.start();
        }
    }
}