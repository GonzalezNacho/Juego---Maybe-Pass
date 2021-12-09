package maybePass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;


public class Ninja extends ElementoBasico {
	
	private BufferedImage img;

    public Ninja(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        String path = Paths.get(Ninja.class.getClassLoader().getResource("imagenes/ninja.PNG").getPath())
                .toString();
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dibujarse(Graphics graphics) {
        try {
            graphics.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
    
    public void frenarEnEjeXPorDerecha() {
    	setPosicionX(getPosicionX()-1);
    }

    public void frenarEnEjeYPorAbajo() {
    	setPosicionY(getPosicionY()-1);
    }
    
    public void frenarEnEjeXPorIzquierda() {
    	setPosicionX(getPosicionX()+1);
    }

    public void frenarEnEjeYPorArriba() {
    	setPosicionY(getPosicionY()+1);
    }
    
    public void volverALaPosicionInicial(ElementoBasico ubicacion) {
    	setPosicionX(ubicacion.getPosicionX() + 10 );
    	setPosicionY(ubicacion.getPosicionY() + 10 );
    }
}
