package core.first.frame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.first.jpanel.BottomJPanel;
import core.first.jpanel.LoginJPanel;
import core.first.jpanel.RegisterJPanel;
import core.first.response.Response;
import other.Global;
import other.Ui;


/**
 * -初始化界面类
 * 
 * @author 陌生人
 *
 */
public class FirstFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public FirstFrame() {
		super();
		initFrame();
		createComonents();
		setCover();
	}
	
	//初始化基本窗体
	public void initFrame() {
		this.setSize(Global.WIDTH,Global.HEIGHT);
		this.setTitle("飞机票订票系统");
		this.setResizable(false);
		Ui.setFrameImage(this, "plane.jpg");
		Ui.setFrameCenter(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);	
		this.setVisible(true);
		Global.setJf1(this);  //把这个窗体保存起来
	}
	
	//设置背景图
	public void setCover() {
		JPanel jp_cover = Ui.setImage(this, "cover.jpg");
	}
	
	//创建组件：登录/注册界面、按钮
	public void createComonents() {
		
		//存放组件，方便提取
		JPanel jp_login = LoginJPanel.init(this);		//登录面板
		JPanel jp_register = RegisterJPanel.init(this);	//注册面板
		JPanel jp_bottom = BottomJPanel.init(this);		//按钮面板
		
		Response r = new Response(this);
		r.firstResponse();
	}
	
}
