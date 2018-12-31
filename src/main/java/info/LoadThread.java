package info;

import battlefield.BattleField;
import gui.FreshThread;
import lives.CB.*;
import lives.Creature;
import lives.MO.Footman;
import lives.MO.Scorpion;
import lives.MO.Snake;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import gui.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoadThread implements Runnable {
    Controller controller;
    File file;
    Database database;
    Thread freshThread;
    long runTime;
    public LoadThread(Controller c, File file)
    {
        this.controller = c;
        this.file = file;
    }
    public void run()
    {
        database = new Database();
        database.gameOn = true;

        //init moveInfos
        database.moveInfos = new ArrayList<>();

        //init battlefield
        database.battleField = new BattleField();

        //init creatures
        database.creatures = new ArrayList<>();

        //load game
        try
        {
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for(int i = 0;i < elements.size();i++)
            {
                    if(elements.get(i).getName() == "init")
                    {
                        List<Element> elementsInit = elements.get(i).elements();
                        for(int j = 0;j < elementsInit.size();j++)
                        {
                            String id = elementsInit.get(j).attributeValue("id");
                            int rowPosition = Integer.parseInt(elementsInit.get(j).attributeValue("rowPosition"));
                            int columnPosition = Integer.parseInt(elementsInit.get(j).attributeValue("columnPosition"));
                            switch (id)
                            {
                                case "0":database.creatures.add(new GrandPa(rowPosition,columnPosition));break;
                                case "1":database.creatures.add(new RedBoy(rowPosition,columnPosition));break;
                                case "2":database.creatures.add(new OrangeBoy(rowPosition,columnPosition));break;
                                case "3":database.creatures.add(new YellowBoy(rowPosition,columnPosition));break;
                                case "4":database.creatures.add(new GreenBoy(rowPosition,columnPosition));break;
                                case "5":database.creatures.add(new CyanBoy(rowPosition,columnPosition));break;
                                case "6":database.creatures.add(new BlueBoy(rowPosition,columnPosition));break;
                                case "7":database.creatures.add(new PurpleBoy(rowPosition,columnPosition));break;
                                case "8":database.creatures.add(new Snake(rowPosition,columnPosition));break;
                                case "9":database.creatures.add(new Scorpion(rowPosition,columnPosition));break;
                                case "10":database.creatures.add(new Footman(rowPosition,columnPosition));break;
                                case "11":database.creatures.add(new Footman(rowPosition,columnPosition));break;
                                case "12":database.creatures.add(new Footman(rowPosition,columnPosition));break;
                                case "13":database.creatures.add(new Footman(rowPosition,columnPosition));break;
                                case "14":database.creatures.add(new Footman(rowPosition,columnPosition));break;
                                case "15":database.creatures.add(new Footman(rowPosition,columnPosition));break;
                                default:return;
                            }
                            database.battleField.lands.get(rowPosition*20 + columnPosition).isUsed = true;
                            database.battleField.lands.get(rowPosition*20 + columnPosition).creatureIndex = Integer.parseInt(id);
                        }
                        //init time
                        runTime = System.currentTimeMillis();

                        //init fresh thread
                        freshThread = new Thread(new FreshThread(database, controller));
                        freshThread.start();
                    }
                    else if(elements.get(i).getName() == "move")
                    {
                        int id = Integer.parseInt(elements.get(i).attributeValue("id"));
                        int oldRowPosition = Integer.parseInt(elements.get(i).attributeValue("rowPosition1"));
                        int oldColumnPosition = Integer.parseInt(elements.get(i).attributeValue("columnPosition1"));
                        int newRowPosition = Integer.parseInt(elements.get(i).attributeValue("rowPosition2"));
                        int newColumnPosition = Integer.parseInt(elements.get(i).attributeValue("columnPosition2"));
                        long moveTime = Integer.parseInt(elements.get(i).attributeValue("time"));
                        long deltaTime = System.currentTimeMillis() - runTime;
                        try {
                            if (deltaTime < moveTime) {
                                Thread.sleep(moveTime - deltaTime);
                            }
                            else
                            {
                                //校正误差
                                runTime += (deltaTime - moveTime);
                            }
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        database.battleField.lands.get(oldRowPosition*20 + oldColumnPosition).isUsed = false;
                        database.creatures.get(id).rowPosition = newRowPosition;
                        database.creatures.get(id).columnPosition = newColumnPosition;
                        database.battleField.lands.get(newRowPosition*20 + newColumnPosition).creatureIndex = id;
                        database.battleField.lands.get(newRowPosition*20 + newColumnPosition).isUsed = true;
                    }
                    else if(elements.get(i).getName() == "shoot")
                    {
                        int id = Integer.parseInt(elements.get(i).attributeValue("id"));
                        int damage = Integer.parseInt(elements.get(i).attributeValue("damage"));
                        int myRowPosition = Integer.parseInt(elements.get(i).attributeValue("myRowPosition"));
                        int myColumnPosition = Integer.parseInt(elements.get(i).attributeValue("myColumnPosition"));
                        int enemyRowPosition = Integer.parseInt(elements.get(i).attributeValue("enemyRowPosition"));
                        int enemyColumnPosition = Integer.parseInt(elements.get(i).attributeValue("enemyColumnPosition"));
                        long shootTime = Integer.parseInt(elements.get(i).attributeValue("time"));
                        long deltaTime = System.currentTimeMillis() - runTime;
                        try {
                            if (deltaTime < shootTime) {
                                Thread.sleep(shootTime - deltaTime);
                            }
                            else
                            {
                                //校正误差
                                runTime += (deltaTime - shootTime);
                            }
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Camp myCamp;
                        if(id <= 7)
                            myCamp = Camp.CB;
                        else
                            myCamp = Camp.MO;
                        Bullet bullet = new Bullet(damage, myRowPosition, myColumnPosition, myRowPosition - enemyRowPosition, myColumnPosition - enemyColumnPosition, myCamp);
                        database.bullets.add(bullet);
                    }
                    else if(elements.get(i).getName() == "end")
                    {
                        long endTime = Integer.parseInt(elements.get(i).attributeValue("time"));
                        long deltaTime = System.currentTimeMillis() - runTime;
                        try {
                            if (deltaTime < endTime) {
                                Thread.sleep(endTime - deltaTime);
                            }
                            else
                            {
                                //校正误差
                                runTime += (deltaTime - endTime);
                            }
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        controller.printText(elements.get(i).attributeValue("winner") +  " have won!\n");
                    }
            }
        }
        catch (DocumentException e)
        {
            controller.printText("Document Exception while loading game\n");
        }

        database.gameOn = false;
    }
}
