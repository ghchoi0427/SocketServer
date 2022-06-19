import client.ChatClient;

public class ClientApplication {
    public static void main(String[] args) {
        ChatClient cl1 = new ChatClient();
        ChatClient cl2 = new ChatClient();
        cl1.start();
        cl2.start();
    }
}
