package core.admin.statistics;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
public class PieChart {
	ChartPanel cp;
	public PieChart(){
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("每周订单占比",data,true,false,false);
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");		//获得一个DecimalFormat对象，主要是设置小数问题
		NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
		//获得StandardPieSectionLabelGenerator对象
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
		pieplot.setLabelGenerator(sp1);						//设置饼图显示百分比
		
		//没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);
		
		pieplot.setIgnoreNullValues(true);//设置不显示空值
		pieplot.setIgnoreZeroValues(true);//设置不显示负值
		cp=new ChartPanel (chart,true);
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
		PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
		piePlot.setLabelFont(new Font("宋体",Font.BOLD,10));//解决乱码
		chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
	}
	private static DefaultPieDataset getDataSet() {
		String[] arr = StatisticsData.getOrderArrs();
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("第一周订单量",Integer.valueOf(arr[0]));
		dataset.setValue("第二周订单量",Integer.valueOf(arr[1]));
		dataset.setValue("第三周订单量",Integer.valueOf(arr[2]));
		dataset.setValue("第四周订单量",Integer.valueOf(arr[3]));
		dataset.setValue("第五周订单量",Integer.valueOf(arr[4]));
		return dataset;
	}
	public ChartPanel getChartPanel(){
		return cp;
	}
}