package core.admin.response;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.admin.input.Input;
import core.admin.inquiry.Inquiry;
import core.admin.jpanel.InputJPanel;
import core.admin.jpanel.InquiryJPanel;
import core.admin.jpanel.MenuJPanel;
import core.admin.jpanel.StatisticsJPanel;
import core.admin.jpanel.WelcomeJPanel;
import other.Global;
import other.Tool;

/**
 * - admin管理员界面按钮集合
 * @author 陌生人
 *
 */

public class Response {
	
	private JFrame jf;
	public Response(JFrame jf) {
		super();
		this.jf = jf;
	}
	
	public void adminResponse() {
		//菜单-航班查询
		MenuJPanel.getJb_inquiry().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(true);
				InputJPanel.getJp_show().setVisible(false);
				StatisticsJPanel.getJp_show().setVisible(false);
			}
		});
		//菜单-航班录入
		MenuJPanel.getJb_input().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(false);
				InputJPanel.getJp_show().setVisible(true);
				StatisticsJPanel.getJp_show().setVisible(false);
			}
		});

		//菜单-统计报表
		MenuJPanel.getJb_statistics().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(false);
				InputJPanel.getJp_show().setVisible(false);
				StatisticsJPanel.getJp_show().setVisible(true);
			}
		});
		
		
		//航班查询-查询航班
		InquiryJPanel.getJb_inquiry().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						InquiryJPanel.getJp_inquiry().setVisible(false);
						InquiryJPanel.getJp_showTitle().setVisible(true);
						InquiryJPanel.getJp_showTable().setVisible(true);
						InquiryJPanel.getJp_showButtom().setVisible(true);
						Object[][] arr = Inquiry.submit();
						if(arr[0][0].equals("暂无航班")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "暂无航班";
							}
							InquiryJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "该区间暂无航班！");
						}else {
							InquiryJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "已显示全部航班！");
						}
						InquiryJPanel.getTable().setEnabled(true);
						InquiryJPanel.getTable().getSelectionModel().clearSelection();	//清除选中行和退出编辑
						InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
						//判断表格状态，保持刷新前的状态
						if(InquiryJPanel.getJrb_alter().isSelected()) {
							InquiryJPanel.getJtf_handle().setEditable(false);
							InquiryJPanel.getTable().setEnabled(true);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//航班查询-取消
		InquiryJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(false);
			}
		});
		//航班查询-刷新
		InquiryJPanel.getJb_reflush().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						InquiryJPanel.getJp_inquiry().setVisible(false);
						InquiryJPanel.getJp_showTitle().setVisible(true);
						InquiryJPanel.getJp_showTable().setVisible(true);
						InquiryJPanel.getJp_showButtom().setVisible(true);
						
						Object[][] arr = Inquiry.submit();
						
						if(arr[0][0].equals("暂无航班")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "暂无航班";
							}
							InquiryJPanel.addTable(arr);
							JOptionPane.showMessageDialog(jf, "该区间暂无航班！");
						}else {
							InquiryJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "已显示全部航班！");
						}
						InquiryJPanel.getTable().setEnabled(true);
						InquiryJPanel.getTable().getSelectionModel().clearSelection();	//清除选中行和退出编辑
						InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
						//判断表格状态，保持刷新前的状态
						if(InquiryJPanel.getJrb_alter().isSelected()) {
							InquiryJPanel.getJtf_handle().setEditable(false);
							InquiryJPanel.getTable().setEnabled(true);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//航班查询-返回
		InquiryJPanel.getJb_reback().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_showTitle().setVisible(false);
				InquiryJPanel.getJp_showTable().setVisible(false);
				InquiryJPanel.getJp_showButtom().setVisible(false);
				InquiryJPanel.getJp_inquiry().setVisible(true);
			}
		});
		//航班查询-查询机场1
		InquiryJPanel.getJb_inquiryAirport1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(String.valueOf(InquiryJPanel.jtf_startCity.getText()).trim().equals("")) {
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
							InquiryJPanel.getJtf_startAirport().setText(airport);
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//航班查询-查询机场2
		InquiryJPanel.getJb_inquiryAirport2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(String.valueOf(InquiryJPanel.jtf_reachCity.getText()).trim().equals("")) {
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
							InquiryJPanel.getJtf_reachAirport().setText(airport);
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//设置日期
		InquiryJPanel.getJb_today().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tool.setToday(InquiryJPanel.getJtf_time());
			}
		});
		//航班查询-单选框-删除
		InquiryJPanel.getJrb_delete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJrb_delete().setSelected(true);
				if(InquiryJPanel.getJrb_delete().isSelected()) {
					InquiryJPanel.getJrb_alter().setSelected(false);
					InquiryJPanel.getJb_handle().setText("删除航班");
					InquiryJPanel.getJtf_handle().setEditable(true);
					InquiryJPanel.getTable().setEnabled(true);
					InquiryJPanel.getTable().getSelectionModel().clearSelection();	//清除选中行和退出编辑
					InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
					JOptionPane.showMessageDialog(jf, "已选择为删除，请输入你想删除的航班号！");
				}
			}
		});
		//航班查询-单选框-修改
		InquiryJPanel.getJrb_alter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJrb_alter().setSelected(true);
				if(InquiryJPanel.getJrb_alter().isSelected()) {
					InquiryJPanel.getJrb_delete().setSelected(false);
					InquiryJPanel.getJb_handle().setText("修改航班");
					InquiryJPanel.getJtf_handle().setEditable(false);
					InquiryJPanel.getTable().setEnabled(true);
					InquiryJPanel.getTable().getSelectionModel().clearSelection();	//清除选中行和退出编辑
					InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
					JOptionPane.showMessageDialog(jf, "已选择为修改，可直接在表格中修改（航班号不支持修改）！");
				}
			}
		});
		
		//航班查询-删除、修改处理
		InquiryJPanel.getJb_handle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inquiry.handle(jf);
			}
		});
		
		
		//航班录入-确定录入
		InputJPanel.getJb_input().addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(new Input(jf).cheak()) {
					try {
						new Input(jf).submit();
						JOptionPane.showMessageDialog(jf, "已录入一条航班！");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(jf, "录入失败！当前日期已存在该航班，请重新录入！");
					}
				}
			}
		});
		//航班录入-取消
		InputJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InputJPanel.getJp_show().setVisible(false);
			}
		});
		
		//统计报表-取消
		StatisticsJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatisticsJPanel.getJp_show().setVisible(false);
			}
		});
		
		//注销
		WelcomeJPanel.getJb_quit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Global.getJf2().setVisible(false);
				Global.getJf1().setVisible(true);
			}
		});
	}
}
