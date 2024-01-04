package com.tnh.bt;

public abstract class DangNhap {

	private String tenDangNhap;

	private String matKhau;


	public DangNhap(String tenDangNhap, String matKhau) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}


	public void dangXuat() {
		System.out.println("Dang xuat thanh cong\n");
	}
	
	public static boolean isMatKhauHopLe(String matKhau) {
        if (matKhau.length() < 6) {
            return false;
        }
        
        for(int i = 0; i < matKhau.length(); i++) {
        	char c =matKhau.charAt(i);
        	if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
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