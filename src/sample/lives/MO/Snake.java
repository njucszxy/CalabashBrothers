package sample.lives.MO;

import sample.info.Camp;
import sample.lives.Creature;

public class Snake extends Creature {
    //init
    public Snake(int rowPosition, int columnPosition)
    {
        //vary from 900 to 2500, pattern 2:Square Root x*(x-1)
        super("蛇精", Camp.MO,0,rowPosition,columnPosition,900,2500,2);
    }

    @Override
    public void getName()
    {
        System.out.println("S");
    }
}
