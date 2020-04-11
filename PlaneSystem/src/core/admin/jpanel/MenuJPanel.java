package core.admin.jpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import other.AddComonents;

/**
 * -管理员登录界面的菜单面板，创建按钮，提供get方法。
 * @author 陌生人
 *
 */

public class MenuJPanel {
	//左边菜单面板+按钮
	private static JPanel jp_menu;	//一级
	private static JButton jb_inquiry;
	private static JButton jb_input;
	private static JButton jb_statistics;
	
	
	public static void init(JFrame jf) {
		jp_menu = AddComonents.addJPanel(jf, 0, 78, 150, 445); jp_menu.setBackground(new Color(203,236,234));
		jp_menu.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		jb_inquiry = AddComonents.addJButton("综合查询", jp_menu, 2, 2, 146, 50, false, true, false);
		jb_input = AddComonents.addJButton("航班录入", jp_menu, 2, 52, 146, 50, false, true, false);
		jb_statistics = AddComonents.addJButton("统计报表", jp_menu, 2, 102, 146, 50, false, true, false);
	}
	
	public static JPanel getJp_menu() {
		return jp_menu;
	}

	public static JButton getJb_inquiry() {
		return jb_inquiry;
	}

	public static JButton getJb_input() {
		return jb_input;
	}

	public static JButton getJb_statistics() {
		return jb_statistics;
	}
}
