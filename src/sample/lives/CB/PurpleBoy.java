package sample.lives.CB;

import sample.info.Camp;
import sample.lives.Creature;

public class PurpleBoy extends Creature {
    //init
    public PurpleBoy(int rowPosition, int columnPosition)
    {
        //vary from 0 to 100, pattern 0:x
        super("七娃", Camp.CB,7,rowPosition,columnPosition,100,0,0);
    }

    @Override
    public void getName()
    {
        System.out.println("p");
    }
}
