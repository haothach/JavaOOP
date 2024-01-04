package com.tnh.bt;
public class Test {

    public static void main(String[] args) {
//     Menu kiemtra = new Menu();
//     kiemtra.chonLoaiTaiKhoan();
    	KhachHang a = new KhachHang("a", "nam", "12/12/1212", "123", "123");
    	a.moTk();
    	a.moTkKyHan();
    	a.moTkKyHan();
    	System.out.println(a.tinhTong());
    }
}
