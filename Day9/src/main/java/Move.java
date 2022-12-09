public class Move
{
    private final Direction direction;
    private final int steps;

    public Move(final String move)
    {
        this.direction = Direction.fromChar(move.charAt(0));
        this.steps = Integer.parseInt(move.substring(2));
    }

    public Direction getDirection() {
        return direction;
    }
    public int getSteps() {
        return steps;
    }
}
