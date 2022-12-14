package xyz.foulds.aoc.year22.day14;

import java.io.IOException;

import org.junit.Test;

public class MainTest
{
    @Test
    public void testInputExample() throws IOException
    {
        Main.main(new String[]{"src/test/resources/inputTest.txt"});
    }

    @Test
    public void testInput() throws IOException
    {
        Main.main(new String[]{"src/test/resources/input.txt"});
    }
}
