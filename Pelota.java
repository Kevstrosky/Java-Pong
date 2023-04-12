import javax.swing.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.util.Random;

public class Pelota extends JLabel implements Runnable {

	private String url1;
	private ImageIcon icon;
	private int posY = 389;
  private int posX = 389;
	JLabel background;
  int startDirection = (int)(Math.random() * 360); // dirección inicial aleatoria
  int screenWidth = 782;
  int screenHeight = 700;
  int direction = 400;
  int ballWidth = 20;
  int ballHeight = 20;
	private Rectangle barraBounds;
  private Rectangle barraDerechaBounds;
	private BarraIzquierda barra;
  private BarraDerecha barraDerecha;


	public Pelota(String url1, BarraIzquierda barra, BarraDerecha barraDerecha) {
		this.url1 = url1;
		icon = new ImageIcon(this.getClass().getResource(url1));
		setIcon(icon);
		this.barra = barra; // Almacenar la instancia de BarraIzquierda
    this.barraDerecha = barraDerecha; // Almacenar la instancia de BarraDerecha
	}
	public void run() {		
		while(true) {
			 moveImage(2, 4);
		} //end while
	} //end run
	
	private void moveImage(int power, int time) {
    while (true) {
        barraDerechaBounds = barraDerecha.getBounds(); // Obtener los bounds de la barra
        barraBounds = barra.getBounds(); // Obtener los bounds de la barra
        Rectangle pelotaBounds = this.getBounds(); // Obtener los bounds de la pelota

        // Comprobar si hay colisión con la barra
        if (pelotaBounds.intersects(barraBounds) || pelotaBounds.intersects(barraDerechaBounds)) {
            // Si la pelota toca la barra, cambiar la dirección horizontal de la pelota
            Random rand = new Random();
            boolean success = rand.nextBoolean();
            direction = 180 - direction;
            posX = Math.max(barraBounds.x + barraBounds.width, posX);
            if(success){
              power+=1;
            }else{
              power = 2;
              time = 4;
            }
        }

        // Calcular la nueva posición de la pelota con la dirección actual
        int newX = posX + (int)(power * Math.cos(Math.toRadians(direction)));
        int newY = posY + (int)(power * Math.sin(Math.toRadians(direction)));

        // Verificar si la pelota ha chocado contra el borde de la pantalla
        if (newX < 0 || newX + ballWidth > screenWidth) {
            // Cambiar la dirección horizontal de la pelota
            direction = 180 - direction;
            newX = Math.max(0, Math.min(screenWidth - ballWidth, newX));
        }
        if (newY < 100 || newY + ballHeight > screenHeight) {
            // Cambiar la dirección vertical de la pelota
            direction = 360 - direction;
            newY = Math.max(0, Math.min(screenHeight - ballHeight, newY));
        }

        // Actualizar la posición de la pelota
        posX = newX;
        posY = newY;
        setBounds(posX, posY, ballWidth, ballHeight);

        try {
            Thread.sleep(time); // pausa para dar tiempo a que se actualice la posición
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

	
}