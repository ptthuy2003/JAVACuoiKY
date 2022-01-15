package Class;

public class KhachHang {
		private Integer Id;
		private String Ten;
		private String DiaChi;
		private String SDT;
		private String MatKhau;
		private String Email;
		public KhachHang(Integer id, String ten, String diaChi, String sDT, String matKhau, String email) {
			super();
			Id = id;
			Ten = ten;
			DiaChi = diaChi;
			SDT = sDT;
			MatKhau = matKhau;
			Email = email;
		}
		public KhachHang() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Integer getId() {
			return Id;
		}
		public void setId(Integer id) {
			Id = id;
		}
		public String getTen() {
			return Ten;
		}
		public void setTen(String ten) {
			Ten = ten;
		}
		public String getDiaChi() {
			return DiaChi;
		}
		public void setDiaChi(String diaChi) {
			DiaChi = diaChi;
		}
		public String getSDT() {
			return SDT;
		}
		public void setSDT(String sDT) {
			SDT = sDT;
		}
		public String getMatKhau() {
			return MatKhau;
		}
		public void setMatKhau(String matKhau) {
			MatKhau = matKhau;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		
}
