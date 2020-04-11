package core.admin.statistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import core.admin.jpanel.StatisticsJPanel;
import core.database.Database;
import other.Global;

//统计面板响应类
public class StatisticsData {
	public static String startCity;
	public static String startAirport;
	public static String reachCity;
	public static String reachAirport;
	public static String year;
	public static String month;
	public static String flight;
	private static ResultSet rs = null;	//保存查询集
	private static int flightCount = 0;
	private static List<String[]> list;
	private static String[] orderArrs;
	private static String[] rateArrs;
	//获得字段
	public static void setText() {
		startCity = StatisticsJPanel.getStartCity();
		startAirport = StatisticsJPanel.getStartAirport();
		reachCity = StatisticsJPanel.getReachCity();
		reachAirport = StatisticsJPanel.getReachAirport();
		year = StatisticsJPanel.getYear();
		month = StatisticsJPanel.getMonth();
		flight = StatisticsJPanel.getFlight();
	}
	
	//获得查询集
	public static void getData() {
		setText();
		String sql = "select * from flight where flight_num='"
				+flight+"' and start_city='"+startCity+"' and start_airport='"+startAirport
				+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"';";
		try {
			rs = new Database().getSelect(sql);
		} catch (SQLException e) {
			System.out.println("统计数据获得查询集失败！");
		}
		flightCount = getFlightCount(rs);
		list = getCountAndRate(rs);
	}
	
	public static Object[][] getArr(){
		getData();
		Object[][] arr = new Object[1][7];	//表格体数组
		arr[0][0] = getFlightCount(rs);
		if(getFlightCount(rs) == 0) {
			Object[][] temp= {{"无","无","无","无","无","无","无"}};
			JOptionPane.showMessageDialog(Global.getJf2(), "暂无航班报表！");
			return temp;
		}
		for (int i = 1; i < arr[0].length; i++) {
			arr[0][i] = orderArrs[i-1] +" & "+rateArrs[i-1]+"%";
		}
		return arr;
	}
	
	
	
	//获得航班数
	public static int getFlightCount(ResultSet rs) {
		int count = 0;
		try {
			rs.last();
			count = rs.getRow();	//获得最后一行即为长度
			rs.beforeFirst();		//置为头位置
		} catch (SQLException e) {
			System.out.println("统计数据获得航班数失败！");
		}
		return count;
	}
	
	//获得每周单量、比例、返回数组
	public static List<String[]> getCountAndRate(ResultSet rs) {
		List<String[]> list = new ArrayList<String[]>();
		String tempdate;	//临时变量
		int[] orderTempArr = new int[6];				//存放订单数（通过判断减少的座位数）
		orderArrs = new String[6];
		rateArrs = new String[6];
		try {
			//扫描
			while(rs.next()) {
				String date = rs.getString("date");	//获得日期，判断是多少号
				int seatCount = Integer.valueOf(rs.getString("seat_count"));
				for(int i = 1; i<=31; i++) {
					if(i<10) {
						tempdate = year+"-"+month+"-"+"0"+i;
					}else {
						tempdate = year+"-"+month+"-"+i;
					}
					//如果日期相等，拿出当前航班数据，得到某日、的座位数
					if(tempdate.equals(date)) {
						if(i>=1 && i<=7) {			//第一周
							orderTempArr[0] += Global.SEAT_COUMT-seatCount;	  //获得第1周订单数
						}else if(i>=8 && i<=14) {	//第二周
							orderTempArr[1] += Global.SEAT_COUMT-seatCount;	  //获得第2周订单数
						}else if(i>=15 && i<=21) {	//第三周
							orderTempArr[2] += Global.SEAT_COUMT-seatCount;	  //获得第3周订单数
						}else if(i>=22 & i<=28) {	//第四周
							orderTempArr[3] += Global.SEAT_COUMT-seatCount;	  //获得第4周订单数
						}else {						//第五周
							orderTempArr[4] += Global.SEAT_COUMT-seatCount;	  //获得第5周订单数
						}
						orderTempArr[5] += Global.SEAT_COUMT-seatCount;	 	 //获得总订单数
					}
				}
			}
			rs.beforeFirst();	//置为头位置
			for (int i = 0; i < rateArrs.length; i++) {
				//做成比例
				double rate = 0;
				if(i != rateArrs.length-1) {	
					rate = (double)orderTempArr[i]/(orderTempArr[orderTempArr.length-1]);
				}
				else{	//如果是总数位
					rate = (double)orderTempArr[i]/(Global.SEAT_COUMT*getFlightCount(rs));
				}
				DecimalFormat df = new DecimalFormat("#.00");
				rateArrs[i] = df.format(rate*100);
				orderArrs[i] = String.valueOf(orderTempArr[i]);
			}
			list.add(orderArrs);
			list.add(rateArrs);
			return list;
		} catch (SQLException e) {
			System.out.println("获得集合数据失败！");
			return list;
		}
	}

	public static String[] getOrderArrs() {
		return orderArrs;
	}
	public static int getFlightCount() {
		return flightCount;
	}
	
}
