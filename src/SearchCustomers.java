import java.sql.*;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchCustomers extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	DefaultTableModel dt;
	public SearchCustomers() {
		setBounds(0,0, 450, 300);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					dt.setRowCount(0);
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
					String query="select * from customers where name like ?";
					PreparedStatement p=cn.prepareStatement(query);
					p.setString(1, "%"+textField.getText()+"%");
					ResultSet rs=p.executeQuery();
					while(rs.next()) {
						dt.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3)});
					}
					table.setModel(dt);
					cn.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		table = new JTable();
		getContentPane().add(table, BorderLayout.CENTER);
		dt=new DefaultTableModel();
		dt.addColumn("ID");
		dt.addColumn("NAME");
		dt.addColumn("PHONE NO.");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
			String query="select * from customers where name like ?";
			PreparedStatement p=cn.prepareStatement(query);
			p.setString(1, "%"+textField.getText()+"%");
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				dt.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3)});
			}
			table.setModel(dt);
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
