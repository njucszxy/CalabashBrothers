package lives.MO;

import info.Camp;
import lives.Creature;

public class Scorpion extends Creature {
    //init
    public Scorpion(int rowPosition, int columnPosition)
    {
        //vary from 625 to 1600, pattern 2:Square Root x*(x-1)
        super("蝎子精", Camp.MO,1,rowPosition,columnPosition,625,1600,2);
    }

    @Override
    public void getName()
    {
        System.out.println("s");
    }
}
