package lives.CB;

import info.Camp;
import lives.Creature;

public class GrandPa extends Creature {
    //init
    public GrandPa(int rowPosition, int columnPosition)
    {
        //vary from 1 to 5, pattern 1:x^2
        super("爷爷", Camp.CB,0,rowPosition,columnPosition,5,1,1);
    }

    @Override
    public void getName()
    {
        System.out.println("G");
    }
}