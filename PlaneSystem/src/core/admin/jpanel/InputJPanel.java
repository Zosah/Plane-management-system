package core.admin.jpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import other.AddComonents;

public class InputJPanel {
	
	private static JPanel jp_show; 	
	
	private static JLabel jl_tips;
	private static JLabel jl_line1;			private static JLabel jl_line2;
	private static JLabel jl_date;			private static JTextField jtf_date;
	private static JLabel jl_flight;		private static JTextField jtf_flight;
	private static JLabel jl_startCity;		private static JTextField jtf_startCity;
	private static JLabel jl_startAirport;	private static JTextField jtf_startAirport;
	private static JLabel jl_startTime;		private static JTextField jtf_startTime;
	private static JLabel jl_reachCity;		private static JTextField jtf_reachCity;
	private static JLabel jl_reachAirport;	private static JTextField jtf_reachAirport;
	private static JLabel jl_reachTime;		private static JTextField jtf_reachTime;
	private static JLabel jl_seatCount;		private static JTextField jtf_seatCount;
	private static JLabel jl_price;			private static JTextField jtf_price;
	private static JLabel jl_usedTime;		private static JTextField jtf_usedTime;

	private static JButton jb_cancel;
	private static JButton jb_input;
	
	private static Color c = new Color(205,235,234);
	private static int width = 120;
	private static int height = 30;
	
	public static void init(JFrame jf) {
		jp_show = AddComonents.addJPanel(jf, 150, 78, 693, 445); jp_show.setBackground(c);
		jp_show.setBorder(BorderFactory.createLineBorder(Color.black, 1));jp_show.setVisible(false);
		
		jl_tips = AddComonents.addJLabel("航班录入", jp_show, 300, 10, 100, 30);
		jl_line1 = AddComonents.addJLabel("__________________________________"
				+ "____________________________________"
				+ "____________________________________________", jp_show, 0, 30, 800, 30);
		
		jl_date = AddComonents.addJLabel("日期：", jp_show,   35, 70, 100, 30);
		jtf_date = AddComonents.addJTextField(jp_show,      90, 70, width, height);
		jl_flight = AddComonents.addJLabel("航班号：", jp_show, 255, 70, 100, 30);
		jtf_flight = AddComonents.addJTextField(jp_show,    320, 70, width, height);
	
		jl_startCity = AddComonents.addJLabel("出发城市：", jp_show, 20, 120, 150, 30);
		jtf_startCity = AddComonents.addJTextField(jp_show, 90, 120, width, height);
		jl_startAirport = AddComonents.addJLabel("出发机场：", jp_show, 250, 120, 150, 30);
		jtf_startAirport = AddComonents.addJTextField(jp_show, 320, 120, width, height);
		jl_startTime = AddComonents.addJLabel("起飞时间：", jp_show, 480, 120, 150, 30);
		jtf_startTime = AddComonents.addJTextField(jp_show, 550, 120, width, height);
	
		jl_reachCity = AddComonents.addJLabel("到达城市：", jp_show, 20, 170, 150, 30);
		jtf_reachCity = AddComonents.addJTextField(jp_show, 90, 170, width, height);
		jl_reachAirport = AddComonents.addJLabel("到达机场：", jp_show, 250, 170, 150, 30);
		jtf_reachAirport = AddComonents.addJTextField(jp_show, 320, 170, width, height);
		jl_reachTime = AddComonents.addJLabel("降落时间：", jp_show, 480, 170, 150, 30);
		jtf_reachTime = AddComonents.addJTextField(jp_show, 550, 170, width, height);
		
		jl_seatCount = AddComonents.addJLabel("座位数：", jp_show, 25, 220, 150, 30);
		jtf_seatCount = AddComonents.addJTextField(jp_show, 90, 220, width, height);
		jl_price = AddComonents.addJLabel("价格：", jp_show, 260, 220, 100, 30);
		jtf_price = AddComonents.addJTextField(jp_show, 320, 220, width, height);
		jl_usedTime = AddComonents.addJLabel("用时：", jp_show, 490, 220, 150, 30);
		jtf_usedTime = AddComonents.addJTextField(jp_show, 550, 220, width, height);
		jl_line2 = AddComonents.addJLabel("______________________________"
				+ "____________________________________"
				+ "________________________________________________", jp_show, 0, 315, 800, 30);

		
		
		jb_input = AddComonents.addJButton("确定录入", jp_show, 275, 365, 120, 50, false, true, false);
		jb_cancel = AddComonents.addJButton("取消", jp_show, 610, 390, 60, 30, true, true, false);
	}

	public static JPanel getJp_show() {
		return jp_show;
	}

	public static JTextField getJtf_date() {
		return jtf_date;
	}

	public static JTextField getJtf_flight() {
		return jtf_flight;
	}

	public static JTextField getJtf_startCity() {
		return jtf_startCity;
	}

	public static JTextField getJtf_startAirport() {
		return jtf_startAirport;
	}

	public static JTextField getJtf_startTime() {
		return jtf_startTime;
	}

	public static JTextField getJtf_reachCity() {
		return jtf_reachCity;
	}

	public static JTextField getJtf_reachAirport() {
		return jtf_reachAirport;
	}

	public static JTextField getJtf_reachTime() {
		return jtf_reachTime;
	}

	public static JTextField getJtf_seatCount() {
		return jtf_seatCount;
	}

	public static JTextField getJtf_price() {
		return jtf_price;
	}

	public static JTextField getJtf_usedTime() {
		return jtf_usedTime;
	}

	public static JButton getJb_cancel() {
		return jb_cancel;
	}

	public static JButton getJb_input() {
		return jb_input;
	}
	
	
}
