package core.user.jpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import other.AddComonents;
import other.Global;
import other.Tool;


//退改界面

public class BackAlterJPanel {
	private static Color c = new Color(205,235,234);
	private static JPanel jp_show;
	private static JScrollPane jsp_show;
	private static JLabel jl_test;
	private static JPanel jp_test;
	private static JLabel jl_head;
	private static JLabel jl_line1;
	private static JButton jb_cancel;
	private static JButton jb_reflush;
	private static JLabel jl_tips;
	
	public static void init(JFrame jf, String username) {
		//基层面板，用于存放订单面板
		jp_show = new JPanel();
		jp_show.setBackground(c);
		jp_show.setPreferredSize(new Dimension(693,2500));	//设置一定大小和长度
		jp_show.setLayout(null);		//设置空布局则可以改变坐标
		
		jl_head = AddComonents.addJLabel("退改管理", jp_show, 300, 10, 100, 30);
		jb_reflush = AddComonents.addJButton("刷新", jp_show, 530, 11, 60, 30, true, true, false);
		jl_line1 = AddComonents.addJLabel("__________________________________________________________________________________________________________________", jp_show, 0, 30, 800, 30);
		jb_cancel = AddComonents.addJButton("取消", jp_show, 600, 11, 60, 30, true, true, false);
		
		jl_tips = AddComonents.addJLabel("（暂无订单）", jp_show, 290, 150, 100, 30);
		
		//获得订单列表
		Global.setOrderCount(OrderJPanel.getOrderList(jp_show, username));
		
		if(Global.getOrderCount() != 0) {
			jl_tips.setVisible(false);
		}else {
			jl_tips.setVisible(true);
		}
		
		//底层框架面板，用于显示滚动条
		jsp_show = new JScrollPane(jp_show,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp_show.setBounds(150, 78, 693, 445);
		jf.add(jsp_show);
		jsp_show.setVisible(false);
	}

	
	public static JLabel getJl_tips() {
		return jl_tips;
	}
	public static JScrollPane getJsp_show() {
		return jsp_show;
	}
	public static JButton getJb_cancel() {
		return jb_cancel;
	}
	public static JButton getJb_reflush() {
		return jb_reflush;
	}
	public static JPanel getJp_show() {
		return jp_show;
	}
	
	
}
