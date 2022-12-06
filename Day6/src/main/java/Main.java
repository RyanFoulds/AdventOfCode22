import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        final String input = new String(Files.readAllBytes(Paths.get("src/main/resources/input.txt")));
        final char[] inputChars = input.toCharArray();

        // Part 1.
        System.out.println(findStart(inputChars, 4));

        // Part 2.
        System.out.println(findStart(inputChars, 14));
    }

    public static int findStart(final char[] input, final int size)
    {
        final EvictingQueue<Character> queue = new EvictingQueue<>(size);
        for (int i = 0; i < input.length; i++)
        {
            queue.offer(input[i]);

            if (i >= size && queue.containsOnlyUniqueElements())
            {
                return i + 1;
            }
        }
        throw new RuntimeException();
    }
}