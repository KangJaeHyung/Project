package Diesel;

import java.util.ArrayList;

public class Customer_info {
	private String user_id;
	private String password;
	private String e_mail;
	private String phone_num;
	private String country;
	private int user_cash;
	private int user_stat;

	public Customer_info() {

	}
	public Customer_info(String user_id, String password) {
		super();
		this.user_id = user_id;
		this.password = password;

	}
	public Customer_info(String user_id, String password, String e_mail, String phone_num, String country) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.e_mail = e_mail;
		this.phone_num = phone_num;
		this.country = country;
		user_cash=0;
		user_stat=0;
	}
	public Customer_info(String user_id, String e_mail, String phone_num, String country, int user_stat) {
		this.user_id = user_id;
		this.e_mail = e_mail;
		this.phone_num = phone_num;
		this.country = country;
		user_cash=0;
		this.user_stat= user_stat;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getUser_cash() {
		return user_cash;
	}
	public void setUser_cash(int user_cash) {
		this.user_cash = user_cash;
	}
	public int getUser_stat() {
		return user_stat;
	}
	public void setUser_stat(int user_stat) {
		this.user_stat = user_stat;
	}

}
