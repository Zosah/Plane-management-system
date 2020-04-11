package other;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * -用于刷新组件的类
 * -解决java组件刷新不出来
 * @author 陌生人
 *
 */
public class Reflush {
	
	public static void reflush(JPasswordField o) {
		o.setVisible(false);
		o.setVisible(true);
	}
	
	public static void reflush(JTextField o) {
		o.setVisible(false);
		o.setVisible(true);
	}
	
	public static void reflush(JPanel o) {
		o.setVisible(false);
		o.setVisible(true);
	}
	
	public static void reflush(JButton o) {
		o.setVisible(false);
		o.setVisible(true);
	}
	
	public static void reflush(JLabel o) {
		o.setVisible(false);
		o.setVisible(true);
	}
	
	public static void reflush(JRadioButton o) {
		o.setVisible(false);
		o.setVisible(true);
	}
}
