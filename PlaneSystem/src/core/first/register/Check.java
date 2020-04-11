package core.first.register;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import core.first.jpanel.RegisterJPanel;


/**
 * - 检查输入的文本内容类
 * @author 陌生人
 *
 */
public class Check {
	
	public static boolean checkAll(JFrame jf, UserBank ub) {
		//检查用户名
		if(!ub.getUsername().matches("[a-zA-Z]\\w{5}")) {
			JOptionPane.showMessageDialog(jf, "用户名必须为英文字母开头，共六个字符，请重新输入！");	
			RegisterJPanel.setJtf_re_usernameFocus();
			return false;
			
		}else if(!ub.getPassword1().matches("\\w{6,12}")) {
			JOptionPane.showMessageDialog(jf, "密码必须为6-12位字符，请重新输入！");
			RegisterJPanel.setJtf_re_password1Focus();
			return false;
			
		}else if(!ub.getPassword1().equals(ub.getPassword2())) {
			JOptionPane.showMessageDialog(jf, "两次输入的密码不匹配，请重新输入！");
			RegisterJPanel.setJtf_re_password2Focus();
			return false;
			
		}else if(ub.getName().equals("")) {
			JOptionPane.showMessageDialog(jf, "真实姓名不能为空，请重新输入！");
			RegisterJPanel.setJtf_re_nameFocus();
			return false;
			
		}else if(ub.getId().equals("")) {
			JOptionPane.showMessageDialog(jf, "身份证号不能为空，请重新输入！");
			RegisterJPanel.setJtf_re_idFocus();
			return false;
		}else if(!ub.getId().matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")){
			JOptionPane.showMessageDialog(jf, "身份证号格式不正确，请重新输入！");
			RegisterJPanel.setJtf_re_idFocus();
			return false;
			
		}else if(ub.getPhone().equals("")) {
			JOptionPane.showMessageDialog(jf, "手机号不能为空，请重新输入！");
			RegisterJPanel.setJtf_re_phoneFocus();
			return false;
		}else if(!ub.getPhone().matches("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")) {
			JOptionPane.showMessageDialog(jf, "手机号格式不正确，请重新输入！");
			RegisterJPanel.setJtf_re_phoneFocus();
			return false;
		}
		else {
			//均通过，返回true
			return true;
		}
	}
}

