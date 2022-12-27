package xyz.foulds.aoc.year22.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class Snafu
{
    private static final List<Character> CHARS = Stream.of('=', '-', '0', '1', '2')
                                                       .collect(Collectors.toList());
    private final long value;
    private final List<Character> chars;

    public Snafu(final String string)
    {
        chars = new ArrayList<>(string.length());
        for (int i = string.length()-1; i >= 0; i--)
        {
            chars.add(string.charAt(i));
        }

        long tempVal = 0;
        for (int j = 0; j < chars.size(); j++)
        {
            tempVal += fromChar(chars.get(j)) * (long) Math.pow(5, j);
        }
        value = tempVal;
    }

    public Snafu(final long value)
    {
        this.value = value;
        chars = new ArrayList<>();

        long carry = 0;
        int i = 1;

        while (true)
        {
            final long scale = (long) Math.pow(5, i-1);

            if (scale/5 > value)
            {
                break;
            }

            final long val = ((value % (5*scale)) / (scale)) + carry;
            carry = getCarry(val);
            char c = getChar(val);
            chars.add(c);
            i+=1;
        }
    }

    private long fromChar(final char c)
    {
        return CHARS.indexOf(c) - 2;
    }

    private char getChar(final long l)
    {
        final int index = (int) (l+2) % 5;
        return CHARS.get(index);
    }
    private long getCarry(final long l)
    {
        return (l+2) / 5;
    }

    public long getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return chars.stream()
                    .map(String::valueOf)
                    .reduce((s1, s2) -> s1 + s2)
                    .map(StringUtils::reverse)
                    .map(str -> StringUtils.stripStart(str, "0"))
                    .orElseThrow(IllegalStateException::new);
    }
}
