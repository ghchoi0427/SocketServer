package pubandsub;

import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Topic {

    public static Queue<String> messageQueue;
    private List<Socket> publishers;
    private List<Socket> subscribers;
    public static Topic topic;

    public static Topic getInstance() {
        if (topic == null) {
            topic = new Topic();
        }
        return topic;
    }

    public List<Socket> getSubscribers() {
        return subscribers;
    }

    public void addPublisher(Socket pub) {
        publishers.add(pub);
    }

    public void addSubscriber(Socket sub) {
        subscribers.add(sub);
    }

    public Topic() {
        messageQueue = new LinkedList<>();
        publishers = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public Queue<String> getMessageQueue() {
        return messageQueue;
    }

    public void offerMessage(String message) {
        System.out.println("[TOPIC] offer message: " + message);
        messageQueue.offer(message);
    }

    public String pollMessage() {
        if (messageQueue.isEmpty()) {
            return null;
        }
        System.out.println("[TOPIC] poll message");
        return messageQueue.poll();
    }
}
