package com.tnh.bt;

public class Menu {

    public void displayMenu() {
        System.out.println("================NGAN HANG DH MO===================");
        System.out.println("1. Mo tai khoan cho khach hang.");
        System.out.println("2. Mo them tai khoan co ky han.");
        System.out.println("0. Thoat");
        System.err.print("Vui long lua chon chuc nang: ");
    }

    public void chooseFunction() {
        int choice;
        QuanLyKhachHang ql = new QuanLyKhachHang();
        do {
            displayMenu();
            choice = Integer.parseInt(CauHinh.sc.nextLine());
            switch (choice) {
                case 1:
                    ql.adminDangNhap();
                    KhachHang a = new KhachHang();
                    ql.them(a);
                    break;
                case 2:
                    ql.khachHangDangNhap().moTkKyHan();
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
