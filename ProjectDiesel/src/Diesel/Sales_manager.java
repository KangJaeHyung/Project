package Diesel;


import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;


public class Sales_manager extends JPanel{
	private ImageIcon cal_b = new ImageIcon(Main.class.getResource("../resource/관리자_달력.png"));
	private ImageIcon cal_p = new ImageIcon(Main.class.getResource("../resource/관리자_달력_반응.png"));
	
	private DiselFrame j;
	private SalesCalendar sc;
	private JTextField caltext;
	private JLabel dayChargelabel, dayChargeCount;
	private JLabel daySaleslabel, daySalesCount;
	private JLabel monthSaleslabel, monthSalesCount;
	private JLabel yearSaleslabel, yearSalesCount;
	private JButton btn_cal;
	private List ChargeList;
	private JTable table;
	private int year;
	private int month;
	private int date;
	private String today;
	public TableModel model; 
	
	
	static ArrayList<Charge_sale> chargeList = new ArrayList<Charge_sale>();
	
	
	public Sales_manager(DiselFrame j){

		this.j = j;
		this.setBackground(Color.DARK_GRAY);
		setButton();
		setText();
		setLabel();
		
		
	
}
	public void dayCountCash() {
		CrudProcess crud = new CrudProcess();
		Charge_sale cs= new Charge_sale();
		String day = caltext.getText();
		cs.setCharge_date(day);
		crud.selectCountCash(cs);
		Integer a = crud.selectCountCash(cs);
		if(a==null) {
			a=0;
		}
		dayChargeCount.setText(a+"회");
	}
	public void dayIncome() {
		CrudProcess crud = new CrudProcess();
		Charge_sale cs= new Charge_sale();
		
		String day = caltext.getText();
		//System.out.println(day);
		
		cs.setCharge_date(day);
		
		Integer a = crud.selectDayIncome(cs);
		Charge_refund cr= new Charge_refund();
		cr.setRefund_date(day);
		Integer b = crud.selectDayIncome2(cr);
		if(a==null) {
			a=0;
		}
		if(b==null) {
			b=0;
		}
		daySalesCount.setText(a+b+"원");

	}
	
	public void monthIncome() {
		CrudProcess crud = new CrudProcess();
		Charge_sale cs= new Charge_sale();
		String day = caltext.getText();
		String splitDay= day.substring(0,day.length()-3);
		cs.setCharge_date(splitDay);
		Charge_refund cr= new Charge_refund();
		cr.setRefund_date(splitDay);
		Integer b = crud.selectDayIncome2(cr);
		Integer a = crud.selectDayIncome(cs);
		if(a==null) {
			a=0;
		}
		if(b==null) {
			b=0;
		}
		monthSalesCount.setText(a+b+"원");
	}
	
	public void yearIncome() {
		CrudProcess crud = new CrudProcess();
		Charge_sale cs= new Charge_sale();
		String day = caltext.getText();
		String splitMonth= day.substring(0,4);
		cs.setCharge_date(splitMonth);
		Charge_refund cr= new Charge_refund();
		cr.setRefund_date(splitMonth);
		Integer b = crud.selectDayIncome2(cr);
		
		Integer a = crud.selectDayIncome(cs);
		if(a==null) {
			a=0;
		}
		if(b==null) {
			b=0;
		}
		yearSalesCount.setText(a+b+"원");
	}
	public void setButton() {
		btn_cal = new JButton(cal_b);
		btn_cal.setBounds(790, 25, 200, 100);
		btn_cal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				Integer num = null;
				num = 0;
				sc = new SalesCalendar(j.sm);
				}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_cal.setIcon(cal_b);
				btn_cal.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn_cal.setIcon(cal_p);
				btn_cal.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		add(btn_cal);
	}
	public void setText() {
		caltext = new JTextField(20);
		caltext.setBounds(790, 140, 200, 30);
		caltext.setBackground(Color.DARK_GRAY);
		caltext.setForeground(Color.white);
		caltext.setEnabled(false);
		add(caltext);
	}
	public void setLabel() {
		removeAll();
		dayChargelabel = new JLabel("오늘 충전 횟수");
		dayChargelabel.setBounds(50, 10, 400, 100);
		dayChargelabel.setFont(new Font("휴먼모음T", Font.PLAIN, 28));
		dayChargelabel.setForeground(Color.white);
		add(dayChargelabel);
		
		dayChargeCount = new JLabel();
		dayChargeCount.setBounds(50, 20, 200, 200);
		dayChargeCount.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		dayChargeCount.setForeground(Color.white);
		dayChargeCount.setHorizontalAlignment(SwingConstants.RIGHT);
		add(dayChargeCount);
		
		
		daySaleslabel = new JLabel("오늘 충전 금액");
		daySaleslabel.setBounds(530, 10, 400, 100);
		daySaleslabel.setFont(new Font("휴먼모음T", Font.PLAIN, 28));
		daySaleslabel.setForeground(Color.white);
		add(daySaleslabel);
		
		daySalesCount = new JLabel();
		daySalesCount.setBounds(300, 20, 450, 200);
		daySalesCount.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		daySalesCount.setForeground(Color.white);
		daySalesCount.setHorizontalAlignment(SwingConstants.RIGHT);
		add(daySalesCount);
		
		monthSaleslabel = new JLabel("이번달 총 수입");
		monthSaleslabel.setBounds(50, 300, 400, 100);
		monthSaleslabel.setFont(new Font("휴먼모음T", Font.PLAIN, 28));
		monthSaleslabel.setForeground(Color.white);
		add(monthSaleslabel);
		
		monthSalesCount = new JLabel();
		monthSalesCount.setBounds(50, 320, 300, 200);
		monthSalesCount.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		monthSalesCount.setForeground(Color.white);
		monthSalesCount.setHorizontalAlignment(SwingConstants.RIGHT);
		add(monthSalesCount);
		
		yearSaleslabel = new JLabel("이번년도 총 수입");
		yearSaleslabel.setBounds(530, 300, 400, 100);
		yearSaleslabel.setFont(new Font("휴먼모음T", Font.PLAIN, 28));
		yearSaleslabel.setForeground(Color.white);
		add(yearSaleslabel);
		
		yearSalesCount = new JLabel();
		yearSalesCount.setBounds(300, 320, 450, 200);
		yearSalesCount.setFont(new Font("휴먼모음T", Font.PLAIN, 50));
		yearSalesCount.setForeground(Color.white);
		yearSalesCount.setHorizontalAlignment(SwingConstants.RIGHT);
		add(yearSalesCount);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		caltext.setText(simple.format(date));
		dayIncome();
		monthIncome();
		yearIncome();
		dayCountCash();
		setButton();
		setText();
	}
	public void cal2late() {
		try {
			
			
			
			
//			int r = 0;
//			while (it.hasNext()) {
//				Charge_sale cc = (Charge_sale) it.next();
//				tableData[r][0] = cc.getMoney_order_code();
//				tableData[r][1] = cc.getUser_id();
//				tableData[r][2] = cc.getCharge_cash();
//				tableData[r][3] = cc.getCharge_date();
//				
			
		
		

			JOptionPane.showMessageDialog(null, "완료");
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "매출정보를 불러오는데에 실패했습니다");
		}
		
	}

	
class SalesCalendar extends JFrame implements WindowListener, ActionListener, ItemListener {
	private Choice chyear, chmonth;
	private JLabel yLabel, mLabel;
	GregorianCalendar gc;
	private int year, month;
	private JLabel[] dayLabel = new JLabel[7];
	private String[] day = { "일", "월", "화", "수", "목", "금", "토" };
	private JButton[] days = new JButton[42];// 7일이 6주이므로 42개의 버튼필요
	private JPanel selectPanel = new JPanel();
	private GridLayout grid = new GridLayout(7, 7, 5, 5);// 행,열,수평갭,수직갭
	private Calendar ca = Calendar.getInstance();
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Sales_manager q1;

//	private EmpSystem es;
	public SalesCalendar(Sales_manager q1) {
		this.q1 = q1;
//		this.es = es;
//		this.std2 = std2;
		setTitle("달력 - 오늘:" + ca.get(Calendar.YEAR) + "/" + (ca.get(Calendar.MONTH) + 1) + "/" + ca.get(Calendar.DATE));
		setSize(550, 500);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		setLocation(xpos, ypos);// 화면의 가운데에 출력
		setResizable(false);
		setVisible(true);
		chyear = new Choice();
		chmonth = new Choice();
		
		yLabel = new JLabel("년");
		mLabel = new JLabel("월");
		addWindowListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}

	public void actionPerformed(ActionEvent arg0) {
		String year = chyear.getSelectedItem();
		String month="";
		System.out.println(chmonth.getSelectedItem().length());
		if(chmonth.getSelectedItem().length()==1) {
			 month = "0"+chmonth.getSelectedItem();
	//		System.out.println(month);
		}else if(chmonth.getSelectedItem().length()==2) {
			month = chmonth.getSelectedItem();
		}
		
		JButton btn = (JButton) arg0.getSource();
			String day = "";
			if(btn.getText().toString().length()==1) {
				day="0"+btn.getText().toString();
			}else if(btn.getText().toString().length()==2) {
				day=btn.getText().toString();
			}
		
		
		String date = year + "-" + month + "-" + day;
		if (q1 != null) {
				q1.caltext.setText(date);
				dayIncome();
				monthIncome();
				yearIncome();
				dayCountCash();
				String year1=caltext.getText().substring(0,4);
				String months1=caltext.getText().substring(5,7);
				String day1=caltext.getText().substring(8,10);
				dayChargelabel.setText(months1+"월 "+day1+"일 총 충전횟수");
				daySaleslabel.setText(months1+"월 "+day1+"일 총 수입");
				monthSaleslabel.setText(year1+"년 "+ months1+"월 총 수입");
				yearSaleslabel.setText(year1+"년 총 수입");
				dispose();
			
		}
		// 월에 따라서 일을 채운다. 시작
	}

	public void init() {
		select();
		calendar();
	}

	public void select() {
		JPanel panel = new JPanel(grid);// 7행 7열의 그리드레이아웃
		for (int i = 2020; i >= 2000; i--) {
			chyear.add(String.valueOf(i));
		}
		for (int i = 1; i <= 12; i++) {
			chmonth.add(String.valueOf(i));
		}
		for (int i = 0; i < day.length; i++) {// 요일 이름을 레이블에 출력
			dayLabel[i] = new JLabel(day[i], JLabel.CENTER);
			panel.add(dayLabel[i]);
			dayLabel[i].setBackground(Color.GRAY);// 사실상 의미가 없슴. 바뀌지 않음.
		}
		dayLabel[6].setForeground(Color.BLUE);// 토요일 색상
		dayLabel[0].setForeground(Color.RED);// 일요일 색상
		for (int i = 0; i < 42; i++) {// 모두 42개의 버튼을 생성
			days[i] = new JButton("");// 제목이 없는 버튼 생성
			if (i % 7 == 0)
				days[i].setForeground(Color.RED);// 일요일 버튼의 색
			else if (i % 7 == 6)
				days[i].setForeground(Color.BLUE);// 토요일 버튼의 색
			else
				days[i].setForeground(Color.BLACK);
			days[i].addActionListener(this);
			panel.add(days[i]);
		}
		selectPanel.add(chyear);
		selectPanel.add(yLabel);
		selectPanel.add(chmonth);
		selectPanel.add(mLabel);
		this.add(selectPanel, "North");// 연도와 월을 선택할 수 있는 화면읠 상단에 출력
		this.add(panel, "Center");
		String m = (ca.get(Calendar.MONTH) + 1) + "";
		String y = ca.get(Calendar.YEAR) + "";
		chyear.select(y);
		chmonth.select(m);
		chyear.addItemListener(this);
		chmonth.addItemListener(this);
	}

	public void calendar() {
		year = Integer.parseInt(chyear.getSelectedItem());
		month = Integer.parseInt(chmonth.getSelectedItem());
		gc = new GregorianCalendar(year, month - 1, 1);
		int max = gc.getActualMaximum(gc.DAY_OF_MONTH);// 해당 달의 최대 일 수 획득
		int week = gc.get(gc.DAY_OF_WEEK);// 해당 달의 시작 요일
		System.out.println("DAY_OF_WEEK:" + week);
		String today = Integer.toString(ca.get(Calendar.DATE));// 오늘 날짜 획득
		String today_month = Integer.toString(ca.get(Calendar.MONTH) + 1);// 오늘의 달 획득
		System.out.println("today:" + today);
		for (int i = 0; i < days.length; i++) {
			days[i].setEnabled(true);
		}
		for (int i = 0; i < week - 1; i++) {// 시작일 이전의 버튼을 비활성화
			days[i].setEnabled(false);
		}
		for (int i = week; i < max + week; i++) {
			days[i - 1].setText((String.valueOf(i - week + 1)));
			days[i - 1].setBackground(Color.WHITE);
			if (today_month.equals(String.valueOf(month))) {// 오늘이 속한 달과 같은 달인 경우
				if (today.equals(days[i - 1].getText())) {// 버튼의 날짜와 오늘날짜가 일치하는 경우
					days[i - 1].setBackground(Color.CYAN);// 버튼의 배경색 지정
				}
			}
		}
		for (int i = (max + week - 1); i < days.length; i++) {// 날짜가 없는 버튼을 비활성화
			days[i].setEnabled(false);
		}
		System.out.println("max+week:" + (max + week) + ",week:" + week);
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		arg0.getWindow().dispose();
//		System.exit(0);
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	public void itemStateChanged(ItemEvent arg0) {
		Color color = this.getBackground();
		if (arg0.getStateChange() == ItemEvent.SELECTED) {
			for (int i = 0; i < 42; i++) {// 년이나 월이 선택되면 기존의 달력을 지우고 새로 그린다.
				if (!days[i].getText().equals("")) {
					days[i].setText("");// 기존의 날짜를 지움
					days[i].setBackground(color);// 달력의 배경색과 동일한 색으로 버튼의 배경색을 설정함.
				}
			}
			calendar();
		}
	}

//	public static void main(String[] args) {
//		new CalendarBySwing();
//	}
}
}



