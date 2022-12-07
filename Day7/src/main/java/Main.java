import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        final String input = new String(Files.readAllBytes(Paths.get("src/main/resources/input.txt")));
        final FileSystem fileSystem = new FileSystem();

        // Construct the FS.
        Arrays.stream(input.split("\\$"))
              .map(String::trim)
              .filter(str -> !str.isEmpty())
              .map(command -> Arrays.stream(command.split("\n"))
                                    .map(String::trim)
                                    .toArray(String[]::new))
              .map(Command::parseCommand)
              .forEach(command -> command.execute(fileSystem));
        fileSystem.cdRoot();

        final Map<String, Long> directorySizes = new HashMap<>();
        fileSystem.getCurrentDirectory().getSize(directorySizes);

        // Part 1.
        System.out.println(directorySizes.values().stream()
                                         .mapToLong(Long::longValue)
                                         .filter(l -> l <= 100000L)
                                         .sum());

        // Part 2.
        final long totalSize = fileSystem.getCurrentDirectory().getSize();
        final long requiredSize = totalSize - 40000000L;

        directorySizes.values().stream()
                      .mapToLong(Long::longValue)
                      .filter(l -> l >= requiredSize)
                      .min()
                      .ifPresent(System.out::println);
    }
}