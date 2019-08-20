package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MyPage extends JPanel {

	static int MygameNum = 0;
	private JScrollPane scrollpane;
	private JPanel listPanel;
	private JFrame j;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image listImage = new ImageIcon(Main.class.getResource("../DieselImage/마이페이지_사이드레이아웃.png")).getImage();
	private JLabel[] gameLabel;
	private JLabel gameImageLabel;
	private JLabel gametitleLabel;
	private ImageIcon refundImage = new ImageIcon(Main.class.getResource("../DieselImage/환불.png"));
	private ImageIcon refundImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/환불_반응.png"));
	private ImageIcon startImage = new ImageIcon(Main.class.getResource("../DieselImage/버튼_플레이.png"));
	private ImageIcon startImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/버튼_플레이_반응.png"));
	private JButton refundGame;
	private JButton startGame;

	MyPage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);

	}

	public void setPanel() {
		removeAll();
		listPanel = new JPanel();
		listPanel.setBackground(new Color(0, 0, 0, 0));
		listPanel.setLayout(null);
		Integer size = DiselFrame.mylist.size();
		ArrayList<Game_info> myGameList = new ArrayList<Game_info>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < DiselFrame.gamelist.size(); j++) {
				if (DiselFrame.mylist.get(i).getGame_code().equals(DiselFrame.gamelist.get(j).getGame_code())) {
					myGameList.add(DiselFrame.gamelist.get(j));
				}
			}
		}
		gametitleLabel = new JLabel();
		try {
			gametitleLabel.setText(myGameList.get(0).getGame_name());
			gametitleLabel.setBounds(100, 30, 590, 35);
			gametitleLabel.setForeground(Color.WHITE);
			gametitleLabel.setFont(new Font("Arian", Font.BOLD, 30));
			gametitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			gameImageLabel = new JLabel(new ImageIcon(
					Main.class.getResource("../DieselImage/" + myGameList.get(0).getGame_bigimage_dir())));
			gameImageLabel.setBounds(30, 10, 710, 580);
			gameImageLabel.add(gametitleLabel);
			gameLabel = new JLabel[size];
			for (int i = 0; i < size; i++) {
				gameLabel[i] = new JLabel("          " + myGameList.get(i).getGame_name());
				gameLabel[i].setBounds(10, 10 + i * 60, 210, 50);
				gameLabel[i].setOpaque(true);
				gameLabel[i].setBackground(new Color(100, 100, 100, 113));
				gameLabel[i].setForeground(Color.WHITE);
				gameLabel[i].setFont(new Font("Arian", Font.BOLD, 20));
				gameLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				ImageIcon icon = new ImageIcon(Main.class.getResource("../DieselImage/"+myGameList.get(i).getGame_icon_dir()));
				JLabel iconlabel = new JLabel(icon);
				gameLabel[i].add(iconlabel);
				iconlabel.setBounds(5,5,40,40);
				int a = i;
				gameLabel[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						MygameNum = a;
						gameImageLabel.setIcon(new ImageIcon(
								Main.class.getResource("../DieselImage/" + myGameList.get(a).getGame_bigimage_dir())));
						gametitleLabel.setText(myGameList.get(a).getGame_name());
					}
				});
				listPanel.add(gameLabel[i]);

			}
			refundGame = new JButton(refundImage);
			refundGame.setBounds(50, 490, 100, 35);
			refundGame.setBorderPainted(false);
			refundGame.setContentAreaFilled(false);
			refundGame.setFocusPainted(false);
			refundGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					DiselFrame.isRefundPage = true;
					DiselFrame.isMyPage = false;
					DiselFrame.rp.setName();

				}

				@Override
				public void mouseExited(MouseEvent e) {
					refundGame.setIcon(refundImage);
					refundGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					refundGame.setIcon(refundImageEntered);
					refundGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});
			gameImageLabel.add(refundGame);

			startGame = new JButton(startImage);
			startGame.setBounds(580, 100, 100, 60);
			startGame.setBorderPainted(false);
			startGame.setContentAreaFilled(false);
			startGame.setFocusPainted(false);
			startGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (DiselFrame.mylist.get(MygameNum).getGame_code().equals("RJ000001")) {
						RhythmGame4.Main.game();
					}
					

				}

				@Override
				public void mouseExited(MouseEvent e) {
					startGame.setIcon(startImage);
					startGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					startGame.setIcon(startImageEntered);
					startGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});
			gameImageLabel.add(startGame);
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
			repaint();
		} catch (Exception e) {
			gametitleLabel.setText("게임이 없습니다.");
			gametitleLabel.setBounds(100, 30, 590, 35);
			gametitleLabel.setForeground(Color.WHITE);
			gametitleLabel.setFont(new Font("Arian", Font.BOLD, 30));
			gametitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			add(gametitleLabel);
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
	}

}
