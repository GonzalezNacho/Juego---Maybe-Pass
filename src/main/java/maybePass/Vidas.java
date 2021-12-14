package maybePass;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Vidas implements Dibujable {

    private int posicionX;
    private int posicionY;
    private Font font;
    private Color color;
    private int vidas;

    public Vidas(int posicionX, int posicionY, Font font, Color color, int vidas) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.font = font;
        this.color = color;
        this.vidas = vidas;
    }

    public void dibujarse(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Vidas: " + String.valueOf(vidas), posicionX, posicionY);
    }
    
    public void puntajeDibujarse(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        if(vidas==5) {
        	g.drawString("No moriste!", posicionX, posicionY);
        }else if(vidas==4) {
        	g.drawString("Moriste 1 vez", posicionX, posicionY);
        }else {
        	g.drawString("Moriste " + String.valueOf(5-vidas) + " veces", posicionX, posicionY);
        }
    }

    public void perderVida() {
        vidas--;
    }

    public int getVidas() {
        return vidas;
    }

}
