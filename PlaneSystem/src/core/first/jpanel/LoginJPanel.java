package core.first.jpanel;

import java.awt.Color;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import other.AddComonents;
/**
 * -开始界面的登录面板
 * @author 陌生人
 *
 */
public class LoginJPanel {
	
	private static JPanel jp_login;
	
	private static JLabel jl_lo_username;
	private static JLabel jl_lo_password;
	private static JLabel jl_introduce;
	
	private static JTextField jtf_lo_username;
	private static JPasswordField jpf_lo_password;
	
	private static JButton jb_lo_cancelLogin;
	private static JButton jb_lo_login;

	public static JPanel init(JFrame jf) {
		jp_login = AddComonents.addJPanel(jf, 225, 140, 400, 240);
		jp_login.setVisible(false);
		jp_login.setBorder(BorderFactory.createLineBorder(Color.white, 4));
		
		jl_introduce = AddComonents.addJLabel("———用户登录———", jp_login, 130, 5, 150, 30);
		jl_lo_username = AddComonents.addJLabel("用户名：", jp_login, 50, 50, 60, 30);
		jtf_lo_username = AddComonents.addJTextField(jp_login, 130, 50, 200, 30);
		jl_lo_password = AddComonents.addJLabel("密　码：", jp_login, 50, 80, 60, 70);
		jpf_lo_password = AddComonents.addJPasswordField(jp_login, 130, 100, 200, 30);
		
		jb_lo_cancelLogin = AddComonents.addJButton("取消登录", jp_login, 80, 160, 100, 40, false, true, false);
		
		jb_lo_login = AddComonents.addJButton("马上登录", jp_login, 230, 160, 100, 40, false, true, false);
		
		return jp_login;
	}
	
	public static JPanel getJp_login() {
		return jp_login;
	}


	public static JTextField getJtf_lo_username() {
		return jtf_lo_username;
	}

	public static JPasswordField getJpf_lo_password() {
		return jpf_lo_password;
	}

	public static JButton getJb_lo_cancelLogin() {
		return jb_lo_cancelLogin;
	}


	public static JButton getJb_lo_login() {
		return jb_lo_login;
	}

}
