package maybePass;

import java.awt.Color;
import java.util.List;

public class Nivel2  implements Nivel {

	private int columnasDeEnemigos;
	private int enemigosPorColumna;
	//private int anchoJuego;
	//private int largoJuego;
	private Ninja ninja;
	private ElementoBasico ubicacionInicial;
	private ElementoBasico zonaSegura;
	private List<ElementoInanimado> paredes;
	private List<Enemigo> enemigos;
	
	
	public Nivel2(Ninja ninja, ElementoBasico ubicacionInicial, ElementoBasico zonaSegura,int anchoJuego, int largoJuego, List<Enemigo> enemigos, List<ElementoInanimado> paredes) {
		//this.anchoJuego = anchoJuego;
        //this.largoJuego = largoJuego;
        this.ninja = ninja;
        this.ubicacionInicial = ubicacionInicial;
        this.zonaSegura = zonaSegura;
        this.paredes = paredes;
        this.enemigos = enemigos;
        this.enemigosPorColumna = 1;
        this.columnasDeEnemigos= 14;
	}
	@Override
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

	@Override
	public void agregarEnemigos() {
		for (int x = 1; x <= enemigosPorColumna; x++) {
            for (int y = 1; y <= columnasDeEnemigos; y++) {
            	if( y % 2 == 0) {
   					agregarEnemigo(new Shuriken(180 + y * 41, 220, 0, 1.5, 20, 20, Color.white));
            	}
            	else {
            		agregarEnemigo(new Shuriken(180 + y * 41, 220, 0, -1.5, 20, 20, Color.red));
            	}
                
            }
        }
	}

	@Override
	public void ubicarNinja() {
		ninja.setPosicionX(100);
		ninja.setPosicionY(200);
	}

	@Override
	public void configurarZonaInicial() {
		ubicacionInicial.setPosicionX(50);
		ubicacionInicial.setPosicionY(200);
		ubicacionInicial.setAncho(150);
		ubicacionInicial.setLargo(100);
	}

	@Override
	public void configurarZonaSegura() {
		zonaSegura.setPosicionX(800);
		zonaSegura.setPosicionY(200);
		zonaSegura.setAncho(150);
		zonaSegura.setLargo(100);
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