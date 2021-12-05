package maybePass;

public interface Nivel {
	
	public void agregarParedes();
	
	public void agregarEnemigos();
	
	public void ubicarNinja();
	
	public void configurarZonaInicial();
	
	public void configurarZonaSegura();
	
	public void agregarEnemigo(Enemigo enemigo);
	
	public void agregaPared(ElementoInanimado pared);
	
	//public void ubicarMonedas();
}
