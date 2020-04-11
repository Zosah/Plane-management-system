package core.first.response;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.first.jpanel.BottomJPanel;
import core.first.jpanel.LoginJPanel;
import core.first.jpanel.RegisterJPanel;
import core.first.login.Login;
import core.first.register.Register;
import other.Global;

/**
 * 
 * - 按钮响应类
 * 
 * @author 陌生人
 *
 */
public class Response {

	private JFrame jf;
	
	public Response(JFrame jf) {
		super();
		this.jf = jf;
	}

	public void firstResponse() {
		
		JPanel jp_login = LoginJPanel.getJp_login();
		JPanel jp_register = RegisterJPanel.getJp_register();
		
		//底部用户登录按钮响应
		BottomJPanel.getJb_bo_userLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_login.setVisible(true);
				jp_register.setVisible(false);
			}
		});
		
		//底部用户注册响应
		BottomJPanel.getJb_bo_register().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_login.setVisible(false);
				jp_register.setVisible(true);
			}
		});
		
		//登录面板---取消登录按钮
		LoginJPanel.getJb_lo_cancelLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_login.setVisible(false);
			}
		});
		
		//注册面板---取消注册按钮
		RegisterJPanel.getJb_re_cancelRegister().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_register.setVisible(false);
			}
		});
		
		//注册面板---注册按钮
		RegisterJPanel.getJb_re_register().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Register(jf);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//登录面板--登录按钮
		LoginJPanel.getJb_lo_login().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Login(jf);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
