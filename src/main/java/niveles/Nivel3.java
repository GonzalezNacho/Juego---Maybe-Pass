package niveles;

import java.awt.Color;
import java.util.List;

import maybePass.ElementoBasico;
import maybePass.ElementoInanimado;
import maybePass.Enemigo;
import maybePass.Moneda;
import maybePass.Ninja;
import maybePass.Shuriken;

public class Nivel3 extends Nivel{

	public Nivel3(Ninja ninja, ElementoBasico ubicacionInicial, ElementoBasico zonaSegura, int anchoJuego,
			int largoJuego, List<Enemigo> enemigos, List<Moneda> monedas, List<ElementoInanimado> paredes,String path) {
		super(ninja, ubicacionInicial, zonaSegura, anchoJuego, largoJuego, enemigos, monedas, paredes, path);
		
	}
	
	public void agregarParedes() {
		agregaPared(new  ElementoInanimado (20,80,5,440, Color.blue));
		agregaPared(new  ElementoInanimado (20,520,605,5, Color.blue));
		agregaPared(new  ElementoInanimado (620,420,5,100, Color.blue));
		agregaPared(new  ElementoInanimado (620,420,300,5, Color.blue));
		agregaPared(new  ElementoInanimado (920,100,5,325, Color.blue));
		agregaPared(new  ElementoInanimado (830,100,90,5, Color.blue));
		agregaPared(new  ElementoInanimado (830,50,5,50, Color.blue));
		agregaPared(new  ElementoInanimado (730,50,100,5, Color.blue));
		agregaPared(new  ElementoInanimado (725,50,5,50, Color.blue));
		agregaPared(new  ElementoInanimado (655,100,75,5, Color.blue));
		agregaPared(new  ElementoInanimado (655,100,5,270, Color.blue));
		agregaPared(new  ElementoInanimado (300,365,360,5, Color.blue));
		agregaPared(new  ElementoInanimado (300,365,5,100, Color.blue));
		agregaPared(new  ElementoInanimado (225,465,80,5, Color.blue));
		agregaPared(new  ElementoInanimado (225,80,5,390, Color.blue));
		agregaPared(new  ElementoInanimado (180,80,50,5, Color.blue));
		agregaPared(new  ElementoInanimado (180,35,5,50, Color.blue));
		agregaPared(new  ElementoInanimado (120,35,60,5, Color.blue));
		agregaPared(new  ElementoInanimado (120,35,5,50, Color.blue));
		agregaPared(new  ElementoInanimado (20,80,100,5, Color.blue));
	}
	public void agregarEnemigos() {
		super.setFilasDeEnemigos(4);
		for (int x = 1; x <= getEnemigosPorLinea(); x++) {
            for (int y = 1; y <= getFilasDeEnemigos(); y++) {
            	if( y % 2 == 0) {
   					agregarEnemigo(new Shuriken(180, 50 + y * 35, 1, 0, 20, 20, Color.white));
            		agregarEnemigo(new Shuriken(30, 80 + y * 35, 1, 0, 20, 20, Color.red));
            		agregarEnemigo(new Shuriken(180, 250 + y * 35, 1, 0, 20, 20, Color.red));
            		agregarEnemigo(new Shuriken(30, 280 + y * 35, 1, 0, 20, 20, Color.red));

            	}
            	else {
            		agregarEnemigo(new Shuriken(700, 100 + y * 35, 1, 0, 20, 20, Color.red));
            		agregarEnemigo(new Shuriken(800, 180 + y * 35, 1, 0, 20, 20, Color.red));
            		agregarEnemigo(new Shuriken(500, 400 + x * 40, 0, 1, 20, 20, Color.red));
            		agregarEnemigo(new Shuriken(400, 400 + x * 40, 0, 1, 20, 20, Color.red));
            		agregarEnemigo(new Shuriken(320, 350 + x * 40, 0, 1, 20, 20, Color.red));
            	}
                
            }
        }
	}
	
	
	public void agregarMonedas() {
		agregarMoneda(new Moneda(50, 475, 0, 0, 20, 20, Color.white));
		agregarMoneda(new Moneda(325, 375, 0, 0, 20, 20, Color.white));
		agregarMoneda(new Moneda(860, 375, 0, 0, 20, 20, Color.white));
	}

	
	public void ubicarNinja() {
		getNinja().setPosicionX(130);
		getNinja().setPosicionY(40);
	}

	
	public void configurarZonaInicial() {
		getUbicacionInicial().setPosicionX(125);
		getUbicacionInicial().setPosicionY(40);
		getUbicacionInicial().setAncho(55);
		getUbicacionInicial().setLargo(45);
	}

	
	public void configurarZonaSegura() {
		getZonaSegura().setPosicionX(725);
		getZonaSegura().setPosicionY(50);
		getZonaSegura().setAncho(105);
		getZonaSegura().setLargo(55);
	}
}
