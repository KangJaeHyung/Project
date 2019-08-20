package RhythmGame4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasic1 = new ImageIcon(Main.class.getResource("../Images/noteBasic1.png")).getImage();
	private Image noteBasic2 = new ImageIcon(Main.class.getResource("../Images/noteBasic2.png")).getImage();
	private int x;
	private int y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private String noteColor;
	private String noteLong;
	private int noteNum;
	private int width = noteBasic1.getWidth(null);
	private int height;
	private BufferedImage longNote1;
	private BufferedImage longNote2;
	private boolean proceeded = true;
	boolean isPressed = false;
	private int endY;

	public void setProceeded(boolean proceeded) {
		this.proceeded = proceeded;
	}

	public int getY() {
		return y;
	}

	public String getNoteLong() {
		return noteLong;
	}

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	public Note(String PressedButton, String noteLong, int noteNum) {
		this.noteLong = noteLong;
		this.noteNum = noteNum;
		height = noteBasic1.getHeight(null) * noteNum;
		if (PressedButton.equals("a")) {
			x = 440;
			noteColor = "Note1";
			noteType = "a";
			if (noteLong.equals("long")) {
				longNote1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics g = longNote1.getGraphics();
				for (int i = 0; i < this.noteNum; i++) {
					g.drawImage(noteBasic1, 0, i * 20, null);
				}
			}
		} else if (PressedButton.equals("d")) {
			x = 520;
			noteColor = "Note2";
			noteType = "d";
			if (noteLong.equals("long")) {
				longNote2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) longNote2.getGraphics();
				for (int i = 0; i < this.noteNum; i++) {
					g.drawImage(noteBasic2, 0, i * 20, null);
				}
			}
		} else if (PressedButton.equals("j")) {
			x = 600;
			noteColor = "Note1";
			noteType = "j";
			if (noteLong.equals("long")) {
				longNote1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) longNote1.getGraphics();
				for (int i = 0; i < this.noteNum; i++) {
					g.drawImage(noteBasic1, 0, i * 20, null);
				}
			}
		} else if (PressedButton.equals("i")) {
			x = 680;
			noteColor = "Note2";
			noteType = "i";
			if (noteLong.equals("long")) {
				longNote2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) longNote2.getGraphics();
				for (int i = 0; i < this.noteNum; i++) {
					g.drawImage(noteBasic2, 0, i * 20, null);
				}
			}
		} else if (PressedButton.equals("l")) {
			x = 760;
			noteColor = "Note1";
			noteType = "l";
			if (noteLong.equals("long")) {
				longNote1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) longNote1.getGraphics();
				for (int i = 0; i < this.noteNum; i++) {
					g.drawImage(noteBasic1, 0, i * 20, null);
				}
			}
		}

	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if (y > 800 && noteLong.equals("short")) {
//			System.out.println("outMiss");
			close();
		} else if (y > 800 && noteLong.equals("long")) {
			if (noteType.equals("a")) {
				if (!Game.pressedA) {
					isPressed = false;
					close();
				}
			} else if (noteType.equals("d")) {
				if (!Game.pressedD) {
//					System.out.println("πﬂµøµ ");
					isPressed = false;
					close();
				}
			} else if (noteType.equals("j")) {
				if (!Game.pressedJ) {
//					System.out.println("πﬂµøµ ");
					isPressed = false;
					close();
				}
			} else if (noteType.equals("i")) {
				if (!Game.pressedI) {
//					System.out.println("πﬂµøµ ");
					isPressed = false;
					close();
				}
			} else if (noteType.equals("l")) {
				if (!Game.pressedL) {
//					System.out.println("πﬂµøµ ");
					isPressed = false;
					close();
				}
			}

		}
	}

	public void screenDraw(Graphics2D g) {
		if (noteColor.equals("Note1")) {
			if (noteLong.equals("short")) {
				g.drawImage(noteBasic1, x, y, null);
			} else if (noteLong.equals("long")) {
				drawLongNote1(g);

			}
		} else if (noteColor.equals("Note2")) {
			if (noteLong.equals("short")) {
				g.drawImage(noteBasic2, x, y, null);
			} else if (noteLong.equals("long")) {
				drawLongNote2(g);
			}
		}
	}

	public void drawLongNote1(Graphics2D g) {
		if (!isPressed) {
			if (y < 680) {
				g.drawImage(longNote1, x, y - 20 * noteNum, x + 80, y, 0, 0, 80, 20 * noteNum, null);
			} else {
				g.drawImage(longNote1, x, y - 20 * noteNum, x + 80, 680, 0, 0, 80, 20 * noteNum - (y - 680), null);
			}
		} else {
			g.drawImage(longNote1, x, y - 20 * noteNum, x + 80, 610, 0, 0, 80, 20 * noteNum - (y - 610), null);
		}
	}

	public void drawLongNote2(Graphics2D g) {
		if (!isPressed) {
			if (y < 680) {
				g.drawImage(longNote2, x, y - 20 * noteNum, x + 80, y, 0, 0, 80, 20 * noteNum, null);
			} else {
				g.drawImage(longNote2, x, y - 20 * noteNum, x + 80, 680, 0, 0, 80, 20 * noteNum - (y - 680), null);
			}
		} else {
			g.drawImage(longNote2, x, y - 20 * noteNum, x + 80, 610, 0, 0, 80, 20 * noteNum - (y - 610), null);
		}
	}

	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String startJudge;

	public String judge(String input) {
		if (input.equals("start")) {
			if (noteLong.equals("short")) {
				if (noteType.equals("a") && Game.pressedA) {
					if (y >= 790) {
//			System.out.println("miss");
						close();
						return "MISS";
					} else if (y >= 770) {
//			System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 700) {
//			System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 600) {
//			System.out.println("kool");
						close();
						return "KOOL";
					} else if (y >= 530) {
//			System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 520) {
//			System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 500) {
//			System.out.println("miss");
						close();
						return "MISS";
					}
				} else if (noteType.equals("d") && Game.pressedD) {
					if (y >= 790) {
//					System.out.println("miss");
						close();
						return "MISS";
					} else if (y >= 770) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 700) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 600) {
//					System.out.println("kool");
						close();
						return "KOOL";
					} else if (y >= 530) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 520) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 450) {
//					System.out.println("miss");
						close();
						return "MISS";
					}
				} else if (noteType.equals("j") && Game.pressedJ) {
					if (y >= 790) {
//					System.out.println("miss");
						close();
						return "MISS";
					} else if (y >= 770) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 700) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 600) {
//					System.out.println("kool");
						close();
						return "KOOL";
					} else if (y >= 530) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 520) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 450) {
//					System.out.println("miss");
						close();
						return "MISS";
					}
				} else if (noteType.equals("i") && Game.pressedI) {
					if (y >= 790) {
//					System.out.println("miss");
						close();
						return "MISS";
					} else if (y >= 770) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 700) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 600) {
//					System.out.println("kool");
						close();
						return "KOOL";
					} else if (y >= 530) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 520) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 450) {
//					System.out.println("miss");
						close();
						return "MISS";
					}
				} else if (noteType.equals("l") && Game.pressedL) {
					if (y >= 790) {
//					System.out.println("miss");
						close();
						return "MISS";
					} else if (y >= 770) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 700) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 600) {
//					System.out.println("kool");
						close();
						return "KOOL";
					} else if (y >= 530) {
//					System.out.println("cool");
						close();
						return "COOL";
					} else if (y >= 520) {
//					System.out.println("good");
						close();
						return "GOOD";
					} else if (y >= 450) {
//					System.out.println("miss");
						close();
						return "MISS";
					}
				}

			} else if (noteLong.equals("long") && !isPressed) {
				if (noteType.equals("a") && Game.pressedA) {
					if (y >= 790) {
//				System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						return "MISS";
					} else if (y >= 770) {
//				System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 700) {
//				System.out.println("cool")
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 600) {
//				System.out.println("kool");
						startJudge = "KOOL";
						isPressed = true;
						return "KOOL";
					} else if (y >= 530) {
//				System.out.println("cool");
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 520) {
//				System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 460) {
//				System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						proceeded = false;
						return "MISS";
					}
				} else if (noteType.equals("d") && Game.pressedD) {
					if (y >= 790) {
//					System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						return "MISS";
					} else if (y >= 770) {
//					System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 700) {
//					System.out.println("cool")
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 600) {
//					System.out.println("kool");
						startJudge = "KOOL";
						isPressed = true;
						return "KOOL";
					} else if (y >= 530) {
//					System.out.println("cool");
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 520) {
//					System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 460) {
//					System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						proceeded = false;
						return "MISS";
					}
				} else if (noteType.equals("j") && Game.pressedJ) {
					if (y >= 790) {
//						System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						return "MISS";
					} else if (y >= 770) {
//						System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 700) {
//						System.out.println("cool")
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 600) {
//						System.out.println("kool");
						startJudge = "KOOL";
						isPressed = true;
						return "KOOL";
					} else if (y >= 530) {
//						System.out.println("cool");
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 520) {
//						System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 460) {
//						System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						proceeded = false;
						return "MISS";
					}
				} else if (noteType.equals("i") && Game.pressedI) {
					if (y >= 790) {
//							System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						return "MISS";
					} else if (y >= 770) {
//							System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 700) {
//							System.out.println("cool")
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 600) {
//							System.out.println("kool");
						startJudge = "KOOL";
						isPressed = true;
						return "KOOL";
					} else if (y >= 530) {
//							System.out.println("cool");
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 520) {
//							System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 460) {
//							System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						proceeded = false;
						return "MISS";
					}
				} else if (noteType.equals("l") && Game.pressedL) {
					if (y >= 790) {
//								System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						return "MISS";
					} else if (y >= 770) {
//								System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 700) {
//								System.out.println("cool")
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 600) {
//								System.out.println("kool");
						startJudge = "KOOL";
						isPressed = true;
						return "KOOL";
					} else if (y >= 530) {
//								System.out.println("cool");
						startJudge = "COOL";
						isPressed = true;
						return "COOL";
					} else if (y >= 520) {
//								System.out.println("good");
						startJudge = "GOOD";
						isPressed = true;
						return "GOOD";
					} else if (y >= 460) {
//								System.out.println("miss");
						startJudge = "MISS";
						isPressed = true;
						proceeded = false;
						return "MISS";
					}
				}
			}
		}
		if (input.equals("pressed")) {
			if (isPressed && noteLong.equals("long")) {
				if (endY > 630) {
					proceeded = false;
				}
				if (proceeded) {
					endY = y - noteNum * 20;
//				System.out.println("µ«∞Ì¿÷¿Ω");
//				System.out.println(endY);
					return startJudge;
				}

			}
		}
		return "NONE";
	}

}
