package ua.com.alevel.utils;

import org.junit.jupiter.api.*;
import ua.com.youtube.utils.Dictionary;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDictionary {
    private static final Dictionary<String, String> dictionary = new Dictionary<>();
    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < 100; i++) {
            dictionary.put("key " + i, "test " + i);
        }
    }

    @Test
    @Order(1)
    public void testPutWithTwoSameKeys() {
        int size = dictionary.size();
        dictionary.put("key 0", "test value");
        Assertions.assertEquals(dictionary.size(), size);
    }

    @Test
    @Order(2)
    public void testIsEmptyAfterSetUp() {
        Assertions.assertFalse(dictionary.isEmpty());
    }

    @Test
    @Order(3)
    public void testContainsKey() {
        Assertions.assertTrue(dictionary.containsKey("key 1"));
    }

    @Test
    @Order(4)
    public void testContainsValue() {
        Assertions.assertTrue(dictionary.containsValue("test value"));
    }

    @Test
    @Order(5)
    public void testGet() {
        Assertions.assertNotNull(dictionary.get("key 1"));
    }

    @Test
    @Order(6)
    public void testRemove() {
        int size = dictionary.size();
        dictionary.remove("key 90");
        Assertions.assertNotEquals(dictionary.size(), size);
    }

    @Test
    @Order(7)
    public void testPutAll() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            map.put("test key " + i, "test value");
        }
        int size = dictionary.size();
        dictionary.putAll(map);
        Assertions.assertEquals(size + map.size(), dictionary.size());
    }

    @Test
    @Order(8)
    public void testKeySet() {
        Assertions.assertEquals(dictionary.size(), dictionary.keySet().size());
    }

    @Test
    @Order(9)
    public void tesValues() {
        Assertions.assertEquals(dictionary.size(), dictionary.values().size());

    }

    @Test
    @Order(10)
    public void testEntrySet() {
        Assertions.assertEquals(dictionary.size(), dictionary.entrySet().size());
    }

    @Test
    @Order(11)
    public void testClear() {
        dictionary.clear();
        Assertions.assertEquals(dictionary.size(), 0);
    }
}
