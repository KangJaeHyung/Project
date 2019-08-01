package DieselKJH;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class DiselFrame extends JFrame {
	public static int pageNum;
	LoginPage lp;
	JoinPage jp;
	FindPage fp;
	MainPage mp;
	MyPage myp;
	StorePage sp;
	ButtonPanel bp;

	public static boolean isManagement = false;
	public static boolean isLoginPage = true;
	public static boolean isJoinPage = false;
	public static boolean isFindPage = false;
	public static boolean isMainPage = false;
	public static boolean isStorePage = false;
	public static boolean isButtonPage = false;
	public static boolean isMyPage = false;
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
		fp = new FindPage(this);
		mp = new MainPage(this);
		bp = new ButtonPanel();
		sp = new StorePage(this);
		myp = new MyPage(this);
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
					isFindPage = false;
					isJoinPage = false;
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
		// 회원가입 페이지 넣기
		jp.setBounds(40, 70, 1000, 600);
		jp.setLayout(null);
		add(jp);
		// 회원가입 페이지 넣기
		// 계정찾기 페이지 넣기
		fp.setBounds(40, 70, 1000, 600);
		fp.setLayout(null);
		add(fp);
		// 계정찾기 페이지 넣기
		// 메인 페이지 넣기
		mp.setBounds(40, 70, 1000, 600);
		mp.setLayout(null);
		add(mp);
		// 메인 페이지 넣기
		// 버튼패널 넣기
		bp.setBounds(1050, 70, 200, 600);
		bp.setLayout(null);
		add(bp);
		// 버튼패널 넣기 종료
		// 상점 페이지 넣기
		sp.setBounds(40, 70, 1000, 600);
		sp.setLayout(null);
		add(sp);
		// 상점 페이지 넣기
		// 마이 페이지 넣기
		myp.setBounds(40, 70, 1000, 600);
		myp.setLayout(null);
		add(myp);
		// 상점 페이지 넣기

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
		fp.setVisible(isFindPage);
		mp.setVisible(isMainPage);
		bp.setVisible(isButtonPage);
		sp.setVisible(isStorePage);
		myp.setVisible(isMyPage);
		if (isJoinPage || isFindPage) {
			g.drawImage(logoSero, 1070, 70, null);
		}
		this.repaint();
	}
}

class ButtonPanel extends JPanel {

	ImageIcon MP_basic = new ImageIcon(Main.class.getResource("../DieselImage/마이페이지.png"));
	ImageIcon MP_pressed = new ImageIcon(Main.class.getResource("../DieselImage/마이페이지_반응.png"));
	ImageIcon logOut_basic = new ImageIcon(Main.class.getResource("../DieselImage/로그아웃 일반.png"));
	ImageIcon logOut_pressed = new ImageIcon(Main.class.getResource("../DieselImage/로그아웃 반응.png"));
	ImageIcon Store_basic = new ImageIcon(Main.class.getResource("../DieselImage/버튼_스토어.png"));
	ImageIcon Store_pressed = new ImageIcon(Main.class.getResource("../DieselImage/버튼_스토어_반응.png"));
	ImageIcon Cash_basic = new ImageIcon(Main.class.getResource("../DieselImage/금액표기.png"));
	ImageIcon Cash_pressed = new ImageIcon(Main.class.getResource("../DieselImage/금액표기_반응.png"));
	ImageIcon Option_basic = new ImageIcon(Main.class.getResource("../DieselImage/옵션.png"));
	ImageIcon Option_pressed = new ImageIcon(Main.class.getResource("../DieselImage/옵션_반응.png"));
	ImageIcon Back_basic = new ImageIcon(Main.class.getResource("../DieselImage/뒤로가기 일반.png"));
	ImageIcon Back_pressed = new ImageIcon(Main.class.getResource("../DieselImage/뒤로가기 반응.png"));
	ImageIcon Admin_basic = new ImageIcon(Main.class.getResource("../DieselImage/관리자_일반.png"));
	ImageIcon Admin_pressed = new ImageIcon(Main.class.getResource("../DieselImage/관리자_반응.png"));
	JButton back_btn;
	JButton logOut_btn;
	JButton button_admin = new JButton(Admin_basic);

	ButtonPanel() {

		setLayout(null);
		button_admin = new JButton(Admin_basic);

		button_admin.setBounds(0, 0, 200, 120);
		add(button_admin);

		logOut_btn = new JButton(logOut_basic);
		logOut_btn.setBounds(0, 220, 90, 90);
		add(logOut_btn);
		logOut_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isLoginPage = true;
				DiselFrame.isButtonPage = false;

			}

			@Override
			public void mouseExited(MouseEvent e) {
				logOut_btn.setIcon(logOut_basic);
				logOut_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				logOut_btn.setIcon(logOut_pressed);
				logOut_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		back_btn = new JButton(Back_basic);
		back_btn.setBounds(0, 220, 90, 90);
		add(back_btn);
		back_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = true;
				DiselFrame.isMyPage = false;
				DiselFrame.isStorePage = false;

			}
			@Override
			public void mouseExited(MouseEvent e) {
				back_btn.setIcon(Back_basic);
				back_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				back_btn.setIcon(Back_pressed);
				back_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		JButton opt_btn = new JButton(Option_basic);
		opt_btn.setBounds(110, 220, 90, 90);
		add(opt_btn);
		opt_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				opt_btn.setIcon(Option_basic);
				opt_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				opt_btn.setIcon(Option_pressed);
				opt_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		JButton btn_store = new JButton(Store_basic);
		btn_store.setBounds(0, 320, 200, 100);
		add(btn_store);
		btn_store.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isMyPage = false;
				DiselFrame.isStorePage = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_store.setIcon(Store_basic);
				btn_store.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn_store.setIcon(Store_pressed);
				btn_store.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		JButton btn_cash = new JButton(Cash_basic);
		btn_cash.setBounds(0, 430, 200, 60);
		add(btn_cash);
		btn_cash.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				btn_cash.setIcon(Cash_basic);
				btn_cash.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn_cash.setIcon(Cash_pressed);
				btn_cash.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		JButton mp_btn = new JButton(MP_basic);
		mp_btn.setBounds(0, 500, 200, 100);
		add(mp_btn);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Main.class.getResource("/DieselImage/Logo.png")));
		label_1.setBounds(0, 130, 200, 80);
		add(label_1);
		mp_btn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isMyPage = true;
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				mp_btn.setIcon(MP_basic);
				mp_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mp_btn.setIcon(MP_pressed);
				mp_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
		button_admin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_admin.setIcon(Admin_basic);
				button_admin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button_admin.setIcon(Admin_pressed);
				button_admin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		button_admin.setVisible(DiselFrame.isManagement);
		logOut_btn.setVisible(DiselFrame.isMainPage);
		back_btn.setVisible(!DiselFrame.isMainPage);
		repaint();
	}
}// 버튼패널 내부 클래스 종료