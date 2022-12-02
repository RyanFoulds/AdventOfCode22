package day2;

public enum Result implements Scorable
{
    WIN(6),
    LOSS(0),
    DRAW(3);

    private final long score;

    Result(final long score)
    {
        this.score = score;
    }

    @Override
    public long getScore()
    {
        return score;
    }

    public static Result fromString(final String string)
    {
        switch (string)
        {
            case "X": return LOSS;
            case "Y": return DRAW;
            case "Z": return WIN;
            default: throw new IllegalArgumentException();
        }
    }
}
