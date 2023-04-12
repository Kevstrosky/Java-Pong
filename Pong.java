import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl;



public class Pong extends JFrame {
	private Thread t;
	private Thread t2;
	private Thread t3;

	public Pong() {
		initValues();
	}

	public static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // para reproducir en loop
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
		}
	
	private void initValues() {
		String filePath = "music/music.wav";

		
		Font font = null;
		JButton btnStart = new JButton("PLAY");
		JButton btnPause = new JButton("PAUSE");
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
		BarraDerecha derecha = new BarraDerecha("images/barraderecha.png");
		Pelota pelota = new Pelota("images/ball.png", izquierda, derecha);

		izquierda.background = background;
		derecha.background = background;
		pelota.background = background;
		
		
		izquierda.setFocusable(true);
		derecha.setFocusable(true);
		izquierda.setBounds(60,60,15,80);
		derecha.setBounds(750,60,15,320);

		btnStart.setFocusable(false);
		btnStart.setBounds(700,20,90,50);
		btnStart.setFont(font);
		btnStart.setForeground(new Color(42, 189, 244));
		btnStart.setBackground(Color.WHITE);
		btnPause.setFocusable(false);
		btnPause.setBounds(560,20,120,50);
		btnPause.setFont(font);
		btnPause.setForeground(new Color(42, 189, 244));
		btnPause.setBackground(Color.WHITE);
		btnPause.setEnabled(false);
		pelota.setBounds(390,390,20,20);

		background.setBounds(0,0,800,800);

		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				playSound(filePath);
				btnStart.setEnabled(false);
				btnPause.setEnabled(true);
				t = new Thread(izquierda);
				t2 = new Thread(derecha);
				t3 = new Thread(pelota);
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
		add(btnPause);
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