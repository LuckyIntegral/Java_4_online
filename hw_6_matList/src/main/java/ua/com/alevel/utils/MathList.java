package ua.com.alevel.utils;

import java.util.*;
import java.util.function.Consumer;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathList mathList = (MathList) o;
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
        for (Number number : data)
            if (number.equals(o))
                return true;
        return false;
    }

    //return new Iterator<Number>() {
    //            private int totalSize = size;
    //            private int current = 0;
    //            @Override
    //            public boolean hasNext() {
    //                return current < totalSize;
    //            }
    //
    //            @Override
    //            public Number next() {
    //                Number n = data[current];
    //                if (current == totalSize - 1) {
    //                    current = 0;
    //                } else {
    //                    current++;
    //                }
    //                return n;
    //            }
    //
    //            @Override
    //            public void remove() {
    //                MathList.this.remove(current);
    //                totalSize = size;
    //                current--;
    //            }
    //
    //            @Override
    //            public void forEachRemaining(Consumer<? super Number> action) {
    //                Iterator.super.forEachRemaining(action);
    //            }
    //        };

    @Override
    public Iterator<Number> iterator() {
        return new Itr();
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
        return new ListItr(0);
    }

    @Override
    public ListIterator<Number> listIterator(int index) {
        if (index > size || index < 0)
            throw new NoSuchElementException();
        return new ListItr(index);
    }

    @Override
    public List<Number> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size) return new ArrayList<>();
        if (fromIndex > toIndex) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(data).subList(fromIndex, toIndex));
    }

    private class Itr implements Iterator<Number> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = size;

        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        public Number next() {
            checkForCoModification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Number[] elementData = MathList.this.data;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return elementData[lastRet = i];
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
        public void forEachRemaining(Consumer<? super Number> action) {
            Objects.requireNonNull(action);
            final int size = MathList.this.size;
            int i = cursor;
            if (i < size) {
                final Number[] es = data;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && size == expectedModCount; i++)
                    action.accept(es[i]);
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

    private class ListItr extends Itr implements ListIterator<Number> {
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

        public Number previous() {
            checkForCoModification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Number[] elementData = MathList.this.data;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return elementData[lastRet = i];
        }

        public void set(Number e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForCoModification();

            try {
                MathList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(Number e) {
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
