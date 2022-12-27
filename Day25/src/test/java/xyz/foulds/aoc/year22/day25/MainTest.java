package xyz.foulds.aoc.year22.day25;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class MainTest
{
    @Ignore
    @Test
    public void inputTest() throws IOException
    {
        Main.main(new String[]{"src/test/resources/inputTest.txt"});
    }

    @Ignore
    @Test
    public void input() throws IOException
    {
        Main.main(new String[]{"src/test/resources/input.txt"});
    }

    @Test
    public void snafuTest()
    {
        final long val = 1136658049970L;
        final Snafu a = new Snafu(val);
        final Snafu b = new Snafu(a.toString());
        assert val == a.getValue();
        assert val == b.getValue();
    }

    @Test
    public void snafuStringTest()
    {
        final String str = "1121-1110-1=0";
        final Snafu a = new Snafu(str);
        final Snafu b = new Snafu(a.getValue());
        assert str.equals(a.toString());
        assert str.equals(b.toString());
    }
}