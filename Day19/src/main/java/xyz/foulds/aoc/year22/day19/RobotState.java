package xyz.foulds.aoc.year22.day19;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RobotState
{
    private final int ore;
    private final int clay;
    private final int obsidian;
    private final int geode;
    private final int oreRate;
    private final int clayRate;
    private final int obsidianRate;
    private final int geodeRate;
    private final int depth;

    private final Blueprint blueprint;

    public RobotState(final int ore, final int clay, final int obsidian, final int geode,
                      final int oreRate, final int clayRate, final int obsidianRate,
                      final int geodeRate, final Blueprint blueprint, final int depth)
    {
        this.ore = ore;
        this.clay = clay;
        this.obsidian = obsidian;
        this.geode = geode;
        this.oreRate = oreRate;
        this.clayRate = clayRate;
        this.obsidianRate = obsidianRate;
        this.geodeRate = geodeRate;

        this.blueprint = blueprint;
        this.depth = depth;
    }

    public RobotState(final Blueprint blueprint)
    {
        this.ore = 0;
        this.clay = 0;
        this.obsidian = 0;
        this.geode = 0;
        this.oreRate = 1;
        this.clayRate = 0;
        this.obsidianRate = 0;
        this.geodeRate = 0;

        this.blueprint = blueprint;
        this.depth = 0;
    }

    public Set<RobotState> getAdjacentStates()
    {
        final Set<RobotState> retSet = new HashSet<>();
        final RobotState nextBaseState = new RobotState(ore + oreRate, clay + clayRate,
                                                        obsidian + obsidianRate, geode + geodeRate,
                                                        oreRate, clayRate,
                                                        obsidianRate, geodeRate,
                                                        blueprint, depth+1);

        retSet.add(nextBaseState);

        if (ore >= blueprint.getGeodeBotCostOre()
            && obsidian >= blueprint.getGeodeBotCostObsidian())
        {
            retSet.add(buildGeodeBot(nextBaseState, blueprint));
        }
        if (obsidianRate < blueprint.getGeodeBotCostObsidian()
            && ore >= blueprint.getObsidianBotCostOre()
            && clay >= blueprint.getObsidianBotCostClay())
        {
            retSet.add(buildObsidianBot(nextBaseState, blueprint));
        }
        if (oreRate < blueprint.getMaxUsefulOreMachines()
            && ore >= blueprint.getOreBotCost())
        {
            retSet.add(buildOreBot(nextBaseState, blueprint));
        }
        if (clayRate < blueprint.getObsidianBotCostClay()
            && ore >= blueprint.getClayBotCost())
        {
            retSet.add(buildClayBot(nextBaseState, blueprint));
        }

        return retSet;
    }

    private static RobotState buildOreBot(final RobotState initalState,
                                          final Blueprint blueprint)
    {
        return new RobotState(initalState.ore - blueprint.getOreBotCost(),
                              initalState.clay, initalState.obsidian,
                              initalState.geode, initalState.oreRate+1,
                              initalState.clayRate, initalState.obsidianRate,
                              initalState.geodeRate, blueprint, initalState.depth);
    }

    private static RobotState buildClayBot(final RobotState initalState,
                                           final Blueprint blueprint)
    {
        return new RobotState(initalState.ore - blueprint.getClayBotCost(),
                              initalState.clay, initalState.obsidian,
                              initalState.geode, initalState.oreRate,
                              initalState.clayRate+1, initalState.obsidianRate,
                              initalState.geodeRate, blueprint, initalState.depth);
    }

    private RobotState buildObsidianBot(final RobotState initalState,
                                        final Blueprint blueprint)
    {
        return new RobotState(initalState.ore - blueprint.getObsidianBotCostOre(),
                              initalState.clay - blueprint.getObsidianBotCostClay(),
                              initalState.obsidian, initalState.geode,
                              initalState.oreRate, initalState.clayRate,
                              initalState.obsidianRate+1, initalState.geodeRate,
                              blueprint, initalState.depth);
    }

    private RobotState buildGeodeBot(final RobotState initalState,
                                     final Blueprint blueprint)
    {
        return new RobotState(initalState.ore - blueprint.getGeodeBotCostOre(),
                              initalState.clay, initalState.obsidian - blueprint.getGeodeBotCostObsidian(),
                              initalState.geode, initalState.oreRate, initalState.clayRate,
                              initalState.obsidianRate, initalState.geodeRate+1,
                              blueprint, initalState.depth);
    }

    public int getDepth()
    {
        return depth;
    }

    public int getGeode()
    {
        return geode;
    }

    public int getUpperBound(final int time)
    {
        final int n = time - depth;
        return ((n * (n+1)) / 2)  + n*geodeRate + geode;
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
        final RobotState that = (RobotState) o;
        return ore == that.ore && clay == that.clay && obsidian == that.obsidian
               && geode == that.geode && oreRate == that.oreRate && clayRate == that.clayRate
               && obsidianRate == that.obsidianRate && geodeRate == that.geodeRate && depth == that.depth;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ore, clay, obsidian, geode, oreRate,
                            clayRate, obsidianRate, geodeRate, depth);
    }
}
