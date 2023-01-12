package ua.com.alevel.utils;

import java.util.*;

public class MathList implements List<Number> {
    private static final int DEFAULT_SIZE = 10;
    private Number[] data;
    private int size;

    public MathList() {
        data = new Number[DEFAULT_SIZE];
    }

    public MathList(Number... numbers) {
        data = numbers;
        size = numbers.length;
    }

    public MathList(Number[]... numbers) {
        for (Number[] number : numbers) {
            addAll(List.of(number));
        }
    }

    public MathList(MathList... lists) {
        for (MathList list : lists) {
            addAll(list);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Number number : data)
            if (number.equals(o))
                return true;
        return false;
    }

    @Override
    public Iterator<Number> iterator() {
        return null;
    }

    @Override
    public Number[] toArray() {
        Number[] numbers = new Number[size];
        System.arraycopy(data, 0, numbers, 0, size);
        return numbers;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(Number number) {
        if (size == data.length) {
            Number[] numbers = new Number[size * 2];
            System.arraycopy(data, 0, numbers, 0, size);
            data = numbers;
        }
        data[size] = number;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean exist = false;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                index = i;
                exist = true;
                break;
            }
        }
        if (exist) {
            for (int i = index; i < size - 1; i++)
                data[i] = data[i + 1];
            size--;
        }
        return exist;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var v : c.toArray())
            if (!contains(v))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Number> c) {
        for (Object number : c.toArray())
            add((Number) number);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Number> c) {
        if (c == null) return false;
        if (index < 0 && index >= size) return false;
        Object[] numbers = c.toArray();
        for (int i = index; i < c.size(); i++) {
            add((Number)numbers[i]);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c)
            remove(o);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) return false;
        for (int i = 0; i < size; i++)
            if (!c.contains(data[i])) {
                remove(data[i]);
                i--;
            }
        return true;
    }

    @Override
    public void clear() {
        data = new Number[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public Number get(int index) {
        if (index < 0 || index >= size) return null;
        return data[index];
    }

    @Override
    public Number set(int index, Number element) {
        if (index < 0 || index >= size) return null;
        data[index] = element;
        return data[index];
    }

    @Override
    public void add(int index, Number element) {
        if (index < 0 || index >= size) return;
        add(0);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
    }

    @Override
    public Number remove(int index) {
        if (index < 0 || index >= size) return null;
        Number number = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        return number;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < size; i++)
            if (data[i].equals(o))
                lastIndex = i;
        return lastIndex;
    }

    @Override
    public ListIterator<Number> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Number> listIterator(int index) {
        return null;
    }

    @Override
    public List<Number> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size) return new ArrayList<>();
        if (fromIndex > toIndex) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(data).subList(fromIndex, toIndex));
    }
}
