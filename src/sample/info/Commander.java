package sample.info;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import sample.battlefield.BattleField;
import sample.gui.Controller;
import sample.lives.Creature;
import sample.lives.CB.*;
import sample.lives.MO.*;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Commander {
    public static List<Creature> creatures;
    public static List<Thread> threads;
    public static BattleField battleField;
    public static Controller controller;

    private static int currentRunTime = -1;

    public static void initCommander(Controller c)
    {
        //init Controller
        controller = c;

        //init runTimeCount
        currentRunTime = controller.getRunTimes();

        //init battlefield
        battleField = new BattleField();

        //init creatures
        creatures = new ArrayList<Creature>();
        creatures.add(new GrandPa(6,5));
        creatures.add(new RedBoy(7,5));
        creatures.add(new OrangeBoy(8,5));
        creatures.add(new YellowBoy(9,5));
        creatures.add(new GreenBoy(10,5));
        creatures.add(new CyanBoy(11,5));
        creatures.add(new BlueBoy(12,5));
        creatures.add(new PurpleBoy(13,5));
        creatures.add(new Snake(6,14));
        creatures.add(new Scorpion(7,14));
        creatures.add(new Footman(8,14));
        creatures.add(new Footman(9,14));
        creatures.add(new Footman(10,14));
        creatures.add(new Footman(11,14));
        creatures.add(new Footman(12,14));
        creatures.add(new Footman(13,14));

        battleField.lands.get(6*20 + 5).isUsed = true;
        battleField.lands.get(6*20 + 5).creatureIndex = 0;
        paintCreature("爷爷",6,5);
        battleField.lands.get(7*20 + 5).isUsed = true;
        battleField.lands.get(7*20 + 5).creatureIndex = 1;
        paintCreature("大娃",7,5);
        battleField.lands.get(8*20 + 5).isUsed = true;
        battleField.lands.get(8*20 + 5).creatureIndex = 2;
        paintCreature("二娃",8,5);
        battleField.lands.get(9*20 + 5).isUsed = true;
        battleField.lands.get(9*20 + 5).creatureIndex = 3;
        paintCreature("三娃",9,5);
        battleField.lands.get(10*20 + 5).isUsed = true;
        battleField.lands.get(10*20 + 5).creatureIndex = 4;
        paintCreature("四娃",10,5);
        battleField.lands.get(11*20 + 5).isUsed = true;
        battleField.lands.get(11*20 + 5).creatureIndex = 5;
        paintCreature("五娃",11,5);
        battleField.lands.get(12*20 + 5).isUsed = true;
        battleField.lands.get(12*20 + 5).creatureIndex = 6;
        paintCreature("六娃",12,5);
        battleField.lands.get(13*20 + 5).isUsed = true;
        battleField.lands.get(13*20 + 5).creatureIndex = 7;
        paintCreature("七娃",13,5);
        battleField.lands.get(6*20 + 14).isUsed = true;
        battleField.lands.get(6*20 + 14).creatureIndex = 8;
        paintCreature("蛇精",6,14);
        battleField.lands.get(7*20 + 14).isUsed = true;
        battleField.lands.get(7*20 + 14).creatureIndex = 9;
        paintCreature("蝎子精",7,14);
        battleField.lands.get(8*20 + 14).isUsed = true;
        battleField.lands.get(8*20 + 14).creatureIndex = 10;
        paintCreature("小喽啰",8,14);
        battleField.lands.get(9*20 + 14).isUsed = true;
        battleField.lands.get(9*20 + 14).creatureIndex = 11;
        paintCreature("小喽啰",9,14);
        battleField.lands.get(10*20 + 14).isUsed = true;
        battleField.lands.get(10*20 + 14).creatureIndex = 12;
        paintCreature("小喽啰",10,14);
        battleField.lands.get(11*20 + 14).isUsed = true;
        battleField.lands.get(11*20 + 14).creatureIndex = 13;
        paintCreature("小喽啰",11,14);
        battleField.lands.get(12*20 + 14).isUsed = true;
        battleField.lands.get(12*20 + 14).creatureIndex = 14;
        paintCreature("小喽啰",12,14);
        battleField.lands.get(13*20 + 14).isUsed = true;
        battleField.lands.get(13*20 + 14).creatureIndex = 15;
        paintCreature("小喽啰",13,14);

        //record position
        try{
            Document document = DocumentHelper.createDocument();
            Element rootElement = document.addElement("root");
            Element initElement = rootElement.addElement("init");
            for(int i = 0;i < 16;i++)
            {
                Element tempElement = initElement.addElement("creature" );
                tempElement.addAttribute("id",""+i);
                tempElement.addAttribute("name",creatures.get(i).name);
                tempElement.addAttribute("rowPosition","" + creatures.get(i).rowPosition);
                tempElement.addAttribute("columnPosition","" + creatures.get(i).columnPosition);
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            File file = new File("src/sample/info/" + currentRunTime + ".xml");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
            writer.write(document);
        }
        catch (FileNotFoundException e)
        {
            printText("File not found while init\n");
        }
        catch (UnsupportedEncodingException e)
        {
            printText("UnsupportedEncoding while init\n");
        }
        catch (IOException e)
        {
            printText("IO Exception while init\n");
        }

        threads = new ArrayList<Thread>();
        for(int i = 0;i < 16;i++)
            threads.add(new Thread(creatures.get(i)));
    }
    private static void printText(String text)
    {
        controller.printText(text);
    }
    private static void clearLand(int rowPosition,int columnPosition)
    {
        controller.clearRect(rowPosition,columnPosition);
    }
    private static void paintCreature(String name,int rowPosition,int columnPosition)
    {
        controller.paintImage( name,rowPosition,columnPosition);
    }
    public synchronized static int oneStep(Creature creature)
    {
        int currentCreatureIndex = 0;
        for(int i = 0;i < 16;i++)
        {
            if(creatures.get(i) == creature)
            {
                currentCreatureIndex = i;
                break;
            }
        }

        //is alive ?
        if(!creatures.get(currentCreatureIndex).isAlive)
            return -1;

        //Tell status
        /*
            TODO delete println
            */
        //System.out.println("I'm " + creatures.get(currentCreatureIndex).name + " at " + creatures.get(currentCreatureIndex).rowPosition + "," + creatures.get(currentCreatureIndex).columnPosition);

        //if succeed
        boolean success = true;
        for(int i = 0;i < 16;i++)
        {
            if((creatures.get(i).camp != creatures.get(currentCreatureIndex).camp) && creatures.get(i).isAlive)
            {
                success = false;
                break;
            }
        }
        if(success)
        {
            /*
            TODO delete println
            */
            //System.out.println("I'm " + creatures.get(currentCreatureIndex).name + " ,WE HAVE WON !");

            //record winner
            try
            {
                SAXReader reader = new SAXReader();
                File file = new File("src/sample/info/" + currentRunTime + ".xml");
                //if(!file.exists())
                //    printText("File dosen't exist\n");
                Document document = reader.read(file);
                Element rootElement = document.getRootElement();
                Element round = rootElement.addElement("end");
                round.addAttribute("winner",creatures.get(currentCreatureIndex).name);
                round.addAttribute("id","" + currentCreatureIndex);
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
                writer.write(document);
            }
            catch (DocumentException e)
            {
                printText("Document Exception while recording winner\n");
            }
            catch (FileNotFoundException e)
            {
                printText("File not found while recording winner\n");
            }
            catch (UnsupportedEncodingException e)
            {
                printText("UnsupportedEncoding while recording winner\n");
            }
            catch (IOException e)
            {
                printText("IO Exception while recording winner\n");
            }

            printText("I'm " + creatures.get(currentCreatureIndex).name + " ,WE HAVE WON !\n");
            return 0;
        }

        //find next enemy
        int minDistance = 40;
        int enemyRowPosition = 20;
        int enemyColumnPosition = 20;
        for(int i = 0;i < 16;i++)
        {
            if((creatures.get(i).camp != creatures.get(currentCreatureIndex).camp) && creatures.get(i).isAlive)
            {
                int temp = Math.abs(creatures.get(currentCreatureIndex).rowPosition - creatures.get(i).rowPosition) + Math.abs(creatures.get(currentCreatureIndex).columnPosition - creatures.get(i).columnPosition);
                if(temp < minDistance)
                {
                    minDistance = temp;
                    enemyRowPosition = creatures.get(i).rowPosition;
                    enemyColumnPosition = creatures.get(i).columnPosition;
                }
            }
        }

        //find next position
        int moveUp = 1;
        int moveRight = 1;
        if(enemyRowPosition == creatures.get(currentCreatureIndex).rowPosition)
            moveUp = 0;
        else if(enemyRowPosition > creatures.get(currentCreatureIndex).rowPosition)
            moveUp = -1;
        if(enemyColumnPosition == creatures.get(currentCreatureIndex).columnPosition)
            moveRight = 0;
        else if(enemyColumnPosition < creatures.get(currentCreatureIndex).columnPosition)
            moveRight = -1;
        int nextRowPosition = -1;
        int nextColumnPosition = -1;
        int nextRowPositionTemp;
        int nextColumnPositionTemp;
        int nextLandIndexTemp;
        if(moveUp == 1)
        {
            nextRowPositionTemp = creatures.get(currentCreatureIndex).rowPosition - 1;
            nextColumnPositionTemp = creatures.get(currentCreatureIndex).columnPosition;
            nextLandIndexTemp = nextRowPositionTemp*20 + nextColumnPositionTemp;
            if((!battleField.lands.get((nextLandIndexTemp)).isUsed) || (creatures.get(battleField.lands.get(nextLandIndexTemp).creatureIndex).camp != creatures.get(currentCreatureIndex).camp))
            {
                nextRowPosition = creatures.get(currentCreatureIndex).rowPosition - 1;
                nextColumnPosition = creatures.get(currentCreatureIndex).columnPosition;
            }
        }
        else if(moveUp == 0)
        {
            if(moveRight == 1)
            {
                nextRowPositionTemp = creatures.get(currentCreatureIndex).rowPosition;
                nextColumnPositionTemp = creatures.get(currentCreatureIndex).columnPosition + 1;
                nextLandIndexTemp = nextRowPositionTemp*20 + nextColumnPositionTemp;
                if((!battleField.lands.get((nextLandIndexTemp)).isUsed) || (creatures.get(battleField.lands.get(nextLandIndexTemp).creatureIndex).camp != creatures.get(currentCreatureIndex).camp))
                {
                    nextRowPosition = creatures.get(currentCreatureIndex).rowPosition;
                    nextColumnPosition = creatures.get(currentCreatureIndex).columnPosition + 1;
                }
            }
            else if(moveRight == 0)
                ;
            else if(moveRight == -1)
            {
                nextRowPositionTemp = creatures.get(currentCreatureIndex).rowPosition;
                nextColumnPositionTemp = creatures.get(currentCreatureIndex).columnPosition - 1;
                nextLandIndexTemp = nextRowPositionTemp*20 + nextColumnPositionTemp;
                if((!battleField.lands.get((nextLandIndexTemp)).isUsed) || (creatures.get(battleField.lands.get(nextLandIndexTemp).creatureIndex).camp != creatures.get(currentCreatureIndex).camp))
                {
                    nextRowPosition = creatures.get(currentCreatureIndex).rowPosition;
                    nextColumnPosition = creatures.get(currentCreatureIndex).columnPosition - 1;
                }
            }
        }
        else if(moveUp == -1)
        {
            nextRowPositionTemp = creatures.get(currentCreatureIndex).rowPosition + 1;
            nextColumnPositionTemp = creatures.get(currentCreatureIndex).columnPosition;
            nextLandIndexTemp = nextRowPositionTemp*20 + nextColumnPositionTemp;
            if((!battleField.lands.get((nextLandIndexTemp)).isUsed) || (creatures.get(battleField.lands.get(nextLandIndexTemp).creatureIndex).camp != creatures.get(currentCreatureIndex).camp))
            {
                nextRowPosition = creatures.get(currentCreatureIndex).rowPosition + 1;
                nextColumnPosition = creatures.get(currentCreatureIndex).columnPosition;
            }
        }

        //move
        if(nextRowPosition == -1 && nextColumnPosition == -1)
            return 1;
        int nextLandIndex = nextRowPosition*20 + nextColumnPosition;
        if(battleField.lands.get(nextLandIndex).isUsed)
        {
            //fight
            int enemyIndex = battleField.lands.get(nextLandIndex).creatureIndex;
            int myPower = PowerPattern.getPowerNum(creatures.get(currentCreatureIndex).powerPattern,creatures.get(currentCreatureIndex).powerTop,creatures.get(currentCreatureIndex).powerBottom);
            int enemyPower = PowerPattern.getPowerNum(creatures.get(enemyIndex).powerPattern,creatures.get(enemyIndex).powerTop,creatures.get(enemyIndex).powerBottom);
            boolean result = true;
            if(myPower > enemyPower)
                ;
            else if(myPower == enemyPower)
                result = PowerPattern.getRandomResult();
            else
                result = false;

            //show status
            //System.out.println("I'm " + creatures.get(currentCreatureIndex).name + " at " + creatures.get(currentCreatureIndex).rowPosition + "," + creatures.get(currentCreatureIndex).columnPosition);
            //System.out.println("Enemy is " + creatures.get(enemyIndex).name + " at " + creatures.get(enemyIndex).rowPosition + "," + creatures.get(enemyIndex).columnPosition);

            //kill
            if(result)
            {
                creatures.get(enemyIndex).isAlive = false;
                printText(creatures.get(currentCreatureIndex).name + " attack " + creatures.get(enemyIndex).name + " : " + creatures.get(enemyIndex).name + " died, " + myPower + " vs " + enemyPower + "\n");
                battleField.lands.get(creatures.get(enemyIndex).rowPosition*20 + creatures.get(enemyIndex).columnPosition).isUsed = false;
                clearLand(nextRowPosition,nextColumnPosition);
            }
            else
            {
                creatures.get(currentCreatureIndex).isAlive = false;
                printText(creatures.get(currentCreatureIndex).name + " attack " + creatures.get(enemyIndex).name + " : " + creatures.get(currentCreatureIndex).name + " died, " + myPower + " vs " + enemyPower + "\n");
                battleField.lands.get(creatures.get(currentCreatureIndex).rowPosition*20 + creatures.get(currentCreatureIndex).columnPosition).isUsed = false;
                clearLand(creatures.get(currentCreatureIndex).rowPosition,creatures.get(currentCreatureIndex).columnPosition);
            }

            //record battle
            try
            {
                SAXReader reader = new SAXReader();
                File file = new File("src/sample/info/" + currentRunTime + ".xml");
                //if(!file.exists())
                //    printText("File dosen't exist\n");
                Document document = reader.read(file);
                Element rootElement = document.getRootElement();
                Element round = rootElement.addElement("kill");
                round.addAttribute("creature1",creatures.get(currentCreatureIndex).name);
                round.addAttribute("id1",""+currentCreatureIndex);
                round.addAttribute("rowPosition1",""+creatures.get(currentCreatureIndex).rowPosition);
                round.addAttribute("columnPosition1",""+creatures.get(currentCreatureIndex).columnPosition);
                round.addAttribute("creature2",creatures.get(enemyIndex).name);
                round.addAttribute("id2",""+enemyIndex);
                round.addAttribute("rowPosition2",""+creatures.get(enemyIndex).rowPosition);
                round.addAttribute("columnPosition2",""+creatures.get(enemyIndex).columnPosition);
                if(result)
                    round.addAttribute("killed",creatures.get(enemyIndex).name);
                else
                    round.addAttribute("killed",creatures.get(currentCreatureIndex).name);
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
                writer.write(document);
            }
            catch (DocumentException e)
            {
                printText("Document Exception while recording battle\n");
            }
            catch (FileNotFoundException e)
            {
                printText("File not found while recording battle\n");
            }
            catch (UnsupportedEncodingException e)
            {
                printText("UnsupportedEncoding while recording battle\n");
            }
            catch (IOException e)
            {
                printText("IO Exception while recording battle\n");
            }

            if(!result)
                return -1;
        }

        //record move
        try
        {
            SAXReader reader = new SAXReader();
            File file = new File("src/sample/info/" + currentRunTime + ".xml");
            //if(!file.exists())
            //    printText("File dosen't exist\n");
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement("move");
            round.addAttribute("name",creatures.get(currentCreatureIndex).name);
            round.addAttribute("id",""+currentCreatureIndex);
            round.addAttribute("rowPosition1",""+creatures.get(currentCreatureIndex).rowPosition);
            round.addAttribute("columnPosition1",""+creatures.get(currentCreatureIndex).columnPosition);
            round.addAttribute("rowPosition2",""+nextRowPosition);
            round.addAttribute("columnPosition2",""+nextColumnPosition);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
            writer.write(document);
        }
        catch (DocumentException e)
        {
            printText("Document Exception while recording move\n");
        }
        catch (FileNotFoundException e)
        {
            printText("File not found while recording move\n");
        }
        catch (UnsupportedEncodingException e)
        {
            printText("UnsupportedEncoding while recording move\n");
        }
        catch (IOException e)
        {
            printText("IO Exception while recording move\n");
        }

        battleField.lands.get(creatures.get(currentCreatureIndex).rowPosition*20 + creatures.get(currentCreatureIndex).columnPosition).isUsed = false;
        clearLand(creatures.get(currentCreatureIndex).rowPosition,creatures.get(currentCreatureIndex).columnPosition);
        battleField.lands.get(nextLandIndex).isUsed = true;
        battleField.lands.get(nextLandIndex).creatureIndex = currentCreatureIndex;
        paintCreature(creatures.get(currentCreatureIndex).name,nextRowPosition,nextColumnPosition);
        creatures.get(currentCreatureIndex).rowPosition = nextRowPosition;
        creatures.get(currentCreatureIndex).columnPosition = nextColumnPosition;
        paintCreature(creatures.get(currentCreatureIndex).name,nextRowPosition,nextColumnPosition);

        return 2;
    }
    public static void runCommander()
    {
        //for(int i = 0;i < 16;i++)
        //    threads.get(i).start();
        threads.get(0).start();
        threads.get(8).start();
        threads.get(1).start();
        threads.get(9).start();
        threads.get(2).start();
        threads.get(10).start();
        threads.get(3).start();
        threads.get(11).start();
        threads.get(4).start();
        threads.get(12).start();
        threads.get(5).start();
        threads.get(13).start();
        threads.get(6).start();
        threads.get(14).start();
        threads.get(7).start();
        threads.get(15).start();
    }
}
