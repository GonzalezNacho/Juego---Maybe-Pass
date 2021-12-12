package maybePass;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class ElementoConImagen extends ElementoBasico {

    public ElementoConImagen(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
    }

    public BufferedImage rotarImagen(BufferedImage imagenARotar, int grados) {
		int anchoImagen = imagenARotar.getWidth();
		int altoImagen = imagenARotar.getHeight();
		int tipoImagen = imagenARotar.getType();

		BufferedImage imagenRotada = new BufferedImage(anchoImagen, altoImagen, tipoImagen);

		Graphics2D graphics2D = imagenRotada.createGraphics();

		graphics2D.rotate(Math.toRadians(grados), anchoImagen / 2, altoImagen / 2);
		graphics2D.drawImage(imagenARotar, null, 0, 0);

		return imagenRotada;
	}

}
