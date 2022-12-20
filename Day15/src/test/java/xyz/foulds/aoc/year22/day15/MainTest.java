package xyz.foulds.aoc.year22.day15;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MainTest
{
    @Test
    public void inputTest() throws IOException
    {
        Main.main(new String[]{"src/test/resources/inputTest.txt", "10", "20"});
    }

    @Test
    public void input() throws IOException
    {
        Main.main(new String[]{"src/test/resources/input.txt", "2000000", "4000000"});
    }
}
