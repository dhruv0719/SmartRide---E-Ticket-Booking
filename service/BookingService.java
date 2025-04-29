package service;

import model.*;
import java.io.FileWriter;
import java.io.IOException;
import model.Ticket;

import utils.IDGenerator;

public class BookingService {
    public Ticket bookTicket(User user, Route route, Transport transport, int seats) {
        if (seats > transport.getAvailableSeats()) {
            System.out.println("Not enough seats available!");
            return null;
        }
        transport.bookSeats(seats);
        double price = route.getDistance() * transport.getPricePerKm() * seats;
        String ticketId = IDGenerator.generateId();
        Ticket ticket = new Ticket(ticketId, user, route, transport, seats, price);

        // Print to console
        System.out.println(ticket);

        // Save to file
        try {
            FileWriter writer = new FileWriter("tickets.txt", true); // Append mode
            writer.write(ticket.toString());
            writer.close();
            System.out.println("Ticket successfully saved to tickets.txt");
        } catch (IOException e) {
            System.out.println("Error writing ticket to file: " + e.getMessage());
        }
        
        return ticket;
    }
}

