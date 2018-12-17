package sample.lives.CB;

import sample.info.Camp;
import sample.lives.Creature;

public class YellowBoy extends Creature {
    //init
    public YellowBoy(int rowPosition, int columnPosition)
    {
        //vary from 3600 to 6400, pattern 3:square root x
        super("三娃", Camp.CB,3,rowPosition,columnPosition,6400,3600,3);
    }

    @Override
    public void getName()
    {
        System.out.println("y");
    }
}
