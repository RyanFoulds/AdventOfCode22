package xyz.foulds.aoc.year22.day13;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class NodeTest
{

    @Test
    public void testListVsList()
    {
        final Node one = new Node("[1,1,3,1,1]");
        final Node other = new Node("[1,1,5,1,1]");

        assert one.compareTo(other) < 0;
        assert other.compareTo(one) > 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }

    @Test
    public void test2()
    {
        final Node one = new Node("[[1],[2,3,4]]");
        final Node other = new Node("[[1],4]");

        assert one.compareTo(other) < 0;
        assert other.compareTo(one) > 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }

    @Test
    public void testSingleVsNestedList()
    {
        final Node one = new Node("[9]");
        final Node other = new Node("[[8,7,6]]");

        assert one.compareTo(other) > 0;
        assert other.compareTo(one) < 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }

    @Test
    public void test4()
    {
        final Node one = new Node("[[4,4],4,4]");
        final Node other = new Node("[[4,4],4,4,4]");

        assert one.compareTo(other) < 0;
        assert other.compareTo(one) > 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }
    
    @Test
    public void test5()
    {
        final Node one = new Node("[7,7,7,7]");
        final Node other = new Node("[7,7,7]");

        assert one.compareTo(other) > 0;
        assert other.compareTo(one) < 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }

    @Test
    public void testEmptyVsItem()
    {
        final Node one = new Node("[]");
        final Node other = new Node("[3]");

        assert one.compareTo(other) < 0;
        assert other.compareTo(one) > 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }

    @Test
    public void testNestedEmpties()
    {
        final Node one = new Node("[[[]]]");
        final Node other = new Node("[[]]");

        assert one.compareTo(other) > 0;
        assert other.compareTo(one) < 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }

    @Test
    public void testUtterMadness()
    {
        final Node one = new Node("[1,[2,[3,[4,[5,6,7]]]],8,9]");
        final Node other = new Node("[1,[2,[3,[4,[5,6,0]]]],8,9]");

        assert one.compareTo(other) > 0;
        assert other.compareTo(one) < 0;
        assert one.compareTo(one) == 0;
        assert other.compareTo(other) == 0;
    }
}