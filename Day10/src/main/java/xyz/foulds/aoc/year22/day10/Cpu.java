package xyz.foulds.aoc.year22.day10;

public class Cpu
{
    private int register = 1;

    private int cycle = 0;

    private int signalStrengthIntegral = 0;

    public void execute(final Instruction instruction)
    {
        for (int i = 0; i < instruction.getCycles(); i++)
        {
            doACycle();
        }
        register += instruction.getArgument();
    }

    public int getSignalStrengthIntegral()
    {
        return signalStrengthIntegral;
    }

    private void doACycle()
    {
        final int normalisedCycle = cycle++ % 40;

        final String toPrint = (Math.abs(normalisedCycle - register) <= 1) ? "█" : " ";
        System.out.print(toPrint);

        if (normalisedCycle == 39)
        {
            System.out.print("\n");
        }
        else if (normalisedCycle == 20)
        {
            signalStrengthIntegral += cycle * register;
        }
    }
}
