
import javax.swing.JFrame;


public class GameFrame extends JFrame{

	/**
	 Her laver jeg en GameFrame til spillet, som lave JfFrame ved GamePanel() og sætter titlen. Den extender JFrame.
	 */
	private static final long serialVersionUID = 1L;

	GameFrame(){
			
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
	}
	
}
