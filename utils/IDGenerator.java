package utils;

import java.util.UUID;

public class IDGenerator {
    public static String generateId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
