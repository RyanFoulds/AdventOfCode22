package xyz.foulds.aoc.year22.day10;

public class NoOp implements Instruction
{
    @Override
    public int getCycles()
    {
        return 1;
    }

    @Override
    public int getArgument()
    {
        return 0;
    }
}
