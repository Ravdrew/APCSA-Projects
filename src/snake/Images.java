package snake;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
    
    BufferedImage pika; {
        try{
            File file = new File("C:/Users/andre/Documents/GitHub/APCSA-Projects/src/snake/pika.png");
            FileInputStream fis = new FileInputStream(file);
            pika = ImageIO.read(fis);
        } catch(IOException e){
            System.err.println(e);
        }
    }

    /*BufferedImage ball; {
        try{
            File file = new File("C:/Users/andre/Documents/GitHub/APCSA-Projects/src/snake/pokeball.png");
            FileInputStream fis = new FileInputStream(file);
            pika = ImageIO.read(fis);
        } catch(IOException e){
            System.err.println(e);
        }
    }*/
}
