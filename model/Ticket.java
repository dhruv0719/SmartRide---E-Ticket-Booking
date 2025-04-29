package model;

public class Ticket {
    private String ticketId;
    private String passengerName;
    private String source;
    private String destination;
    private String transport;
    private int seats;
    private double price;

    public Ticket(String ticketId, User user, Route route, Transport transport, int seats, double price) {
        this.ticketId = ticketId;
        this.passengerName = user.getName();
        this.source = route.getSource();
        this.destination = route.getDestination();
        this.transport = transport.getType();
        this.seats = seats;
        this.price = price;
    }

    @Override
    public String toString() {
        return "===== TICKET =====\n" +
               "Ticket ID: " + ticketId +
               "\nPassenger: " + passengerName +
               "\nFrom: " + source + " To: " + destination +
               "\nTransport: " + transport +
               "\nSeats: " + seats +
               "\nTotal Price: ₹" + price +
               "\n==================\n";
    }
}
