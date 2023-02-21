package ua.com.alevel.persistence.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public final class ResourceUtil {
    private ResourceUtil() {}

    public static Map<String, String> getResources(ClassLoader main) {
        try (InputStream stream = main.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            Map<String, String> map = new TreeMap<>();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            return map;
        } catch (IOException e) {
            throw new SecurityException("File nor loaded");
        }
    }
}
