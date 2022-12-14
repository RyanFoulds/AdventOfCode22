package xyz.foulds.aoc.year22.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Wall
{
    private final List<Coordinate> coordinates = new ArrayList<>();
    public Wall(final String string)
    {
        final List<Coordinate> corners = Arrays.stream(string.split(" -> "))
                                               .map(str ->str.split(","))
                                               .map(strs -> new Coordinate(Integer.parseInt(strs[0]), 
                                                                           Integer.parseInt(strs[1])))
                                               .collect(Collectors.toList());

        coordinates.addAll(corners);
        for (int i = 0; i < corners.size()-1; i++)
        {
            final Coordinate start = corners.get(i);
            final Coordinate end = corners.get(i+1);

            coordinates.addAll(start.getAllInLineTo(end));
        }
        // create the wall.
    }

    public List<Coordinate> getCoordinates()
    {
        return Collections.unmodifiableList(coordinates);
    }
}
