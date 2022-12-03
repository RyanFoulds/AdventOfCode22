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
        final List<Rucksack> rucksacks = Arrays.stream(input.split("\n")).map(Rucksack::new).collect(Collectors.toList());

        // Part 1.
        final int sum = rucksacks.stream()
                                 .mapToInt(Rucksack::getTotalDuplicatePriority)
                                 .sum();
        System.out.println(sum);

        // Part 2.
        if (rucksacks.size() % 3 != 0)
        {
            throw new RuntimeException();
        }

        int badgeSum = 0;
        for (int i = 0; i < rucksacks.size(); i += 3)
        {
            final Group group = new Group(rucksacks.get(i), rucksacks.get(i+1), rucksacks.get(i+2));
            badgeSum += group.getBadgePriority();
        }
        System.out.println(badgeSum);
    }
}