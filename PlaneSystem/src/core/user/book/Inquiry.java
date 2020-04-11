package core.user.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.admin.jpanel.InquiryJPanel;
import core.database.Database;
import core.user.jpanel.BookJPanel;
import other.Tool;


/**
 * 查询预订请求类
 * 
 * @author 陌生人
 *
 */
public class Inquiry {
	private static JTextField jtf_startCity = BookJPanel.getJtf_startCity();
	private static JTextField jtf_startAirport = BookJPanel.getJtf_startAirport();
	private static JTextField jtf_reachCity = BookJPanel.getJtf_reachCity();
	private static JTextField jtf_reachAirport = BookJPanel.getJtf_reachAirport();
	private static JTextField jtf_time = BookJPanel.getJtf_time();
	
	private static String startCity;
	private static String startAirport;
	private static String reachCity;
	private static String reachAirport;
	private static String time;

	public static void getText() {
		startCity = Tool.getTextValue(jtf_startCity);
		startAirport = Tool.getTextValue(jtf_startAirport);
		reachCity = Tool.getTextValue(jtf_reachCity);
		reachAirport = Tool.getTextValue(jtf_reachAirport);
		time = Tool.getTextValue(jtf_time);
//		startCity = "广州";
//		startAirport = "白云机场";
//		reachCity = "上海";
//		reachAirport = "虹桥机场";
//		time = "2019-10-22";
	}
	
	//开始匹配
	public static Object[][] submit() throws ClassNotFoundException, SQLException {
		getText();
		BookJPanel.getJl_showInfo().setText("基本信息："+startCity+"("+startAirport+") → "+reachCity+"("+reachAirport+")");
		ResultSet rs = new Database().getSelect("select * from flight where start_city='"+startCity+"' and start_airport='"+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"';");
		String[] temp = new String[1024];	//定义一个临时数组
		Object[][] arr = null;				//定义一个二维数组，用于返回
		int arrlength = 0;
		while(rs.next()) {
			//匹配数据库,开始提取数据库内容
			if(startCity.equals(rs.getString("start_city")) && startAirport.equals(rs.getString("start_airport"))
					&& reachCity.equals(rs.getString("reach_city")) && reachAirport.equals(rs.getString("reach_airport"))
					&& time.equals(rs.getString("date"))) {
				temp[arrlength++] = time+"#"+rs.getString("flight_num")+"#"+rs.getString("start_time")+"#"+rs.getString("reach_time")+"#"
						+rs.getString("used_time")+"#"+rs.getString("seat_count")+"#"+rs.getString("price");
			}
		}
		if(arrlength == 0) {
			arr = new Object[1][7];
		}else {
			arr = new Object[arrlength][7];
		}
		
		//提取完毕，封装成一个二维数组返回
		if(arrlength == 0) {
			System.out.println("暂无航班");
			arr[0][0] = "暂无航班";	//如果没有航班则设定一个这样的标志
		}else {
			for(int i = 0; i<arrlength; i++) {
				String[] s = temp[i].split("#");
				for(int j = 0; j<7; j++) {
					arr[i][j] = s[j];
				}
			}
		}
		rs.last();  //操作完毕立即关掉
		return arr;
	}
	
	//检查格式
	public static boolean check(JFrame jf) {
		getText();
		String reg1 = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		if(startCity.equals("")) {
			JOptionPane.showMessageDialog(jf, "请输入出发城市！");
			return false;
		}else if(reachCity.equals("")) {
			JOptionPane.showMessageDialog(jf, "请输入到达城市！");
			return false;
		}else if(startAirport.equals("")) {
			JOptionPane.showMessageDialog(jf, "请查询获取出发机场！");
			return false;
		}else if(reachAirport.equals("")) {
			JOptionPane.showMessageDialog(jf, "请查询获取到达机场！");
			return false;
		}else if(time.equals("")) {
			JOptionPane.showMessageDialog(jf, "输入出发时间！");
			return false;
		}else if(!time.matches(reg1)) {	//"^^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$"
			JOptionPane.showMessageDialog(jf, "输入的日期格式不正确！");
			return false;
		}else if(!Tool.Tomorrow(time)){
			JOptionPane.showMessageDialog(jf, "无法查询过去的航班！");
			return false;
		}else{
			return true;
		}
	}
	
	//根据城市，匹配数据库获得机场、返回list集合
	public static List<String> getAirport(String str) throws ClassNotFoundException, SQLException {
		getText();
		List<String> airportlist = new ArrayList<String>();
		ResultSet rs = new Database().getSelect("select * from flight;");
		while(rs.next()) {
			//匹配数据库
			if(str.equals(rs.getString("start_city"))) {
				if(!airportlist.contains(rs.getString("start_airport"))) {
					airportlist.add(rs.getString("start_airport"));
				}
			}
			if(str.equals(rs.getString("reach_city"))) {
				if(!airportlist.contains(rs.getString("reach_airport"))) {
					airportlist.add(rs.getString("reach_airport"));
				}
			}
		}
		rs.last();  //操作完毕立即关掉
		return airportlist;
	}
	
	public static String getStartCity() {
		getText();
		return startCity;
	}

	public static String getReachCity() {
		getText();
		return reachCity;
	}
}
