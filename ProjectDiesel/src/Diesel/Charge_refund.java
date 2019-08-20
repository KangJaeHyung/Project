package Diesel;

public class Charge_refund {
	private String money_order_code;
	private String user_id;
	private int refund_cash;
	private String refund_date;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getRefund_cash() {
		return refund_cash;
	}
	public void setRefund_cash(int refund_cash) {
		this.refund_cash = refund_cash;
	}
	
	public String getMoney_order_code() {
		return money_order_code;
	}
	public void setMoney_order_code(String money_order_code) {
		this.money_order_code = money_order_code;
	}
	public String getRefund_date() {
		return refund_date;
	}
	public void setRefund_date(String rech_date) {
		this.refund_date = rech_date;
	}
	
}
