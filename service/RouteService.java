package service;

import model.Route;
import java.util.HashMap;
import java.util.Map;

public class RouteService {
    private Map<String, Integer> routes = new HashMap<>();

    public RouteService() {
        routes.put("Surat-Mumbai", 300);
        routes.put("Mumbai-Delhi", 1200);
        routes.put("Surat-Delhi", 1400);
        routes.put("Surat-Bengaluru", 1600);
        routes.put("Mumbai-Bengaluru", 1000);
        routes.put("Delhi-Bengaluru", 1500);
        routes.put("Hyderabad-Bengaluru", 700);
        routes.put("Mumbai-Hyderabad", 800);
        routes.put("Surat-Hyderabad", 1300);
        routes.put("Delhi-Hyderabad", 1100);
    }

    public Route findRoute(String source, String destination) {
        String key = source + "-" + destination;
        if (routes.containsKey(key)) {
            return new Route(source, destination, routes.get(key));
        } else {
            return null;
        }
    }    
}
