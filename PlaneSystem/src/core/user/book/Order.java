package core.user.book;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import core.database.Database;
import core.user.jpanel.BackAlterJPanel;
import core.user.jpanel.BookJPanel;
import other.Tool;

/**
 * 订单操作：退改管理类
 * @author 陌生人
 *
 */
public class Order {
	//设置一个单号，用于操作处理
	private static String usingOrderNum;	//保存正在使用的航班号
	private static String oldFlight;		//判断是否改签为同一个航班
	private static String oldTime;
	private static JButton alter;			//改签按钮
	
	//改签修改航班信息
	public static void updateOrder() {
		String orderNum = getUsingOrderNum();
		String sql = "UPDATE orders SET date = '"+Tool.getTextValue(BookJPanel.getJtf_time())+"',flight_num = '"
				+Tool.getTextValue(BookJPanel.getJtf_handle())+"' WHERE order_num = '"+orderNum+"';";
		try {
			new Database().setUpdate(sql);
		} catch (SQLException e) {
			System.out.println("改签修改航班信息失败！");
		}
			
	}
	
	//退票或者改签 参数：退票模式、改签模式
	public static void backTicket(String model) {
		//设置座位数加一
		String orderNum = getUsingOrderNum();
		String sql1 = "UPDATE flight f,orders o SET f.seat_count = (f.seat_count + 1)" + 
					  "WHERE" + 
					  "	f.flight_num = o.flight_num" + 
					  "	AND f.date = o.date" + 
					  "	AND f.start_city = o.start_city" +
					  "	AND f.start_airport = o.start_airport" +
					  "	AND f.reach_city = o.reach_city" +
					  "	AND f.reach_airport = o.reach_airport" +
					  "	AND o.order_num = '"+orderNum+"'";
		String sql2 = "Delete from orders where order_num='"+orderNum+"'";
		try {
			new Database().setUpdate(sql1);
			if(model.equals("退票模式")) {
				new Database().setDelete(sql2);
			}
		} catch (SQLException e) {
			System.out.println(model+"操作读取数据库失败！");
		}
	}
	
	//改签模式
	public static void alterModel() {
		//跳转查询界面
		String orderNum = getUsingOrderNum();
		BackAlterJPanel.getJsp_show().setVisible(false);
		BookJPanel.getJp_show().setVisible(true);
		//
		String sql = "SELECT" + 
				     "  flight.date, flight.start_city, flight.start_airport, flight.reach_city, flight.reach_airport, flight.flight_num " + 
					 "FROM" + 
					 "  flight,orders " + 
					 "WHERE" + 
					 "	flight.flight_num = orders.flight_num " + 
					 "	AND flight.start_city = orders.start_city" +
					 "	AND flight.start_airport = orders.start_airport" +
					 "	AND flight.reach_city = orders.reach_city" +
					 "	AND flight.reach_airport = orders.reach_airport" +
					 "	AND flight.date = orders.date " + 
					 "	AND orders.order_num = '"+orderNum+"'";
		try {
			ResultSet rs = new Database().getSelect(sql); rs.next();
			BookJPanel.getJtf_time().setText(rs.getString("date"));
			//设置并不可编辑
			BookJPanel.getJtf_startCity().setText(rs.getString("start_city"));
			BookJPanel.getJtf_startCity().setEditable(false);
			BookJPanel.getJtf_startAirport().setText(rs.getString("start_airport"));
			BookJPanel.getJtf_startAirport().setEditable(false);
			BookJPanel.getJtf_reachCity().setText(rs.getString("reach_city"));
			BookJPanel.getJtf_reachCity().setEditable(false);
			BookJPanel.getJtf_reachAirport().setText(rs.getString("reach_airport"));
			BookJPanel.getJtf_reachAirport().setEditable(false);
			BookJPanel.getJtf_handle().setText(rs.getString("flight_num"));
			setOldFlight(rs.getString("flight_num"));
			setOldTime(rs.getString("date"));
			//修改数据库
			
		} catch (SQLException e) {
			System.out.println("改签查询数据库失败！");
		}
	}
	
	//设置回非改签模式
	public static void cancelAlter() {
		//查询界面设置回输入框界面，其他为false
		BookJPanel.getJp_inquiry().setVisible(true);
		BookJPanel.getJp_showTitle().setVisible(false);
		BookJPanel.getJp_showTable().setVisible(false);
		BookJPanel.getJp_showButtom().setVisible(false);
		BookJPanel.getJp_showPassenger().setVisible(false);
		BookJPanel.getJp_showPay().setVisible(false);
		
		BackAlterJPanel.getJsp_show().setVisible(true);
		BookJPanel.getJp_show().setVisible(false);
		BookJPanel.getJtf_startCity().setText("");
		BookJPanel.getJtf_startCity().setEditable(true);
		BookJPanel.getJtf_startAirport().setText("");
		BookJPanel.getJtf_startAirport().setEditable(true);
		BookJPanel.getJtf_reachCity().setText("");
		BookJPanel.getJtf_reachCity().setEditable(true);
		BookJPanel.getJtf_reachAirport().setText("");
		BookJPanel.getJtf_reachAirport().setEditable(true);
		BookJPanel.getJtf_time().setText("");
		BookJPanel.getJtf_handle().setText("");
		
	}
	public static JButton getAlter() {
		return alter;
	}

	public static void setAlter(JButton alter) {
		Order.alter = alter;
	}

	public static String getOldFlight() {
		return oldFlight;
	}

	public static void setOldFlight(String oldFlight) {
		Order.oldFlight = oldFlight;
	}

	public static String getUsingOrderNum() {
		return usingOrderNum;
	}

	public static void setUsingOrderNum(String usingOrderNum) {
		Order.usingOrderNum = usingOrderNum;
	}

	public static String getOldTime() {
		return oldTime;
	}

	public static void setOldTime(String oldTime) {
		Order.oldTime = oldTime;
	}

}
