package xyz.foulds.aoc.year22.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }
        final List<String> lines = Files.readAllLines(Paths.get(args[0]))
                                        .stream()
                                        .map(String::trim)
                                        .collect(Collectors.toList());

        final Cpu cpu = new Cpu();

        // Part 2 is a side effect of running the cpu instructions.
        lines.stream()
             .map(Instruction::fromString)
             .forEach(cpu::execute);

        // Part 2.
        System.out.println(cpu.getSignalStrengthIntegral());
    }
}