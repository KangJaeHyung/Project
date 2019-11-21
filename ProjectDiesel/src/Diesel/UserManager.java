package Diesel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class UserManager extends JPanel {
	DiselFrame j;
	AdminChange ac;

	ImageIcon edit = new ImageIcon(Main.class.getResource("../resource/관리자_수정.png"));
	ImageIcon edit_p = new ImageIcon(Main.class.getResource("../resource/관리자_수정_반응.png"));
	ImageIcon search = new ImageIcon(Main.class.getResource("../resource/관리자_조회.png"));
	ImageIcon search_p = new ImageIcon(Main.class.getResource("../resource/관리자_조회_반응.png"));
	ImageIcon re_b = new ImageIcon(Main.class.getResource("../resource/갱신.png"));
	ImageIcon re_p = new ImageIcon(Main.class.getResource("../resource/갱신_반응.png"));

	
	static ArrayList<Customer_info> user_info = new ArrayList<Customer_info>();
	private JScrollPane u_table;
	private JButton btn_edit, btn_search, btn_re;
	private JTextField searchField;
	private JTable table;
	public TableModel model; 
	private UserInfoTable uit, uit2;
	int nRow;
	Object Value;
	
	public void tableReset() {
		 
		uit = new UserInfoTable();
		uit.fireTableDataChanged();
		table.setModel(uit);
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}

	UserManager(DiselFrame j) {
		CrudProcess crud = new CrudProcess();
		user_info = (ArrayList<Customer_info>) crud.selectAllUser(); 
		
		this.j = j;
		
		nRow = -1;
		
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);

		uit = new UserInfoTable();
		
		table = new JTable();
		table.setModel(uit);
		table.setRowHeight(30);
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getComponent();
				model = table.getModel();
				nRow = table.getSelectedRow();
				DiselFrame.ac.setTextString(table);
				
			}
		});
		

	u_table=new JScrollPane(table);
	u_table.setBounds(25,25,850,500);
	add(u_table);
		
		
		
		btn_edit = new JButton(edit);
		btn_edit.setBounds(890, 470, 100, 50);
		btn_edit.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				
				if(nRow==-1){
					JOptionPane.showMessageDialog(null, "수정할 유저를 클릭해주십시오");
				}else{
				DiselFrame.isProductManagerPage = false;
				DiselFrame.isUserManagerPage = false;
				DiselFrame.isAdminChange = true;}

//				public static boolean isManagement = false;
//				public static boolean isLoginPage = true;
//				public static boolean isJoinPage = false;
//				public static boolean isFindPage = false;
//				public static boolean isMainPage = false;
//				public static boolean isStorePage = false;
//				public static boolean isButtonPage = false;
//				public static boolean isMyPage = false;
//				public static boolean isAdminPage = false;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btn_edit.setIcon(edit);
				btn_edit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn_edit.setIcon(edit_p);
				btn_edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(btn_edit);
		
		searchField = new JTextField("", 20);
		searchField.setForeground(Color.WHITE);
		searchField.setBackground(Color.GRAY);
		searchField.setBounds(575, 545, 300, 40);
		add(searchField);
		
		
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
				
				if(searchField.getText().equals("")){
					tableReset();
					
				}else {
					String str = searchField.getText();
					List<Customer_info> cin = crud.selectInfoA(str);
					uit2 = new UserInfoTable(cin);
					uit2.fireTableDataChanged();
					table.setModel(uit2);
					DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
					tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
					TableColumnModel tcmSchedule = table.getColumnModel();
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
	

class UserInfoTable extends AbstractTableModel {
	private Object[][] tableData;
	int cols, rows;
	private String[] columnName = 
		{ "User ID", "E-Mail", "Phone Number", 
			"Country", "Cash", "Stat"};
	private List UserList;
	
	UserInfoTable(List<Customer_info> User_info){//조건조회
		UserList = User_info;
		setData();
	}
	
	UserInfoTable() {//전체조회
		CrudProcess crud = new CrudProcess();
		UserList = crud.selectAllUser();
		setData();
	}
	
	void setData() {
		Iterator<Customer_info> it = UserList.iterator();
		rows = UserList.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while (it.hasNext()) {
			Customer_info cc = (Customer_info) it.next();
			tableData[r][0] = cc.getUser_id();
			tableData[r][1] = cc.getE_mail();
			tableData[r][2] = cc.getPhone_num();
			tableData[r][3] = cc.getCountry();
			tableData[r][4] = cc.getUser_cash();
			if(cc.getUser_stat()==0) {
				tableData[r][5] = "일반 유저";
			}else if(cc.getUser_stat()==1) {
				tableData[r][5] = "밴 유저";
			}else if(cc.getUser_stat()==2) {
				tableData[r][5] = "관리자";
			}
//			tableData[r][5] = cc.getUser_stat();
			
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
