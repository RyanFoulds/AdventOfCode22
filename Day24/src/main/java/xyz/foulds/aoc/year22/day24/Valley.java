package xyz.foulds.aoc.year22.day24;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Valley
{
    private int turn = 0;
    private final int width;
    private final int height;

    private final Coordinate start;
    private final Coordinate end;
    private Map<Coordinate, Collection<Blizzard>> blizzards = new HashMap<>();

    private Set<Coordinate> possibleExpeditionLocations = new HashSet<>();

    public Valley(final List<String> rows)
    {
        height = rows.size() - 2;
        width = rows.get(0).length() - 2;
        start = new Coordinate(rows.get(0).indexOf('.'), 0);
        end = new Coordinate(rows.get(height+1).indexOf('.'), height+1);

        for (int y = 1; y < rows.size()-1; y++)
        {
            final String row = rows.get(y);
            for (int x = 1; x < row.length()-1; x++)
            {
                final char c = row.charAt(x);
                final List<Blizzard> list = new ArrayList<>();
                final Coordinate coord = new Coordinate(x,y);
                if (c != '.')
                {
                    list.add(new Blizzard(coord, c));
                }
                blizzards.put(coord, list);
            }
        }
        possibleExpeditionLocations.add(start);
    }

    public int navigate()
    {
        while (!possibleExpeditionLocations.contains(end))
        {
            doTurn();
        }
        possibleExpeditionLocations.clear();
        possibleExpeditionLocations.add(end);
        return turn;
    }

    public void returnTrip()
    {
        while (!possibleExpeditionLocations.contains(start))
        {
            doTurn();
        }
        possibleExpeditionLocations.clear();
        possibleExpeditionLocations.add(start);
    }

    private void doTurn()
    {
        turn += 1;
        moveBlizzards();
        possibleExpeditionLocations = possibleExpeditionLocations.stream()
                                                                 .map(this::getAvailableMoves)
                                                                 .flatMap(Collection::stream)
                                                                 .collect(Collectors.toSet());
    }

    private List<Coordinate> getAvailableMoves(final Coordinate current)
    {
        return getAdjacentCoords(current).stream()
                                         .filter(coord -> isEmpty(blizzards.get(coord)))
                                         .collect(Collectors.toList());
    }

    private List<Coordinate> getAdjacentCoords(final Coordinate coordinate)
    {
        final int x0 = coordinate.getX();
        final int y0 = coordinate.getY();
        return  Stream.of(coordinate,
                          new Coordinate(x0+1, y0),
                          new Coordinate(x0-1, y0),
                          new Coordinate(x0, y0+1),
                          new Coordinate(x0, y0-1))
                      .filter(coord -> (coord.getX() >= 1 && coord.getX() <= width))
                      .filter(coord -> (coord.getY() >= 1 && coord.getY() <= height) || coord.equals(end) || coord.equals(start))
                      .collect(Collectors.toList());
    }

    private void moveBlizzards()
    {
        final Map<Coordinate, Collection<Blizzard>> next = new HashMap<>();
        for (final Entry<Coordinate, Collection<Blizzard>> entry : blizzards.entrySet())
        {
            for (final Blizzard blizzard : entry.getValue())
            {
                moveBlizzard(blizzard);
                if (next.containsKey(blizzard.getCoordinate()))
                {
                    next.get(blizzard.getCoordinate()).add(blizzard);
                }
                else
                {
                    final List<Blizzard> list = new ArrayList<>();
                    list.add(blizzard);
                    next.put(blizzard.getCoordinate(), list);
                }
            }
        }

        blizzards = next;
    }

    private void moveBlizzard(final Blizzard blizzard)
    {
        final Coordinate coordinate = blizzard.getCoordinate();
        int newX = coordinate.getX();
        int newY = coordinate.getY();

        switch (blizzard.getDirection())
        {
            case '<':
                newX = newX == 1 ? width : newX-1;
                break;

            case '>':
                newX = newX == width ? 1 : newX+1;
                break;

            case 'v':
                newY = newY == height ? 1 : newY+1;
                break;
            case '^':
                newY = newY == 1 ? height : newY-1;
                break;
            default: throw new IllegalArgumentException();
        }

        blizzard.setCoordinate(new Coordinate(newX, newY));
    }

    private boolean isEmpty(final Collection<?> collection)
    {
        return collection == null || collection.isEmpty();
    }
}
