package xyz.foulds.aoc.year22.day17;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tetris
{

    private static final char[] rockOrder = "abcde".toCharArray();
    private final char[] inputs;
    private long rockCount = 0;
    private boolean[][] board = new boolean[7][10000];
    private Rock currentRock;

    private boolean doneRepeating = false;
    private long totalNormalisation = 0;
    private Map<DashBlockState, BoardState> record = new HashMap<>();

    public Tetris(final char[] inputs)
    {
        this.inputs = inputs;
        createRock();
    }


    private void createRock()
    {
        final int hightestRock = calculateHighPoint();
        currentRock = new Rock(rockOrder[(int) (rockCount % rockOrder.length)], hightestRock + 4);
        rockCount += 1;
    }

    public void play(final long rocks)
    {
        for (int i = 0; rockCount <= rocks; i++)
        {
            final int moveIndex = i % inputs.length;
            if (doTurn(moveIndex))
            {
                if (currentRock.isDash() && !doneRepeating)
                {
                    // record x position of the "-" block and the move index.
                    // When it repeats, need to know the number of blocks and the height added between repeats.
                    final int x = currentRock.getCoordinates().stream().mapToInt(Coordinate::getX).min().orElse(0);
                    final DashBlockState dashBlockState = new DashBlockState(x, moveIndex);
                    final BoardState boardState = new BoardState(totalHeight(), rockCount);
    
                    if (record.containsKey(dashBlockState))
                    {
                        // Repetition detected.
                        final BoardState oldBoardState = record.get(dashBlockState);
                        final long rocksDiff = rockCount - oldBoardState.getRockNum();
                        final long heightDiff = boardState.getHeight() - oldBoardState.getHeight();
    
                        final long timesToAdd = (rocks - rockCount) / rocksDiff;
    
                        totalNormalisation = timesToAdd * heightDiff;
                        rockCount += timesToAdd * rocksDiff;
                        doneRepeating = true;
                    }
                    else
                    {
                        record.put(dashBlockState, boardState);
                    }
                }
                createRock();
            }
        }
    }

    /** returns true if the piece is done moving. */
    private boolean doTurn(final int moveIndex)
    {
        currentRock.move(inputs[moveIndex], board);
        if (!currentRock.fall(board))
        {
            currentRock.getCoordinates().forEach(c -> board[c.getX()][c.getY()] = true);
            return true;
        }
        return false;
    }

    private int calculateHighPoint()
    {
        int highPoint = -1;
        for (final boolean[] column : board)
        {
            for (int y = column.length-1; y >= 0; y--)
            {
                if (column[y])
                {
                    if (y > highPoint)
                    {
                        highPoint = y;
                    }
                    break;
                }
            }
        }
        return highPoint;
    }

    public long totalHeight()
    {
        return calculateHighPoint() + totalNormalisation + 1;
    }

    @Override
    public String toString()
    {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final boolean[] column : board)
        {
            for (int y = 0; y < 30; y++)
            {
                stringBuilder.append(column[y] ? '#' : ' ').append(",");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
        
    }
}
