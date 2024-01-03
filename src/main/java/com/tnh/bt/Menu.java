package com.tnh.bt;

public class Menu {

	private QuanLyKhachHang ql = new QuanLyKhachHang();

	public void menuTaiKhoan() {
		System.out.println("\n=======CHON LOAI TAI KHOAN========");
		System.out.println("1. Tai khoan admin");
		System.out.println("2. Tai khoan khach hang");
		System.out.println("0. Ket thuc chuong trinh");
		System.out.print("Vui long nhap lua chon: ");
	}

	public void menuAdmin() {
		System.out.println("\n=======QUAN LY KHACH HANG========");
		System.out.println("1. Them khach hang");
		System.out.println("2. Xoa khach hang");
		System.out.println("3. Tinh tien lai cho khach hang");
		System.out.println("4. Hien thi danh sach khach hang");
		System.out.println("5. Tra cuu khach hang");
		System.out.println("6. Tra cuu danh sach tai khoan");
		System.out.println("7. Sap xep");
		System.out.println("0. Thoat");
		System.out.print("Vui long nhap lua chon: ");

	}

	public void menuKhachHang() {
		System.out.println("\n======NGAN HANG DH MO======");
		System.out.println("1. Mo them tai khoan co ky han.");
		System.out.println("2. Nap tien");
		System.out.println("3. Rut tien");
		System.out.println("4. Doi mat khau");
		System.out.println("5. Hien thi tai khoan");
		System.out.println("0. Thoat");
		System.out.print("Vui long nhap lua chon: ");
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
				String stk;
				System.out.print("Nhap stk can tinh tien lai: ");
				stk = CauHinh.sc.nextLine();
				ql.tinhTienLai(stk);
				break;
			case 4:
				ql.output();
				break;
			case 5: {
				int choose;
				System.out.println("\n---TRA CUU KHACH HANG---");
				System.out.println("\n1. Theo ho ten");
				System.out.println("2. Theo ma khach hang");
				System.out.print("Nhap lua chon: ");
				do {
					choose = Integer.parseInt(CauHinh.sc.nextLine());
					if (choose > 2 || choose < 1) {
						System.out.print("Lua chon khong hop le. Nhap lai: ");
					}
				} while (choose > 2 || choose < 1);
				if (choose == 1) {
					System.out.print("Nhap ten: ");
					String kw = CauHinh.sc.nextLine();
					if (ql.traCuuTheoHoTen(kw).size() > 0) {
						ql.traCuuTheoHoTen(kw).forEach(h -> h.output());
					} else
						System.out.println("Khong co khach hang can tim!!!");
				} else {
					System.out.print("Nhap ma khach hang: ");
					String kw = CauHinh.sc.nextLine();
					KhachHang o = ql.traCuuTheoMa(kw);
					if (ql.traCuuTheoMa(kw) != null) {
						System.out.println("KHACH HANG CAN TIM");
						o.output();
					}
					else System.out.println("Khong tim thay khach hang!!!");
				}
			}
				break;
			case 6:
				System.out.print("Nhap ma khach hang: ");
				String kw = CauHinh.sc.nextLine();
				ql.traCuuDsTk(kw);
				break;

			case 7:
				if(ql.getDs().size() > 0) {
					ql.sapXep();
					System.out.println("Da sap xep!!!\n");
				}
				else System.out.println("Danh sach tai khoan trong!!!\n");
				break;
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
				a.moTkKyHan();
				break;
			case 2:
				a.napTien();
				break;
			case 3:
				a.rutTien();
				break;
			case 4:
				a.doiMatKhau();
				break;
			case 5:
				a.tkOutput();
				break;
			case 0:
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
			}

		} while (choice != 0);
	}

}
