package core.admin.jpanel;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import other.AddComonents;
import other.Tool;

/**
 * -航班查询面板
 * @author 陌生人
 *
 */

public class InquiryJPanel {
	

	private static JPanel jp_show;	//一级面板
	
	private static JLabel jl_head;
	private static JLabel jl_line1;
	private static JLabel jl_line2;
	
	private static JPanel jp_inquiry;		//二级
	private static JLabel jl_startCity ;
	public static JTextField jtf_startCity;
	private static JLabel jl_startAirport;
	private static JTextField jtf_startAirport;
	private static JLabel jl_reachCity;
	public static JTextField jtf_reachCity;
	private static JLabel jl_reachAirport;
	private static JTextField jtf_reachAirport;
	private static JLabel jl_time;
	private static JTextField jtf_time;
	private static JButton jb_inquiryAirport1;
	private static JButton jb_inquiryAirport2;
	private static JLabel jl_tips;
	private static JButton jb_inquiry;
	private static JButton jb_reflush;
	private static JButton jb_reback;
	private static JButton jb_cancel;
	
	private static JPanel jp_showTitle;
	private static JLabel jl_showInfo;
	private static JPanel jp_showTable;		//二级
	private static JTable table;
	
	
	private static JPanel jp_showButtom;	//二级
	private static JLabel jl_handle;
	private static JTextField jtf_handle;
	private static JRadioButton jrb_delete;
	private static JRadioButton jrb_alter;
	private static JButton jb_handle;
	
	private static Color c = new Color(205,235,234);

	private static JButton jb_today;


	public static void init(JFrame jf) {
		jp_show = AddComonents.addJPanel(jf, 150, 78, 693, 445); /* jp_show.setBackground(c); */
		jp_show.setBorder(BorderFactory.createLineBorder(Color.black, 1));jp_show.setVisible(false);
		//添加查询面板的组件
		jp_inquiry = AddComonents.addJPanel_2(jp_show, 2, 2, 688, 440); jp_inquiry.setBackground(c); jp_inquiry.setVisible(true);
		jl_head = AddComonents.addJLabel("航班查询", jp_inquiry, 300, 10, 100, 30);
		jl_line1 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_inquiry, 0, 30, 800, 30);
		jl_startCity = AddComonents.addJLabel("出发城市：", jp_inquiry, 25, 80, 100, 30);
		jtf_startCity = AddComonents.addJTextField(jp_inquiry, 95, 80, 150, 30);
		jl_startAirport = AddComonents.addJLabel("出发机场：", jp_inquiry, 310, 80, 100, 30);
		jtf_startAirport = AddComonents.addJTextField(jp_inquiry, 380, 80, 150, 30);
		jb_inquiryAirport1 = AddComonents.addJButton("查询机场", jp_inquiry, 550, 80, 90, 30, true, true, false);
		jl_reachCity = AddComonents.addJLabel("到达城市：", jp_inquiry, 25, 130, 100, 30);
		jtf_reachCity = AddComonents.addJTextField(jp_inquiry, 95, 130, 150, 30);
		jl_reachAirport = AddComonents.addJLabel("到达机场：", jp_inquiry, 310, 130, 100, 30);
		jtf_reachAirport = AddComonents.addJTextField(jp_inquiry, 380, 130, 150, 30);
		jb_inquiryAirport2 = AddComonents.addJButton("查询机场", jp_inquiry, 550, 130, 90, 30, true, true, false);
		jl_time = AddComonents.addJLabel("出发日期：", jp_inquiry, 25, 180, 100, 30);
		jtf_time = AddComonents.addJTextField(jp_inquiry, 95, 180, 150, 30);
		jl_tips = AddComonents.addJLabel("(格式：2019-10-22)", jp_inquiry, 110, 210, 150, 30);
		jb_today = AddComonents.addJButton("查询今天", jp_inquiry, 260, 180, 90, 30, true, true, false);
		jl_line2 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_inquiry, 0, 315, 800, 30);
		
		jb_inquiry = AddComonents.addJButton("查询航班", jp_inquiry, 275, 365, 120, 50, false, true, false);
		jb_cancel = AddComonents.addJButton("取消", jp_inquiry, 610, 390, 60, 30, true, true, false);
		
		//表格界面-头面板
		jp_showTitle = AddComonents.addJPanel_2(jp_show, 1, 1, 691, 54);jp_showTitle.setVisible(false);
		jl_showInfo = AddComonents.addJLabel("null", jp_showTitle, 15, 4, 500, 50);
		jb_reflush = AddComonents.addJButton("刷新", jp_showTitle, 545, 11, 60, 30, true, true, false);
		jb_reback = AddComonents.addJButton("返回", jp_showTitle, 620, 11, 60, 30, true, true, false);
		//表格界面-表格面板
		jp_showTable = AddComonents.addJPanel_2(jp_show, 1, 55, 691, 300);jp_showTable.setVisible(false);
		//表格界面-按钮面板
		jp_showButtom = AddComonents.addJPanel_2(jp_show, 2, 355, 689, 88);jp_showButtom.setVisible(false);
		jl_handle = AddComonents.addJLabel("请输入需要操作的航班号：", jp_showButtom, 60, 25, 200, 30);
		jtf_handle = AddComonents.addJTextField(jp_showButtom, 220, 27, 150, 30);
		jrb_delete = AddComonents.addJRadioButton("删除", jp_showButtom, 390, 27, 60, 30); jrb_delete.setSelected(true);
		jrb_alter = AddComonents.addJRadioButton("修改", jp_showButtom, 450, 27, 60, 30);
		jb_handle = AddComonents.addJButton("删除航班", jp_showButtom, 520, 20, 100, 40, false, true, false);
	}
	
	public static void addTable(Object[][] arr) {
		jp_showTable.setVisible(false);
		jp_showTable = AddComonents.addJPanel_2(jp_show, 1, 55, 691, 300);	//把原面板影藏，重新新建并赋值
		Object[] title = {"日期","航班号","起飞时间","降落时间","用时(分钟)","座位数","票价(元)"};	//表头
		table = AddComonents.addJTable(title, arr, jp_showTable, false);
		
		//添加表格获取行数响应、然后获得当前的航班号
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				jtf_handle.setText(String.valueOf(arr[table.getSelectedRow()][1]));
			}
		});
	}
	
	
	public static JTable getTable() {
		return table;
	}

	public static JPanel getJp_show() {
		return jp_show;
	}

	public static JPanel getJp_inquiry() {
		return jp_inquiry;
	}

	public static JTextField getJtf_startCity() {
		return jtf_startCity;
	}

	public static JTextField getJtf_startAirport() {
		return jtf_startAirport;
	}

	public static JTextField getJtf_reachCity() {
		return jtf_reachCity;
	}

	public static JTextField getJtf_reachAirport() {
		return jtf_reachAirport;
	}

	public static JTextField getJtf_time() {
		return jtf_time;
	}

	public static JButton getJb_inquiryAirport1() {
		return jb_inquiryAirport1;
	}

	public static JButton getJb_inquiryAirport2() {
		return jb_inquiryAirport2;
	}

	public static JButton getJb_inquiry() {
		return jb_inquiry;
	}

	public static JButton getJb_reback() {
		return jb_reback;
	}

	public static JPanel getJp_showTitle() {
		return jp_showTitle;
	}

	public static JLabel getJl_showInfo() {
		return jl_showInfo;
	}

	public static JPanel getJp_showTable() {
		return jp_showTable;
	}

	public static JButton getJb_cancel() {
		return jb_cancel;
	}

	public static JLabel getJl_head() {
		return jl_head;
	}

	public static JPanel getJp_showButtom() {
		return jp_showButtom;
	}

	public static JTextField getJtf_handle() {
		return jtf_handle;
	}

	public static JRadioButton getJrb_delete() {
		return jrb_delete;
	}

	public static JRadioButton getJrb_alter() {
		return jrb_alter;
	}

	public static JButton getJb_handle() {
		return jb_handle;
	}

	public static JButton getJb_reflush() {
		return jb_reflush;
	}

	public static JButton getJb_today() {
		return jb_today;
	}
	
}
