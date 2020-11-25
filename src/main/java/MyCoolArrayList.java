package main.java;

import java.util.*;


public class MyCoolArrayList<E> implements List<E> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    transient Object[] elements;
    private int size;

    public MyCoolArrayList(int size) {
        if (size > 0) {
            this.elements = new Object[size];
        } else if (size == 0) {
            this.elements = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal argument" + size);
        }
    }

    public MyCoolArrayList() {
        super();
        this.elements = EMPTY_ELEMENT_DATA;
    }

    public MyCoolArrayList(Collection<? extends E> c) {
        elements = c.toArray();
        size = elements.length;
        if (elements.getClass() != Object[].class)
            elements = Arrays.copyOf(elements, size, Object[].class);
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
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {  //Google about the method
       if (a.length < size) {
           return (T[]) Arrays.copyOf(elements, size, a.getClass());
       }
       System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        capacityInternal(size + 1);
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkRange(index);

        capacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elements, index, elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null) {
                    removeElem(i);
                    return true;
            }
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i])) {
                    removeElem(i);
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (size != c.size()) return false;
        else {
           for (Object o : c)
               if (!contains(o))
                    return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        capacityInternal(size + numNew);
        System.arraycopy(a, 0, elements, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkRange(index);
        Object[] a = c.toArray();
        int numNew = a.length;
        capacityInternal(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elements, index, elements, index + numNew, numMoved);

        System.arraycopy(a, 0, elements, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

   /* @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) elements, 0, size, c);
    }*/

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;

    }

    @Override
    public E get(int index) {
        checkRange(index);
            return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkRange(index);
        E oldValue = (E)elements[index];
         elements[index] = element;
         return oldValue;
    }

    @Override
    public E remove(int index) {
        E oldValue = (E)elements[index];
        int count = size - index - 1;
        if (count > 0)
            System.arraycopy(elements, index - 1, elements, index, count);
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return i;
        } else
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    private void removeElem (int i) {
        int count = size - i - 1;
        if (count > 0)
        System.arraycopy(elements, i + 1, elements, i, count);
        elements[--size] = null;
    }

    private void checkRange(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index value is outside the array");
        }
    }

    private void capacityInternal(int minCapacity) {
        if (elements == EMPTY_ELEMENT_DATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

}
