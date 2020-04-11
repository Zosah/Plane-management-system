package core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 链接数据库，增删改查
 * 
 * @author 陌生人
 *
 */
public class Database {
	// 连接数据库
	private String url = "jdbc:mysql://localhost:3306/java_data?useUnicode=true&characterEncoding=utf8";
	private String sqluser = "root";
	private String sqlpass = "123456";
	private Connection conn; // 连接对象
	private Statement stmt; // 连接后用于操作的对象
	
	//链接数据库
	public void connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, sqluser, sqlpass);
		stmt = conn.createStatement();
	}
	
	//查询数据，返回集合
	public ResultSet getSelect(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接失败！");
		}
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	//更新数据
	public void setUpdate(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接失败！");
		}
		stmt.executeUpdate(sql);
	}
	
	//插入数据
	public void setInsert(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接失败！");
		}
		stmt.executeUpdate(sql); 
	}
	
	//删除数据
	public int setDelete(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接失败！");
		}
		int row = stmt.executeUpdate(sql);
		return row;
	}
	
	//获得数据库中的航班日期-年/月  1为年，(0为月,年份）
	public List<String> getFlightYearOrMonth(int flag,String itemYear) throws SQLException{
		List<String> list = new ArrayList<String>();
		String sql = "select date from flight";
		
		try {
			connect();
			ResultSet rs = stmt.executeQuery(sql);
			if(flag == 1) {
				while(rs.next()) {
					String year = rs.getString("date").split("-")[0];
					if(!list.contains(year)) {
						list.add(year);
					}
				}
			}else {
				while(rs.next()) {
					//传入的年份和菜单的年份一致
					if(itemYear.equals(rs.getString("date").split("-")[0])) {	
						String month = rs.getString("date").split("-")[1];
						if(!list.contains(month)) {
							list.add(month);
						}
					}
				}
			}
			return list;
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接失败！");
		}
		return list;
	}
}
