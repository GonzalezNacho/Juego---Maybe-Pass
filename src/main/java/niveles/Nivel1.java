package niveles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import maybePass.ElementoBasico;
import maybePass.ElementoInanimado;
import maybePass.Enemigo;
import maybePass.Moneda;
import maybePass.Ninja;
import maybePass.Shuriken;

public class Nivel1 extends Nivel{

	public Nivel1(Ninja ninja, ElementoBasico ubicacionInicial, ElementoBasico zonaSegura, int anchoJuego,
			int largoJuego, List<Enemigo> enemigos, List<Moneda> monedas, List<ElementoInanimado> paredes,BufferedImage bufferedImage) {
		super(ninja, ubicacionInicial, zonaSegura, anchoJuego, largoJuego, enemigos, monedas, paredes, bufferedImage);
		
	}
	
	public void agregarParedes() {
		agregaPared(new  ElementoInanimado (20,20,5,400, Color.blue));
    	agregaPared(new  ElementoInanimado (20,20,200,5, Color.blue));
    	agregaPared(new  ElementoInanimado (270,70,5,305, Color.blue));
    	agregaPared(new  ElementoInanimado (270,70,400,5, Color.blue));
    	agregaPared(new  ElementoInanimado (670,20,5,55, Color.blue));
    	agregaPared(new  ElementoInanimado (670,20,315,5, Color.blue));
    	agregaPared(new  ElementoInanimado (730,70,55,5, Color.blue));
    	agregaPared(new  ElementoInanimado (730,70,5,305, Color.blue));
    	agregaPared(new  ElementoInanimado (220,20,5,350, Color.blue));
    	agregaPared(new  ElementoInanimado (20,420,315,5, Color.blue));
    	agregaPared(new  ElementoInanimado (220,370,50,5, Color.blue));
    	agregaPared(new  ElementoInanimado (330,370,5,50, Color.blue));
    	agregaPared(new  ElementoInanimado (330,370,400,5, Color.blue));
    	agregaPared(new  ElementoInanimado (780,420,205,5, Color.blue));
    	agregaPared(new  ElementoInanimado (getAnchoJuego()-20,20,5,400, Color.blue));
    	agregaPared(new  ElementoInanimado (getAnchoJuego()-220,70,5,350, Color.blue));
	}
	public void agregarEnemigos() {
		for (int x = 1; x <= getEnemigosPorLinea(); x++) {
            for (int y = 1; y <= getFilasDeEnemigos(); y++) {
            	if( y % 2 == 0) {
   					agregarEnemigo(new Shuriken(getAnchoJuego()/ 2, 60 + y * 40, -2, 0, 20, 20, Color.white));
            	}
            	else {
            		agregarEnemigo(new Shuriken(getAnchoJuego() / 2, 60 + y * 40, 2, 0, 20, 20, Color.red));
            	}
                
            }
        }
	}
	
	
	public void agregarMonedas() {
		agregarMoneda(new Moneda(475, 225, 0, 0, 20, 20, Color.white));
		agregarMoneda(new Moneda(300, 225, 0, 0, 20, 20, Color.white));
		agregarMoneda(new Moneda(675, 225, 0, 0, 20, 20, Color.white));
	}

	
	public void ubicarNinja() {
		getNinja().setPosicionX(40);
		getNinja().setPosicionY(40);
	}

	
	public void configurarZonaInicial() {
		getUbicacionInicial().setPosicionX(25);
		getUbicacionInicial().setPosicionY(25);
		getUbicacionInicial().setAncho(200);
		getUbicacionInicial().setLargo(350);
	}

	
	public void configurarZonaSegura() {
		getZonaSegura().setPosicionX(getAnchoJuego() -220);
		getZonaSegura().setPosicionY(70);
		getZonaSegura().setAncho(200);
		getZonaSegura().setLargo(350);
	}
}
