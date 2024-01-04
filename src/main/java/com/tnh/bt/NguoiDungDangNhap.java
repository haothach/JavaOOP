package com.tnh.bt;


public class NguoiDungDangNhap extends DangNhap {

	public NguoiDungDangNhap(String tenDangNhap, String matKhau) {
		super(tenDangNhap, matKhau);
	}

	@Override
	public boolean isDangNhap() {
		return true;
	}


}
