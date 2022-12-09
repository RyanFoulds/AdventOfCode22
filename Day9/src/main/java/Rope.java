import java.util.HashSet;
import java.util.Set;

public class Rope
{
    private final Coordinate[] knots;
    private final Set<Coordinate> tailVisits = new HashSet<>();

    public Rope(final int length)
    {
        // Populate the initial state of the rope.
        knots = new Coordinate[length];
        for (int i = 0; i < length; i++)
        {
            knots[i] = new Coordinate(0, 0);
        }

        // Record visit to the origin.
        tailVisits.add(knots[knots.length - 1]);
    }

    public void moveHead(final Move move)
    {
        for (int i = 0; i < move.getSteps(); i++)
        {
            knots[0] = knots[0].move(move.getDirection());
            moveFollowingKnots();
        }
    }

    private void moveFollowingKnots()
    {
        for (int i = 1; i < knots.length; i++)
        {
            knots[i] = knots[i].follow(knots[i-1]);
        }

        tailVisits.add(knots[knots.length - 1]);
    }

    public int countUniqueTailVisits()
    {
        return tailVisits.size();
    }
}
