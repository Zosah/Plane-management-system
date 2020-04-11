package core.admin.jpanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import core.admin.statistics.BarChart;
import core.admin.statistics.PieChart;
import core.admin.statistics.StatisticsData;
import core.database.Database;
import other.AddComonents;
import other.Global;

public class StatisticsJPanel {
	private static Color c = new Color(205,235,234);
	private static JPanel jp_show;
	private static JLabel jl_head;
	private static JButton jb_cancel;
	private static JLabel jl_line1;
	private static JLabel jl_line2;
	private static JLabel jl_select;
	private static JComboBox<String> jcb_startCity;
	private static JComboBox<String> jcb_startAirport;
	private static JComboBox<String> jcb_reachCity;
	private static JComboBox<String> jcb_reachAirport;
	private static JButton jb_generate;
	private static JComboBox jcb_year;
	private static JComboBox jcb_month;
	private static JComboBox jcb_flight;
	private static JLabel jl_select2;
	private static String startCity;
	private static String startAirport;
	private static String reachCity;
	private static String reachAirport;
	private static String year;
	private static String month;
	private static JPanel jp_showTable;
	private static JTable table;
	private static JLabel tips2;
	private static JPanel jp_chart;
	@SuppressWarnings("unchecked")
	
	public static void init(JFrame jf) {
		jp_show = AddComonents.addJPanel(jf, 150, 78, 693, 445); /* jp_show.setBackground(c); */
		jp_show.setBorder(BorderFactory.createLineBorder(Color.black, 1));jp_show.setVisible(false);
		jl_head = AddComonents.addJLabel("统计报表", jp_show, 300, 10, 100, 30);
		jb_cancel = AddComonents.addJButton("取消", jp_show, 620, 11, 60, 30, true, true, false);
		jl_line1 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_show, 0, 30, 800, 30);
		jl_select = AddComonents.addJLabel("请选择：", jp_show, 30, 60, 100, 30);
		jcb_startCity = AddComonents.addJComboBox(jp_show, getList(1,""), "-出发城市-", 90, 60, 90, 30);
		jcb_startAirport = AddComonents.addJComboBox(jp_show, getList(2,""), "", 200, 60, 90, 30);
		jcb_reachCity = AddComonents.addJComboBox(jp_show, getList(3,""), "-到达城市-", 340, 60, 90, 30);
		jcb_reachAirport = AddComonents.addJComboBox(jp_show, getList(4,""), "", 450, 60, 90, 30);
		jb_generate = AddComonents.addJButton("生成报表", jp_show, 580, 60, 90, 70, false, true, false);
		try {
			jcb_year = AddComonents.addJComboBox(jp_show, new Database().getFlightYearOrMonth(1,"")
					,"-查询年份-", 90, 100, 90, 30);
		} catch (SQLException e1) {
			System.out.println("获得年份失败月份！");
		}
		jcb_month = AddComonents.addJComboBox(jp_show, getList(2,""), "", 200, 100, 90, 30);
		jcb_flight = AddComonents.addJComboBox(jp_show, getList(4,""), "", 340, 100, 120, 30);
		jl_select2 = AddComonents.addJLabel("(可选航班）", jp_show, 470, 100, 100, 30);
//		tips2 = AddComonents.addJLabel("占比：订座率占比（计算公式：(周订座量 & 月订座量) / 月航班座位总量）", jp_show, 5, 145, 600, 30);
		jp_showTable = AddComonents.addJPanel_2(jp_show, 1, 140, 691, 49);jp_showTable.setVisible(true);
		String[][] arr= {{"无","无","无","无","无","无","无"}};
		addTable(arr);	//添加表格
		jp_chart = AddComonents.addJPanel_2(jp_show, 1, 188, 691, 255);jp_chart.setVisible(true);
		jp_chart.setLayout(new GridLayout(1,2,10,10));
		//JCombobox触发事件
		jcb_startCity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> list = getList(2,String.valueOf(jcb_startCity.getSelectedItem()));
				jcb_startAirport.removeAllItems();
				for (String str : list) {
					jcb_startAirport.addItem(str);
				}
				if(fill(false)) {
					jcb_flight.removeAllItems();
					for (String str : fillFlightItem()) {
						jcb_flight.addItem(str);
					}
				}else {
					jcb_flight.removeAllItems();
				}
			}
		});
		
		jcb_reachCity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> list = getList(4,String.valueOf(jcb_reachCity.getSelectedItem())); 
				jcb_reachAirport.removeAllItems();
				for (String str : list) {
					jcb_reachAirport.addItem(str);
				}
				if(fill(false)) {	//触发后满足他条件，则自动填充Jcmb，否则去除
					jcb_flight.removeAllItems();
					for (String str : fillFlightItem()) {
						jcb_flight.addItem(str);
					}
				}else {
					jcb_flight.removeAllItems();
				}
			}
			
		});
		
		jcb_year.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> list;
				try {
					list = new Database().getFlightYearOrMonth(0,String.valueOf(jcb_year.getSelectedItem()));
					jcb_month.removeAllItems();
					for (String str : list) {
						jcb_month.addItem(str);
					}
				} catch (SQLException e1) {
					System.out.println("填充月份失败！");
				}
				if(fill(false)) {
					jcb_flight.removeAllItems();
					for (String str : fillFlightItem()) {
						jcb_flight.addItem(str);
					}
				}else {
					jcb_flight.removeAllItems();
				}
			}
		});
		
		jb_generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fill(true)){
					addTable(StatisticsData.getArr());
					if(StatisticsData.getFlightCount() != 0) {
						addChart();
					}
				}
			}
		});
	}
	
	public static void addTable(Object[][] arr) {
		jp_showTable.setVisible(false);
		jp_showTable = AddComonents.addJPanel_2(jp_show, 1, 140, 691, 49);	//把原面板影藏，重新新建并赋值
		Object[] title = {"月航班数","第一周单量&占比","第二周单量&占比","第三周单量&占比","第四周单量&占比","第五周单量&占比","总单量&总占比"};	//表头
		table = AddComonents.addJTable(title, arr, jp_showTable, false);
	}
	
	public static void addChart() {
		jp_chart.setVisible(false); 							//把原面板影藏，重新新建并赋值
		jp_chart = AddComonents.addJPanel_2(jp_show, 1, 188, 691, 255);jp_chart.setVisible(true);
		jp_chart.setLayout(new GridLayout(1,2,10,10));
		jp_chart.add(new BarChart().getChartPanel());           //添加柱形图
		jp_chart.add(new PieChart().getChartPanel());           //添加饼状图
	}
	
	//填充航班
	public static List<String> fillFlightItem() {
		List<String> list = new ArrayList<String>();
		for(int i = 1; i <= 31; i++) {	//生成 天
			String day;
			if(i<10) {
				day = "0"+String.valueOf(i);
			}else {
				day = String.valueOf(i);
			}
			String date = year+"-"+month+"-"+day;	//生成日期
			//查询航班,放进集合里
			String sql = "select * from flight where date='"+date+"' and start_city='"+startCity+"' and start_airport='"
					+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"';";
			try {
				ResultSet rs = new Database().getSelect(sql);
				while(rs.next()) {
					String flight = rs.getString("flight_num");
					if(!list.contains(flight)) {
						list.add(flight);
					}
				}
			} catch (SQLException e) {
				System.out.println("查询数据库填充航班失败！");
			}
		}
		return list;
	}
	
	//填满检查
	public static boolean fill(boolean tips) {
		getJcmbItem();
		if(startCity.equals("-出发城市-")) {
			if(tips)
				JOptionPane.showMessageDialog(Global.getJf2(), "请输入或选择航班号！");
			return false;
		}else if(reachCity.equals("-到达城市-")) {
			if(tips)
				JOptionPane.showMessageDialog(Global.getJf2(), "请选择到达城市！");
			return false;
		}else if(year.equals("-查询年份-")) {
			if(tips)
				JOptionPane.showMessageDialog(Global.getJf2(), "请查询年份！");
			return false;
		}else
			return true;
	}
	
	//初始化下拉框内容，返回list集合
	public static List<String> getList(int i,String city) {
		List<String> list = new ArrayList<String>();
		if(city.equals("") && i%2==0) {
			return list;
		}
		String sql1 = "select start_city from flight";
		String sql2 = "select start_airport from flight where start_city = '"+city+"';";
		String sql3 = "select reach_city from flight";
		String sql4 = "select reach_airport from flight where reach_city = '"+city+"';";
		String target;
		String sql;
		if(i==1) {
			sql = sql1;
			target="start_city";
		}else if(i==2) {
			sql = sql2;
			target="start_airport";
		}else if(i==3) {
			sql = sql3;
			target="reach_city";
		}else {
			sql = sql4;
			target="reach_airport";
		}
		try {
			ResultSet rs = new Database().getSelect(sql);
			while(rs.next()) {
				String temp = rs.getString(target);
				if(!list.contains(temp)) {
					list.add(temp);
				}
			}
			return list;
		} catch (SQLException e) {
			System.out.println("统计报表-获取开始机场出错！");
		}
		return list;
	}
	
	//获得下拉框的值
	public static void getJcmbItem() {
		startCity = String.valueOf(jcb_startCity.getSelectedItem());
		startAirport = String.valueOf(jcb_startAirport.getSelectedItem());
		reachCity = String.valueOf(jcb_reachCity.getSelectedItem());
		reachAirport = String.valueOf(jcb_reachAirport.getSelectedItem());
		year = String.valueOf(jcb_year.getSelectedItem());
		month = String.valueOf(jcb_month.getSelectedItem());
	}

	public static String getStartCity() {
		return startCity;
	}
	public static String getStartAirport() {
		return startAirport;
	}
	public static String getReachCity() {
		return reachCity;
	}
	public static String getReachAirport() {
		return reachAirport;
	}
	public static String getYear() {
		return year;
	}
	public static String getMonth() {
		return month;
	}
	public static String getFlight() {
		return String.valueOf(jcb_flight.getSelectedItem());
	}
	public static JButton getJb_cancel() {
		return jb_cancel;
	}

	public static JPanel getJp_show() {
		return jp_show;
	}
	
}
