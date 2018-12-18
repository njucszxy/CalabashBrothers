package sample.info;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sample.gui.Controller;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoadThread implements Runnable {
    Controller controller;
    File file;
    public LoadThread(Controller c, File file)
    {
        this.controller = c;
        this.file = file;
    }
    public void run()
    {
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
                            String url = elementsInit.get(j).attributeValue("name");
                            controller.paintImage(url,Integer.parseInt(elementsInit.get(j).attributeValue("rowPosition")),Integer.parseInt(elementsInit.get(j).attributeValue("columnPosition")));
                        }
                        Thread.sleep(50);
                    }
                    else if(elements.get(i).getName() == "move")
                    {
                        controller.clearRect(Integer.parseInt(elements.get(i).attributeValue("rowPosition1")),Integer.parseInt(elements.get(i).attributeValue("columnPosition1")));
                        String url = elements.get(i).attributeValue("name");
                        controller.paintImage(url,Integer.parseInt(elements.get(i).attributeValue("rowPosition2")),Integer.parseInt(elements.get(i).attributeValue("columnPosition2")));
                        Thread.sleep(50);
                    }
                    else if(elements.get(i).getName() == "kill")
                    {
                        String name1 = elements.get(i).attributeValue("creature1");
                        String name2 = elements.get(i).attributeValue("creature2");
                        String killedName = elements.get(i).attributeValue("killed");
                        if(name1.equals(killedName))
                        {
                            controller.clearRect(Integer.parseInt(elements.get(i).attributeValue("rowPosition1")),Integer.parseInt(elements.get(i).attributeValue("columnPosition1")));
                            controller.paintRemains(Integer.parseInt(elements.get(i).attributeValue("rowPosition1")),Integer.parseInt(elements.get(i).attributeValue("columnPosition1")));
                            controller.printText(name2 + " killed " + name1 + "\n");
                        }
                        else
                        {
                            controller.clearRect(Integer.parseInt(elements.get(i).attributeValue("rowPosition2")),Integer.parseInt(elements.get(i).attributeValue("columnPosition2")));
                            controller.paintRemains(Integer.parseInt(elements.get(i).attributeValue("rowPosition2")),Integer.parseInt(elements.get(i).attributeValue("columnPosition2")));
                            String url = name1;
                            controller.paintImage(url,Integer.parseInt(elements.get(i).attributeValue("rowPosition2")),Integer.parseInt(elements.get(i).attributeValue("columnPosition2")));
                            controller.clearRect(Integer.parseInt(elements.get(i).attributeValue("rowPosition1")),Integer.parseInt(elements.get(i).attributeValue("columnPosition1")));
                            controller.printText(name1 + " killed " + name2 + "\n");
                        }
                        Thread.sleep(50);
                    }
                    else if(elements.get(i).getName() == "end")
                    {
                        controller.printText(elements.get(i).attributeValue("winner") +  " have won!\n");
                    }

            }
        }
        catch (DocumentException e)
        {
            controller.printText("Document Exception while loading game\n");
        }
        catch (InterruptedException e)
        {
            controller.printText("Interrupted Exception while loading game\n");
        }
    }
}
