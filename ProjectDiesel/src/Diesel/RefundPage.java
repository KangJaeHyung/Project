package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicTextAreaUI;

public class RefundPage extends JPanel {
	private JCheckBox directInput;
	private JComboBox selection;
	private String[] selectList = { "인증방법을 선택해주세요", "이메일", "전화번호" };
	private JButton confirmbtn, cancelbtn;
	private ImageIcon icon1, icon2, icon3, icon4;
	private ImageIcon way1, way2, way3, way4;
	private JScrollPane returnPolicy;
	private JLabel game_title, game_count, game_price, purchase_confirm;
	private JLabel result1, result2, bangbub, yakguan;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image resultImage = new ImageIcon(Main.class.getResource("../resource/영수증.png")).getImage();
	private Image nameLabelImage = new ImageIcon(Main.class.getResource("../resource/네임라벨_200X50.png")).getImage();
	private Image nameLabelImage2 = new ImageIcon(Main.class.getResource("../resource/네임라벨_200X50.png")).getImage();
	private Image nameLabelImage3 = new ImageIcon(Main.class.getResource("../resource/네임라벨_200X50.png")).getImage();
	private Image NamePlate = new ImageIcon(Main.class.getResource("../resource/네임플레이트.png")).getImage();
	private Font font, font2, font3;
	private JFrame j;
	private JButton[] btn = new JButton[4];
	private int index;
	private String[] auth;
	private int random;
	private int gameNum;
	private JCheckBox checkbox;

	private Game_info game_info;
	int x, y;

	RefundPage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		resultImage = resultImage.getScaledInstance(350, 500, Image.SCALE_SMOOTH);
		nameLabelImage = nameLabelImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		nameLabelImage2 = nameLabelImage2.getScaledInstance(307, 35, Image.SCALE_SMOOTH);
		nameLabelImage3 = nameLabelImage3.getScaledInstance(428, 205, Image.SCALE_SMOOTH);
		NamePlate = NamePlate.getScaledInstance(250, 50, Image.SCALE_SMOOTH);

		font = new Font("휴먼모음T", Font.BOLD, 30);
		font2 = new Font("휴먼모음T", Font.BOLD, 20);
		font3 = new Font("휴먼모음T", Font.BOLD, 15);
		y = y - 30;
		setLabel();
		setButton();
		setScroll();
		setCheckbox();
	}

	public void setCheckbox() {
		checkbox = new JCheckBox("위 사항을 숙지하였으며 대충 뭐 이상의 내용에 동의합니다");
		checkbox.setBounds(x+100, y+480, 500, 30);
		checkbox.setFont(font3);
		checkbox.setForeground(Color.WHITE);
		checkbox.setBorder(null);
		checkbox.setOpaque(false);
		add(checkbox);
	}
	//캐시 환불 시작
	public void doCustomerInfo() {
		CrudProcess crud = new CrudProcess();
		int price = DiselFrame.gamelist.get(gameNum).getGame_price();
		String user_id=DiselFrame.loginCustomer.getUser_id();
		crud.updateCash(price, user_id);
	}
	//캐시환불 끝

	//매출관리db수정시작
	public void doUpdateSalesInfo() {
		CrudProcess crud = new CrudProcess();
		String game_code = DiselFrame.gamelist.get(gameNum).getGame_code();
		Sales_info s = new Sales_info();
		s.setGame_code(game_code);
		crud.updateSalesInfo2(s);
	}
	//매출관리db수정끝
	
	//게임리스트에서 삭제시작
	public void doDeleteMylist() {
		CrudProcess crud = new CrudProcess();
		String user_id = DiselFrame.loginCustomer.getUser_id();
		String game_code = DiselFrame.gamelist.get(gameNum).getGame_code();
		MyList m = new MyList();
		m.setGame_code(game_code);
		m.setUser_id(user_id);
		crud.deleteMyList(m);
		HashMap<String, Object> map = new HashMap();
		map.put("user_id",DiselFrame.loginCustomer.getUser_id());
		DiselFrame.mylist = crud.selectGetGameInfo(map);
	}
	//게임리스트에서 삭제끝
	
	//게임환불db에 삽입시작
	public void doInsertGameRefund() {
		CrudProcess crud = new CrudProcess();
		String user_id = DiselFrame.loginCustomer.getUser_id();
		String game_code = DiselFrame.gamelist.get(gameNum).getGame_code();
		Game_sale gs = new Game_sale();
		gs.setGame_code(game_code);
		gs.setUser_id(user_id);
		gs.setIsRefunded(0);
		gs =crud.selectgameSale(gs);
		String goc= gs.getGame_order_code();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		Date date = new Date();
		Game_refund gr= new Game_refund();
		gr.setRefund_date(simple.format(date));
		gr.setGame_order_code(goc);
		crud.insertGameRefund(gr);
		crud.updateIsRefunded(gs);
	}
	//게임환불db에 삽입끝

	
//	public void doMyListAndSalesInfo() {
//		CrudProcess crud = new CrudProcess();
//		//게임 구매 db에 자료 넣을 준비 
//		Game_sale g = new Game_sale();
//		SimpleDateFormat simple = new SimpleDateFormat("yy/MM/dd/HH:mm:ss");
//		Date date = new Date();
//		String gameCode = crud.selectGameOrderCode();
//		int number= 0;
//		if (gameCode != null) {
//			String[] codeSplit = gameCode.split("C");
//			number = Integer.parseInt(codeSplit[1]);
//		}		
//		number = number + 1;
//		String format = String.format("%05d", number);
//		g.setGame_order_code("C"+format);
//		g.setUser_id(DiselFrame.loginCustomer.getUser_id());
//		g.setPayment(DiselFrame.gamelist.get(StorePage.gameNum).getGame_price());
//		g.setOrder_date(simple.format(date));
//		g.setGame_code(DiselFrame.gamelist.get(StorePage.gameNum).getGame_code());
//		//게임 구매 db에 자료넣을 준비 끝
//		crud.insertGameSale(g);
//	}
//	public void doGameSaledb() {
//		
//		CrudProcess crud = new CrudProcess();
//		//게임 리스트db,매출관리db에 자료넣을 준비
//	
//		int price = DiselFrame.gamelist.get(StorePage.gameNum).getGame_price();
//		String game_name = DiselFrame.gamelist.get(StorePage.gameNum).getGame_name();
//		String game_code = DiselFrame.gamelist.get(StorePage.gameNum).getGame_code();
//		String user_id = DiselFrame.loginCustomer.getUser_id();
//		//게임 리스트db,매출관리db에 자료넣을 준비끝
//		MyList m = new MyList();
//		m.setUser_id(user_id);
//		m.setGame_code(game_code);
//		Sales_info s = new Sales_info();
//		s.setGame_code(game_code);
//		crud.insertGameList(m);
//		crud.updateSalesInfo(s);
//		HashMap<String, Object> map = new HashMap();
//		map.put("user_id",DiselFrame.loginCustomer.getUser_id());
//		DiselFrame.mylist = crud.selectGetGameInfo(map);
//	}
	

	public void setButton() {

		// 이미지 아이콘 선언 시작
		icon1 = new ImageIcon(RefundPage.class.getResource("../resource/확인버튼_일반.png"));
		icon2 = new ImageIcon(RefundPage.class.getResource("../resource/취소버튼_일반.png"));
		icon3 = new ImageIcon(RefundPage.class.getResource("../resource/확인버튼_반응.png"));
		icon4 = new ImageIcon(RefundPage.class.getResource("../resource/취소버튼_반응.png"));
		way1 = new ImageIcon(RefundPage.class.getResource("../resource/결제1.png"));
		way2 = new ImageIcon(RefundPage.class.getResource("../resource/결제2.png"));
		way3 = new ImageIcon(RefundPage.class.getResource("../resource/결제1_반응.png"));
		way4 = new ImageIcon(RefundPage.class.getResource("../resource/결제2_반응.png"));
		// 이미지 아이콘 선언 끝

		// 환불방법 버튼 생성 시작
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton();
			btn[i].setBorderPainted(false);
			btn[i].setContentAreaFilled(false);
			btn[i].setFocusPainted(false);

		}
		btn[0].setIcon(way1);
		btn[1].setIcon(way2);
		btn[2].setIcon(way2);
		btn[3].setIcon(way2);
		btn[0].addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				btn[0].setIcon(way3);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn[0].setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btn[0].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		btn[0].setBounds(x + 100, y + 100, 200, 50);
		btn[1].setBounds(x + 320, y + 100, 200, 50);
		btn[2].setBounds(x + 100, y + 170, 200, 50);
		btn[3].setBounds(x + 320, y + 170, 200, 50);
		add(btn[0]);
		add(btn[1]);
		add(btn[2]);
		add(btn[3]);

		confirmbtn = new JButton(icon1);
		cancelbtn = new JButton(icon2);
		confirmbtn.setBorderPainted(false);
		confirmbtn.setContentAreaFilled(false);
		confirmbtn.setFocusPainted(false);
		cancelbtn.setBorderPainted(false);
		cancelbtn.setContentAreaFilled(false);
		cancelbtn.setFocusPainted(false);
		confirmbtn.setBounds(x + 170, y + 510, 100, 50);
		cancelbtn.setBounds(x + 320, y + 510, 100, 50);
		// 확인/취소 버튼 생성 시작
		// 확인버튼에 마우스리스너달기
		confirmbtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
			if(btn[0].getIcon().equals(way1)) {
				JOptionPane.showMessageDialog(null,"환불수단을 선택해주세요.");
				return;
			}
			if(checkbox.isSelected()) {
				doCustomerInfo();
				doUpdateSalesInfo();
				doDeleteMylist();
				doInsertGameRefund();
			
				DiselFrame.myp.setPanel();
				JOptionPane.showMessageDialog(null, "환불이 완료되었습니다");
					DiselFrame.isMainPage = false;
					DiselFrame.isMyPage = true;
					DiselFrame.isStorePage = false;
					DiselFrame.isAdminChange = false;
					DiselFrame.isPurchasePage = false;
					DiselFrame.isRefundPage = false;
					DiselFrame.isChangePage = false;
					btn[0].setIcon(way1);
					checkbox.setSelected(false);
				
			}
				
				else{JOptionPane.showMessageDialog(null,"약관에 동의 해주세요.");}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				confirmbtn.setIcon(icon3);
				confirmbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				confirmbtn.setIcon(icon1);
				confirmbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		// 확인버튼에 마우스리스너달기 끝
		cancelbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isMyPage = true;
//				DiselFrame.isSalesPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isAdminChange= false;
				DiselFrame.isPurchasePage=false;
				DiselFrame.isRefundPage=false;
				DiselFrame.isChangePage=false;
				btn[0].setIcon(way1);
				checkbox.setSelected(false);
				}
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelbtn.setIcon(icon4);
				cancelbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				cancelbtn.setIcon(icon2);
				cancelbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		// 취소버튼에 마우스리스너 달기

		add(confirmbtn);
		add(cancelbtn);
	}
	public void setScroll() {
		JTextArea str = new JTextArea();
		returnPolicy = new JScrollPane(str) {
			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
			}
		};
		UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.BLACK));

		returnPolicy.getVerticalScrollBar().setUI(new BasicScrollBarUI());
		returnPolicy.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
		str.setFont(new Font("휴먼모음T", Font.BOLD, 20));
		str.setForeground(Color.white);
		BasicTextAreaUI bt = new BasicTextAreaUI();
		str.setUI(bt);
		str.setBackground(new Color(80, 80, 80));
		str.setText(
				"상당히 그럴싸한 환불 정책     \n 1.대충 그럴싸한 환불정책 \n 2.대충 그럴싸한 환불정책 \n 3.대충 그럴싸한 환불정책 \n 4.대충 그럴싸한 환불정책 \n 5.대충 그럴싸한 환불정책 \n 6.대충 그럴싸한 환불정책 \n 7.대충 그럴싸한 환불정책 \n 8.대충 그럴싸한 환불정책");
		str.setEditable(false);
		returnPolicy.setBounds(x + 100, y + 280, 420, 200);
		add(returnPolicy);
	}

	public void setLabel() {
		game_title = new JLabel("게임이름");
		game_count = new JLabel("1");
		game_price = new JLabel("게임가격");
		result1 = new JLabel("총액");
		result2 = new JLabel("가격");
		purchase_confirm = new JLabel("환불명세 확인");
		bangbub = new JLabel("환불수단");
		yakguan = new JLabel("환불약관");
		game_title.setFont(font3);
		game_count.setFont(font3);
		game_price.setFont(font3);
		result1.setFont(font3);
		result2.setFont(font3);
		bangbub.setFont(font2);
		yakguan.setFont(font2);
		purchase_confirm.setFont(font2);
		game_title.setForeground(Color.WHITE);
		game_count.setForeground(Color.WHITE);
		game_price.setForeground(Color.WHITE);
		result1.setForeground(Color.WHITE);
		result2.setForeground(Color.WHITE);
		purchase_confirm.setForeground(Color.WHITE);
		bangbub.setForeground(Color.WHITE);
		yakguan.setForeground(Color.WHITE);
		purchase_confirm.setBounds(x + 635, y + 60, 300, 50);
		game_title.setBounds(x + 635, y + 110, 100, 50);
		game_count.setBounds(x + 830, y + 110, 50, 50);
		game_price.setBounds(x + 850, y + 110, 100, 50);
		result1.setBounds(x + 635, y + 500, 50, 50);
		result2.setBounds(x + 850, y + 500, 100, 50);
		bangbub.setBounds(x + 100, y + 50, 100, 50);
		yakguan.setBounds(x+100, y+230,100,50);
		add(purchase_confirm);
		add(game_title);
		add(game_count);
		add(game_price);
		add(result1);
		add(result2);
		add(bangbub);
		add(yakguan);

	}
	public void setName() {
		for(int i=0;i<DiselFrame.gamelist.size();i++) {
			if(DiselFrame.gamelist.get(i).getGame_code().equals(DiselFrame.mylist.get(MyPage.MygameNum).getGame_code())) {
				gameNum=i;
			}
		}
		game_title.setText(DiselFrame.gamelist.get(gameNum).getGame_name());
		game_price.setText(DiselFrame.gamelist.get(gameNum).getGame_price()+" ￦ ");
		result2.setText(DiselFrame.gamelist.get(gameNum).getGame_price()+" ￦ ");
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
		g.drawImage(resultImage, x + 600, y + 60, null);
		g.drawImage(nameLabelImage3,x+98,y+277,null);

	
	}

}