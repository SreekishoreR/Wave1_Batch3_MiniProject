package repository;

import model.Message;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    private final List<Message> messages = new ArrayList<>();

    public void save(Message message) {
        messages.add(message);
    }

    public List<Message> findInbox(User user) {
        List<Message> inbox = new ArrayList<>();
        for (Message m : messages) {
            if (m.getReceiver().equals(user)) {
                inbox.add(m);
            }
        }
        return inbox;
    }

    public List<Message> findOutbox(User user) {
        List<Message> outbox = new ArrayList<>();
        for (Message m : messages) {
            if (m.getSender().equals(user)) {
                outbox.add(m);
            }
        }
        return outbox;
    }
}
