package maybePass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.nio.file.Paths;
import javax.imageio.ImageIO;


public class Ninja extends ElementoConImagen {
	
	private BufferedImage img;
	private BufferedImage imgWalk1;
	private BufferedImage imgWalk2;
	private BufferedImage imgWalk3;
	private int contador;

    public Ninja(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        //String path = Paths.get(Ninja.class.getClassLoader().getResource("imagenes/ninja.PNG").getPath())
        //        .toString();
        String path = Utilidades.obtenerRuta("imagenes/ninja.PNG");
        String pathWalk1 = Utilidades.obtenerRuta("imagenes/walk1.PNG");
        String pathWalk2 = Utilidades.obtenerRuta("imagenes/walk2.PNG");
        String pathWalk3 = Utilidades.obtenerRuta("imagenes/walk3.PNG");
        try {
        	img = ImageIO.read(new File(path));
        	imgWalk1 = ImageIO.read(new File(pathWalk1));
        	imgWalk2 = ImageIO.read(new File(pathWalk2));
        	imgWalk3 = ImageIO.read(new File(pathWalk3));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dibujarse(Graphics graphics) {
        try {
            if (getVelocidadX() == 0 && getVelocidadY() == 0 ) {
            	graphics.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
            } else {
            	contador++;
                if (contador == 30) {
               	 contador = 0;
                }
                
                if (0 <= contador && contador < 10) {
                    graphics.drawImage(imgWalk1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                }
                if (10 <= contador  && contador < 20) {
                    graphics.drawImage(imgWalk2, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                }
               
                if (20 <= contador  && contador < 30) {
                    graphics.drawImage(imgWalk3, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                }
            }
        	
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

    public void frenarEnEjeYPoArriba() {
    	setPosicionY(getPosicionY()+1);
    }
    
    public void volverALaPosicionInicial(ElementoBasico ubicacion) {
    	setPosicionX(ubicacion.getPosicionX() + 10 );
    	setPosicionY(ubicacion.getPosicionY() + 10 );
    }
}
