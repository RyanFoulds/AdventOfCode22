import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) throws IOException
    {
        final String input = new String(Files.readAllBytes(Paths.get("src/main/resources/input.txt")));

        final List<Move> moves = Arrays.stream(input.split("\n"))
                                       .map(String::trim)
                                       .map(Move::new)
                                       .collect(Collectors.toList());

        final Rope rope1 = new Rope(2);
        final Rope rope2 = new Rope(10);

        for (final Move move : moves)
        {
            rope1.moveHead(move);
            rope2.moveHead(move);
        }

        System.out.println(rope1.countUniqueTailVisits());
        System.out.println(rope2.countUniqueTailVisits());
    }
}