package com.tnh.bt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaiKhoanCoKyHan extends TaiKhoanKhongKyHan implements TaiKhoan {

	private KyHan kyHan;
	private double tmp = super.phanTramLaiSuat;
	private LocalDate ngayDaoHan;

	public TaiKhoanCoKyHan(double soDu, KyHan kyHan) {
		super(soDu);
		this.kyHan = kyHan;
		this.laiSuat = this.kyHan.tinhLaiSuat(soDu);
		this.phanTramLaiSuat = this.kyHan.phanTramLai();
		this.ngayDaoHan = kyHan.tinhNgayDaoHan(LocalDate.now());
	}

	public void napTien(double soTien) {
		if (LocalDate.now().compareTo(ngayDaoHan) < 0) {
			System.out.println("Khong the nap tien khi chua den ngay dao han.");
		} else if (soTien <= 0) {
			System.out.println("So tien nap phai lon hon 0");
		} else if (LocalDate.now().compareTo(ngayDaoHan) >= 0 && soTien > 0) {
			this.soDu += soTien;
			System.out.println("Da gui tien thanh cong");
			System.out.printf("So du: %.2f\n", this.soDu);
		}

	}

	public boolean isRutTien() {
		if (LocalDate.now().compareTo(ngayDaoHan) < 0) {
			System.out.println("Khong the rut tien khi chua den ngay dao han!");
			System.out.print("\n"
					+ "Ban co dong y rut khong?\n1. Co\t2. Khong: ");
			int choose;
			do {
				choose = Integer.parseInt(CauHinh.sc.nextLine());
				if (choose < 1 || choose > 2)
					System.out.print("Nhap sai, vui long nhap lai: ");
			} while (choose < 1 || choose > 2);
			if (choose == 1) {
				capNhatlaiSuat();
				return true;
			} else {
				System.out.println("Da huy dich vu rut tien");
				return false;
			}

		}
		return true;

	}

	public void output() {
		super.output();
		System.out.printf("Ky han: %s\nNgay dao han: %s\n", this.kyHan,
				this.ngayDaoHan.format(DateTimeFormatter.ofPattern(CauHinh.PATTERN)));
	}
	
	@Override
	public void capNhatlaiSuat() {
		this.phanTramLaiSuat = tmp;
		this.kyHan.setLaiSuat(this.phanTramLaiSuat);
		this.laiSuat = this.kyHan.tinhLaiSuat(soDu);
	}

	@Override
	public double getLaiSuat() {
		// TODO Auto-generated method stub
		return laiSuat;
	}

	@Override
	public double getPhanTramLaiSuat() {
		// TODO Auto-generated method stub
		return phanTramLaiSuat;
	}

	@Override
	public double getSoDu() {
		// TODO Auto-generated method stub
		return getSoDu();
	}

	public KyHan getKyHan() {
		return kyHan;
	}

	public void setKyHan(KyHan kyHan) {
		this.kyHan = kyHan;
	}

	public LocalDate getNgayDaoHan() {
		return ngayDaoHan;
	}

	public void setNgayDaoHan(LocalDate ngayDaoHan) {
		this.ngayDaoHan = ngayDaoHan;
	}

}
