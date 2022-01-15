package Class;

public class TienDien {
		private Integer ID;
		private String MaHoaDon;
		private Double SoDien;
		private Double TienDien;
		private String ThoiGian;
		private String TinhTrang;
		private String NgayThanhToan;
		private String ID_KhachHang;
		
		public TienDien(Integer iD, String maHoaDon, Double soDien, Double tienDien, String thoiGian, String tinhTrang,
				String ngayThanhToan, String iD_KhachHang) {
			super();
			ID = iD;
			MaHoaDon = maHoaDon;
			SoDien = soDien;
			TienDien = tienDien;
			ThoiGian = thoiGian;
			TinhTrang = tinhTrang;
			NgayThanhToan = ngayThanhToan;
			ID_KhachHang = iD_KhachHang;
		}

		public TienDien() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getID() {
			return ID;
		}

		public void setID(Integer iD) {
			ID = iD;
		}

		public String getMaHoaDon() {
			return MaHoaDon;
		}

		public void setMaHoaDon(String maHoaDon) {
			MaHoaDon = maHoaDon;
		}

		public Double getSoDien() {
			return SoDien;
		}

		public void setSoDien(Double soDien) {
			SoDien = soDien;
		}

		public Double getTienDien() {
			return TienDien;
		}

		public void setTienDien(Double tienDien) {
			TienDien = tienDien;
		}

		public String getThoiGian() {
			return ThoiGian;
		}

		public void setThoiGian(String thoiGian) {
			ThoiGian = thoiGian;
		}

		public String getTinhTrang() {
			return TinhTrang;
		}

		public void setTinhTrang(String tinhTrang) {
			TinhTrang = tinhTrang;
		}

		public String getNgayThanhToan() {
			return NgayThanhToan;
		}

		public void setNgayThanhToan(String ngayThanhToan) {
			NgayThanhToan = ngayThanhToan;
		}

		public String getID_KhachHang() {
			return ID_KhachHang;
		}

		public void setID_KhachHang(String iD_KhachHang) {
			ID_KhachHang = iD_KhachHang;
		}
		
		 
}
