import java.util.ArrayList;
import java.util.Scanner;

class Ticket {
    int ticketId;
    String passengerName;
    String trainName;
    int seatNumber;

    public Ticket(int ticketId, String passengerName, String trainName, int seatNumber) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.trainName = trainName;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", Passenger Name: " + passengerName +
                ", Train Name: " + trainName + ", Seat Number: " + seatNumber;
    }
}

public class RailwayReservationSystem {
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private static int nextTicketId = 1;
    private static int maxSeats = 10; // Max seats available on the train

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the Railway Reservation System!");
        
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Tickets");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    bookTicket(scanner);
                    break;
                case 2:
                    cancelTicket(scanner);
                    break;
                case 3:
                    viewTickets();
                    break;
                case 4:
                    System.out.println("Thank you for using the Railway Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void bookTicket(Scanner scanner) {
        if (tickets.size() >= maxSeats) {
            System.out.println("Sorry, no seats are available.");
            return;
        }

        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();

        int seatNumber = tickets.size() + 1; // Assign next available seat
        Ticket ticket = new Ticket(nextTicketId++, passengerName, trainName, seatNumber);
        tickets.add(ticket);

        System.out.println("Ticket booked successfully!");
        System.out.println(ticket);
    }

    private static void cancelTicket(Scanner scanner) {
        System.out.print("Enter Ticket ID to cancel: ");
        int ticketId = scanner.nextInt();

        Ticket ticketToCancel = null;
        for (Ticket ticket : tickets) {
            if (ticket.ticketId == ticketId) {
                ticketToCancel = ticket;
                break;
            }
        }

        if (ticketToCancel != null) {
            tickets.remove(ticketToCancel);
            System.out.println("Ticket canceled successfully!");
        } else {
            System.out.println("Ticket not found!");
        }
    }

    private static void viewTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets have been booked yet.");
        } else {
            System.out.println("Booked Tickets:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }
    }
}
