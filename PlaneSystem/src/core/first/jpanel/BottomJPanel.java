package core.first.jpanel;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import other.AddComonents;

/**
 * -开始界面的按钮面板
 * 
 * @author 陌生人
 *
 */
public class BottomJPanel {
	private static JPanel jp_bottom;
	private static JButton jb_bo_register;
	private static JButton jb_bo_userLogin;
	
	public static JPanel init(JFrame jf) {
		jp_bottom = AddComonents.addJPanel(jf, 0, 465, 850, 150);
		jb_bo_register = AddComonents.addJButton("用户注册", jp_bottom, 220, 40, 150, 60, false, true, false);
		jb_bo_userLogin = AddComonents.addJButton("用户登录", jp_bottom, 480, 40, 150, 60, false, true, false);
		return jp_bottom;
	}
	public static JPanel getJp_bottom() {
		return jp_bottom;
	}

	public static JButton getJb_bo_register() {
		return jb_bo_register;
	}

	public static JButton getJb_bo_userLogin() {
		return jb_bo_userLogin;
	}
}
