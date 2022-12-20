package xyz.foulds.aoc.year22.day19;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

public class Blueprint
{
    private final int id;
    private final int oreBotCost;
    private final int clayBotCost;
    private final int obsidianBotCostOre;
    private final int obsidianBotCostClay;
    private final int geodeBotCostOre;
    private final int geodeBotCostObsidian;
    private final int maxUsefulOreMachines;
    private final Map<Integer, Integer> maxGeodeCount = new HashMap<>();

    public Blueprint(final String line)
    {
        final String csv = line.replaceAll("[^\\d]", " ")
                               .trim()
                               .replaceAll(" +", ",");
        final int[] numbers = Arrays.stream(csv.split(","))
                                    .filter(str -> !str.isEmpty())
                                    .mapToInt(Integer::parseInt)
                                    .toArray();

        this.id = numbers[0];
        this.oreBotCost = numbers[1];
        this.clayBotCost = numbers[2];
        this.obsidianBotCostOre = numbers[3];
        this.obsidianBotCostClay = numbers[4];
        this.geodeBotCostOre = numbers[5];
        this.geodeBotCostObsidian = numbers[6];

        maxUsefulOreMachines = IntStream.of(oreBotCost, clayBotCost, obsidianBotCostOre, geodeBotCostOre)
                                                  .max()
                                                  .getAsInt();
    }

    public int getMaxGeodeCount(final int time)
    {
        if (!maxGeodeCount.containsKey(time))
        {
            maxGeodeCount.put(time, findOptimalStrategyGeodeCount(time));
        }
        return maxGeodeCount.get(time);
    }

    public int getQualityLevel(final int time)
    {
        return id * getMaxGeodeCount(time);
    }

    private int findOptimalStrategyGeodeCount(final int time)
    {
        final Set<RobotState> visited = new HashSet<>();
        final Stack<RobotState> toCheck = new Stack<>();
        final RobotState initialState = new RobotState(this);
        toCheck.add(initialState);
        int highestGeodeCount = 0;

        while (!toCheck.isEmpty())
        {
            final RobotState current = toCheck.pop();
            visited.add(current);
            if (current.getDepth() < time)
            {
                for (final RobotState candidate : current.getAdjacentStates())
                {
                    final int upperBound = candidate.getUpperBound(time);
                    if (upperBound > highestGeodeCount && !visited.contains(candidate))
                    {
                        toCheck.push(candidate);
                    }
                }
            }
            else if (current.getDepth() == time)
            {
                highestGeodeCount = Math.max(highestGeodeCount, current.getGeode());
            }
        }

        return highestGeodeCount;
    }

    public int getOreBotCost()
    {
        return oreBotCost;
    }

    public int getClayBotCost()
    {
        return clayBotCost;
    }

    public int getObsidianBotCostOre()
    {
        return obsidianBotCostOre;
    }

    public int getObsidianBotCostClay()
    {
        return obsidianBotCostClay;
    }

    public int getGeodeBotCostOre()
    {
        return geodeBotCostOre;
    }

    public int getGeodeBotCostObsidian()
    {
        return geodeBotCostObsidian;
    }

    public int getMaxUsefulOreMachines()
    {
        return maxUsefulOreMachines;
    }

    public int getId()
    {
        return id;
    }
}
