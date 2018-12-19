package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import info.*;

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
    ChoiceBox choiceBoxCB;
    @FXML
    ChoiceBox choiceBoxMO;

    private Color colorBattleField = Color.web("#FFFFFF",0);
    private GraphicsContext gc1;
    private GraphicsContext gc2;
    private double gapX,gapY;

    private List<String> savedFiles = new ArrayList<String>();
    private int runTimes = 0;
    private Stage primaryStage;

    private FormationType formationTypeCB = FormationType.Snake;
    private FormationType formationTypeMO = FormationType.Snake;

    public void initController(Stage primaryStage)
    {
        layer1.toFront();
        gc1 = layer1.getGraphicsContext2D();
        gc2 = layer2.getGraphicsContext2D();

        textArea.setDisable(true);

        choiceBoxCB.setItems(FXCollections.observableArrayList("长蛇","鹤翼","雁行","踟蹰","鱼鳞","方圆","偃月","锋矢","随机"));
        choiceBoxCB.getSelectionModel().selectFirst();
        choiceBoxCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                switch (newValue.intValue())
                {
                    case 0:formationTypeCB = FormationType.Snake;break;
                    case 1:formationTypeCB = FormationType.Wing;break;
                    case 2:formationTypeCB = FormationType.Goose;break;
                    case 3:formationTypeCB = FormationType.Zig;break;
                    case 4:formationTypeCB = FormationType.Fish;break;
                    case 5:formationTypeCB = FormationType.Square;break;
                    case 6:formationTypeCB = FormationType.Moon;break;
                    case 7:formationTypeCB = FormationType.Arrow;break;
                    case 8:formationTypeCB = Formation.GetRandomFormationType();break;
                    default:formationTypeCB = FormationType.Snake;break;
                }
                paintFormation();
            }
        });
        choiceBoxMO.setItems(FXCollections.observableArrayList("长蛇","鹤翼","雁行","踟蹰","鱼鳞","方圆","偃月","锋矢","随机"));
        choiceBoxMO.getSelectionModel().selectFirst();
        choiceBoxMO.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                switch (newValue.intValue())
                {
                    case 0:formationTypeMO = FormationType.Snake;break;
                    case 1:formationTypeMO = FormationType.Wing;break;
                    case 2:formationTypeMO = FormationType.Goose;break;
                    case 3:formationTypeMO = FormationType.Zig;break;
                    case 4:formationTypeMO = FormationType.Fish;break;
                    case 5:formationTypeMO = FormationType.Square;break;
                    case 6:formationTypeMO = FormationType.Moon;break;
                    case 7:formationTypeMO = FormationType.Arrow;break;
                    case 8:formationTypeMO = Formation.GetRandomFormationType();break;
                    default:formationTypeMO = FormationType.Snake;break;
                }
                paintFormation();
            }
        });

        gapX = layer1.getWidth() / 20;
        gapY = layer1.getWidth() / 20;

        this.primaryStage = primaryStage;
        paintBackground();
        paintFormation();
    }
    public void initGame()
    {
        //init layer2
        for(int i = 0;i < 20;i++)
            for(int j = 0;j < 20;j++)
                gc2.clearRect(i*gapY,j*gapX,gapY,gapX);
        paintBackground();
    }
    private void paintFormation()
    {
        initGame();
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

        //init layer1
        for(int i = 0;i < 20;i++)
            for(int j = 0;j < 20;j++)
                clearRect(i,j);
        //repaint
        for(int i = 0;i < 16;i++)
        {
            String name = new String();
            switch (i)
            {
                case 0:name = "爷爷";break;
                case 1:name = "大娃";break;
                case 2:name = "二娃";break;
                case 3:name = "三娃";break;
                case 4:name = "四娃";break;
                case 5:name = "五娃";break;
                case 6:name = "六娃";break;
                case 7:name = "七娃";break;
                case 8:name = "蛇精";break;
                case 9:name = "蝎子精";break;
                case 10:name = "小喽啰";break;
                case 11:name = "小喽啰";break;
                case 12:name = "小喽啰";break;
                case 13:name = "小喽啰";break;
                case 14:name = "小喽啰";break;
                case 15:name = "小喽啰";break;
                default:name = "小喽啰";break;
            }
            paintImage(name,positionInfos.get(i).RowPosition,positionInfos.get(i).columnPosition);
        }
    }
    private void paintBackground()
    {
        try {
            File file = new File("src/main/java/gui/葫芦娃素材/background.jpg");
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
        String url = "src/main/java/gui/葫芦娃素材/" + name + ".jpg";
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
    public void paintRemains(int rowPosition,int columnPosition)
    {
        String url = "src/main/java/gui/葫芦娃素材/尸体.jpg";
        try {
            File file = new File(url);
            Image image = new Image(file.toURI().toURL().toString());
            gc2.drawImage(image, rowPosition*gapY, columnPosition*gapX, gapY, gapX);
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
        File file = new File("src/main/java/info/" + runTimes + ".xml");
        while(file.exists())
        {
            runTimes++;
            file = new File("src/main/java/info/" + runTimes + ".xml");
        }
        //init canvas
        initGame();
        textArea.setText("new Game\n");
        //init layer1
        for(int i = 0;i < 20;i++)
            for(int j = 0;j < 20;j++)
                clearRect(i,j);
        Commander.initCommander(this,formationTypeCB,formationTypeMO);
        Commander.runCommander();
    }
    public void LoadGame()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Game Record File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/main/java/info/"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML","*.xml"));
        File destFile = fileChooser.showOpenDialog(primaryStage);
        if(destFile.exists())
        {
            initGame();
            textArea.setText("new Game\n");
            //init layer1
            for(int i = 0;i < 20;i++)
                for(int j = 0;j < 20;j++)
                    clearRect(i,j);

            Thread thread = new Thread(new LoadThread(this,destFile));
            thread.start();
        }
        else
            printText("Error while choosing file\n");
    }
}
