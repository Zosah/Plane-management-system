package other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * -添加组件的类
 * @author 陌生人
 *
 */

public class AddComonents {
	//添加面板
	public static JPanel addJPanel(JFrame jf, int x, int y ,int width, int height) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(x, y, width, height);
		jf.add(jp);
		Reflush.reflush(jp);
		return jp;
	}
	//添加二级面板
	public static JPanel addJPanel_2(JPanel jpanel, int x, int y ,int width, int height) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(x, y, width, height);
		jpanel.add(jp);
		jp.setVisible(true);
		return jp;
	}
	
	//添加按钮
	private static Color c = new Color(187,206,219);
	public static JButton addJButton(String s, JPanel jp, int x, int y ,int width, int height,
			boolean opaque, boolean border, boolean left) {
		
		JButton jb = new JButton(s);
		jb.setBounds(x, y, width, height);
		if(opaque) {
			jb.setBackground(c);
			jb.setOpaque(false);
		}
		jb.setBorderPainted(border);
		if(left) {
			jb.setHorizontalAlignment(JButton.LEFT);
			jb.setMargin(new Insets(0,10,0,0));
		}
		
		jp.add(jb); 
		Reflush.reflush(jb);
		return jb;
	}
	//添加标签
	public static JLabel addJLabel(String s, JPanel jp, int x, int y ,int width, int height) {
		JLabel jl = new JLabel(s);
		jl.setBounds(x, y, width, height);
		jp.add(jl);
		Reflush.reflush(jl);
		return jl;
	}
	//添加文本框
	public static JTextField addJTextField(JPanel jp, int x, int y ,int width, int height) {
		JTextField jtf = new JTextField(10);
		jtf.setBounds(x, y, width, height);
		jp.add(jtf);
		Reflush.reflush(jtf);
		return jtf;
	}
	
	//添加密码文本框
	public static JPasswordField addJPasswordField(JPanel jp, int x, int y ,int width, int height) {
		JPasswordField jpf = new JPasswordField(10);
		jpf.setBounds(x, y, width, height);
		jp.add(jpf);
		Reflush.reflush(jpf);
		return jpf;
	}
	
	//添加文本域返回封装好的面板
	public static JTextArea addJTextArea(JPanel jp, int x, int y ,int width, int height) {
		JTextArea jta = new JTextArea();
		jta.setBounds(x, y, width, height);
		jp.add(jta);
		jta.setVisible(false);
		jta.setLineWrap(true);  		//自动换行
		jta.setWrapStyleWord(true);  	//不断字
		return jta;
	}
	
	//添加单选框
	public static JRadioButton addJRadioButton(String s, JPanel jp, int x, int y ,int width, int height) {
		JRadioButton jrb = new JRadioButton(s);
		jrb.setBounds(x, y, width, height);
		jp.add(jrb);
		Reflush.reflush(jrb);
		return jrb;
	}	
	
	//设置二维码图标返回一个面板
	public static JPanel addCode(JPanel j,String img,int x, int y) {
		Icon i = new ImageIcon("src\\image\\"+img);
		JLabel jl = new JLabel(i);
		JPanel jp = new JPanel(); jp.setBackground(new Color(205,235,234));
		jl.setBounds(0, 0, 90,90);
		jp.setBounds(x, y, 205,205);
		jp.add(jl);
		j.add(jp);
		Reflush.reflush(jp);
		return jp;
	}	
		
	//添加表格：传入面板、表头、数组
	public static JTable addJTable(Object[] title, Object[][] arr, JPanel jp, boolean edit) {
		
		//设定一个模式，只可编辑一部分
		DefaultTableModel model = new DefaultTableModel(arr, title) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				//默认设定航班号所在列不可以修改
				if(column != 1) {
					return true;
				}else {
					return false;
				}
			}
		};
		JTable table = new JTable(model);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 30));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(edit);		//设置是否可编辑
		table.updateUI();
		return table;
	}
	
	//添加表格：传入面板、表头、数组
	public static JTable addJTable2(Object[] title, Object[][] arr, JPanel jp, boolean edit) {
		
		//设定一个模式，只可编辑一部分
		DefaultTableModel model = new DefaultTableModel(arr, title) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				//默认设定航班号所在列不可以修改
				return false;
			}
		};
		JTable table = new JTable(model);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 30));	//设置表头的宽
		
		JScrollPane jsp = new JScrollPane(table);		//把表格放到滚动条面板中
		jp.setLayout(new BorderLayout());			//设置普通面板为边界布局，从而覆盖整个普通面板
		jp.add(jsp, BorderLayout.CENTER);			//把滚动条面板添加到普通面板上
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //表格数据居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(edit);		//设置是否可编辑
		table.updateUI();
		return table;
	}
	//添加表格：传入面板、数组、提示头
	public static JComboBox addJComboBox(JPanel j, List<String> list, String title, int x, int y, int width, int height) {
		JComboBox<String> cmb = new JComboBox<String>();    //创建JComboBox
		cmb.setBounds(x, y, width, height);
        cmb.addItem(title);    	//向下拉列表中添加一项
        for (String str : list) {
			cmb.addItem(str);
		}
        j.add(cmb);
        return cmb;
	}
}
