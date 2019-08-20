package RhythmGame4;

import javax.swing.JOptionPane;

public class Main {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED=15;
	public static final int SLEEP_TIME=10;
	public static final int REACH_TIME=1;
	public static DynamicBeat game;
	public static void game() {
		if(game!=null) {
			JOptionPane.showMessageDialog(null, "이미 실행중입니다.");
			return;
		}
		JOptionPane.showMessageDialog(null, "게임이 실행됩니다!");
		game = new DynamicBeat();
	}
	
}
