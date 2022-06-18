package pubandsub.entity;

public class MessageObject {
    String sender;
    String content;
    enum messageType {JOIN, CHAT, LEAVE}
}
