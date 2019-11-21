package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;



public class SalesTable extends JPanel{
	
	
	ImageIcon search = new ImageIcon(Main.class.getResource("../resource/관리자_조회.png"));
	ImageIcon search_p = new ImageIcon(Main.class.getResource("../resource/관리자_조회_반응.png"));
	ImageIcon re_b = new ImageIcon(Main.class.getResource("../resource/갱신.png"));
	ImageIcon re_p = new ImageIcon(Main.class.getResource("../resource/갱신_반응.png"));
	
	ImageIcon ch_b = new ImageIcon(Main.class.getResource("../resource/충전.png"));
	ImageIcon ch_p = new ImageIcon(Main.class.getResource("../resource/충전_반응.png"));
	ImageIcon ref_b = new ImageIcon(Main.class.getResource("../resource/환불.png"));
	ImageIcon ref_p = new ImageIcon(Main.class.getResource("../resource/환불_반응.png"));


	public static boolean isChargePage = true;
	public static boolean isRefundPage = false;
	
	private DiselFrame j;
	private JScrollPane u_table, rf_table;
	private JButton btn_search, btn_re, btn_charge, btn_refund;
	private JTextField searchField;
	private JTable chargeTable, refundTable;
	static ArrayList<Charge_sale> charge_info = new ArrayList<Charge_sale>();
	static ArrayList<Charge_refund> refund_info = new ArrayList<Charge_refund>();
	private chargeInfoTable uit, uit2;
	private refundInfoTable ruit, ruit2;
	public TableModel chargeModel, refundModel; 
	int nRow;
	Object Value;
	
	public void tableReset() {
		if(isChargePage == true) {
		uit = new chargeInfoTable();
		uit.fireTableDataChanged();
		chargeTable.setModel(uit);
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = chargeTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}}else {
			ruit = new refundInfoTable();
			ruit.fireTableDataChanged();
			refundTable.setModel(ruit);
			DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcmSchedule = refundTable.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
		}
		
	}
	
	
	public void chargeDraw() {
		CrudProcess crud = new CrudProcess();
		charge_info = (ArrayList<Charge_sale>) crud.selectAllCharge(); 
		uit = new chargeInfoTable();
		chargeTable = new JTable();
		chargeTable.setModel(uit);
		chargeTable.setRowHeight(30);
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = chargeTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}
	
	public void refundDraw() {
		CrudProcess crud = new CrudProcess();
		refund_info = (ArrayList<Charge_refund>) crud.selectAllRefund(); 
		ruit = new refundInfoTable();
		refundTable = new JTable();
		refundTable.setModel(ruit);
		refundTable.setRowHeight(30);
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = refundTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}
	
	public SalesTable(DiselFrame j) {
		this.j=j;
		
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		CrudProcess crud = new CrudProcess();
		chargeDraw();
		refundDraw();
		
	u_table=new JScrollPane(chargeTable);
	rf_table=new JScrollPane(refundTable);
	u_table.setBounds(25,25,850,500);
	rf_table.setBounds(25, 25, 850, 500);
	add(u_table);
	
	add(rf_table);


	searchField = new JTextField("", 20);
	searchField.setForeground(Color.WHITE);
	searchField.setBackground(Color.GRAY);
	searchField.setBounds(575, 545, 300, 40);
	add(searchField);
	
	
	btn_charge = new JButton(ch_b);
	btn_charge.setBounds(890, 125, 100, 35);
	btn_charge.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
			u_table.setVisible(true);
			rf_table.setVisible(false);
			isChargePage = true;
			isRefundPage = false;
			 
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btn_charge.setIcon(ch_b);
			btn_charge.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btn_charge.setIcon(ch_p);
			btn_charge.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	});
	add(btn_charge);
	
	btn_refund = new JButton(ref_b);
	btn_refund.setBounds(890, 170, 100, 35);
	btn_refund.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {

			rf_table.setVisible(true);
			u_table.setVisible(false);
			isChargePage = false;
			isRefundPage = true;
			 
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btn_refund.setIcon(ref_b);
			btn_refund.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btn_refund.setIcon(ref_p);
			btn_refund.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	});
	add(btn_refund);
	
	btn_re = new JButton(re_b);
	btn_re.setBounds(890, 25, 100, 50);
	btn_re.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			tableReset();
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btn_re.setIcon(re_b);
			btn_re.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btn_re.setIcon(re_p);
			btn_re.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	});
	add(btn_re);
	
	btn_search = new JButton(search);
	btn_search.setBounds(890, 540, 100, 50);
	btn_search.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
			if(isChargePage==true){
				tableReset();
				String str = searchField.getText();
				List<Charge_sale> cin = crud.selectCharge(str);
				uit2 = new chargeInfoTable(cin);
				uit2.fireTableDataChanged();
				chargeTable.setModel(uit2);
				DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
				tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				TableColumnModel tcmSchedule = chargeTable.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
				}
			}if(isRefundPage==true) {
				String str = searchField.getText();
				List<Charge_refund> crf = crud.selectRefund(str);
				ruit2 = new refundInfoTable(crf);
				ruit2.fireTableDataChanged();
				refundTable.setModel(ruit2);
				DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
				tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				TableColumnModel tcmSchedule = refundTable.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
				}
				
			}
			
			
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			btn_search.setIcon(search);
			btn_search.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			btn_search.setIcon(search_p);
			btn_search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	});
	add(btn_search);
	

}


class chargeInfoTable extends AbstractTableModel {
	private Object[][] tableData;
	int cols, rows;
	private String[] columnName = 
		{ "Money Order Code", "User ID", "Charge Cash", 
			"Charge date"};
	private List chargeList;
	
	chargeInfoTable(List<Charge_sale> charge_info){//조건조회
		chargeList = charge_info;
		setData();
	}
	
	chargeInfoTable() {//전체조회
		CrudProcess crud = new CrudProcess();
		chargeList = crud.selectAllCharge();
		setData();
	}
	
	void setData() {
		Iterator<Charge_sale> it = chargeList.iterator();
		rows = chargeList.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while (it.hasNext()) {
			Charge_sale cc = (Charge_sale) it.next();
			tableData[r][0] =  cc.getMoney_order_code();
			tableData[r][1] = cc.getUser_id();
			tableData[r][2] = cc.getCharge_cash();
			tableData[r][3] = cc.getCharge_date();
			
			r++;
		}
		
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnName[arg0];
	}

	public int getColumnCount() {
		return cols;
	}

	public int getRowCount() {
		return rows;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData[rowIndex][columnIndex];
	}
	
}}


class refundInfoTable extends AbstractTableModel {
	private Object[][] tableData;
	int cols, rows;
	private String[] columnName = 
		{ "Money Order Code", "User ID", "Refund Cash", 
			"Refund date"};
	private List refundList;
	
	refundInfoTable(List<Charge_refund> refund_info){//조건조회
		refundList = refund_info;
		setData();
	}
	
	refundInfoTable() {//전체조회
		CrudProcess crud = new CrudProcess();
		refundList = crud.selectAllRefund();
		setData();
	}
	
	void setData() {
		Iterator<Charge_refund> it = refundList.iterator();
		rows = refundList.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while (it.hasNext()) {
			Charge_refund cf = (Charge_refund) it.next();
			tableData[r][0] =  cf.getMoney_order_code();
			tableData[r][1] = cf.getUser_id();
			tableData[r][2] = cf.getRefund_cash();
			tableData[r][3] = cf.getRefund_date();
			
			r++;
		}
		
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnName[arg0];
	}

	public int getColumnCount() {
		return cols;
	}

	public int getRowCount() {
		return rows;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData[rowIndex][columnIndex];
	}
	
}
