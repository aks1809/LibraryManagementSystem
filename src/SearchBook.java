import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;

public class SearchBook extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	DefaultTableModel dt;
	public SearchBook() {
		dt=new DefaultTableModel();
		table=new JTable();
		textField = new JTextField();
		setBounds(0, 0, 450, 300);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().add(table, BorderLayout.CENTER);
		textField.setColumns(10);
		getContentPane().add(textField, BorderLayout.NORTH);
		dt.addColumn("ID");
		dt.addColumn("BOOK NAME");
		dt.addColumn("PUBLISHER");
		dt.addColumn("QUANTITY");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
			String query="select * from books where name like ? or pub like ?";
			PreparedStatement p=cn.prepareStatement(query);
			p.setString(1, "%"+textField.getText()+"%");
			p.setString(2, "%"+textField.getText()+"%");
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				dt.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)});
			}
			table.setModel(dt);
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					dt.setRowCount(0);
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
					String query="select * from books where name like ? or pub like ?";
					PreparedStatement p=cn.prepareStatement(query);
					p.setString(1, "%"+textField.getText()+"%");
					p.setString(2, "%"+textField.getText()+"%");
					ResultSet rs=p.executeQuery();
					while(rs.next()) {
						dt.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)});
					}
					table.setModel(dt);
					cn.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
	}

}
