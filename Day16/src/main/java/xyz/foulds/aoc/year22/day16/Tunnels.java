package xyz.foulds.aoc.year22.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Tunnels
{
    private final Map<String, Valve> valves;

    private Map<TunnelState, Integer> scores = new HashMap<>();

    public Tunnels(final Map<String, Valve> valves)
    {
        this.valves = valves;
        scores.put(new TunnelState(), 0);
    }

    public long play()
    {
        int turnsLeft = 30;
        while (turnsLeft > 0)
        {
            turnsLeft -=1;
            doTurn(turnsLeft);
        }
        return getHighScore();
    }

    public long playWithElephant()
    {
        int turnsLeft = 26;
        while (turnsLeft > 0)
        {
            turnsLeft -= 1;
            doTurn(turnsLeft);
        }

        // Try to find two paths that combine for the highest pressure release but don't open any of the same valves.
        // This assumes that there isn't enough time for all valves to be opened.
        long candidateScore = getHighScore(); // Two openers must be better than 1.
        for (final TunnelState one : scores.keySet())
        {
            final int score1 = scores.get(one);
            if (score1 < candidateScore / 2)
            {
                // At least one of the paired openers must do better than half the current best.
                continue;
            }
            for (final TunnelState two : scores.keySet())
            {
                final int score2 = scores.get(two);
                if (score2 + score1 <= candidateScore)
                {
                    // Don't bother checking for uniqueness if the score wouldn't win anyway.
                    continue;
                }
                // If removeAll doesn't change the set then it must contain no elements from the second set.
                if (!one.getOpenValveNames().removeAll(two.getOpenValveNames()))
                {
                    candidateScore = score1 + score2;
                }
            }
        }

        return candidateScore;
    }

    public void doTurn(final int turnsLeft)
    {
        final Map<TunnelState, Integer> tempScores = new HashMap<>();
        for (final Entry<TunnelState, Integer> entry : scores.entrySet())
        {
            final TunnelState state = entry.getKey();
            final Valve currentValve = valves.get(state.getCurrentValveName());
            
            if (currentValve.getFlowRate() != 0 && !state.getOpenValveNames().contains(currentValve.getName()))
            {
                final Set<String> newlyOpen = state.getOpenValveNames();
                newlyOpen.add(currentValve.getName());
                tempScores.merge(new TunnelState(currentValve.getName(), newlyOpen),
                                 entry.getValue() + currentValve.getTotalFlow(turnsLeft),
                                 Math::max);
            }
            for (final Valve nextValve : currentValve.getNextValves())
            {
                tempScores.merge(new TunnelState(nextValve.getName(), state.getOpenValveNames()),
                                 entry.getValue(),
                                 Math::max);
            }
        }

        scores = tempScores;
    }

    public long getHighScore()
    {
        return scores.values().stream().mapToLong(Integer::longValue).max().orElse(0);
    }
}
