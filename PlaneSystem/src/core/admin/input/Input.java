package core.admin.input;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.admin.jpanel.InputJPanel;
import core.database.Database;
import other.Tool;

/**
 * 录入类
 * 
 * @author 陌生人
 *
 */

public class Input {
	private static JTextField jtf_date = InputJPanel.getJtf_date();
	private static JTextField jtf_flight = InputJPanel.getJtf_flight();
	private static JTextField jtf_startCity = InputJPanel.getJtf_startCity();
	private static JTextField jtf_startAirport = InputJPanel.getJtf_startAirport();
	private static JTextField jtf_startTime = InputJPanel.getJtf_startTime();
	private static JTextField jtf_reachCity = InputJPanel.getJtf_reachCity();
	private static JTextField jtf_reachAirport = InputJPanel.getJtf_reachAirport();
	private static JTextField jtf_reachTime = InputJPanel.getJtf_reachTime();
	private static JTextField jtf_seatCount = InputJPanel.getJtf_seatCount();
	private static JTextField jtf_price = InputJPanel.getJtf_price();
	private static JTextField jtf_usedTime = InputJPanel.getJtf_usedTime();
	
	private static String date;
	private static String flight;
	private static String startCity;
	private static String startAirport;
	private static String startTime;
	private static String reachCity;
	private static String reachAirport;
	private static String reachTime;
	private static String seatCount;
	private static String price;
	private static String usedTime;
	private static JFrame jf;
	
	public Input(JFrame jf) {
		super();
		this.jf = jf;
		getText();
	}
	
	public static void getText() {
		date = Tool.getTextValue(jtf_date);;
		flight = Tool.getTextValue(jtf_flight);;
		startCity = Tool.getTextValue(jtf_startCity);
		startAirport = Tool.getTextValue(jtf_startAirport);
		startTime = Tool.getTextValue(jtf_startTime);
		reachCity = Tool.getTextValue(jtf_reachCity);
		reachAirport = Tool.getTextValue(jtf_reachAirport);
		reachTime = Tool.getTextValue(jtf_reachTime);
		seatCount = Tool.getTextValue(jtf_seatCount);
		price = Tool.getTextValue(jtf_price);
		usedTime = Tool.getTextValue(jtf_usedTime);
	}
	
	public static void submit() throws ClassNotFoundException, SQLException {
		String sql = "insert into flight values('"+date+"','"+flight+"','"
				+startCity+"','"+startAirport+"','"+startTime+"','"
				+reachCity+"','"+reachAirport+"','"+reachTime+"','"
				+seatCount+"','"+price+"','"+usedTime+"');";
		new Database().setInsert(sql);
	}
	
	//对录入的字符做判断
	public static boolean cheak() {
		String reg1 = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		String reg2 = "^([0-1]\\d|2[0-3]):[0-5]\\d$";
		if(!date.matches(reg1)) {
			JOptionPane.showMessageDialog(jf, "录入的日期格式不正确！");return false;
		}else if(flight.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入航班号！");return false;
		}else if(startCity.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入航出发城市！");return false;
		}else if(startAirport.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入出发机场！");return false;
		}else if(startTime.equals("")){
			JOptionPane.showMessageDialog(jf, "请录入起飞时间！");return false;
		}else if(!startTime.matches(reg2)) {
			JOptionPane.showMessageDialog(jf, "起飞时间格式不正确，格式用例为：16:30！");return false;
		}else if(reachCity.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入航到达城市！");return false;
		}else if(reachAirport.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入到达机场！");return false;
		}else if(reachTime.equals("")){
			JOptionPane.showMessageDialog(jf, "请录入降落时间！");return false;
		}else if(!reachTime.matches(reg2)) {
			JOptionPane.showMessageDialog(jf, "降落时间格式不正确，格式用例为：16:30！");return false;
		}else if(seatCount.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入座位数！");return false;
		}else if(!seatCount.matches("^\\d+$")) {
			JOptionPane.showMessageDialog(jf, "座位数格式不正确，必须为大于或等于0的数！");return false;
		}else if(price.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入价格！");return false;
		}else if(!price.matches("^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$")) {
			JOptionPane.showMessageDialog(jf, "价格数格式不正确，必须为大于零的数！");return false;
		}else if(usedTime.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入用时，单位为分钟！");return false;
		}else if(!usedTime.matches("^\\d+$")) {
			JOptionPane.showMessageDialog(jf, "用时格式不正确，格式用例：120");return false;
		}else {
			return true;
		}
	}
}
