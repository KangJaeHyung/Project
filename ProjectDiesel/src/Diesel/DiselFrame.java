package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class DiselFrame extends JFrame {
	LoginPage lp;
	JoinPage jp;

	public static boolean isLoginPage = true;
	public static boolean isJoinPage = false;
	private Image screenImage;
	private Graphics screenGraphic;

	private Image background = new ImageIcon(Main.class.getResource("../DieselImage/Background.png")).getImage();

	private JLabel manuBar = new JLabel(new ImageIcon(Main.class.getResource("../DieselImage/ManuBar.png")));
	private ImageIcon exitImage = new ImageIcon(Main.class.getResource("../DieselImage/Exit.png"));
	private JButton exitButton = new JButton(exitImage);
	private ImageIcon miniImage = new ImageIcon(Main.class.getResource("../DieselImage/Minimize.png"));
	private JButton miniButton = new JButton(miniImage);
	private JLabel logoIcon = new JLabel(new ImageIcon(Main.class.getResource("../DieselImage/logo_Icon.png")));
	private Image logo = new ImageIcon(Main.class.getResource("../DieselImage/Diesel_logo.png")).getImage();
	private Image logoSero = new ImageIcon(Main.class.getResource("../DieselImage/로고_세로.png")).getImage();

	public int mouseX, mouseY;

	public DiselFrame() {
		lp = new LoginPage(this);
		jp = new JoinPage(this);
		setUndecorated(true);
		setTitle("DynamicBeat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		logoIcon.setBounds(2, 2, 26, 26);
		logoIcon.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					isLoginPage = true;
				}
				if (e.getClickCount() == 2) {
					System.exit(0);
				}
			}
		});
		add(logoIcon);

		exitButton.setBounds(1245, 2, 25, 25);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		manuBar.setBounds(0, 0, 1280, 30);
		manuBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		manuBar.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(manuBar);

		// 로그인 페이지 넣기
		lp.setBounds((Main.SCREEN_WIDTH - lp.loginBoxImage.getIconWidth()) / 2, 400, lp.loginBoxImage.getIconWidth(),
				lp.loginBoxImage.getIconHeight());
		lp.setLayout(null);
		add(lp);
		// 로그인페이지 넣기
		//회원가입 페이지 넣기
		jp.setBounds(40,70,1000,600);
		jp.setLayout(null);
		add(jp);
		//회원가입 페이지 넣기
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(logo, (1280 - 750) / 2, 150, null);
		paintComponents(g);
		lp.setVisible(isLoginPage);
		jp.setVisible(isJoinPage);
		if(isJoinPage) {
			g.drawImage(logoSero,1070, 70, null);
		}
		this.repaint();
	}
}
