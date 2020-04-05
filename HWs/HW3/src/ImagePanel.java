import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 * @author Ege Aydin
 * @author Onur Kirmizi
 * @author Denizhan Soydas
 * @author Ali Ozer
 * @author Sina Sahan
 * In this class, we draw the images acc. to the type of the object, (O or X)
 * 
 */
public class ImagePanel extends JPanel {
	BufferedImage image;
	Player symbol;
	Border border;

	public ImagePanel(int index) {
		try {
			image = ImageIO.read(new File("./src/blank.png"));
		} catch (IOException e) {
			try {
				image = ImageIO.read(new File("./blank.png"));
			} catch (IOException e1) {
				System.out.println("no image found");
			}
		}
		;

		this.setVisible(true);
		this.setSize(150, 150);
		border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.setBorder(border);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public void setXImage() {
		try {
			image = ImageIO.read(new File("./src/boldX.png"));
		} catch (IOException ex) {
			try {
				image = ImageIO.read(new File("./boldX.png"));
			} catch (IOException e1) {
				System.out.println("no image found");
			}
		}
		this.symbol = Player.X;
		this.revalidate();
	}

	public void setOImage() {
		try {
			image = ImageIO.read(new File("./src/boldO.png"));
		} catch (IOException ex) {
			try {
				image = ImageIO.read(new File("./boldO.png"));
			} catch (IOException e1) {
				System.out.println("no image found");
			}
		}
		this.symbol = Player.O;
		this.revalidate();
	}

	public void reSetImage() {
		if (symbol == Player.X) {
			try {
				image = ImageIO.read(new File("./src/normalX.png"));
			} catch (IOException e) {
				try {
					image = ImageIO.read(new File("./normalX.png"));
				} catch (IOException e1) {
					System.out.println("no image found");
				}
			}
			this.revalidate();
		} else if (symbol == Player.O){
			try {
				image = ImageIO.read(new File("./src/normalO.png"));
			} catch (IOException e) {
				try {
					image = ImageIO.read(new File("./normalO.png"));
				} catch (IOException e1) {
					System.out.println("no image found");
				}
			}
			this.revalidate();
		}
	}

}
