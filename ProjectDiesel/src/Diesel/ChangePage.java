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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ChangePage extends JPanel {
	private ImageIcon icon1, icon2;
	private ImageIcon icon3, icon4;
	private JButton btn1, btn2;
	private Font font;
	private JFrame j;
	private JTextField id, name, eMail, phoneNumber, plate;
	private JLabel user_id, user_pass, user_pass2, user_email, user_pnumber, country2;
	private JPasswordField password, confirm;
	private JComboBox<String> country;
	private JPanel terms;
	private Image textField = new ImageIcon(Main.class.getResource("../DieselImage/TextField.png")).getImage();
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image NamePlate = new ImageIcon(Main.class.getResource("../resource/네임플레이트.png")).getImage();
	private Image idImage;
	private Image eMailImage;
	private Image checkBox = new ImageIcon(Main.class.getResource("../DieselImage/체크박스.png")).getImage();
	private Image ischeck;
	private Image serviceImage;
	private String[] coutryList = { "Country", "한국", "미국", "일본", "중국" };
	private JScrollPane service;
	private boolean isConfirm = false;

	public void setTextContents() {
		id.setText(DiselFrame.loginCustomer.getUser_id());
		id.setForeground(Color.white);
		eMail.setText(DiselFrame.loginCustomer.getE_mail());
		eMail.setForeground(Color.white);
		password.setText("Password");
		password.setForeground(Color.gray);
		confirm.setText("Confirm");
		confirm.setForeground(Color.gray);

		phoneNumber.setText(DiselFrame.loginCustomer.getPhone_num());
		phoneNumber.setForeground(Color.white);
		country.setSelectedItem(DiselFrame.loginCustomer.getCountry());
		country.setForeground(Color.white);
	}

	int x, y;

	ChangePage(DiselFrame j) {
		this.j = j;
		font = new Font("arian", Font.BOLD, 30);
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		idImage = textField.getScaledInstance(300, 60, Image.SCALE_SMOOTH);
		eMailImage = textField.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
		serviceImage = textField.getScaledInstance(360, 360, Image.SCALE_SMOOTH);
		NamePlate = NamePlate.getScaledInstance(250, 50, Image.SCALE_SMOOTH);
		x = 320;
		y = 30;
		setTextField();
		setComboBox();
//		setJScrollPane();
		setLable();
		setButton();
	}

//	public void setJScrollPane() {
//		JTextArea str=new JTextArea();
//		
//		service=new JScrollPane(str) {
//			@Override
//			public void paintComponent(Graphics g) {
//				// TODO Auto-generated method stub
//				super.paintComponent(g);
//			}
//		};
//		str.setFont(new Font("arian", Font.BOLD, 20));
//		str.setForeground(Color.white);
//		BasicTextAreaUI bt=new BasicTextAreaUI(); 
//		str.setUI(bt);
//		str.setBackground(new Color(80, 80, 80));
//		str.setText("                    \n              그렇다 나는천재다.");
//		str.setEditable(false);
//		service.setBounds(x+443, y-85, 353, 350);
//		
//		add(service);
//	}
//	
	public void setButton() {
		icon1 = new ImageIcon(ChangePage.class.getResource("../resource/확인버튼_일반.png"));
		icon2 = new ImageIcon(ChangePage.class.getResource("../resource/취소버튼_일반.png"));
		icon3 = new ImageIcon(ChangePage.class.getResource("../resource/확인버튼_반응.png"));
		icon4 = new ImageIcon(ChangePage.class.getResource("../resource/취소버튼_반응.png"));
		btn1 = new JButton(icon1);
		btn2 = new JButton(icon2);
		btn1.setBorderPainted(false);
		btn1.setContentAreaFilled(false);
		btn1.setFocusPainted(false);
		btn2.setBorderPainted(false);
		btn2.setContentAreaFilled(false);
		btn2.setFocusPainted(false);
		btn1.setBounds(x + 425, y + 470, 100, 50);
		btn2.setBounds(x + 550, y + 470, 100, 50);
		add(btn1);
		add(btn2);
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (isConfirm) {
						int result = JOptionPane.showConfirmDialog(null, "회원정보를 변경 하시겠습니까?", "Confirm",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.CLOSED_OPTION) {
							return;
						} else if (result == JOptionPane.YES_OPTION) {
							String str = (String) country.getSelectedItem();
							Customer_info ci = new Customer_info(id.getText(), password.getText(), eMail.getText(),
									phoneNumber.getText(), str);
							CrudProcess crud = new CrudProcess();
							crud.updateInform(ci);
							DiselFrame.loginCustomer = crud.selectInfo(ci.getUser_id());
							JOptionPane.showMessageDialog(null, "회원정보변경이 완료되었습니다");
							DiselFrame.isMainPage = false;
							DiselFrame.isMyPage = true;
							DiselFrame.isStorePage = false;
							DiselFrame.isAdminChange = false;
							DiselFrame.isPurchasePage = false;
							DiselFrame.isRefundPage = false;
							DiselFrame.isChangePage = false;
							 
						}
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인 해 주세요.");
					}
				} catch (Exception ex) {

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn1.setIcon(icon3);
				btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btn1.setIcon(icon1);
				btn1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiselFrame.isMainPage = false;
				DiselFrame.isMyPage = true;
//			DiselFrame.isSalesPage = false;
				DiselFrame.isStorePage = false;
				DiselFrame.isAdminChange = false;
				DiselFrame.isPurchasePage = false;
				DiselFrame.isRefundPage = false;
				DiselFrame.isChangePage = false;
				 
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn2.setIcon(icon4);
				btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btn2.setIcon(icon2);
				btn2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}

	public void setLable() {

		user_id = new JLabel("User Id :");
		user_pass = new JLabel("User Password :");
		user_pass2 = new JLabel("Confirm Password :");
		user_email = new JLabel("E-mail :");
		user_pnumber = new JLabel("Phone Number :");
		country2 = new JLabel("Country :");
		user_id.setFont(font);
		user_pass.setFont(font);
		user_pass2.setFont(font);
		user_email.setFont(font);
		user_pnumber.setFont(font);
		country2.setFont(font);
		user_id.setForeground(Color.WHITE);
		user_pass.setForeground(Color.WHITE);
		user_pass2.setForeground(Color.WHITE);
		user_email.setForeground(Color.WHITE);
		user_pnumber.setForeground(Color.WHITE);
		country2.setForeground(Color.WHITE);
		user_id.setBounds(x - 135, y + 4, 230, 45);
		user_pass.setBounds(x - 250, y + 94, 250, 45);
		user_pass2.setBounds(x - 295, y + 184, 290, 45);
		user_email.setBounds(x - 120, y + 274, 230, 45);
		user_pnumber.setBounds(x - 240, y + 364, 230, 45);
		country2.setBounds(x - 145, y + 454, 230, 45);
		add(user_id);
		add(user_pass);
		add(user_pass2);
		add(user_email);
		add(user_pnumber);
		add(country2);
	}

	public void setComboBox() {
		country = new JComboBox<String>(coutryList);
		country.setOpaque(false);
		country.setBackground(new Color(48, 48, 48));
		country.setBorder(null);
		country.setOpaque(false);

		country.getSelectedIndex();
		country.setForeground(Color.gray);
		country.setFont(new Font("arian", Font.BOLD, 20));
		country.setBounds(x + 10, y + 454, 280, 52);
		country.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				int index = cb.getSelectedIndex();
				if (index == 0) {
					country.setForeground(Color.gray);
				} else {
					country.setForeground(Color.white);
				}

			}
		});
		BasicComboBoxUI bs = new BasicComboBoxUI();
		country.setUI(bs);
		add(country);
	}

	public void setTextField() {
		id = new JTextField(20);
		id.setEnabled(false);
		id.setBorder(null);
		id.setOpaque(false);
		id.setForeground(Color.gray);
		id.setFont(new Font("arian", Font.BOLD, 20));
		id.setBounds(x + 10, y + 4, 290, 56);
		id.setCaretColor(Color.white);

		add(id);

		password = new JPasswordField("Password", 20);
		password.setBorder(null);
		password.setOpaque(false);
		password.setForeground(Color.gray);
		password.setFont(new Font("arian", Font.BOLD, 20));
		password.setBounds(x + 10, y + 94, 290, 56);
		password.setCaretColor(Color.white);
		password.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (password.getText().equals("")) {
					password.setForeground(Color.gray);
					password.setText("Password");

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (password.getForeground() == Color.gray) {
					password.setText("");
					password.setForeground(Color.white);
				}

			}
		});
		add(password);

		confirm = new JPasswordField("Confirm", 20);
		confirm.setBorder(null);
		confirm.setOpaque(false);
		confirm.setForeground(Color.gray);
		confirm.setFont(new Font("arian", Font.BOLD, 20));
		confirm.setBounds(x + 10, y + 184, 290, 56);
		confirm.setCaretColor(Color.white);
		confirm.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (confirm.getText().equals("")) {
					confirm.setForeground(Color.gray);
					confirm.setText("Confirm");
				}
				if (confirm.getText().equals(password.getText())) {
					isConfirm = true;
					repaint();
				} else {
					isConfirm = false;
					repaint();
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (confirm.getForeground() == Color.gray) {
					confirm.setText("");
					confirm.setForeground(Color.white);
				}
			}
		});
		add(confirm);

		eMail = new JTextField(30);
		eMail.setBorder(null);
		eMail.setOpaque(false);
		eMail.setForeground(Color.gray);
		eMail.setFont(new Font("arian", Font.BOLD, 20));
		eMail.setBounds(x + 10, y + 274, 390, 56);
		eMail.setCaretColor(Color.white);
		eMail.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (eMail.getText().equals("")) {
					eMail.setForeground(Color.gray);
					eMail.setText("Email");

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (eMail.getForeground() == Color.gray) {
					eMail.setText("");
					eMail.setForeground(Color.white);
				}

			}
		});
		add(eMail);

		phoneNumber = new JTextField(30);
		phoneNumber.setBorder(null);
		phoneNumber.setOpaque(false);
		phoneNumber.setForeground(Color.gray);
		phoneNumber.setFont(new Font("arian", Font.BOLD, 20));
		phoneNumber.setBounds(x + 10, y + 364, 390, 56);
		phoneNumber.setCaretColor(Color.white);
		phoneNumber.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (phoneNumber.getText().equals("")) {
					phoneNumber.setForeground(Color.gray);
					phoneNumber.setText("PhoneNumber");

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (phoneNumber.getForeground() == Color.gray) {
					phoneNumber.setText("");
					phoneNumber.setForeground(Color.white);
				}

			}
		});
		add(phoneNumber);

		plate = new JTextField("회원정보변경", 30);
		plate.setBorder(null);
		plate.setOpaque(false);
		plate.setForeground(Color.white);
		plate.setFont(font);
		plate.setBounds(x + 470, y - 30, 350, 50);
		add(plate);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
		g.drawImage(idImage, x, y, null);
		g.drawImage(idImage, x, y + 90, null);
		g.drawImage(idImage, x, y + 180, null);
		g.drawImage(checkBox, x + 330, y + 100, null);
		if (isConfirm) {
			ischeck = new ImageIcon(Main.class.getResource("../DieselImage/체크박스_true.png")).getImage();
		} else {
			ischeck = new ImageIcon(Main.class.getResource("../DieselImage/체크박스_false.png")).getImage();
		}
		g.drawImage(ischeck, x + 330, y + 100, null);
		g.drawImage(eMailImage, x, y + 270, null);
		g.drawImage(eMailImage, x, y + 360, null);
		g.drawImage(idImage, x, y + 450, null);
		g.drawImage(NamePlate, x + 424, y - 27, null);
//		g.drawImage(serviceImage,x+440, y-90,null);
	}

}
