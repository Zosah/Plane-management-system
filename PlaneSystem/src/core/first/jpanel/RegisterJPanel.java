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
 * -开始界面的注册面板
 * -提供文本框组件的获取方法
 * -提供文本框组件的设置焦点方法
 * -提供文本框组件的内容清空方法
 * @author 陌生人
 *
 */
public class RegisterJPanel {
	
	private static JPanel jp_register;
	
	private static JLabel jl_introduce;
	private static JLabel jl_re_username;
	private static JLabel jl_re_password1;
	private static JLabel jl_re_password2;
	private static JLabel jl_re_name;
	private static JLabel jl_re_id;
	private static JLabel jl_re_phone;
	private static JTextField jtf_re_username;
	private static JPasswordField jtf_re_password1;
	private static JPasswordField jtf_re_password2;
	private static JTextField jtf_re_name;
	private static JTextField jtf_re_id;
	private static JTextField jtf_re_phone;
	private static JButton jb_re_cancelRegister;
	private static JButton jb_re_register;

	
	//设置清空所有
	public static void resetAll() {
		jtf_re_username.setText("");
		jtf_re_password1.setText("");
		jtf_re_password2.setText("");
		jtf_re_name.setText("");
		jtf_re_id.setText("");
	}
	
	public static JPanel init(JFrame jf) {
		jp_register = AddComonents.addJPanel(jf, 225, 100, 400, 300);
		jp_register.setVisible(false);
		jp_register.setBorder(BorderFactory.createLineBorder(Color.white, 4));
		
		jl_introduce = AddComonents.addJLabel("用户注册", jp_register, 170, 2, 150, 30);
		jl_re_username = AddComonents.addJLabel("用户名：", jp_register, 50, 40, 60, 25);
		jtf_re_username = AddComonents.addJTextField(jp_register, 130, 40, 200, 25);
		
		jl_re_password1 = AddComonents.addJLabel("密　码：", jp_register, 50, 70, 90, 25);
		jtf_re_password1 = AddComonents.addJPasswordField(jp_register, 130, 70, 200, 25);
		
		jl_re_password2 = AddComonents.addJLabel("确认密码：", jp_register, 50, 100, 80, 25);
		jtf_re_password2 = AddComonents.addJPasswordField(jp_register, 130, 100, 200, 25);
		
		jl_re_name = AddComonents.addJLabel("真实姓名：", jp_register, 50, 130, 80, 25);
		jtf_re_name = AddComonents.addJTextField(jp_register, 130, 130, 200, 25);
		
		jl_re_id = AddComonents.addJLabel("身份证号：", jp_register, 50, 160, 80, 25);
		jtf_re_id = AddComonents.addJTextField(jp_register, 130, 160, 200, 25);
		
		jl_re_phone = AddComonents.addJLabel("手机号：", jp_register, 50, 190, 90, 25);
		jtf_re_phone = AddComonents.addJTextField(jp_register, 130, 190, 200, 25);
		
		jb_re_cancelRegister = AddComonents.addJButton("取消", jp_register, 75, 240, 100, 40, false, true, false);
		jb_re_register = AddComonents.addJButton("注册", jp_register, 230, 240, 100, 40, false, true, false);
		
		return jp_register;
	}
	
	//获得对应的组件-按钮
	public static JPanel getJp_register() {
		return jp_register;
	}
	
	public static JButton getJb_re_cancelRegister() {
		return jb_re_cancelRegister;
	}
	
	public static JButton getJb_re_register() {
		return jb_re_register;
	}
	
	//获得对应的组件-文本框
	public static JTextField getJtf_re_username() {
		return jtf_re_username;
	}
	
	public static JPasswordField getJtf_re_password1() {
		return jtf_re_password1;
	}
	
	public static JPasswordField getJtf_re_password2() {
		return jtf_re_password2;
	}
	
	public static JTextField getJtf_re_name() {
		return jtf_re_name;
	}
	
	public static JTextField getJtf_re_id() {
		return jtf_re_id;
	}
	
	public static JTextField getJtf_re_phone() {
		return jtf_re_phone;
	}

	//设置焦点
	public static void setJtf_re_usernameFocus() {
		jtf_re_username.requestFocus();
	}
	public static void setJtf_re_password1Focus() {
		jtf_re_password1.requestFocus();
	}
	public static void setJtf_re_password2Focus() {
		jtf_re_password2.requestFocus();
	}
	public static void setJtf_re_nameFocus() {
		jtf_re_name.requestFocus();
	}
	public static void setJtf_re_idFocus() {
		jtf_re_id.requestFocus();
	}
	public static void setJtf_re_phoneFocus() {
		jtf_re_phone.requestFocus();
	}
}
