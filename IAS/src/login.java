import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class login {

	JFrame Login;
	private JTextField username;
	private JPasswordField password;
	
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
					login window = new login();
					window.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	@SuppressWarnings("serial")
	private void initialize() {
		Login = new JFrame();
		Login.setTitle("Technovation Cashier System");
		Login.setBounds(100, 100, 1280, 720);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.getContentPane().setLayout(null);
		
		JPanel content = new JPanel(){
			public void paintComponent(Graphics g) {
				ImageIcon image = new ImageIcon("bg.jpg");
				Image bg = image.getImage();

				g.drawImage(bg, 0, 0, 1280, 720, Login);
			}
		};
		content.setBounds(0, 0, 1264, 681);
		Login.getContentPane().add(content);
		content.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblUsername.setBounds(460, 287, 158, 34);
		content.add(lblUsername);
		
		username = new JTextField();
		username.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		username.setBounds(460, 325, 349, 34);
		content.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblPassword.setBounds(459, 370, 172, 34);
		content.add(lblPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		password.setBounds(460, 406, 349, 34);
		content.add(password);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					connect = DriverManager.getConnection(con,user, pass);
                    char[] pass = password.getPassword();
                    String passString = new String(pass);
					ps = connect.prepareStatement("SELECT * from employee WHERE username=? AND password=?");
					
					ps.setString(1, username.getText());
					ps.setString(2, passString);
					rs = ps.executeQuery();
						
						if(username.getText().equals("")||passString.equals("")) {
							JOptionPane.showMessageDialog(Login,"Username/Password are Required to Log In","Credentials Required",JOptionPane.ERROR_MESSAGE);
						}
						else if (rs.next() == false) {
	                       JOptionPane.showMessageDialog(Login,"Username/Password is Incorrect","Log In Error",JOptionPane.ERROR_MESSAGE);
	                       username.setText("");
	                       password.setText("");
	                    }
						else {                   
	                            JOptionPane.showMessageDialog(Login,"Login credentials matched","Log In Succesful",JOptionPane.INFORMATION_MESSAGE);
		                        username.setText("");
		                        password.setText("");
	                         }                         
			        }
						catch(ClassNotFoundException | SQLException e1) {
				}
			}
		});
		
		btnLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnLogin.setBounds(493, 462, 129, 34);
		content.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(Login, "Do you really want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(input==0) {
					Login.dispose();
				}
			}
		});
		btnExit.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnExit.setBounds(650, 462, 129, 34);
		content.add(btnExit);
		
		
	}
}

