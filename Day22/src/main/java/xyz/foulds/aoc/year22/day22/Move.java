package xyz.foulds.aoc.year22.day22;

public class Move
{
    private final int steps;
    private final String turn;

    public Move(final int steps, final String turn)
    {
        this.steps = steps;
        this.turn = turn;
    }

    public int getSteps()
    {
        return steps;
    }

    public String getTurn()
    {
        return turn;
    }
}
