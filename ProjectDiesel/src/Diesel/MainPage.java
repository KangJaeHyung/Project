package Diesel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Diesel.Main;


public class MainPage extends JPanel {
	private JFrame j;
	private JPanel cardPanel;
	private JPanel[] newsPanel;
	private CardLayout cardLayout = new CardLayout();
	private ImageIcon cardButtonImage = new ImageIcon(Main.class.getResource("../DieselImage/카드버튼_null.png"));
	private ImageIcon cardButtonImagePressed = new ImageIcon(Main.class.getResource("../DieselImage/카드버튼_on.png"));
	private ImageIcon leftImage = new ImageIcon(Main.class.getResource("../DieselImage/카드버튼_left.png"));
	private ImageIcon leftImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/카드버튼_left_반응.png"));
	private ImageIcon rightImage = new ImageIcon(Main.class.getResource("../DieselImage/카드버튼_right.png"));
	private ImageIcon rightImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/카드버튼_right_반응.png"));
	private ImageIcon news1 = new ImageIcon(Main.class.getResource("../DieselImage/광고1.png"));
	private ImageIcon news2= new ImageIcon(Main.class.getResource("../DieselImage/광고2.gif"));
	private ImageIcon news3= new ImageIcon(Main.class.getResource("../DieselImage/광고3.png"));
	private JButton left, right;
	private JButton[] cardButton;

	private JLabel[] news;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	public static Timer timer;
	public static TimerTask timerTask;
	MainPage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		setCardPanel();
		setCardButton();
		timer=new Timer();
		timerTask=new TimerTask() {
			@Override
			public void run() {
				cardLayout.next(cardPanel);
				if(cardButton[0].getIcon().equals(cardButtonImagePressed)) {
					cardButton[0].setIcon(cardButtonImage);
					cardButton[1].setIcon(cardButtonImagePressed);
				}else if(cardButton[1].getIcon().equals(cardButtonImagePressed)) {
					cardButton[1].setIcon(cardButtonImage);
					cardButton[2].setIcon(cardButtonImagePressed);
				}else if(cardButton[2].getIcon().equals(cardButtonImagePressed)) {
					cardButton[2].setIcon(cardButtonImage);
					cardButton[0].setIcon(cardButtonImagePressed);
				}
			}
		};
		timer.schedule(timerTask, 3000, 5000);
	}

	private int a;

	public void setCardButton() {
		left = new JButton();
		right = new JButton();
		
		
		left = new JButton(leftImage);
		left.setBorderPainted(false);
		left.setContentAreaFilled(false);
		left.setFocusPainted(false);
		left.setBounds(385, 545, leftImage.getIconWidth(), leftImage.getIconHeight());
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.previous(cardPanel);
				if(cardButton[0].getIcon().equals(cardButtonImagePressed)) {
					cardButton[0].setIcon(cardButtonImage);
					cardButton[2].setIcon(cardButtonImagePressed);
				}else if(cardButton[1].getIcon().equals(cardButtonImagePressed)) {
					cardButton[1].setIcon(cardButtonImage);
					cardButton[0].setIcon(cardButtonImagePressed);
				}else if(cardButton[2].getIcon().equals(cardButtonImagePressed)) {
					cardButton[2].setIcon(cardButtonImage);
					cardButton[1].setIcon(cardButtonImagePressed);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				left.setIcon(leftImage);
				left.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				left.setIcon(leftImageEntered);
				left.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(left);
		
		right = new JButton(rightImage);
		right.setBorderPainted(false);
		right.setContentAreaFilled(false);
		right.setFocusPainted(false);
		right.setBounds(585, 545, rightImage.getIconWidth(), rightImage.getIconHeight());
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.next(cardPanel);
				if(cardButton[0].getIcon().equals(cardButtonImagePressed)) {
					cardButton[0].setIcon(cardButtonImage);
					cardButton[1].setIcon(cardButtonImagePressed);
				}else if(cardButton[1].getIcon().equals(cardButtonImagePressed)) {
					cardButton[1].setIcon(cardButtonImage);
					cardButton[2].setIcon(cardButtonImagePressed);
				}else if(cardButton[2].getIcon().equals(cardButtonImagePressed)) {
					cardButton[2].setIcon(cardButtonImage);
					cardButton[0].setIcon(cardButtonImagePressed);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				right.setIcon(rightImage);
				right.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				right.setIcon(rightImageEntered);
				right.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(right);
		
		
		
		
		
		
		
		
		
		cardButton = new JButton[3];
		for (int i = 0; i < 3; i++) {
			cardButton[i] = new JButton(cardButtonImage);
			cardButton[i].setBorderPainted(false);
			cardButton[i].setContentAreaFilled(false);
			cardButton[i].setFocusPainted(false);
			cardButton[i].setBounds(425 + 50 * i, 540, cardButtonImage.getIconWidth(), cardButtonImage.getIconHeight());

		}
		cardButton[0].setIcon(cardButtonImagePressed);
		cardButton[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardButton[1].setIcon(cardButtonImage);
				cardButton[2].setIcon(cardButtonImage);
				cardButton[0].setIcon(cardButtonImagePressed);
				cardButton[0].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cardLayout.show(cardPanel, "news0");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cardButton[0].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (cardButton[0].getIcon().equals(cardButtonImage)) {
					cardButton[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

		});

		cardButton[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardButton[0].setIcon(cardButtonImage);
				cardButton[2].setIcon(cardButtonImage);
				cardButton[1].setIcon(cardButtonImagePressed);
				cardButton[1].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cardLayout.show(cardPanel, "news1");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cardButton[1].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (cardButton[1].getIcon().equals(cardButtonImage)) {
					cardButton[1].setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

		});
		cardButton[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardButton[1].setIcon(cardButtonImage);
				cardButton[0].setIcon(cardButtonImage);
				cardButton[2].setIcon(cardButtonImagePressed);
				cardButton[2].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cardLayout.show(cardPanel, "news2");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cardButton[2].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (cardButton[2].getIcon().equals(cardButtonImage)) {
					cardButton[2].setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

		});
		add(cardButton[0]);
		add(cardButton[1]);
		add(cardButton[2]);
	}

	public void setCardPanel() {
		cardPanel = new JPanel();
		cardPanel.setLayout(cardLayout);
		cardPanel.setBounds(50, 40, 900, 500);
		cardPanel.setBackground(Color.red);
		newsPanel = new JPanel[3];
		news = new JLabel[3];
		int panelLength = newsPanel.length;
		news[0] = new JLabel(news1);
		news[1] = new JLabel(news2);
		news[2] = new JLabel(news3);
		for (int i = 0; i < panelLength; i++) {
			newsPanel[i] = new JPanel();
			newsPanel[i].setLayout(null);
			news[i].setBounds(2, 2, 896, 496);
			news[i].setBackground(Color.BLACK);
			newsPanel[i].add(news[i]);
			cardPanel.add(newsPanel[i], "news" + i);
		}
		add(cardPanel);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
	}

}
