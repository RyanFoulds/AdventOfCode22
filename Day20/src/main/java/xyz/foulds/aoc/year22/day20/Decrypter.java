package xyz.foulds.aoc.year22.day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Decrypter
{
    private final List<Long> originalList;

    private final List<Node> changyList;

    private final int mixCount;
    private boolean reArranged = false;

    public Decrypter(final List<Long> list, final int mixCount)
    {
        this.mixCount = mixCount;
        this.originalList = Collections.unmodifiableList(list);
        changyList = new ArrayList<>();

        for (int i = 0; i < originalList.size(); i++)
        {
            changyList.add(new Node(i, originalList.get(i)));
        }
    }

    private void reArrangeList()
    {
        for (int j = 0; j < mixCount; j++)
        {
            for (int i = 0; i < originalList.size(); i++)
            {
                final Node node = new Node(i, originalList.get(i));
                long currentIndex = changyList.indexOf(node);
                changyList.remove(node);
                currentIndex += node.getValue();
                currentIndex = modulo(currentIndex);
                changyList.add((int) currentIndex, node);
            }
        }
        reArranged = true;
    }

    private long modulo(final long index)
    {
        final int denominator = originalList.size() - 1;
        long modulo = index % denominator;
        if (modulo < 0)
        {
            modulo += denominator;
        }
        return modulo;
    }

    public long decrypt()
    {
        if (!reArranged)
        {
            reArrangeList();
        }

        final Node zeroNode = new Node(originalList.indexOf(0L), 0);
        final int zeroNodeNewIndex = changyList.indexOf(zeroNode);

        return changyList.get((zeroNodeNewIndex+1000) % originalList.size()).getValue()
                + changyList.get((zeroNodeNewIndex+2000) % originalList.size()).getValue()
                + changyList.get((zeroNodeNewIndex+3000) % originalList.size()).getValue();
    }
}
