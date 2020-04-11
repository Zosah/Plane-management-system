package core.user.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.user.jpanel.WelcomeJPanel;
import core.user.response.Response;
import core.user.jpanel.BackAlterJPanel;
import core.user.jpanel.BookJPanel;
import core.user.jpanel.MenuJPanel;
import core.user.jpanel.PersonJPanel;
import other.Global;
import other.Ui;

/**
 *  用户窗口
 * 
 * @author 陌生人
 *
 */
public class UserFrame extends JFrame{

	public String username;
	public String name;
	public UserFrame(String username, String name) {
		super();
		this.username = username;
		this.name = name;
		initFrame();
		createComonents();
	}
	
	public void initFrame() {
		this.setSize(Global.WIDTH,Global.HEIGHT);
		this.setTitle("已登录");
		this.setResizable(false);
		Ui.setFrameImage(this, "plane.jpg");
		Ui.setFrameCenter(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);	
		this.setVisible(false);
		Global.setJf3(this);  //把这个窗体保存起来
	}
	
	public void createComonents() {
		WelcomeJPanel.init(this,name);
		MenuJPanel.init(this);
		BookJPanel.init(this);
		PersonJPanel.init(this,username);
		BackAlterJPanel.init(this,username);
		JPanel jp_cover = Ui.setImage(this, "cover2.jpg");
		
		Response r = new Response(this,username);
		r.userResponse();
		this.setVisible(true);
	}
}
