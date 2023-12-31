package com.tnh.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuanLyKhachHang {
	private AdminDangNhap ad;

	private List<KhachHang> ds = new ArrayList<KhachHang>();
	
	public boolean isAdmin() {
		return ad.isDangNhap();
	}

	public void adminDangNhap() {
		System.out.print("Nhap tai khoan: ");
		String admin = CauHinh.sc.nextLine();
		System.out.print("Nhap mat khau: ");
		String mk = CauHinh.sc.nextLine();
		this.ad = new AdminDangNhap(admin, mk);
		if (isAdmin()) {
			System.out.println("Dang nhap thanh cong voi quyen quan tri\n");
		} else {
			System.out.println("Dang nhap that bai. Vui long kiem tra lai ten dang nhap va mat khau.\n");
		}
	}

	public void them(KhachHang a) {
		if (isAdmin()) {
//			a.nhapKh();
			String user = a.getMaKH();
			String mk = "User" + a.getMaKH().substring(a.getMaKH().length() - 4);
			a.setTkDangNhap(new NguoiDungDangNhap(user, mk));

			this.ds.addAll(Arrays.asList(a));
			System.out.println("Da them khach hang vao danh sach.\n");
		} else {
			System.out.println("Chua truy cap quyen quan tri\n");
		}
	}

	public void xoa(KhachHang a) {
		if (isAdmin()) {
			this.ds.remove(a);
			System.out.println("Da xoa khach hang khoi danh sach\n");
		} else {
			System.out.println("Chua truy cap quyen quan tri\n");
		}
	}

	public KhachHang khachHangDangNhap() {
		System.out.print("Nhap ten tai khoan: ");
		String ten = CauHinh.sc.nextLine();
		System.out.print("Nhap mat khau: ");
		String matKhau = CauHinh.sc.nextLine();
		for (KhachHang x : ds) {
			if (x.getTkDangNhap().getTenDangNhap().equals(ten) && x.getTkDangNhap().getMatKhau().equals(matKhau)) {
				System.out.println("\n====DANG NHAP THANH CONG====");
				return x;
			}
		} 
		if(ds.size() == 0) {
			System.out.println("Danh sach khach hang trong\n");
			return null;
		}
		System.out.println("Vui long kiem tra lai ten hoac mat khau.");
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