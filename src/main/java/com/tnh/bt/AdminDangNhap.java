package com.tnh.bt;

public class AdminDangNhap extends DangNhap {
	
	private final String ADMIN_USERNAME = "admin";
	private final String ADMIN_PASSWORD = "Admin1234";

	public AdminDangNhap(String tenDangNhap, String matKhau) {
		super(tenDangNhap, matKhau);
	}


	public boolean isDangNhap() {
		return this.getTenDangNhap().equals(ADMIN_USERNAME) && this.getMatKhau().equals(ADMIN_PASSWORD);
	}

}