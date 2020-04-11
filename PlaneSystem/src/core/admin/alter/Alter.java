package core.admin.alter;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import core.admin.inquiry.Inquiry;
import core.admin.jpanel.InquiryJPanel;
import core.database.Database;
import other.Tool;

/**
 * 修改数据库
 * 1、获取表格的数据
 * 2、更新到数据库中
 * 
 * @author 陌生人
 *
 */

public class Alter {

	static Object[][] temp;
	
	//检查通过，获取新表格数据，和原表格数据作比较，然后写入到数据库中
	public static int alter() {
		int count = 0;		//修改的有效行数
		try {
			Object[][] arr = Tool.arrSort(Inquiry.submit());	//获取原表格数据，即数据库数据
			String startCity = Tool.getTextValue(InquiryJPanel.getJtf_startCity());
			String startAirport = Tool.getTextValue(InquiryJPanel.getJtf_startAirport());
			String reachCity = Tool.getTextValue(InquiryJPanel.getJtf_reachCity());
			String reachAirport = Tool.getTextValue(InquiryJPanel.getJtf_reachAirport());
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(!arr[i][j].equals(temp[i][j])) {		//如果不等，即被修改过了
						count++;
						String sql = "update flight set date='"+temp[i][0]+"',start_time='"+temp[i][2]
								+"',reach_time='"+temp[i][3]+"',used_time='"+temp[i][4]
										+"',seat_count="+temp[i][5]+",price="+temp[i][6]+ 
								" where start_city='"+startCity+"' and start_airport='"+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"' and date = '"+arr[i][0]+"' and flight_num='"+arr[i][1]+"';";
						new Database().setUpdate(sql);
						break;
					}
				}
			}
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			count = -1;
			System.out.println("当前日期已存在该航班，修改失败！");
		}
		return count;
	}
	
	//检查字段是否通过
	public static boolean checkTableInfo(JFrame jf) {
		try {
			Object[][] arr = Inquiry.submit();	//获得表格行和列
			temp = new Object[arr.length][arr[0].length];	//初始化空间
			//这条语句用于判断当前所处的行和列，响应后立即结束编辑状态
			JTable table = InquiryJPanel.getTable();
			//结果如果没有被编辑过，获取选择行选择列抛异常问题
			ListSelectionModel lsm = table.getSelectionModel();
			if(!lsm.isSelectionEmpty())
				table.getCellEditor(table.getSelectedRow(), table.getSelectedColumn()).stopCellEditing();
			//赋值
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(!cheak(jf, String.valueOf(table.getValueAt(i,j)), j)) {
						return false;
					}else {
						temp[i][j] = table.getValueAt(i,j);		//同时赋值到新数组中保存起来，无所谓是否通过，因为通过了才用这个新数组
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("修改数据库的时候读取数据库生成表格失败！");
		}
		return true;
	}
	
	
	//对录入的字符做判断
	public static boolean cheak(JFrame jf ,String str, int column) {
		String reg1 = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		String reg2 = "^([0-1]\\d|2[0-3]):[0-5]\\d$";
		if(column == 0 && !str.matches(reg1)) {
			JOptionPane.showMessageDialog(jf, "录入的日期格式不正确！");return false;
		}else if(column == 1 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入航班！");return false;
		}else if(column == 2 && str.equals("")){
			JOptionPane.showMessageDialog(jf, "请录入起飞时间！");return false;
		}else if(column == 2 && !str.matches(reg2)) {
			JOptionPane.showMessageDialog(jf, "起飞时间格式不正确，格式用例为：16:30！");return false;
		}else if(column == 3 && str.equals("")){
			JOptionPane.showMessageDialog(jf, "请录入降落时间！");return false;
		}else if(column == 3 && !str.matches(reg2)) {
			JOptionPane.showMessageDialog(jf, "降落时间格式不正确，格式用例为：16:30！");return false;
		}else if(column == 4 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入用时，单位为分钟！");return false;
		}else if(column == 4 && !str.matches("^\\d+$")) {
			JOptionPane.showMessageDialog(jf, "用时格式不正确，格式用例：120");return false;
		}else if(column == 5 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入座位数！");return false;
		}else if(column == 5 && !str.matches("^\\d+$")) {
			JOptionPane.showMessageDialog(jf, "座位数格式不正确，必须为大于或等于0的数！");return false;
		}else if(column == 6 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "请录入价格！");return false;
		}else if(column == 6 && !str.matches("^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$")) {
			JOptionPane.showMessageDialog(jf, "价格数格式不正确，必须为大于零的数！");return false;
		}else {
			return true;
		}
	}
}
