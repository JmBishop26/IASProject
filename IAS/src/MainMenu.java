import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class MainMenu {

	JFrame menu;
	private JLabel lblName;
	private JLabel lblJob, Job;
	String name, job;
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/technovation";
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("SELECT * from employee WHERE employee_num = ?");
			ps.setInt(1, Login.id);
			
			rs = ps.executeQuery();
			rs.next();
			name = rs.getString("employee_name");
			job = rs.getString("job");
			
		}catch(Exception e) {
			
		}
		menu = new JFrame();
		menu.setResizable(false);
		menu.setTitle("Technovation - Main Menu");
		menu.setBounds(100, 100, 1280, 720);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				ImageIcon image = new ImageIcon("img\\bg.png");
				Image bg = image.getImage();

				g.drawImage(bg, 0, 0, 1280, 720, menu);
			}
		};
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainPanel.setBounds(0, 0, 1264, 681);
		menu.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		lblName = new JLabel(""+name);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblName.setBounds(70, 97, 447, 44);
		mainPanel.add(lblName);
		
		lblJob = new JLabel("Job:");
		lblJob.setForeground(Color.BLACK);
		lblJob.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblJob.setBounds(70, 152, 51, 33);
		mainPanel.add(lblJob);
		
		Job = new JLabel(""+job);
		Job.setForeground(Color.BLACK);
		Job.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		Job.setBounds(120, 153, 157, 33);
		mainPanel.add(Job);
		


		JButton btnCashier = new JButton("");
		btnCashier.setIcon(new ImageIcon("C:\\Users\\JM Obispo\\git\\IASProject\\IAS\\img\\cashier.png"));
		btnCashier.setBounds(181, 225, 287, 289);
		mainPanel.add(btnCashier);
		
		JButton btnInventory = new JButton("");
		btnInventory.setIcon(new ImageIcon("C:\\Users\\JM Obispo\\git\\IASProject\\IAS\\img\\inventory.png"));
		btnInventory.setBounds(495, 225, 287, 289);
		mainPanel.add(btnInventory);
		
		JButton btnAdmin = new JButton("");
		btnAdmin.setIcon(new ImageIcon("C:\\Users\\JM Obispo\\git\\IASProject\\IAS\\img\\admin.png"));
		btnAdmin.setBounds(809, 225, 287, 289);
		mainPanel.add(btnAdmin);
		
		
		JButton btnLogout = new JButton("");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int logout = JOptionPane.showConfirmDialog(menu, "Do you really want to Log Out?", "Technovation Log Out", JOptionPane.YES_NO_OPTION);
				if(logout==0) {
						menu.dispose();
						Login out = new Login();
						out.login.setVisible(true);
				}
			}
		});
		btnLogout.setIcon(new ImageIcon("img\\switch.png"));
		btnLogout.setBounds(1164, 31, 65, 62);
		mainPanel.add(btnLogout);
		

	}
}
