package lives.CB;

import info.Camp;
import lives.Creature;

public class BlueBoy extends Creature {
    //init
    public BlueBoy(int rowPosition, int columnPosition)
    {
        //vary from 50 to 80, pattern 0:x
        super("六娃", Camp.CB,6,rowPosition,columnPosition,80,50,0);
    }

    @Override
    public void getName()
    {
        System.out.println("b");
    }
}
