package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MyPage extends JPanel {
	private  File file=null;
	static int MygameNum = 0;
	private JScrollPane scrollpane;
	private JPanel listPanel;
	private JFrame j;
	private Image PanelImage = new ImageIcon(Main.class.getResource("../DieselImage/LoginBox.png")).getImage();
	private Image listImage = new ImageIcon(Main.class.getResource("../DieselImage/����������_���̵巹�̾ƿ�.png")).getImage();
	private JLabel[] gameLabel;
	private JLabel gameImageLabel;
	private JLabel gametitleLabel;
	private ImageIcon refundImage = new ImageIcon(Main.class.getResource("../DieselImage/ȯ��.png"));
	private ImageIcon refundImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/ȯ��_����.png"));
	private ImageIcon startImage = new ImageIcon(Main.class.getResource("../DieselImage/��ư_�÷���.png"));
	private ImageIcon startImageEntered = new ImageIcon(Main.class.getResource("../DieselImage/��ư_�÷���_����.png"));
	private ImageIcon downloadFile = new ImageIcon(Main.class.getResource("../DieselImage/��ư_�ٿ�ε�.png"));
	private ImageIcon downloadFileEntered = new ImageIcon(Main.class.getResource("../DieselImage/��ư_�ٿ�ε�_����.png"));
	private JButton refundGame;
	private JButton startGame;
	private JButton downloadGame;

	MyPage(DiselFrame j) {
		this.j = j;
		PanelImage = PanelImage.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);

	}
	
	
	public void btnChange() {
		file = new File("C:\\cc\\c.PNG");
		if(file.exists()&&DiselFrame.mylist.get(0).getGame_code().equals("RJ000001")) {
			downloadGame.setVisible(false);
			startGame.setVisible(true);
		}
	}
	public void fileclient() throws IOException {

	     // Socket socket = new Socket("127.0.0.1", 9999);
	       Socket socket = new Socket("192.168.0.15", 9999);
	      System.out.println("�������� �Ϸ�");

	      Scanner s = new Scanner(System.in);

	      // �����͸� ����� ���ؼ� ������ ��Ʈ�� ���.

	      InputStream in = socket.getInputStream();
	      DataInputStream dis = new DataInputStream(in);
	      OutputStream out = socket.getOutputStream();
	      DataOutputStream dos = new DataOutputStream(out);


	      String fileNameMsg = "c.PNG";
	       file = new File("C:\\cc\\"+fileNameMsg);
	      dos.writeUTF(fileNameMsg);//�����ڵ� �������� ���ϸ��� ������.
	      JOptionPane.showMessageDialog(null, "������ ���� ��û�� �߽��ϴ�.");
	      JOptionPane.showMessageDialog(null, "�������� ���� �����͸� �޾ƿɴϴ�.");
	      FileOutputStream fos = new FileOutputStream(file);
	      BufferedOutputStream bos = new BufferedOutputStream(fos);
	      byte[] buffer = new byte[1024];

	      while (true) {
	         int data = dis.read(buffer);
	         System.out.println(data);
	         if (data == -1)
	            break;
	  //      fos.write(data);
	         bos.write(buffer,0,data);
	         bos.flush();
	         try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				 JOptionPane.showMessageDialog(null, "�ٿ�ε� �� ������ �߻��߽��ϴ�.");
				e.printStackTrace();
			}

	      }

	      JOptionPane.showMessageDialog(null, "�ٿ�ε尡 �Ϸ� �Ǿ����ϴ�.");

	      // ��Ʈ�� , ���� �ݱ�

	      fos.close();

	      dos.close();
	      dis.close();
	      out.close();
	      in.close();
	      socket.close();

	   }
	public void setPanel() {
		removeAll();
		listPanel = new JPanel();
		listPanel.setBackground(new Color(0, 0, 0, 0));
		listPanel.setLayout(null);
		Integer size = DiselFrame.mylist.size();
	
		ArrayList<Game_info> myGameList = new ArrayList<Game_info>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < DiselFrame.gamelist.size(); j++) {
				if (DiselFrame.mylist.get(i).getGame_code().equals(DiselFrame.gamelist.get(j).getGame_code())) {
					myGameList.add(DiselFrame.gamelist.get(j));
				}
			}
		}
		gametitleLabel = new JLabel();
		try {
			gametitleLabel.setText(myGameList.get(0).getGame_name());
			gametitleLabel.setBounds(100, 30, 590, 35);
			gametitleLabel.setForeground(Color.WHITE);
			gametitleLabel.setFont(new Font("Arian", Font.BOLD, 30));
			gametitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			gameImageLabel = new JLabel(new ImageIcon(
					Main.class.getResource("../DieselImage/" + myGameList.get(0).getGame_bigimage_dir())));
			gameImageLabel.setBounds(30, 10, 710, 580);
			gameImageLabel.add(gametitleLabel);
			gameLabel = new JLabel[size];
			for (int i = 0; i < size; i++) {
				gameLabel[i] = new JLabel("          " + myGameList.get(i).getGame_name());
				gameLabel[i].setBounds(10, 10 + i * 60, 210, 50);
				gameLabel[i].setOpaque(true);
				gameLabel[i].setBackground(new Color(100, 100, 100, 113));
				gameLabel[i].setForeground(Color.WHITE);
				gameLabel[i].setFont(new Font("Arian", Font.BOLD, 20));
				gameLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				ImageIcon icon = new ImageIcon(Main.class.getResource("../DieselImage/"+myGameList.get(i).getGame_icon_dir()));
				JLabel iconlabel = new JLabel(icon);
				gameLabel[i].add(iconlabel);
				iconlabel.setBounds(5,5,40,40);
				int a = i;
				gameLabel[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						MygameNum = a;
						gameImageLabel.setIcon(new ImageIcon(
								Main.class.getResource("../DieselImage/" + myGameList.get(a).getGame_bigimage_dir())));
						gametitleLabel.setText(myGameList.get(a).getGame_name());
						file = new File("C:\\cc\\c.PNG");
						if(file.exists()&&myGameList.get(a).getGame_code().equals("RJ000001")) {
							downloadGame.setVisible(false);
							startGame.setVisible(true);
							
						}else {
							downloadGame.setVisible(true);
							startGame.setVisible(false);
						}
					}
				});
				listPanel.add(gameLabel[i]);

			}
			refundGame = new JButton(refundImage);
			refundGame.setBounds(50, 490, 100, 35);
			refundGame.setBorderPainted(false);
			refundGame.setContentAreaFilled(false);
			refundGame.setFocusPainted(false);
			refundGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					DiselFrame.isRefundPage = true;
					DiselFrame.isMyPage = false;
					DiselFrame.rp.setName();

				}

				@Override
				public void mouseExited(MouseEvent e) {
					refundGame.setIcon(refundImage);
					refundGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					refundGame.setIcon(refundImageEntered);
					refundGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});
			gameImageLabel.add(refundGame);

			
			downloadGame = new JButton(downloadFile);
			downloadGame.setBounds(480, 100, 100, 60);
			downloadGame.setBorderPainted(false);
			downloadGame.setContentAreaFilled(false);
			downloadGame.setFocusPainted(false);
			downloadGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						fileclient();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "�ٿ�ε� �� ������ �߻��߽��ϴ�.");
					}
					file = new File("C:\\cc\\c.PNG");
					if(file.exists()) {
						downloadGame.setVisible(false);
						startGame.setVisible(true);
						
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {
					downloadGame.setIcon(downloadFile);
					downloadGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					downloadGame.setIcon(downloadFileEntered);
					downloadGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
			});
			gameImageLabel.add(downloadGame);
			
			startGame = new JButton(startImage);
			startGame.setBounds(580, 100, 100, 60);
			startGame.setBorderPainted(false);
			startGame.setContentAreaFilled(false);
			startGame.setFocusPainted(false);
			startGame.setVisible(false);
			startGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (DiselFrame.mylist.get(MygameNum).getGame_code().equals("RJ000001")) {
						RhythmGame4.Main.game();
					}
					

				}

				@Override
				public void mouseExited(MouseEvent e) {
					startGame.setIcon(startImage);
					startGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					startGame.setIcon(startImageEntered);
					startGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});
			gameImageLabel.add(startGame);
			scrollpane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(listImage, 0, 0, null);
				}
			};
			scrollpane.setBounds(744, 8, 250, 586);
			add(gameImageLabel);
			add(scrollpane);
			repaint();
		} catch (Exception e) {
			gametitleLabel.setText("������ �����ϴ�.");
			gametitleLabel.setBounds(100, 30, 590, 35);
			gametitleLabel.setForeground(Color.WHITE);
			gametitleLabel.setFont(new Font("Arian", Font.BOLD, 30));
			gametitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			add(gametitleLabel);
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(PanelImage, 0, 0, null);
	}

}
