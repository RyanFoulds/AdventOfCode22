package xyz.foulds.day10;

public class AddX implements Instruction
{
    private final int argument;

    public AddX(final int argument)
    {
        this.argument = argument;
    }

    @Override
    public int getCycles()
    {
        return 2;
    }

    @Override
    public int getArgument()
    {
        return argument;
    }
}
