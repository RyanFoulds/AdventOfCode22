package xyz.foulds.aoc.year22.day20;

import java.util.Objects;

public class Node
{
    private final int originalIndex;
    private final long value;

    public Node(final int index, final long value)
    {
        this.originalIndex = index;
        this.value = value;
    }

    public long getValue()
    {
        return value;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final Node node = (Node) o;
        return originalIndex == node.originalIndex && value == node.value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(originalIndex, value);
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
