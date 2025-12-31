package model;

import java.time.LocalDateTime;

public abstract class Message {
    private final String messageId;
    private final User sender;
    private final User receiver;
    private final LocalDateTime timestamp;
    private MessageStatus status;

    public Message(String messageId, User sender, User receiver) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
        this.status = MessageStatus.SENT;
    }

    public abstract void deliver();

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public MessageStatus getStatus() {
        return status;
    }

    protected void setStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "From: " + sender.getName() +
               ", To: " + receiver.getName() +
               ", Status: " + status +
               ", Time: " + timestamp;
    }
}

