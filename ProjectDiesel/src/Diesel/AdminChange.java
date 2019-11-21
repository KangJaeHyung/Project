package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class AdminChange extends JPanel{
	private ImageIcon icon1,icon2;
	private ImageIcon icon3,icon4;
	private JButton ok_btn, cancle_btn;
	private Font font;
	private JFrame j;
	private JTextField id, eMail, phoneNumber,plate;
	private JLabel user_id, user_stat, user_email,user_pnumber,country2;
	private JPasswordField password, confirm;
	private JComboBox<String> country;
	private JComboBox<String> status;
	private Image textField = new ImageIcon(Main.class.getResource("../DieselImage/TextField.png")).getImage();
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image NamePlate = new ImageIcon(Main.class.getResource("../resource/네임플레이트.png")).getImage();
	private Image idImage;
	private Image eMailImage;
	private Image boxImage;
	private Image checkBox= new ImageIcon(Main.class.getResource("../DieselImage/체크박스.png")).getImage();
	private Image ischeck;
	private Image serviceImage;
	private String[] userStat = {"일반 유저","밴 유저","관리자"};
	private String[] coutryList= {"Country","한국","미국","일본","짱깨"};
	private JScrollPane service;
	private boolean isConfirm=false;
	
	int x,y;
	AdminChange(DiselFrame j) {
		this.j = j;
		font = new Font("휴먼모음T",Font.BOLD,30);
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		idImage = textField.getScaledInstance(300, 60, Image.SCALE_SMOOTH);
		boxImage = textField.getScaledInstance(295, 60, Image.SCALE_SMOOTH);
		eMailImage = textField.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
		serviceImage = textField.getScaledInstance(360, 360, Image.SCALE_SMOOTH);
		NamePlate = NamePlate.getScaledInstance(250, 50, Image.SCALE_SMOOTH);
		x=320;
		y=30;
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
//		str.setFont(new Font("휴먼모음T", Font.BOLD, 20));
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
	
	public void setTextString(JTable table) {
		id.setText((String)table.getValueAt(DiselFrame.um.nRow, 0));
		eMail.setText((String) table.getValueAt(DiselFrame.um.nRow, 1));
		phoneNumber.setText((String) table.getValueAt(DiselFrame.um.nRow, 2));
		String selcoun= (String) table.getValueAt(DiselFrame.um.nRow, 3);
		if(selcoun.equals("한국")) {
			country.setSelectedIndex(1);
		}else if(selcoun.equals("미국")) {
			country.setSelectedIndex(2);
		}else if(selcoun.equals("일본")) {
			country.setSelectedIndex(3);
		}else if(selcoun.equals("짱깨")) {
			country.setSelectedIndex(4);
		}
		
		String selstat= (String) table.getValueAt(DiselFrame.um.nRow, 5);
		if(selstat.equals("일반 유저")) {
			status.setSelectedIndex(0);
		}else if(selstat.equals("밴 유저")) {
			status.setSelectedIndex(1);
		}else if(selstat.equals("관리자")) {
			status.setSelectedIndex(2);
		}
		
	}
	
	public void setPass() {
		
	}
	
	public void setButton() {
		icon1 = new ImageIcon(ChangePage.class.getResource("../resource/확인버튼_일반.png"));
		icon2 = new ImageIcon(ChangePage.class.getResource("../resource/취소버튼_일반.png"));
		icon3 = new ImageIcon(ChangePage.class.getResource("../resource/확인버튼_반응.png"));
		icon4 = new ImageIcon(ChangePage.class.getResource("../resource/취소버튼_반응.png"));
		ok_btn = new JButton(icon1);
		cancle_btn = new JButton(icon2);
		ok_btn.setBorderPainted(false);
		ok_btn.setContentAreaFilled(false);
		ok_btn.setFocusPainted(false);
		cancle_btn.setBorderPainted(false);
		cancle_btn.setContentAreaFilled(false);
		cancle_btn.setFocusPainted(false);
		ok_btn.setBounds(x+425, y+470, 100, 50);
		cancle_btn.setBounds(x+550, y+470, 100, 50);
		add(ok_btn);
		add(cancle_btn);
		ok_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String strcunt = (String)country.getSelectedItem();	
				String strstat = (String)status.getSelectedItem();	
				System.out.println(strstat);
				int statn =-1;
				if(strstat.equals("일반 유저")) {
					statn =0;
				}else if(strstat.equals("밴 유저")) {
					statn=1;
				}else if(strstat.equals("관리자")) {
					statn=2;
				}
				CrudProcess crud = new CrudProcess();
				Customer_info ci1 = new Customer_info();
				try {
					
					
					Customer_info ci2 = new Customer_info(
							id.getText(),
							eMail.getText(),
							phoneNumber.getText(),
							strcunt,
							statn
							);
					crud.updateAdminInform(ci2);
					DiselFrame.um.tableReset();
					
					JOptionPane.showMessageDialog(null, "회원정보변경이 완료되었습니다");
					DiselFrame.isLoginPage=false;
					DiselFrame.isAdminChange=false;
					DiselFrame.isUserManagerPage = true;
					 
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "등록에 실패했습니다");
				}
				
				
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ok_btn.setIcon(icon3);
				ok_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				ok_btn.setIcon(icon1);
				ok_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	
	cancle_btn.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			DiselFrame.isLoginPage=false;
			DiselFrame.isAdminChange=false;
			DiselFrame.isUserManagerPage = true;
			 
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			cancle_btn.setIcon(icon4);
			cancle_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			cancle_btn.setIcon(icon2);
			cancle_btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	});
}
	public void setLable() {
		user_stat = new JLabel("User Status :");
		user_id = new JLabel("User Id :");
		user_email = new JLabel("E-mail :");
		user_pnumber = new JLabel("Phone Number :");
		country2 = new JLabel("Country :");
		user_id.setFont(font);
		user_email.setFont(font);
		user_pnumber.setFont(font);
		country2.setFont(font);
		user_stat.setFont(font);
		user_id.setForeground(Color.WHITE);
		user_email.setForeground(Color.WHITE);
		user_pnumber.setForeground(Color.WHITE);
		country2.setForeground(Color.WHITE);
		user_stat.setForeground(Color.WHITE);
		user_id.setBounds(x-135, y+4, 230, 45);
		user_email.setBounds(x-120, y+94, 250, 45);
		user_pnumber.setBounds(x-245, y+184, 290, 45);
		country2.setBounds(x-150, y+274, 230, 45);
		user_stat.setBounds(x-200, y+364, 230, 45);
		add(user_id);
		add(user_email);
		add(user_pnumber);
		add(country2);
		add(user_stat);
	}
	

	public void setComboBox() {
		UIManager.put("ComboBox.background", new ColorUIResource(new Color(60,60,60)));
		UIManager.put("ComboBox.buttonBackground", new ColorUIResource(new Color(60,60,60)));
		UIManager.put("ComboBox.buttonDarkShadow", new ColorUIResource(new Color(255,255,255)));
		UIManager.put("ComboBox.buttonHighlight", new ColorUIResource(new Color(255,255,255)));
		UIManager.put("ComboBox.buttonShadow", new ColorUIResource(new Color(255,255,255)));
		UIManager.put("ComboBox.foreground", new ColorUIResource(new Color(255,255,255)));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(60,60,60)));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(new Color(255,255,255)));
		country=new JComboBox<String>(coutryList);
		country.setUI(new BasicComboBoxUI());
		country.setFont(font);
		country.setBounds(x+10, y+274, 280, 50);
		status = new JComboBox<String>(userStat);
		status.setUI(new BasicComboBoxUI());
		status.setFont(font);
		status.setBounds(x+10, y+364, 280, 50);
		add(country);
		add(status);
	}

	public void setTextField() {
		id = new JTextField("ID", 20);
		id.setBorder(null);
		id.setOpaque(false);
		id.setForeground(Color.gray);
		id.setFont(new Font("휴먼모음T", Font.BOLD, 20));
		id.setBounds(x+10, y+4, 290, 56);
		id.setCaretColor(Color.white);
		id.setEnabled(false);
		id.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (id.getText().equals("")) {
					id.setForeground(Color.gray);
					id.setText("ID");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (id.getText().equals("ID") && id.getForeground() == Color.gray) {
					id.setText("");
					id.setForeground(Color.white);
				}

			}
		});
		add(id);
		
		eMail = new JTextField("Email", 30);
		eMail.setBorder(null);
		eMail.setOpaque(false);
		eMail.setForeground(Color.white);
		eMail.setFont(new Font("휴먼모음T", Font.BOLD, 20));
		eMail.setBounds(x+10, y+94, 290,56 );
		eMail.setCaretColor(Color.white);
		eMail.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (eMail.getText().equals("")) {
					eMail.setForeground(Color.white);
					eMail.setText("Email");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (eMail.getText().equals("Email") && eMail.getForeground() == Color.white) {
					eMail.setText("");
					eMail.setForeground(Color.white);
				}

			}
		});
		add(eMail);
		
		phoneNumber = new JTextField("PhoneNumber", 30);
		phoneNumber.setBorder(null);
		phoneNumber.setOpaque(false);
		phoneNumber.setForeground(Color.white);
		phoneNumber.setFont(new Font("휴먼모음T", Font.BOLD, 20));
		phoneNumber.setBounds(x+10, y+184, 390, 56);
		phoneNumber.setCaretColor(Color.white);
		phoneNumber.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (phoneNumber.getText().equals("")) {
					phoneNumber.setForeground(Color.white);
					phoneNumber.setText("PhoneNumber");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (phoneNumber.getText().equals("PhoneNumber") && phoneNumber.getForeground() == Color.white) {
					phoneNumber.setText("");
					phoneNumber.setForeground(Color.white);
				}

			}
		});
		add(phoneNumber);
		
		plate = new JTextField("회원정보수정",30);
		plate.setBorder(null);
		plate.setOpaque(false);
		plate.setForeground(Color.white);
		plate.setFont(font);
		plate.setBounds(x+470, y-30, 350, 50);
		add(plate);
		
	}

	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(PanelImage, 0, 0, null);
		g.drawImage(idImage, x, y, null);
		g.drawImage(eMailImage, x, y+90, null);
		g.drawImage(eMailImage, x, y+180, null);
		g.drawImage(NamePlate,x+424,y-27,null);
		g.drawImage(boxImage,x+3, y+359,null);
		g.drawImage(boxImage,x+3, y+269,null);
//		g.drawImage(serviceImage,x+440, y-90,null);
	}

}

