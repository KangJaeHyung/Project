package DieselKJH;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MyPage extends JPanel {
	private JScrollPane scrollpane;
	private JPanel listPanel;
	private JFrame j;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image listImage = new ImageIcon(Main.class.getResource("../DieselImage/마이페이지_사이드레이아웃.png")).getImage();
	private JLabel[] gameLabel;
	private JLabel gameImageLabel;
	private JLabel gametitleLabel;
	MyPage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		setPanel();
	}

	public void setPanel() {
		listPanel = new JPanel();
		listPanel.setBackground(new Color(0, 0, 0, 0));
		listPanel.setLayout(null);
		int size = StorePage.gamelist.size();
		gametitleLabel=new JLabel(StorePage.gamelist.get(0).getGame_name());
		gametitleLabel.setBounds(100, 30, 590, 35);
		gametitleLabel.setForeground(Color.WHITE);
		gametitleLabel.setFont(new Font("Arian", Font.BOLD, 30 ));
		gametitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gameImageLabel= new JLabel(StorePage.gamelist.get(0).getGame_bigimage());
		gameImageLabel.setBounds(30,10,710,580);
		gameImageLabel.add(gametitleLabel);
		gameLabel = new JLabel[size];
		for (int i = 0; i < size; i++) {
			gameLabel[i] = new JLabel("  "+StorePage.gamelist.get(i).getGame_name());
			gameLabel[i].setBounds(10, 10+i*50, 210, 40);
			gameLabel[i].setOpaque(true);
			gameLabel[i].setBackground(new Color(100,100,100,113));
			gameLabel[i].setForeground(Color.WHITE);
			gameLabel[i].setFont(new Font("Arian", Font.BOLD, 20));
			gameLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			int a=i;
			gameLabel[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					gameImageLabel.setIcon(StorePage.gamelist.get(a).getGame_bigimage());
					gametitleLabel.setText(StorePage.gamelist.get(a).getGame_name());
				}
			});
			listPanel.add(gameLabel[i]);
		}
		
		scrollpane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(listImage, 0, 0, null);
			}
		};
		scrollpane.setBounds(744, 8, 250, 586);
		add(gameImageLabel);
		add(scrollpane);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
	}

}
