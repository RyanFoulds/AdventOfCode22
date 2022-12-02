package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
        final String input = new Scanner(new File("src/main/resources/input.txt")).useDelimiter("\\Z").next();

        // part 1
        final long totalScore = Arrays.stream(input.split("\n"))
                .map(Round::fromString)
                .mapToLong(Round::getScore)
                .sum();

        // part 2
        final long part2Score = Arrays.stream(input.split("\n"))
                .map(Round::fromResultString)
                .mapToLong(Round::getScore)
                .sum();

        System.out.println(totalScore);
        System.out.println(part2Score);
    }
}