package lives.CB;

import info.Camp;
import lives.Creature;

public class OrangeBoy extends Creature {
    //init
    public OrangeBoy(int rowPosition, int columnPosition)
    {
        //vary from 20 to 50, pattern 0:x
        super("二娃", Camp.CB,2,rowPosition,columnPosition,50,20,0);
    }

    @Override
    public void getName()
    {
        System.out.println("o");
    }
}
