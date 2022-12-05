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