package xyz.foulds.aoc.year22.day5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final String input = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                            Charset.defaultCharset()))
                .lines()
                .collect(Collectors.joining("\n"));

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