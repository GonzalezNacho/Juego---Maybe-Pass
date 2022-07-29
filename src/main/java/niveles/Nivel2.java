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

public class Nivel2 extends Nivel{

	private int columnasDeEnemigos;
	private int enemigosPorColumna;
	
	public Nivel2(Ninja ninja, ElementoBasico ubicacionInicial, ElementoBasico zonaSegura, int anchoJuego,
			int largoJuego, List<Enemigo> enemigos, List<Moneda> monedas, List<ElementoInanimado> paredes, BufferedImage bufferedImage) {
		super(ninja, ubicacionInicial, zonaSegura, anchoJuego, largoJuego, enemigos, monedas, paredes, bufferedImage);
		this.enemigosPorColumna = 1;
        this.columnasDeEnemigos= 14;
	}
	
	public void agregarParedes() {
		agregaPared(new  ElementoInanimado (50,200,5,100, Color.blue));
    	agregaPared(new  ElementoInanimado (50,200,150,5, Color.blue));
    	agregaPared(new  ElementoInanimado (200,105,5,100, Color.blue));
    	agregaPared(new  ElementoInanimado (200,105,600,5, Color.blue));
    	agregaPared(new  ElementoInanimado (800,105,5,100, Color.blue));
    	agregaPared(new  ElementoInanimado (800,200,150,5, Color.blue));
    	agregaPared(new  ElementoInanimado (950,200,5,100, Color.blue));
    	agregaPared(new  ElementoInanimado (805,300,150,5, Color.blue));
    	agregaPared(new  ElementoInanimado (800,300,5,100, Color.blue));
    	agregaPared(new  ElementoInanimado (200,400,605,5, Color.blue));
    	agregaPared(new  ElementoInanimado (200,300,5,100, Color.blue));
    	agregaPared(new  ElementoInanimado (50,300,155,5, Color.blue));
	}
	public void agregarEnemigos() {
		for (int x = 1; x <= enemigosPorColumna; x++) {
            for (int y = 1; y <= columnasDeEnemigos; y++) {
            	if( y % 2 == 0) {
   					agregarEnemigo(new Shuriken(180 + y * 41, 220, 0, 2, 20, 20, Color.white));
            	}
            	else {
            		agregarEnemigo(new Shuriken(180 + y * 41, 220, 0, -2, 20, 20, Color.red));
            	}
                
            }
        }
	}
	
	
	public void agregarMonedas() {
		agregarMoneda(new Moneda(225, 130, 0, 0, 20, 20, Color.white));
		agregarMoneda(new Moneda(475, 250, 0, 0, 20, 20, Color.white));
		agregarMoneda(new Moneda(750, 355, 0, 0, 20, 20, Color.white));;
	}

	
	public void ubicarNinja() {
		getNinja().setPosicionX(100);
		getNinja().setPosicionY(200);
	}

	
	public void configurarZonaInicial() {
		getUbicacionInicial().setPosicionX(50);
		getUbicacionInicial().setPosicionY(200);
		getUbicacionInicial().setAncho(150);
		getUbicacionInicial().setLargo(100);
	}

	
	public void configurarZonaSegura() {
		getZonaSegura().setPosicionX(800);
		getZonaSegura().setPosicionY(200);
		getZonaSegura().setAncho(150);
		getZonaSegura().setLargo(100);
	}
}
