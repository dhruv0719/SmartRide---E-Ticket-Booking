package model;

public class Transport {
    private String type;
    private double pricePerKm;
    private int availableSeats;

    public Transport(String type, double pricePerKm, int availableSeats) {
        this.type = type;
        this.pricePerKm = pricePerKm;
        this.availableSeats = availableSeats;
    }

    public String getType() {
        return type;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeats(int seats) {
        this.availableSeats -= seats;
    }
}
