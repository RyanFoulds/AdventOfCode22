import java.io.IOException;

import org.junit.Test;

import xyz.foulds.aoc.year22.day16.Main;

public class MainTest
{
    @Test
    public void input() throws IOException
    {
        Main.main(new String[]{"src/test/resources/input.txt"});
    }

    @Test
    public void inputTest() throws IOException
    {
        Main.main(new String[]{"src/test/resources/inputTest.txt"});
    }
}
