import model.*;
import service.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RouteService routeService = new RouteService();
        TransportService transportService = new TransportService();
        BookingService bookingService = new BookingService();

        System.out.println("=== Welcome to SmartRide E-Ticket Booking ===");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        User user = new User(name, phone);

        System.out.print("Enter Source City (Surat, Mumbai, Delhi, Bengaluru, Hyderabad): ");
        String source = capitalize(scanner.nextLine().toLowerCase());
        System.out.print("Enter Destination City (Surat, Mumbai, Delhi, Bengaluru, Hyderabad): ");
        String destination = capitalize(scanner.nextLine().toLowerCase());

        Route route = routeService.findRoute(source, destination);
        if (route == null) {
            System.out.println("No route available!");
            scanner.close();
            return;
        }

        System.out.print("Choose Transport (Bus/Train/Flight): ");
        String transportType = scanner.nextLine();
        Transport transport = transportService.getTransport(transportType);
        if (transport == null) {
            System.out.println("Invalid transport type!");
            scanner.close();
            return;
        }

        System.out.print("Enter number of seats to book: ");
        int seats = scanner.nextInt();

        Ticket ticket = bookingService.bookTicket(user, route, transport, seats);
        if (ticket != null) {
            System.out.println(ticket);
        }
        
        System.out.println("=== Thank you for booking with SmartRide! ===");
        scanner.close();
    }
    
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
