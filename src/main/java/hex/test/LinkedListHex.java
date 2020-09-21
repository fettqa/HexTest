package hex.test;
import java.util.*;

public class LinkedListHex<T> implements List<T> {

    private Item<T> firstInList = null;

    private Item<T> lastInList = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        return indexOf(o) != -1;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator(0);
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        Object[] newArray = new Object[size];
        int i = 0;
        for(Item<T> x = firstInList; x != null; x = x.nextItem){
            newArray[i++] = x.element;
        }
        return newArray;
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        if (a.length < size)
            a = (T1[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Item<T> x = firstInList; x != null; x = x.nextItem)
            result[i++] = x.element;

        if (a.length > size)
            a[size] = null;

        return a;
        // END
    }

    @Override
    public boolean add(final T newElement) {
        // BEGIN (write your solution here)
        linkLast(newElement);
        return true;
        // END
    }

    void linkFirst(T newFirstItem){
        final Item<T> oldFirstItem = firstInList;
        final Item<T> newItem = new Item<>(newFirstItem,null,oldFirstItem);
        firstInList = newItem;
        if(oldFirstItem == null) lastInList = newItem;
        else oldFirstItem.prevItem = newItem;
        size++;
    }

    void linkLast(T newLastItem){
        final Item<T> oldLastItem = lastInList;
        final Item<T> newItem = new Item<>(newLastItem,oldLastItem,null);
        lastInList = newItem;
        if(oldLastItem == null) firstInList = newItem;
        else oldLastItem.nextItem = newItem;
        size++;
    }

    T unlink(Item<T> item){
        final T unlinkedItem = item.element;
        final Item<T> nextItem = item.nextItem;
        final Item<T> previousItem = item.prevItem;

        if(previousItem == null){
            firstInList = nextItem;
        }else {
            previousItem.nextItem = nextItem;
            item.prevItem = null;
        }
        if(nextItem == null){
            lastInList = previousItem;
        }else {
            nextItem.prevItem = item;
            item.nextItem = null;
        }
        item.element = null;
        size--;
        return unlinkedItem;
    }

    T unlinkLast(Item<T> item){
        final T lastElement = item.element;
        final Item<T> previousItem = item.prevItem;
        item.element = null;
        item.prevItem = null;
        lastInList = previousItem;
        if (previousItem == null) firstInList = null;
        else previousItem.nextItem = null;
        size--;
        return lastElement;
    }

    T unlinkFirst(Item<T> item){
        final T firstElement = item.element;
        final Item<T> nextItem = item.nextItem;
        item.element = null;
        item.nextItem = null;
        firstInList = nextItem;
        if (nextItem == null) lastInList = null;
        else nextItem.prevItem = null;
        size--;
        return firstElement;
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        if(o == null)
            for (Item<T> item = firstInList; item != null; item = item.nextItem){
                if (item.element == null) {
                    unlink(item);
                    return true;
                }
            }
        else{
            for(Item<T> item = firstInList; item != null; item = item.nextItem){
                if (o.equals(item.element)) {
                    unlink(item);
                    return true;
                }
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final T item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        for(Item<T> item = firstInList; item != null; ){
            Item<T> nextItem = item.nextItem;
            item.element = null;
            item.nextItem = null;
            item.prevItem = null;
            item = nextItem;
        }
        firstInList = lastInList = null;
        size = 0;
        // END
    }

    @Override
    public T remove(final int index) throws IndexOutOfBoundsException {
        // BEGIN (write your solution here)
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        return unlink(node(index));
        // END
    }

    Item<T> node(int index) throws NoSuchElementException {
        int i = 0;
        for (Item<T> item = firstInList; i < size; item = item.nextItem){
            if (index == i) return item;
            i++;
        }
        throw new NoSuchElementException();
    }


    private void remove(final Item<T> current) {
        // BEGIN (write your solution here)
        if(firstInList == null) throw new NoSuchElementException();
        unlinkFirst(current);
        // END
    }

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object o) {
        // BEGIN (write your solution here)
        int index = 0;
        if (o == null){
            for(Item<T> item = firstInList; item != null; item = item.nextItem ){
                if (item.element == null) return index;
                index++;
            }
        }else {
            for (Item<T> item = firstInList; item != null; item = item.nextItem) {
                if (o.equals(item.element)) return index;
                index++;
            }
        }
        return -1;
        // END
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        // BEGIN (write your solution here)
        T oldItem = node(index).element;
        node(index).element = element;
        return oldItem;
        // END
    }

    @Override
    public T get(final int index) {
        // BEGIN (write your solution here)
        return node(index).element;
        // END
    }

    private Item<T> getItemByIndex(final int index) {
        // BEGIN (write your solution here) An auxiliary method for searching for node by index.
        return node(index);
        // END
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> currentItemInIterator;

        private Item<T> lastReturnedItemFromIterator;

        private int index;

        ElementsIterator(final int index) {
            // BEGIN (write your solution here)
            currentItemInIterator = (index == size) ? null : node(index);
            this.index = index;
            // END
        }

        @Override
        public boolean hasNext() {
            // BEGIN (write your solution here)
            return index < size;
            // END
        }

        @Override
        public T next() {
            // BEGIN (write your solution here)
            if(!hasNext()) throw new NoSuchElementException();
            lastReturnedItemFromIterator = currentItemInIterator;
            currentItemInIterator = currentItemInIterator.nextItem;
            index++;
            return lastReturnedItemFromIterator.element;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            return index > 0;
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if(!hasPrevious()) throw new NoSuchElementException();
            lastReturnedItemFromIterator = currentItemInIterator = currentItemInIterator == null ? lastInList : currentItemInIterator.prevItem;
            index--;
            return lastReturnedItemFromIterator.element;
            // END
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if(lastReturnedItemFromIterator == null) throw new IllegalStateException();
            lastReturnedItemFromIterator.element = element;
            // END
        }

        @Override
        public int previousIndex() {
            // BEGIN (write your solution here)
            return index - 1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)
            return index;
            // END
        }


        @Override
        public void remove() {
            // BEGIN (write your solution here)
            if(lastReturnedItemFromIterator == null) throw new IllegalStateException();
            Item<T> lastNext = lastReturnedItemFromIterator.nextItem;
            unlink(lastReturnedItemFromIterator);
            if (currentItemInIterator == lastReturnedItemFromIterator) currentItemInIterator = lastNext;
            else index--;
            lastReturnedItemFromIterator = null;
            // END
        }
    }

    private static class Item<T> {

        private T element;

        private Item<T> nextItem;

        private Item<T> prevItem;

        Item(final T element, final Item<T> prevItem, final Item<T> nextItem) {
            this.element = element;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }


        public Item<T> getNextItem() {
            return nextItem;
        }

        public Item<T> getPrevItem() {
            return prevItem;
        }
    }
}
