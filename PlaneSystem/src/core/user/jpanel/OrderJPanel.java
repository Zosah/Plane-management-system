package core.user.jpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.database.Database;
import core.user.book.Order;
import core.user.book.OrderBank;
import other.AddComonents;
import other.Global;
import other.Tool;

public class OrderJPanel {

	public static int height = 230;
	public static int sum = 0;
	static List<JPanel> list = new ArrayList<JPanel>();	//用来存放飞机票样式，如果刷新则清空重新放飞机票
	
	public static JPanel createTicket(JPanel jp_show, OrderBank ob, int sum) {
		JPanel jp_order = AddComonents.addJPanel_2(jp_show, 50, 80+(height*(sum-1)), 580, 200);Tool.setBorder(jp_order);
		//"Default", 1, 12
		JLabel jl0 = AddComonents.addJLabel("订单号：", jp_order, 10, 0, 60, 30);
		JLabel orderNum = AddComonents.addJLabel(ob.getOrderNum(), jp_order, 60, 0, 100, 30);
		JLabel startCity = AddComonents.addJLabel(ob.getStartCity(), jp_order, 110, 30, 100, 30);startCity.setFont(new Font("Default", 1, 32));
		JLabel reachCity = AddComonents.addJLabel(ob.getReachCity(), jp_order, 400, 30, 100, 30);reachCity.setFont(new Font("Default", 1, 32));
		JLabel startAirport = AddComonents.addJLabel("（"+ob.getStartAirport()+"）", jp_order, 105, 60, 100, 30);
		JLabel reachAirport = AddComonents.addJLabel("（"+ob.getReachAirport()+"）", jp_order, 395, 60, 100, 30);
		JLabel jl1 = AddComonents.addJLabel("—————————→", jp_order, 225, 40, 200, 30);
		JLabel flightNum = AddComonents.addJLabel(ob.getFlightNum(), jp_order, 245, 25, 100, 30);
		JLabel date = AddComonents.addJLabel(ob.getDate(), jp_order, 255, 55, 100, 30);
		JLabel startTime = AddComonents.addJLabel(ob.getStartTime()+" 起飞", jp_order, 115, 80, 100, 30);
		JLabel reachTime = AddComonents.addJLabel(ob.getReachTime()+" 降落", jp_order, 405, 80, 100, 30);
		JLabel usedTime = AddComonents.addJLabel("用时 "+ob.getUsedTime()+" 分钟", jp_order, 250, 80, 100, 30);
		JLabel jl2 = AddComonents.addJLabel("_______________________________________________________________________________________", jp_order, 0, 100, 600, 30);
		JLabel name = AddComonents.addJLabel(ob.getName(), jp_order, 180, 130, 150, 30);
		JLabel jl4 = AddComonents.addJLabel("手机号：", jp_order, 30, 130, 150, 30);
		JLabel phone = AddComonents.addJLabel(ob.getPhone(), jp_order, 85, 130, 150, 30);
		JLabel jl3 = AddComonents.addJLabel("身份证：", jp_order, 30, 155, 150, 30);
		JLabel identity = AddComonents.addJLabel(ob.getIdentity(), jp_order, 85, 155, 300, 30);
		JLabel price = AddComonents.addJLabel("￥ "+ob.getPrice()+" 元", jp_order, 250, 145, 150, 30);price.setFont(new Font("Default", 1, 18));
		JButton jb_back = AddComonents.addJButton("退票", jp_order, 380, 140, 80, 40, true, true, false);
		JButton jb_alter = AddComonents.addJButton("改签", jp_order, 480, 140, 80, 40, true, true, false);
		
		//退票操作
		jb_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定要退票吗?", "退票提示：", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					Order.setUsingOrderNum(ob.getOrderNum());
					Order.backTicket("退票模式");	//把单号传过来
					JOptionPane.showMessageDialog(Global.getJf3(), "退票成功，请重新刷新查看票单！");
				}
			}
		});
		
		//改签操作
		jb_alter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定要改签吗?", "改签提示：", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					Global.setAlter(true);
					Order.setUsingOrderNum(ob.getOrderNum());
					Order.alterModel();
				}else {
					JOptionPane.showMessageDialog(Global.getJf3(), "改签已取消！");
				}
			}
		});
		
		return jp_order;
	}
	
	//查询订单列表，返回数据
	public static int getOrderList(JPanel jp_show,String username) {
		String orderListSQL = "select * from orders where username = '"+username+"';";
		ResultSet rs1 = null;
		sum = 0;	//没跑一次，就从头位置开始创建面板
		try {
			rs1 = new Database().getSelect(orderListSQL);
			while(rs1.next()) {
				sum++;	//订单个数
				//获得数据封装到对象中
				OrderBank ob = new OrderBank();
				ob.setOrderNum(rs1.getString("order_num"));
				ob.setName(rs1.getString("name"));
				ob.setIdentity(rs1.getString("id"));
				ob.setPhone(rs1.getString("phone"));
				ob.setDate(rs1.getString("date"));
				ob.setFlightNum(rs1.getString("flight_num"));
				//表连接，获得航班全部信息
				String flightInfoSQL = "SELECT flight.* FROM flight,orders WHERE flight.flight_num = '"+rs1.getString("flight_num")
					+"' AND flight.date = '"+rs1.getString("date")+"' AND flight.start_city='"+rs1.getString("start_city")+"' "
					+" AND flight.start_airport = '"+rs1.getString("start_airport")+"' AND flight.reach_city='"+rs1.getString("reach_city")
					+"' AND flight.reach_airport = '"+rs1.getString("reach_airport")+"';";
				ResultSet rs2 = new Database().getSelect(flightInfoSQL);
				while(rs2.next()) {
					ob.setStartCity(rs2.getString("start_city"));
					ob.setStartAirport(rs2.getString("start_airport"));
					ob.setStartTime(rs2.getString("start_time"));
					ob.setReachCity(rs2.getString("reach_city"));
					ob.setReachAirport(rs2.getString("reach_airport"));
					ob.setReachTime(rs2.getString("reach_time"));
					ob.setUsedTime(rs2.getString("used_time"));
					ob.setPrice(rs2.getString("price"));
				}
				list.add(createTicket(jp_show,ob,sum));	//sum=0，从0开始创建面板
			}
			return sum;
		} catch (SQLException e) {
			System.out.println("获得飞机票订单失败！");
			return sum;
		} finally {
			if(rs1!=null) {
				try {
					rs1.last();
				} catch (SQLException e) {
					System.out.println("查询数据库订单关闭资源失败！");
				}
			}
		}
	}
	
	//刷新
	public static int setReflush(JPanel jp_show,String username) {
		//全部设置为不可见
		for (JPanel jp : list) {
			jp.setVisible(false);
		}
		//重做
		return getOrderList(jp_show,username);
	}
	
}
