package com.tnh.bt;

public abstract class DangNhap {

	protected String tenDangNhap;

	protected String matKhau;


	public DangNhap(String tenDangNhap, String matKhau) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}

	public abstract boolean isDangNhap();

	public void dangXuat() {
		System.out.println("Dang xuat thanh cong\n");
	}
	
	public static boolean isMatKhauHopLe(String matKhau) {
        if (matKhau.length() < 8) {
            return false;
        }
        boolean coKyTuHoa = false;
        boolean coKyTuThuong = false;
        boolean coSo = false;
        for (int i = 0; i < matKhau.length(); i++) {
            char c = matKhau.charAt(i);
            if (Character.isUpperCase(c)) {
                coKyTuHoa = true;
            } else if (Character.isLowerCase(c)) {
                coKyTuThuong = true;
            } else if (Character.isDigit(c)) {
                coSo = true;
            } else {
                return false;
            }
        }
        return coKyTuHoa && coKyTuThuong && coSo;
    }

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	 
	
}