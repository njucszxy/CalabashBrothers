package sample.gui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sample.info.Commander;
import sample.info.LoadThread;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    Canvas canvas;
    @FXML
    TextArea textArea;
    @FXML
    Button buttonLoad;
    @FXML
    Button buttonNew;

    private Color colorBattleField = Color.valueOf("#FFFAFA");
    private Color colorLine = Color.valueOf("#1C1C1C");
    private GraphicsContext gc;
    private double gapX,gapY;

    private List<String> savedFiles = new ArrayList<String>();
    private int runTimes = 0;
    private Stage primaryStage;

    public void initController(Stage primaryStage)
    {
        gc = canvas.getGraphicsContext2D();
        /*
        TODO modify gapX and gapY
        */
        gapX = canvas.getWidth() / 20;
        gapY = canvas.getWidth() / 20;

        this.primaryStage = primaryStage;
    }
    public void initGame()
    {
        //init background
        gc.setFill(colorBattleField);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        //init line
        refreshLine();
    }
    private void refreshLine()
    {
        gc.setStroke(colorLine);
        for(int i = 0;i < 20;i++)
        {
            gc.strokeLine(i*gapX,0,i*gapX,canvas.getHeight());
            gc.strokeLine(0,i*gapY,canvas.getWidth(),i*gapY);
        }
    }
    public void reportSavedFile(String filename)
    {
        savedFiles.add(filename);
    }
    public int getRunTimes()
    {
        return runTimes;
    }
    public void clearRect(int rowPosition,int columnPosition)
    {
        gc.setFill(colorBattleField);
        gc.fillRect(rowPosition*gapY,columnPosition*gapX,gapY,gapX);
        refreshLine();
    }
    public void paintImage(String url,int rowPosition,int columnPosition)
    {
        try {
            File file = new File(url);
            Image image = new Image(file.toURI().toURL().toString());
            gc.drawImage(image, rowPosition*gapY, columnPosition*gapX, gapY, gapX);
        }
        catch (MalformedURLException e)
        {
            textArea.appendText("Error painting " + url + "\n");
            e.printStackTrace();
        }
        refreshLine();
    }
    public void printText(String text)
    {
        textArea.appendText(text);
    }

    public void NewGame()
    {
        //init runTimes
        File file = new File("src/sample/info/" + runTimes + ".xml");
        while(file.exists())
        {
            runTimes++;
            file = new File("src/sample/info/" + runTimes + ".xml");
        }
        //init canvas
        initGame();
        textArea.setText("new Game\n");
        Commander.initCommander(this);
        Commander.runCommander();
    }
    public void LoadGame()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Game Record File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/sample/info/"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML","*.xml"));
        File destFile = fileChooser.showOpenDialog(primaryStage);
        if(destFile.exists())
        {
            initGame();
            textArea.setText("new Game\n");

            Thread thread = new Thread(new LoadThread(this,destFile));
            thread.start();
        }
        else
            printText("Error while choosing file\n");
    }
}
