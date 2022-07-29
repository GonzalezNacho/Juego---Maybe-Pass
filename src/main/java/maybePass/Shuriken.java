package maybePass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//import javax.imageio.ImageIO;


public class Shuriken extends Enemigo {
	
	private BufferedImage img1;
	private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private int contador;
	
    public Shuriken(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        img1 = Utilidades.obtenerRuta("/imagenes/shuriken.PNG");
		img2 = rotarImagen(img1, 45);
		img3 = rotarImagen(img1, 22);
		img4 = rotarImagen(img1, 68);
    }
    
    public void dibujarse(Graphics graphics) {
        try {
        	 contador++;
             if (contador ==40) {
            	 contador = 0;
             }
             
             if (0 <= contador && contador < 10) {
                 graphics.drawImage(img1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
             }
             if (10 <= contador  && contador < 20) {
                 graphics.drawImage(img3, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
             }
            
             if (20 <= contador  && contador < 30) {
                 graphics.drawImage(img2, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
             }
             
             if (30 <= contador  && contador <= 40) {
            	 graphics.drawImage(img4, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
             }
        	
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

}
