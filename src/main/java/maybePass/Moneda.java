package maybePass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Moneda extends ElementoConImagen {

	private BufferedImage img1;
	
    public Moneda(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
        img1 = Utilidades.obtenerRuta("/imagenes/moneda.png");
    }
    
    public void dibujarse(Graphics graphics) {
        try {
            graphics.drawImage(img1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
}
