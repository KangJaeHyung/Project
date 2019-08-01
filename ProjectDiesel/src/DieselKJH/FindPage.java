package DieselKJH;

import java.awt.CardLayout;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Diesel.Main;

public class FindPage extends JPanel {
	private	JFrame j;
	private	Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private	Image cardImage = new ImageIcon(Main.class.getResource("../DieselImage/레이아웃2_850x450.png")).getImage();
	private	ImageIcon idImage = new ImageIcon(Main.class.getResource("../DieselImage/레이아웃2_ID찾기.png"));
	private	ImageIcon idImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/레이아웃2_ID찾기_반응.png"));
	private	ImageIcon passwordImage = new ImageIcon(Main.class.getResource("../DieselImage/레이아웃2_비번찾기.png"));
	private	ImageIcon passwordImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/레이아웃2_비번찾기_반응.png"));
	private Image textField = new ImageIcon(Main.class.getResource("../DieselImage/TextField.png")).getImage();
	private ImageIcon okImage=new ImageIcon(Main.class.getResource("../DieselImage/확인버튼_일반.png"));
	private ImageIcon okImageEnter=new ImageIcon(Main.class.getResource("../DieselImage/확인버튼_반응.png"));
	private ImageIcon cancelImage=new ImageIcon(Main.class.getResource("../DieselImage/취소버튼_일반.png"));
	private ImageIcon cancelImageEnter=new ImageIcon(Main.class.getResource("../DieselImage/취소버튼_반응.png"));
	
	private	CardLayout card;
	private	JPanel cardPanel;
	private	JPanel findID, findPassword;
	private	JButton ok, cancle, idButton, passwordButton;
	private	JButton ok2, cancle2;
	private JTextField idEmail,idPhone;
	private JTextField passEmail,passId,passPhone;
	private int x=50,y=100;
	FindPage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
		textField = textField.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
		ok=new JButton(okImage);
		cancle=new JButton(cancelImage);
		ok2=new JButton(okImage);
		cancle2=new JButton(cancelImage);
		setCardLayout();
		setButton();
		setIdPanel();
		setPasswordPanel();
	}

	public void setIdPanel() {
		idEmail = new JTextField("Email", 20);
		idEmail.setBorder(null);
		idEmail.setOpaque(false);
		idEmail.setForeground(Color.gray);
		idEmail.setFont(new Font("arian", Font.BOLD, 20));
		idEmail.setBounds(x+10, y+4, 380, 52);
		idEmail.setCaretColor(Color.white);
		idEmail.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (idEmail.getText().equals("")) {
					idEmail.setForeground(Color.gray);
					idEmail.setText("Email");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (idEmail.getText().equals("Email") && idEmail.getForeground() == Color.gray) {
					idEmail.setText("");
					idEmail.setForeground(Color.white);
				}

			}
		});
		findID.add(idEmail);
		
		idPhone = new JTextField("PhoneNumber", 20);
		idPhone.setBorder(null);
		idPhone.setOpaque(false);
		idPhone.setForeground(Color.gray);
		idPhone.setFont(new Font("arian", Font.BOLD, 20));
		idPhone.setBounds(x+10, y+94, 380, 52);
		idPhone.setCaretColor(Color.white);
		idPhone.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (idPhone.getText().equals("")) {
					idPhone.setForeground(Color.gray);
					idPhone.setText("PhoneNumber");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (idPhone.getText().equals("PhoneNumber") && idPhone.getForeground() == Color.gray) {
					idPhone.setText("");
					idPhone.setForeground(Color.white);
				}

			}
		});
		findID.add(idPhone);
		
		
		ok.setBounds(305, y+230, 100, 50);
		ok.setBorderPainted(false);
		ok.setContentAreaFilled(false);
		ok.setFocusPainted(false);
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ok.setIcon(okImage);
				ok.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ok.setIcon(okImageEnter);
				ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		findID.add(ok);
		
		cancle.setBounds(445, y+230, 100, 50);
		cancle.setBorderPainted(false);
		cancle.setContentAreaFilled(false);
		cancle.setFocusPainted(false);
		cancle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiselFrame.isFindPage=false;
				DiselFrame.isLoginPage=true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cancle.setIcon(cancelImage);
				cancle.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cancle.setIcon(cancelImageEnter);
				cancle.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		findID.add(cancle);
		
		
		
	}

	public void setPasswordPanel() {
		
		passId = new JTextField("ID", 20);
		passId.setBorder(null);
		passId.setOpaque(false);
		passId.setForeground(Color.gray);
		passId.setFont(new Font("arian", Font.BOLD, 20));
		passId.setBounds(x+10, y+4-45, 380, 52);
		passId.setCaretColor(Color.white);
		passId.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (passId.getText().equals("")) {
					passId.setForeground(Color.gray);
					passId.setText("ID");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (passId.getText().equals("ID") && passId.getForeground() == Color.gray) {
					passId.setText("");
					passId.setForeground(Color.white);
				}

			}
		});
		findPassword.add(passId);
		
		
		
		passEmail = new JTextField("Email", 20);
		passEmail.setBorder(null);
		passEmail.setOpaque(false);
		passEmail.setForeground(Color.gray);
		passEmail.setFont(new Font("arian", Font.BOLD, 20));
		passEmail.setBounds(x+10, y+4+45, 380, 52);
		passEmail.setCaretColor(Color.white);
		passEmail.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (passEmail.getText().equals("")) {
					passEmail.setForeground(Color.gray);
					passEmail.setText("Email");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (passEmail.getText().equals("Email") && passEmail.getForeground() == Color.gray) {
					passEmail.setText("");
					passEmail.setForeground(Color.white);
				}

			}
		});
		findPassword.add(passEmail);
		
		passPhone = new JTextField("PhoneNumber", 20);
		passPhone.setBorder(null);
		passPhone.setOpaque(false);
		passPhone.setForeground(Color.gray);
		passPhone.setFont(new Font("arian", Font.BOLD, 20));
		passPhone.setBounds(x+10, y+94+45, 380, 52);
		passPhone.setCaretColor(Color.white);
		passPhone.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (passPhone.getText().equals("")) {
					passPhone.setForeground(Color.gray);
					passPhone.setText("PhoneNumber");
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (passPhone.getText().equals("PhoneNumber") && passPhone.getForeground() == Color.gray) {
					passPhone.setText("");
					passPhone.setForeground(Color.white);
				}

			}
		});
		findPassword.add(passPhone);
		
		
		ok2.setBounds(305, y+230, 100, 50);
		ok2.setBorderPainted(false);
		ok2.setContentAreaFilled(false);
		ok2.setFocusPainted(false);
		ok2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ok2.setIcon(okImage);
				ok2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ok2.setIcon(okImageEnter);
				ok2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		findPassword.add(ok2);
		
		cancle2.setBounds(445, y+230, 100, 50);
		cancle2.setBorderPainted(false);
		cancle2.setContentAreaFilled(false);
		cancle2.setFocusPainted(false);
		cancle2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiselFrame.isFindPage=false;
				DiselFrame.isLoginPage=true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cancle2.setIcon(cancelImage);
				cancle2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cancle2.setIcon(cancelImageEnter);
				cancle2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		findPassword.add(cancle2);
	}

	public void setButton() {

		idButton = new JButton(idImageEntered);
		idButton.setBounds(75, 30, 250, 60);
		idButton.setBorderPainted(false);
		idButton.setContentAreaFilled(false);
		idButton.setFocusPainted(false);
		idButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(cardPanel, "1");
				idButton.setIcon(idImageEntered);
				passwordButton.setIcon(passwordImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
//				idButton.setIcon(idImage);
				idButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
//				idButton.setIcon(idImageEntered);
				if (idButton.getIcon().equals(idImage)) {
					idButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
		});
		add(idButton);

		passwordButton = new JButton(passwordImage);
		passwordButton.setBounds(325, 30, 250, 60);
		passwordButton.setBorderPainted(false);
		passwordButton.setContentAreaFilled(false);
		passwordButton.setFocusPainted(false);
		passwordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(cardPanel, "2");
				idButton.setIcon(idImage);
				passwordButton.setIcon(passwordImageEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
//				passwordButton.setIcon(passwordImage);
				if (passwordButton.isEnabled()) {
					passwordButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (passwordButton.getIcon().equals(passwordImage)) {
					passwordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
		});
		add(passwordButton);

	}

	public void setCardLayout() {
		cardPanel = new JPanel();
		cardPanel.setBounds(75, 90, 850, 450);
		card = new CardLayout();
		cardPanel.setLayout(card);
		findID = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(cardImage, 0, 0, null);
				g.drawImage(textField,x,y,null);
				g.drawImage(textField,x,y+90,null);
			}
		};
		findID.setLayout(null);
		findPassword = new JPanel();

		findPassword = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(cardImage, 0, 0, null);
				g.drawImage(textField,x,y-45,null);
				g.drawImage(textField,x,y+45,null);
				g.drawImage(textField,x,y+135,null);
			}
		};
		findPassword.setLayout(null);

		cardPanel.add(findID, "1");
		cardPanel.add(findPassword, "2");
		add(cardPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
		
	}

}
