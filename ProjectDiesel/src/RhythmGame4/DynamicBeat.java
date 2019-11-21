package RhythmGame4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	public static Image background = new ImageIcon(Main.class.getResource("../Images/IntroBackGround2.gif")).getImage();

	private ImageIcon startBasicButton = new ImageIcon(Main.class.getResource("../Images/startButtonBasic.png"));
	private ImageIcon endBasicButton = new ImageIcon(Main.class.getResource("../Images/quitButtonBasic.png"));
	private ImageIcon startEnteredButton = new ImageIcon(Main.class.getResource("../Images/startButtonEntered.png"));
	private ImageIcon endEnteredButton = new ImageIcon(Main.class.getResource("../Images/quitButtonEntered.png"));

	private ImageIcon leftBasicButton = new ImageIcon(Main.class.getResource("../Images/Arrowleft.png"));
	private ImageIcon laftEnteredButton = new ImageIcon(Main.class.getResource("../Images/Arrowleftclick.png"));
	private ImageIcon rightBasicButton = new ImageIcon(Main.class.getResource("../Images/Arrowright.png"));
	private ImageIcon rightEnteredButton = new ImageIcon(Main.class.getResource("../Images/Arrowrightclick.png"));
	private ImageIcon quitBasicButton = new ImageIcon(Main.class.getResource("../Images/Quit.png"));
	private ImageIcon quitEnteredButton = new ImageIcon(Main.class.getResource("../Images/Quitentered.png"));
	private ImageIcon hardBasicButton = new ImageIcon(Main.class.getResource("../Images/hardButtonBasic.png"));
	private ImageIcon hardEnteredButton = new ImageIcon(Main.class.getResource("../Images/hardButtonEntered.png"));
	private ImageIcon easyBasicButton = new ImageIcon(Main.class.getResource("../Images/easyButtonBasic.png"));
	private ImageIcon easyEnteredButton = new ImageIcon(Main.class.getResource("../Images/easyButtonEntered.png"));
	private ImageIcon backBasicButton = new ImageIcon(Main.class.getResource("../Images/backButtonBasic.png"));
	private ImageIcon backEnteredButton = new ImageIcon(Main.class.getResource("../Images/backButtonEntered.png"));

	private JButton backButton = new JButton(backBasicButton);
	private JButton easyButton = new JButton(easyBasicButton);
	private JButton hardButton = new JButton(hardBasicButton);
	private JButton startButton = new JButton(startBasicButton);
	private JButton endButton = new JButton(endBasicButton);
	private JButton leftButton = new JButton(leftBasicButton);
	private JButton rightButton = new JButton(rightBasicButton);
	private JButton quitButton = new JButton(quitBasicButton);

	private int mouseX, mouseY;

	public static boolean isMainScreen = false;
	public static boolean isGameScreen = false;
	public static boolean isResultScreen = false;
	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private int nowSelected = 0;
	Music introMusic;

	public static Game game;
	public static Result result;

	public DynamicBeat() {
		trackList.add(
				new Track("Auratitle.png", "Auraintro.png", "Auraingame.jpg", "Aurahighlight.mp3", "Aura.mp3", "Aura"));
		trackList.add(new Track("legendtitle.png", "leofmoonintro.jpg", "leofmooningame.jpg",
				"LegendofMoonlighthighlight.mp3", "LegendofMoonlight.mp3", "Legend OF Moonlight"));
		trackList.add(new Track("MirrorNighttitle.png", "Mirrornightintro.png", "Mirrornightingame.jpg",
				"MirrornightHighlight.MP3", "Mirrornight.mp3", "Mirro Night"));
		trackList.add(new Track("EvolutionEratitle.png", "evolutionintro.jpg", "evolutioningame.jpg",
				"EvolutioneroHighlight.MP3", "Evolutionero.mp3", "Evolution Ero"));

		setUndecorated(true);
		setTitle("DynamicBeat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(new KeyListener());

		startButton.setBounds(40, 450, 400, 90);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startEnteredButton);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startBasicButton);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();

				startMain();
			}
		});
		add(startButton);

		endButton.setBounds(40, 560, 400, 90);
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);
		endButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				endButton.setIcon(endEnteredButton);
				endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				endButton.setIcon(endBasicButton);
				endButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
				try {
					introMusic.close();
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// TODO: handle exception
				}

				dispose();
			}
		});
		add(endButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(laftEnteredButton);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftBasicButton);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();
				// 왼쪽 버튼 이벤트
			}
		});
		add(leftButton);
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightEnteredButton);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightBasicButton);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
			}
			// 오른쪽 버튼 이벤트
		});
		add(rightButton);

		quitButton.setVisible(false);
		quitButton.setBounds(1100, 600, 150, 60);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitEnteredButton);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitBasicButton);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();

				try {
					introMusic.close();
					selectedMusic.close();
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				dispose();

			}
		});
		add(quitButton);

		easyButton.setVisible(false);
		easyButton.setBounds(640-125, 50, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyEnteredButton);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyBasicButton);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

//		hardButton.setVisible(false);
//		hardButton.setBounds(710, 50, 250, 67);
//		hardButton.setBorderPainted(false);
//		hardButton.setContentAreaFilled(false);
//		hardButton.setFocusPainted(false);
//		hardButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				hardButton.setIcon(hardEnteredButton);
//				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
//				buttonEnteredMusic.start();
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				hardButton.setIcon(hardBasicButton);
//				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
//				buttonEnteredMusic.start();
//				// 하드버튼 이벤트
//				gameStart(nowSelected, "Hard");
//			}
//		});
//		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backEnteredButton);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backBasicButton);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("Beep_Short.mp3", false);
				buttonEnteredMusic.start();
				// 백버튼 이벤트
				backMain();

			}
		});
		add(backButton);

	}

	String musicname() {
		int rnd = (int) (Math.random() * 4 + 1);
		String music = "";
		if (rnd == 1) {
			music = "Mirrornight.mp3";
		} else if (rnd == 2) {
			music = "LegendofMoonlight.mp3";
		} else if (rnd == 3) {
			music = "Evolutionero.mp3";
		} else if (rnd == 4) {
			music = "Aura.mp3";
		}
		return music;
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	public void screenDraw(Graphics2D g) {

		if (introMusic == null || introMusic.isInterrupted()) {
			introMusic = new Music(musicname(), false);
			introMusic.start();
		}
		g.drawImage(background, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 110, null);
			g.drawImage(titleImage, 340, 550, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		if (isResultScreen) {
			result.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		this.repaint();
	}

	public void selectTrack(int num) {
		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../Images/" + trackList.get(num).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../Images/" + trackList.get(num).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(num).getStartMusic(), true);
		selectedMusic.start();

	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	public void backMain() {
		isMainScreen = true;

		leftButton.setVisible(true);
		rightButton.setVisible(true);
		hardButton.setVisible(true);
		easyButton.setVisible(true);
		quitButton.setVisible(true);
		backButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../Images/mainBackGround.jpg")).getImage();
		selectTrack(nowSelected);
		isGameScreen = false;
		isResultScreen = false;
		game.close();
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		hardButton.setVisible(false);
		easyButton.setVisible(false);
		quitButton.setVisible(false);
		backButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../Images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());

		game.start();
		setFocusable(true);
	}

	public void startMain() {
		introMusic.close();
		startButton.setVisible(false);
		endButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		quitButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../Images/mainBackGround.jpg")).getImage();
		isMainScreen = true;
		selectTrack(0);
	}

}
