import javax.swing.*;
import java.awt.event.*;

public class BarraIzquierda extends JLabel implements Runnable, KeyListener {

	private String url1;
	private ImageIcon icon;
	private boolean changeImg = false, runStatus = false, down = false, up = false, shift = false;
	private int posY = 60;
	JLabel background;

	public BarraIzquierda(String url1) {
		this.url1 = url1;
		icon = new ImageIcon(this.getClass().getResource(url1));
		setIcon(icon);
	}

	public void run() {
		runStatus = true;
		
		while(true) {
			System.out.println("while");
			if(down && shift) { moveImage(2, 2); }
			if(down) { moveImage(1, 2); }
			if(up && shift) { moveImageUp(2, 2); }
			if(up) { moveImageUp(1, 2); }
		} //end while
	} //end run
	
	private void moveImage(int power, int time) {
		setIcon(icon);
		if(posY <= 614) {
			posY += power;
			setBounds(60,posY,15,80);
		}	
				try { Thread.sleep(time); } catch(Exception e) {}
	} //end moveImage

		private void moveImageUp(int power, int time) {
		
		if(posY >= 99) {
			posY -= power;
			setBounds(60,posY,15,80);
		}	

		try { Thread.sleep(time); } catch(Exception e) {}
	} //end moveImage
	
	public void keyTyped(KeyEvent ke) {}
	
	public void keyPressed(KeyEvent ke) {
		if(runStatus) {
			if(ke.getKeyCode() == KeyEvent.VK_UP) { up = true; }
			if(ke.getKeyCode() == KeyEvent.VK_DOWN) { down = true; }
			if(ke.getKeyCode() == KeyEvent.VK_SHIFT) { shift = true; }
		}
	} //end keyPressed
	
	public void keyReleased(KeyEvent ke) {
		if(runStatus) {
			if(ke.getKeyCode() == KeyEvent.VK_UP) { up = false; }
			if(ke.getKeyCode() == KeyEvent.VK_DOWN) { down = false; }
			if(ke.getKeyCode() == KeyEvent.VK_SHIFT) { shift = false; }
		}
	}

}