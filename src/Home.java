import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static Home frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);
		
		JMenuItem mntmAddBook = new JMenuItem("Add Book");
		mntmAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddBook ab =new AddBook();
				ab.setVisible(true);
				desktopPane.add(ab);
			}
		});
		mnBooks.add(mntmAddBook);
		
		JMenuItem mntmSearchBook = new JMenuItem("Search Book");
		mntmSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchBook sb=new SearchBook();
				sb.setVisible(true);
				desktopPane.add(sb);
			}
		});
		mnBooks.add(mntmSearchBook);
		
		JMenu mnCustomers = new JMenu("Customers");
		menuBar.add(mnCustomers);
		
		JMenuItem mntmAddCustomers = new JMenuItem("Add Customers");
		mntmAddCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCustomers ac=new AddCustomers();
				ac.setVisible(true);
				desktopPane.add(ac);
			}
		});
		mnCustomers.add(mntmAddCustomers);
		
		JMenuItem mntmSearchCustomers = new JMenuItem("Search Customers");
		mntmSearchCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchCustomers sc=new SearchCustomers();
				sc.setVisible(true);
				desktopPane.add(sc);
			}
		});
		mnCustomers.add(mntmSearchCustomers);
		
		JMenu mnTransactions = new JMenu("Transactions");
		menuBar.add(mnTransactions);
		
		JMenuItem mntmIssued = new JMenuItem("Issue");
		mntmIssued.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Issue i=new Issue();
				i.setVisible(true);
				desktopPane.add(i);
			}
		});
		mnTransactions.add(mntmIssued);
		
		JMenuItem mntmReturn = new JMenuItem("Return");
		mntmReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReturnBook rb=new ReturnBook();
				rb.setVisible(true);
				desktopPane.add(rb);
			}
		});
		mnTransactions.add(mntmReturn);
		
		JMenuItem mntmDefaulters = new JMenuItem("Defaulters");
		mntmDefaulters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Defaulters df=new Defaulters();
				df.setVisible(true);
				desktopPane.add(df);
			}
		});
		mnTransactions.add(mntmDefaulters);
	}
}
