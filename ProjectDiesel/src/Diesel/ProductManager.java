package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProductManager extends JPanel {
	JFrame j;
	private JTextField productName;
	private JTextField productPrice;
	private JTextField gameGrade;
	private JTextField mainJanre;
	private JTextField subJanre;
	FileReader reader; // 문자 스트림을 읽는 개체
	JTextField fileName1;
	JTextField fileName2;
	JTextField fileName3;
	FileDialog fileDialog;
	ImagePanel ip1, ip2, ip3;
	JLabel storeImage, iconImage;

	ImageIcon Store_image = new ImageIcon(Main.class.getResource("../resource/게임_스토어이미지_null.png"));
	ImageIcon icon_image = new ImageIcon(Main.class.getResource("../resource/게임_아이콘이미지_null.png"));
	ImageIcon upload_basic = new ImageIcon(Main.class.getResource("../resource/관리자_이미지업로드.png"));
	ImageIcon upload_pressed = new ImageIcon(Main.class.getResource("../resource/관리자_이미지업로드_반응.png"));
	ImageIcon okbtn_basic = new ImageIcon(Main.class.getResource("../resource/확인버튼_일반.png"));
	ImageIcon okbtn_pressed = new ImageIcon(Main.class.getResource("../resource/확인버튼_반응.png"));
	ImageIcon canclebtn_basic = new ImageIcon(Main.class.getResource("../resource/취소버튼_일반.png"));
	ImageIcon canclebtn_pressed = new ImageIcon(Main.class.getResource("../resource/취소버튼_반응.png"));

	/**
	 * Create the panel.
	 */

	public ProductManager(DiselFrame j) {
		this.j= j;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
	
		productName = new JTextField("", 20);
		productName.setForeground(Color.WHITE);
		productName.setBackground(Color.GRAY);
		productName.setBounds(100, 100, 450, 45);
		add(productName);

		productPrice = new JTextField("", 20);
		productPrice.setForeground(Color.WHITE);
		productPrice.setBackground(Color.GRAY);
		productPrice.setBounds(100, 200, 450, 45);
		add(productPrice);

		gameGrade = new JTextField("", 20);
		gameGrade.setForeground(Color.WHITE);
		gameGrade.setBackground(Color.GRAY);
		gameGrade.setBounds(100, 300, 450, 45);
		add(gameGrade);

		mainJanre = new JTextField("", 20);
		mainJanre.setForeground(Color.WHITE);
		mainJanre.setBackground(Color.GRAY);
		mainJanre.setBounds(100, 400, 450, 45);
		add(mainJanre);

		subJanre = new JTextField("", 20);
		subJanre.setForeground(Color.WHITE);
		subJanre.setBackground(Color.GRAY);
		subJanre.setBounds(100, 500, 450, 45);
		add(subJanre);

		ip1 = new ImagePanel();
		ip1.setBounds(641, 35, 290, 170);
		add(ip1);

		ip2 = new ImagePanel();
		ip2.setBounds(881, 215, 50, 50);
		add(ip2);

		fileName1 = new JTextField(10);
		fileName1.setForeground(Color.WHITE);
		fileName1.setBackground(Color.DARK_GRAY);
		fileName1.setBounds(641, 289, 143, 33);
		fileName1.setEnabled(false);
		add(fileName1);

		fileName2 = new JTextField(10);
		fileName2.setHorizontalAlignment(SwingConstants.RIGHT);
		fileName2.setForeground(Color.WHITE);
		fileName2.setBackground(Color.DARK_GRAY);
		fileName2.setBounds(788, 356, 143, 40);
		fileName2.setEnabled(false);
		add(fileName2);

		fileName3 = new JTextField(10);
		fileName3.setForeground(Color.WHITE);
		fileName3.setBackground(Color.DARK_GRAY);
		fileName3.setBounds(641, 430, 143, 40);
		fileName3.setEnabled(false);
		add(fileName3);

		JLabel fileType1 = new JLabel("\uC2A4\uD1A0\uC5B4\uC774\uBBF8\uC9C0");
		fileType1.setFont(new Font("휴먼모음T", Font.PLAIN, 17));
		fileType1.setForeground(Color.WHITE);
		fileType1.setBounds(641, 265, 189, 33);
		add(fileType1);

		JLabel fileType2 = new JLabel("\uC544\uC774\uCF58\uC774\uBBF8\uC9C0");
		fileType2.setHorizontalAlignment(SwingConstants.RIGHT);
		fileType2.setForeground(Color.WHITE);
		fileType2.setFont(new Font("휴먼모음T", Font.PLAIN, 17));
		fileType2.setBounds(741, 329, 190, 33);
		add(fileType2);

		JLabel fileType3 = new JLabel("\uB77C\uC774\uBE0C\uB7EC\uB9AC \uB300\uD615\uC774\uBBF8\uC9C0");
		fileType3.setForeground(Color.WHITE);
		fileType3.setFont(new Font("휴먼모음T", Font.PLAIN, 17));
		fileType3.setBounds(641, 406, 189, 33);
		add(fileType3);

		JLabel proName_label = new JLabel("\uC0C1\uD488\uC774\uB984 varchar2(50)");
		proName_label.setForeground(Color.WHITE);
		proName_label.setFont(new Font("휴먼모음T", Font.BOLD, 18));
		proName_label.setBounds(100, 60, 450, 40);
		add(proName_label);

		JLabel proPrice_label = new JLabel("\uC0C1\uD488\uAC00\uACA9 number(8)");
		proPrice_label.setForeground(Color.WHITE);
		proPrice_label.setFont(new Font("휴먼모음T", Font.BOLD, 18));
		proPrice_label.setBounds(100, 160, 450, 40);
		add(proPrice_label);

		JLabel grade_label = new JLabel("\uC774\uC6A9\uB4F1\uAE09 varchar2(5)");
		grade_label.setForeground(Color.WHITE);
		grade_label.setFont(new Font("휴먼모음T", Font.BOLD, 18));
		grade_label.setBounds(100, 260, 205, 40);
		add(grade_label);

		JLabel mainJ_label = new JLabel("\uC8FC \uC7A5\uB974 varchar2(10)");
		mainJ_label.setForeground(Color.WHITE);
		mainJ_label.setFont(new Font("휴먼모음T", Font.BOLD, 18));
		mainJ_label.setBounds(100, 360, 205, 40);
		add(mainJ_label);

		JLabel subJ_label = new JLabel("\uBD80\uC7A5\uB974  varchar2(10)");
		subJ_label.setForeground(Color.WHITE);
		subJ_label.setFont(new Font("휴먼모음T", Font.BOLD, 18));
		subJ_label.setBounds(100, 460, 205, 40);
		add(subJ_label);

		JButton upload1 = new JButton("");
		upload1.setIcon(upload_basic);
		upload1.setBounds(641, 215, 100, 50);
		upload1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				upload1.setIcon(upload_basic);
				upload1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				upload1.setIcon(upload_pressed);
				upload1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		});
		add(upload1);
		upload1.addActionListener(new LoadListener1(this));

		JButton upload2 = new JButton(upload_basic);
		upload2.setIcon(new ImageIcon(ProductManager.class
				.getResource("/resource/\uAD00\uB9AC\uC790_\uC774\uBBF8\uC9C0\uC5C5\uB85C\uB4DC.png")));
		upload2.setBounds(831, 275, 100, 50);
		upload2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				upload2.setIcon(upload_basic);
				upload2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				upload2.setIcon(upload_pressed);
				upload2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(upload2);
		upload2.addActionListener(new LoadListener2(this));

		JButton upload3 = new JButton(upload_basic);
		upload3.setIcon(new ImageIcon(ProductManager.class
				.getResource("/resource/\uAD00\uB9AC\uC790_\uC774\uBBF8\uC9C0\uC5C5\uB85C\uB4DC.png")));
		upload3.setBounds(641, 350, 100, 50);
		upload3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				upload3.setIcon(upload_basic);
				upload3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				upload3.setIcon(upload_pressed);
				upload3.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(upload3);
		upload3.addActionListener(new LoadListener3(this));

		JButton btn_ok = new JButton(okbtn_basic);
		btn_ok.setIcon(
				new ImageIcon(ProductManager.class.getResource("/resource/\uD655\uC778\uBC84\uD2BC_\uC77C\uBC18.png")));
		btn_ok.setBounds(720, 500, 100, 50);
		btn_ok.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (productName.getText().equals("") || productPrice.getText().equals("")
						|| gameGrade.getText().equals("") || mainJanre.getText().equals("")
						|| subJanre.getText().equals("") || fileName1.getText().equals("")
						|| fileName2.getText().equals("") || fileName3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "항목을 전부 기입해주십시오");
				} else {

					try {

						CrudProcess crud = new CrudProcess();
						Game_info gi2 = new Game_info();

						String suffix = String.format("%06d", (DiselFrame.gamelist.size() + 1));

						String gameCode = "RJ" + suffix;

						int year = GregorianCalendar.YEAR;
						int mon = GregorianCalendar.MONTH;
						String month = null;
						if (mon > 10) {
							month = "0" + mon;
						}
						int day = GregorianCalendar.DAY_OF_MONTH;
						String date = null;
						if (day > 10) {
							date = "0" + day;
						}

						String tdate = year + "-" + month + "-" + date;

						Sales_info si = new Sales_info(gameCode, productName.getText(), 0,
								Integer.parseInt(productPrice.getText()), 0);

						Game_info gi = new Game_info(gameCode, productName.getText(),
								Integer.parseInt(productPrice.getText()), tdate, gameGrade.getText(),
								mainJanre.getText(), subJanre.getText(), fileName1.getText(), fileName3.getText(),
								fileName2.getText());
						crud.insertGame(gi);
						crud.insertSales(si);

						JOptionPane.showMessageDialog(null, "등록");

						productName.setText("");
						productPrice.setText("");
						gameGrade.setText("");
						mainJanre.setText("");
						subJanre.setText("");
						fileName1.setText("");
						fileName2.setText("");
						fileName3.setText("");
						ip1.image = new ImageIcon(Main.class.getResource("../resource/게임_스토어이미지_null.png")).getImage();
						ip2.image = new ImageIcon(Main.class.getResource("../resource/게임_스토어이미지_null.png")).getImage();
						DiselFrame.isProductManagerPage = false;
						DiselFrame.isSalesManagerPage = true;
						DiselFrame.gamelist = (ArrayList<Game_info>) crud.selectGameInfo();
						
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "등록에 실패했습니다");
					}
				}
				 
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_ok.setIcon(okbtn_basic);
				btn_ok.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn_ok.setIcon(okbtn_pressed);
				btn_ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(btn_ok);

		JButton btn_cancle = new JButton(canclebtn_basic);
		btn_cancle.setIcon(
				new ImageIcon(ProductManager.class.getResource("/resource/\uCDE8\uC18C\uBC84\uD2BC_\uC77C\uBC18.png")));
		btn_cancle.setBounds(850, 500, 100, 50);
		btn_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				productName.setText("");
				productPrice.setText("");
				gameGrade.setText("");
				mainJanre.setText("");
				subJanre.setText("");
				fileName1.setText("");
				fileName2.setText("");
				fileName3.setText("");
				ip1.image = new ImageIcon(Main.class.getResource("../resource/게임_스토어이미지_null.png")).getImage();
				ip2.image = new ImageIcon(Main.class.getResource("../resource/게임_스토어이미지_null.png")).getImage();
				ip3.image = new ImageIcon(Main.class.getResource("../resource/게임_스토어이미지_null.png")).getImage();
				DiselFrame.isProductManagerPage = false;
				 
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_cancle.setIcon(canclebtn_basic);
				btn_cancle.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn_cancle.setIcon(canclebtn_pressed);
				btn_cancle.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(btn_cancle);

	}
}

class ImagePanel extends JPanel {
	Image image = new ImageIcon(Main.class.getResource("../resource/게임_스토어이미지_null.png")).getImage();
	Toolkit toolkit = this.getToolkit();

	void setPath(String path) {
		image = toolkit.getImage(path);
	}

	@Override
	public void paint(Graphics arg0) {
		int w = this.getWidth();
		int h = this.getHeight();
		arg0.drawImage(image, 0, 0, w, h, this);
	}
}

class LoadListener1 implements ActionListener {
	// 원하는 이미지를 클릭
	ProductManager pm;
	JFrame jf = new JFrame();
	FileDialog fileDialog;

	LoadListener1(ProductManager p) {
		this.pm = p;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(jf, "파일열기", FileDialog.LOAD);
		fileDialog.setVisible(true);
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		String path2 = fileDialog.getFile();
		try {
			File imagefile = new File(path);
			FileInputStream input = new FileInputStream(imagefile);
			OutputStream output = new FileOutputStream(new File(("src\\DieselImage\\" + path2)));
			int readBuffer = 0;
			byte[] buffer = new byte[512];
			while ((readBuffer = input.read(buffer)) != -1) {
				output.write(buffer, 0, readBuffer);

			}
			// 생성된 InputStream Object를 닫아준다.
			input.close();
			// 생성된 OutputStream Object를 닫아준다.
			output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pm.fileName1.setText(path2);
		pm.ip1.setPath(path);
		pm.ip1.repaint();
		
	}

//	public void actionPerformed(ActionEvent arg0) {
//		JFileChooser jfc = new JFileChooser(new File("C:\\Users\\19-2-8\\Pictures"));
//		jfc.setPreferredSize(new Dimension(800, 400));
//		int returnValue = jfc.showOpenDialog(pm);
//		if (returnValue == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = jfc.getSelectedFile();
//			String path = selectedFile.getName();
//			String path2 = selectedFile.getAbsolutePath();
//			pm.fileName1.setText(path);
//			pm.ip1.setPath(path2);
//			pm.ip1.repaint();
//
//		}
//
//	}
}

class LoadListener2 implements ActionListener {
	// 원하는 이미지를 클릭
	ProductManager pm;
	FileDialog fileDialog;
	JFrame jf = new JFrame();

	LoadListener2(ProductManager p) {
		this.pm = p;
	}

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		JFileChooser jfc = new JFileChooser(new File("C:\\Users\\19-2-8\\Pictures"));
//		jfc.setPreferredSize(new Dimension(800, 400));
//		int returnValue = jfc.showOpenDialog(pm);
//		if (returnValue == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = jfc.getSelectedFile();
//			String path = selectedFile.getName();
//			String path2 = selectedFile.getAbsolutePath();
//			pm.fileName2.setText(path);
//			pm.ip2.setPath(path2);
//			pm.ip2.repaint();
//
//		}
//
//	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(jf, "파일열기", FileDialog.LOAD);
		fileDialog.setVisible(true);
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		String path2 = fileDialog.getFile();
		pm.fileName2.setText(path2);
		pm.ip2.setPath(path);
		pm.ip2.repaint();
		try {
			File imagefile = new File(path);
			FileInputStream input = new FileInputStream(imagefile);
			OutputStream output = new FileOutputStream(new File(("src\\DieselImage\\" + path2)));
			int readBuffer = 0;
			byte[] buffer = new byte[512];
			while ((readBuffer = input.read(buffer)) != -1) {
				output.write(buffer, 0, readBuffer);

			}
			// 생성된 InputStream Object를 닫아준다.
			input.close();
			// 생성된 OutputStream Object를 닫아준다.
			output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class LoadListener3 implements ActionListener {
	// 원하는 이미지를 클릭
	ProductManager pm;
	FileDialog fileDialog;
	JFrame jf = new JFrame();

	LoadListener3(ProductManager p) {
		this.pm = p;
	}

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		JFileChooser jfc = new JFileChooser(new File("C:\\Users\\19-2-8\\Pictures"));
//		jfc.setPreferredSize(new Dimension(800, 400));
//		int returnValue = jfc.showOpenDialog(pm);
//		if (returnValue == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = jfc.getSelectedFile();
//			String path = selectedFile.getName();
//			pm.fileName3.setText(path);
//
//		}
//
//	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(jf, "파일열기", FileDialog.LOAD);
		fileDialog.setVisible(true);
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		String path2 = fileDialog.getFile();
		pm.fileName3.setText(path2);
		try {
			File imagefile = new File(path);
			FileInputStream input = new FileInputStream(imagefile);
			OutputStream output = new FileOutputStream(new File(("src\\DieselImage\\" + path2)));
			int readBuffer = 0;
			byte[] buffer = new byte[512];
			while ((readBuffer = input.read(buffer)) != -1) {
				output.write(buffer, 0, readBuffer);

			}
			// 생성된 InputStream Object를 닫아준다.
			input.close();
			// 생성된 OutputStream Object를 닫아준다.
			output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
