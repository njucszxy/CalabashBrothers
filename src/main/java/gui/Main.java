package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.media.Manager;
import javax.media.Player;
import java.io.*;
import java.net.URI;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = getClass().getResource("sample.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        final Controller controller = fxmlLoader.getController();
        primaryStage.setTitle("Calabash Brothers vs Monsters");
        Scene scene = new Scene(root, 800, 700);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.S)
                {
                    controller.NewGame();
                }
                else if(event.getCode() == KeyCode.L)
                {
                    controller.LoadGame();
                }
            }
        });

        File file = null;
        String resource = "/葫芦娃素材/bgm2.wav";
        URL res = getClass().getResource(resource);
        if (res.toString().startsWith("jar:")) {
            try {
                InputStream input = getClass().getResourceAsStream(resource);
                file = File.createTempFile("tempfile", ".tmp");
                OutputStream out = new FileOutputStream(file);
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                file.deleteOnExit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //this will probably work in your IDE, but not from a JAR
            file = new File(res.toURI());
        }

        if (file != null && !file.exists()) {
            throw new RuntimeException("Error: File " + file + " not found!");
        }

        final Player player = Manager.createPlayer(file.toURI().toURL());
        player.start();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                player.close();
                System.exit(0);
            }
        });

        primaryStage.setScene(scene);
        controller.initController(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
