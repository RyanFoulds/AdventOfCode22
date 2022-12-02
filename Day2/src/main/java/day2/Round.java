package day2;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round implements Scorable
{
    private static final Map<Shape, Integer> INDEXES = new HashMap<>();
    static
    {
        INDEXES.put(Shape.ROCK, 0);
        INDEXES.put(Shape.PAPER, 1);
        INDEXES.put(Shape.SCISSORS, 2);
    }
    private static final List<Shape> SHAPES = Arrays.asList(Shape.ROCK, Shape.PAPER, Shape.SCISSORS);

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
        final int diff = Math.floorMod(INDEXES.get(own) - INDEXES.get(opponent), 3);
        switch (diff)
        {
            case 0: return Result.DRAW;
            case 1: return Result.WIN;
            case 2: return Result.LOSS;
        }
        throw new IllegalArgumentException();
    }

    public Shape calculateShape()
    {
        switch (result)
        {
            case DRAW: return opponent;
            case LOSS: return SHAPES.get((INDEXES.get(opponent) + 2) % 3);
            case WIN: return SHAPES.get((INDEXES.get(opponent) + 1) % 3);
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
