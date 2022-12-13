package xyz.foulds.aoc.year22.day13;

public class Pair
{
    private final Node left;
    private final Node right;

    public Pair(final Node left, final Node right)
    {
        this.left = left;
        this.right = right;
    }

    public boolean isOrdered()
    {
        return left.compareTo(right) < 0;
    }

}
