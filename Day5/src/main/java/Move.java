import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move
{
    private static final Pattern PATTERN = Pattern.compile(" [a-z]+ ");
    private final int numCrates;
    private final int fromStackIndex;
    private final int toStackIndex;

    public Move(final String string)
    {
        final String[] values = PATTERN.split(string.substring(5));
        this.numCrates = Integer.parseInt(values[0]);
        this.fromStackIndex = Integer.parseInt(values[1]) - 1;
        this.toStackIndex = Integer.parseInt(values[2]) - 1;
    }

    public int getNumCrates() {
        return numCrates;
    }

    public int getFromStackIndex() {
        return fromStackIndex;
    }

    public int getToStackIndex() {
        return toStackIndex;
    }
}
