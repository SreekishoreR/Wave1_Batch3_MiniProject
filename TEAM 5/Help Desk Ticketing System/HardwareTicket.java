package helpdesk;

public class HardwareTicket extends Ticket {

    public HardwareTicket(int ticketId, String priority, User user) {
        super(ticketId, priority, user, 24); // 24 hours SLA
    }

    @Override
    public void resolveTicket() {
        status = "Resolved";
        System.out.println("Hardware issue resolved.");
    }
}
