package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class PurchasePage extends JPanel {
	private JCheckBox directInput;
	private JComboBox selection;
	private String[] selectList = { "��������� �������ּ���", "�̸���", "��ȭ��ȣ" };
	private JButton confirmbtn, cancelbtn, authbtn, mailbtn;;
	private ImageIcon icon1, icon2, icon3, icon4;
	private ImageIcon way1, way2, way3, way4;
	private JTextField name;
	private JTextField emailAndPhone;
	private JTextField injung;
	private JLabel game_title, game_count, game_price, purchase_confirm;
	private JLabel result1, result2, bangbub;
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
	private int random;
	private String[] auth;
	private String authcode;
	

	private Game_info game_info;
	int x, y;

	PurchasePage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		resultImage = resultImage.getScaledInstance(350, 500, Image.SCALE_SMOOTH);
		nameLabelImage = nameLabelImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		nameLabelImage2 = nameLabelImage2.getScaledInstance(307, 35, Image.SCALE_SMOOTH);
		nameLabelImage3 = nameLabelImage3.getScaledInstance(300, 40, Image.SCALE_SMOOTH);
		NamePlate = NamePlate.getScaledInstance(250, 50, Image.SCALE_SMOOTH);

		font = new Font("�޸ո���T", Font.BOLD, 30);
		font2 = new Font("�޸ո���T", Font.BOLD, 20);
		font3 = new Font("�޸ո���T", Font.BOLD, 15);
		y = y - 30;
		setLabel();
		setTextField();
		setButton();
		setComboBox();
	}
	
	public void doName() {

		name.setText(DiselFrame.loginCustomer.getUser_id());
		emailAndPhone.setText("");
		injung.setText("");
		selection.setSelectedIndex(0);
		btn[0].setIcon(way1);
	}

	public void doCustomerInfo() {
		CrudProcess crud = new CrudProcess();
		int price = DiselFrame.gamelist.get(StorePage.gameNum).getGame_price();
		String user_id = DiselFrame.loginCustomer.getUser_id();
		crud.updateAfterPurchase(price, user_id);
	}

	public void doMyListAndSalesInfo() {
		CrudProcess crud = new CrudProcess();
		// ���� ���� db�� �ڷ� ���� �غ�
		Game_sale g = new Game_sale();
		SimpleDateFormat simple = new SimpleDateFormat("yy-MM-dd/HH:mm:ss");
		Date date = new Date();
		String gameCode = crud.selectGameOrderCode();
		int number = 0;
		if (gameCode != null) {
			String[] codeSplit = gameCode.split("C");
			number = Integer.parseInt(codeSplit[1]);
		}
		number = number + 1;
		String format = String.format("%05d", number);
		g.setGame_order_code("C" + format);
		g.setUser_id(DiselFrame.loginCustomer.getUser_id());
		g.setPayment(DiselFrame.gamelist.get(StorePage.gameNum).getGame_price());
		g.setOrder_date(simple.format(date));
		
		g.setGame_code(DiselFrame.gamelist.get(StorePage.gameNum).getGame_code());
		g.setIsRefunded(0);
		// ���� ���� db�� �ڷ���� �غ� ��
		crud.insertGameSale(g);
	}

	public void doGameSaledb() {

		CrudProcess crud = new CrudProcess();
		// ���� ����Ʈdb,�������db�� �ڷ���� �غ�

		int price = DiselFrame.gamelist.get(StorePage.gameNum).getGame_price();
		String game_name = DiselFrame.gamelist.get(StorePage.gameNum).getGame_name();
		String game_code = DiselFrame.gamelist.get(StorePage.gameNum).getGame_code();
		String user_id = DiselFrame.loginCustomer.getUser_id();
		// ���� ����Ʈdb,�������db�� �ڷ���� �غ�
		MyList m = new MyList();
		m.setUser_id(user_id);
		m.setGame_code(game_code);
		Sales_info s = new Sales_info();
		s.setGame_code(game_code);
		crud.insertGameList(m);
		crud.updateSalesInfo(s);
		HashMap<String, Object> map = new HashMap();
		map.put("user_id", DiselFrame.loginCustomer.getUser_id());
		DiselFrame.mylist = crud.selectGetGameInfo(map);
	}

	public void setCheckbox() {
		directInput = new JCheckBox("�����Է�");
		directInput.setBounds(x + 100, y + 430, 500, 30);
		directInput.setFont(font3);
		directInput.setForeground(Color.WHITE);
		directInput.setBorder(null);
		directInput.setOpaque(false);
		add(directInput);
	}

	public void setComboBox() {

		UIManager.put("ComboBox.background", new ColorUIResource(new Color(60, 60, 60)));
		UIManager.put("ComboBox.buttonBackground", new ColorUIResource(new Color(60, 60, 60)));
		UIManager.put("ComboBox.buttonDarkShadow", new ColorUIResource(new Color(255, 255, 255)));
		UIManager.put("ComboBox.buttonHighlight", new ColorUIResource(new Color(255, 255, 255)));
		UIManager.put("ComboBox.buttonShadow", new ColorUIResource(new Color(255, 255, 255)));
		UIManager.put("ComboBox.foreground", new ColorUIResource(new Color(255, 255, 255)));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(60, 60, 60)));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(new Color(255, 255, 255)));

		selection = new JComboBox(selectList);
		selection.setUI(new BasicComboBoxUI());

//		selection.setForeground(Color.WHITE);
//		selection.setBorder(null);
//		selection.setOpaque(false);
		selection.setFont(font2);
		selection.setBounds(x + 103, y + 170, 300, 30);
		selection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox) arg0.getSource();
				index = cb.getSelectedIndex();
				if (index == 1) {
					emailAndPhone.setText(DiselFrame.loginCustomer.getE_mail());

				} else if (index == 2) {
					emailAndPhone.setText(DiselFrame.loginCustomer.getPhone_num());
				} else {
					emailAndPhone.setText(" ");
				}
			}
		});
		add(selection);
	}

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
		btn[0].setBounds(x + 100, y + 350, 200, 50);
		btn[1].setBounds(x + 320, y + 350, 200, 50);
		btn[2].setBounds(x + 100, y + 420, 200, 50);
		btn[3].setBounds(x + 320, y + 420, 200, 50);
		add(btn[0]);
		add(btn[1]);
		add(btn[2]);
		add(btn[3]);
		authbtn= new JButton(icon1);
		mailbtn = new JButton(icon1);
		confirmbtn = new JButton(icon1);
		cancelbtn = new JButton(icon2);
		confirmbtn.setBorderPainted(false);
		confirmbtn.setContentAreaFilled(false);
		confirmbtn.setFocusPainted(false);
		confirmbtn.setEnabled(false);
		cancelbtn.setBorderPainted(false);
		cancelbtn.setContentAreaFilled(false);
		cancelbtn.setFocusPainted(false);
		authbtn.setBorderPainted(false);
		authbtn.setContentAreaFilled(false);
		authbtn.setFocusPainted(false);
		mailbtn.setBorderPainted(false);
		mailbtn.setContentAreaFilled(false);
		mailbtn.setFocusPainted(false);	
		confirmbtn.setBounds(x + 170, y + 490, 100, 50);
		cancelbtn.setBounds(x + 320, y + 490, 100, 50);
		authbtn.setBounds(x+410, y+250, 100, 50);
		mailbtn.setBounds(x+410, y+200, 100, 50);
		// Ȯ��/��� ��ư ���� ����
		// Ȯ�ι�ư�� ���콺�����ʴޱ�
		mailbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (index == 0) {
					JOptionPane.showMessageDialog(null, "��������� �������ּ���.");
					return;
				}else if(index==1) {
					naverMailSend();
				}
				
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				mailbtn.setIcon(icon3);
				mailbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				mailbtn.setIcon(icon1);
				mailbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		authbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(injung.getText().equals(authcode)) {
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�");
					confirmbtn.setEnabled(true);
					
				}else {JOptionPane.showMessageDialog(null,"������ȣ�� ��ġ���� �ʽ��ϴ�");
						injung.setText("");}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				authbtn.setIcon(icon3);
				authbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				authbtn.setIcon(icon1);
				authbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		confirmbtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (index == 0) {
					JOptionPane.showMessageDialog(null, "��������� �������ּ���.");
					return;
				}
				if (btn[0].getIcon().equals(way3)) {
					if (DiselFrame.loginCustomer.getUser_cash() >= DiselFrame.gamelist.get(StorePage.gameNum)
							.getGame_price()) {
						doCustomerInfo();
						doMyListAndSalesInfo();
						doGameSaledb();
						DiselFrame.myp.setPanel();
						JOptionPane.showMessageDialog(null, "���Ű� �Ϸ�Ǿ����ϴ�");
						DiselFrame.isMainPage = false;
						DiselFrame.isMyPage = false;
						DiselFrame.isStorePage = true;
						DiselFrame.isAdminChange = false;
						DiselFrame.isPurchasePage = false;
						DiselFrame.isRefundPage = false;
						DiselFrame.isChangePage = false;
						 
					}else {
						JOptionPane.showMessageDialog(null, "�ݾ��� �����մϴ�.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���Ź���� �������ּ���.");
				}
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
				DiselFrame.isMyPage = false;
//				DiselFrame.isSalesPage = false;
				DiselFrame.isStorePage = true;
				DiselFrame.isAdminChange = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isChangePage = false;
				 
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
		add(authbtn);
		add(mailbtn);
	}

	public void setTextField() {
		emailAndPhone = new JTextField();
		emailAndPhone.setBorder(null);
		emailAndPhone.setOpaque(false);
		emailAndPhone.setFont(font2);
		emailAndPhone.setForeground(Color.WHITE);
		emailAndPhone.setEnabled(false);
		emailAndPhone.setBounds(x + 103, y + 210, 297, 40);
		add(emailAndPhone);

		name = new JTextField(20);
		name.setEnabled(false);
		name.setBorder(null);
		name.setOpaque(false);
		name.setForeground(Color.gray);
		name.setFont(font);
		name.setBounds(x + 103, y + 100, 200, 50);
		name.setCaretColor(Color.white);
		injung = new JTextField(20);
		injung.setBorder(null);
		injung.setOpaque(false);
		injung.setFont(font2);
		injung.setForeground(Color.WHITE);
		injung.setBounds(x+103, y+260, 297, 40);
		add(injung);
		add(name);

	}

	public void setLabel() {
		game_title = new JLabel("�����̸�");
		game_count = new JLabel("1");
		game_price = new JLabel("���Ӱ���");
		result1 = new JLabel("�Ѿ�");
		result2 = new JLabel("����");
		purchase_confirm = new JLabel("���Ÿ� Ȯ��");
		bangbub = new JLabel("���Ź��");
		game_title.setFont(font3);
		game_count.setFont(font3);
		game_price.setFont(font3);
		result1.setFont(font3);
		result2.setFont(font3);
		bangbub.setFont(font2);
		purchase_confirm.setFont(font2);
		game_title.setForeground(Color.WHITE);
		game_count.setForeground(Color.WHITE);
		game_price.setForeground(Color.WHITE);
		result1.setForeground(Color.WHITE);
		result2.setForeground(Color.WHITE);
		purchase_confirm.setForeground(Color.WHITE);
		bangbub.setForeground(Color.WHITE);

		purchase_confirm.setBounds(x + 635, y + 60, 300, 50);
		game_title.setBounds(x + 635, y + 110, 100, 50);
		game_count.setBounds(x + 830, y + 110, 50, 50);
		game_price.setBounds(x + 850, y + 110, 100, 50);
		result1.setBounds(x + 635, y + 500, 50, 50);
		result2.setBounds(x + 850, y + 500, 100, 50);
		bangbub.setBounds(x + 100, y + 300, 100, 50);
		add(purchase_confirm);
		add(game_title);
		add(game_count);
		add(game_price);
		add(result1);
		add(result2);
		add(bangbub);

	}

	public void setName() {
		game_title.setText(DiselFrame.gamelist.get(StorePage.gameNum).getGame_name());
		game_price.setText(DiselFrame.gamelist.get(StorePage.gameNum).getGame_price() + " �� ");
		result2.setText(DiselFrame.gamelist.get(StorePage.gameNum).getGame_price() + " �� ");
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
		g.drawImage(resultImage, x + 600, y + 60, null);
		g.drawImage(nameLabelImage, x + 100, y + 100, null);
		g.drawImage(nameLabelImage3, x + 100, y + 210, null);
		g.drawImage(nameLabelImage2, x + 100, y + 167, null);
		g.drawImage(nameLabelImage3,x+100, y+260,null);

	}
	public void naverMailSend() {

		doCreateCode();
		final String host = "smtp.naver.com";
		final String user = "testpret@naver.com";
		final String password = "test1@3$";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.stmp.port", 465);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAndPhone.getText()));
			// ���� ����
			message.setSubject("Ȯ�θ����Դϴ�");
			// ���� ����
			message.setText("������ȣ��" +authcode + "�Դϴ�");
			// send the message
			Transport.send(message);
			JOptionPane.showMessageDialog(null, "������ȣ�� ������ ���Ϸ� �߼۵Ǿ����ϴ�");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void doCreateCode() {
		auth = new String[6];
		char a = 65;
		for (int i = 0; i < auth.length; i++) {
			random = (int) (Math.random() * 36);
			if (random < 10) {
				auth[i] = Integer.toString(random);

			} else {
				auth[i] = Character.toString((char) (a + random - 10));

			}
		}
		authcode = auth[0] + auth[1] + auth[2] + auth[3] + auth[4] + auth[5];
	}
}

