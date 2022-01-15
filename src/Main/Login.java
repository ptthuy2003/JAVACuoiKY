package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Class.*;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfEmai;
	private JPasswordField tfpassword;
	private JTextField emailKhachHang;
	private JPasswordField passKhachHang;
	private Connection connection;
	private Vector dataKH,cotKH;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void connectDataBase() {	
		try {
			 String userName = "root";
			   String password = "";
			   String url = "jdbc:mysql://localhost/csdlquanlydien";
			   Class.forName ("com.mysql.cj.jdbc.Driver");
			   connection = DriverManager.getConnection(url, userName, password);   
			   System.out.println("ok");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public Login() {
		connectDataBase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(10, 0, 785, 166);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("H\u1EC7 th\u1ED1ng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(205, 0, 372, 63);
		panel.add(lblNewLabel);
		
		JLabel lblQunLThu = new JLabel("Qu\u1EA3n l\u00FD thu v\u00E0 t\u00EDnh ti\u1EC1n \u0111i\u1EC7n");
		lblQunLThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLThu.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblQunLThu.setBounds(0, 60, 775, 63);
		panel.add(lblQunLThu);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 143, 785, 343);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panelKhachHang = new JPanel();
		panelKhachHang.setBackground(Color.PINK);
		panelKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		panelKhachHang.setBounds(136, 79, 206, 177);
		panel_1.add(panelKhachHang);
		panelKhachHang.setLayout(null);
		
		JPanel panelQuanLy = new JPanel();
		panelQuanLy.setBackground(Color.GREEN);
		panelQuanLy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		panelQuanLy.setBounds(429, 79, 206, 177);
		panel_1.add(panelQuanLy);
		panelQuanLy.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Email :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(185, 43, 71, 35);
		panel_2.add(lblNewLabel_2);
		
		tfEmai = new JTextField();
		tfEmai.setBounds(283, 45, 279, 35);
		panel_2.add(tfEmai);
		tfEmai.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("M\u1EADt kh\u1EA9u :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(185, 100, 78, 35);
		panel_2.add(lblNewLabel_2_1);
		
		tfpassword = new JPasswordField();
		tfpassword.setBounds(283, 90, 279, 39);
		panel_2.add(tfpassword);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hi\u1EC3n th\u1ECB m\u1EADt kh\u1EA9u");
		chckbxNewCheckBox.setBounds(283, 149, 115, 21);
		panel_2.add(chckbxNewCheckBox);
		
		JPanel panel_5_1_1_1 = new JPanel();
		panel_5_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		panel_5_1_1_1.setLayout(null);
		panel_5_1_1_1.setBackground(Color.PINK);
		panel_5_1_1_1.setBounds(0, 0, 54, 46);
		panel_2.add(panel_5_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("X");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(10, 10, 29, 19);
		panel_5_1_1_1.add(lblNewLabel_1_1_1_1);
		
		JButton btnNewButton_1 = new JButton("Đăng Nhập");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String email = tfEmai.getText();
			String mk = tfpassword.getText().toString();
			System.out.println(mk);
			if(email.equals("thuyxinhdep") == true && mk.equals("220603") == true)
			{
			 QuanLy ql = new QuanLy();
			 ql.setVisible(true);
			}else 
				JOptionPane.showMessageDialog(rootPane, "Ten đăng nhập hoặc mật khẩu không đúng");
		}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setBounds(283, 191, 279, 39);
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("Email :");
		lblNewLabel_2_2.setBounds(216, 69, 49, 17);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblNewLabel_2_2);
		
		emailKhachHang = new JTextField();
		emailKhachHang.setColumns(10);
		emailKhachHang.setBounds(275, 51, 279, 35);
		panel_3.add(emailKhachHang);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("M\u1EADt kh\u1EA9u :");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(192, 111, 78, 35);
		panel_3.add(lblNewLabel_2_1_1);
		
		passKhachHang = new JPasswordField();
		passKhachHang.setBounds(275, 107, 279, 39);
		panel_3.add(passKhachHang);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Hi\u1EC3n th\u1ECB m\u1EADt kh\u1EA9u");
		chckbxNewCheckBox_1.setBounds(275, 163, 115, 21);
		panel_3.add(chckbxNewCheckBox_1);
		
		JPanel panel_5_1_1 = new JPanel();
		panel_5_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		panel_5_1_1.setLayout(null);
		panel_5_1_1.setBackground(Color.GREEN);
		panel_5_1_1.setBounds(0, 0, 54, 46);
		panel_3.add(panel_5_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("X");
		lblNewLabel_1_1_1.setBounds(10, 10, 29, 19);
		panel_5_1_1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("\u0110\u0103ng Nh\u1EADp");
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(275, 190, 279, 39);
		panel_3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataKH= new Vector();
				String Id = null;
				String pass = null;
				
				try {
					String sql= "SELECT * FROM khachhang where 	Email = ?";
					PreparedStatement stm = connection.prepareStatement(sql);
					stm.setString(1, emailKhachHang.getText());
					ResultSet rs= stm.executeQuery();
					while(rs.next()) {
						Id = rs.getString(1);
						pass = rs.getString(5);
					}
					if(Id != null) {
						String pass1 = passKhachHang.getText().toString(); 
						System.out.println(pass1);
						System.out.println(pass);
						if(pass.equals(pass1) == true) {						
							TrangChu tc = new TrangChu(Id);
							System.out.println(Id);
							tc.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Sai pass");
					
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Tài khoản không tồn tại");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
				}	
			}
				
			
		});
	}
	
}
