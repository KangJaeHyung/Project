package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RefundCash extends JPanel {

	JFrame j;

	ImageIcon cc_basic = new ImageIcon(Main.class.getResource("../resource/환불 일반.png"));
	ImageIcon cc_pressed = new ImageIcon(Main.class.getResource("../resource/환불 반응.png"));
	ImageIcon cl1 = new ImageIcon(Main.class.getResource("../resource/환불_1000.png"));
	ImageIcon cl2 = new ImageIcon(Main.class.getResource("../resource/환불_5000.png"));
	ImageIcon cl3 = new ImageIcon(Main.class.getResource("../resource/환불_10000.png"));
	ImageIcon cl4 = new ImageIcon(Main.class.getResource("../resource/환불_50000.png"));
	ImageIcon nameP = new ImageIcon(Main.class.getResource("../resource/네임플레이트.png"));
	ImageIcon background_logo = new ImageIcon(Main.class.getResource("../resource/디젤_화이트.png"));
	ImageIcon btnImage = new ImageIcon(Main.class.getResource("../resource/환불_반응.png"));
	JButton changeBtn;

	public RefundCash(DiselFrame j) {
		this.j = j;

		changeBtn = new JButton(btnImage);
		changeBtn.setBounds(50, 50, 100, 35);
		changeBtn.setBorderPainted(false);
		changeBtn.setContentAreaFilled(false);
		changeBtn.setFocusPainted(false);
		changeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		changeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiselFrame.isRefundCash = false;
				DiselFrame.isRecharge = true;
			}
		});
		add(changeBtn);

		setBackground(Color.DARK_GRAY);
		setLayout(null);

		JLabel cl1000 = new JLabel(cl1);
		cl1000.setBounds(91, 149, 400, 80);
		add(cl1000);

		JLabel cl5000 = new JLabel(cl2);
		cl5000.setBounds(91, 258, 400, 80);
		add(cl5000);

		JLabel cl10000 = new JLabel(cl3);
		cl10000.setBounds(91, 371, 400, 80);
		add(cl10000);

		JLabel cl50000 = new JLabel(cl4);
		cl50000.setBounds(91, 484, 400, 80);
		add(cl50000);

		JButton cc_btn1 = new JButton(cc_basic);
		cc_btn1.setBounds(492, 149, 200, 80);
		cc_btn1.setBorderPainted(false);
		cc_btn1.setContentAreaFilled(false);
		cc_btn1.setFocusPainted(false);
		cc_btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "1000원 환불 하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					return;
				} else if (result == JOptionPane.YES_OPTION) {
					if (DiselFrame.loginCustomer.getUser_cash() >= 1000) {
						refund(-1000);
						JOptionPane.showMessageDialog(null, "환불 되었습니다.");
					}else {
						JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
					}
				} else {
					return;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cc_btn1.setIcon(cc_basic);
				cc_btn1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cc_btn1.setIcon(cc_pressed);
				cc_btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
		add(cc_btn1);

		JButton cc_btn2 = new JButton(cc_basic);
		cc_btn2.setBounds(492, 258, 200, 80);
		cc_btn2.setBorderPainted(false);
		cc_btn2.setContentAreaFilled(false);
		cc_btn2.setFocusPainted(false);
		cc_btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "5000원 환불 하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					return;
				} else if (result == JOptionPane.YES_OPTION) {
					if (DiselFrame.loginCustomer.getUser_cash() >= 5000) {
						refund(-5000);
						JOptionPane.showMessageDialog(null, "환불 되었습니다.");
					}else {
						JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
					}
				} else {
					return;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cc_btn2.setIcon(cc_basic);
				cc_btn2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cc_btn2.setIcon(cc_pressed);
				cc_btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
		add(cc_btn2);

		JButton cc_btn3 = new JButton(cc_basic);
		cc_btn3.setBounds(492, 371, 200, 80);
		cc_btn3.setBorderPainted(false);
		cc_btn3.setContentAreaFilled(false);
		cc_btn3.setFocusPainted(false);
		cc_btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "10000원 환불 하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					return;
				} else if (result == JOptionPane.YES_OPTION) {
					if (DiselFrame.loginCustomer.getUser_cash() >= 10000) {
						refund(-10000);
						JOptionPane.showMessageDialog(null, "환불 되었습니다.");
					}else {
						JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
					}
				} else {
					return;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cc_btn3.setIcon(cc_basic);
				cc_btn3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cc_btn3.setIcon(cc_pressed);
				cc_btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
		add(cc_btn3);

		JButton cc_btn4 = new JButton(cc_basic);
		cc_btn4.setBounds(492, 484, 200, 80);
		cc_btn4.setBorderPainted(false);
		cc_btn4.setContentAreaFilled(false);
		cc_btn4.setFocusPainted(false);
		cc_btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "50000원 환불 하시겠습니까?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					return;
				} else if (result == JOptionPane.YES_OPTION) {
					if (DiselFrame.loginCustomer.getUser_cash() >= 50000) {
						refund(-50000);
						JOptionPane.showMessageDialog(null, "환불 되었습니다.");
					}else {
						JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
					}
				} else {
					return;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cc_btn4.setIcon(cc_basic);
				cc_btn4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cc_btn4.setIcon(cc_pressed);
				cc_btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
		add(cc_btn4);

		JLabel pageName = new JLabel("환불페이지");
		pageName.setFont(new Font("휴먼모음T", Font.BOLD, 30));
		pageName.setForeground(Color.WHITE);
		pageName.setBounds(819, 4, 169, 49);

		add(pageName);

		JLabel namePlate = new JLabel(nameP);
		namePlate.setBounds(750, 1, 250, 50);
		add(namePlate);

		JLabel bg_logo = new JLabel(background_logo);
		bg_logo.setBounds(535, 76, 707, 573);
		add(bg_logo);

	}

	public void refund(Integer num) {
		String str = DiselFrame.loginCustomer.getUser_id();
		CrudProcess crud = new CrudProcess();

		crud.updateCash(num, str);
		Charge_refund refund = new Charge_refund();
		String code = crud.selectRefundCode();
		int number = 0;
		if (code != null) {
			String[] codeSplit = code.split("R");
			number = Integer.parseInt(codeSplit[1]);
		}
		number = number + 1;
		String s = String.format("%05d", number);
		refund.setMoney_order_code("R" + s);
		refund.setUser_id(DiselFrame.loginCustomer.getUser_id());
		refund.setRefund_cash(num);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		Date date = new Date();
		refund.setRefund_date(simple.format(date));
		
		crud.insertRefund(refund);
	}
}