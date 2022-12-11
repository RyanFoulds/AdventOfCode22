package xyz.foulds.aoc.year22.day10;

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
        final List<String> lines = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                            Charset.defaultCharset()))
                .lines()
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