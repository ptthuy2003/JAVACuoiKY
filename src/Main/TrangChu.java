package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.xdevapi.Table;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TrangChu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static Integer ID = 5;
	private Vector dataHoaDon, cotHoaDon;
	private Connection  connection;
	private DefaultTableModel modelHoaDon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TrangChu frame = new TrangChu(String.valueOf(ID));
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	
	
	public String getNameKhachHang(int ID_KhachHang) {
		String Name = null;
		try {
			String sql = "SELECT * FROM khachhang WHERE id = ?";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, ID_KhachHang);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Name= rs.getString(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Name;
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
	
	public TrangChu(String id2) {
		ID = Integer.parseInt(id2);
		System.out.println(ID);
		connectDataBase();
		layHoaDonKhachHang();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 86, 738, 236);
		contentPane.add(scrollPane);
		
		table = new JTable(modelHoaDon);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Xem Hóa Đơn");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(24, 366, 172, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(576, 366, 172, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Xin chào " + getNameKhachHang(ID));
		lblNewLabel.setBounds(10, 50, 172, 19);
		contentPane.add(lblNewLabel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel tm = table.getModel();
				String Id = (String)tm.getValueAt(table.getSelectedRow(), 0);
				GDKhachHang gdKhachHang = new GDKhachHang(Id);
				gdKhachHang.setVisible(true);
			}
		});
		
	}
	public void layHoaDonKhachHang() {
		dataHoaDon = new Vector();
		String sql= "SELECT* FROM hoadon where 	ID_KhachHang = ?;";
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, ID);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				Vector t = new Vector();
				t.add(rs.getString(1));
				t.add(rs.getDouble(2));
				t.add(rs.getDouble(3));
				t.add(rs.getString(4));
				t.add(rs.getString(5));
				t.add(rs.getString(6));
				dataHoaDon.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cotHoaDon= new Vector();
		cotHoaDon.add("Mã hoá đơn");
		cotHoaDon.add("Số điện ");
		cotHoaDon.add(" Tiền điện ");
		cotHoaDon.add("Thời gian ");
		cotHoaDon.add("Tình trạng");
		cotHoaDon.add("Ngày thanh toán ");
		modelHoaDon = new DefaultTableModel(dataHoaDon, cotHoaDon);
		
	}
	
}
