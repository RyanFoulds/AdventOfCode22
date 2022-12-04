import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main
{

    public static void main(final String[] args) throws FileNotFoundException
    {
        final String input = new Scanner(new File("src/main/resources/input.txt")).useDelimiter("\\Z").next();

        final List<Pair> elfPairs = Arrays.stream(input.split("\n"))
                                          .map(String::trim)
                                          .filter(s -> !s.isEmpty())
                                          .map(string -> {
                                              final String[] strings = string.split(",");
                                              return new Pair(new Elf(strings[0]), new Elf(strings[1]));
                                          })
                                          .collect(Collectors.toList());

        // Part 1
        final long containedElves = elfPairs.stream()
                                            .filter(Pair::hasContainedElf)
                                            .count();
        System.out.println(containedElves);

        // Part 2
        final long overlappingElves = elfPairs.stream()
                                              .filter(Pair::hasOverlap)
                                              .count();
        System.out.println(overlappingElves);
    }
}