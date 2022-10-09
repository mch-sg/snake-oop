

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import com.importimage.GamePanel.DisplayImage;

/**
 * @author elias.shaik
 *
 */
public class ImportImage extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DisplayImage panel;

    public ImportImage() throws IOException, InterruptedException {   
        this.setSize(new Dimension(1500, 1500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new DisplayImage();

        this.add(panel);

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ImportImage iI = new ImportImage();
    }
}