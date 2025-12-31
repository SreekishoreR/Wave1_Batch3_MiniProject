package model;

public class Announcement extends Message {
    private String title;

    public Announcement(String id, User sender, User receiver, String title) {
        super(id, sender, receiver);
        this.title = title;
    }

    @Override
    public void deliver() {
        setStatus(MessageStatus.DELIVERED);
    }

    @Override
    public String toString() {
        return super.toString() + ", Announcement: " + title;
    }
}
