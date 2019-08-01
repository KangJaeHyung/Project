package DieselKJH;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class StorePage extends JPanel {
	private JScrollPane scrollpane;
	private JPanel panel;
	private JFrame j;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image plateImage = new ImageIcon(Main.class.getResource("../DieselImage/스토어 플레이트.png")).getImage();
	static ArrayList<Game_info> gamelist = new ArrayList<Game_info>();
	private JPanel[] gamePanel;

	StorePage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		addList();
		setPanel();
	}

	public void setPanel() {

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
			JLabel gameImage = new JLabel(gamelist.get(i).getGame_image());
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
					System.out.println(gamelist.get(a).getGame_name());
				}

			});
			gamePanel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			panel.add(gamePanel[i]);
		}
		scrollpane.requestFocusInWindow();
		scrollpane.getVerticalScrollBar().setUnitIncrement(13);
//		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public void addList() {
		gamelist.add(new Game_info("RJ000001", "고급 레스토랑", 20000, "19.07.31", "전체이용가", "음식점", "시공조아", "게임_스토어이미지1.png",
				"게임_대형이미지_히오스.png"));
		gamelist.add(new Game_info("RJ000002", "폭풍전야", 10000, "19.07.06", "15세이용가", "누가 하찮은지", "결과가 말해줌",
				"게임_스토어이미지2.png", "게임_대형이미지_킹든갓택.png"));
		gamelist.add(new Game_info("RJ000003", "고양이 마리오", 3000000, "17.06.06", "19세이용가", "마리오", "노약자 주의",
				"게임_스토어이미지3.png", "게임_대형이미지_고양이마리오.png"));
		gamelist.add(new Game_info("RJ000004", "배들 그라운드", 10000, "22.12.06", "19세이용가", "배전쟁", "몰라", "게임_스토어이미지4.png",
				"게임_대형이미지_월오쉽.png"));
		gamelist.add(
				new Game_info("RJ000005", "치즈 세븐", 10000, "19.07.01", "15세이용가", "그세븐", "치즈", "게임_스토어이미지5.png", "게임_대형이미지_킹든갓택.png"));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
	}

}
