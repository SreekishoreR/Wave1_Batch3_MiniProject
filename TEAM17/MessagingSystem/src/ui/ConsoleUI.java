package ui;

import model.*;
import repository.MessageRepository;
import service.MessageService;

public class ConsoleUI {
    public static void main(String[] args) {

        User userXYZ = new User("U1", "User XYZ", "HR");
        User userABC = new User("U2", "User ABC", "IT");

        MessageRepository repo = new MessageRepository();
        MessageService service = new MessageService(repo);

        Message msg1 = new TextMessage("M1", userXYZ, userABC, "Hello there");
        Message msg2 = new Announcement("M2", userABC, userXYZ, "Team Meeting at 5 PM");

        service.send(msg1);
        service.send(msg2);

        System.out.println("=== User XYZ Inbox ===");
        for (Message m : service.inbox(userXYZ)) {
            System.out.println(m);
        }

        System.out.println("\n=== User ABC Inbox ===");
        for (Message m : service.inbox(userABC)) {
            System.out.println(m);
        }
    }
}
