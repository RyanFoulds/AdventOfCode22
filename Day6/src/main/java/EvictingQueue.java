import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class EvictingQueue<T> extends ArrayDeque<T>
{
    private final int maxSize;

    public EvictingQueue(final int maxSize)
    {
        this.maxSize = maxSize;
    }

    public void addLast(final T object)
    {
        if (size() == maxSize)
        {
            super.removeFirst();
        }
        super.addLast(object);
    }

    @Override
    public void addFirst(T t)
    {
        if (size() == maxSize)
        {
            super.removeFirst();
        }
        super.addFirst(t);
    }

    public boolean containsOnlyUniqueElements()
    {
        final Set<T> set = new HashSet<>(this);
        return set.size() == this.size();
    }
}
