package maybePass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.nio.file.Paths;
import javax.imageio.ImageIO;


public class Shuriken extends Enemigo {
	
	private BufferedImage img1;
	private BufferedImage img2;
    private BufferedImage img3;
    private int contador;
	
    public Shuriken(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        //String path = Paths.get(Shuriken.class.getClassLoader().getResource("imagenes/shuriken.PNG").getPath())
        //        .toString();
        String path = Utilidades.obtenerRuta("imagenes/shuriken.PNG");
        try {
            img1 = ImageIO.read(new File(path));
            img2 = rotarImagen(img1, 45);
            img3 = rotarImagen(img1, 22);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void dibujarse(Graphics graphics) {
        try {
        	 contador++;
             if (contador ==20) {
            	 contador = 0;
             }
             
             if (0 <= contador && contador < 5) {
                 graphics.drawImage(img1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
             }
             if ((5 <= contador  && contador < 10) ||(15 <= contador  && contador <= 20)) {
                 graphics.drawImage(img3, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
             }
            
             if (10 <= contador  && contador < 15) {
                 graphics.drawImage(img2, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
             }
        	//graphics.drawImage(img1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    public void destruirse(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval(getPosicionX(), getPosicionY(), getAncho(), getLargo());
    }

}
