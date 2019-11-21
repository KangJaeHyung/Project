package Diesel;


import Diesel.Main;

public class Game_info {
	private String game_code;
	private String game_name;
	private int game_price;
	private String game_launch_date;
	private String game_grade;
	private String game_main_genre;
	private String game_sub_genre;
	private String game_image_dir;
	private String game_bigimage_dir;
	private String game_icon_dir;
	
	public Game_info() {
	}
	public Game_info(String game_code, String game_name, int game_price, String game_launch_date, String game_grade,
			String game_main_genre, String game_sub_genre, String game_image_dir, String game_bigimage_dir, String game_icon_dir) {
		super();
		this.game_code = game_code;
		this.game_name = game_name;
		this.game_price = game_price;
		this.game_launch_date = game_launch_date;
		this.game_grade = game_grade;
		this.game_main_genre = game_main_genre;
		this.game_sub_genre = game_sub_genre;
		this.game_image_dir = game_image_dir;
		this.game_bigimage_dir = game_bigimage_dir;
		this.game_icon_dir = game_icon_dir;
	}
	
	

	public void setGame_icon_dir(String game_icon_dir) {
		this.game_icon_dir = game_icon_dir;
	}
	public String getGame_icon_dir() {
		return game_icon_dir;
	}
	public String getGame_code() {
		return game_code;
	}

	public void setGame_code(String game_code) {
		this.game_code = game_code;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public int getGame_price() {
		return game_price;
	}

	public void setGame_price(int game_price) {
		this.game_price = game_price;
	}

	public String getGame_launch_date() {
		return game_launch_date;
	}

	public void setGame_launch_date(String game_launch_date) {
		this.game_launch_date = game_launch_date;
	}

	public String getGame_grade() {
		return game_grade;
	}

	public void setGame_grade(String game_grade) {
		this.game_grade = game_grade;
	}

	public String getGame_main_genre() {
		return game_main_genre;
	}

	public void setGame_main_genre(String game_main_genre) {
		this.game_main_genre = game_main_genre;
	}

	public String getGame_sub_genre() {
		return game_sub_genre;
	}

	public void setGame_sub_genre(String game_sub_genre) {
		this.game_sub_genre = game_sub_genre;
	}

	public String getGame_image_dir() {
		return game_image_dir;
	}

	public void setGame_image_dir(String game_image_dir) {
		this.game_image_dir = game_image_dir;
		
	}

	public String getGame_bigimage_dir() {
		return game_bigimage_dir;
	}

	public void setGame_bigimage_dir(String game_bigimage_dir) {
		this.game_bigimage_dir = game_bigimage_dir;
		
	}

}
