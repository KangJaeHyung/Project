package Diesel;

public class Sales_info {
	
	private String game_code;
	private String game_name;
	private int sale_record;
	private int game_price;
	private int sales_gross;
	
	
	
	
	public Sales_info(){
		
	}
	
	public Sales_info(String game_code, int sale_record, int sales_gross){
		this.game_code = game_code;
		this.sale_record =sale_record;
		this.sales_gross = sales_gross;
	}
	public Sales_info(String game_code, String game_name, int sale_record, int game_price, int sales_gross){
			this.game_code = game_code;
			this.game_name = game_name;
			this.sale_record =sale_record;
			this.game_price = game_price;
			this.sales_gross = sales_gross;
		}
	
	
	public void setGame_code(String game_code) {
		this.game_code = game_code;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public void setSale_record(int sale_record) {
		this.sale_record = sale_record;
	}
	public void setGame_price(int game_price) {
		this.game_price = game_price;
	}
	public void setSales_gross(int sales_gross) {
		this.sales_gross = sales_gross;
	}
	public String getGame_code() {
		return game_code;
	}
	public String getGame_name() {
		return game_name;
	}
	public int getSale_record() {
		return sale_record;
	}
	public int getGame_price() {
		return game_price;
	}
	public int getSales_gross() {
		return sales_gross;
	}


}
