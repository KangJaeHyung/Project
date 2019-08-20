package RhythmGame4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game extends Thread {

	private Image judgementLine = new ImageIcon(Main.class.getResource("../Images/judgementLine2.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../Images/gameInfo.png")).getImage();

	private Image caseImage1 = new ImageIcon(Main.class.getResource("../Images/caseImage.png")).getImage();
	private Image caseImage2 = new ImageIcon(Main.class.getResource("../Images/caseImage2.png")).getImage();
	private Image caseImageA = new ImageIcon(Main.class.getResource("../Images/caseImage3.png")).getImage();
	private Image caseImageJ = new ImageIcon(Main.class.getResource("../Images/caseImage3.png")).getImage();
	private Image caseImageL = new ImageIcon(Main.class.getResource("../Images/caseImage3.png")).getImage();
	private Image caseImageD = new ImageIcon(Main.class.getResource("../Images/caseImage4.png")).getImage();
	private Image caseImageI = new ImageIcon(Main.class.getResource("../Images/caseImage4.png")).getImage();
	private Image noteRouteA = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
	private Image noteRouteD = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
	private Image noteRouteJ = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
	private Image noteRouteI = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
	private Image noteRouteL = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();


	private Image units;
	private Image tens;
	private Image hundreds;
	private Image tenthousands;
	private Image flareImage;
	private Image judgeImage;
//	private Image judgeflareA= new ImageIcon(Main.class.getResource("../Images/judgeImage.gif")).getImage();
//	private Image judgeflareD= new ImageIcon(Main.class.getResource("../Images/judgeImage.gif")).getImage();
//	private Image judgeflareJ= new ImageIcon(Main.class.getResource("../Images/judgeImage.gif")).getImage();
//	private Image judgeflareI= new ImageIcon(Main.class.getResource("../Images/judgeImage.gif")).getImage();
//	private Image judgeflareL= new ImageIcon(Main.class.getResource("../Images/judgeImage.gif")).getImage();

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private int combo;
	private int koolnum;
	private int coolnum;
	private int missnum;
	private int goodnum;
	private int score;
	private boolean judgeA=false;
	private boolean judgeD=false;
	private boolean judgeJ=false;
	private boolean judgeI=false;
	private boolean judgeL=false;
	
	int maxCombo;
	Beat[] beats;
//	private 
	Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		
	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(caseImage2, 400, 0, null);
		g.drawImage(caseImage1, 840, 0, null);
		g.drawImage(noteRouteA, 440, 0, null);
		g.drawImage(noteRouteD, 520, 0, null);
		g.drawImage(noteRouteJ, 600, 0, null);
		g.drawImage(noteRouteI, 680, 0, null);
		g.drawImage(noteRouteL, 760, 0, null);

		for (int i = 0; i < noteList.size(); i++) {

			Note note = noteList.get(i);
			if (note.getNoteLong().equals("short")) {
				if (note.getY() > 800) {
					combo = 0;
					missnum++;
					judgeImage = new ImageIcon(Main.class.getResource("../Images/Miss.png")).getImage();
				}
			} else if (note.getNoteLong().equals("long") && !note.isPressed) {
				if (note.getY() > 800) {
					combo = 0;
					missnum++;
					judgeImage = new ImageIcon(Main.class.getResource("../Images/Miss.png")).getImage();
				}

			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}

		}
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgeImage, 460, 360, null);
		g.drawImage(flareImage, 460, 410, null);
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1140, 702);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		String scoretxt = "000000";
		String combotxt = combo + "";
		if (score < 10) {
			scoretxt = "00000" + score;
		} else if (10 <= score & score < 100) {
			scoretxt = "0000" + score;
		} else if (100 <= score & score < 1000) {
			scoretxt = "000" + score;
		} else if (1000 <= score & score < 10000) {
			scoretxt = "00" + score;
		} else if (10000 <= score & score < 100000) {
			scoretxt = "0" + score;
		} else if (100000 <= score & score < 1000000) {
			scoretxt = score + "";
		}
		String[] combotxt2 = combotxt.split("");
		if (combotxt2.length == 1) {
			g.drawImage(units, 590, 200, null);
			for (int i = 0; i < 10; i++) {
				if ((i + "").equals(combotxt2[0])) {
					units = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
			}
		} else if (combotxt2.length == 2) {
			g.drawImage(units, 540, 200, null);
			g.drawImage(tens, 640, 200, null);
			for (int i = 0; i < 10; i++) {
				if ((i + "").equals(combotxt2[0])) {
					units = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
				if ((i + "").equals(combotxt2[1])) {
					tens = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
			}
		} else if (combotxt2.length == 3) {
			g.drawImage(units, 490, 200, null);
			g.drawImage(tens, 590, 200, null);
			g.drawImage(hundreds, 690, 200, null);
			for (int i = 0; i < 10; i++) {
				if ((i + "").equals(combotxt2[0])) {
					units = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
				if ((i + "").equals(combotxt2[1])) {
					tens = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
				if ((i + "").equals(combotxt2[2])) {
					hundreds = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
			}
		} else if (combotxt2.length == 4) {
			g.drawImage(units, 440, 200, null);
			g.drawImage(tens, 540, 200, null);
			g.drawImage(hundreds, 640, 200, null);
			g.drawImage(tenthousands, 740, 200, null);
			for (int i = 0; i < 10; i++) {
				if ((i + "").equals(combotxt2[0])) {
					units = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
				if ((i + "").equals(combotxt2[1])) {
					tens = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
				if ((i + "").equals(combotxt2[2])) {
					hundreds = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
				if ((i + "").equals(combotxt2[3])) {
					tenthousands = new ImageIcon(Main.class.getResource("../Images/" + i + ".png")).getImage();
				}
			}
		}
		g.drawString(scoretxt, 565, 702);
		g.drawString(combotxt, 800, 702);
		g.drawString(maxCombo() + "", 900, 702);
		g.drawImage(judgementLine, 440, 610, null);
		g.drawImage(caseImageA, 440, 630, null);
		g.drawImage(caseImageD, 520, 630, null);
		g.drawImage(caseImageJ, 600, 630, null);
		g.drawImage(caseImageI, 680, 630, null);
		g.drawImage(caseImageL, 760, 630, null);
//		if(judgeA) {
//			g.drawImage(judgeflareA, 440,570,null);	
//		}
//		if(judgeD) {
//			g.drawImage(judgeflareA, 440,570,null);	
//		}
//		if(judgeJ) {
//			g.drawImage(judgeflareA, 440,570,null);	
//		}
//		if(judgeI) {
//			g.drawImage(judgeflareA, 440,570,null);	
//		}
//		if(judgeL) {
//			g.drawImage(judgeflareA, 440,570,null);	
//		}
//		
	}

	public static boolean pressedA = false;
	public static boolean pressedD = false;
	public static boolean pressedJ = false;
	public static boolean pressedI = false;
	public static boolean pressedL = false;
	public Timer timerA;
	public Timer timerD;
	public Timer timerJ;
	public Timer timerI;
	public Timer timerL;

	public void pressA() {
		caseImageA = new ImageIcon(Main.class.getResource("../Images/caseImage3Pressed.png")).getImage();
		noteRouteA = new ImageIcon(Main.class.getResource("../Images/noteRoutePressed1.png")).getImage();
		if (!pressedA) {
			pressedA = true;
			PressedA();
		}
	}

	public void PressedA() {
		if (pressedA) {
			judge("a");
			new Music("drumSmall1.mp3", false).start();
			timerA = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (!pressedA) {
						timerA.cancel();
					} else {
						judgeLong("a");
					}

				}
			};
			timerA.schedule(task, 75, 75);
		}

	}

	public void releaseA() {
		caseImageA = new ImageIcon(Main.class.getResource("../Images/caseImage3.png")).getImage();
		noteRouteA = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
		pressedA = false;
//		PressedA();
	}

	public void pressJ() {
		caseImageJ = new ImageIcon(Main.class.getResource("../Images/caseImage3Pressed.png")).getImage();
		noteRouteJ = new ImageIcon(Main.class.getResource("../Images/noteRoutePressed1.png")).getImage();
		if (!pressedJ) {
			pressedJ = true;
			PressedJ();
		}
	}

	public void PressedJ() {
		if (pressedJ) {
			judge("j");
			new Music("drumSmall1.mp3", false).start();
			timerJ = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (!pressedJ) {
						timerJ.cancel();
					} else {
						judgeLong("j");
					}

				}
			};
			timerJ.schedule(task, 75, 75);
		}

	}

	public void releaseJ() {
		caseImageJ = new ImageIcon(Main.class.getResource("../Images/caseImage3.png")).getImage();
		noteRouteJ = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
		pressedJ = false;
//		PressedA();
	}

	public void pressL() {
		caseImageL = new ImageIcon(Main.class.getResource("../Images/caseImage3Pressed.png")).getImage();
		noteRouteL = new ImageIcon(Main.class.getResource("../Images/noteRoutePressed1.png")).getImage();
		if (!pressedL) {
			pressedL = true;
			PressedL();
		}
	}

	public void PressedL() {
		if (pressedL) {
			judge("l");
			new Music("drumSmall1.mp3", false).start();
			timerL = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (!pressedL) {
						timerL.cancel();
					} else {
						judgeLong("l");
					}

				}
			};
			timerL.schedule(task, 75, 75);
		}

	}

	public void releaseL() {
		caseImageL = new ImageIcon(Main.class.getResource("../Images/caseImage3.png")).getImage();
		noteRouteL = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
		pressedL = false;
//		PressedA();
	}

	public void pressD() {
		caseImageD = new ImageIcon(Main.class.getResource("../Images/caseImage4Pressed.png")).getImage();
		noteRouteD = new ImageIcon(Main.class.getResource("../Images/noteRoutePressed2.png")).getImage();
		if (!pressedD) {
			pressedD = true;
			PressedD();
		}
	}

	public void PressedD() {
		if (pressedD) {
			judge("d");
			new Music("drumSmall1.mp3", false).start();
			timerD = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (!pressedD) {
						timerD.cancel();
					} else {
						judgeLong("d");
					}

				}
			};
			timerD.schedule(task, 75, 75);
		}

	}

	public void releaseD() {
		caseImageD = new ImageIcon(Main.class.getResource("../Images/caseImage4.png")).getImage();
		noteRouteD = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
		pressedD = false;
//		PressedA();
	}

	public void pressI() {
		caseImageI = new ImageIcon(Main.class.getResource("../Images/caseImage4Pressed.png")).getImage();
		noteRouteI = new ImageIcon(Main.class.getResource("../Images/noteRoutePressed2.png")).getImage();
		if (!pressedI) {
			pressedI = true;
			PressedI();
		}
	}

	public void PressedI() {
		if (pressedI) {
			judge("i");
			new Music("drumSmall1.mp3", false).start();
			timerI = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (!pressedI) {
						timerI.cancel();
					} else {
						judgeLong("i");
					}

				}
			};
			timerI.schedule(task, 75, 75);
		}

	}

	public void releaseI() {
		caseImageI = new ImageIcon(Main.class.getResource("../Images/caseImage4.png")).getImage();
		noteRouteI = new ImageIcon(Main.class.getResource("../Images/noteRoute.png")).getImage();
		pressedI = false;
//		PressedA();
	}

	@Override
	public void run() {
		dropNotes();
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void leoMoon(String difficulty) {

		int StartTime = 1000 - Main.REACH_TIME * 1000;
		int gap = 40;
		if (difficulty.equals("easy")) {
			beats = new Beat[] {
//				new Beat(StartTime, "d", "short", 0),
//					new Beat(StartTime+gap*4, "j", "short", 0),
//					new Beat(StartTime+gap*8, "l", "short", 0),
//					new Beat(StartTime+gap*14, "a", "short", 0),
//					new Beat(StartTime+gap*14, "i", "long", 1),
//					new Beat(StartTime+gap*29, "a", "short", 0),
//					new Beat(StartTime+gap*29, "j", "long", 1),
//					new Beat(StartTime+gap*38, "a", "short", 0),
//					new Beat(StartTime+gap*42, "a", "short", 0),
//					new Beat(StartTime+gap*42, "d", "long", 1),
//					new Beat(StartTime+gap*64, "d", "short", 0),
//					new Beat(StartTime+gap*69, "j", "short", 0),
//					new Beat(StartTime+gap*74, "d", "short", 0),
//					new Beat(StartTime+gap*79, "a", "short", 0),
//					new Beat(StartTime+gap*79, "i", "short", 0),
//					new Beat(StartTime+gap*84, "a", "short", 0),
//					new Beat(StartTime+gap*84, "l", "short", 0),
//		
			};
			// 실행 시간 계산 및 출력

		}
	}

	public void mirroNight(String difficulty) {
		int StartTime = 1900 - Main.REACH_TIME * 1000;
		int gap = 40;
		if (difficulty.equals("easy")) {
			beats = new Beat[] { new Beat(StartTime + gap * 0, "a", "short", 0),
					new Beat(StartTime + gap * 2, "a", "short", 0), new Beat(StartTime + gap * 4, "a", "short", 0),
					new Beat(StartTime + gap * 6, "a", "short", 0), new Beat(StartTime + gap * 8, "a", "short", 0),

			};
		}
	}

	public void Aura(String difficulty) {
		int StartTime = 1000 - Main.REACH_TIME * 1000;
		int gap = 40;
		double a = 2.5;
		if (difficulty.equals("easy")) {
			beats = new Beat[] {new Beat(StartTime + gap * 13, "d", "short", 0),
			new Beat(StartTime + gap * 17, "i", "short", 0), new Beat(StartTime + gap * 42, "j", "short", 0),
			new Beat(StartTime + gap * 47, "d", "short", 0), new Beat(StartTime + gap * 53, "a", "short", 0),
			new Beat(StartTime + gap * 57, "i", "short", 0), new Beat(StartTime + gap * 83, "d", "short", 0),
			new Beat(StartTime + gap * 83, "l", "short", 0), new Beat(StartTime + gap * 93, "a", "short", 0),
			new Beat(StartTime + gap * 97, "i", "short", 0), new Beat(StartTime + gap * 122, "j", "short", 0),
			new Beat(StartTime + gap * 127, "d", "short", 0), new Beat(StartTime + gap * 132, "a", "short", 0),
			new Beat(StartTime + gap * 136, "i", "short", 0), new Beat(StartTime + gap * 151, "d", "short", 0),
			new Beat(StartTime + gap * 157, "l", "short", 0), new Beat(StartTime + gap * 172, "a", "short", 0),
			new Beat(StartTime + gap * 176, "i", "short", 0), new Beat(StartTime + gap * 201, "a", "short", 0),
			new Beat(StartTime + gap * 206, "d", "short", 0), new Beat(StartTime + gap * 212, "j", "short", 0),
			new Beat(StartTime + gap * 216, "l", "short", 0), new Beat(StartTime + gap * 232, "a", "short", 0),
			new Beat(StartTime + gap * 238, "i", "short", 0), new Beat(StartTime + gap * 251, "d", "short", 0),
			new Beat(StartTime + gap * 256, "l", "short", 0), new Beat(StartTime + gap * 281, "i", "short", 0),
			new Beat(StartTime + gap * 285, "j", "short", 0), new Beat(StartTime + gap * 291, "d", "short", 0),
			new Beat(StartTime + gap * 296, "l", "short", 0), new Beat(StartTime + gap * 309, "a", "short", 0),
			new Beat(StartTime + gap * 315, "i", "short", 0),

			new Beat(StartTime + gap * 321, "j", "long", 15),

			new Beat(StartTime + gap * 331, "a", "long", 75), new Beat(StartTime + gap * 331, "i", "long", 75),

			new Beat(StartTime + gap * 362, "j", "long", 15), new Beat(StartTime + gap * 362, "l", "long", 15),

			new Beat(StartTime + gap * 372, "d", "long", 45), new Beat(StartTime + gap * 372, "i", "long", 45),

			new Beat(StartTime + gap * 392, "j", "long", 15), new Beat(StartTime + gap * 392, "l", "long", 15),

			new Beat(StartTime + gap * 402, "a", "long", 15), new Beat(StartTime + gap * 402, "i", "long", 15),

			new Beat(StartTime + gap * 412, "d", "long", 150),
			new Beat(StartTime + gap * 412, "l", "long", 150),

			new Beat(StartTime + gap * 482, "d", "long", 15), new Beat(StartTime + gap * 482, "l", "long", 15),

			new Beat(StartTime + gap * 491, "a", "long", 75), new Beat(StartTime + gap * 491, "i", "long", 75),

			new Beat(StartTime + gap * 521, "d", "long", 15), new Beat(StartTime + gap * 521, "l", "long", 15),

			new Beat(StartTime + gap * 531, "a", "long", 45), new Beat(StartTime + gap * 531, "j", "long", 45),

			new Beat(StartTime + gap * 550, "d", "long", 15), new Beat(StartTime + gap * 550, "i", "long", 15),

			new Beat(StartTime + gap * 561, "j", "long", 15), new Beat(StartTime + gap * 561, "l", "long", 15),

			new Beat(StartTime + gap * 571, "a", "long", 75), new Beat(StartTime + gap * 571, "i", "long", 75),

			new Beat(StartTime + gap * 611, "j", "long", 45), new Beat(StartTime + gap * 611, "l", "long", 45),

			new Beat(StartTime + gap * 633, "d", "long", 15), new Beat(StartTime + gap * 633, "i", "long", 15),

			new Beat(StartTime + gap * 642, "a", "long", 15), new Beat(StartTime + gap * 642, "j", "long", 15),

			new Beat(StartTime + gap * 652, "d", "short", 0), new Beat(StartTime + gap * 654, "j", "short", 0),
			new Beat(StartTime + gap * 656, "i", "short", 0),

			new Beat(StartTime + gap * 661, "d", "short", 0), new Beat(StartTime + gap * 662, "i", "short", 0),
			new Beat(StartTime + gap * 665, "j", "short", 0), new Beat(StartTime + gap * 667, "d", "short", 0),
			new Beat(StartTime + gap * 670, "j", "short", 0), new Beat(StartTime + gap * 672, "l", "short", 0),
			new Beat(StartTime + gap * 675, "a", "short", 0), new Beat(StartTime + gap * 678, "i", "short", 0),
			new Beat(StartTime + gap * 680, "j", "short", 0), new Beat(StartTime + gap * 685, "a", "short", 0),
			new Beat(StartTime + gap * 686, "i", "short", 0), new Beat(StartTime + gap * 689, "d", "short", 0),
			new Beat(StartTime + gap * 692, "a", "short", 0), new Beat(StartTime + gap * 693, "j", "short", 0),
			new Beat(StartTime + gap * 695, "i", "short", 0), new Beat(StartTime + gap * 701, "a", "short", 0),
			new Beat(StartTime + gap * 703, "i", "short", 0), new Beat(StartTime + gap * 705, "j", "short", 0),
			new Beat(StartTime + gap * 710, "d", "short", 0), new Beat(StartTime + gap * 716, "a", "short", 0),
			new Beat(StartTime + gap * 718, "i", "short", 0), new Beat(StartTime + gap * 720, "j", "short", 0),
			new Beat(StartTime + gap * 722, "d", "short", 0), new Beat(StartTime + gap * 726, "l", "short", 0),
			new Beat(StartTime + gap * 730, "a", "short", 0), new Beat(StartTime + gap * 731, "j", "short", 0),
			new Beat(StartTime + gap * 733, "l", "short", 0), new Beat(StartTime + gap * 739, "a", "short", 0),
			new Beat(StartTime + gap * 741, "l", "short", 0), new Beat(StartTime + gap * 745, "a", "short", 0),
			new Beat(StartTime + gap * 747, "j", "short", 0), new Beat(StartTime + gap * 749, "l", "short", 0),
			new Beat(StartTime + gap * 753, "a", "short", 0), new Beat(StartTime + gap * 757, "i", "short", 0),
			new Beat(StartTime + gap * 759, "j", "short", 0), new Beat(StartTime + gap * 763, "a", "short", 0),
			new Beat(StartTime + gap * 766, "l", "short", 0), new Beat(StartTime + gap * 768, "d", "short", 0),
			new Beat(StartTime + gap * 771, "a", "short", 0), new Beat(StartTime + gap * 774, "i", "short", 0),
			new Beat(StartTime + gap * 780, "a", "short", 0), new Beat(StartTime + gap * 785, "a", "short", 0),
			new Beat(StartTime + gap * 789, "l", "short", 0), new Beat(StartTime + gap * 794, "d", "short", 0),
			new Beat(StartTime + gap * 797, "j", "short", 0), new Beat(StartTime + gap * 800, "l", "short", 0),
			new Beat(StartTime + gap * 806, "j", "short", 0), new Beat(StartTime + gap * 811, "d", "short", 0),
			new Beat(StartTime + gap * 813, "j", "short", 0), new Beat(StartTime + gap * 815, "i", "short", 0),
			new Beat(StartTime + gap * 821, "d", "short", 0), new Beat(StartTime + gap * 823, "i", "short", 0),
			new Beat(StartTime + gap * 828, "d", "short", 0), new Beat(StartTime + gap * 829, "i", "short", 0),
			new Beat(StartTime + gap * 832, "l", "short", 0), new Beat(StartTime + gap * 836, "a", "short", 0),
			new Beat(StartTime + gap * 838, "j", "short", 0), new Beat(StartTime + gap * 840, "l", "short", 0),
			new Beat(StartTime + gap * 845, "a", "short", 0), new Beat(StartTime + gap * 846, "i", "short", 0),
			new Beat(StartTime + gap * 849, "d", "short", 0), new Beat(StartTime + gap * 852, "a", "short", 0),
			new Beat(StartTime + gap * 854, "j", "short", 0), new Beat(StartTime + gap * 855, "i", "short", 0),
			new Beat(StartTime + gap * 860, "a", "short", 0), new Beat(StartTime + gap * 863, "i", "short", 0),
			new Beat(StartTime + gap * 866, "a", "short", 0), new Beat(StartTime + gap * 869, "j", "short", 0),
			new Beat(StartTime + gap * 872, "l", "short", 0), new Beat(StartTime + gap * 876, "a", "short", 0),
			new Beat(StartTime + gap * 879, "i", "short", 0), new Beat(StartTime + gap * 882, "d", "short", 0),
			new Beat(StartTime + gap * 885, "j", "short", 0), new Beat(StartTime + gap * 889, "a", "short", 0),
			new Beat(StartTime + gap * 893, "j", "short", 0), new Beat(StartTime + gap * 895, "l", "short", 0),
			new Beat(StartTime + gap * 900, "a", "short", 0), new Beat(StartTime + gap * 903, "l", "short", 0),
			new Beat(StartTime + gap * 906, "a", "short", 0), new Beat(StartTime + gap * 909, "j", "short", 0),
			new Beat(StartTime + gap * 911, "l", "short", 0), new Beat(StartTime + gap * 915, "a", "short", 0),
			new Beat(StartTime + gap * 918, "i", "short", 0), new Beat(StartTime + gap * 921, "l", "short", 0),
			new Beat(StartTime + gap * 925, "a", "short", 0), new Beat(StartTime + gap * 926, "i", "short", 0),
			new Beat(StartTime + gap * 930, "d", "short", 0), new Beat(StartTime + gap * 932, "a", "short", 0),
			new Beat(StartTime + gap * 936, "i", "short", 0), new Beat(StartTime + gap * 940, "a", "short", 0),
			new Beat(StartTime + gap * 946, "a", "short", 0), new Beat(StartTime + gap * 951, "l", "short", 0),
			new Beat(StartTime + gap * 954, "d", "short", 0), new Beat(StartTime + gap * 958, "j", "short", 0),
			new Beat(StartTime + gap * 963, "l", "short", 0), new Beat(StartTime + gap * 967, "d", "short", 0),

			new Beat(StartTime + gap * 969, "a", "short", 0),

			new Beat(StartTime + gap * 971, "l", "long", 50), new Beat(StartTime + gap * 976, "d", "long", 37),

			new Beat(StartTime + gap * 997, "i", "long", 15), new Beat(StartTime + gap * 997, "j", "short", 0),
			new Beat(StartTime + gap * 1003, "d", "short", 0),

			new Beat(StartTime + gap * 1006, "a", "short", 0),
			new Beat(StartTime + gap * 1006, "l", "long", 45),
			new Beat(StartTime + gap * 1011, "d", "long", 32),

			new Beat(StartTime + gap * 1028, "a", "long", 15),
			new Beat(StartTime + gap * 1028, "j", "long", 15),

			new Beat(StartTime + gap * 1039, "i", "long", 15),
			new Beat(StartTime + gap * 1039, "d", "long", 15),

			new Beat(StartTime + gap * 1049, "a", "long", 140),
			new Beat(StartTime + gap * 1049, "j", "short", 0),

			new Beat(StartTime + gap * 1053, "i", "long", 15),

			new Beat(StartTime + gap * 1077, "l", "short", 0),
			new Beat(StartTime + gap * 1082, "i", "short", 0),
			new Beat(StartTime + gap * 1087, "j", "short", 0),

			new Beat(StartTime + gap * 1092, "i", "long", 20),

			new Beat(StartTime + gap * 1107, "j", "short", 0),

			new Beat(StartTime + gap * 1113, "l", "long", 30),
			new Beat(StartTime + gap * 1118, "a", "long", 17),

			new Beat(StartTime + gap * 1128, "l", "long", 75),
			new Beat(StartTime + gap * 1128, "d", "short", 0),
			new Beat(StartTime + gap * 1135, "j", "long", 54),

			new Beat(StartTime + gap * 1157, "a", "short", 0),
			new Beat(StartTime + gap * 1157, "i", "long", 15),
			new Beat(StartTime + gap * 1163, "d", "short", 0),

			new Beat(StartTime + gap * 1168, "a", "short", 0),
			new Beat(StartTime + gap * 1168, "l", "long", 30),
			new Beat(StartTime + gap * 1173, "j", "long", 17),

			new Beat(StartTime + gap * 1187, "a", "short", 0),
			new Beat(StartTime + gap * 1187, "i", "long", 15),
			new Beat(StartTime + gap * 1193, "d", "long", 25),
			new Beat(StartTime + gap * 1198, "l", "long", 15),

			new Beat(StartTime + gap * 1208, "a", "short", 0),
			new Beat(StartTime + gap * 1208, "i", "long", 15),

			new Beat(StartTime + gap * 1215, "d", "long", 15),
			new Beat(StartTime + gap * 1215, "l", "short", 0),

			new Beat(StartTime + gap * 1227, "a", "short", 0),
			new Beat(StartTime + gap * 1227, "i", "long", 15),

			new Beat(StartTime + gap * 1238, "d", "long", 15),
			new Beat(StartTime + gap * 1238, "l", "short", 0),

			new Beat(StartTime + gap * 1248, "a", "short", 0),
			new Beat(StartTime + gap * 1248, "i", "short", 0),

			new Beat(StartTime + gap * 1253, "l", "short", 0),
			new Beat(StartTime + gap * 1253, "d", "short", 0),

			new Beat(StartTime + gap * 1258, "j", "short", 0),

			new Beat(StartTime + gap * 1263, "i", "short", 0),

			new Beat(StartTime + gap * 1268, "d", "short", 0),
			new Beat(StartTime + gap * 1268, "l", "short", 0),

			new Beat(StartTime + gap * 1271, "i", "short", 0),
			new Beat(StartTime + gap * 1276, "d", "short", 0),
			new Beat(StartTime + gap * 1280, "i", "short", 0),
			new Beat(StartTime + gap * 1283, "d", "short", 0),
			new Beat(StartTime + gap * 1288, "a", "short", 0),
			new Beat(StartTime + gap * 1293, "i", "long", 45),

			new Beat(StartTime + gap * 1316, "d", "short", 0),
			new Beat(StartTime + gap * 1318, "d", "short", 0),
			new Beat(StartTime + gap * 1322, "d", "short", 0),
			new Beat(StartTime + gap * 1331, "a", "short", 0),
			new Beat(StartTime + gap * 1336, "i", "short", 0),
			new Beat(StartTime + gap * 1341, "i", "short", 0),
			new Beat(StartTime + gap * 1341, "a", "short", 0),
			new Beat(StartTime + gap * 1351, "i", "long", 15),
			new Beat(StartTime + gap * 1351, "a", "short", 0),
			new Beat(StartTime + gap * 1357, "j", "short", 0),
			new Beat(StartTime + gap * 1362, "a", "short", 0),
			new Beat(StartTime + gap * 1367, "i", "short", 0),
			new Beat(StartTime + gap * 1372, "a", "short", 0),

			new Beat(StartTime + gap * 1377, "j", "short", 0),
			new Beat(StartTime + gap * 1382, "a", "short", 0),
			new Beat(StartTime + gap * 1382, "j", "short", 0),
			new Beat(StartTime + gap * 1392, "j", "long", 15),
			new Beat(StartTime + gap * 1392, "a", "short", 0),
			new Beat(StartTime + gap * 1398, "d", "short", 0),
			new Beat(StartTime + gap * 1401, "a", "short", 0),
			new Beat(StartTime + gap * 1406, "j", "short", 0),
			new Beat(StartTime + gap * 1411, "a", "short", 0),
			new Beat(StartTime + gap * 1416, "l", "short", 0),

			new Beat(StartTime + gap * 1421, "a", "short", 0),
			new Beat(StartTime + gap * 1421, "l", "short", 0),
			new Beat(StartTime + gap * 1431, "l", "long", 15),
			new Beat(StartTime + gap * 1431, "a", "short", 0),
			new Beat(StartTime + gap * 1437, "i", "short", 0),
			new Beat(StartTime + gap * 1439, "a", "short", 0),
			new Beat(StartTime + gap * 1444, "l", "short", 0),
			new Beat(StartTime + gap * 1449, "a", "short", 0),
			new Beat(StartTime + gap * 1454, "j", "short", 0),
			new Beat(StartTime + gap * 1459, "a", "short", 0),
			new Beat(StartTime + gap * 1459, "j", "short", 0),
			new Beat(StartTime + gap * 1471, "i", "long", 15),
			new Beat(StartTime + gap * 1471, "a", "short", 0),

			new Beat(StartTime + gap * 1477, "j", "short", 0),
			new Beat(StartTime + gap * 1481, "d", "short", 0),
			new Beat(StartTime + gap * 1483, "a", "short", 0),
			new Beat(StartTime + gap * 1486, "i", "short", 0),
			new Beat(StartTime + gap * 1491, "a", "short", 0),
			new Beat(StartTime + gap * 1493, "j", "short", 0),
			new Beat(StartTime + gap * 1496, "l", "short", 0),
			new Beat(StartTime + gap * 1501, "j", "short", 0),
			new Beat(StartTime + gap * 1501, "l", "short", 0),
			new Beat(StartTime + gap * 1511, "j", "long", 15),
			new Beat(StartTime + gap * 1511, "l", "short", 0),
			new Beat(StartTime + gap * 1517, "d", "short", 0),
			new Beat(StartTime + gap * 1519, "l", "short", 0),
			new Beat(StartTime + gap * 1521, "j", "short", 0),
			new Beat(StartTime + gap * 1531, "l", "short", 0),
			new Beat(StartTime + gap * 1536, "d", "short", 0),
			new Beat(StartTime + gap * 1541, "l", "short", 0),
			new Beat(StartTime + gap * 1541, "d", "short", 0),
			new Beat(StartTime + gap * 1551, "d", "long", 15),
			new Beat(StartTime + gap * 1551, "l", "short", 0),
			new Beat(StartTime + gap * 1557, "a", "short", 0),
			new Beat(StartTime + gap * 1559, "l", "short", 0),
			new Beat(StartTime + gap * 1564, "d", "short", 0),
			new Beat(StartTime + gap * 1569, "l", "short", 0),
			new Beat(StartTime + gap * 1576, "j", "short", 0),
			new Beat(StartTime + gap * 1581, "l", "short", 0),
			new Beat(StartTime + gap * 1581, "j", "short", 0),
			new Beat(StartTime + gap * 1591, "j", "long", 15),
			new Beat(StartTime + gap * 1591, "l", "short", 0),
			new Beat(StartTime + gap * 1596, "d", "short", 0),
			new Beat(StartTime + gap * 1599, "l", "short", 0),
			new Beat(StartTime + gap * 1604, "j", "short", 0),
			new Beat(StartTime + gap * 1609, "l", "short", 0),
			new Beat(StartTime + gap * 1614, "a", "short", 0),
			new Beat(StartTime + gap * 1621, "a", "short", 0),
			new Beat(StartTime + gap * 1621, "l", "short", 0),
			new Beat(StartTime + gap * 1631, "d", "long", 15),
			new Beat(StartTime + gap * 1631, "l", "short", 0),
			new Beat(StartTime + gap * 1637, "a", "short", 0),
			new Beat(StartTime + gap * 1641, "l", "short", 0),
			new Beat(StartTime + gap * 1646, "d", "short", 0),
			new Beat(StartTime + gap * 1646, "l", "short", 0),

			new Beat(StartTime + gap * 1651, "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 2 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 3 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 4 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 5 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 6 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 7 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 8 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 9 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 10 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 11 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 12 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 13 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 14 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 15 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 16 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 17 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 18 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 19 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 20 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 21 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 22 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 23 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 24 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 25 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 26 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 27 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 28 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 29 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 30 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 31 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 32 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 33 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 34 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 35 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 36 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 37 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 38 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 39 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 40 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 41 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 42 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 43 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 44 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 45 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 46 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 47 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 48 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 49 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 50 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 51 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 52 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 53 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 54 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 55 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 56 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 57 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 58 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 59 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 60 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 61 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 62 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 63 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 64 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 65 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 66 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 67 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 68 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 69 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 70 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 71 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 72 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 73 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 74 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 75 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 76 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 77 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 78 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 79 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 80 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 81 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 82 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 83 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 84 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 85 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 86 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 87 * a)), "d", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 88 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 89 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 90 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 91 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 92 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 93 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 94 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 95 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 96 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 97 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 98 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 99 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 100 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 101 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 102 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 103 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 104 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 105 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 106 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 107 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 108 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 109 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 110 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 111 * a)), "i", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 112 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 113 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 114 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 115 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 116 * a)), "l", "short", 0),

			new Beat((int) (StartTime + gap * (1651 + 117 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 118 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 119 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 120 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 121 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 122 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 123 * a)), "a", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 124 * a)), "l", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 125 * a)), "j", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 126 * a)), "d", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 127 * a)), "i", "short", 0),
			new Beat((int) (StartTime + gap * (1651 + 128 * a)), "l", "short", 0),

			new Beat(StartTime + gap * 1974, "l", "short", 0),
			new Beat(StartTime + gap * 1979, "a", "short", 0),
			new Beat(StartTime + gap * 1979, "d", "short", 0),
			new Beat(StartTime + gap * 1980, "l", "short", 0),
			new Beat(StartTime + gap * 1981, "i", "short", 0),
			new Beat(StartTime + gap * 1989, "a", "short", 0),
			new Beat(StartTime + gap * 1993, "l", "short", 0),
			new Beat(StartTime + gap * 1999, "a", "short", 0),
			new Beat(StartTime + gap * 1999, "d", "short", 0),
			new Beat(StartTime + gap * 2003, "i", "short", 0),
			new Beat(StartTime + gap * 2003, "l", "short", 0),
			new Beat(StartTime + gap * 2009, "a", "short", 0),
			new Beat(StartTime + gap * 2013, "l", "short", 0),
			new Beat(StartTime + gap * 2018, "a", "short", 0),
			new Beat(StartTime + gap * 2018, "d", "short", 0),
			new Beat(StartTime + gap * 2022, "l", "short", 0),
			new Beat(StartTime + gap * 2024, "i", "short", 0),
			new Beat(StartTime + gap * 2029, "a", "short", 0),
			new Beat(StartTime + gap * 2033, "l", "short", 0),
			new Beat(StartTime + gap * 2039, "a", "short", 0),
			new Beat(StartTime + gap * 2039, "d", "short", 0),
			new Beat(StartTime + gap * 2044, "i", "short", 0),
			new Beat(StartTime + gap * 2044, "l", "short", 0),
			new Beat(StartTime + gap * 2048, "a", "short", 0),
			new Beat(StartTime + gap * 2052, "l", "short", 0),
			new Beat(StartTime + gap * 2058, "d", "short", 0),
			new Beat(StartTime + gap * 2059, "a", "short", 0),
			new Beat(StartTime + gap * 2064, "i", "short", 0),
			new Beat(StartTime + gap * 2064, "l", "short", 0),
			new Beat(StartTime + gap * 2069, "a", "short", 0),
			new Beat(StartTime + gap * 2072, "l", "short", 0),
			new Beat(StartTime + gap * 2078, "a", "short", 0),
			new Beat(StartTime + gap * 2078, "d", "short", 0),
			new Beat(StartTime + gap * 2082, "i", "short", 0),
			new Beat(StartTime + gap * 2082, "l", "short", 0),
			new Beat(StartTime + gap * 2088, "a", "short", 0),
			new Beat(StartTime + gap * 2090, "i", "short", 0),
			new Beat(StartTime + gap * 2098, "a", "short", 0),
			new Beat(StartTime + gap * 2099, "l", "short", 0),
			new Beat(StartTime + gap * 2101, "j", "short", 0),
			new Beat(StartTime + gap * 2101, "i", "short", 0),
			new Beat(StartTime + gap * 2109, "a", "short", 0),
			new Beat(StartTime + gap * 2109, "i", "short", 0),
			new Beat(StartTime + gap * 2114, "d", "short", 0),
			new Beat(StartTime + gap * 2118, "a", "short", 0),
			new Beat(StartTime + gap * 2120, "i", "short", 0),
			new Beat(StartTime + gap * 2120, "l", "short", 0),
			new Beat(StartTime + gap * 2125, "d", "short", 0),
			new Beat(StartTime + gap * 2129, "a", "short", 0),
			new Beat(StartTime + gap * 2131, "j", "short", 0),
			new Beat(StartTime + gap * 2135, "l", "short", 0),
			new Beat(StartTime + gap * 2139, "d", "short", 0),
			new Beat(StartTime + gap * 2140, "a", "short", 0),
			new Beat(StartTime + gap * 2145, "i", "short", 0),
			new Beat(StartTime + gap * 2145, "l", "short", 0),
			new Beat(StartTime + gap * 2150, "a", "short", 0),
			new Beat(StartTime + gap * 2155, "l", "short", 0),
			new Beat(StartTime + gap * 2160, "a", "short", 0),
			new Beat(StartTime + gap * 2160, "d", "short", 0),
			new Beat(StartTime + gap * 2166, "i", "short", 0),
			new Beat(StartTime + gap * 2166, "l", "short", 0),
			new Beat(StartTime + gap * 2171, "a", "short", 0),
			new Beat(StartTime + gap * 2174, "l", "short", 0),
			new Beat(StartTime + gap * 2179, "l", "short", 0),
			new Beat(StartTime + gap * 2181, "a", "short", 0),
			new Beat(StartTime + gap * 2181, "d", "short", 0),
			new Beat(StartTime + gap * 2186, "i", "short", 0),
			new Beat(StartTime + gap * 2186, "l", "short", 0),
			new Beat(StartTime + gap * 2191, "a", "short", 0),
			new Beat(StartTime + gap * 2195, "l", "short", 0),
			new Beat(StartTime + gap * 2201, "a", "short", 0),
			new Beat(StartTime + gap * 2201, "d", "short", 0),
			new Beat(StartTime + gap * 2206, "i", "short", 0),
			new Beat(StartTime + gap * 2206, "l", "short", 0),
			new Beat(StartTime + gap * 2211, "a", "short", 0),
			new Beat(StartTime + gap * 2215, "l", "short", 0),
			new Beat(StartTime + gap * 2220, "a", "short", 0),
			new Beat(StartTime + gap * 2220, "d", "short", 0),
			new Beat(StartTime + gap * 2225, "i", "short", 0),
			new Beat(StartTime + gap * 2225, "l", "short", 0),
			new Beat(StartTime + gap * 2231, "a", "short", 0),
			new Beat(StartTime + gap * 2236, "l", "short", 0),
			new Beat(StartTime + gap * 2241, "a", "short", 0),
			new Beat(StartTime + gap * 2241, "d", "short", 0),
			new Beat(StartTime + gap * 2245, "i", "short", 0),
			new Beat(StartTime + gap * 2245, "l", "short", 0),
			new Beat(StartTime + gap * 2251, "a", "short", 0),
			new Beat(StartTime + gap * 2252, "i", "short", 0),
			new Beat(StartTime + gap * 2260, "a", "short", 0),
			new Beat(StartTime + gap * 2261, "i", "short", 0),
			new Beat(StartTime + gap * 2267, "i", "short", 0),
			new Beat(StartTime + gap * 2270, "a", "short", 0),
			new Beat(StartTime + gap * 2276, "d", "short", 0),
			new Beat(StartTime + gap * 2276, "a", "short", 0),
			new Beat(StartTime + gap * 2282, "a", "short", 0),
			new Beat(StartTime + gap * 2282, "i", "short", 0),
			new Beat(StartTime + gap * 2286, "d", "short", 0),
			new Beat(StartTime + gap * 2286, "a", "short", 0),

			new Beat(StartTime + 1960 * gap + gap * 331, "a", "long", 75),
			new Beat(StartTime + 1960 * gap + gap * 331, "i", "long", 75),

			new Beat(StartTime + 1960 * gap + gap * 362, "j", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 362, "l", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 372, "d", "long", 45),
			new Beat(StartTime + 1960 * gap + gap * 372, "i", "long", 45),

			new Beat(StartTime + 1960 * gap + gap * 392, "j", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 392, "l", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 402, "a", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 402, "i", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 412, "d", "long", 150),
			new Beat(StartTime + 1960 * gap + gap * 412, "l", "long", 150),

			new Beat(StartTime + 1960 * gap + gap * 482, "d", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 482, "l", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 491, "a", "long", 75),
			new Beat(StartTime + 1960 * gap + gap * 491, "i", "long", 75),

			new Beat(StartTime + 1960 * gap + gap * 521, "d", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 521, "l", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 531, "a", "long", 45),
			new Beat(StartTime + 1960 * gap + gap * 531, "j", "long", 45),

			new Beat(StartTime + 1960 * gap + gap * 550, "d", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 550, "i", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 561, "j", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 561, "l", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 571, "a", "long", 75),
			new Beat(StartTime + 1960 * gap + gap * 571, "i", "long", 75),

			new Beat(StartTime + 1960 * gap + gap * 611, "j", "long", 45),
			new Beat(StartTime + 1960 * gap + gap * 611, "l", "long", 45),

			new Beat(StartTime + 1960 * gap + gap * 633, "d", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 633, "i", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 642, "a", "long", 15),
			new Beat(StartTime + 1960 * gap + gap * 642, "j", "long", 15),

			new Beat(StartTime + 1960 * gap + gap * 652, "d", "short", 0),
			new Beat(StartTime + 1960 * gap + gap * 654, "j", "short", 0),
			new Beat(StartTime + 1960 * gap + gap * 656, "i", "short", 0),

			new Beat(StartTime + gap * 2618, "d", "short", 0),
			new Beat(StartTime + gap * 2620, "i", "short", 0),
			new Beat(StartTime + gap * 2624, "j", "short", 0),
			new Beat(StartTime + gap * 2629, "l", "short", 0),
			new Beat(StartTime + gap * 2630, "j", "short", 0),
			new Beat(StartTime + gap * 2634, "a", "short", 0),
			new Beat(StartTime + gap * 2636, "i", "short", 0),
			new Beat(StartTime + gap * 2638, "j", "short", 0),
			new Beat(StartTime + gap * 2641, "a", "short", 0),
			new Beat(StartTime + gap * 2643, "i", "short", 0),
			new Beat(StartTime + gap * 2645, "d", "short", 0),
			new Beat(StartTime + gap * 2648, "a", "short", 0),
			new Beat(StartTime + gap * 2651, "j", "short", 0),
			new Beat(StartTime + gap * 2654, "i", "short", 0),
			new Beat(StartTime + gap * 2657, "a", "short", 0),
			new Beat(StartTime + gap * 2660, "i", "short", 0),
			new Beat(StartTime + gap * 2663, "a", "short", 0),
			new Beat(StartTime + gap * 2665, "j", "short", 0),
			new Beat(StartTime + gap * 2669, "d", "short", 0),
			new Beat(StartTime + gap * 2673, "i", "short", 0),
			new Beat(StartTime + gap * 2675, "j", "short", 0),
			new Beat(StartTime + gap * 2677, "a", "short", 0),
			new Beat(StartTime + gap * 2680, "l", "short", 0),
			new Beat(StartTime + gap * 2683, "d", "short", 0),
			new Beat(StartTime + gap * 2685, "j", "short", 0),
			new Beat(StartTime + gap * 2689, "a", "short", 0),
			new Beat(StartTime + gap * 2690, "j", "short", 0),
			new Beat(StartTime + gap * 2692, "l", "short", 0),
			new Beat(StartTime + gap * 2697, "l", "short", 0),
			new Beat(StartTime + gap * 2700, "a", "short", 0),
			new Beat(StartTime + gap * 2703, "j", "short", 0),
			new Beat(StartTime + gap * 2707, "l", "short", 0),
			new Beat(StartTime + gap * 2711, "j", "short", 0),
			new Beat(StartTime + gap * 2712, "a", "short", 0),
			new Beat(StartTime + gap * 2716, "i", "short", 0),
			new Beat(StartTime + gap * 2718, "j", "short", 0),
			new Beat(StartTime + gap * 2721, "a", "short", 0),
			new Beat(StartTime + gap * 2723, "l", "short", 0),
			new Beat(StartTime + gap * 2726, "d", "short", 0),
			new Beat(StartTime + gap * 2728, "a", "short", 0),
			new Beat(StartTime + gap * 2730, "j", "short", 0),
			new Beat(StartTime + gap * 2732, "i", "short", 0),
			new Beat(StartTime + gap * 2737, "a", "short", 0),
			new Beat(StartTime + gap * 2739, "i", "short", 0),
			new Beat(StartTime + gap * 2743, "a", "short", 0),
			new Beat(StartTime + gap * 2745, "j", "short", 0),
			new Beat(StartTime + gap * 2747, "l", "short", 0),
			new Beat(StartTime + gap * 2750, "d", "short", 0),
			new Beat(StartTime + gap * 2754, "a", "short", 0),
			new Beat(StartTime + gap * 2754, "i", "short", 0),
			new Beat(StartTime + gap * 2757, "l", "short", 0),
			new Beat(StartTime + gap * 2761, "i", "short", 0),
			new Beat(StartTime + gap * 2764, "j", "short", 0),
			new Beat(StartTime + gap * 2768, "d", "short", 0),
			new Beat(StartTime + gap * 2770, "j", "short", 0),
			new Beat(StartTime + gap * 2772, "i", "short", 0),
			new Beat(StartTime + gap * 2776, "d", "short", 0),
			new Beat(StartTime + gap * 2778, "i", "short", 0),
			new Beat(StartTime + gap * 2782, "d", "short", 0),
			new Beat(StartTime + gap * 2783, "j", "short", 0),
			new Beat(StartTime + gap * 2786, "l", "short", 0),
			new Beat(StartTime + gap * 2790, "j", "short", 0),
			new Beat(StartTime + gap * 2792, "a", "short", 0),
			new Beat(StartTime + gap * 2795, "i", "short", 0),
			new Beat(StartTime + gap * 2797, "l", "short", 0),
			new Beat(StartTime + gap * 2801, "a", "short", 0),
			new Beat(StartTime + gap * 2802, "i", "short", 0),
			new Beat(StartTime + gap * 2805, "d", "short", 0),
			new Beat(StartTime + gap * 2808, "a", "short", 0),
			new Beat(StartTime + gap * 2810, "j", "short", 0),
			new Beat(StartTime + gap * 2812, "i", "short", 0),
			new Beat(StartTime + gap * 2817, "a", "short", 0),
			new Beat(StartTime + gap * 2819, "i", "short", 0),
			new Beat(StartTime + gap * 2821, "a", "short", 0),
			new Beat(StartTime + gap * 2824, "j", "short", 0),
			new Beat(StartTime + gap * 2827, "l", "short", 0),
			new Beat(StartTime + gap * 2831, "a", "short", 0),
			new Beat(StartTime + gap * 2831, "j", "short", 0),
			new Beat(StartTime + gap * 2835, "i", "short", 0),
			new Beat(StartTime + gap * 2838, "j", "short", 0),
			new Beat(StartTime + gap * 2841, "a", "short", 0),
			new Beat(StartTime + gap * 2843, "i", "short", 0),
			new Beat(StartTime + gap * 2846, "d", "short", 0),
			new Beat(StartTime + gap * 2848, "a", "short", 0),
			new Beat(StartTime + gap * 2850, "j", "short", 0),
			new Beat(StartTime + gap * 2852, "l", "short", 0),
			new Beat(StartTime + gap * 2857, "a", "short", 0),
			new Beat(StartTime + gap * 2860, "l", "short", 0),
			new Beat(StartTime + gap * 2863, "a", "short", 0),
			new Beat(StartTime + gap * 2866, "j", "short", 0),
			new Beat(StartTime + gap * 2868, "l", "short", 0),
			new Beat(StartTime + gap * 2871, "j", "short", 0),
			new Beat(StartTime + gap * 2873, "a", "short", 0),
			new Beat(StartTime + gap * 2876, "i", "short", 0),
			new Beat(StartTime + gap * 2879, "l", "short", 0),
			new Beat(StartTime + gap * 2882, "a", "short", 0),
			new Beat(StartTime + gap * 2883, "i", "short", 0),
			new Beat(StartTime + gap * 2886, "d", "short", 0),
			new Beat(StartTime + gap * 2889, "a", "short", 0),
			new Beat(StartTime + gap * 2892, "i", "short", 0),
			new Beat(StartTime + gap * 2897, "a", "short", 0),
			new Beat(StartTime + gap * 2903, "a", "short", 0),
			new Beat(StartTime + gap * 2908, "l", "short", 0),
			new Beat(StartTime + gap * 2913, "d", "short", 0),
			new Beat(StartTime + gap * 2916, "i", "short", 0),
			new Beat(StartTime + gap * 2918, "l", "short", 0),
			new Beat(StartTime + gap * 2922, "i", "short", 0),
			new Beat(StartTime + gap * 2927, "a", "short", 0),
			new Beat(StartTime + gap * 2928, "l", "short", 0),
			new Beat(StartTime + gap * 2964, "a", "short", 0),
			new Beat(StartTime + gap * 2966, "d", "short", 0),
			new Beat(StartTime + gap * 2968, "j", "short", 0),
			new Beat(StartTime + gap * 2970, "i", "short", 0),
			new Beat(StartTime + gap * 2972, "l", "short", 0),

			new Beat(StartTime + gap * 3011, "i", "short", 0),

			new Beat(StartTime + gap * 3013, "l", "short", 0),
			new Beat(StartTime + gap * 3015, "i", "short", 0),
			new Beat(StartTime + gap * 3017, "l", "short", 0),
			new Beat(StartTime + gap * 3019, "d", "short", 0),};
		}
	}

	public void dropNotes() {
		if (titleName.equals("Legend OF Moonlight")) {
			leoMoon("easy");
		} else if (titleName.equals("Mirro Night")) {
			mirroNight("easy");
		} else if (titleName.equals("Evolution Ero")) {
			beats = new Beat[] {

			};
		} else if (titleName.equals("Aura")) {
			Aura("easy");
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i = 0;
		gameMusic.start();

		while (i < beats.length && !isInterrupted()) {

			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName(), beats[i].getNoteLong(), beats[i].getNoteNum());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
				// D삭제하기
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}
//		try {
//			Thread.sleep(5000);
//			result(titleName);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public void judge(String input) {

		for (Note note : noteList) {
			String noteJudge = note.judge("start");
			if (note.getNoteLong().equals("short")) {
				if (input.equals(note.getNoteType())) {
					judgeEvent(noteJudge, input);
					break;
				} else if (note.getNoteLong().equals("long")) {
					if (input.equals(note.getNoteType())) {
						judgeEvent(noteJudge, input);
						break;
					}
				}
			}
		}
	}

	public void judgeLong(String input) {
		for (Note note : noteList) {
			String noteJudge = note.judge("pressed");
			if (note.getNoteLong().equals("short")) {
				return;
			} else if (note.getNoteLong().equals("long")) {
				if (input.equals(note.getNoteType())) {
					judgeEvent(noteJudge, input);
					break;
				}
			}
		}
	}

	public void judgeFlare(String input) {
	
		if (input.equals("a")) {
			if(judgeA) {
				judgeA=false;
			}
			judgeA=true;
			Timer timer =new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					judgeA=false;
				}
			};
			timer.schedule(task, 200);
		} else if (input.equals("d")) {

		} else if (input.equals("j")) {

		} else if (input.equals("i")) {

		} else if (input.equals("l")) {

		}
	}

	public void judgeEvent(String judge, String input) {
		if (!judge.equals("NONE")) {
			flareImage = new ImageIcon(Main.class.getResource("../Images/flareImage.png")).getImage();
		}
		if (judge.equals("KOOL")) {
			judgeImage = new ImageIcon(Main.class.getResource("../Images/KOOL.png")).getImage();
			combo++;
			koolnum++;
			judgeFlare(input);
			score += 10 * (combo / 20 + 1);
		} else if (judge.equals("GOOD")) {
			judgeImage = new ImageIcon(Main.class.getResource("../Images/GOOD.png")).getImage();
			combo++;
			goodnum++;
			judgeFlare(input);
			score += 5 * (combo / 20 + 1);
		} else if (judge.equals("COOL")) {
			judgeImage = new ImageIcon(Main.class.getResource("../Images/COOL.png")).getImage();
			combo++;
			coolnum++;
			judgeFlare(input);
			score += 7 * (combo / 20 + 1);
		} else if (judge.equals("MISS")) {
			judgeImage = new ImageIcon(Main.class.getResource("../Images/Miss.png")).getImage();
			combo = 0;
			missnum++;
		}
	}

	public int maxCombo() {
		if (combo >= maxCombo) {
			maxCombo = combo;
		}
		return maxCombo;
	}

	public void result(String titleName) {
		if (gameMusic != null) {
			gameMusic.close();
		}
		if (titleName.equals("Legend OF Moonlight")) {
			gameMusic = new Music("LegendofMoonlighthighlight.mp3", true);
		} else if (titleName.equals("Mirro Night")) {
			gameMusic = new Music("MirrornightHighlight.mp3", true);
		} else if (titleName.equals("Evolution Ero")) {
			gameMusic = new Music("EvolutioneroHighlight.mp3", true);
		} else if (titleName.equals("Aura")) {
			gameMusic = new Music("Aurahighlight.mp3", true);
		}
		gameMusic.start();
		DynamicBeat.isGameScreen = false;
		DynamicBeat.result = new Result(koolnum, coolnum, goodnum, missnum, maxCombo(), score, titleName);
//		DynamicBeat.result.insertDB(DynamicBeat.result);
		DynamicBeat.isResultScreen = true;
		DynamicBeat.background = new ImageIcon(Main.class.getResource("../Images/resultImage.png")).getImage();

	}
}
