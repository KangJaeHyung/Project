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
	private String[] selectList = { "��������� �������ּ���", "�̸���", "��ȭ��ȣ" };
	private JButton confirmbtn, cancelbtn;
	private ImageIcon icon1, icon2, icon3, icon4;
	private ImageIcon way1, way2, way3, way4;
	private JScrollPane returnPolicy;
	private JLabel game_title, game_count, game_price, purchase_confirm;
	private JLabel result1, result2, bangbub, yakguan;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image resultImage = new ImageIcon(Main.class.getResource("../resource/������.png")).getImage();
	private Image nameLabelImage = new ImageIcon(Main.class.getResource("../resource/���Ӷ�_200X50.png")).getImage();
	private Image nameLabelImage2 = new ImageIcon(Main.class.getResource("../resource/���Ӷ�_200X50.png")).getImage();
	private Image nameLabelImage3 = new ImageIcon(Main.class.getResource("../resource/���Ӷ�_200X50.png")).getImage();
	private Image NamePlate = new ImageIcon(Main.class.getResource("../resource/�����÷���Ʈ.png")).getImage();
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

		font = new Font("�޸ո���T", Font.BOLD, 30);
		font2 = new Font("�޸ո���T", Font.BOLD, 20);
		font3 = new Font("�޸ո���T", Font.BOLD, 15);
		y = y - 30;
		setLabel();
		setButton();
		setScroll();
		setCheckbox();
	}

	public void setCheckbox() {
		checkbox = new JCheckBox("�� ������ �����Ͽ����� ���� �� �̻��� ���뿡 �����մϴ�");
		checkbox.setBounds(x+100, y+480, 500, 30);
		checkbox.setFont(font3);
		checkbox.setForeground(Color.WHITE);
		checkbox.setBorder(null);
		checkbox.setOpaque(false);
		add(checkbox);
	}
	//ĳ�� ȯ�� ����
	public void doCustomerInfo() {
		CrudProcess crud = new CrudProcess();
		int price = DiselFrame.gamelist.get(gameNum).getGame_price();
		String user_id=DiselFrame.loginCustomer.getUser_id();
		crud.updateCash(price, user_id);
	}
	//ĳ��ȯ�� ��

	//�������db��������
	public void doUpdateSalesInfo() {
		CrudProcess crud = new CrudProcess();
		String game_code = DiselFrame.gamelist.get(gameNum).getGame_code();
		Sales_info s = new Sales_info();
		s.setGame_code(game_code);
		crud.updateSalesInfo2(s);
	}
	//�������db������
	
	//���Ӹ���Ʈ���� ��������
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
	//���Ӹ���Ʈ���� ������
	
	//����ȯ��db�� ���Խ���
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
	//����ȯ��db�� ���Գ�

	
//	public void doMyListAndSalesInfo() {
//		CrudProcess crud = new CrudProcess();
//		//���� ���� db�� �ڷ� ���� �غ� 
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
//		//���� ���� db�� �ڷ���� �غ� ��
//		crud.insertGameSale(g);
//	}
//	public void doGameSaledb() {
//		
//		CrudProcess crud = new CrudProcess();
//		//���� ����Ʈdb,�������db�� �ڷ���� �غ�
//	
//		int price = DiselFrame.gamelist.get(StorePage.gameNum).getGame_price();
//		String game_name = DiselFrame.gamelist.get(StorePage.gameNum).getGame_name();
//		String game_code = DiselFrame.gamelist.get(StorePage.gameNum).getGame_code();
//		String user_id = DiselFrame.loginCustomer.getUser_id();
//		//���� ����Ʈdb,�������db�� �ڷ���� �غ�
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

		// �̹��� ������ ���� ����
		icon1 = new ImageIcon(RefundPage.class.getResource("../resource/Ȯ�ι�ư_�Ϲ�.png"));
		icon2 = new ImageIcon(RefundPage.class.getResource("../resource/��ҹ�ư_�Ϲ�.png"));
		icon3 = new ImageIcon(RefundPage.class.getResource("../resource/Ȯ�ι�ư_����.png"));
		icon4 = new ImageIcon(RefundPage.class.getResource("../resource/��ҹ�ư_����.png"));
		way1 = new ImageIcon(RefundPage.class.getResource("../resource/����1.png"));
		way2 = new ImageIcon(RefundPage.class.getResource("../resource/����2.png"));
		way3 = new ImageIcon(RefundPage.class.getResource("../resource/����1_����.png"));
		way4 = new ImageIcon(RefundPage.class.getResource("../resource/����2_����.png"));
		// �̹��� ������ ���� ��

		// ȯ�ҹ�� ��ư ���� ����
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
		// Ȯ��/��� ��ư ���� ����
		// Ȯ�ι�ư�� ���콺�����ʴޱ�
		confirmbtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
			if(btn[0].getIcon().equals(way1)) {
				JOptionPane.showMessageDialog(null,"ȯ�Ҽ����� �������ּ���.");
				return;
			}
			if(checkbox.isSelected()) {
				doCustomerInfo();
				doUpdateSalesInfo();
				doDeleteMylist();
				doInsertGameRefund();
			
				DiselFrame.myp.setPanel();
				JOptionPane.showMessageDialog(null, "ȯ���� �Ϸ�Ǿ����ϴ�");
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
				
				else{JOptionPane.showMessageDialog(null,"����� ���� ���ּ���.");}
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
		// Ȯ�ι�ư�� ���콺�����ʴޱ� ��
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
		// ��ҹ�ư�� ���콺������ �ޱ�

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
		str.setFont(new Font("�޸ո���T", Font.BOLD, 20));
		str.setForeground(Color.white);
		BasicTextAreaUI bt = new BasicTextAreaUI();
		str.setUI(bt);
		str.setBackground(new Color(80, 80, 80));
		str.setText(
				"����� �׷����� ȯ�� ��å     \n 1.���� �׷����� ȯ����å \n 2.���� �׷����� ȯ����å \n 3.���� �׷����� ȯ����å \n 4.���� �׷����� ȯ����å \n 5.���� �׷����� ȯ����å \n 6.���� �׷����� ȯ����å \n 7.���� �׷����� ȯ����å \n 8.���� �׷����� ȯ����å");
		str.setEditable(false);
		returnPolicy.setBounds(x + 100, y + 280, 420, 200);
		add(returnPolicy);
	}

	public void setLabel() {
		game_title = new JLabel("�����̸�");
		game_count = new JLabel("1");
		game_price = new JLabel("���Ӱ���");
		result1 = new JLabel("�Ѿ�");
		result2 = new JLabel("����");
		purchase_confirm = new JLabel("ȯ�Ҹ� Ȯ��");
		bangbub = new JLabel("ȯ�Ҽ���");
		yakguan = new JLabel("ȯ�Ҿ��");
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
		game_price.setText(DiselFrame.gamelist.get(gameNum).getGame_price()+" �� ");
		result2.setText(DiselFrame.gamelist.get(gameNum).getGame_price()+" �� ");
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
		g.drawImage(resultImage, x + 600, y + 60, null);
		g.drawImage(nameLabelImage3,x+98,y+277,null);

	
	}

}