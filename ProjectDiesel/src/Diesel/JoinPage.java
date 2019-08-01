package Diesel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicTextAreaUI;

public class JoinPage extends JPanel {
	private JFrame j;
	private JTextField id, name, eMail, phoneNumber;
	private JPasswordField password, confirm;
	private JComboBox<String> country;
	private JPanel terms;
	private Image textField = new ImageIcon(Main.class.getResource("../DieselImage/TextField.png")).getImage();
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image idImage;
	private Image eMailImage;
	private Image checkBox= new ImageIcon(Main.class.getResource("../DieselImage/체크박스.png")).getImage();
	private Image ischeck;
	private Image serviceImage;
	private String[] coutryList= {"Country","한국","미국","일본","짱깨"};
	private JScrollPane service;
	private boolean isConfirm=false;
	private JButton ok,cancel;
	private ImageIcon okImage=new ImageIcon(Main.class.getResource("../DieselImage/TextField.png"));
	private ImageIcon cancelImage;
	
	int x,y;
	JoinPage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		idImage = textField.getScaledInstance(300, 60, Image.SCALE_SMOOTH);
		eMailImage = textField.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
		serviceImage = textField.getScaledInstance(360, 360, Image.SCALE_SMOOTH);
		x=80;
		y=140;
		setTextField();
		setComboBox();
		setJScrollPane();
	}
	
	
	public void setJScrollPane() {
		JTextArea str=new JTextArea();
		
		service=new JScrollPane(str) {
			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
			}
		};
		str.setFont(new Font("arian", Font.BOLD, 20));
		str.setForeground(Color.white);
		BasicTextAreaUI bt=new BasicTextAreaUI(); 
		str.setUI(bt);
		str.setBackground(new Color(80, 80, 80));
		str.setText("                    나는 천재다\n              그렇다 나는천재다.");
		str.setEditable(false);
		service.setBounds(x+443, y-85, 353, 350);
		
		add(service);
	}
	
	public void setComboBox() {
		country=new JComboBox<String>(coutryList);
		country.setOpaque(false);
		country.setBackground(new Color(48,48,48));
		country.setBorder(null);
		country.setOpaque(false);
		
	    country.getSelectedIndex();
		country.setForeground(Color.gray);
		country.setFont(new Font("arian", Font.BOLD, 20));
		country.setBounds(x+10, y+364, 280, 52);
		country.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource(); 
				int index=cb.getSelectedIndex();
				if(index==0) {
					country.setForeground(Color.gray);
				}else {
					country.setForeground(Color.white);
				}
				
			}
		});
		BasicComboBoxUI bs=new BasicComboBoxUI();
	    country.setUI(bs);  
		add(country);
	}

	public void setTextField() {
		id = new JTextField("ID", 20);
		id.setBorder(null);
		id.setOpaque(false);
		id.setForeground(Color.gray);
		id.setFont(new Font("arian", Font.BOLD, 20));
		id.setBounds(x+10, y+4-90, 290, 56);
		id.setCaretColor(Color.white);
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
		
		
		
		password = new JPasswordField("Password", 20);
		password.setBorder(null);
		password.setOpaque(false);
		password.setForeground(Color.gray);
		password.setFont(new Font("arian", Font.BOLD, 20));
		password.setBounds(x+10, y+4, 290,56 );
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
				if (password.getText().equals("Password") && password.getForeground() == Color.gray) {
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
		confirm.setBounds(x+10, y+94, 290,56 );
		confirm.setCaretColor(Color.white);
		confirm.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (confirm.getText().equals("")) {
					confirm.setForeground(Color.gray);
					confirm.setText("Confirm");
				}
				if(confirm.getText().equals(password.getText())){
					isConfirm=true;
					repaint();
				}else {
					isConfirm=false;
					repaint();
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (confirm.getText().equals("Confirm") && confirm.getForeground() == Color.gray) {
					confirm.setText("");
					confirm.setForeground(Color.white);
				}
			}
		});
		add(confirm);
		
		eMail = new JTextField("Email", 30);
		eMail.setBorder(null);
		eMail.setOpaque(false);
		eMail.setForeground(Color.gray);
		eMail.setFont(new Font("arian", Font.BOLD, 20));
		eMail.setBounds(x+10, y+184, 390, 56);
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
				if (eMail.getText().equals("Email") && eMail.getForeground() == Color.gray) {
					eMail.setText("");
					eMail.setForeground(Color.white);
				}

			}
		});
		add(eMail);
		
		phoneNumber = new JTextField("PhoneNumber", 30);
		phoneNumber.setBorder(null);
		phoneNumber.setOpaque(false);
		phoneNumber.setForeground(Color.gray);
		phoneNumber.setFont(new Font("arian", Font.BOLD, 20));
		phoneNumber.setBounds(x+10, y+274, 390, 56);
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
				if (phoneNumber.getText().equals("PhoneNumber") && phoneNumber.getForeground() == Color.gray) {
					phoneNumber.setText("");
					phoneNumber.setForeground(Color.white);
				}

			}
		});
		add(phoneNumber);
		
		
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(PanelImage, 0, 0, null);
		g.drawImage(idImage, x, y-90, null);
		g.drawImage(idImage, x, y, null);
		g.drawImage(idImage, x, y+90, null);
		g.drawImage(checkBox,x+330,y+100,null);
		if(isConfirm) {
			ischeck= new ImageIcon(Main.class.getResource("../DieselImage/체크박스_true.png")).getImage();
		}else {
			ischeck= new ImageIcon(Main.class.getResource("../DieselImage/체크박스_false.png")).getImage();
		}
		g.drawImage(ischeck,x+330,y+100,null);
		g.drawImage(eMailImage, x, y+180, null);
		g.drawImage(eMailImage, x, y+270, null);
		g.drawImage(idImage, x, y+360, null);
		g.drawImage(serviceImage,x+440, y-90,null);
	}

}
