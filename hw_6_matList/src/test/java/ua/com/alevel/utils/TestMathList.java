package ua.com.alevel.utils;

import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMathList {
    private static final MathList<Integer> mathList = new MathList<>();
    private static final Random random = new Random();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < 20; i++) {
            mathList.add(i *10);
        }
    }

    @Test
    @Order(1)
    public void testConstructorWithMathLists() {
        MathList<Integer> mathList1 = new MathList<>(1, 2, 3, 4, 5);
        MathList<Integer> mathList2 = new MathList<>(6, 7, 8, 9, 10);
        MathList<Integer> result = new MathList<>(mathList1, mathList2);
        Assertions.assertEquals(result.size(), mathList1.size() + mathList2.size());
    }

    @Test
    @Order(2)
    public void testConstructorWithArrays() {
        Number[] ints = new Integer[] {1, 2, 3, 4};
        Number[] doubles = new Double[] {1.2, 1.3, 1.4};
        MathList<Number> mathList1 = new MathList<>(ints, doubles);
        Assertions.assertEquals(mathList1.size(), ints.length + doubles.length);
    }

    @Test
    @Order(3)
    public void testIsEmpty() {
        Assertions.assertTrue(new MathList<>().isEmpty());
    }

    @Test
    @Order(4)
    public void testContains() {
        Assertions.assertTrue(mathList.contains(mathList.get(2)));
    }

    @Test
    @Order(5)
    public void testIterator() {
        Number number1 = 90;
        Number number2 = 99;
        for (Number number : mathList) {
            if (number.intValue() == number1.intValue()) {
                mathList.set(mathList.indexOf(number), number2);
            }
        }
        Assertions.assertTrue(mathList.contains(number2));
    }

    @Test
    @Order(6)
    public void testToArray() {
        int before = mathList.toArray().length;
        int after = mathList.size();
        Assertions.assertEquals(before, after);
    }

    @Test
    @Order(7)
    public void testCut() {
        Assertions.assertEquals(5 - 2, mathList.cut(2, 5).toArray().length);
    }

    @Test
    @Order(8)
    public void testAddNumberMethods() {
        int size = mathList.size();
        mathList.add(9);
        mathList.add(11, 13, 15);
        Assertions.assertEquals(size + 4, mathList.size());
    }

    @Test
    @Order(9)
    public void testRemove() {
        int size = mathList.size();
        mathList.remove(mathList.get(5));
        Assertions.assertEquals(size - 1, mathList.size());
    }

    @Test
    @Order(10)
    public void testRemoveWithInvalidData() {
        int size = mathList.size();
        mathList.remove(-1.2345);
        Assertions.assertEquals(size, mathList.size());
    }

    @Test
    @Order(11)
    public void testContainsAll() {
        Assertions.assertTrue(mathList.containsAll(new MathList<>(100, 120, 140)));
    }

    @Test
    @Order(12)
    public void testAddAll() {
        MathList<Number> mathList1 = new MathList<>(-1, -2, -3);
        mathList.addAll(1, mathList1);
        Assertions.assertFalse(mathList.containsAll(mathList1));
    }

    @Test
    @Order(13)
    public void testJoin() {
        MathList<Integer>[] mathLists = new MathList[3];
        for (int i = 0; i < mathLists.length; i++){
            mathLists[i] = new MathList<>();
            for (int j = 0; j < 5; j++) {
                mathLists[i].add(random.nextInt(1000));
            }
        }
        mathList.join(mathLists);
        for (MathList<Integer> list : mathLists) {
            Assertions.assertTrue(mathList.containsAll(list));
        }
    }

    @Test
    @Order(14)
    public void testIntersection() {
        MathList<Number> mathList1 = new MathList<>();
        MathList<Number> mathList2 = new MathList<>();
        MathList<Number> mathList3 = new MathList<>();
        mathList1.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        mathList2.add(2, 4, 6, 8, 10);
        mathList3.add(4, 8, 12, 16, 20);
        mathList1.intersection(mathList2, mathList3);
        Assertions.assertEquals(mathList1.size(), 2);
    }

    @Test
    @Order(15)
    public void testRemoveAll() {
        MathList<Integer> values = new MathList<>();
        for (int i = 0; i < 20; i++) {
            values.add(i);
        }
        MathList<Integer> removingValues = new MathList<>();
        for (int i = 0; i < 10; i++) {
            removingValues.add(i);
        }
        values.removeAll(removingValues);
        Assertions.assertEquals(values.size(), 20 - removingValues.size());
    }

    @Test
    @Order(16)
    public void testRetainAll() {
        MathList<Integer> values = new MathList<>();
        for (int i = 0; i < 20; i++) {
            values.add(i);
        }
        MathList<Integer> retainValues = new MathList<>();
        for (int i = 0; i < 10; i++) {
            retainValues.add(i);
        }
        values.retainAll(retainValues);
        Assertions.assertEquals(values.size(), retainValues.size());
    }

    @Test
    @Order(17)
    public void testSortAsc() {
        MathList<Integer> values = new MathList<>();
        for (int i = 0; i < 20; i++) {
            values.add(random.nextInt(100));
        }
        values.sortAsc();
        //firstIndex and secondIndex are random indexes, which compare two random numbers.
        //Following the method sortAsc(), the values have to be sorted from the smallest to the biggest one.
        int firstIndex = random.nextInt(10);
        int secondIndex = random.nextInt(10) + 10;
        Assertions.assertTrue(values.get(firstIndex) < values.get(secondIndex));
    }

    @Test
    @Order(18)
    public void testSortDesc() {
        MathList<Integer> values = new MathList<>();
        for (int i = 0; i < 20; i++) {
            values.add(random.nextInt(100));
        }
        values.sortDesc();
        //firstIndex and secondIndex are random indexes, which compare two random numbers.
        //Following the method sortAsc(), the values have to be sorted from the biggest to the smallest one.
        int firstIndex = random.nextInt(10);
        int secondIndex = random.nextInt(10) + 10;
        Assertions.assertTrue(values.get(firstIndex) > values.get(secondIndex));
    }

    @Test
    @Order(19)
    public void testClearByArray() {
        MathList<Integer> values = new MathList<>();
        for (int i = 0; i < 20; i++) {
            values.add(i);
        }
        MathList<Integer> removingValues = new MathList<>();
        for (int i = 0; i < 10; i++) {
            removingValues.add(i);
        }
        values.clear(removingValues.toArray());
        Assertions.assertEquals(values.size(), 20 - removingValues.size());
    }

    @Test
    @Order(20)
    public void testGetMax() {
        MathList<Integer> values = new MathList<>();
        for (int i = 0; i < 20; i++) {
            values.add(i);
        }
        int max = 100;
        values.add(max);
        Assertions.assertEquals(values.getMax(), max);
    }

    @Test
    @Order(21)
    public void testGetMin() {
        MathList<Integer> values = new MathList<>();
        for (int i = 0; i < 20; i++) {
            values.add(i);
        }
        int min = -100;
        values.add(min);
        Assertions.assertEquals(values.getMin(), min);
    }
}
