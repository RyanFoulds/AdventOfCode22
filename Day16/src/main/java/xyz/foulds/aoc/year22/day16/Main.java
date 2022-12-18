package xyz.foulds.aoc.year22.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single argument, the file path for the puzzle input");
        }

        Map<String, Valve> valves = Files.readAllLines(Paths.get(args[0]))
                                         .stream()
                                         .map(String::trim)
                                         .map(Valve::new)
                                         .collect(Collectors.toMap(Valve::getName,
                                                                   Function.identity()));
        valves.values().forEach(valve -> valve.setValves(valves));

        // Part 1
        final Tunnels tunnels = new Tunnels(valves);
        System.out.println(tunnels.play());

        // Part 2
        final Tunnels tunnels1 = new Tunnels(valves);
        System.out.println(tunnels1.playWithElephant());
    }
}