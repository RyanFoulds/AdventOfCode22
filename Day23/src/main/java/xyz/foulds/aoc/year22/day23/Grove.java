package xyz.foulds.aoc.year22.day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Grove
{
    private final Map<Integer, Coordinate> locationsOfElves;

    public Grove(final List<String> lines)
    {
        locationsOfElves = new HashMap<>();
        int id = 0;
        for (int y = 0; y < lines.size(); y++)
        {
            for (int x = 0; x < lines.get(y).length(); x++)
            {
                if (lines.get(y).charAt(x) == '#')
                {
                    locationsOfElves.put(++id, new Coordinate(x, y));
                }
            }
        }
    }

    public void doRounds(final int rounds)
    {
        for (int i = 0; i < rounds; i++)
        {
            doRound(i);
        }
    }

    public int doRounds()
    {
        int i = 0;
        while (!doRound(i++)){}
        return i;
    }

    private boolean doRound(final int turn)
    {
        Map<Integer, Coordinate> proposals = getProposals(turn);
        proposals = deduplicate(proposals);
        final boolean retVal = proposals.equals(locationsOfElves);
        locationsOfElves.putAll(proposals);
        return retVal;
    }

    private Map<Integer, Coordinate> deduplicate(final Map<Integer, Coordinate> map)
    {
        final Map<Coordinate, Integer> reverseMap = new HashMap<>();
        map.entrySet().stream()
           .filter(e -> reverseMap.putIfAbsent(e.getValue(), e.getKey()) != null)
           .forEach(e -> reverseMap.remove(e.getValue()));

        return reverseMap.entrySet()
                         .stream()
                         .collect(Collectors.toMap(Entry::getValue,
                                                   Entry::getKey));
    }

    private Map<Integer, Coordinate> getProposals(final int turn)
    {
        final Map<Integer, Coordinate> proposedLocations = new HashMap<>();
        final Direction firstDirection = Direction.get(turn);
        for (final Entry<Integer, Coordinate> elf : locationsOfElves.entrySet())
        {
            if (elf.getValue().getAllAdjacentPositions().stream().noneMatch(locationsOfElves::containsValue))
            {
                proposedLocations.put(elf.getKey(), elf.getValue());
            }
            else if (doMove(elf, firstDirection, proposedLocations)){}
            else if (doMove(elf, firstDirection.getNext(1), proposedLocations)){}
            else if (doMove(elf, firstDirection.getNext(2), proposedLocations)){}
            else if (doMove(elf, firstDirection.getNext(3), proposedLocations)){}
            else proposedLocations.put(elf.getKey(), elf.getValue());
        }

        return proposedLocations;
    }

    private boolean doMove(final Entry<Integer, Coordinate> elf, final Direction direction, final Map<Integer, Coordinate> proposed)
    {
        final Coordinate original = elf.getValue();
        if (original.getAdjacentPositions(direction).stream().noneMatch(locationsOfElves::containsValue))
        {
            final Coordinate proposedValue = original.getAdjacent(direction);
            proposed.put(elf.getKey(), proposedValue);
            return true;
        }
        return false;
    }

    private int sizeOfBoundingBox()
    {
        final Set<Integer> x = locationsOfElves.values().stream().map(Coordinate::getX).collect(Collectors.toSet());
        final Set<Integer> y = locationsOfElves.values().stream().map(Coordinate::getY).collect(Collectors.toSet());
        final int minX = x.stream().mapToInt(Integer::intValue).min().getAsInt();
        final int maxX = x.stream().mapToInt(Integer::intValue).max().getAsInt();
        final int minY = y.stream().mapToInt(Integer::intValue).min().getAsInt();
        final int maxY = y.stream().mapToInt(Integer::intValue).max().getAsInt();

        return (maxX - minX + 1) * (maxY - minY + 1);
    }

    public int countEmptySquares()
    {
        return sizeOfBoundingBox() - locationsOfElves.size();
    }
}
