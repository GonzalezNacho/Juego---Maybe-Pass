package maybePass;

import java.awt.Color;
import java.util.List;

public class Nivel1  implements Nivel {

	private int filasDeEnemigos;
	private int enemigosPorLinea;
	private int anchoJuego;
	//private int largoJuego;
	private Ninja ninja;
	private ElementoBasico ubicacionInicial;
	private ElementoBasico zonaSegura;
	private List<ElementoInanimado> paredes;
	private List<Enemigo> enemigos;
	
	
	public Nivel1(Ninja ninja, ElementoBasico ubicacionInicial, ElementoBasico zonaSegura,int anchoJuego, int largoJuego, List<Enemigo> enemigos, List<ElementoInanimado> paredes) {
		this.anchoJuego = anchoJuego;
        //this.largoJuego = largoJuego;
        this.ninja = ninja;
        this.ubicacionInicial = ubicacionInicial;
        this.zonaSegura = zonaSegura;
        this.paredes = paredes;
        this.enemigos = enemigos;
        this.enemigosPorLinea = 1;
        this.filasDeEnemigos= 7;
	}
	@Override
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
    	agregaPared(new  ElementoInanimado (anchoJuego-20,20,5,400, Color.blue));
    	agregaPared(new  ElementoInanimado (anchoJuego-220,70,5,350, Color.blue));
	}

	@Override
	public void agregarEnemigos() {
		for (int x = 1; x <= enemigosPorLinea; x++) {
            for (int y = 1; y <= filasDeEnemigos; y++) {
            	if( y % 2 == 0) {
   					agregarEnemigo(new EnemigoRedondo(anchoJuego/ 2, 60 + y * 40, -2, 0, 20, 20, Color.white));
            	}
            	else {
            		agregarEnemigo(new EnemigoRedondo(anchoJuego / 2, 60 + y * 40, 2, 0, 20, 20, Color.red));
            	}
                
            }
        }
	}

	@Override
	public void ubicarNinja() {
		ninja.setPosicionX(40);
		ninja.setPosicionY(40);
	}

	@Override
	public void configurarZonaInicial() {
		ubicacionInicial.setPosicionX(25);
		ubicacionInicial.setPosicionY(25);
		ubicacionInicial.setAncho(200);
		ubicacionInicial.setLargo(350);
	}

	@Override
	public void configurarZonaSegura() {
		zonaSegura.setPosicionX(anchoJuego -220);
		zonaSegura.setPosicionY(70);
		zonaSegura.setAncho(200);
		zonaSegura.setLargo(350);
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
