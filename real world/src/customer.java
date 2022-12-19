import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class customer implements Runnable {
    private  BlockingQueue<Integer> queue ;
    public customer(BlockingQueue sharedQueue) {
        this.queue=sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception @ Consumer Code");
                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {

                System.out.println(Thread.currentThread().getName() + " is waiting, size: " + queue.size());
                queue.wait();
            }
            Thread.sleep(500);
            int i = (Integer) queue.take(); // remove from front
            System.out.println("\t"+Thread.currentThread().getName()+ " remove==>" + i);
            queue.notifyAll();
        }
    }
}