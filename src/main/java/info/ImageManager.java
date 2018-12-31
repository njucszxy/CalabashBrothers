package info;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageManager {
    List<Image> imageList = new ArrayList<>();
    public ImageManager()
    {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/resource/爷爷.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/大娃.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/二娃.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/三娃.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/四娃.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/五娃.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/六娃.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/七娃.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/蛇精.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/蝎子精.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/小喽啰.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
            image = ImageIO.read(getClass().getResourceAsStream("/resource/尸体.jpg"));
            imageList.add(SwingFXUtils.toFXImage(image, null));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public Image getImage(String name)
    {
        switch (name)
        {
            case "爷爷":return imageList.get(0);
            case "大娃":return imageList.get(1);
            case "二娃":return imageList.get(2);
            case "三娃":return imageList.get(3);
            case "四娃":return imageList.get(4);
            case "五娃":return imageList.get(5);
            case "六娃":return imageList.get(6);
            case "七娃":return imageList.get(7);
            case "蛇精":return imageList.get(8);
            case "蝎子精":return imageList.get(9);
            case "小喽啰":return imageList.get(10);
            case "尸体":return imageList.get(11);
            default:return imageList.get(0);
        }
    }
}
