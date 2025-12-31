package helpdesk;

public class SoftwareTicket extends Ticket {

    public SoftwareTicket(int ticketId, String priority, User user) {
        super(ticketId, priority, user, 12); // 12 hours SLA
    }

    @Override
    public void resolveTicket() {
        status = "Resolved";
        System.out.println("Software issue resolved.");
    }
}
