package lives.MO;

import info.Camp;
import lives.Creature;

public class Footman extends Creature {
    //init
    public Footman(int rowPosition, int columnPosition)
    {
        //vary from 30 to 70, pattern 0:x
        super("小喽啰", Camp.MO,2,rowPosition,columnPosition,75,50 ,0);
    }

    @Override
    public void getName()
    {
        System.out.println("f");
    }
}
