package xyz.foulds.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        final String input = new String(Files.readAllBytes(Paths.get("src/main/resources/input.txt")));
        final Cpu cpu = new Cpu();

        // Part 2 is a side effect of running the cpu instructions.
        Arrays.stream(input.split("\n"))
              .map(String::trim)
              .map(Instruction::fromString)
              .forEach(cpu::execute);

        // Part 2.
        System.out.println(cpu.getSignalStrengthIntegral());
    }
}