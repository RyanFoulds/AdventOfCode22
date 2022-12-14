package xyz.foulds.aoc.year22.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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
        final String input = String.join("\n", Files.readAllLines(Paths.get(args[0])));

        final String[] parts = input.split("\n\n");
        final String stackString = parts[0];
        final String moves = parts[1];

        final List<Move> moveList = Arrays.stream(moves.split("\n"))
                .map(String::trim)
                .map(Move::new)
                .collect(Collectors.toList());

        // Part 1.
        final Stacks firstStack = new Stacks(stackString);
        moveList.forEach(firstStack::doMove);
        System.out.println(firstStack.getStackTops());

        // Part 2.
        final Stacks secondStack = new Stacks(stackString);
        moveList.forEach(secondStack::doGroupedMove);
        System.out.println(secondStack.getStackTops());
    }
}