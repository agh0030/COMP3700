import org.junit.*;

import static org.junit.Assert.assertEquals;

public class JunitTest {

    @Test
    public void test_validMax()
    {
        Finder finder = new Finder();
        int[] t = {1, 2, 3, 4, 5, 6};
        Integer expected = 6;
        Integer actual = finder.findMax(t);

        assertEquals(actual, expected);
    }
    @Test
    public void test_nullMax()
    {
        Finder finder = new Finder();
        int[] t = null;
        Integer expected = null;
        Integer actual = finder.findMax(t);

        assertEquals(actual, expected);
    }
    @Test
    public void test_emptyMax()
    {
        Finder finder = new Finder();
        int[] t = {};
        Integer expected = null;
        Integer actual = finder.findMax(t);

        assertEquals(actual, expected);
    }
    @Test
    public void test_validMin()
    {
        Finder finder = new Finder();
        int[] t = {1, 2, 3, 4, 5, 6};
        Integer expected = 1;
        Integer actual = finder.findMin(t);

        assertEquals(actual, expected);
    }
    @Test
    public void test_nullMin()
    {
        Finder finder = new Finder();
        int[] t = null;
        Integer expected = null;
        Integer actual = finder.findMin(t);

        assertEquals(actual, expected);
    }
    @Test
    public void test_emptyMin()
    {
        Finder finder = new Finder();
        int[] t = {};
        Integer expected = null;
        Integer actual = finder.findMin(t);

        assertEquals(actual, expected);
    }

}
