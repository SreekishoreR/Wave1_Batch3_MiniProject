package service;

import model.Message;
import model.MessageStatus;
import model.User;
import repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void send(Message message) {
        message.deliver();
        repository.save(message);
    }

    public List<Message> inbox(User user) {
        return repository.findInbox(user);
    }

    public List<Message> outbox(User user) {
        return repository.findOutbox(user);
    }

    public List<Message> filterByStatus(User user, MessageStatus status) {
        List<Message> result = new ArrayList<>();
        for (Message m : inbox(user)) {
            if (m.getStatus() == status) {
                result.add(m);
            }
        }
        return result;
    }
}
