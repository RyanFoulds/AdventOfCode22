package day2;


public class Round implements Scorable
{
    private final Shape opponent;
    private final Shape own;
    private final Result result;

    private final long score;

    public Round(final Shape opponent, final Shape own)
    {
        this.opponent = opponent;
        this.own = own;

        result = calculateResult();
        score = result.getScore() + own.getScore();
    }

    public Round(final Shape opponent, final Result target)
    {
        this.opponent = opponent;
        this.result = target;

        this.own = calculateShape();
        this.score = result.getScore() + own.getScore();
    }

    public Result getResult()
    {
        return result;
    }

    public Result calculateResult()
    {
        switch (own)
        {
            case ROCK:
                switch (opponent)
                {
                    case SCISSORS: return Result.WIN;
                    case PAPER: return Result.LOSS;
                    case ROCK: return Result.DRAW;
                }
                break;
            case PAPER:
                switch (opponent)
                {
                    case SCISSORS: return Result.LOSS;
                    case PAPER: return Result.DRAW;
                    case ROCK: return Result.WIN;
                }
                break;
            case SCISSORS:
                switch (opponent)
                {
                    case SCISSORS: return Result.DRAW;
                    case PAPER: return Result.WIN;
                    case ROCK: return Result.LOSS;
                }
                break;
        }
        throw new IllegalArgumentException("Invalid shape");
    }

    public Shape calculateShape()
    {
        switch (result)
        {
            case DRAW: return opponent;
            case LOSS:
                switch (opponent)
                {
                    case ROCK: return Shape.SCISSORS;
                    case PAPER: return Shape.ROCK;
                    case SCISSORS: return Shape.PAPER;
                }
                break;
            case WIN:
                switch (opponent)
                {
                    case ROCK: return Shape.PAPER;
                    case PAPER: return Shape.SCISSORS;
                    case SCISSORS: return Shape.ROCK;
                }
                break;
        }
        throw new IllegalArgumentException();
    }

    public long getScore()
    {
        return score;
    }

    public static Round fromString(final String string)
    {
        final String[] strings = string.split(" ");
        return new Round(Shape.fromString(strings[0]), Shape.fromString(strings[1]));
    }

    public static Round fromResultString(final String string)
    {
        final String[] strings = string.split(" ");
        return new Round(Shape.fromString(strings[0]), Result.fromString(strings[1]));
    }
}
