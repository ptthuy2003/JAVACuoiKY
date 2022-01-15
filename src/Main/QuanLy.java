package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import Class.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
public class QuanLy extends JFrame {

	private JPanel contentPane;
	private JTable tableHoaDon;
	private JTextField seacrhTF;
	private JTextField TftienDien;
	private JTextField tfsoDien;
	private JTextField tfmaHoaDon;
	private JTextField tfthoigian;
	private JTextField tfngayThanhToan;
	private JTextField tftimid;
	private JTextField tfID;
	private JTextField tfTen;
	private JTextField tfDiaChi;
	private JTextField tfSDT;
	private JTextField tfMK;
	private JTextField tfEmail;
	private JComboBox comboBoxtinhtrang;
	private Vector dataHoaDon, cotHoaDon;
	private Connection connection;
	private DefaultTableModel modelHoaDon;
	private Vector dataKH,cotKH, IdKhachHang;
	private DefaultTableModel modelKhachHang;
	private JTable tableKhachhang;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy frame = new QuanLy();
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
	
	public void getDataHoaDon() {
		dataHoaDon = new Vector();
		String sql= "SELECT* FROM hoadon";
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				Vector t = new Vector();
				t.add(rs.getString(1));
				t.add(rs.getDouble(2));
				t.add(rs.getDouble(3));
				t.add(rs.getString(4));
				t.add(rs.getString(5));
				t.add(rs.getString(6));
				t.add(rs.getString(7));
				t.add(getNameKhachHang(rs.getString(7)));
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
		cotHoaDon.add("ID Khách hàng");
		cotHoaDon.add("Ten Khac Hang");
		modelHoaDon = new DefaultTableModel(dataHoaDon, cotHoaDon);
	}
	public void searchDataHoaDon(String Id) {
		dataHoaDon = new Vector();
		String sql= "SELECT* FROM hoadon where ID = ?;";
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, Id);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				Vector t = new Vector();
				t.add(rs.getString(1));
				t.add(rs.getDouble(2));
				t.add(rs.getDouble(3));
				t.add(rs.getString(4));
				t.add(rs.getString(5));
				t.add(rs.getString(6));
				t.add(rs.getString(7));
				t.add(getNameKhachHang(rs.getString(7)));
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
		cotHoaDon.add("ID Khách hàng");
		cotHoaDon.add("Ten Khach Hang");
		modelHoaDon = new DefaultTableModel(dataHoaDon, cotHoaDon);
	}
	public Integer layIdCuoiCung(String nameTable) {
		
		Integer Id = 0;
		Integer Idtemp = 0;
		String sql= "SELECT * FROM "+ nameTable +" ORDER BY ID DESC LIMIT 1";
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				Idtemp = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Idtemp != null) {
			Id = Idtemp + 1;
		}
		return Id ;
		
	}
	public String getNameKhachHang(String ID_KhachHang) {
		String Name = null;
		try {
			String sql = "SELECT * FROM khachhang WHERE id = ?";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, ID_KhachHang);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Name= rs.getString(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Name;
	}
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
	public void getdataKhachhang() {
		dataKH= new Vector();
		IdKhachHang = new Vector();
		String sql= "SELECT * FROM khachhang";
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				Vector t= new Vector();
				t.add(rs.getString(1));
				t.add(rs.getString(2));
				t.add(rs.getString(3));
				t.add(rs.getString(4));	
				t.add(rs.getString(5));
				t.add(rs.getString(6));
				IdKhachHang.add(rs.getString(1));
				dataKH.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		cotKH= new Vector();
		cotKH.add("ID");
		cotKH.add("Tên khách hàng ");
		cotKH.add(" Địa chỉ ");
		cotKH.add("SĐT"); 
		cotKH.add("Mat khau");
		cotKH.add("Email");
		modelKhachHang = new DefaultTableModel(dataKH,cotKH );
	}
	public void SearchKhachHang(String id) {
		dataKH= new Vector();
		IdKhachHang = new Vector();
		String sql= "SELECT * FROM khachhang where ID = ?";
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, id);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				Vector t= new Vector();
				t.add(rs.getString(1));
				t.add(rs.getString(2));
				t.add(rs.getString(3));
				t.add(rs.getString(4));	
				t.add(rs.getString(5));
				t.add(rs.getString(6));
				IdKhachHang.add(rs.getString(1));
				dataKH.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		cotKH= new Vector();
		cotKH.add("ID");
		cotKH.add("Tên khách hàng ");
		cotKH.add(" Địa chỉ ");
		cotKH.add("SĐT"); 
		cotKH.add("Mat khau");
		cotKH.add("Email");
		modelKhachHang = new DefaultTableModel(dataKH,cotKH );
		
	}
	
	public QuanLy() {
		connectDataBase();
		getDataHoaDon();
		getdataKhachhang();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 715);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 41, 216, 627);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_5_1 = new JPanel();
		
		panel_5_1.setBackground(new Color(60, 179, 113));
		panel_5_1.setBorder(null);
		panel_5_1.setBounds(10, 81, 196, 43);
		panel.add(panel_5_1);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBackground(new Color(60, 179, 113));
		panel_5_2.setBorder(null);
		panel_5_2.setBounds(10, 135, 196, 43);
		panel.add(panel_5_2);
		
		JPanel panel_5_2_1 = new JPanel();
		
		panel_5_2_1.setBackground(new Color(60, 179, 113));
		panel_5_2_1.setBorder(null);
		panel_5_2_1.setBounds(10, 188, 196, 43);
		panel.add(panel_5_2_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(null);
		panel_4.setBounds(233, 0, 770, 64);
		contentPane.add(panel_4);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(236, 41, 783, 627);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Hóa Đơn", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane PanelHoaDon = new JScrollPane();
		PanelHoaDon.setBackground(Color.WHITE);
		PanelHoaDon.setBounds(10, 10, 758, 264);
		panel_1.add(PanelHoaDon);
		
		tableHoaDon = new JTable(modelHoaDon);
		
		PanelHoaDon.setViewportView(tableHoaDon);
		
		seacrhTF = new JTextField();
		seacrhTF.setBounds(10, 298, 381, 48);
		panel_1.add(seacrhTF);
		seacrhTF.setColumns(10);
		
		JButton SearchBt = new JButton("Tìm Theo ID");
		
		SearchBt.setBounds(401, 297, 192, 48);
		panel_1.add(SearchBt);
		
		TftienDien = new JTextField();
		TftienDien.setEditable(false);
		TftienDien.setColumns(10);
		TftienDien.setBounds(534, 376, 234, 48);
		panel_1.add(TftienDien);
		
		tfsoDien = new JTextField();
		tfsoDien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.out.println("ok");
			}
		});
		tfsoDien.setColumns(10);
		tfsoDien.setBounds(272, 376, 234, 48);
		panel_1.add(tfsoDien);
		
		tfmaHoaDon = new JTextField();
		tfmaHoaDon.setEditable(false);
		tfmaHoaDon.setColumns(10);
		tfmaHoaDon.setBounds(10, 376, 234, 48);
		panel_1.add(tfmaHoaDon);
		
		tfthoigian = new JTextField();
		tfthoigian.setColumns(10);
		tfthoigian.setBounds(10, 453, 234, 48);
		panel_1.add(tfthoigian);
		
		tfngayThanhToan = new JTextField();
		tfngayThanhToan.setColumns(10);
		tfngayThanhToan.setBounds(534, 453, 234, 48);
		panel_1.add(tfngayThanhToan);
		
		JButton BtThem = new JButton("Thêm");
		BtThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		BtThem.setBounds(272, 529, 138, 50);
		panel_1.add(BtThem);
		
		JButton btXoa = new JButton("Xoá");
		btXoa.setEnabled(false);
		
		btXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btXoa.setBounds(630, 529, 138, 50);
		panel_1.add(btXoa);
		
		JButton btSua = new JButton("Sửa");
		btSua.setEnabled(false);
		btSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btSua.setBounds(454, 529, 138, 50);
		panel_1.add(btSua);
		String dataCombo[] = { "Chưa thanh toán", "Đã thanh toán" };
		comboBoxtinhtrang = new JComboBox(dataCombo);
		comboBoxtinhtrang.setBackground(Color.WHITE);
		comboBoxtinhtrang.setBounds(272, 452, 234, 48);
		panel_1.add(comboBoxtinhtrang);
		
		JLabel lblNewLabel = new JLabel("Mã hoá đơn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 356, 171, 19);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Thời gian");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 434, 116, 13);
		panel_1.add(lblNewLabel_2);
		
		JButton btnTnhTin = new JButton("Tính tiền");
		
		btnTnhTin.setBounds(603, 298, 165, 48);
		panel_1.add(btnTnhTin);
		
		JLabel lblSin = new JLabel("Số điện");
		lblSin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSin.setBounds(272, 356, 171, 19);
		panel_1.add(lblSin);
		
		JLabel lblTinin = new JLabel("Tiền điện");
		lblTinin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTinin.setBounds(534, 355, 171, 19);
		panel_1.add(lblTinin);
		
		JLabel lblTnhTrng = new JLabel("Tình trạng");
		lblTnhTrng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnhTrng.setBounds(272, 431, 171, 19);
		panel_1.add(lblTnhTrng);
		
		JLabel lblNgyThanhTon = new JLabel("Ngày thanh toán");
		lblNgyThanhTon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgyThanhTon.setBounds(534, 431, 171, 19);
		panel_1.add(lblNgyThanhTon);
		
		JLabel lblIdKhchHng = new JLabel("ID Khách hàng");
		lblIdKhchHng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdKhchHng.setBounds(10, 511, 171, 19);
		panel_1.add(lblIdKhchHng);
		
		JComboBox comboBoxKhachHang = new JComboBox(IdKhachHang);
		comboBoxKhachHang.setBackground(Color.WHITE);
		comboBoxKhachHang.setBounds(10, 529, 234, 48);
		panel_1.add(comboBoxKhachHang);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Khách Hàng", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btTimKiem = new JButton("Tìm");
		
		btTimKiem.setBounds(576, 297, 192, 48);
		panel_2.add(btTimKiem);
		
		tftimid = new JTextField();
		tftimid.setColumns(10);
		tftimid.setBounds(10, 298, 556, 48);
		panel_2.add(tftimid);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		tfID.setBounds(10, 363, 234, 48);
		panel_2.add(tfID);
		System.out.println("sdfdsf");
		tfTen = new JTextField();
		tfTen.setColumns(10);
		tfTen.setBounds(272, 363, 234, 48);
		panel_2.add(tfTen);
		
		tfDiaChi = new JTextField();
		tfDiaChi.setColumns(10);
		tfDiaChi.setBounds(534, 363, 234, 48);
		panel_2.add(tfDiaChi);
		
		tfSDT = new JTextField();
		tfSDT.setColumns(10);
		tfSDT.setBounds(10, 436, 234, 48);
		panel_2.add(tfSDT);
		
		tfMK = new JTextField();
		tfMK.setColumns(10);
		tfMK.setBounds(272, 436, 234, 48);
		panel_2.add(tfMK);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(534, 436, 234, 48);
		panel_2.add(tfEmail);
		
		JButton BtThem2 = new JButton("New button");
		
		BtThem2.setBounds(10, 508, 138, 50);
		panel_2.add(BtThem2);
		
		JButton BtSua2 = new JButton("Sửa");
		
		BtSua2.setBounds(192, 508, 138, 50);
		panel_2.add(BtSua2);
		
		JButton btxoaKhachHang = new JButton("New button");
		
		btxoaKhachHang.setBounds(368, 508, 138, 50);
		panel_2.add(btxoaKhachHang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 758, 235);
		panel_2.add(scrollPane);
		
		tableKhachhang = new JTable(modelKhachHang);
		tableKhachhang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel tm = tableKhachhang.getModel();
				tfID.setText((String)tm.getValueAt(tableKhachhang.getSelectedRow(), 0));
				tfTen.setText((String)tm.getValueAt(tableKhachhang.getSelectedRow(), 1));
				tfDiaChi.setText((String)tm.getValueAt(tableKhachhang.getSelectedRow(), 2));
				tfSDT.setText((String)tm.getValueAt(tableKhachhang.getSelectedRow(), 3));
				tfMK.setText((String)tm.getValueAt(tableKhachhang.getSelectedRow(), 4));
				tfEmail.setText((String)tm.getValueAt(tableKhachhang.getSelectedRow(), 5));
			}
		});
		scrollPane.setViewportView(tableKhachhang);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab(":>", null, panel_3, null);
		tableHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btSua.setEnabled(true);
				btXoa.setEnabled(true);
				TableModel tm = tableHoaDon.getModel();
				tfmaHoaDon.setText((String)tm.getValueAt(tableHoaDon.getSelectedRow(), 0));
				tfsoDien.setText(Double.toString((double) tm.getValueAt(tableHoaDon.getSelectedRow(), 1)));
				TftienDien.setText(Double.toString((double) tm.getValueAt(tableHoaDon.getSelectedRow(), 2)));
				tfthoigian.setText((String)tm.getValueAt(tableHoaDon.getSelectedRow(), 3));
				tfngayThanhToan.setText((String)tm.getValueAt(tableHoaDon.getSelectedRow(), 5));
				comboBoxtinhtrang.setSelectedItem((String)tm.getValueAt(tableHoaDon.getSelectedRow(), 4));
				comboBoxKhachHang.setSelectedItem((String)tm.getValueAt(tableHoaDon.getSelectedRow(), 6));
				
				
			}
		});
		btnTnhTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double soDien = Double.parseDouble(tfsoDien.getText());
				TftienDien.setText((Double.toString((double)Math.round(TinhTienDien(soDien)))));
				
			}
		});
		btSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double soDien = Double.parseDouble(tfsoDien.getText());
				TftienDien.setText((Double.toString((double)Math.round(TinhTienDien(soDien)))));
				try {
					String sql1 = "UPDATE hoadon set SoDien= ?, TienDien= ?, ThoiGian=?, TinhTrang=?, NgayThanhToan = ?, ID_Khachhang=?  WHERE ID=?";
					PreparedStatement stm = connection.prepareStatement(sql1);
					stm.setString(1, tfsoDien.getText());
					stm.setString(2, TftienDien.getText());
					stm.setString(3, tfthoigian.getText());
					stm.setString(4, comboBoxtinhtrang.getSelectedItem().toString());
					stm.setString(5, tfngayThanhToan.getText());
					stm.setString(6, comboBoxKhachHang.getSelectedItem().toString());
					stm.setString(7, tfmaHoaDon.getText());
					stm.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				getDataHoaDon();
				tableHoaDon.setModel(modelHoaDon);
				btSua.setEnabled(false);
				btXoa.setEnabled(false);
				
			}
		});
		BtThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maHD= tfmaHoaDon.getText();
				double sodien = Double.parseDouble(tfsoDien.getText());
				double tiendien= Double.parseDouble(TftienDien.getText());
				String thoigian = tfthoigian.getText();
				String tinhtrang= (String) comboBoxtinhtrang.getSelectedItem();
				String ngaythanhtoan= tfngayThanhToan.getText();
				String idKh= comboBoxKhachHang.getSelectedItem().toString();
				String sql= "INSERT INTO hoadon(ID, SoDien,TienDien, ThoiGian, TinhTrang, NgayThanhToan,ID_KhachHang)"+" VALUES(?,?,?,?,?,?,?)";
				
				try {
					PreparedStatement stm = connection.prepareStatement(sql);
					stm.setInt(1, layIdCuoiCung("hoadon"));
					stm.setDouble(2,sodien);
					stm.setDouble(3,tiendien);
					stm.setString(4, thoigian);
					stm.setString(5, tinhtrang);
					stm.setString(6, ngaythanhtoan);
					stm.setString(7, idKh);
					stm.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
						System.out.println(e1);
				}
				
				getDataHoaDon();
				tableHoaDon.setModel(modelHoaDon);
			}
		});
		btXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableHoaDon.getSelectedRow();
				String Id = tfmaHoaDon.getText();
				PreparedStatement stm;
				try {
					String sql = "DELETE FROM hoadon WHERE ID = '" + Id + "'";
					stm = connection.prepareStatement(sql);
					stm.execute();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
				}
				btSua.setEnabled(false);
				btXoa.setEnabled(false);
				getDataHoaDon();
				tableHoaDon.setModel(modelHoaDon);
			}
		});
		
		BtThem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id= tfID.getText();
				String ten= tfTen.getText();
				String diachi= tfDiaChi.getText();
				String sdt= tfSDT.getText();
				String tendn = tfMK.getText();
				String email= tfEmail.getText();
				String sql= "INSERT INTO khachhang(ID, Ten, DiaChi, SDT,MatKhau,Email)"+" VALUES(?,?,?,?,?,?)";
				
				try {
					PreparedStatement stm = connection.prepareStatement(sql);
					stm.setInt(1, layIdCuoiCung("khachhang"));
					stm.setString(2,ten);
					stm.setString(3,diachi);
					stm.setString(4, sdt);
					stm.setString(5, tendn);
					stm.setString(6, email);
					stm.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
				
				}
				
				getdataKhachhang();
				getDataHoaDon();
				tableKhachhang.setModel(modelKhachHang);
				DefaultComboBoxModel model = new DefaultComboBoxModel(IdKhachHang);
				comboBoxKhachHang.setModel(model);
				
				
			}
		});
		SearchBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IdSearch = seacrhTF.getText();
				if(IdSearch.length() > 0 ) {
					searchDataHoaDon(IdSearch);
					tableHoaDon.setModel(modelHoaDon);
				}
				else {
					getDataHoaDon();
					tableHoaDon.setModel(modelHoaDon);
				}
				
				
			}
		});
		
		btxoaKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableKhachhang.getSelectedRow();
				String Id = tfID.getText();
				PreparedStatement stm;
				try {
					String sql = "DELETE FROM khachhang WHERE ID = '" + Id + "'";
					stm = connection.prepareStatement(sql);
					stm.execute();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
				}
				
				getdataKhachhang();
				getDataHoaDon();
				tableKhachhang.setModel(modelKhachHang);
				DefaultComboBoxModel model = new DefaultComboBoxModel(IdKhachHang);
				comboBoxKhachHang.setModel(model);
			}
		});
		BtSua2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql1 = "UPDATE  khachhang set Ten = ?, DiaChi = ?, SDT = ?, MatKhau = ?, Email = ?   WHERE ID= ?";
					PreparedStatement stm = connection.prepareStatement(sql1);
					stm.setString(1, tfTen.getText());
					stm.setString(2, tfDiaChi.getText());
					stm.setString(3, tfSDT.getText());
					stm.setString(4, tfMK.getText());
					stm.setString(5, tfEmail.getText());
					stm.setString(6, tfID.getText());
					stm.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
				}
				getdataKhachhang();
				getDataHoaDon();
				tableKhachhang.setModel(modelKhachHang);
				DefaultComboBoxModel model = new DefaultComboBoxModel(IdKhachHang);
				comboBoxKhachHang.setModel(model);
			}
		});
		btTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Id = tftimid.getText();
				
				if(Id.length()>0) {
					SearchKhachHang(Id);
					tableKhachhang.setModel(modelKhachHang);
				}else {
					getdataKhachhang();
					tableKhachhang.setModel(modelKhachHang);
				}
			}
		});
		panel_5_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		panel_5_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		panel_5_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		
	}
	
	public Double TinhTienDien(Double soDien) {
		Double tiendien = null;
		if(soDien>= 401.0) {
			tiendien = (50.0 * 1678.0)+(50.0 * 1734.0)+(100.0 * 2014.0)+(100.0 * 2536.0)+(100.0 * 2834.0)+((soDien-400) * 2927.0);
			return tiendien;
		}
		 if(soDien>= 301 && soDien<401) {
			tiendien = (50.0 * 1678.0)+(50.0 * 1734.0)+(100.0 * 2014.0)+(100.0 * 2536.0)+((soDien-300) * 2834.0);
			return tiendien;
		}
		 if(soDien>= 201 && soDien<301) {
			tiendien = (50.0 * 1678.0)+(50.0 * 1734.0)+(100.0 * 2014.0)+((soDien-200) * 2536.0);
			return tiendien;
		}
		 if(soDien>= 101 && soDien<201) {
			tiendien = (50.0 * 1678.0)+(50.0 * 1734.0)+((soDien-100) * 2014.0);
			return tiendien;
		}
		if(soDien>= 51 && soDien<101) {
			tiendien = (50.0 * 1678.0)+((soDien-50) * 1734.0);
			return tiendien;
		}
		if(soDien>= 0 && soDien<51) {
			tiendien = (soDien * 1678.0);
			return tiendien;
		}
		return tiendien;
	}
}
