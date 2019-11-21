package RhythmGame4;

public class Beat {
	private int time;
	private String noteName;
	private int noteNum;
	private String noteLong;
	
	public Beat(int time, String noteName, String noteLong, int noteNum) {
		super();
		this.time = time;
		this.noteName = noteName;
		this.noteNum = Main.NOTE_SPEED/15*noteNum;
		this.noteLong = noteLong;
	}
	public int getNoteNum() {
		return noteNum;
	}
	public void setNoteNum(int noteNum) {
		this.noteNum = Main.NOTE_SPEED/15*noteNum;
	}
	public String getNoteLong() {
		return noteLong;
	}
	public void setNoteLong(String noteLong) {
		this.noteLong = noteLong;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	
}
