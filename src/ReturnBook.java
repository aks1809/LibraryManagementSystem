import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class ReturnBook extends JInternalFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	PreparedStatement p;
	ResultSet rs;
	int bid;
	public ReturnBook() {
		setBounds(0,0, 450, 300);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblTransactionId = new JLabel("Transaction ID");
		lblTransactionId.setBounds(74, 70, 141, 32);
		getContentPane().add(lblTransactionId);
		
		textField = new JTextField();
		textField.setBounds(215, 77, 124, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
					p=cn.prepareStatement("select bid from trans where id=?");
					p.setInt(1, Integer.parseInt(textField.getText()));
					rs=p.executeQuery();
					rs.next();
					bid=rs.getInt(1);
					p=cn.prepareStatement("update trans set r_date=? where id=?");
					p.setString(1, Calendar.getInstance().getTime().toString());
					p.setInt(2, Integer.parseInt(textField.getText()));
					p.executeUpdate();
					p=cn.prepareStatement("update books set qty=qty+1 where id=?");
					p.setInt(1, bid);
					p.executeUpdate();
					cn.close();
					javax.swing.JOptionPane.showMessageDialog(Home.frame,"Book Returned Successfully");
					textField.setText("");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
		});
		btnReturn.setBounds(215, 145, 124, 25);
		getContentPane().add(btnReturn);
	}

}
