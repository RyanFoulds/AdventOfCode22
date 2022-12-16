package xyz.foulds.aoc.year22.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input, a row" 
                                                   + " number to test with part 1, and a maximum coordinate for part 2.");
        }
        List<Sensor> sensors = Files.readAllLines(Paths.get(args[0]))
                                    .stream()
                                    .map(String::trim)
                                    .map(str -> str.split(":"))
                                    .map(strs -> new Sensor(strs[0], new Beacon(strs[1])))
                                    .collect(Collectors.toList());

        System.out.println(solvePart1(sensors, Integer.parseInt(args[1])));
        System.out.println(solvePart2(sensors, Integer.parseInt(args[2])));
    }

    private static int solvePart1(final List<Sensor> sensors, final int rowNum)
    {
        final List<Integer> beacons = sensors.stream()
                                             .map(Sensor::getClosestBeacon)
                                             .distinct()
                                             .filter(b -> b.getY() == rowNum)
                                             .map(Beacon::getX)
                                             .collect(Collectors.toList());

        final List<Range> ranges = sensors.stream()
                                          .filter(sensor -> sensor.intersectsRow(rowNum))
                                          .map(sensor -> sensor.getExcludedFromRow(rowNum))
                                          .filter(Objects::nonNull)
                                          .collect(Collectors.toList());

        final int minX = ranges.stream().mapToInt(Range::getStart).min().orElse(0);
        final int maxX = ranges.stream().mapToInt(Range::getEnd).max().orElse(0);
        final int[] row = new int[maxX - minX + 1];
        for (int i = 0; i < row.length; i++)
        {
            row[i] = i + minX;
        }

        return (int) Arrays.stream(row)
                           .filter(i -> ranges.stream().anyMatch(range -> range.contains(i)))
                           .filter(i -> !beacons.contains(i))
                           .count();
    }

    private static long solvePart2(final List<Sensor> sensors, final int maxSize)
    {
        final List<Coordinate> searchSpace = new ArrayList<>(sensors.size() * sensors.size());
        for (int i = 0; i < sensors.size(); i++)
        {
            for (int j = 0; j < sensors.size(); j++)
            {
                if (i == j) continue;
                searchSpace.addAll(sensors.get(i).getBoundaryIntersectionPoints(sensors.get(j)));
            }
        }
        searchSpace.add(new Coordinate(0, 0));
        searchSpace.add(new Coordinate(0, maxSize));
        searchSpace.add(new Coordinate(maxSize, 0));
        searchSpace.add(new Coordinate(maxSize, maxSize));

        return searchSpace.stream()
                          .distinct()
                          .filter(c -> c.getX() >= 0 && c.getX() <= maxSize && c.getY() >= 0 && c.getY() <= maxSize)
                          .filter(c -> sensors.stream().noneMatch(s -> s.contains(c)))
                          .mapToLong(Coordinate::getFrequency)
                          .findAny()
                          .orElse(0);
    }
}