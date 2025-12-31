package helpdesk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TicketService service = new TicketService();

        while (true) {
            try {
                System.out.println("\n===== HELP DESK SYSTEM =====");
                System.out.println("1. Create Hardware Ticket");
                System.out.println("2. Create Software Ticket");
                System.out.println("3. Resolve Ticket");
                System.out.println("4. View Tickets");
                System.out.println("5. Check SLA");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {

                    case 1:
                    case 2:
                        if (!service.hasAvailability()) {
                            System.out.println("No tickets available.");
                            break;
                        }

                        System.out.print("Enter User Name: ");
                        String name = sc.nextLine();
                        User user = new User(service.remainingSlots(), name);

                        System.out.print("Enter Priority (Low/Medium/High): ");
                        String priority = sc.nextLine();

                        int ticketId = service.generateTicketId();

                        Ticket ticket;
                        if (choice == 1) {
                            ticket = new HardwareTicket(ticketId, priority, user);
                        } else {
                            ticket = new SoftwareTicket(ticketId, priority, user);
                        }

                        service.addTicket(ticket);
                        break;

                    case 3:
                        System.out.print("Enter Ticket ID to resolve: ");
                        int id = sc.nextInt();
                        service.getTicket(id).resolveTicket();
                        break;

                    case 4:
                        service.showAllTickets();
                        break;

                    case 5:
                        service.checkAllSLAs();
                        break;

                    case 6:
                        System.out.println("Exiting application...");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
