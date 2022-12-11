package xyz.foulds.aoc.year22.day11;

import java.util.function.Function;

public class Operation implements Function<Long, Long>
{
    private final char operator;
    private final long operand;
    public Operation(final String string)
    {
        this.operator = string.charAt(21);
        final String substring = string.substring(23);
        if ("old".equals(substring))
        {
            operand = -1;
        }
        else
        {
            operand = Integer.parseInt(substring);
        }
    }

    @Override
    public Long apply(final Long integer)
    {
        final long resolvedOperand = operand == -1 ? integer : operand;
        if ('*' == operator)
        {
            return integer * resolvedOperand;
        }
        else if ('+' == operator)
        {
            return integer + resolvedOperand;
        }

        throw new IllegalArgumentException();
    }

}
