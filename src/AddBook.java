import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class AddBook extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public AddBook() {
		setBounds(0, 0, 450, 300);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(96, 52, 114, 15);
		getContentPane().add(lblBookName);
		
		textField = new JTextField();
		textField.setBounds(242, 50, 124, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPublisherName = new JLabel("Publisher Name");
		lblPublisherName.setBounds(96, 102, 143, 15);
		getContentPane().add(lblPublisherName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(242, 100, 124, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(96, 149, 66, 15);
		getContentPane().add(lblQuantity);
		
		textField_2 = new JTextField();
		textField_2.setBounds(242, 147, 124, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/lms?user=root&password=Son@L1997");
					String query="insert into books(name,pub,qty) values (?,?,?)";
					PreparedStatement p=cn.prepareStatement(query);
					p.setString(1, textField.getText());
					p.setString(2, textField_1.getText());
					p.setInt(3, Integer.parseInt(textField_2.getText()));
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
		btnSave.setBounds(242, 196, 124, 25);
		getContentPane().add(btnSave);
	}
}
