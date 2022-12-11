package xyz.foulds.aoc.year22.day10;

public interface Instruction
{
    int getCycles();

    int getArgument();

    static Instruction fromString(final String string)
    {
        if ("noop".equals(string))
        {
            return new NoOp();
        }
        else if (string != null && string.startsWith("addx"))
        {
            return new AddX(Integer.parseInt(string.substring(5)));
        }
        throw new IllegalArgumentException();
    }
}
