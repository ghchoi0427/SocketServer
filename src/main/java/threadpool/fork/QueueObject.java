package threadpool.fork;

import java.util.LinkedList;
import java.util.Queue;

public class QueueObject {
    private static QueueObject queueObject = new QueueObject();
    private Queue<String> queue = new LinkedList<>();

    public Queue<String> getQueue() {
        return queue;
    }

    int pollCount = 0;
    public String poll() {
        System.out.println("QueueObject.poll: " + (++pollCount));
        return queue.poll();
    }

    int count = 0;
    public void add(String s) {
        queue.add(s);
        System.out.println("QueueObject.add: "+(++count));
    }



    private QueueObject() {
    }

    public static QueueObject getInstance() {
        return queueObject;
    }
}
