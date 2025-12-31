package model;

public class TextMessage extends Message {
    private String content;

    public TextMessage(String id, User sender, User receiver, String content) {
        super(id, sender, receiver);
        this.content = content;
    }

    @Override
    public void deliver() {
        setStatus(MessageStatus.DELIVERED);
    }

    @Override
    public String toString() {
        return super.toString() + ", Content: " + content;
    }
}
