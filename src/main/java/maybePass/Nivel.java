package maybePass;

import java.awt.Graphics;

public interface Nivel {
	
	public void agregarParedes();
	
	public void agregarEnemigos();
	
	public void agregarMonedas();
	
	public void ubicarNinja();
	
	public void configurarZonaInicial();
	
	public void configurarZonaSegura();
	
	public void agregarEnemigo(Enemigo enemigo);
	
	public void agregaPared(ElementoInanimado pared);
	
	public void agregarMoneda(Moneda moneda);
	
	//public void ubicarMonedas();
	public void dibujarFondo(Graphics graphics);
}
