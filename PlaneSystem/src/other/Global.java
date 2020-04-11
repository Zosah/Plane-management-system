package other;


import javax.swing.JFrame;

//该类存放全局可用的一些方法、常量、变量

public class Global {
	public static final int WIDTH = 850; 
	public static final int HEIGHT = 650;
	public static final int SEAT_COUMT = 100;
	public static  String[] userArr = {"username","password","name","id","phone"};
	public static JFrame jf1 = null;				//初始化界面的窗体
	public static JFrame jf2 = null;
	public static JFrame jf3 = null;
	public static int orderCount = 0;
	public static boolean alter = false;
	
	
	public static boolean getAlter() {
		return alter;
	}
	public static void setAlter(boolean alter) {
		Global.alter = alter;
	}
	public static int getOrderCount() {
		return orderCount;
	}
	public static void setOrderCount(int orderCount) {
		Global.orderCount = orderCount;
	}
	public static JFrame getJf3() {
		return jf3;
	}
	public static void setJf3(JFrame jf3) {
		Global.jf3 = jf3;
	}

	public static JFrame getJf2() {
		return jf2;
	}

	public static void setJf2(JFrame jf2) {
		Global.jf2 = jf2;
	}

	public static JFrame getJf1() {
		return jf1;
	}

	public static void setJf1(JFrame jf1) {
		Global.jf1 = jf1;
	}

	
}
