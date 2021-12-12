package maybePass;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Monedas implements Dibujable{
	
	private int posicionX;
    private int posicionY;
    private Font font;
    private Color color;
    private int monedas;

    public Monedas(int posicionX, int posicionY, Font font, Color color, int monedas) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.font = font;
        this.color = color;
        this.monedas = monedas;
    }

    public void dibujarse(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Monedas: " + String.valueOf(monedas), posicionX=150, posicionY=580);
    }
	
    public void puntajeDibujarse(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Recolectaste " + String.valueOf(monedas) + "de 9" + " monedas", posicionX=300, posicionY=580);
    }

    public void ganarMonedas() {
    	monedas++;
    }
    
    public void reiniciarContadorDeMonedas() {
    	monedas = 0;
    }

    public int getMonedas() {
    	return monedas;
    }

}
