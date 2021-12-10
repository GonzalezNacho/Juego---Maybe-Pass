package maybePass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Moneda extends ElementoBasico {

	private BufferedImage img1;
	
    public Moneda(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        String path ="/C:/Users/jony/Documents/GitHub/JuegoConverso/Juego---Maybe-Pass/src/main/resources/imagenes/moneda.png";
        try {
            img1 = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void dibujarse(Graphics graphics) {
        try {
            graphics.drawImage(img1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    public void destruirse(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval(getPosicionX(), getPosicionY(), getAncho(), getLargo());
    }
    
    public void destroy(){
    	setPosicionY(-1000);
    }
    
}
