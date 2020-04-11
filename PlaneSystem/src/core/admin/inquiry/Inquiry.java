package core.admin.inquiry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.admin.alter.Alter;
import core.admin.jpanel.InquiryJPanel;
import core.database.Database;
import other.Tool;

/**
 * 查询航班
 * 
 * @author 陌生人
 *
 */

public class Inquiry {
	
	private static JTextField jtf_startCity = InquiryJPanel.getJtf_startCity();
	private static JTextField jtf_startAirport = InquiryJPanel.getJtf_startAirport();
	private static JTextField jtf_reachCity = InquiryJPanel.getJtf_reachCity();
	private static JTextField jtf_reachAirport = InquiryJPanel.getJtf_reachAirport();
	private static JTextField jtf_time = InquiryJPanel.getJtf_time();
	private static JTextField jtf_handle = InquiryJPanel.getJtf_handle();
	
	private static String startCity;
	private static String startAirport;
	private static String reachCity;
	private static String reachAirport;
	private static String time;
	private static String handle;

	public static void getText() {
		startCity = Tool.getTextValue(jtf_startCity);
		startAirport = Tool.getTextValue(jtf_startAirport);
		reachCity = Tool.getTextValue(jtf_reachCity);
		reachAirport = Tool.getTextValue(jtf_reachAirport);
		time = Tool.getTextValue(jtf_time);
		handle = Tool.getTextValue(jtf_handle);
		handle = Tool.getTextValue(jtf_handle);
//		startCity = "广州";
//		startAirport = "白云机场";
//		reachCity = "上海";
//		reachAirport = "虹桥机场";
//		time = "2019-10-22";
	}
	
	//删除、修改处理
	public static void handle(JFrame jf) {
		if(InquiryJPanel.getJb_handle().getText().equals("删除航班")) {
			int n = JOptionPane.showConfirmDialog(null, "确定要删除该航班吗?", "删除提示：", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				int row = delete(jf);
				if(row == -1) {
					JOptionPane.showMessageDialog(jf, "删除失败，请输入航班号！");
				}else if(row == 0) {
					JOptionPane.showMessageDialog(jf, "删除失败，"+time+"不存在航班"+handle);
				}else {
					JOptionPane.showMessageDialog(jf, "删除成功！");
					jtf_handle.setText("");
					jtf_handle.requestFocus();
				}
			}
			
		}else {
			//修改航班
			if(Alter.checkTableInfo(jf)) {
				int n = JOptionPane.showConfirmDialog(null, "确定要修改航班吗?", "修改提示：", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					int count = Alter.alter();
					if(count == -1) {
						JOptionPane.showMessageDialog(jf, "当前日期已存在该航班，修改失败！");
					}else if(count == 0){
						JOptionPane.showMessageDialog(jf, "无数据改动！");
					}else {
						JOptionPane.showMessageDialog(jf, "修改成功，改动航班数为："+count);
					}
				}
			}
		}
	}
	
	
	//删除航班
	public static int delete(JFrame jf) {
		
		getText();
		int row = -1;	//-1为空，0为删除失败，1为成功
		if(handle.equals("")) {
			row = -1;
		}else {
			try {
				row = new Database().setDelete("delete from flight where flight_num='"+handle+"' and date='"+time+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	
	//开始匹配
	public static Object[][] submit() throws ClassNotFoundException, SQLException {
		getText();
		InquiryJPanel.getJl_showInfo().setText("基本信息："+startCity+"("+startAirport+") → "+reachCity+"("+reachAirport+")");
		ResultSet rs = new Database().getSelect("select * from flight;");
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
		String reg1 = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|"
				+ "(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|"
				+ "(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|"
				+ "(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|"
				+ "((16|[2468][048]|[3579][26])00))-0?2-29-))$";
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
