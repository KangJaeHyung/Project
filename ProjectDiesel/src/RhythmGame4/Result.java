package RhythmGame4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
	private int kool;
	private int cool;
	private int good;
	private int miss;
	private int max_combo;
	private int score;
	private String titleName;
	private String play_date;
	public int getKool() {
		return kool;
	}

	public int getCool() {
		return cool;
	}

	public int getGood() {
		return good;
	}

	public int getMiss() {
		return miss;
	}

	public int getMax_combo() {
		return max_combo;
	}

	public int getScore() {
		return score;
	}

	public String getPlay_date() {
		return play_date;
	}

	public Result(int kool, int cool, int good, int miss, int maxCombo, int score,String titleName) {
		super();
		this.kool = kool;
		this.cool = cool;
		this.good = good;
		this.miss = miss;
		this.max_combo = maxCombo;
		this.score = score;
		this.titleName=titleName;
		Date today=new Date();
		SimpleDateFormat date=new SimpleDateFormat("yy/MM/dd/HH:mm:ss");
		play_date=date.format(today);
	}
	
	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public void setKool(int kool) {
		this.kool = kool;
	}

	public void setCool(int cool) {
		this.cool = cool;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public void setMiss(int miss) {
		this.miss = miss;
	}

	public void setMax_combo(int max_combo) {
		this.max_combo = max_combo;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setPlay_date(String play_date) {
		this.play_date = play_date;
	}

	public Result() {
		
	}
	
	public void insertDB(Result re) {
		CrudProcess crud=new CrudProcess();
		if (titleName.equals("Legend OF Moonlight")) {
			crud.insert_legend_of_moonlight(re);
		} else if (titleName.equals("Mirro Night")) {
			crud.insert_mirro_night(re);
		} else if (titleName.equals("Evolution Ero")) {
		
		} else if (titleName.equals("Aura")) {
			
		}
	}

	public void screenDraw(Graphics2D g) {
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(kool+"", 300, 316);
		g.drawString(cool+"", 300, 366);
		g.drawString(good+"", 300, 416);
		g.drawString(miss+"", 300, 466);
		g.drawString(max_combo+"", 300, 518);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString(score+"", 250, 598);
	}
}
