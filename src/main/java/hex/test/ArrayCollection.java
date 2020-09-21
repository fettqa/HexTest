package hex.test;

import java.util.*;

public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[]) new Object[0];

    private int size;

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return this.size;
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return this.size == 0;
        // END
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        for (int i = 0; i < size; i++){
            if (o.equals(m[i])) return true;
        }
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return new ElementsIterator();
        // END
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        final T[] newM = (T[]) new Object[size];
        System.arraycopy(m,0,newM,0,size);
        return newM;
        // END
    }

    @Override
    /*This method may prove to be too difficult.
    he test is not covered.*/
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        if (a == null) throw new NullPointerException();
        if (a.getClass() != m.getClass()) throw new ArrayStoreException();
        if (a.length > size) a[size] = null;
        return (T1[]) Arrays.copyOf(m,a.length,a.getClass());
        // END
    }

    @Override
    public boolean add(final T t) {
        // BEGIN (write your solution here)
        if (m.length == size) {
            final T[] oldM = m;
            int newLength = m.length == 0 ? 4 : m.length * 2;
            m = (T[]) new Object[newLength];
            System.arraycopy(oldM,0,m,0,oldM.length);
        }
        m[size++] = t;
        return true;
        // END
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        for (int i = 0; i < size; i++){
            if (o.equals(m[i])) {
                final T[] oldM = m;
                if (i == size - 1) m[--size] = null;
                else System.arraycopy(oldM, i + 1, m, i, (size -= 1) - i);
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (Object item : c){
            if (!this.contains(item)) return false;
        }
        return true;
        // END
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        // BEGIN (write your solution here)
        for (T item : c){
            this.add(item);
        }
        return true;
        // END
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for(Object item : c){
            this.remove(item);
        }
        return true;
        // END
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        T[] newM = (T[]) new Object[size];
        int i = 0;
        int newSize = 0;
        for (final Object item : this) {
            if (c.contains(item)) {
                newM[i++] = (T) item;
                newSize++;
            }
        }
        this.size = newSize;
        this.m = newM;
        return true;
        // END
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        this.m = (T[]) new Object[0];
        size = 0;
        // END
    }

    private class ElementsIterator implements Iterator<T> {
        // BEGIN (write your solution here)
        private int index = 0;
        @Override
        public boolean hasNext() {
            return this.index < ArrayCollection.this.size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return m[this.index ++];
        }

        @Override
        public void remove() {
            if (ArrayCollection.this.size == index && index < 0) throw new UnsupportedOperationException();
            if (!this.hasNext()) throw new IllegalStateException();
            m[index--] = null;
        }
        // END
    }
}

