package hex.test.set;
import java.util.*;

public class HexSet<T> implements Set<T> {

    static final Boolean EXIST = true;

    final Map<T, Boolean> elements = new HashMap<>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return elements.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return elements.isEmpty();
        // END
    }

    @Override
    public boolean contains(Object o) {
        // BEGIN (write your solution here)
        return elements.containsKey(o);
        // END
    }

    @Override
    public Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return elements.keySet().iterator();
        // END
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        return elements.keySet().toArray();
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        return (T1[]) elements.keySet().toArray();
        // END
    }

    @Override
    public boolean add(T t) {
        // BEGIN (write your solution here)
        try {
            elements.put(t,EXIST);
            return true;
        }catch (NullPointerException e){
            return false;
        }
        // END
    }

    @Override
    public boolean remove(Object o) {
        // BEGIN (write your solution here)
        return elements.remove(o)==EXIST;
        // END
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // BEGIN (write your solution here)
        try {
            return elements.keySet().containsAll(c);
        }catch (NullPointerException e){
            return false;
        }
        // END
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // BEGIN (write your solution here)
        for(T item : c){
            if (!this.add(item)) return false;
        }
        return true;
        // END
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // BEGIN (write your solution here)
        return elements.keySet().retainAll(c);
        // END
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // BEGIN (write your solution here)
        Object[] a = c.toArray();
        for(Object b : a){
            elements.remove(b);
        }
        return true;
        // END
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        elements.clear();
        Integer[] sum;
        // END
    }

    @Override
    public boolean equals(Object o) {
        // BEGIN (write your solution here)
        return elements.containsKey(o);
        // END
    }

    @Override
    public int hashCode() {
        // BEGIN (write your solution here)
        return elements.keySet().hashCode();
        // END
    }
}