package maybePass;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Utilidades {
    
    public static boolean hayColision(
            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
        boolean haySolapamientoEnX =  haySolapamientoDeRango(
                elemento1PosicionX,
                elemento1PosicionX+elemento1Ancho,
                elemento2PosicionX,
                elemento2PosicionX+elemento2Ancho);
        boolean haySolapamientoEnY = haySolapamientoDeRango(
                elemento1PosicionY,
                elemento1PosicionY+elemento1Largo,
                elemento2PosicionY,
                elemento2PosicionY+elemento2Largo);
    	if (haySolapamientoEnX &&  haySolapamientoEnY) {
            return true;
        }
        return false;
    }
    
    private static boolean haySolapamientoDeRango(int a, int b, int c, int d) {
        if (a < d && b > c  ) {
            return true;
        }
        return false;
    }
    
    public static boolean verificarColisionEnEjeY(
            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
        int diferenciaDeSolapamientoEnX =  CalcularDiferenciaDeSolapamiento(
                elemento1PosicionX,
                elemento1Ancho,
                elemento2PosicionX,
                elemento2Ancho);
        int diferenciaDeSolapamientoEnY = CalcularDiferenciaDeSolapamiento(
                elemento1PosicionY,
                elemento1Largo,
                elemento2PosicionY,
                elemento2Largo);
    	if (diferenciaDeSolapamientoEnX >  diferenciaDeSolapamientoEnY) {
            return true;     
        }
        return false;
    }
    
    private static int CalcularDiferenciaDeSolapamiento (int a, int b, int c, int d) {
    	if (a < c) {
    		return (a + b)-c;
    	} else {
    		if(( a + b ) >(c + d)) {
    			return (c + d) - a;
    		} else {
    			return b;
    		}
    	}
    }
    
    public static BufferedImage obtenerRuta(String pathRelativoDesdeResources) {
    	try {
    		return ImageIO.read(Utilidades.class.getResource(pathRelativoDesdeResources));
    	} catch (IOException e) {
    		e.printStackTrace();
    		System.exit(1);
    	}
    	return null;
    }
    
    public static Font cargarFuente() {
    	Font fuente = null;
    	String path = "src/main/resources/fuentes/lastninja.ttf";
    	try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(20f);	
			return fuente;
    	} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
