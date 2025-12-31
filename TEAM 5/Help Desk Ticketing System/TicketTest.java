package helpdesk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    private TicketService service;
    private User user;

    @BeforeEach
    void setup() {
        service = new TicketService();
        user = new User(1, "Akash");
    }

    // Ticket creation success
    @Test
    void testTicketCreation() throws Exception {
        Ticket ticket = new SoftwareTicket(service.generateTicketId(), "Low", user);
        service.addTicket(ticket);

        assertEquals("Open", ticket.status);
        assertEquals("Low", ticket.priority);
        assertEquals(4, service.remainingSlots());
    }

    // Ticket limit enforcement (MAX = 5)
    @Test
    void testTicketLimitExceeded() throws Exception {
        for (int i = 1; i <= 5; i++) {
            service.addTicket(
                new HardwareTicket(service.generateTicketId(), "Medium", user)
            );
        }

        Exception exception = assertThrows(Exception.class, () -> {
            service.addTicket(
                new SoftwareTicket(service.generateTicketId(), "Low", user)
            );
        });

        assertEquals("No tickets available. Please try later.", exception.getMessage());
    }

    //  First Come First Serve (FCFS) validation
    @Test
    void testFirstComeFirstServeOrder() throws Exception {
        Ticket t1 = new SoftwareTicket(service.generateTicketId(), "Low", user);
        Ticket t2 = new HardwareTicket(service.generateTicketId(), "Medium", user);

        service.addTicket(t1);
        service.addTicket(t2);

        Ticket fetchedFirst = service.getTicket(t1.ticketId);
        Ticket fetchedSecond = service.getTicket(t2.ticketId);

        assertEquals(t1.ticketId, fetchedFirst.ticketId);
        assertEquals(t2.ticketId, fetchedSecond.ticketId);
    }

    // Ticket resolution
    @Test
    void testTicketResolution() throws Exception {
        Ticket ticket = new HardwareTicket(service.generateTicketId(), "High", user);
        service.addTicket(ticket);

        ticket.resolveTicket();

        assertEquals("Resolved", ticket.status);
    }

    // SLA escalation test
    @Test
    void testSLAEscalation() {
        Ticket ticket = new SoftwareTicket(200, "Low", user);
        ticket.createdTime = ticket.createdTime.minusHours(15); // SLA = 12 hrs

        ticket.checkSLA();

        assertEquals("High", ticket.priority);
    }

    //  Ticket not found exception
    @Test
    void testTicketNotFoundException() {
        Exception exception = assertThrows(Exception.class, () -> {
            service.getTicket(999);
        });

        assertEquals("Ticket ID not found.", exception.getMessage());
    }
}
