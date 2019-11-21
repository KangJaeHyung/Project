package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class StorePage extends JPanel {
	static int gameNum;
	private JScrollPane scrollpane;
	private JPanel panel;
	private JFrame j;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image plateImage = new ImageIcon(Main.class.getResource("../DieselImage/스토어 플레이트.png")).getImage();

	private JPanel[] gamePanel;

	StorePage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);

		setScrollPanel();
		
	}

	public void setScrollPanel() {

		panel = new JPanel();
		scrollpane = new JScrollPane(panel);
		scrollpane.setBounds(5, 5, 990, 590);
		scrollpane.setBackground(new Color(48, 48, 48));
		UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(50, 50, 50)));
		UIManager.put("ScrollBar.track", new ColorUIResource(new Color(60, 60, 60)));
		UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(new Color(190, 87, 5)));
		UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(new Color(190, 87, 5)));
		UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(new Color(190, 87, 5)));
		scrollpane.getVerticalScrollBar().setUI(new BasicScrollBarUI());// 상하스크롤바에 적용
		add(scrollpane);

	}

	public void setPanel(ArrayList<Game_info> gamelist) {
		panel.removeAll();
		panel.setLayout(null);
		panel.setBackground(new Color(70, 70, 70));
		panel.setSize(990, 20 + 190 * gamelist.size());
		panel.setPreferredSize(new Dimension(970, 20 + 190 * gamelist.size()));
		gamePanel = new JPanel[gamelist.size() + 1];
		int listSize = gamelist.size();
		for (int i = 0; i < listSize; i++) {

			gamePanel[i] = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(plateImage, 0, 0, null);
				}
			};
			JLabel gamePrice = new JLabel("￦  " + gamelist.get(i).getGame_price());
			gamePrice.setFont(new Font("Arian", Font.BOLD, 30));
			gamePrice.setForeground(new Color(180, 193, 204));
			gamePrice.setBounds(730, 120, 200, 30);
			gamePrice.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel gameGenre = new JLabel(
					gamelist.get(i).getGame_main_genre() + " , " + gamelist.get(i).getGame_sub_genre());
			gameGenre.setFont(new Font("Arian", Font.BOLD, 20));
			gameGenre.setForeground(Color.gray);
			gameGenre.setBounds(315, 45, 500, 25);
			JLabel gameTitle = new JLabel(gamelist.get(i).getGame_name());
			gameTitle.setFont(new Font("Arian", Font.BOLD, 25));
			gameTitle.setForeground(new Color(180, 193, 204));
			gameTitle.setBounds(315, 15, 500, 30);
			System.out.println(gamelist.get(i).getGame_image_dir());
			JLabel gameImage = new JLabel(
					new ImageIcon(Main.class.getResource("../DieselImage/" + gamelist.get(i).getGame_image_dir())));
			gameImage.setBounds(5, 5, 290, 170);
			gamePanel[i].add(gamePrice);
			gamePanel[i].add(gameGenre);
			gamePanel[i].add(gameImage);
			gamePanel[i].add(gameTitle);
			gamePanel[i].setLayout(null);
			gamePanel[i].setBounds(15, 15 + i * 190, 950, 180);
			int a = i;
			gamePanel[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < DiselFrame.mylist.size(); i++) {
						if (DiselFrame.mylist.get(i).getGame_code().equals(gamelist.get(a).getGame_code())) {
							JOptionPane.showMessageDialog(null, "이미 가지고 있는 게임 입니다.");
							return;
						}
					}
					gameNum = a;
					DiselFrame.isMainPage = false;
//				DiselFrame.isSalesPage = false;
					DiselFrame.isMyPage = false;
					DiselFrame.isStorePage = false;
					DiselFrame.isAdminChange = false;
					DiselFrame.isPurchasePage = true;
					DiselFrame.isRefundPage = false;
					DiselFrame.isChangePage = false;
					DiselFrame.pp.setName();
					DiselFrame.pp.doName();
				

				}

			});
			gamePanel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			panel.add(gamePanel[i]);
		}
		scrollpane.requestFocusInWindow();
		scrollpane.getVerticalScrollBar().setUnitIncrement(13);
//		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public void findName(String str) {
		
		int listNum = DiselFrame.gamelist.size();
		ArrayList<Game_info> findlist = new ArrayList<Game_info>();
		for (int i = 0; i < listNum; i++) {
			if (DiselFrame.gamelist.get(i).getGame_name().contains(str)) {
				findlist.add(DiselFrame.gamelist.get(i));
			}
			;
		}
		setPanel(findlist);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
	}

}
