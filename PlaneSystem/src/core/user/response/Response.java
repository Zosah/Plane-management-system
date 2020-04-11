package core.user.response;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.user.book.Book;
import core.user.book.Inquiry;
import core.user.book.Order;
import core.user.jpanel.BackAlterJPanel;
import core.user.jpanel.BookJPanel;
import core.user.jpanel.MenuJPanel;
import core.user.jpanel.OrderJPanel;
import core.user.jpanel.PersonJPanel;
import core.user.jpanel.WelcomeJPanel;
import other.Global;
import other.Tool;

/*
 * 用户窗体按钮响应类
 */
public class Response {
	private JFrame jf;
	private String username;
	public Response(JFrame jf,String username) {
		super();
		this.jf = jf;
		this.username = username;
	}
	
	public void userResponse() {
		MenuJPanel.getJb_book().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					JOptionPane.showMessageDialog(jf, "正在进行改签操作！");
				}else {
					BookJPanel.getJp_show().setVisible(true);
					PersonJPanel.getJp_show().setVisible(false);
					BackAlterJPanel.getJsp_show().setVisible(false);
				}
			}
		});
		
		//航班预订-查询航班
		BookJPanel.getJb_inquiry().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						BookJPanel.getJp_inquiry().setVisible(false);
						BookJPanel.getJp_showTitle().setVisible(true);
						BookJPanel.getJp_showTable().setVisible(true);
						BookJPanel.getJp_showButtom().setVisible(true);
						BookJPanel.getJp_showPassenger().setVisible(false);
						BookJPanel.getJp_showPay().setVisible(false);
						Object[][] arr = Inquiry.submit();		//获取数据库的表格
						if(arr[0][0].equals("暂无航班")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "暂无航班";
							}
							BookJPanel.addTable(Tool.arrSort(arr));		//排序后、调用添加表格方法
							JOptionPane.showMessageDialog(jf, "该区间暂无航班！");
						}else {
							BookJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "已显示全部航班！");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		//航班预订-取消
		BookJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					int n = JOptionPane.showConfirmDialog(null, "确定要取消改签吗?", "改签提示：", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						Global.setAlter(false); //修改改签标志
						Order.cancelAlter();
						JOptionPane.showMessageDialog(Global.getJf3(), "改签已取消！");
					}
				}else {
					BookJPanel.getJp_show().setVisible(false);
				}
			}
		});
		//航班查询-刷新
		BookJPanel.getJb_reflush().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						BookJPanel.getJp_inquiry().setVisible(false);
						BookJPanel.getJp_showTitle().setVisible(true);
						BookJPanel.getJp_showTable().setVisible(true);
						BookJPanel.getJp_showButtom().setVisible(true);
						BookJPanel.getJp_showPassenger().setVisible(false);
						BookJPanel.getJp_showPay().setVisible(false);
						Object[][] arr = Inquiry.submit();	
						
						if(arr[0][0].equals("暂无航班")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "暂无航班";
							}
							BookJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "该区间暂无航班！");
						}else {
							BookJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "已显示全部航班！");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//航班查询-查询机场1
		BookJPanel.getJb_inquiryAirport1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Global.getAlter()) {
						JOptionPane.showMessageDialog(jf, "正在进行改签操作！");
					}else {
						//填充机场
						if(String.valueOf(BookJPanel.jtf_startCity.getText()).trim().equals("")) {
							JOptionPane.showMessageDialog(jf, "请输入出发城市！");
						}else {
							List<String> airportlist = Inquiry.getAirport(Inquiry.getStartCity());
							String airport = "";
							if(airportlist.size()==0) {
								JOptionPane.showMessageDialog(jf, "暂未查询到该城市的机场！");
							}else {
								String msg = "已为你查询到"+airportlist.size()+"个机场：";
								for (String s : airportlist) {
									msg+=s+";";
									airport = s;
								}
								JOptionPane.showMessageDialog(jf, msg+"已为你自动填充一个机场！");
								BookJPanel.getJtf_startAirport().setText(airport);
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//航班查询-查询机场2
		BookJPanel.getJb_inquiryAirport2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Global.getAlter()) {
						JOptionPane.showMessageDialog(jf, "正在进行改签操作！");
					}else {
						//填充机场
						if(String.valueOf(BookJPanel.jtf_reachCity.getText()).trim().equals("")) {
							JOptionPane.showMessageDialog(jf, "请输入出发城市！");
						}else {
							List<String> airportlist = Inquiry.getAirport(Inquiry.getReachCity());
							String airport = "";
							if(airportlist.size()==0) {
								JOptionPane.showMessageDialog(jf, "暂未查询到该城市的机场！");
							}else {
								String msg = "已为你查询到"+airportlist.size()+"个机场：";
								for (String s : airportlist) {
									msg+=s+";";
									airport = s;
								}
								JOptionPane.showMessageDialog(jf, msg+"已为你自动填充一个机场！");
								BookJPanel.getJtf_reachAirport().setText(airport);
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//设置日期
		BookJPanel.getJb_today().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tool.setToday(BookJPanel.getJtf_time());
			}
		});
		//查询预订-返回
		BookJPanel.getJb_reback().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookJPanel.getJp_inquiry().setVisible(true);
				BookJPanel.getJp_showTitle().setVisible(false);
				BookJPanel.getJp_showTable().setVisible(false);
				BookJPanel.getJp_showButtom().setVisible(false);
				BookJPanel.getJp_showPassenger().setVisible(false);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		//查询预订-预订航班
		BookJPanel.getJb_book().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					
					//检测到是改签模式
					int flag = Book.cheakFlight(username);
					if(flag==1) {	//初步检查通过
						//二次检查是否为统一航班
						if(!((Tool.getTextValue(BookJPanel.getJtf_handle()).equals(Order.getOldFlight()))
								&& (Tool.getTextValue(BookJPanel.getJtf_time()).equals(Order.getOldTime())))) {
							//改签成功需要进行以下操作
							Order.backTicket("改签模式");	//原来航班座位数在数据库中+1
							Book.setDownSeat();			//选中的航班座位数在数据库中-1
							Order.updateOrder(); 		//修改订单信息
							JOptionPane.showMessageDialog(jf, "改签成功，正在跳转到订单页面...");
							Global.setAlter(false); //修改改签标志为false
							BookJPanel.getJp_show().setVisible(false);
							Order.cancelAlter();	//设置回非改签模式
							JOptionPane.showMessageDialog(jf, "请刷新你的订单！");
//							Order.getAlter().setText("已改签");
							
						}else {
							JOptionPane.showMessageDialog(jf, "改签的新航班和旧航班一致，请修改！");
						}
						
					}else if(flag==2) {
						JOptionPane.showMessageDialog(jf, "请输入或选择航班号！");
					}else if(flag==3) {
						JOptionPane.showMessageDialog(jf, "当前航班余票不足！");
					}else {
						JOptionPane.showMessageDialog(jf, "未知错误，改签失败！");
					}
				}else {
					// 1成功，2为空，3座位为0了，4未知错误
					int flag = Book.cheakFlight(username);
					if(flag==1) {	//检查通过
						BookJPanel.getJp_inquiry().setVisible(false);
						BookJPanel.getJp_showTitle().setVisible(false);
						BookJPanel.getJp_showTable().setVisible(false);
						BookJPanel.getJp_showButtom().setVisible(false);
						BookJPanel.getJp_showPassenger().setVisible(true);
						BookJPanel.getJp_showPay().setVisible(false);
					}else if(flag==2) {
						JOptionPane.showMessageDialog(jf, "请输入或选择航班号！");
					}else if(flag==3) {
						JOptionPane.showMessageDialog(jf, "当前航班余票不足！");
					}else {
						JOptionPane.showMessageDialog(jf, "未知错误，预订失败！");
					}
				}
			}
		});
		//返回查询预订界面
		BookJPanel.getJb_reback2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookJPanel.getJp_inquiry().setVisible(false);
				BookJPanel.getJp_showTitle().setVisible(true);
				BookJPanel.getJp_showTable().setVisible(true);
				BookJPanel.getJp_showButtom().setVisible(true);
				BookJPanel.getJp_showPassenger().setVisible(false);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		//填充个人信息
		BookJPanel.getJb_useOneSelf().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book.getOneSelf(username);
			}
		});
		//去支付
		BookJPanel.getJb_toPay().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Book.cheakFull(jf,username)) {
					BookJPanel.getJp_inquiry().setVisible(false);
					BookJPanel.getJp_showTitle().setVisible(false);
					BookJPanel.getJp_showTable().setVisible(false);
					BookJPanel.getJp_showButtom().setVisible(false);
					BookJPanel.getJp_showPassenger().setVisible(false);
					BookJPanel.getJp_showPay().setVisible(true);
					Book.setPrice(); //到支付界面前，设置价格
				}
			}
		});
		//支付界面返回
		BookJPanel.getJb_reback3().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookJPanel.getJp_inquiry().setVisible(false);
				BookJPanel.getJp_showTitle().setVisible(false);
				BookJPanel.getJp_showTable().setVisible(false);
				BookJPanel.getJp_showButtom().setVisible(false);
				BookJPanel.getJp_showPassenger().setVisible(true);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		//支付成功
		BookJPanel.getJb_paid().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Book.insertOrder(username)) {	//插入订单表
					Book.setDownSeat();		//座位号减1
					JOptionPane.showMessageDialog(jf, "支付成功！请到退改管理页面查询飞机票订单！");
					Book.setAllEmpty();
				}else {
					JOptionPane.showMessageDialog(jf, "已存在预订了当前航班，购买失败！！");
				}
				//返回初始界面
				BookJPanel.getJp_inquiry().setVisible(true);
				BookJPanel.getJp_showTitle().setVisible(false);
				BookJPanel.getJp_showTable().setVisible(false);
				BookJPanel.getJp_showButtom().setVisible(false);
				BookJPanel.getJp_showPassenger().setVisible(false);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		
		
		//菜单-退改管理
		MenuJPanel.getJb_backAlter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					JOptionPane.showMessageDialog(jf, "正在进行改签操作！");
				}else {
					BookJPanel.getJp_show().setVisible(false);
					BackAlterJPanel.getJsp_show().setVisible(true);
					PersonJPanel.getJp_show().setVisible(false);
					Global.setOrderCount(OrderJPanel.setReflush(BackAlterJPanel.getJp_show(), username));
					if(Global.getOrderCount() != 0) {
						BackAlterJPanel.getJl_tips().setVisible(false);
					}else {
						BackAlterJPanel.getJl_tips().setVisible(true);
					}
					JOptionPane.showMessageDialog(jf, "读取完毕，已经显示"+Global.getOrderCount()+"条订单！");
				}
			}
		});
		//退改管理-取消
		BackAlterJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BackAlterJPanel.getJsp_show().setVisible(false);
			}
		});
		//退改管理-刷新
		BackAlterJPanel.getJb_reflush().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Global.setOrderCount(OrderJPanel.setReflush(BackAlterJPanel.getJp_show(), username));
				if(Global.getOrderCount() != 0) {
					BackAlterJPanel.getJl_tips().setVisible(false);
				}else {
					BackAlterJPanel.getJl_tips().setVisible(true);
				}
				JOptionPane.showMessageDialog(jf, "刷新成功，已经显示"+Global.getOrderCount()+"条订单！");
			}
		});
		
		//菜单-个人信息
		MenuJPanel.getJb_person().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					JOptionPane.showMessageDialog(jf, "正在进行改签操作！");
				}else {
					BookJPanel.getJp_show().setVisible(false);
					BackAlterJPanel.getJsp_show().setVisible(false);
					PersonJPanel.getJp_show().setVisible(true);
				}
			}
		});
		
		//打开更换手机号权限
		PersonJPanel.getJb_alterPhone().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PersonJPanel.getJb_alterPhone().getText().equals("暂不更换")) {
					PersonJPanel.getJtf_phone().setEditable(false);
					PersonJPanel.getJtf_phone().setText(PersonJPanel.getPhone());
					PersonJPanel.getJb_alterPhone().setText("更换手机号");
				}else {
					PersonJPanel.getJtf_phone().setEditable(true);
					PersonJPanel.getJb_alterPhone().setText("暂不更换");
				}
			}
		});
		//更换手机获取文本框内容
		PersonJPanel.getJb_alter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PersonJPanel.alterPhone(jf)) {
					JOptionPane.showMessageDialog(jf, "新手机号修改成功！");
					PersonJPanel.getJtf_phone().setEditable(false);
					PersonJPanel.getJb_alterPhone().setText("更换手机号");
				}
			}
		});
		//个人信息-取消
		PersonJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonJPanel.getJp_show().setVisible(false);
			}
		});
		//注销
		WelcomeJPanel.getJb_quit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Global.getJf3().setVisible(false);
				Global.getJf1().setVisible(true);
			}
		});
	}
}
