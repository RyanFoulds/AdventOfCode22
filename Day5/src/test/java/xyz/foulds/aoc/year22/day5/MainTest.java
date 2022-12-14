package xyz.foulds.aoc.year22.day5;

import org.junit.Test;

import java.io.IOException;

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