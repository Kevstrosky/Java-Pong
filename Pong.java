import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

public class Pong extends JFrame {

	public Pong() {
		initValues();
	}
	
	private void initValues() {
		Font font = null;
		JButton btnStart = new JButton("PLAY");
		JLabel background = new JLabel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("images/background.png"));
		try {
    		File fontFile = new File("fonts/pixelfont.otf");
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(24f);
			} catch (IOException | FontFormatException e) {
   		 e.printStackTrace();
		}

		background.setIcon(icon);
		
		BarraIzquierda izquierda = new BarraIzquierda("images/barra.png");
		BarraDerecha derecha = new BarraDerecha("images/barra.png");
		Pelota pelota = new Pelota("images/ball.png", izquierda, derecha);

		izquierda.background = background;
		derecha.background = background;
		pelota.background = background;
		
		
		izquierda.setFocusable(true);
		derecha.setFocusable(true);
		izquierda.setBounds(50,60,15,80);
		derecha.setBounds(750,60,15,80);

		btnStart.setFocusable(false);
		btnStart.setBounds(700,20,90,50);
		btnStart.setFont(font);
		btnStart.setForeground(new Color(42, 189, 244));
		btnStart.setBackground(Color.WHITE);
		pelota.setBounds(390,390,20,20);

		background.setBounds(0,0,800,800);

		btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
   		 public void mouseEntered(java.awt.event.MouseEvent evt) {
       	 btnStart.setBackground(new Color(42, 189, 244));
				btnStart.setForeground(Color.WHITE);

      	  btnStart.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
  		  }

   		 public void mouseExited(java.awt.event.MouseEvent evt) {
    	 btnStart.setBackground(Color.WHITE);
		 btnStart.setForeground(new Color(42, 189, 244));
   		 }
});
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Thread t = new Thread(izquierda);
				Thread t2 = new Thread(derecha);
				Thread t3 = new Thread(pelota);
				t.start();
				t2.start();
				t3.start();
			} //end actionPerformed
		};
		
		btnStart.addActionListener(listener);

		izquierda.addKeyListener(izquierda);

		
		add(izquierda);
		add(derecha);
		add(pelota);
		add(btnStart);
		add(background);
	
		setTitle("PING PONG BY KEVSTROSKY");
		setSize(816, 839);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
	} //end initValues

}