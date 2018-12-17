package sample.info;

public class PowerPattern {
    private static int getRandomNum(int powerTop,int powerBottom)
    {
        final double d = Math.random();
        final int result = (int)(d * (powerTop-powerBottom)) + powerBottom;
        return result;
    }
    //pattern 0
    private static int funcX(int r)
    {
        return r;
    }
    //pattern 1
    private static int funcX2(int r)
    {
        return r*r;
    }
    //pattern 2
    private static int funcRootX(int r)
    {
        return (int)(Math.log(r * (r-1)));
    }
    //pattern 3
    private static int funcSquareRootX(int r)
    {
        return (int)(Math.sqrt(r));
    }
    //pattern 4
    private static int funcEX(int r)
    {
        return (int)(Math.exp(r));
    }
    public static int getPowerNum(int powerPattern,int powerTop,int powerBottom)
    {
        switch (powerPattern)
        {
            case 0:return funcX(getRandomNum(powerTop,powerBottom));
            case 1:return funcX2(getRandomNum(powerTop,powerBottom));
            case 2:return funcRootX(getRandomNum(powerTop,powerBottom));
            case 3:return funcSquareRootX(getRandomNum(powerTop,powerBottom));
            case 4:return funcEX(getRandomNum(powerTop,powerBottom));
            default:return -1;
        }
    }
    public static boolean getRandomResult()
    {
        final double d = Math.random();
        final int result = (int)(d*100);
        if(d > 50)
            return true;
        else
            return false;
    }
}
