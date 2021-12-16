package maybePass;

import java.awt.Color;


public abstract class Enemigo extends ElementoConImagen {

    public Enemigo(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
            Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
    }

}
