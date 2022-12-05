import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stacks
{
    private final List<Stack<Character>> stacks;

    public Stacks(final String string)
    {
        final String[] rows = string.split("\n");
        final int numOfStacks = (int) rows[rows.length - 2].trim().chars()
                                                                  .filter(cha -> ']' == cha)
                                                                  .count();
        this.stacks = new ArrayList<>(numOfStacks);

        // Loop over the rows from bottom to top.
        for (int i = rows.length - 2; i >= 0; i--)
        {
            for (int j = 0; j < numOfStacks; j++)
            {
                addToStack(j, rows[i]);
            }
        }
    }

    private void addToStack(final int stackIndex, final String row)
    {
        if (stacks.size() <= stackIndex)
        {
            stacks.add(new Stack<>());
        }

        final Stack<Character> stack = stacks.get(stackIndex);

        if (row.length() > getColumnForStack(stackIndex))
        {
            final char content = row.charAt(getColumnForStack(stackIndex));
            if (!Character.isWhitespace(content))
            {
                stack.add(content);
            }
        }
    }

    private int getColumnForStack(final int stackIndex)
    {

        return 4 * stackIndex + 1;
    }

    public void doMove(final Move move)
    {
        final Stack<Character> fromStack = stacks.get(move.getFromStackIndex());
        final Stack<Character> toStack = stacks.get(move.getToStackIndex());

        for (int i = 0; i < move.getNumCrates(); i++)
        {
            toStack.add(fromStack.pop());
        }
    }

    public void doGroupedMove(final Move move)
    {
        final Stack<Character> tempStack = new Stack<>();
        final Stack<Character> fromStack = stacks.get(move.getFromStackIndex());
        final Stack<Character> toStack = stacks.get(move.getToStackIndex());

        for (int i = 0; i < move.getNumCrates(); i++)
        {
            tempStack.add(fromStack.pop());
        }
        for (int i = 0; i < move.getNumCrates(); i++)
        {
            toStack.add(tempStack.pop());
        }
    }

    public String getStackTops()
    {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Stack<Character> stack :stacks)
        {
            stringBuilder.append(stack.isEmpty() ? ' ' : stack.peek());
        }

        return stringBuilder.toString();
    }
}
