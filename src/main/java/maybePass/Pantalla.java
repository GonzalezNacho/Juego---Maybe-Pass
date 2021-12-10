package maybePass;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Pantalla implements Dibujable {

    protected BufferedImage img;
    protected int ancho;
    protected int largo;

    public Pantalla(int ancho, int largo, String resource) {
        try {
            this.ancho = ancho;
            this.largo = largo;
            String path ="/C:/Users/jony/Documents/GitHub/JuegoConverso/Juego---Maybe-Pass/src/main/resources/imagenes/portada.PNG";
            this.img = ImageIO.read(new File(path));
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    public void dibujarse(Graphics graphics) {
        try {
            graphics.drawImage(img, 0, 0, ancho, largo, null);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

}
