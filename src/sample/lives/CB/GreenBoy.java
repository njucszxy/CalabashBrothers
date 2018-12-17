package sample.lives.CB;

import sample.info.Camp;
import sample.lives.Creature;

public class GreenBoy extends Creature {
    //init
    public GreenBoy(int rowPosition, int columnPosition)
    {
        //vary from 3 to 5, pattern 4:e^x
        super("四娃", Camp.CB,4,rowPosition,columnPosition,5,3,4);
    }

    @Override
    public void getName()
    {
        System.out.println("g");
    }
}
