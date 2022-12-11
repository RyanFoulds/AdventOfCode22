package xyz.foulds.aoc.year22.day11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

public class Monkey
{
    private final Map<Integer, Monkey> troop;
    private final Function<Long, Long> operation;
    private final long testDivisor;
    private final int trueTarget;
    private final int falseTarget;
    private final Queue<Long> items = new LinkedList<>();

    private final long modBase;

    private long inspectionCount = 0;

    public Monkey(final String string, final Map<Integer, Monkey> troop, final long modbase)
    {
        final String[] lines = string.split("\n");

        final int id = Integer.parseInt(lines[0].substring(7, lines[0].length() - 1));
        this.troop = troop;
        this.troop.put(id, this);

        Arrays.stream(lines[1].substring(16)
                              .trim()
                              .split(", "))
              .mapToLong(Long::parseLong)
              .forEach(items::add);
        operation = new Operation(lines[2]);
        testDivisor = Integer.parseInt(lines[3].substring(19));
        trueTarget = Integer.parseInt(lines[4].substring(25));
        falseTarget = Integer.parseInt(lines[5].substring(26));

        this.modBase = modbase;
    }

    public void takeTurn(final boolean relaxed)
    {
        final int initialSize = items.size();
        inspectionCount += initialSize;

        for (int i = 0; i < initialSize; i++)
        {

            long item = operation.apply(items.poll());

            if (relaxed)
            {
                item /= 3;
            }
            else
            {
                item %= modBase;
            }

            troop.get(item % testDivisor == 0 ? trueTarget : falseTarget).receive(item);
        }
    }

    public void receive(final long item)
    {
        items.offer(item);
    }

    public long getInspectionCount()
    {
        return inspectionCount;
    }
}
