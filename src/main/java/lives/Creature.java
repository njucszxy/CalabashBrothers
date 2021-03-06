package lives;

import info.Camp;
import info.Commander;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.Thread.yield;

public class Creature implements Runnable{
    //battle attributes
    public int powerTop = -1;
    public int powerBottom = -1;
    public int powerPattern = -1;
    public boolean isAlive = true;
    public int healthPoint = 1000;

    //non-battle attributes
    public String name = new String("Undefined");
    public Camp camp = Camp.NE;
    public int rank = -1;
    public int rowPosition = -1;
    public int columnPosition = -1;

    //global view


    //init
    public Creature(){}
    public Creature(String name,Camp camp,int rank,int rowPosition,int columnPosition,int powerTop,int powerBottom,int powerPattern)
    {
        this.name = name;
        this.camp = camp;
        this.rank = rank;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.powerTop = powerTop;
        this.powerBottom = powerBottom;
        this.powerPattern = powerPattern;
    }

    //move...
    public void getName(){}

    //fight..

    //run...
    public void run()
    {
        while(this.isAlive)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int result = Commander.oneStep(this) ;
            if(result == 0)
            {
                return;
            }
            yield();
        }
    }
}
