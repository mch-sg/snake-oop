//*****************************************
import java.awt.*;
import javax.swing.JFrame;  
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GamePanel extends JPanel implements ActionListener {

	/**
	 Jeg definerer variabler som skærmhøjden samt hastighed (delay) og kropStørrelse til slangen
	 */
	private static final long serialVersionUID = 1L;
	static final int SCREEN_WIDTH = 750; // skærmvidde // 1300
	static final int SCREEN_HEIGHT = 750; // skærmhøjde // 750
	static final int ENHED_STØRRELSE = (int) 47; // 50 // 35 // 40
	static final int SPIL_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(ENHED_STØRRELSE*ENHED_STØRRELSE);
	static final int DELAY = 100; // 175 // tiden // 80 // 100
	int x[] = new int[SPIL_UNITS]; // x af slange
	int y[] = new int[SPIL_UNITS]; // y af slange
	JButton resetKnap; // Genstartknappen til JButton
	int kropStørrelse = 3; // Kropsdele som slangen start med at have
	int æbleSpist; // Antal æbler, slangen spiser
	int appleX; // Æblens x pos
	int appleY; // Æblens y pos
	int apple2X; int apple2Y; int apple3X; int apple3Y; // duplikerede æbler
	
	char direction = 'R';
	boolean spilIgang = false; // spillet starter ikke med at køre, før start spil bliver kaldt
	boolean staart = false; // Gør, så slangen ikke bevæger sig før man rør en knap
	Timer timer; // En timer til spillet
	Random random;
	
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.WHITE); // 254,250,224
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startSpil();
	}
	
	// Spillet startes funktion
	public void startSpil() {
		newApple(); newApple2(); newApple3();
		spilIgang = true; // t
		timer = new Timer(DELAY,this);

		timer.start();
	}
	
	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		g.clearRect(0, 0, SCREEN_WIDTH * ENHED_STØRRELSE, SCREEN_HEIGHT);
		
		
		JLabel label = new JLabel("asd");
		label.setIcon(new ImageIcon("/SnakeGame/Billeder/apple.png"));
		JTextArea text = new JTextArea();
		text.setText("Add subject here...");
		
		JFrame frame = new JFrame("no image");
		ImageIcon image = new ImageIcon("C:\\Users\\Magnus\\eclipse-workspace\\SnakeGame\\Billeder\\image.png");
		JLabel imagelabel = new JLabel(image);
		frame.add(imagelabel);
	     

		
		if(spilIgang) {
			
			// Farver til spillet
			Color myColor = new Color(204,213,174); // 204,213,174 // rgb(246,157,63)
			Color txtColor = new Color(250, 122, 67); // 212,163,115
			Color txtlight = new Color(252, 169, 134); // 250,237,205
			g.setColor(myColor);
		//	icon = new ImageIcon("Billeder/apple.png");
			g.fillOval(appleX, appleY, ENHED_STØRRELSE, ENHED_STØRRELSE);
			g.fillOval(apple2X, apple2Y, ENHED_STØRRELSE, ENHED_STØRRELSE);
			g.fillOval(apple3X, apple3Y, ENHED_STØRRELSE, ENHED_STØRRELSE);
		
			
			for(int i = 0; i< kropStørrelse;i++) {
				if(i == 0) {
					g.setColor(txtColor);
					g.fillRect(x[i], y[i], ENHED_STØRRELSE, ENHED_STØRRELSE);
				}
				else {
					g.setColor(txtlight);
					//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], ENHED_STØRRELSE, ENHED_STØRRELSE);
				}			
			}
			
			g.setColor(txtColor);
			g.setFont( new Font("Arial",Font.PLAIN, 20)); // 40
			//FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+æbleSpist, (SCREEN_WIDTH - 200), g.getFont().getSize()); // SCREEN_WIDTH - metrics.stringWidth("Score: "+æbleSpist) 

		}
		else {
			// Hvis spilIgang er false, skal gameOver funktionen køres.
			gameOver(g);
		}
		
	}
	public void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/ENHED_STØRRELSE))*ENHED_STØRRELSE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/ENHED_STØRRELSE))*ENHED_STØRRELSE;
		
		if (x[0] == appleX && y[0] == appleY) {
			appleX = random.nextInt((int)(SCREEN_WIDTH/ENHED_STØRRELSE))*ENHED_STØRRELSE;
			appleY = random.nextInt((int)(SCREEN_HEIGHT/ENHED_STØRRELSE))*ENHED_STØRRELSE;
				}
	}
	public void newApple2(){ 
		apple2X = random.nextInt((int)(SCREEN_WIDTH/ENHED_STØRRELSE))*ENHED_STØRRELSE; 
		apple2Y = random.nextInt((int)(SCREEN_HEIGHT/ENHED_STØRRELSE))*ENHED_STØRRELSE; 
	}
	public void newApple3(){ 
		apple3X = random.nextInt((int)(SCREEN_WIDTH/ENHED_STØRRELSE))*ENHED_STØRRELSE; 
		apple3Y = random.nextInt((int)(SCREEN_HEIGHT/ENHED_STØRRELSE))*ENHED_STØRRELSE; 
	}
	
	// Gør at slangen kan bevæge sig med piltaster
	public void flyt(){
		for(int i = kropStørrelse;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		if (staart = true) {
		switch(direction) {
		case 'U': // UP
			y[0] = y[0] - ENHED_STØRRELSE; // y - ENHED_STØRRELSE
			break;
		case 'D': // DOWN
			y[0] = y[0] + ENHED_STØRRELSE; // y +
			break;
		case 'L': // LEFT
			x[0] = x[0] - ENHED_STØRRELSE; // x -
			break;
		case 'R': // RIGHT
			x[0] = x[0] + ENHED_STØRRELSE; // x +
			break;
		}
		}
		
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			kropStørrelse++;
			æbleSpist++;
			newApple();
		} else if((x[0] == apple2X) && (y[0] == apple2Y)) { kropStørrelse++; æbleSpist++; newApple2();
		} else if((x[0] == apple3X) && (y[0] == apple3Y)) { kropStørrelse++; æbleSpist++; newApple3();
		}
		
	}
	public void checkCollisions() {
		// Tjekker hvis hovedet kolliderer med kroppen
		for(int i = kropStørrelse;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				spilIgang = false;
			}
		}
		
		// Tjekker hvis hoved kolliderer med venstre side
		if(x[0] < 0) {
			spilIgang = false;
		}
		// Tjekker hvis hoved kolliderer med højre side
		if(x[0] > SCREEN_WIDTH) {
			spilIgang = false;
		}
		// Tjekker hvis hovedet kolliderer med toppen
		if(y[0] < 0) {
			spilIgang = false;
		}
		// Tjekker hvis hoved kolliderer med bunden
		if(y[0] > SCREEN_HEIGHT) {
			spilIgang = false;
		}
		
		if(!spilIgang) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		// Definerer farver
		Color txtColor = new Color(250, 122, 67); // 212,163,115
		g.setColor(txtColor);
		
		// Score tekst
		g.setFont( new Font("Arial",Font.BOLD, 33));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("SCORE: "+æbleSpist, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+æbleSpist))/2, SCREEN_HEIGHT/2 - 150);
		
		// Game Over tekst
		g.setFont( new Font("Arial",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2 - 25);
		
		// Retry tekst
		JButton resetKnap;
		resetKnap = new JButton();
		resetKnap.setText("Retry");
		resetKnap.setFont(new Font("Arial", Font.BOLD,50));
		resetKnap.setSize(300, 200);
		resetKnap.setBackground(new Color(255,255,255,0));
		resetKnap.setOpaque(false);
		resetKnap.setContentAreaFilled(false); 
		resetKnap.setBorderPainted(false);
		resetKnap.setForeground(txtColor);
		resetKnap.setLocation(SCREEN_WIDTH/2 - 150, SCREEN_HEIGHT/2 - 25);
		resetKnap.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			//    spilIgang = true;

				  setVisible(false);
			//	  g.dispose();
				  new GameFrame();
				  } 
				} );
		this.add(resetKnap);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(spilIgang && staart == true) {
			flyt();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	// Når man indtaster keys bevæger de sig faktisk i retningen.
	// Gør samt at man ikke kan dø ved at gå ind i sig selv
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {       
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				staart = true;
				break;
			case KeyEvent.VK_LEFT:
				staart = true;
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				staart = true;
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				staart = true;
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				staart = true;
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}


}