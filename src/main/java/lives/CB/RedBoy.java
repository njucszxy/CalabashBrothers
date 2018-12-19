package lives.CB;

import info.Camp;
import lives.Creature;

public class RedBoy extends Creature {
    //init
    public RedBoy(int rowPosition, int columnPosition)
    {
        //vary from 40 to 60, pattern 0:x
        super("大娃", Camp.CB,1,rowPosition,columnPosition,60,40,0);
    }

    @Override
    public void getName()
    {
        System.out.println("r");
    }
}
