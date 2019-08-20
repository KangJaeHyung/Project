package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DiselFrame extends JFrame {
	public static int pageNum;

	public static LoginPage lp;
	public static JoinPage jp;
	public static FindPage fp;
	public static MainPage mp;
	public static MyPage myp;
	public static ChangePage cp;
	public static RefundPage rp;
	public static AdminChange ac;
	public static PurchasePage pp;
	public static Sales_manager sm;
	public static StorePage sp;
	public static ButtonPanel bp;
	public static Recharge re;
	public static RefundCash ref;
	public static Customer_info loginCustomer;
	public static AdminBtnPanel abp;
	public static ProductManager pm;
	public static UserManager um;
	public static SalesTable st;

	static List<MyList> mylist;
	static ArrayList<Game_info> gamelist = new ArrayList<Game_info>();

//	SalesPage sap;
	public static boolean isManagement = false;
	public static boolean isLoginPage = true;
	public static boolean isJoinPage = false;
	public static boolean isFindPage = false;
	public static boolean isMainPage = false;
	public static boolean isStorePage = false;
	public static boolean isButtonPage = false;
	public static boolean isMyPage = false;
//	public static boolean isSalesPage = false;
	public static boolean isChangePage = false;
	public static boolean isRefundPage = false;
	public static boolean isPurchasePage = false;
	public static boolean isAdminChange = false;
	public static boolean isRecharge = false;
	public static boolean isRefundCash = false;
	public static boolean isAdminBtnPage = false;
	public static boolean isProductManagerPage = false;
	public static boolean isUserManagerPage = false;
	public static boolean isSalesManagerPage = false;
	public static boolean isSalesTablePage = false;

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
		Game_info gi1 = null;
		CrudProcess crud = new CrudProcess();
		gamelist = (ArrayList<Game_info>) crud.selectGameInfo();
		re = new Recharge(this);
		ref = new RefundCash(this);
		lp = new LoginPage(this);
		jp = new JoinPage(this);
		fp = new FindPage(this);
		mp = new MainPage(this);
		bp = new ButtonPanel(this);
		sp = new StorePage(this);
		myp = new MyPage(this);
		cp = new ChangePage(this);
		rp = new RefundPage(this);
		pp = new PurchasePage(this);
		ac = new AdminChange(this);
		abp = new AdminBtnPanel(this);
		pm = new ProductManager(this);
		sm = new Sales_manager(this);
		um = new UserManager(this);
		st = new SalesTable(this);
//		sap = new SalesPage(this);
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
		cp.setBounds(40, 70, 1000, 600);
		cp.setLayout(null);
		add(cp);
//		환불 페이지 넣기
		rp.setBounds(40, 70, 1000, 600);
		rp.setLayout(null);
		add(rp);
//		구매 페이지 넣기
		pp.setBounds(40, 70, 1000, 600);
		pp.setLayout(null);
		add(pp);
//		관리자변경페이지
		ac.setBounds(40, 70, 1000, 600);
		ac.setLayout(null);
		add(ac);
		// 상점 페이지 넣기
		// 매출 페이지 넣기
//		sap.setBounds(40, 70, 1000, 600);
//		sap.setLayout(null);
//		add(sap);
		// 매출 페이지 넣기
		// 충전 페이지 넣기
		re.setBounds(40, 70, 1000, 600);
		re.setLayout(null);
		add(re);
		// 충전 페이지 넣기
		// 환불 페이지 넣기
		ref.setBounds(40, 70, 1000, 600);
		ref.setLayout(null);
		add(ref);
		// 상점 페이지 넣기

		abp.setBounds(1050, 70, 200, 600);
		abp.setLayout(null);
		add(abp);
		// 관리자 버튼 넣기 종료

		pm.setBounds(40, 70, 1000, 600);
		pm.setLayout(null);
		add(pm);

		um.setBounds(40, 70, 1000, 600);
		um.setLayout(null);
		add(um);
		sm.setBounds(40, 70, 1000, 600);
		sm.setLayout(null);
		add(sm);

		st.setBounds(40, 70, 1000, 600);
		st.setLayout(null);
		add(st);

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
		cp.setVisible(isChangePage);
		rp.setVisible(isRefundPage);
		pp.setVisible(isPurchasePage);
		ac.setVisible(isAdminChange);
		re.setVisible(isRecharge);
		ref.setVisible(isRefundCash);
//		sap.setVisible(isSalesPage);
		abp.setVisible(isAdminBtnPage);
		pm.setVisible(isProductManagerPage);
		um.setVisible(isUserManagerPage);
		sm.setVisible(isSalesManagerPage);
		st.setVisible(isSalesTablePage);
		if (isJoinPage || isFindPage) {
			g.drawImage(logoSero, 1070, 70, null);
		}
		repaint();

	}
}

class ButtonPanel extends JPanel {
	JFrame j;
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
	ImageIcon logoIcon = new ImageIcon(Main.class.getResource("/DieselImage/Logo.png"));
	Image textFieldImage = new ImageIcon(Main.class.getResource("/DieselImage/TextField.png")).getImage();
	ImageIcon findImage = new ImageIcon(Main.class.getResource("../DieselImage/검색확인_검색.png"));
	ImageIcon findImagePressed = new ImageIcon(Main.class.getResource("../DieselImage/검색확인_검색_반응.png"));
	JButton Find_btn;
	JButton back_btn;
	JButton logOut_btn;
	JButton button_admin = new JButton(Admin_basic);
	JTextField storeFind;
	JLabel label_1 = new JLabel("");
	JButton btn_cash;
	JLabel Cash_Label;

	ButtonPanel(DiselFrame j) {
		this.j = j;
		label_1.setIcon(logoIcon);
		label_1.setBounds(0, 130, 200, 80);
		add(label_1);

		Find_btn = new JButton(findImage);
		Find_btn.setBounds(163 + 5, 165, 30, 30);
		Find_btn.setBorderPainted(false);
		Find_btn.setContentAreaFilled(false);
		Find_btn.setFocusPainted(false);

		Find_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (storeFind.getText().equals("검색") && storeFind.getForeground() == Color.gray) {
					DiselFrame.sp.findName("");
				} else {
					DiselFrame.sp.findName(storeFind.getText());
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				Find_btn.setIcon(findImage);
				Find_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				Find_btn.setIcon(findImagePressed);
				Find_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		add(Find_btn);

		textFieldImage = textFieldImage.getScaledInstance(160, 40, Image.SCALE_SMOOTH);
		storeFind = new JTextField("검색");
		storeFind.setBorder(null);
		storeFind.setOpaque(false);
		storeFind.setForeground(Color.gray);
		storeFind.setFont(new Font("arian", Font.BOLD, 30));
		storeFind.setBounds(7, 166, 156, 26);
		storeFind.setCaretColor(Color.white);
		storeFind.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (storeFind.getText().equals("")) {
					storeFind.setForeground(Color.gray);
					storeFind.setText("검색");

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (storeFind.getText().equals("검색") && storeFind.getForeground() == Color.gray) {
					storeFind.setText("");
					storeFind.setForeground(Color.white);
				}

			}
		});
		storeFind.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (storeFind.getText().equals("검색") && storeFind.getForeground() == Color.gray) {
						DiselFrame.sp.findName("");
					} else {
						DiselFrame.sp.findName(storeFind.getText());
					}

				}
			}
		});
		add(storeFind);

		setLayout(null);
		button_admin = new JButton(Admin_basic);
		button_admin.setBounds(0, 0, 200, 120);
		button_admin.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isButtonPage = false;
				DiselFrame.isAdminBtnPage = true;
				DiselFrame.isAdminChange = false;
				DiselFrame.isChangePage = false;
				DiselFrame.isMyPage = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRecharge = false;
				DiselFrame.isRefundCash = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isLoginPage = false;
				DiselFrame.isSalesManagerPage = true;
				DiselFrame.sm.setLabel();

			}

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
		add(button_admin);

		logOut_btn = new JButton(logOut_basic);
		logOut_btn.setBounds(0, 220, 90, 90);
		add(logOut_btn);
		logOut_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					return;
				} else if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "다음에 또 와주세요.");
					DiselFrame.isMainPage = false;
					DiselFrame.isAdminChange = false;
					DiselFrame.isButtonPage = false;
					DiselFrame.isChangePage = false;
					DiselFrame.isMyPage = false;
					DiselFrame.isPurchasePage = false;
					DiselFrame.isRecharge = false;
					DiselFrame.isRefundCash = false;
					DiselFrame.isRefundPage = false;
					DiselFrame.isStorePage = false;
					DiselFrame.isLoginPage = true;
					DiselFrame.loginCustomer = null;
					DiselFrame.mylist = null;
					DiselFrame.isManagement = false;
				}

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
				DiselFrame.isAdminChange = false;
				DiselFrame.isChangePage = false;
				DiselFrame.isMyPage = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRecharge = false;
				DiselFrame.isRefundCash = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isLoginPage = false;
				DiselFrame.isSalesManagerPage = false;

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
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isAdminChange = false;
				DiselFrame.isChangePage = true;
				DiselFrame.isMyPage = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRecharge = false;
				DiselFrame.isRefundCash = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isLoginPage = false;
				DiselFrame.isSalesManagerPage = false;
				DiselFrame.cp.setTextContents();

			}

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
				DiselFrame.isAdminChange = false;
				DiselFrame.isChangePage = false;
				DiselFrame.isMyPage = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRecharge = false;
				DiselFrame.isRefundCash = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isStorePage = true;
				DiselFrame.isLoginPage = false;
				DiselFrame.isSalesManagerPage = false;
				CrudProcess crud = new CrudProcess();
				DiselFrame.gamelist = (ArrayList<Game_info>) crud.selectGameInfo();
				DiselFrame.sp.setPanel(DiselFrame.gamelist);

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

		btn_cash = new JButton(Cash_basic);
		btn_cash.setBounds(0, 430, 200, 60);

		add(btn_cash);
		btn_cash.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isAdminChange = false;
				DiselFrame.isChangePage = false;
				DiselFrame.isMyPage = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRecharge = true;
				DiselFrame.isRefundCash = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isLoginPage = false;
				DiselFrame.isSalesManagerPage = false;

			}

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

		Cash_Label = new JLabel();
		Cash_Label.setForeground(Color.white);
		Cash_Label.setFont(new Font("arian", Font.BOLD, 20));
		Cash_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_cash.add(Cash_Label);

		JButton mp_btn = new JButton(MP_basic);
		mp_btn.setBounds(0, 500, 200, 100);
		add(mp_btn);

		mp_btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isAdminChange = false;
				DiselFrame.isChangePage = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRecharge = false;
				DiselFrame.isRefundCash = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isLoginPage = false;
				DiselFrame.isSalesManagerPage = false;
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

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		button_admin.setVisible(DiselFrame.isManagement);
		logOut_btn.setVisible(DiselFrame.isMainPage);
		back_btn.setVisible(!DiselFrame.isMainPage);
		label_1.setVisible(!DiselFrame.isStorePage);
		storeFind.setVisible(DiselFrame.isStorePage);
		Find_btn.setVisible(DiselFrame.isStorePage);
		if (DiselFrame.isStorePage) {
			g.drawImage(textFieldImage, 5, 160, null);
		}
		repaint();
	}
}

class AdminBtnPanel extends JPanel {
	JFrame j;
	ImageIcon sale_basic = new ImageIcon(Main.class.getResource("../resource/관리자_매출관리.png"));
	ImageIcon sale_pressed = new ImageIcon(Main.class.getResource("../resource/관리자_매출관리_반응.png"));
	ImageIcon user_basic = new ImageIcon(Main.class.getResource("../resource/관리자_유저관리.png"));
	ImageIcon user_pressed = new ImageIcon(Main.class.getResource("../resource/관리자_유저관리_반응.png"));
	ImageIcon prod_basic = new ImageIcon(Main.class.getResource("../resource/관리자_상품관리.png"));
	ImageIcon prod_pressed = new ImageIcon(Main.class.getResource("../resource/관리자_상품관리_반응.png"));
	ImageIcon Back_basic = new ImageIcon(Main.class.getResource("../resource/뒤로가기 일반.png"));
	ImageIcon Back_pressed = new ImageIcon(Main.class.getResource("../resource/뒤로가기 반응.png"));

	AdminBtnPanel(DiselFrame j) {
		this.j = j;
		setBackground(new Color(0, 0, 0, 0));
		JButton sale_manag = new JButton(sale_basic);
		sale_manag.setBounds(0, 180, 200, 100);
		add(sale_manag);
		sale_manag.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				DiselFrame.isProductManagerPage = false;
				DiselFrame.isUserManagerPage = false;
				DiselFrame.isSalesManagerPage = false;
				DiselFrame.isSalesTablePage = true;
				DiselFrame.st.tableReset();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sale_manag.setIcon(sale_basic);
				sale_manag.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				sale_manag.setIcon(sale_pressed);
				sale_manag.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		JButton prod_manag = new JButton(prod_basic);
		prod_manag.setBounds(0, 290, 200, 100);
		add(prod_manag);
		prod_manag.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				DiselFrame.isProductManagerPage = true;
				DiselFrame.isUserManagerPage = false;
				DiselFrame.isSalesManagerPage = false;

//					public static boolean isManagement = false;
//					public static boolean isLoginPage = true;
//					public static boolean isJoinPage = false;
//					public static boolean isFindPage = false;
//					public static boolean isMainPage = false;
//					public static boolean isStorePage = false;
//					public static boolean isButtonPage = false;
//					public static boolean isMyPage = false;
//					public static boolean isAdminPage = false;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				prod_manag.setIcon(prod_basic);
				prod_manag.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				prod_manag.setIcon(prod_pressed);
				prod_manag.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		JButton user_manag = new JButton(user_basic);
		user_manag.setBounds(0, 70, 200, 100);
		add(user_manag);
		user_manag.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				DiselFrame.isProductManagerPage = false;
				DiselFrame.isUserManagerPage = true;
				DiselFrame.isSalesManagerPage = false;

//					public static boolean isManagement = false;
//					public static boolean isLoginPage = true;
//					public static boolean isJoinPage = false;
//					public static boolean isFindPage = false;
//					public static boolean isMainPage = false;
//					public static boolean isStorePage = false;
//					public static boolean isButtonPage = false;
//					public static boolean isMyPage = false;
//					public static boolean isAdminPage = false;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				user_manag.setIcon(user_basic);
				user_manag.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				user_manag.setIcon(user_pressed);
				user_manag.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});

		JButton ad_btnback = new JButton(Back_basic);
		ad_btnback.setBounds(110, 510, 90, 90);
		add(ad_btnback);
		ad_btnback.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (DiselFrame.isSalesManagerPage == true) {
					DiselFrame.isMainPage = true;
					DiselFrame.isMyPage = false;
					DiselFrame.isStorePage = false;
					DiselFrame.isAdminBtnPage = false;
					DiselFrame.isButtonPage = true;
					DiselFrame.isUserManagerPage = false;
					DiselFrame.isSalesManagerPage = false;
				}
				if (DiselFrame.isUserManagerPage == true || DiselFrame.isProductManagerPage == true
						|| DiselFrame.isSalesTablePage == true) {
					DiselFrame.isUserManagerPage = false;
					DiselFrame.isProductManagerPage = false;
					DiselFrame.isSalesTablePage = false;
					DiselFrame.isSalesManagerPage = true;

//					public static boolean isManagement = false;
//					public static boolean isLoginPage = true;
//					public static boolean isJoinPage = false;
//					public static boolean isFindPage = false;
//					public static boolean isMainPage = false;
//					public static boolean isStorePage = false;
//					public static boolean isButtonPage = false;
//					public static boolean isMyPage = false;
//					public static boolean isAdminPage = false;
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				ad_btnback.setIcon(Back_basic);
				ad_btnback.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ad_btnback.setIcon(Back_pressed);
				ad_btnback.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
	}
}
