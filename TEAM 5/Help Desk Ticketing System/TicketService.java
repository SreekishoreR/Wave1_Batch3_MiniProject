package helpdesk;

import java.util.LinkedHashMap;
import java.util.Map;

public class TicketService {

    private static final int MAX_TICKETS = 5;
    private Map<Integer, Ticket> ticketMap = new LinkedHashMap<>();
    private int ticketCounter = 100;

    public boolean hasAvailability() {
        return ticketMap.size() < MAX_TICKETS;
    }

    public int remainingSlots() {
        return MAX_TICKETS - ticketMap.size();
    }

    public void addTicket(Ticket ticket) throws Exception {
        if (!hasAvailability()) {
            throw new Exception("No tickets available. Please try later.");
        }
        ticketMap.put(ticket.ticketId, ticket);
        System.out.println("Ticket created successfully.");
        System.out.println("Remaining ticket slots: " + remainingSlots());
    }

    public int generateTicketId() {
        return ++ticketCounter;
    }

    public Ticket getTicket(int id) throws Exception {
        if (!ticketMap.containsKey(id)) {
            throw new Exception("Ticket ID not found.");
        }
        return ticketMap.get(id);
    }

    public void showAllTickets() {
        if (ticketMap.isEmpty()) {
            System.out.println("No tickets available.");
            return;
        }
        for (Ticket t : ticketMap.values()) {
            t.display();
            System.out.println();
        }
    }

    public void checkAllSLAs() {
        for (Ticket t : ticketMap.values()) {
            t.checkSLA();
        }
    }
}
