package service;

import model.Transport;
import java.util.HashMap;
import java.util.Map;

public class TransportService {
    private Map<String, Transport> transports = new HashMap<>();

    public TransportService() {
        transports.put("Bus", new Transport("Bus", 5.0, 40));
        transports.put("Train", new Transport("Train", 3.0, 200));
        transports.put("Flight", new Transport("Flight", 10.0, 150));
    }

    public Transport getTransport(String type) {
        return transports.get(type);
    }
}
