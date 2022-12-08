import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        final String input = new String(Files.readAllBytes(
                Paths.get("src/main/resources/input.txt")));
        final String[] rows = Arrays.stream(input.trim().split("\n"))
                .map(String::trim)
                .toArray(String[]::new);

        final Grid grid = new Grid(rows);

        // Part 1.
        System.out.println(grid.getVisibleTreeCount());

        // Part 2.
        System.out.println(grid.getAllTreeScores().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(RuntimeException::new));
    }
}