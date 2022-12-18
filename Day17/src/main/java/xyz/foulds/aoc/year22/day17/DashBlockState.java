package xyz.foulds.aoc.year22.day17;

import java.util.Objects;

public class DashBlockState
{
    private final int startingX;
    private final int moveIndex;

    public DashBlockState(final int startingX, final int moveIndex)
    {
        this.startingX = startingX;
        this.moveIndex = moveIndex;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        final DashBlockState that = (DashBlockState) obj;
        return startingX == that.startingX && moveIndex == that.moveIndex;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(moveIndex, startingX);
    }
}
