package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GDKhachHang extends JFrame {

	private JPanel contentPane;
	private Connection conn;
	private JLabel IDKhachHang;
	private JLabel Thoigian;
	private JLabel Tinhtrang;
	private JLabel Tiendien;
	private JLabel MaHoaDon;
	private JLabel SoDien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GDKhachHang frame = new GDKhachHang("");
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void getdataHoaDon(String id) {
		String sql = "SELECT ID, SoDien,TienDien,ThoiGian,TinhTrang ,ID_KhachHang FROM hoadon Where ID = ? LIMIT 1";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {

				MaHoaDon.setText(rs.getString(1));
				SoDien.setText(String.valueOf(rs.getDouble(2)));
				Tiendien.setText(String.valueOf(rs.getDouble(3)));
				Thoigian.setText(rs.getString(4));
				Tinhtrang.setText(rs.getString(5));
				IDKhachHang.setText(rs.getString(6));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String  LayID (String s) {
		String x = MaHoaDon.getText();
		
		
		return x;
	}

	public GDKhachHang(String id) {
		connectDataBase();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 516, 74);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Ho\u00E1 \u0111\u01A1n");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(118, 10, 308, 54);
		panel.add(lblNewLabel_5);

		JLabel MaHD = new JLabel("M\u00E3 Ho\u00E1 \u0111\u01A1n :");
		MaHD.setFont(new Font("Tahoma", Font.BOLD, 13));
		MaHD.setBounds(36, 106, 86, 23);
		contentPane.add(MaHD);

		JLabel lblIdKhchHng = new JLabel("ID Kh\u00E1ch h\u00E0ng :");
		lblIdKhchHng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdKhchHng.setBounds(36, 153, 111, 23);
		contentPane.add(lblIdKhchHng);

		JLabel lblSinS = new JLabel("S\u1ED1 \u0111i\u1EC7n s\u1EED d\u1EE5ng :");
		lblSinS.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSinS.setBounds(36, 203, 123, 23);
		contentPane.add(lblSinS);

		JLabel lblThiGian = new JLabel("Th\u1EDDi gian: ");
		lblThiGian.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThiGian.setBounds(36, 246, 86, 23);
		contentPane.add(lblThiGian);

		MaHoaDon = new JLabel("9");
		MaHoaDon.setBounds(181, 106, 226, 23);
		contentPane.add(MaHoaDon);

		IDKhachHang = new JLabel("New label");
		IDKhachHang.setBounds(181, 154, 226, 23);
		contentPane.add(IDKhachHang);

		SoDien = new JLabel("New label");
		SoDien.setBounds(181, 204, 226, 23);
		contentPane.add(SoDien);

		JButton btnNewButton = new JButton("Thanh toán");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql1 = "UPDATE hoadon set TinhTrang=? WHERE ID=?";
					PreparedStatement stm = conn.prepareStatement(sql1);
					stm.setString(1, "Đã thanh toán");
					stm.setInt(2, Integer.parseInt(MaHoaDon.getText()));
					stm.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(155, 422, 186, 57);
		contentPane.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(143, 313, 217, 74);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		Tiendien = new JLabel("New label");
		Tiendien.setBounds(33, 10, 160, 54);
		panel_1.add(Tiendien);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(181, 322, 155, 54);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);

		Thoigian = new JLabel("New label");
		Thoigian.setBounds(181, 246, 179, 23);
		contentPane.add(Thoigian);

		JLabel TT = new JLabel("TinhTrang");
		TT.setFont(new Font("Tahoma", Font.BOLD, 13));
		TT.setBounds(36, 279, 86, 23);
		contentPane.add(TT);

		Tinhtrang = new JLabel("New label");
		Tinhtrang.setBounds(181, 279, 179, 23);
		contentPane.add(Tinhtrang);
		
		JButton btnNewButton_1 = new JButton("Đóng");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(395, 497, 85, 21);
		contentPane.add(btnNewButton_1);
		getdataHoaDon(id);
	}
}
