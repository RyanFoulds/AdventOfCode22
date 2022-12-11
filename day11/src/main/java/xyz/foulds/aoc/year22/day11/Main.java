package xyz.foulds.aoc.year22.day11;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .map(String::trim)
                .collect(Collectors.joining("\n"));

        // Calculate the product of all the divisors of the tests,
        // we can wrap around this value while preserving the outcome of all tests.
        final long modBase = Arrays.stream(input.split("\n"))
                                   .map(String::trim)
                                   .filter(str -> str.startsWith("Test"))
                                   .map(str -> str.substring(19))
                                   .mapToLong(Long::parseLong)
                                   .reduce(1L, (a, b) -> a * b);

        // Part 1
        System.out.println(getProductOfTwoMostActiveMonkeys(solve(input, modBase, true, 20)));
        // Part 2
        System.out.println(getProductOfTwoMostActiveMonkeys(solve(input, modBase, false, 10000)));
    }

    private static Collection<Monkey> solve(final String input,
                                            final long modbase,
                                            final boolean relaxed,
                                            final int turns)
    {
        // Create the troop, in its initial state.
        final Map<Integer, Monkey> troop = new HashMap<>();
        Arrays.stream(input.split("\n\n"))
              .forEach(monkey -> new Monkey(monkey, troop, modbase));

        // Play keep away.
        for (int turn = 0; turn < turns; turn++)
        {
            for (int id = 0; id < troop.size(); id++)
            {
                troop.get(id).takeTurn(relaxed);
            }
        }

        return troop.values();
    }

    private static long getProductOfTwoMostActiveMonkeys(final Collection<Monkey> monkeys)
    {
        final List<Long> inpectionCounts =  monkeys.stream()
                                                   .map(Monkey::getInspectionCount)
                                                   .sorted(Comparator.reverseOrder())
                                                   .collect(Collectors.toList());

        return inpectionCounts.get(0) * inpectionCounts.get(1);
    }
}