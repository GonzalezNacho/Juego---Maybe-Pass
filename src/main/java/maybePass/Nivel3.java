package maybePass;

import java.awt.Color;
import java.util.List;

public class Nivel3  implements Nivel {

	private int filasDeEnemigos;
	private int enemigosPorLinea;
	//private int anchoJuego;
	//private int largoJuego;
	private Ninja ninja;
	private ElementoBasico ubicacionInicial;
	private ElementoBasico zonaSegura;
	private List<ElementoInanimado> paredes;
	private List<Enemigo> enemigos;
	
	
	public Nivel3(Ninja ninja, ElementoBasico ubicacionInicial, ElementoBasico zonaSegura,int anchoJuego, int largoJuego, List<Enemigo> enemigos, List<ElementoInanimado> paredes) {
		//this.anchoJuego = anchoJuego;
        //this.largoJuego = largoJuego;
        this.ninja = ninja;
        this.ubicacionInicial = ubicacionInicial;
        this.zonaSegura = zonaSegura;
        this.paredes = paredes;
        this.enemigos = enemigos;
        this.enemigosPorLinea = 1;
        this.filasDeEnemigos= 4;
	}
	@Override
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

	@Override
	public void agregarEnemigos() {
		for (int x = 1; x <= enemigosPorLinea; x++) {
            for (int y = 1; y <= filasDeEnemigos; y++) {
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

	@Override
	public void ubicarNinja() {
		ninja.setPosicionX(130);
		ninja.setPosicionY(40);
	}

	@Override
	public void configurarZonaInicial() {
		ubicacionInicial.setPosicionX(125);
		ubicacionInicial.setPosicionY(40);
		ubicacionInicial.setAncho(55);
		ubicacionInicial.setLargo(45);
	}

	@Override
	public void configurarZonaSegura() {
		zonaSegura.setPosicionX(725);
		zonaSegura.setPosicionY(50);
		zonaSegura.setAncho(105);
		zonaSegura.setLargo(55);
	}
	@Override
	public void agregarEnemigo(Enemigo enemigo) {
		this.enemigos.add(enemigo);
	}
	@Override
	public void agregaPared(ElementoInanimado pared) {
		this.paredes.add(pared);
	}

}