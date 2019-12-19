import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class AddCustomers extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	public AddCustomers() {
		setBounds(0,0, 450, 300);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 49, 119, 15);
		getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(223, 47, 124, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(71, 113, 137, 15);
		getContentPane().add(lblPhoneNo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(223, 111, 124, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
					String query="insert into customers(name,pno) values (?,?)";
					PreparedStatement p=cn.prepareStatement(query);
					p.setString(1, textField.getText());
					p.setString(2, textField_1.getText());
					p.executeUpdate();
					cn.close();
					javax.swing.JOptionPane.showMessageDialog(Home.frame, "DETAILS SAVED");
					textField.setText("");
					textField_1.setText("");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
		});
		btnSave.setBounds(223, 177, 124, 25);
		getContentPane().add(btnSave);
	}
}
