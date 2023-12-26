package com.tnh.bt;

public class AdminDangNhap extends DangNhap {
	
	private static final String ADMIN_USERNAME = "admin";
	private static final String ADMIN_PASSWORD = "Admin1234";

	public AdminDangNhap(String tenDangNhap, String matKhau) {
		super(tenDangNhap, matKhau);
	}


	@Override
	public boolean isDangNhap() {
		return this.getTenDangNhap().equals(ADMIN_USERNAME) && this.getMatKhau().equals(ADMIN_PASSWORD);
		
	}

}