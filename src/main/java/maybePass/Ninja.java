package maybePass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Ninja extends ElementoConImagen {
	
	private BufferedImage img;
	private BufferedImage imgWalk1;
	private BufferedImage imgWalk2;
	private BufferedImage imgWalk3;
	private BufferedImage imgWalk1izq;
	private BufferedImage imgWalk2izq;
	private BufferedImage imgWalk3izq;
	private int contador;

    public Ninja(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        img = Utilidades.obtenerRuta("/imagenes/ninja.PNG");
        imgWalk1 = Utilidades.obtenerRuta("/imagenes/walk1.PNG");
        imgWalk2 = Utilidades.obtenerRuta("/imagenes/walk2.PNG");
        imgWalk3 = Utilidades.obtenerRuta("/imagenes/walk3.PNG");
        imgWalk1izq = Utilidades.obtenerRuta("/imagenes/walk1izq.PNG");
        imgWalk2izq = Utilidades.obtenerRuta("/imagenes/walk2izq.PNG");
        imgWalk3izq = Utilidades.obtenerRuta("/imagenes/walk3izq.PNG");
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
                    if (getVelocidadX() == -1) {
                    	graphics.drawImage(imgWalk1izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                    } else {
                    	graphics.drawImage(imgWalk1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                    }	
                }
                if (10 <= contador  && contador < 20) {
                	if (getVelocidadX() == -1) {
                		graphics.drawImage(imgWalk2izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	} else {
                		graphics.drawImage(imgWalk2, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	}
                }
               
                if (20 <= contador  && contador < 30) {
                	if (getVelocidadX() == -1) {
                		graphics.drawImage(imgWalk3izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	} else {
                		graphics.drawImage(imgWalk3, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	}
                }
            }
        	
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
    
    public void frenarEnEjeXPorDerecha() {
    	setPosicionX(getPosicionX()-1);
    }

    public void frenarEnEjeYPorAbajo(ElementoInanimado pared) {
    	setPosicionY(pared.getPosicionY()- getLargo()-1);
    }
    
    public void frenarEnEjeXPorIzquierda() {
    	setPosicionX(getPosicionX()+1);
    }

    public void frenarEnEjeYPorArriba(ElementoInanimado pared) {
    	setPosicionY(pared.getPosicionY()+pared.getLargo()+1);
    }
    
    public void volverALaPosicionInicial(ElementoBasico ubicacion) {
    	setPosicionX(ubicacion.getPosicionX() + 10 );
    	setPosicionY(ubicacion.getPosicionY() + 10 );
    }
}
