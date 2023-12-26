package com.tnh.bt;

public class Test {
	public static void main(String[] args) throws Exception {
		KhachHang k = new KhachHang();
		TaiKhoanKhongKyHan t = new TaiKhoanKhongKyHan(500000);
		k.setTkKhongKyHan(t);
		
		KhachHang k2 = new KhachHang();
		TaiKhoanKhongKyHan t2 = new TaiKhoanKhongKyHan(400000);
//		System.out.println(k.getTkKhongKyHan().laiSuat);
		TaiKhoanCoKyHan t1 = new TaiKhoanCoKyHan(5000000, KyHan.MOT_NAM);
//		TaiKhoanCoKyHan t2 = new TaiKhoanCoKyHan(70000, KyHan.MOT_THANG);
		
		QuanLyKhachHang ds = new QuanLyKhachHang();
		ds.adminDangNhap();
		ds.them(k);
		ds.them(k2);
//		ds.getDs().get(0).tkKhongKyHanOutput();
		System.out.println("ma: " + k.getMaKH());
		KhachHang s = ds.khachHangDangNhap();

		s.output();
	}
}
