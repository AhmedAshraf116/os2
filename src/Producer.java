import java.util.*;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable{
    private final int MAX_CAPACITY;
    private BlockingQueue<Integer> queue ;
    public Producer( int size,BlockingQueue sharedQueue){

        this.queue=sharedQueue;
        this.MAX_CAPACITY = size;
    }
    private volatile int counter=0;
    @Override
    public void run(){
        while(true){
            try{
                produce(counter++);
            }
            catch (InterruptedException e){
                System.out.println("Interrupted Exception @ Producer Code");
                e.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException{
        synchronized(queue){
            while(queue.size()==MAX_CAPACITY){
                System.out.println(Thread.currentThread().getName() + " is waiting, size: " + queue.size());
                queue.wait();
            }
            Thread.sleep(500); // wait for a second
            queue.put(i); // add to task QUEUE
            System.out.println(Thread.currentThread().getName()+" -- add "+  i);
            queue.notifyAll();
        }
    }
}