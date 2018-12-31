package gui;

import info.Commander;
import info.Database;

import static java.lang.Thread.yield;

public class FreshThread implements Runnable {
    public Database database;
    public Controller controller;
    public int updateRate = 17;
    public FreshThread(Database database,Controller controller)
    {
        this.database = database;
        this.controller = controller;
    }

    public void run()
    {
        while(true)
        {
            synchronized (database)
            {
                //sleep
                try {
                    Thread.sleep(updateRate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(!database.gameOn)
                    return;


                //clear
                controller.clearLayer1();

                //calculate
                for(int i = 0;i < database.bullets.size();i++)
                {
                    double minDis = 10000;
                    int id = -1;
                    for(int j = 0;j < 16;j++)
                    {
                        if(database.creatures.get(j).isAlive && database.bullets.get(i).camp != database.creatures.get(j).camp && Math.abs((double)database.creatures.get(j).rowPosition - database.bullets.get(i).currentRowPosition) <= 0.5 && Math.abs((double)database.creatures.get(j).columnPosition - database.bullets.get(i).currentColumnPosition) <= 0.5)
                        {
                            double num =  Math.abs(database.creatures.get(j).rowPosition - database.bullets.get(i).currentRowPosition) + Math.abs(database.creatures.get(j).columnPosition- database.bullets.get(i).currentColumnPosition);
                            if(num < minDis)
                            {
                                id = j;
                                minDis = num;
                            }
                        }
                    }

                    //attack
                    if(id == -1 || minDis == 10000)
                    {
                        //next position
                        double stdSpeed = (double)5 / ((double)1000 / updateRate);
                        double degree = Math.atan((double) Math.abs(database.bullets.get(i).ySpeed / database.bullets.get(i).xSpeed));
                        double xSpeed = Math.abs(Math.cos(degree) * stdSpeed);
                        double ySpeed = Math.abs(Math.sin(degree) * stdSpeed);
                        if(database.bullets.get(i).xSpeed > 0 )
                            xSpeed = -xSpeed;
                        if(database.bullets.get(i).ySpeed > 0 )
                            ySpeed = -ySpeed;

                        database.bullets.get(i).currentRowPosition += xSpeed;
                        database.bullets.get(i).currentColumnPosition += ySpeed;

                        //move out
                        if(database.bullets.get(i).currentRowPosition < 0 || database.bullets.get(i).currentRowPosition > controller.layer1.getHeight() || database.bullets.get(i).currentColumnPosition < 0 || database.bullets.get(i).currentColumnPosition > controller.layer1.getWidth())
                            database.bullets.remove(i);
                        continue;
                    }
                    if(database.bullets.get(i).damage >= database.creatures.get(id).healthPoint)
                    {
                        //kill
                        database.creatures.get(id).isAlive = false;
                        controller.printText(database.creatures.get(id).name + " is dead\n");
                        database.battleField.lands.get(database.creatures.get(id).rowPosition * 20 + database.creatures.get(id).columnPosition).isUsed = false;
                    }
                    else
                    {
                        //damage
                        database.creatures.get(id).healthPoint -= database.bullets.get(i).damage;
                    }
                    //remove bullet
                    database.bullets.remove(i);
                }

                //paint remains
                for(int i = 0;i < 16;i++)
                {
                    if(!database.creatures.get(i).isAlive)
                    {
                        controller.paintRemains(database.creatures.get(i).rowPosition,database.creatures.get(i).columnPosition);
                    }
                }

                //paint creatures
                for(int i = 0;i < 16;i++)
                {
                    if(database.creatures.get(i).isAlive)
                    {
                        controller.paintImage(database.creatures.get(i).name,database.creatures.get(i).rowPosition,database.creatures.get(i).columnPosition);
                    }
                }

                //paint bullets
                for(int i = 0;i < database.bullets.size();i++)
                {
                    controller.paintBullet(database.bullets.get(i).currentRowPosition,database.bullets.get(i).currentColumnPosition,database.bullets.get(i).camp);
                }

                yield();
            }
        }
    }
}
