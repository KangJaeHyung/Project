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
			JOptionPane.showMessageDialog(null, "�̹� �������Դϴ�.");
			return;
		}
		JOptionPane.showMessageDialog(null, "������ ����˴ϴ�!");
		game = new DynamicBeat();
	}
	
}
