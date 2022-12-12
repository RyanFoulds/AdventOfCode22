package xyz.foulds.aoc.year22.day12;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Node
{
    private final int height;
    private final Set<Node> accessibleNodes;
    private final Set<Node> accessibleToNodes;

    public Node(final int z)
    {
        this.height = z;

        this.accessibleNodes = new HashSet<>();
        this.accessibleToNodes = new HashSet<>();
    }

    public void addDestinationNode(final Node other)
    {
        if (other.height <= this.height + 1)
        {
            this.accessibleNodes.add(other);
            other.accessibleToNodes.add(this);
        }
    }

    public Collection<Node> getNodes(final boolean reverse)
    {
        return Collections.unmodifiableCollection(reverse ? accessibleToNodes : accessibleNodes);
    }

    public int getHeight()
    {
        return height;
    }
}
