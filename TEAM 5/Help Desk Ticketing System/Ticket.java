package helpdesk;

import java.time.LocalDateTime;

public abstract class Ticket {
    protected int ticketId;
    protected String priority;
    protected String status;
    protected User createdBy;
    protected LocalDateTime createdTime;
    protected int slaHours;

    public Ticket(int ticketId, String priority, User createdBy, int slaHours) {
        this.ticketId = ticketId;
        this.priority = priority;
        this.createdBy = createdBy;
        this.slaHours = slaHours;
        this.status = "Open";
        this.createdTime = LocalDateTime.now();
    }

    public abstract void resolveTicket();

    public void checkSLA() {
        boolean breached = false;

        if (!status.equals("Resolved")) {
            LocalDateTime deadline = createdTime.plusHours(slaHours);

            if (LocalDateTime.now().isAfter(deadline)) {
                priority = "High";
                System.out.println("⚠ SLA breached! Ticket " + ticketId +
                        " escalated to HIGH priority.");
                breached = true;
            }
        }

        if (!breached) {
            System.out.println("✔ Ticket " + ticketId + " is within SLA.");
        }
    }

    public void display() {
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Priority: " + priority);
        System.out.println("Status: " + status);
        System.out.println("Created By: " + createdBy.getName());
    }
}
