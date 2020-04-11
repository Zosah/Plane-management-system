package core.user.jpanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomeJPanel {
private static JLabel jl_welcome;
private static JButton jb_quit;
	
	public static void init(JFrame jf, String name) {
		jl_welcome = new JLabel("欢迎你， "+name+" ！");	
		jl_welcome.setBounds(360, 540, 250, 50);
		jb_quit = new JButton("注销");
		jb_quit.setBounds(760, 560, 60, 30);
		jb_quit.setBackground(new Color(187,206,219));
		jb_quit.setOpaque(false);
		jf.add(jl_welcome);
		jf.add(jb_quit); 
	}

	public static JButton getJb_quit() {
		return jb_quit;
	}
	
}
