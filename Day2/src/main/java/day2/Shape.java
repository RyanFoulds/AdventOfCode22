package day2;

public enum Shape implements Scorable
{
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final long score;

    Shape(final long score)
    {
        this.score = score;
    }

    @Override
    public long getScore()
    {
        return score;
    }

    public static Shape fromString(final String string)
    {
        switch (string)
        {
            case "A":
            case "X":
                return ROCK;
            case "B":
            case "Y":
                return PAPER;
            case "C":
            case "Z":
                return SCISSORS;
            default:
                throw new IllegalArgumentException("Input must be one of ABCXYZ");
        }
    }
}
