package ua.com.alevel.utils;

import java.util.*;
import java.util.function.Consumer;

public class MathList<T extends Number> implements List<Number> {
    private static final int DEFAULT_SIZE = 10;
    private Number[] data;
    private int size;

    public MathList() {
        data = new Number[DEFAULT_SIZE];
    }

    @SafeVarargs
    public MathList(T... numbers) {
        data = numbers;
        size = numbers.length;
    }

    @SafeVarargs
    public MathList(T[]... numbers) {
        data = new Number[DEFAULT_SIZE];
        for (Number[] number : numbers) {
            addAll(List.of(number));
        }
    }

    @SafeVarargs
    public MathList(MathList<T>... lists) {
        data = new Number[DEFAULT_SIZE];
        for (MathList<T> list : lists) {
            addAll(list);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathList<T> mathList = (MathList<T>) o;
        return size == mathList.size && Arrays.equals(data, mathList.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
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
        for (int i = 0; i < size; i++)
            if (data[i].equals(o))
                return true;
        return false;
    }

    @Override
    public Iterator<Number> iterator() {
        return (Iterator<Number>) new Itr<>();
    }

    @Override
    public Number[] toArray() {
        Number[] numbers = new Number[size];
        System.arraycopy(data, 0, numbers, 0, size);
        return numbers;
    }

    public T[] toArray(int begin, int end) {
        if ((begin < 0 || end > size) || begin > end)
            throw new NoSuchElementException();
        Number[] numbers = new Number[end - begin];
        if (end - 1 - begin >= 0) System.arraycopy(data, begin, numbers, 0, end - 1 - begin);
        return (T[])numbers;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size)
            return (E[]) Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, Arrays.stream(a).toArray(), 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public MathList<T> cut(int begin, int end) {
        return new MathList<>(this.toArray(begin, end));
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

    @SafeVarargs
    public final void add(T... numbers) {
        addAll(Arrays.asList(numbers));
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
        for (Object v : c.toArray())
            if (!contains(v))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Number> c) {
        for (Object number : c.toArray()) {
            add((T) number);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Number> c) {
        if (c == null)
            throw new NoSuchElementException();
        if (index < 0 && index >= size) return false;
        Object[] numbers = c.toArray();
        for (int i = index; i < numbers.length; i++) {
            add((T)numbers[i]);
        }
        return true;
    }

    @SafeVarargs
    public final void join(MathList<T>... mathLists) {
        for (MathList<T> mathList : mathLists) {
            add((T[])mathList.toArray());
        }
    }

    @SafeVarargs
    public final void intersection(MathList<T>... mathLists) {
        for (int i = 0; i < size; i++) {
            for (MathList<T> mathList : mathLists) {
                if (!mathList.contains(get(i))) {
                    remove(get(i));
                    i--;
                    break;
                }
            }
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c)
            remove(o);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null)
            throw new NoSuchElementException();
        for (int i = 0; i < size; i++)
            if (!c.contains(data[i])) {
                remove(data[i]);
                i--;
            }
        return true;
    }

    public void sortDesc() {
        sortDesc(0, size);
    }

    public void sortDesc(T number) {
        if (indexOf(number) != -1)
            sortDesc(indexOf(number), size);
    }

    public void sortDesc(int begin, int end) {
        boolean changes;
        do {
            changes = false;
            for (int i = begin; i < end - 1; i++) {
                if (data[i].doubleValue() < data[i + 1].doubleValue()) {
                    Number temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    changes = true;
                }
            }
        } while (changes);
    }

    public void sortAsc() {
        sortAsc(0, size);
    }

    public void sortAsc(T number) {
        if (indexOf(number) != -1)
            sortAsc(indexOf(number), size);
    }

    public void sortAsc(int begin, int end) {
        boolean changes;
        do {
            changes = false;
            for (int i = begin; i < end - 1; i++) {
                if (data[i].doubleValue() > data[i + 1].doubleValue()) {
                    Number temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    changes = true;
                }
            }
        } while (changes);
    }

    @Override
    public void clear() {
        data = new Number[DEFAULT_SIZE];
        size = 0;
    }

    public void clear(Number[] numbers) {
        removeAll(Arrays.stream(numbers).toList());
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return (T)data[index];
    }

    public T getMax() {
        if (size == 0)
            throw new NoSuchElementException();
        T number = get(0);
        for (int i = 0; i < size; i++)
            if (number.doubleValue() < get(i).doubleValue())
                number = get(i);
        return number;
    }

    public T getMin() {
        if (size == 0)
            throw new NoSuchElementException();
        T number = get(0);
        for (int i = 0; i < size; i++)
            if (number.doubleValue() > get(i).doubleValue())
                number = get(i);
        return number;
    }

    public double getAverage() {
        double number = 0.0;
        for (int i = 0; i < size; i++) {
            number += get(i).doubleValue();
        }
        return number / size;
    }

    public T getMedian() {
        if (size == 0)
            throw new NoSuchElementException();
        return get(size / 2);
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
    public T remove(int index) {
        if (index < 0 || index >= size) return null;
        T number = (T)data[index];
        remove(number);
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
        return (ListIterator<Number>) new ListItr<>(0);
    }

    @Override
    public ListIterator<Number> listIterator(int index) {
        if (index > size || index < 0)
            throw new NoSuchElementException();
        return (ListIterator<Number>) new ListItr<>(index);
    }


    @Override
    public List<Number> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size) return new ArrayList<>();
        if (fromIndex > toIndex) return new ArrayList<>();
        return new ArrayList<>(List.of(toArray(fromIndex, toIndex)));
    }

    private class Itr<E extends T> implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = size;

        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            checkForCoModification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Number[] elementData = MathList.this.data;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E)elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForCoModification();

            try {
                MathList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = size;
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            final int size = MathList.this.size;
            int i = cursor;
            if (i < size) {
                final Number[] es = data;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && size == expectedModCount; i++)
                    action.accept((T)es[i]);
                cursor = i;
                lastRet = i - 1;
                checkForCoModification();
            }
        }

        final void checkForCoModification() {
            if (size != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr<E extends T> extends Itr<T> implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public E previous() {
            checkForCoModification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Number[] elementData = MathList.this.data;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E)elementData[lastRet = i];
        }

        public void set(T e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForCoModification();

            try {
                MathList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(T e) {
            checkForCoModification();

            try {
                int i = cursor;
                MathList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = size;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
