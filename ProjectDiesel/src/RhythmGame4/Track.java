package RhythmGame4;

public class Track {
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return StartMusic;
	}
	public void setStartMusic(String startMusic) {
		StartMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	private String titleImage; //���� �κ� �̹���
	private String startImage; //���� ����â ǥ�� �̹���
	private String gameImage; //�ش�� ���� ǥ�� �̹���
	private String StartMusic; //���� ���� â ���
	private String gameMusic; //�ش� ���� ���������� ����
	private String titleName;
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic,String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		StartMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName= titleName;
	}
	
	
}
