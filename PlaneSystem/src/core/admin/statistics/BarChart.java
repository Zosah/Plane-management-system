package core.admin.statistics;

import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
	
	ChartPanel cp;
	public  BarChart(){
		// 图表标题
		// 目录轴的显示标签
 		// 数值轴的显示标签
		// 数据集
		// 图表方向：水平、垂直
     	// 是否显示图例(对于简单的柱状图必须是false)
     	// 是否生成工具
       	// 是否生成URL链接
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("订单销售情况柱形图","阶段","订单量",dataset,PlotOrientation.VERTICAL,true,false,false);
      
       CategoryPlot plot=chart.getCategoryPlot();							//获取图表区域对象
       CategoryAxis domainAxis=plot.getDomainAxis();         				//水平底部列表
       domainAxis.setLabelFont(new Font("Default",Font.BOLD,14));          //水平底部标题
       domainAxis.setTickLabelFont(new Font("Default",Font.BOLD,12));  		//垂直标题
       ValueAxis rangeAxis=plot.getRangeAxis();								//获取柱状
       rangeAxis.setLabelFont(new Font("Default",Font.BOLD,15));
       chart.getLegend().setItemFont(new Font("Default", Font.BOLD, 15));
       chart.getTitle().setFont(new Font("Default",Font.BOLD,20));			//设置标题字体
       cp = new ChartPanel(chart,true);        
	}
       
	public static CategoryDataset getDataSet() {
		String[] arr = StatisticsData.getOrderArrs();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(Integer.valueOf(arr[0]), "订单量", "第一周");
		dataset.addValue(Integer.valueOf(arr[1]), "订单量", "第二周");
		dataset.addValue(Integer.valueOf(arr[2]), "订单量", "第三周");
		dataset.addValue(Integer.valueOf(arr[3]), "订单量", "第四周");
		dataset.addValue(Integer.valueOf(arr[4]), "订单量", "第五周");
		dataset.addValue(Integer.valueOf(arr[5]), "订单量", "单月总量");
		return dataset;
	}
	public ChartPanel getChartPanel(){
		return cp;
	}
}




