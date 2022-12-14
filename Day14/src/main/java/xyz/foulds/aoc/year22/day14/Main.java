package xyz.foulds.aoc.year22.day14;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main
{

    public static void main(final String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final List<Wall> walls = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                            Charset.defaultCharset()))
            .lines()
            .map(String::trim)
            .map(Wall::new)
            .collect(Collectors.toList());

        // Part 1
        System.out.println(calculateSandCount(walls, false));
        System.out.println(calculateSandCount(walls, true));
    }

    private static int calculateSandCount(final List<Wall> walls, final boolean withFloor)
    {
        final Cave cave = new Cave();
        walls.forEach(cave::addWall);
        cave.lockInWalls();
        while(cave.addSand(withFloor))
        {
            // Just wait for this to break;
        }
        return cave.getSandCount();
    }
}