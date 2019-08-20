package Diesel;

public class Game_sale {
	private String game_order_code;
	private String user_id;
	private int payment;
	private String order_date;
	private String game_code;
	private int isRefunded;

	
	public Game_sale() {

	}

	public int getIsRefunded() {
		return isRefunded;
	}

	public void setIsRefunded(int isRefunded) {
		this.isRefunded = isRefunded;
	}


	public String getGame_order_code() {
		return game_order_code;
	}

	public void setGame_order_code(String game_order_code) {
		this.game_order_code = game_order_code;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getGame_code() {
		return game_code;
	}

	public void setGame_code(String game_code) {
		this.game_code = game_code;
	}

}
