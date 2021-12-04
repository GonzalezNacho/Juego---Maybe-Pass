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
//import java.util.Random;

import javax.swing.JPanel;

// Implemento KeyListener para poder leer en los metodos keyPressed y keyReleased los codigos de tecla que apreto el usuario
// Implemento Runnable para crear un Thread que ejecute en paralelo con mi programa
public class Juego extends JPanel implements KeyListener, Runnable {


    private static final long serialVersionUID = 1L;
    private int anchoJuego;
    private int largoJuego;
    private int tiempoDeEsperaEntreActualizaciones;
    private ElementoBasico zonaSegura;
    private List<Pared> paredes;
    private Ninja ninja;
    private Vidas vidas;
    private List<Enemigo> enemigos;
    //private int pantallaActual;
    private int enemigosPorLinea;
    private int filasDeEnemigos;
    private int cantidadVidas;


    public Juego(int anchoJuego, int largoJuego, int tiempoDeEsperaEntreActualizaciones, int enemigosPorLinea,
        int filasDeEnemigos, int vidas) {
        this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
        this.ninja = new Ninja(40, 40, 0, 0, 40, 40, Color.black);
        this.zonaSegura = new ZonaSegura (anchoJuego -220, 70, 200, 350, Color.GREEN); //anchoJuego-220,70,5,350
        this.paredes = new ArrayList<Pared>();
        this.enemigos = new ArrayList<Enemigo>();
        this.vidas = new Vidas(10, 45, new Font("Arial", 8, 20), Color.blue, vidas);
        this.tiempoDeEsperaEntreActualizaciones = tiempoDeEsperaEntreActualizaciones;
        this.enemigosPorLinea = enemigosPorLinea;
        this.filasDeEnemigos = filasDeEnemigos;
        this.cantidadVidas = vidas;
    }

    private void inicializarJuego() {
        this.enemigos.clear();
        this.paredes.clear();
        this.vidas = new Vidas(10, 500, new Font("Arial", 8, 20), Color.blue, cantidadVidas);
        agregarEnemigos(enemigosPorLinea, filasDeEnemigos);
        agregarParedes();
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
            actualizarJuego();
            dibujarJuego();
            esperar(tiempoDeEsperaEntreActualizaciones);
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    	// si mantengo apretada la tecla de la derecha se asigna velocidad 1 a la ninja
        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
            ninja.setVelocidadX(1);
        }
        
        if (arg0.getKeyCode() == KeyEvent.VK_UP) {
            ninja.setVelocidadY(-1);
        }
        
        if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
            ninja.setVelocidadY(1);
        }

        // si mantengo apretada la tecla de la izquierda se asigna velocidad -1 a la
        // ninja
        if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
            ninja.setVelocidadX(-1);
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
        zonaSegura.dibujarse(g);
        //paredes.dibujarse(g);
        ninja.dibujarse(g);
        vidas.dibujarse(g);
        dibujarEnemigos(g);
        dibujarParedes(g);
    }

    // En este metodo se actualiza el estado de todos los elementos del juego
    private void actualizarJuego() {
        verificarEstadoAmbiente();
        //pelota.moverse();
        ninja.moverse();
        moverEnemigos();
    }

    private void dibujarJuego() {
        this.repaint();
    }

    public void agregarEnemigo(Enemigo enemigo) {
        this.enemigos.add(enemigo);
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
        //verificarFinDeJuego();
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

    // se verifica si hay colision entre la ninja y la pelota. Si hay colision se
    // cambia la direccion de la pelota en el eje Y
   /* private void verificarReboteEntrePelotaYninja() {
        if (ninja.hayColision(pelota)) {
            pelota.rebotarEnEjeY();
        }
    }*/
    
    private void dibujarParedes(Graphics g) {
        for (Pared pared : paredes) {
            pared.dibujarse(g);
        }
    }
    
    private void verificarReboteEntreParedYninja() {
    	Iterator<Pared> iterador = paredes.iterator();
    	while (iterador.hasNext()) {
    		Pared pared = iterador.next();
    		if (ninja.hayColision(pared)) {
        		if (ninja.hayColisionEnY(pared)) {
        			if (ninja.getPosicionY() < pared.getPosicionY()) {
            			ninja.setPosicionY(pared.getPosicionY()-ninja.getLargo()-1);
            		}else {
            			ninja.setPosicionY(pared.getPosicionY()+pared.getLargo()+1);
            		}
        		} else {
        			if (ninja.getPosicionX() < pared.getPosicionX()) {
            			ninja.setPosicionX(pared.getPosicionX()-ninja.getAncho()-1);
            		}else {
            			ninja.setPosicionX(pared.getPosicionX()+pared.getAncho()+1);
            		}
        		}
        	}
    	}
    }
    
    private void verificarReboteEnemigosContraParedesLaterales() {
    	Iterator<Pared> iterador = paredes.iterator();
    	while (iterador.hasNext()) {
    		Pared pared = iterador.next();
    		for (Enemigo enemigo : enemigos) {
        		if (enemigo.hayColision(pared)) {
        			enemigo.rebotarEnEjeX();
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
                iterador.remove();
                vidas.perderVida();
            }
        }
    }

    // Se verifica si la cantidad de enemigos es 0 o si la cantidad de vidas es 0
    // para parar el juego
    /*private void verificarFinDeJuego() {

        if (vidas.getVidas() == 0) {
            pantallaActual = PANTALLA_PERDEDOR;
        }

        if (enemigos.size() == 0) {
            pantallaActual = PANTALLA_GANADOR;
        }
    }*/

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

    private void agregarEnemigos(int enemigosPorLinea, int filasDeEnemigos) {
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
    
    private void agregarParedes() {
    	agregaPared(new  Pared (20,20,5,400, Color.blue));
    	//agregaPared(new  Pared (20,20,anchoJuego-35,5, Color.blue));
    	agregaPared(new  Pared (20,20,200,5, Color.blue));
    	agregaPared(new  Pared (270,70,5,305, Color.blue));
    	agregaPared(new  Pared (270,70,400,5, Color.blue));
    	agregaPared(new  Pared (670,20,5,55, Color.blue));
    	agregaPared(new  Pared (670,20,315,5, Color.blue));
    	agregaPared(new  Pared (730,70,55,5, Color.blue));
    	agregaPared(new  Pared (730,70,5,305, Color.blue));
    	agregaPared(new  Pared (220,20,5,350, Color.blue));
    	//agregaPared(new  Pared (20,420,anchoJuego-35,5, Color.blue));
    	agregaPared(new  Pared (20,420,315,5, Color.blue));
    	agregaPared(new  Pared (220,370,50,5, Color.blue));
    	agregaPared(new  Pared (330,370,5,50, Color.blue));
    	agregaPared(new  Pared (330,370,400,5, Color.blue));
    	agregaPared(new  Pared (780,420,205,5, Color.blue));
    	agregaPared(new  Pared (anchoJuego-20,20,5,400, Color.blue));
    	agregaPared(new  Pared (anchoJuego-220,70,5,350, Color.blue));
	}

	private void agregaPared(Pared pared) {
		// TODO Auto-generated method stub
		this.paredes.add(pared);
	}

}
