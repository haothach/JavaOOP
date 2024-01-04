package com.tnh.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLyKhachHang {
	private AdminDangNhap ad;

	private List<KhachHang> ds = new ArrayList<>();

	public boolean isAdmin() {
		return ad.isDangNhap();
	}

	public void adminDangNhap() {
		System.out.println("\n=====NHAP TAI KHOAN ADMIN=====");
		System.out.print("Tai khoan: ");
		String admin = CauHinh.sc.nextLine();
		System.out.print("Mat khau: ");
		String mk = CauHinh.sc.nextLine();
		this.ad = new AdminDangNhap(admin, mk);
		if (isAdmin()) {
			System.out.println("Dang nhap thanh cong voi quyen quan tri!!!\n");
		} else {
			System.out.println("Dang nhap that bai. Vui long kiem tra lai ten dang nhap va mat khau!!!\n");
		}
	}

	public void them(KhachHang a) {
		if (isAdmin()) {
			this.ds.add(a);
			System.out.println("Da them khach hang vao danh sach!!!\n");
		} else {
			System.out.println("Chua truy cap quyen quan tri!!!n");
		}
	}

	public void xoa() {
		if (isAdmin()) {
			System.out.print("Nhap tai khoan: ");
			String tk = CauHinh.sc.nextLine();
			boolean removed = this.ds.removeIf(k -> k.getMaKH().equals(tk));
			if (removed) {
				System.out.printf("Da xoa khach hang co ma %s!!!\n", tk);
			} else {
				System.out.printf("Khong tim thay khach hang co ma %s\n", tk);
			}
		} else {
			System.out.println("Chua truy cap quyen quan tri!!!\n");
		}
	}

	public KhachHang khachHangDangNhap() {
		System.out.print("Nhap ten tai khoan: ");
		String ten = CauHinh.sc.nextLine();
		System.out.print("Nhap mat khau: ");
		String matKhau = CauHinh.sc.nextLine();
		for (KhachHang x : ds) {
			if (x.getTkDangNhap().getTenDangNhap().equals(ten) && x.getTkDangNhap().getMatKhau().equals(matKhau)) {
				System.out.println("DANG NHAP THANH CONG!!!\n");
				return x;
			}
		}
		if (ds.size() == 0) {
			System.out.println("Danh sach khach hang trong!!!\n");
			return null;
		}
		System.out.println("Vui long kiem tra lai ten hoac mat khau!!!");
		return null;
	}

	public void output() {
		if (ds.size() != 0)
			for (KhachHang x : ds) {
				x.output();
			}
		else
			System.out.println("Danh sach khach hang trong!!!\n");
	}

	public void tinhTienLai(String stk) {
		KhachHang a = this.ds.stream().filter(h -> h.getMaKH().equals(stk)).findFirst().orElse(null);
		if (a != null) {
			double laiSuatKhongKyHan = a.getTkKhongKyHan().getLaiSuat();
			List<Double> laiSuatCoKyHan = new ArrayList<>();
			for (int i = 0; i < a.getTkCoKyhan().size(); i++) {
				laiSuatCoKyHan.add(a.getTkCoKyhan().get(i).getLaiSuat());

			}
			int choice;
			do {
				System.out.println("\nTAI KHOAN CAN TINH LAI SUAT");
				System.out.println("1. Lai suat tai khoan chinh");
				System.out.println("2. Lai suat tai khoan co ky han");
				System.out.println("0. Thoat !");
				System.out.print("Vui long lua chon chuc nang: ");
				choice = Integer.parseInt(CauHinh.sc.nextLine());
				switch (choice) {
				case 1:
					System.out.printf("\nLai suat tai khoan chinh la: %.1f\n", laiSuatKhongKyHan);
					break;
				case 2:
					if (laiSuatCoKyHan.size() > 0) {
						for (int i = 0; i < laiSuatCoKyHan.size(); i++) {
							System.out.printf("\nLai suat tai khoan thu %d la: %.1f\n", (i + 1), laiSuatCoKyHan.get(i));
						}
					} else
						System.out.println("\nBan khong co tai khoan co ky han nao!!!\n");
					break;
				case 0:
					break;
				default:
					System.out.println("Vui long nhap lai lua chon!");
				}
			} while (choice != 0);
		} else
			System.out.println("Khong tim thay khach hang!!!\n");
	}

	public List<KhachHang> traCuuTheoHoTen(String kw) {
		return this.ds.stream().filter(h -> h.getTen().toLowerCase().contains(kw.toLowerCase()))
				.collect(Collectors.toList());
	}

	public KhachHang traCuuTheoMa(String ma) {
		return this.ds.stream().filter(h -> h.getMaKH().equals(ma)).findFirst().orElse(null);
	}

	public List<TaiKhoan> traCuuDsTk(String ma) {
		KhachHang o = this.ds.stream().filter(h -> h.getMaKH().equals(ma)).findFirst().orElse(null);
		if(o != null) {
			System.out.println("\nDANH SACH TAI KHOAN\n");
			List<TaiKhoan> tk = new ArrayList<TaiKhoan>();
			tk.add(o.getTkKhongKyHan());
			for (TaiKhoanCoKyHan x : o.getTkCoKyhan()) {
				tk.add(x);
			}
			for (TaiKhoan x : tk) {
				x.output();
			}
			return tk;
		}
		else System.out.println("Khong tim thay khach hang!!!");
		return null;
	}

	public void sapXep() {
		this.ds.sort(Comparator.comparing(KhachHang::tinhTong).reversed());
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