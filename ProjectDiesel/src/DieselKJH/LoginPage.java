package DieselKJH;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JLabel{
	JFrame j;
	JTextField idField;
	JPasswordField passwordField;
	ImageIcon loginBoxImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png"));
	ImageIcon loginBasic= new ImageIcon(Main.class.getResource("../DieselImage/로그인_로그인_일반.png"));
	ImageIcon loginPressed= new ImageIcon(Main.class.getResource("../DieselImage/로그인_로그인_선택.png"));
	ImageIcon JoinBasic= new ImageIcon(Main.class.getResource("../DieselImage/로그인_회원가입_일반.png"));
	ImageIcon JoinPressed= new ImageIcon(Main.class.getResource("../DieselImage/로그인_회원가입_선택.png"));
	ImageIcon exitBasic= new ImageIcon(Main.class.getResource("../DieselImage/로그인_종료_일반.png"));
	ImageIcon exitPressed= new ImageIcon(Main.class.getResource("../DieselImage/로그인_종료_선택.png"));
	ImageIcon FindBasic= new ImageIcon(Main.class.getResource("../DieselImage/로그인_계정찾기_일반.png"));
	ImageIcon FindPressed= new ImageIcon(Main.class.getResource("../DieselImage/로그인_계정찾기_선택.png"));
	private JButton loginButton;
	private JButton JoinButton;
	private JButton exitButton;
	private JButton FindButton;


	LoginPage(DiselFrame j) {
		this.j = j;
		
		
		loginBoxImage = new ImageIcon(loginBoxImage.getImage().getScaledInstance(400, 240, Image.SCALE_FAST));
		this.setIcon(loginBoxImage);
		// 로그인 버튼
		loginButton = new JButton(loginBasic);
		loginButton.setBounds(280, 45, 90, 90);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(idField.getForeground() == Color.gray) {
					JOptionPane.showMessageDialog(null,"ID를 입력해 주세요.");
					return;
				}
				if(passwordField.getForeground() == Color.gray) {
					JOptionPane.showMessageDialog(null,"비밀번호를 입력해 주세요.");
					return;
				}
				if(idField.getText().equals("fuko")&&passwordField.getText().equals("1234")) {
					DiselFrame.isLoginPage = false;
					DiselFrame.isMainPage = true;
					DiselFrame.isButtonPage= true;
					passwordField.setText("");
					idField.setText("");
				}else {
					JOptionPane.showMessageDialog(null,"ID, 비밀번호를 정확하게 입력해 주세요.");
					passwordField.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setIcon(loginBasic);
				loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setIcon(loginPressed);
				loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		this.add(loginButton);

		// 회원가입 버튼
		JoinButton = new JButton(JoinBasic);
		JoinButton.setBounds(45, 155, 100, 50);
		JoinButton.setBorderPainted(false);
		JoinButton.setContentAreaFilled(false);
		JoinButton.setFocusPainted(false);
		JoinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiselFrame.isLoginPage=false;
				DiselFrame.isJoinPage=true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JoinButton.setIcon(JoinBasic);
				JoinButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JoinButton.setIcon(JoinPressed);
				JoinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		this.add(JoinButton);

		// 계정찾기
		FindButton = new JButton(FindBasic);
		FindButton.setBounds(155, 155, 100, 50);
		FindButton.setBorderPainted(false);
		FindButton.setContentAreaFilled(false);
		FindButton.setFocusPainted(false);
		FindButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiselFrame.isLoginPage = false;
				DiselFrame.isFindPage = true;
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				FindButton.setIcon(FindBasic);
				FindButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				FindButton.setIcon(FindPressed);
				FindButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		this.add(FindButton);

		
		
		
		//종료 버튼
		exitButton = new JButton(exitBasic);
		exitButton.setBounds(265, 155, 100, 50);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitBasic);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitPressed);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		this.add(exitButton);

		
		
		
		passwordField = new JPasswordField("Password", 20);
		passwordField.setForeground(Color.gray);
		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (passwordField.getText().equals("")) {
					passwordField.setForeground(Color.gray);
					passwordField.setText("Password");

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (passwordField.getText().equals("Password") && passwordField.getForeground() == Color.gray) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
				}
			}
		});
		passwordField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(idField.getForeground() == Color.gray) {
						JOptionPane.showMessageDialog(null,"ID를 입력해 주세요.");
						return;
					}
					if(passwordField.getForeground() == Color.gray) {
						JOptionPane.showMessageDialog(null,"비밀번호를 입력해 주세요.");
						return;
					}
					if(idField.getText().equals("fuko")&&passwordField.getText().equals("1234")) {
						DiselFrame.isLoginPage = false;
						DiselFrame.isMainPage = true;
						DiselFrame.isButtonPage= true;
						passwordField.setText("");
						idField.setText("");
					}else {
						JOptionPane.showMessageDialog(null,"ID, 비밀번호를 정확하게 입력해 주세요.");
						passwordField.setText("");
						
					}
				}
			}
			
			
		});

		idField = new JTextField("ID", 20);
		idField.setForeground(Color.gray);
		idField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (idField.getText().equals("")) {
					idField.setForeground(Color.gray);
					idField.setText("ID");

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (idField.getText().equals("ID") && idField.getForeground() == Color.gray) {
					idField.setText("");
					idField.setForeground(Color.BLACK);
				}

			}
		});
		
		idField.setBounds(50, 50, 200, 30);
		passwordField.setBounds(50, 100, 200, 30);
		this.add(idField);
		this.add(passwordField);
		
		
		
	}
	
}
