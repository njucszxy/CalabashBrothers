package lives;

import info.Camp;
import info.Commander;

public class Creature implements Runnable{
    //battle attributes
    public int powerTop = -1;
    public int powerBottom = -1;
    public int powerPattern = -1;
    public boolean isAlive = true;

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
            int result = Commander.oneStep(this);
            if(result == 0)
            {
                //System.out.println(this.name + " End With Game Over");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(this.name + " End With isAlive == false");
    }
}
