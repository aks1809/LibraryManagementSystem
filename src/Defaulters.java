import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Defaulters extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	PreparedStatement p;
	DefaultTableModel dt;
	String bookDetails(int id) {
		String s = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
			p=cn.prepareStatement("select name,pub from books where id=?");
			p.setInt(1, id);
			ResultSet rs=p.executeQuery();
			rs.next();
			s=rs.getString(1)+","+rs.getString(2);
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return s;
	}
	String customerDetails(int id) {
		String s = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
			p=cn.prepareStatement("select name,pno from customers where id=?");
			p.setInt(1, id);
			ResultSet rs=p.executeQuery();
			rs.next();
			s=rs.getString(1)+","+rs.getString(2);
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return s;
	}
	public Defaulters() {
		setBounds(0, 0, 450, 300);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		dt=new DefaultTableModel();
		table = new JTable();
		dt.addColumn("Customer ID");
		dt.addColumn("Customer Name");
		dt.addColumn("Customer PNo.");
		dt.addColumn("Book ID");
		dt.addColumn("Book Name");
		dt.addColumn("Book Publisher");
		dt.addColumn("Issue Date");
		getContentPane().add(table, BorderLayout.CENTER);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
			p=cn.prepareStatement("select bid,cid,i_date from trans where r_date='NA'");
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				String bd[]=bookDetails(rs.getInt(1)).split(",");
				String cd[]=customerDetails(rs.getInt(2)).split(",");
				dt.addRow(new Object[]{rs.getInt(2),cd[0],cd[1],rs.getInt(1),bd[0],bd[1],rs.getString(3)});
			}
			table.setModel(dt);
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
