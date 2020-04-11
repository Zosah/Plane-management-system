package core.first.register;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.database.Database;
import core.first.jpanel.LoginJPanel;
import core.first.jpanel.RegisterJPanel;
import other.Tool;

/**
 * -开始信息处理类
 * @author 陌生人
 *
 */
public class Register {
	
	private JFrame jf = null;
	private JTextField jtf_re_username = RegisterJPanel.getJtf_re_username();
	private JPasswordField jpf_re_password1 = RegisterJPanel.getJtf_re_password1();
	private JPasswordField jpf_re_password2 = RegisterJPanel.getJtf_re_password2();
	private JTextField jtf_re_name = RegisterJPanel.getJtf_re_name();
	private JTextField jtf_re_id = RegisterJPanel.getJtf_re_id();
	private JTextField jtf_re_phone = RegisterJPanel.getJtf_re_phone();
	private UserBank ub; 
	
	public Register(JFrame jf) throws ClassNotFoundException, SQLException {
		super();
		this.jf = jf;
		getContent();
		check();
	}
	
	
	//获得组件的内容
	public void getContent() throws ClassNotFoundException, SQLException {
		ub = new UserBank(
			Tool.getTextValue(jtf_re_username),
			Tool.getTextValue(jpf_re_password1),
			Tool.getTextValue(jpf_re_password2),
			Tool.getTextValue(jtf_re_name),
			Tool.getTextValue(jtf_re_id),
			Tool.getTextValue(jtf_re_phone)
		);
	}
	
	//检查字段和检查数据库是否存在相同用户名，都通过，则写入数据库
	public void check() throws SQLException, ClassNotFoundException {
		
		//第一次检查
		if(Check.checkAll(jf, ub)) {
			//通过
			Database db = new Database();
			ResultSet rs = db.getSelect("select * from user");
			
			boolean secondcheck = true;		//第二次检查标记
			while(rs.next()) {
				//如果已经存在账号，则不插入数据库
				if(ub.getUsername().equals(rs.getString("username"))) {
					JOptionPane.showMessageDialog(jf, "该账号已注册，请直接登录！");
					rs.last();  //操作完毕立即关掉
					secondcheck = false;
					break;
				}
			}
			//第二次检查通过，说明可注册，则插入！
			if(secondcheck) {
				db.setUpdate("INSERT INTO USER (username,password,name,id,phone) VALUES "
						+ "('" + ub.getUsername() + "','" + ub.getPassword1() + "','"+ ub.getName() + "','"+ ub.getId() +"','"+ub.getPhone()+"');" );
				JOptionPane.showMessageDialog(jf, "恭喜你，注册成功！");
				//注册成功，跳转登录界面
				LoginJPanel.getJp_login().setVisible(true);
				RegisterJPanel.getJp_register().setVisible(false);
				
				//重置文本框
				RegisterJPanel.resetAll();
			}
		}
	}
}
