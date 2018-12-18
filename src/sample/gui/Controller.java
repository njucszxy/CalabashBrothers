package sample.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
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
    Pane pane;
    @FXML
    Canvas layer1;
    @FXML
    Canvas layer2;
    @FXML
    TextArea textArea;
    @FXML
    Button buttonLoad;
    @FXML
    Button buttonNew;
    @FXML
    ChoiceBox choiceBox;
    @FXML
    RadioButton choiceCB;
    @FXML
    RadioButton choiceMO;

    private Color colorBattleField = Color.web("#FFFFFF",0);
    private GraphicsContext gc1;
    private GraphicsContext gc2;
    private double gapX,gapY;

    private List<String> savedFiles = new ArrayList<String>();
    private int runTimes = 0;
    private Stage primaryStage;

    public void initController(Stage primaryStage)
    {
        layer1.toFront();
        gc1 = layer1.getGraphicsContext2D();
        gc2 = layer2.getGraphicsContext2D();

        choiceBox.setItems(FXCollections.observableArrayList("长蛇","鹤翼","雁行","踟蹰","鱼鳞","方圆","偃月","锋矢"));
        ToggleGroup toggleGroup = new ToggleGroup();
        choiceCB.setToggleGroup(toggleGroup);
        choiceMO.setToggleGroup(toggleGroup);
        choiceCB.setSelected(true);

        /*
        TODO modify gapX and gapY
        */
        gapX = layer1.getWidth() / 20;
        gapY = layer1.getWidth() / 20;

        this.primaryStage = primaryStage;
        paintBackground();
    }
    public void initGame()
    {
        paintBackground();
    }
    private void paintBackground()
    {
        try {
            File file = new File("src/sample/gui/葫芦娃素材/background.jpg");
            Image image = new Image(file.toURI().toURL().toString());
            gc2.drawImage(image, 0, 0, layer2.getWidth(), layer2.getHeight());
        }
        catch (MalformedURLException e)
        {
            textArea.appendText("Error painting background" + "\n");
            e.printStackTrace();
        }
    }
    public int getRunTimes()
    {
        return runTimes;
    }
    public void clearRect(int rowPosition,int columnPosition)
    {
        gc1.clearRect(rowPosition*gapY,columnPosition*gapX,gapY,gapX);
    }
    public void paintImage(String name,int rowPosition,int columnPosition)
    {
        String url = "src/sample/gui/葫芦娃素材/" + name + ".jpg";
        try {
            File file = new File(url);
            Image image = new Image(file.toURI().toURL().toString());
            gc1.drawImage(image, rowPosition*gapY, columnPosition*gapX, gapY, gapX);
        }
        catch (MalformedURLException e)
        {
            textArea.appendText("Error painting " + url + "\n");
            e.printStackTrace();
        }
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
        //init layer1
        for(int i = 0;i < 20;i++)
            for(int j = 0;j < 20;j++)
                clearRect(i,j);
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
