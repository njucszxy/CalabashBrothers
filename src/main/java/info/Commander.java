package info;

import com.sun.javafx.runtime.SystemProperties;
import gui.FreshThread;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import battlefield.BattleField;
import gui.Controller;
import lives.Creature;
import lives.CB.*;
import lives.MO.*;

import javax.print.Doc;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Commander {
    public static List<Thread> threads;
    public static Controller controller;
    public static Database database;
    public static Thread freshThread;
    public static long startTime;



    private static int currentRunTime = -1;

    public static void initCommander(Controller c,FormationType formationTypeCB,FormationType formationTypeMO)
    {

        //init startTime
        startTime = -1;

        //init Database
        database = new Database();
        database.gameOn = true;

        //init moveInfos
        database.moveInfos = new ArrayList<>();

        //init Controller
        controller = c;

        //init runTimeCount
        currentRunTime = controller.getRunTimes();

        //init battlefield
        database.battleField = new BattleField();

        List<PositionInfo> positionInfos = new ArrayList<PositionInfo>();
        for(int i = 0;i < 16;i++)
            positionInfos.add(new PositionInfo(-1,-1));

        switch (formationTypeCB)
        {
            case Zig:Formation.getZigFormation(positionInfos,Camp.CB);break;
            case Fish:Formation.getFishFormation(positionInfos,Camp.CB);break;
            case Moon:Formation.getMoonFormation(positionInfos,Camp.CB);break;
            case Wing:Formation.getWingFormation(positionInfos,Camp.CB);break;
            case Arrow:Formation.getArrowFormation(positionInfos,Camp.CB);break;
            case Goose:Formation.getGooseFormation(positionInfos,Camp.CB);break;
            case Snake:Formation.getSnakeFormation(positionInfos,Camp.CB);break;
            case Square:Formation.getSquareFormation(positionInfos,Camp.CB);break;
            default:Formation.getRandomFormation(positionInfos,Camp.CB);break;
        }
        switch (formationTypeMO)
        {
            case Zig:Formation.getZigFormation(positionInfos,Camp.MO);break;
            case Fish:Formation.getFishFormation(positionInfos,Camp.MO);break;
            case Moon:Formation.getMoonFormation(positionInfos,Camp.MO);break;
            case Wing:Formation.getWingFormation(positionInfos,Camp.MO);break;
            case Arrow:Formation.getArrowFormation(positionInfos,Camp.MO);break;
            case Goose:Formation.getGooseFormation(positionInfos,Camp.MO);break;
            case Snake:Formation.getSnakeFormation(positionInfos,Camp.MO);break;
            case Square:Formation.getSquareFormation(positionInfos,Camp.MO);break;
            default:Formation.getRandomFormation(positionInfos,Camp.MO);break;
        }

        //init creatures
        database.creatures = new ArrayList<Creature>();
        database.creatures.add(new GrandPa(positionInfos.get(0).RowPosition,positionInfos.get(0).columnPosition));
        database.creatures.add(new RedBoy(positionInfos.get(1).RowPosition,positionInfos.get(1).columnPosition));
        database.creatures.add(new OrangeBoy(positionInfos.get(2).RowPosition,positionInfos.get(2).columnPosition));
        database.creatures.add(new YellowBoy(positionInfos.get(3).RowPosition,positionInfos.get(3).columnPosition));
        database.creatures.add(new GreenBoy(positionInfos.get(4).RowPosition,positionInfos.get(4).columnPosition));
        database.creatures.add(new CyanBoy(positionInfos.get(5).RowPosition,positionInfos.get(5).columnPosition));
        database.creatures.add(new BlueBoy(positionInfos.get(6).RowPosition,positionInfos.get(6).columnPosition));
        database.creatures.add(new PurpleBoy(positionInfos.get(7).RowPosition,positionInfos.get(7).columnPosition));
        database.creatures.add(new Snake(positionInfos.get(8).RowPosition,positionInfos.get(8).columnPosition));
        database.creatures.add(new Scorpion(positionInfos.get(9).RowPosition,positionInfos.get(9).columnPosition));
        database.creatures.add(new Footman(positionInfos.get(10).RowPosition,positionInfos.get(10).columnPosition));
        database.creatures.add(new Footman(positionInfos.get(11).RowPosition,positionInfos.get(11).columnPosition));
        database.creatures.add(new Footman(positionInfos.get(12).RowPosition,positionInfos.get(12).columnPosition));
        database.creatures.add(new Footman(positionInfos.get(13).RowPosition,positionInfos.get(13).columnPosition));
        database.creatures.add(new Footman(positionInfos.get(14).RowPosition,positionInfos.get(14).columnPosition));
        database.creatures.add(new Footman(positionInfos.get(15).RowPosition,positionInfos.get(15 ).columnPosition));

        database.battleField.lands.get(positionInfos.get(0).RowPosition*20 + positionInfos.get(0).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(0).RowPosition*20 + positionInfos.get(0).columnPosition).creatureIndex = 0;
        database.battleField.lands.get(positionInfos.get(1).RowPosition*20 + positionInfos.get(1).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(1).RowPosition*20 + positionInfos.get(1).columnPosition).creatureIndex = 1;
        database.battleField.lands.get(positionInfos.get(2).RowPosition*20 + positionInfos.get(2).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(2).RowPosition*20 + positionInfos.get(2).columnPosition).creatureIndex = 2;
        database.battleField.lands.get(positionInfos.get(3).RowPosition*20 + positionInfos.get(3).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(3).RowPosition*20 + positionInfos.get(3).columnPosition).creatureIndex = 3;
        database.battleField.lands.get(positionInfos.get(4).RowPosition*20 + positionInfos.get(4).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(4).RowPosition*20 + positionInfos.get(4).columnPosition).creatureIndex = 4;
        database.battleField.lands.get(positionInfos.get(5).RowPosition*20 + positionInfos.get(5).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(5).RowPosition*20 + positionInfos.get(5).columnPosition).creatureIndex = 5;
        database.battleField.lands.get(positionInfos.get(6).RowPosition*20 + positionInfos.get(6).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(6).RowPosition*20 + positionInfos.get(6).columnPosition).creatureIndex = 6;
        database.battleField.lands.get(positionInfos.get(7).RowPosition*20 + positionInfos.get(7).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(7).RowPosition*20 + positionInfos.get(7).columnPosition).creatureIndex = 7;
        database.battleField.lands.get(positionInfos.get(8).RowPosition*20 + positionInfos.get(8).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(8).RowPosition*20 + positionInfos.get(8).columnPosition).creatureIndex = 8;
        database.battleField.lands.get(positionInfos.get(9).RowPosition*20 + positionInfos.get(9).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(9).RowPosition*20 + positionInfos.get(9).columnPosition).creatureIndex = 9;
        database.battleField.lands.get(positionInfos.get(10).RowPosition*20 + positionInfos.get(10).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(10).RowPosition*20 + positionInfos.get(10).columnPosition).creatureIndex = 10;
        database.battleField.lands.get(positionInfos.get(11).RowPosition*20 + positionInfos.get(11).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(11).RowPosition*20 + positionInfos.get(11).columnPosition).creatureIndex = 11;
        database.battleField.lands.get(positionInfos.get(12).RowPosition*20 + positionInfos.get(12).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(12).RowPosition*20 + positionInfos.get(12).columnPosition).creatureIndex = 12;
        database.battleField.lands.get(positionInfos.get(13).RowPosition*20 + positionInfos.get(13).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(13).RowPosition*20 + positionInfos.get(13).columnPosition).creatureIndex = 13;
        database.battleField.lands.get(positionInfos.get(14).RowPosition*20 + positionInfos.get(14).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(14).RowPosition*20 + positionInfos.get(14).columnPosition).creatureIndex = 14;
        database.battleField.lands.get(positionInfos.get(15).RowPosition*20 + positionInfos.get(15).columnPosition).isUsed = true;
        database.battleField.lands.get(positionInfos.get(15).RowPosition*20 + positionInfos.get(15).columnPosition).creatureIndex = 15;

        //record position
        try{
            Document document = DocumentHelper.createDocument();
            Element rootElement = document.addElement("root");
            Element initElement = rootElement.addElement("init");
            for(int i = 0;i < 16;i++)
            {
                Element tempElement = initElement.addElement("creature" );
                tempElement.addAttribute("id",""+i);
                tempElement.addAttribute("name",database.creatures.get(i).name);
                tempElement.addAttribute("rowPosition","" + database.creatures.get(i).rowPosition);
                tempElement.addAttribute("columnPosition","" + database.creatures.get(i).columnPosition);
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            String currentDir = System.getProperty("user.dir");

            File file = new File(currentDir + "/" + currentRunTime + ".xml");

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
            threads.add(new Thread(database.creatures.get(i)));
        freshThread = new Thread(new FreshThread(database,controller));
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
    public static synchronized int oneStep(Creature creature)
    {
        if(startTime == -1)
            startTime = System.currentTimeMillis();

            int currentCreatureIndex = 0;
            for (int i = 0; i < 16; i++) {
                if (database.creatures.get(i) == creature) {
                    currentCreatureIndex = i;
                    break;
                }
            }

            //is alive ?
            if (!database.creatures.get(currentCreatureIndex).isAlive) {
                return -1;
            }

            //if succeed
            boolean success = true;
            for (int i = 0; i < 16; i++) {
                if ((database.creatures.get(i).camp != database.creatures.get(currentCreatureIndex).camp) && database.creatures.get(i).isAlive) {
                    success = false;
                    break;
                }
            }
            if (success) {
                database.gameOn = false;

                //record winner
                try {
                    SAXReader reader = new SAXReader();
                    String currentDir = System.getProperty("user.dir");
                    File file = new File(currentDir + "/" + currentRunTime + ".xml");
                    Document document = reader.read(file);
                    Element rootElement = document.getRootElement();
                    Element round = rootElement.addElement("end");
                    round.addAttribute("winner", database.creatures.get(currentCreatureIndex).name);
                    round.addAttribute("id", "" + currentCreatureIndex);
                    round.addAttribute("time","" + (System.currentTimeMillis() - startTime));
                    OutputFormat format = OutputFormat.createPrettyPrint();
                    format.setEncoding("UTF-8");
                    XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
                    writer.write(document);
                } catch (DocumentException e) {
                    printText("Document Exception while recording winner\n");
                } catch (FileNotFoundException e) {
                    printText("File not found while recording winner\n");
                } catch (UnsupportedEncodingException e) {
                    printText("UnsupportedEncoding while recording winner\n");
                } catch (IOException e) {
                    printText("IO Exception while recording winner\n");
                }

                printText("I'm " + database.creatures.get(currentCreatureIndex).name + " ,WE HAVE WON !\n");

                return 0;
            }

            //find next enemy
            int minDistance = 40;
            int enemyRowPosition = 20;
            int enemyColumnPosition = 20;
            for (int i = 0; i < 16; i++) {
                if ((database.creatures.get(i).camp != database.creatures.get(currentCreatureIndex).camp) && database.creatures.get(i).isAlive) {
                    int temp = Math.abs(database.creatures.get(currentCreatureIndex).rowPosition - database.creatures.get(i).rowPosition) + Math.abs(database.creatures.get(currentCreatureIndex).columnPosition - database.creatures.get(i).columnPosition);
                    if (temp < minDistance) {
                        minDistance = temp;
                        enemyRowPosition = database.creatures.get(i).rowPosition;
                        enemyColumnPosition = database.creatures.get(i).columnPosition;
                    }
                }
            }

            //find next position
            Random RandomNumber = new Random();
            int ran = Math.abs(RandomNumber.nextInt()) % 4 + 1;
            int nextRowPosition = database.creatures.get(currentCreatureIndex).rowPosition;
            int nextColumnPosition = database.creatures.get(currentCreatureIndex).columnPosition;
            switch (ran) {
                case 1:
                    nextRowPosition++;
                    break;
                case 2:
                    nextColumnPosition++;
                    break;
                case 3:
                    nextRowPosition--;
                    break;
                case 4:
                    nextColumnPosition--;
                    break;
                default:
                    break;
            }
            boolean moveResult = true;
            if (nextRowPosition == database.creatures.get(currentCreatureIndex).rowPosition && nextColumnPosition == database.creatures.get(currentCreatureIndex).columnPosition)
                moveResult = false;
            else if (nextRowPosition >= 20 || nextRowPosition < 0 || nextColumnPosition >= 20 || nextColumnPosition < 0)
                moveResult = false;
            else if (database.battleField.lands.get(nextRowPosition * 20 + nextColumnPosition).isUsed)
                moveResult = false;
            if (moveResult) {
                //record move
                try {
                    SAXReader reader = new SAXReader();
                    String currentDir = System.getProperty("user.dir");
                    File file = new File(currentDir + "/" + currentRunTime + ".xml");
                    Document document = reader.read(file);
                    Element rootElement = document.getRootElement();
                    Element round = rootElement.addElement("move");
                    round.addAttribute("name", database.creatures.get(currentCreatureIndex).name);
                    round.addAttribute("id", "" + currentCreatureIndex);
                    round.addAttribute("time","" + (System.currentTimeMillis() - startTime));
                    round.addAttribute("rowPosition1", "" + database.creatures.get(currentCreatureIndex).rowPosition);
                    round.addAttribute("columnPosition1", "" + database.creatures.get(currentCreatureIndex).columnPosition);
                    round.addAttribute("rowPosition2", "" + nextRowPosition);
                    round.addAttribute("columnPosition2", "" + nextColumnPosition);
                    OutputFormat format = OutputFormat.createPrettyPrint();
                    format.setEncoding("UTF-8");
                    XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
                    writer.write(document);
                } catch (DocumentException e) {
                    printText("Document Exception while recording move\n");
                } catch (FileNotFoundException e) {
                    printText("File not found while recording move\n");
                } catch (UnsupportedEncodingException e) {
                    printText("UnsupportedEncoding while recording move\n");
                } catch (IOException e) {
                    printText("IO Exception while recording move\n");
                }


                database.battleField.lands.get(database.creatures.get(currentCreatureIndex).rowPosition * 20 + database.creatures.get(currentCreatureIndex).columnPosition).isUsed = false;
                database.creatures.get(currentCreatureIndex).rowPosition = nextRowPosition;
                database.creatures.get(currentCreatureIndex).columnPosition = nextColumnPosition;
                database.battleField.lands.get(database.creatures.get(currentCreatureIndex).rowPosition * 20 + database.creatures.get(currentCreatureIndex).columnPosition).isUsed = true;
            }


        //shoot
        int myPower = PowerPattern.getPowerNum(database.creatures.get(currentCreatureIndex).powerPattern, database.creatures.get(currentCreatureIndex).powerTop, database.creatures.get(currentCreatureIndex).powerBottom);
        Bullet bullet = new Bullet(myPower, database.creatures.get(currentCreatureIndex).rowPosition, database.creatures.get(currentCreatureIndex).columnPosition, database.creatures.get(currentCreatureIndex).rowPosition - enemyRowPosition, database.creatures.get(currentCreatureIndex).columnPosition - enemyColumnPosition, database.creatures.get(currentCreatureIndex).camp);
        database.bullets.add(bullet);

        //record shoot
        try {
            SAXReader reader = new SAXReader();
            String currentDir = System.getProperty("user.dir");
            File file = new File(currentDir + "/" + currentRunTime + ".xml");
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element round = rootElement.addElement("shoot");
            round.addAttribute("name", database.creatures.get(currentCreatureIndex).name);
            round.addAttribute("id", "" + currentCreatureIndex);
            round.addAttribute("time","" + (System.currentTimeMillis() - startTime));
            round.addAttribute("damage","" + myPower);
            round.addAttribute("myRowPosition", "" + database.creatures.get(currentCreatureIndex).rowPosition);
            round.addAttribute("myColumnPosition", "" + database.creatures.get(currentCreatureIndex).columnPosition);
            round.addAttribute("enemyRowPosition", "" + enemyRowPosition);
            round.addAttribute("enemyColumnPosition", "" + enemyColumnPosition);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(document);
        } catch (DocumentException e) {
            printText("Document Exception while recording move\n");
        } catch (FileNotFoundException e) {
            printText("File not found while recording move\n");
        } catch (UnsupportedEncodingException e) {
            printText("UnsupportedEncoding while recording move\n");
        } catch (IOException e) {
            printText("IO Exception while recording move\n");
        }


            return 2;
    }
    public static void runCommander()
    {
        freshThread.start();

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
