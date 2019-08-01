package Diesel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FindPage extends JPanel{
	JFrame j;
	Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	FindPage(DiselFrame j) {
		this.j = j;
		PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(PanelImage,0, 0, null);
	}
	
	
	
}
