package com.tnh.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuanLyKhachHang {
	private AdminDangNhap ad;

	private List<KhachHang> ds = new ArrayList<>();
	
	public boolean isAdmin() {
		return ad.isDangNhap();
	}

	public void adminDangNhap() {
		System.out.print("Nhập tài khoản: ");
		String admin = CauHinh.sc.nextLine();
		System.out.print("Nhập mật khẩu: ");
		String mk = CauHinh.sc.nextLine();
		this.ad = new AdminDangNhap(admin, mk);
		if (isAdmin()) {
			System.out.println("Đăng nhập thành công với quyền quản trị.\n");
		} else {
			System.out.println("Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.\n");
		}
	}

	public void them(KhachHang a) {
		if (isAdmin()) {
			a.nhapKh();
			String user = a.getMaKH();
			String mk = "User" + a.getMaKH().substring(a.getMaKH().length() - 4);
			a.setTkDangNhap(new NguoiDungDangNhap(user, mk));

			this.ds.addAll(Arrays.asList(a));
			System.out.println("Đã thêm khách hàng vào danh sách.\n");
		} else {
			System.out.println("Chưa truy cập quyền quản trị.\n");
		}
	}

	public void xoa(KhachHang a) {
		if (isAdmin()) {
			this.ds.remove(a);
			System.out.println("Đã xóa khách hàng khỏi danh sách.\n");
		} else {
			System.out.println("Chưa truy cập quyền quản trị.\n");
		}
	}

	public KhachHang khachHangDangNhap() {
		System.out.print("Nhập tên tài khoản: ");
		String ten = CauHinh.sc.nextLine();
		System.out.print("Nhập mật khẩu: ");
		String matKhau = CauHinh.sc.nextLine();
		for (KhachHang x : ds) {
			if (x.getTkDangNhap().getTenDangNhap().equals(ten) && x.getTkDangNhap().getMatKhau().equals(matKhau)) {
				return x;
			}
		} 
		if(ds.size() == 0) {
			System.out.println("Danh sách khách hàng trống.\n");
			return null;
		}
		System.out.println("Vui lòng kiểm tra lại tên hoặc mật khẩu.");
		return null;
	}



	public List<KhachHang> getDs() {
		return ds;
	}

	public void setDs(List<KhachHang> ds) {
		this.ds = ds;
	}

	public AdminDangNhap getAd() {
		return ad;
	}

	public void setAd(AdminDangNhap ad) {
		this.ad = ad;
	}

}