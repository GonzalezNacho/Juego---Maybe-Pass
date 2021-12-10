package maybePass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.*;
import javax.swing.JPanel;

// Implemento KeyListener para poder leer en los metodos keyPressed y keyReleased los codigos de tecla que apreto el usuario
// Implemento Runnable para crear un Thread que ejecute en paralelo con mi programa
public class Juego extends JPanel implements KeyListener, Runnable {

    private int pantalla;
    private final static int PANTALLA_INICIO = 1;
    private final static int PANTALLA_JUEGO = 2;
    private final static int PANTALLA_PERDEDOR = 3;
    private final static int PANTALLA_GANADOR = 4;
    private final static int PANTALLA_SIGUIENTE_NIVEL = 5;
    private static final long serialVersionUID = 1L;
    private int anchoJuego;
    private int largoJuego;
    private int tiempoDeEsperaEntreActualizaciones;
    private ElementoBasico zonaSegura;
    private ElementoBasico ubicacionInicial;
    private List<ElementoInanimado> paredes;
    private Ninja ninja;
    private Vidas vidas;
    private Nivel nivel;
    private List<Enemigo> enemigos;
    private int numeroNivel;
    private int cantidadVidas;
    private Pantalla portada;
    private Pantalla ganaste;
    private Pantalla perdiste;
    private Pantalla siguienteNivel;
    private Monedas monedasTexto;
    private List<Moneda> monedas;


    public Juego(int anchoJuego, int largoJuego, int tiempoDeEsperaEntreActualizaciones, int numeroNivel, int vidas, int cantidadMonedas) {
    	this.pantalla = PANTALLA_INICIO;
        this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
        this.ninja = new Ninja(0, 0, 0, 0, 40, 40, Color.black);
        this.zonaSegura = new ElementoInanimado (0, 0, 0, 0, Color.GREEN);
        this.ubicacionInicial = new ElementoInanimado (0, 0, 0, 0, Color.red);
        this.paredes = new ArrayList<ElementoInanimado>();
        this.enemigos = new ArrayList<Enemigo>();
        this.vidas = new Vidas(10, 45, new Font("Arial", 8, 20), Color.blue, vidas);
        this.tiempoDeEsperaEntreActualizaciones = tiempoDeEsperaEntreActualizaciones;
        this.numeroNivel = numeroNivel;
        this.cantidadVidas = vidas;
        this.portada = new Pantalla(anchoJuego, largoJuego, "/C:/Code/Java/2Juego---Maybe-Pass/src/main/resources/imagenes/portada.PNG");
        this.ganaste = new Pantalla(anchoJuego, largoJuego, "/C:/Code/Java/2Juego---Maybe-Pass/src/main/resources/imagenes/ganaste.png");
        this.perdiste = new Pantalla(anchoJuego, largoJuego, "/C:/Code/Java/2Juego---Maybe-Pass/src/main/resources/imagenes/perdiste.png");
        this.siguienteNivel = new Pantalla(anchoJuego, largoJuego, "/C:/Code/Java/2Juego---Maybe-Pass/src/main/resources/imagenes/siguiente-nivel.png");
        this.monedas = new ArrayList<Moneda>();
        this.monedasTexto = new Monedas(100, 500, new Font("Arial", 8, 20), Color.blue, cantidadMonedas);
    }
    
    private void obtenerNivel() {
    	if (numeroNivel ==1) {
    		this.nivel = new Nivel1( ninja, ubicacionInicial, zonaSegura, anchoJuego, largoJuego, enemigos, monedas,  paredes);
    	}else if (numeroNivel ==2) {
    		this.nivel = new Nivel2( ninja, ubicacionInicial, zonaSegura, anchoJuego, largoJuego, enemigos,  monedas,paredes);
    	}else if (numeroNivel ==3) {
    		this.nivel = new Nivel3( ninja, ubicacionInicial, zonaSegura, anchoJuego, largoJuego, enemigos, monedas, paredes);
    	}
    }

    private void inicializarJuego() {
    	numeroNivel = 1;
    	crearNivel();
        this.vidas = new Vidas(10, 500, new Font("Arial", 8, 20), Color.blue, cantidadVidas);
    }
    

	private void obtenerDatosDelNivel(Nivel nivel) {
		nivel.agregarEnemigos();
    	nivel.agregarParedes();
    	nivel.configurarZonaInicial();
    	nivel.ubicarNinja();
    	nivel.configurarZonaSegura();
	}

	@Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }

    /*
     * Actualizar la actualizacion y el dibujado del juego de esta forma no es
     * recomendable dado que tendra distintas velocidades en distinto hardware. Se
     * hizo asi por simplicidad para facilitar el aprendizaje dado que lo
     * recomendado es separar la parte de dibujado de la de actualizacion y usar
     * interpolation
     */
    @Override
    public void run() {
    	inicializarJuego();
        while (true) {
            if (pantalla == PANTALLA_JUEGO) {	
            	actualizarJuego();
            }
            dibujarJuego();
            esperar(tiempoDeEsperaEntreActualizaciones);
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    	
    	if (pantalla == PANTALLA_INICIO && arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            inicializarJuego();
            pantalla = PANTALLA_JUEGO;
            nivel.agregarMonedas();
    		System.out.println("moneddaa");
        }
    	
    	if (pantalla == PANTALLA_SIGUIENTE_NIVEL && arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            pantalla = PANTALLA_JUEGO;
            nivel.agregarMonedas();
        }

        if ((pantalla == PANTALLA_PERDEDOR && arg0.getKeyCode() == KeyEvent.VK_ENTER) || (pantalla == PANTALLA_GANADOR && arg0.getKeyCode() == KeyEvent.VK_ENTER)) {
            pantalla = PANTALLA_INICIO;
        }
    	
        if (pantalla == PANTALLA_JUEGO) {
        	// si mantengo apretada la tecla de la derecha se asigna velocidad en X 1 al ninja
        	if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
		    ninja.setVelocidadX(1);
        	}

        	if (arg0.getKeyCode() == KeyEvent.VK_UP) {
        		ninja.setVelocidadY(-1);
        	}

        	if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
        		ninja.setVelocidadY(1);
        	}

        	// si mantengo apretada la tecla de la izquierda se asigna velocidad en x -1 al ninja
        	if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
        		ninja.setVelocidadX(-1);
        	}
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // si suelto la tecla 39 o la 37 se asigna velocidad 0 a la ninja
        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT || arg0.getKeyCode() == KeyEvent.VK_LEFT) {
            ninja.setVelocidadX(0);
        }
        
        if (arg0.getKeyCode() == KeyEvent.VK_DOWN || arg0.getKeyCode() == KeyEvent.VK_UP) {
            ninja.setVelocidadY(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    // Este metodo se llama cuando se hace un this.repaint() automaticamente
    // Aca se dibujan a todos los elementos, para ello cada elemento implementa el
    // metodo dibujarse
    protected void paintComponent(Graphics g) {
    	this.limpiarPantalla(g);
    	if (pantalla == PANTALLA_INICIO) {
    		portada.dibujarse(g);
    	}
    	if (pantalla == PANTALLA_PERDEDOR) {
    		perdiste.dibujarse(g);
    	}
    	if (pantalla == PANTALLA_JUEGO) {
    		super.paintComponent(g);
	        zonaSegura.dibujarse(g);
	        ubicacionInicial.dibujarse(g);
	        ninja.dibujarse(g);
	        vidas.dibujarse(g);
	        monedasTexto.dibujarse(g);
	        dibujarEnemigos(g);
	        dibujarMonedas(g);
	        dibujarParedes(g);
    	}
    	if (pantalla == PANTALLA_GANADOR) {
    		ganaste.dibujarse(g);
    	}
    	if (pantalla == PANTALLA_SIGUIENTE_NIVEL) {
    		siguienteNivel.dibujarse(g);
    	} 
    }

    // En este metodo se actualiza el estado de todos los elementos del juego
    private void actualizarJuego() {
        verificarEstadoAmbiente();
        ninja.moverse();
        moverEnemigos();
    }
    
    private void dibujarMonedas(Graphics g) {
        for (Moneda moneda : monedas) {
            moneda.dibujarse(g);
        }
    }

    private void dibujarJuego() {
        this.repaint();
    }


    // se hace una iteracion de todos los enemigos cargados en la lista de enemigos
    // y se le dice a cada uno que ejecute el metodo moverse().
    // moverse() actualiza la posicionX y posicionY del elemento en base a la
    // direccion/velocidad que tenia para X e Y
    private void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            enemigo.moverse();
        }
    }

    // Se hace una iteracion en la lista de enemigos y se ejecuta el metodo
    // dibujarse()
    private void dibujarEnemigos(Graphics g) {
        for (Enemigo enemigo : enemigos) {
            enemigo.dibujarse(g);
        }
    }

    // En este metodo verifico las colisiones, los rebotes de la pelota contra las
    // paredes, la colision entre enemigos y el fin de juego
    private void verificarEstadoAmbiente() {
        verificarReboteEntreParedYninja();
        verificarReboteEnemigosContraParedesLaterales(); 
        verificarReboteEntreEnemigos();
        verificarColisionEntreEnemigoYninja();
        verificarColisionEntreMonedaYninja();
        verificarFinDeJuego();
    }

	private void crearNivel() {
		this.enemigos.clear();
		this.monedas.clear();
        this.paredes.clear();
        obtenerNivel();
        obtenerDatosDelNivel(nivel);
	}

	// Se iteran todos los enemigos y se verifica para cada enemigo si hay colision
    // con cada enemigo. Si hay colision se ejecuta el metodo rebotarEnEjeX() del
    // enemigo esto hace que el enemigo cambie de direccion en el eje X
    private void verificarReboteEntreEnemigos() {
        for (Enemigo enemigo1 : enemigos) {
            for (Enemigo enemigo2 : enemigos) {
                if (enemigo1 != enemigo2 && enemigo1.hayColision(enemigo2)) {
                    enemigo1.rebotarEnEjeX();
                }
            }
        }
    }
    
    private void dibujarParedes(Graphics g) {
        for (ElementoInanimado pared : paredes) {
            pared.dibujarse(g);
        }
    }
    
    private void verificarReboteEntreParedYninja() {
    	Iterator<ElementoInanimado> iterador = paredes.iterator();
    	while (iterador.hasNext()) {
    		ElementoInanimado pared = iterador.next();
    		if (ninja.hayColision(pared)) {
        		if (ninja.hayColisionEnY(pared)) {
        			if (ninja.getPosicionY() < pared.getPosicionY()) {
            			ninja.setPosicionY(pared.getPosicionY()-ninja.getLargo()-1);
            		}else {
            			ninja.setPosicionY(pared.getPosicionY()+pared.getLargo()+1);
            		}
        		} else {
        			if (ninja.getPosicionX() < pared.getPosicionX()) {
        				ninja.frenarEnEjeXPorDerecha();
            		}else {
            			ninja.frenarEnEjeXPorIzquierda();
            		}
        		}
        	}
    	}
    }
    
    private void verificarReboteEnemigosContraParedesLaterales() {
    	Iterator<ElementoInanimado> iterador = paredes.iterator();
    	while (iterador.hasNext()) {
    		ElementoInanimado pared = iterador.next();
    		for (Enemigo enemigo : enemigos) {
        		if (enemigo.hayColision(pared)) {
        			enemigo.rebotarEnEjeX();
        			enemigo.rebotarEnEjeY();
            	}
    		}	
    	}
    }

    // se verifica si hay colision de cada enemigo contra las paredes laterales, si
    // hay colision se cambia la direccion del enemigo en el eje X
    /*private void verificarReboteEnemigosContraParedesLaterales() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.getPosicionX() <= 0 || enemigo.getPosicionX() + enemigo.getAncho() >= anchoJuego) {
                enemigo.rebotarEnEjeX();
            }
        }
    }*/

    // se verifica si la pelota colisiona con cada uno de los enemigos. Si hay
    // colision se hace rebotar la pelota en el ejeY, se suma un punto y se toca el
    // sonido toc
    private void verificarColisionEntreEnemigoYninja() {
        Iterator<Enemigo> iterador = enemigos.iterator();
        while (iterador.hasNext()) {
            Enemigo enemigo = iterador.next();
            if (enemigo.hayColision(ninja)) {
                vidas.perderVida();
                ninja.volverALaPosicionInicial(ubicacionInicial);
            }
        }
    }
    
    private void verificarColisionEntreMonedaYninja() {
        Iterator<Moneda> iterador = monedas.iterator();
        while (iterador.hasNext()) {
            Moneda moneda = iterador.next();
            if (moneda.hayColision(ninja)) {
                monedasTexto.ganarMonedas();
                moneda.destroy();
            }
        }
    }

    // Se verifica si la cantidad de enemigos es 0 o si la cantidad de vidas es 0
    // para parar el juego
    private void verificarFinDeJuego() {

        if (vidas.getVidas() == 0) {
            pantalla = PANTALLA_PERDEDOR;
        	//System.out.println("Perdiste");
        }

        if (ninja.hayColision(zonaSegura) && numeroNivel <= 3) {
        	pantalla = PANTALLA_SIGUIENTE_NIVEL;
        	numeroNivel++;
        	crearNivel();
        	//System.out.println("Ganaste");
        }
        
       if (ninja.hayColision(zonaSegura) && numeroNivel == 4) {
    	   pantalla = PANTALLA_GANADOR;
       }
    }

    // metodo para limpiar la pantalla
    private void limpiarPantalla(Graphics graphics) {
        graphics.setColor(Color.gray);
        graphics.fillRect(0, 0, anchoJuego, largoJuego);
    }

    // metodo para esperar una cantidad de milisegundos
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
}
