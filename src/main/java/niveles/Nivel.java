package niveles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import maybePass.ElementoBasico;
import maybePass.ElementoInanimado;
import maybePass.Enemigo;
import maybePass.Moneda;
import maybePass.Ninja;


public abstract class Nivel  {

	private int filasDeEnemigos;
	private int enemigosPorLinea;
	private int anchoJuego;
	private int largoJuego;
	private Ninja ninja;
	private ElementoBasico ubicacionInicial;
	private ElementoBasico zonaSegura;
	private List<ElementoInanimado> paredes;
	private List<Enemigo> enemigos;
	private List<Moneda> monedas;
	private String pathImagen;
	private BufferedImage img;
	
	
	public Nivel(Ninja ninja, ElementoBasico ubicacionInicial, ElementoBasico zonaSegura,int anchoJuego, int largoJuego, List<Enemigo> enemigos, List<Moneda> monedas, List<ElementoInanimado> paredes, String path) {
		this.monedas = monedas;
		this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
        this.ninja = ninja;
        this.ubicacionInicial = ubicacionInicial;
        this.zonaSegura = zonaSegura;
        this.paredes = paredes;
        this.enemigos = enemigos;
        this.enemigosPorLinea = 1;
        this.filasDeEnemigos= 7;
        this.pathImagen = path;
        try {
        	this.img = ImageIO.read(new File(pathImagen));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	public abstract void agregarParedes();

	
	public abstract void agregarEnemigos();
	
	
	public abstract void agregarMonedas();

	
	public abstract void ubicarNinja();

	
	public abstract void configurarZonaInicial();

	
	public abstract void configurarZonaSegura();
	
	public void agregarEnemigo(Enemigo enemigo) {
		this.enemigos.add(enemigo);
	}
	
	public void agregarMoneda(Moneda moneda) {
		this.monedas.add(moneda);
	}
	
	public void agregaPared(ElementoInanimado pared) {
		this.paredes.add(pared);
	}
	
	public void dibujarFondo(Graphics graphics) {
		graphics.drawImage(img, 0, 0, getAnchoJuego(), largoJuego, null);
	}
	
	public void numeroNivelDibujarse(Graphics g, int posicionX, int posicionY, int nivel, Font fuente) {
		g.setColor(Color.BLUE);
        g.setFont(fuente);
        g.drawString("Nivel: " + nivel, posicionX, posicionY);
	}

	public int getAnchoJuego() {
		return anchoJuego;
	}

	public ElementoBasico getZonaSegura() {
		return zonaSegura;
	}

	public ElementoBasico getUbicacionInicial() {
		return ubicacionInicial;
	}

	public Ninja getNinja() {
		return ninja;
	}

	public int getEnemigosPorLinea() {
		return enemigosPorLinea;
	}

	public int getFilasDeEnemigos() {
		return filasDeEnemigos;
	}
	
	public void setFilasDeEnemigos(int filasDeEnemigos) {
		this.filasDeEnemigos = filasDeEnemigos;
	}

}
