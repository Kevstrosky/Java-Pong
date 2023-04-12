import javax.swing.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl;

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
  private int puntosIzquierda = 0;
  private int puntosDerecha = 0;

  public static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-6.0f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
		}

	public Pelota(String url1, BarraIzquierda barra, BarraDerecha barraDerecha) {
		this.url1 = url1;
    this.barra = barra;
    this.barraDerecha = barraDerecha;
		icon = new ImageIcon(this.getClass().getResource(url1));
		setIcon(icon);
	}
	public void run() {		
		while(true) {
			 moveImage(2, 4);
		} //end while
	} //end run
	
	private void moveImage(int power, int time) {
    String filePath = "music/pong.wav";
    while (true) {
        barraDerechaBounds = barraDerecha.getBounds();
        barraBounds = barra.getBounds(); 
        Rectangle pelotaBounds = this.getBounds(); 

        if (pelotaBounds.intersects(barraBounds) || pelotaBounds.intersects(barraDerechaBounds)) {
           
            playSound(filePath);
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
              playSound(filePath);
            // Cambiar la dirección horizontal de la pelota
            direction = 180 - direction;
            newX = Math.max(0, Math.min(screenWidth - ballWidth, newX));
        }
        if (newY < 100 || newY + ballHeight > screenHeight) {
              playSound(filePath);
            // Cambiar la dirección vertical de la pelota
            direction = 360 - direction;
            newY = Math.max(0, Math.min(screenHeight - ballHeight, newY));
        }

         if (posX == 1  && !barraBounds.contains(posX, posY)) {
            puntosIzquierda++;
            System.out.println("Puntos izquierda: " + puntosIzquierda);
        }

        // Verificar si la pelota ha alcanzado la coordenada X deseada a la derecha
        if (posX + ballWidth == 790 && !barraDerechaBounds.contains(posX + ballWidth, posY)) {
            puntosDerecha++;
            System.out.println("Puntos derecha: " + puntosDerecha);
        }

      
        posX = newX;
        posY = newY;
        setBounds(posX, posY, ballWidth, ballHeight);

        try {
            Thread.sleep(time);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

	
}