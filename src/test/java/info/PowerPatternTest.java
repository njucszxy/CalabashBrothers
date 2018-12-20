package info;

import org.junit.Test;

import static org.junit.Assert.*;

public class PowerPatternTest {
    @Test
    public void getRandomNumTest() {
        int powerTop = 100;
        int powerBottom = 50;
        //test1
        int result1 = PowerPattern.getRandomNum(powerTop,powerBottom);
        assertEquals(true,(result1 >= powerBottom) && (result1 <= powerTop));
        //test2
        int result2 = PowerPattern.getRandomNum(powerTop,powerBottom);
        assertEquals(true,(result2 >= powerBottom) && (result2 <= powerTop));
        //test3
        int result3 = PowerPattern.getRandomNum(powerTop,powerBottom);
        assertEquals(true,(result3 >= powerBottom) && (result3 <= powerTop));
        //test4
        int result4 = PowerPattern.getRandomNum(powerTop,powerBottom);
        assertEquals(true,(result4 >= powerBottom) && (result4 <= powerTop));
        //test5
        int result5 = PowerPattern.getRandomNum(powerTop,powerBottom);
        assertEquals(true,(result5 >= powerBottom) && (result5 <= powerTop));
    }

    @Test
    public void funcXTest()
    {
        //test1
        assertEquals(10,PowerPattern.funcX(10));
        //test2
        assertEquals(100,PowerPattern.funcX(100));
        //test3
        assertEquals(1000,PowerPattern.funcX(1000));
        //test4
        assertEquals(172837,PowerPattern.funcX(172837));
        //test5
        assertNotEquals(129014,PowerPattern.funcX(139128));
    }

    @Test
    public void funcX2Test()
    {
        //test1
        assertEquals(100,PowerPattern.funcX2(10));
        //test2
        assertEquals(10000,PowerPattern.funcX2(100));
        //test3
        assertEquals(3600,PowerPattern.funcX2(60));
        //test4
        assertEquals(6400,PowerPattern.funcX2(80));
        //test5
        assertNotEquals(12902413,PowerPattern.funcX2(1391));
    }

    @Test
    public void funcRootXTest()
    {
        //test1
        assertEquals((int)Math.log(100*99),PowerPattern.funcRootX(100));
        //test2
        assertEquals((int)Math.log(10000*9999),PowerPattern.funcRootX(10000));
        //test3
        assertEquals((int)Math.log(3600*3599),PowerPattern.funcRootX(3600));
        //test4
        assertEquals((int)Math.log(6401*6400),PowerPattern.funcRootX(6401));
        //test5
        assertNotEquals((int)Math.log(1293*1292),PowerPattern.funcRootX(13911234));
    }

    @Test
    public void funcSquareRootXTest()
    {
        //test1
        assertEquals(10,PowerPattern.funcSquareRootX(100));
        //test2
        assertEquals(100,PowerPattern.funcSquareRootX(10000));
        //test3
        assertEquals(60,PowerPattern.funcSquareRootX(3600));
        //test4
        assertEquals(80,PowerPattern.funcSquareRootX(6400));
        //test5
        assertNotEquals(1290,PowerPattern.funcSquareRootX(13912454));
    }

    @Test
    public void funcEXTest()
    {
        //test1
        assertEquals((int)Math.exp(3),PowerPattern.funcEX(3));
        //test2
        assertEquals((int)Math.exp(4),PowerPattern.funcEX(4));
        //test3
        assertEquals((int)Math.exp(5),PowerPattern.funcEX(5));
        //test4
        assertEquals((int)Math.exp(6),PowerPattern.funcEX(6));
        //test5
        assertNotEquals((int)Math.exp(7.5),PowerPattern.funcEX(7));
    }
}