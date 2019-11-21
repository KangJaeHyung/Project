package Diesel;

public class Charge_sale {
	private String money_order_code;
	private String user_id;
	private int charge_cash;
	private String charge_date;
	public String getMoney_order_code() {
		return money_order_code;
	}
	public void setMoney_order_code(String money_order_code) {
		this.money_order_code = money_order_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCharge_cash() {
		return charge_cash;
	}
	public void setCharge_cash(int charge_cash) {
		this.charge_cash = charge_cash;
	}
	public String getCharge_date() {
		return charge_date;
	}
	public void setCharge_date(String charge_date) {
		this.charge_date = charge_date;
	}
	@Override
	public String toString() {
		return "Charge_sale [money_order_code=" + money_order_code + ", user_id=" + user_id + ", charge_cash="
				+ charge_cash + ", charge_date=" + charge_date + "]";
	}
	
}
