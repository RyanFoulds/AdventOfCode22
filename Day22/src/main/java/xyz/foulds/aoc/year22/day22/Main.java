package xyz.foulds.aoc.year22.day22;

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

        final String[] input = String.join("\n", Files.readAllLines(Paths.get(args[0])))
                                     .split("\n\n");
        final List<String> boardLines = Arrays.stream(input[0].split("\n"))
                                              .collect(Collectors.toList());

        // Part 1.
        final Board board = new Board(boardLines);
        board.moveAlongPath(input[1], false);
        System.out.println(board.getScore());

        // Part 2.
        board.reset();
        board.moveAlongPath(input[1], true);
        System.out.println(board.getScore());
    }
}