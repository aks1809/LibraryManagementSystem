import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Issue extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	PreparedStatement p;
	ResultSet rs;
	/**
	 * 
	 */
	public void lookBookId(int a) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
			p=cn.prepareStatement("select qty from books where id = ?");
			p.setInt(1,a);
			rs=p.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)==0) {
					javax.swing.JOptionPane.showMessageDialog(Home.frame,"Insufficient Copies");
					textField.setText("");
				}
				else
					javax.swing.JOptionPane.showMessageDialog(Home.frame,"Book Available");
			}
			else {
				javax.swing.JOptionPane.showMessageDialog(Home.frame,"Invalid Book ID" );
				textField.setText("");
			}
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public void lookCustomerId(int a) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
			p=cn.prepareStatement("select * from customers where id = ?");
			p.setInt(1,a);
			rs=p.executeQuery();
			if(rs.next()==false) {
				javax.swing.JOptionPane.showMessageDialog(Home.frame,"Invalid Customer ID" );
				textField_1.setText("");
			}
			else
				javax.swing.JOptionPane.showMessageDialog(Home.frame,"Customer Exists");
			cn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	private static final long serialVersionUID = 1L;
	public Issue() {
		setBounds(0, 0, 450, 300);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		JLabel lblBookId = new JLabel("Customer ID");
		lblBookId.setBounds(74, 125, 107, 15);
		getContentPane().add(lblBookId);
		textField = new JTextField();
		textField.setBounds(184, 60, 124, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnIssue = new JButton("ISSUE");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
					p=cn.prepareStatement("insert into trans (bid,cid,i_date,r_date) values (?,?,?,?) ");
					p.setInt(1, Integer.parseInt(textField.getText()));
					p.setInt(2, Integer.parseInt(textField_1.getText()));
					p.setString(3,Calendar.getInstance().getTime().toString());
					p.setString(4,"NA");
					p.executeUpdate();
					p=cn.prepareStatement("update books set qty=qty-1 where id = ?");
					p.setInt(1, Integer.parseInt(textField.getText()));
					p.executeUpdate();
					cn.close();
					javax.swing.JOptionPane.showMessageDialog(Home.frame,"Book Has Been Issued");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
		});
		btnIssue.setBounds(184, 206, 114, 25);
		getContentPane().add(btnIssue);
		
		JLabel lblCustomerId = new JLabel("Book ID");
		lblCustomerId.setBounds(74, 60, 107, 15);
		getContentPane().add(lblCustomerId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 123, 124, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lookBookId(Integer.parseInt(textField.getText()));
			}
		});
		btnSearch.setBounds(320, 60, 81, 19);
		getContentPane().add(btnSearch);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lookCustomerId(Integer.parseInt(textField_1.getText()));
			}
		});
		btnSearch_1.setBounds(320, 123, 81, 19);
		getContentPane().add(btnSearch_1);
	}
}
