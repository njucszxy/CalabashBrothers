package lives.CB;


import info.Camp;
import lives.Creature;

public class CyanBoy extends Creature {
    //init
    public CyanBoy(int rowPosition, int columnPosition)
    {
        //vary from 6 to 10, pattern 1:x^2
        super("五娃", Camp.CB,5,rowPosition,columnPosition,6,10,1);
    }

    @Override
    public void getName()
    {
        System.out.println("c");
    }
}