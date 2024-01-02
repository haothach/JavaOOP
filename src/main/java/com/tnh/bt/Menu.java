package com.tnh.bt;

public class Menu {

	private QuanLyKhachHang ql = new QuanLyKhachHang();
	

	public void menuTaiKhoan() {
		System.out.println("=======CHON LOAI TAI KHOAN========");
		System.out.println("1. Tai khoan admin");
		System.out.println("2. Tai khoan khach hang");
		System.out.println("0. Ket thuc chuong trinh");
		System.out.print("Chon: ");
	}

	public void menuAdmin() {
		System.out.println("=======QUAN LY KHACH HANG========");
		System.out.println("1. Them khach hang");
		System.out.println("2. Xoa khach hang");
		System.out.println("3. Tim khach hang");
		System.out.println("4. Hien thi danh sach khach hang");
		System.out.println("0. Thoat");
		System.out.print("Chon: ");

	}

	public void menuKhachHang() {
		System.out.println("================NGAN HANG DH MO===================");
		System.out.println("1. Mo them tai khoan co ky han.");
		System.out.println("2. Nap tien");
		System.out.println("3. Rut tien");
		System.out.println("0. Thoat");
		System.out.print("Vui long lua chon chuc nang: ");
	}

	public void chonLoaiTaiKhoan() {
		int choice;
		do {
			menuTaiKhoan();
			choice = Integer.parseInt(CauHinh.sc.nextLine());
			switch (choice) {
			case 1:
				ql.adminDangNhap();
				chucNangAdmin();
				break;
			case 2:
				KhachHang a = new KhachHang();
				a = ql.khachHangDangNhap();
				chucNangKhachHang(a);
				break;
			case 0:
				System.out.println("Cam on ban da su dung chuong trinh.");
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
			}

		} while (choice != 0);
	}

	public void chucNangAdmin() {
		int choice;
		do {
			menuAdmin();
			choice = Integer.parseInt(CauHinh.sc.nextLine());
			switch (choice) {
			case 1:
				KhachHang a = new KhachHang();
				a.moTk();
				ql.them(a);
				break;
			case 2:
				ql.xoa();
				break;
			case 3:
				break;
			case 4:
				ql.output();
			case 0:
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
			}
		} while (choice != 0);
	}

	public void chucNangKhachHang(KhachHang a) {
		int choice;
		do {
			menuKhachHang();
			choice = Integer.parseInt(CauHinh.sc.nextLine());
			switch (choice) {
			case 1:
				System.out.println(a.getTen());
				break;
			case 2:
				break;
			case 0:
				System.out.println("Cam on ban da su dung chuong trinh.");
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
			}

		} while (choice != 0);
	}

}
