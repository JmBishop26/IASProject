import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminAccount {

	 JFrame accounts;
	 private JPanel panel;
	 private JButton btnNewEmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAccount window = new AdminAccount();
					window.accounts.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		accounts = new JFrame();
		accounts.setTitle("Technovation");
		accounts.setBounds(100, 100, 1280, 720);
		accounts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accounts.getContentPane().setLayout(null);
		
		 panel = new JPanel(){
			public void paintComponent(Graphics g) {
				ImageIcon image = new ImageIcon("img\\bg.png");
				Image bg = image.getImage();

				g.drawImage(bg, 0, 0, 1280, 720, panel);
			}
		};
		panel.setBounds(189, 0, 1075, 681);
		accounts.getContentPane().add(panel);
		panel.setVisible(false);
		panel.setLayout(null);
		
		JPanel side = new JPanel();
		side.setBounds(0, 0, 190, 681);
		accounts.getContentPane().add(side);
		side.setLayout(null);
		
		btnNewEmp = new JButton("<html><center><p>Register<br>New Employee</p></center></html>");
		btnNewEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		btnNewEmp.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		btnNewEmp.setForeground(Color.BLACK);
		btnNewEmp.setBounds(10, 98, 170, 55);
		side.add(btnNewEmp);
	}
}
