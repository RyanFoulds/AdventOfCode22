import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws FileNotFoundException
    {
        final String input = new Scanner(new File("src/main/resources/input.txt")).useDelimiter("\\Z").next();

        // Part 1 - Top elf
        Arrays.stream(input.split("\n\n"))
                .map(elf -> Arrays.stream(elf.split("\n"))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .sum())
                .mapToInt(Integer::intValue)
                .max()
                .ifPresent(System.out::println);

        // Part 2 - Top 3 elves.
        final List<Integer> ans = Arrays.stream(input.split("\n\n"))
                .map(elf -> Arrays.stream(elf.split("\n"))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .sum())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(ans.get(0) + ans.get(1) + ans.get(2));
    }
}