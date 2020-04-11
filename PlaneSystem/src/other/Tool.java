package other;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tool {
	
	//传入二维数组，对时间进行排序然后返回，向下递增
	public static Object[][] arrSort(Object[][] arr){
		Object[] temp = new Object[arr[0].length];		//	定义临时一个一维数组
		for(int i=0; i<arr.length-1; i++){   			//表示趟数，一共arr.length-1次。
	         for(int j = arr.length-1; j>i; j--){
	        	 int time1 = Integer.parseInt(((arr[j][2]).toString().split(":"))[0]+((arr[j][2]).toString().split(":"))[1]);
	        	 int time2 = Integer.parseInt(((arr[j-1][2]).toString().split(":"))[0]+((arr[j-1][2]).toString().split(":"))[1]);
	        	 if(time1<time2) {
	        		 for (int k = 0; k < temp.length; k++) {
	        			 temp[k] = arr[j][k];
	        		 }
	        		 for (int k = 0; k < arr[j].length; k++) {
	        			 arr[j][k] = arr[j-1][k];
	        		 }
	        		 for (int k = 0; k < arr[j].length; k++) {
	        			 arr[j-1][k] = temp[k];
	        		 }
	        	 }
	         }
		}
		return arr;
	}
	
	//传入JTextField，返回对应的值
	public static String getTextValue(JTextField jtf) {
		return String.valueOf(jtf.getText()).trim();
	}
	
	//传入面板，设置边框
	public static void setBorder(JPanel jp) {
		jp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	}
	
	//获得今天的日期和输入的日期做比较
	public static boolean Tomorrow(String date) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(d);
		if(Integer.valueOf(date.split("-")[0]+date.split("-")[1]+date.split("-")[2])
				>= Integer.valueOf(today.split("-")[0]+today.split("-")[1]+today.split("-")[2])) {
			return true;
		}else {
			return false;
		}
	}
	
	//设置文本框为今天
	public static void setToday(JTextField jtf) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(d);
		jtf.setText(today);
	}
	
}
