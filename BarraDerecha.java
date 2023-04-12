import javax.swing.*;
import java.awt.event.*;

public class BarraDerecha extends JLabel implements Runnable {

	private String url1;
	private ImageIcon icon;
	private boolean changeImg = false;
	private int posY = 60;
	JLabel background;

	public BarraDerecha(String url1) {
		this.url1 = url1;
		icon = new ImageIcon(this.getClass().getResource(url1));
		setIcon(icon);
	}

	public void run() {		
		while(true) {
			 moveImage(2, 2);
		} //end while
	} //end run
	
	private void moveImage(int power, int time) {
    boolean isMovingUp = false; // bandera para saber si el objeto está moviéndose hacia arriba o hacia abajo
    
    while (true) { // ciclo infinito
        if (!isMovingUp && posY <= 654) { // movimiento hacia abajo
            posY += power;
            setBounds(750, posY, 15, 80);
        } else if (!isMovingUp && posY >= 654) { // cambio de dirección: empezar a moverse hacia arriba
            isMovingUp = true;
        } else if (isMovingUp && posY >= 60) { // movimiento hacia arriba
            posY -= power;
            setBounds(750, posY, 15, 80);
        } else { // cambio de dirección: empezar a moverse hacia abajo
            isMovingUp = false;
        }
        
        // Cambiar la imagen cada vez que se actualiza la posición
        
        try {
            Thread.sleep(time); // pausa para dar tiempo a que se actualice la posición
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end moveImage

	
}