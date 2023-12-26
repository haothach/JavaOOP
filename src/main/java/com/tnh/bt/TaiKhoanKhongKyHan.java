package com.tnh.bt;

public class TaiKhoanKhongKyHan implements TaiKhoan {
	protected double soDu;
	protected double laiSuat;
	protected double phanTramLaiSuat;

	public TaiKhoanKhongKyHan(double soDu) {
		this.soDu = soDu;
		this.phanTramLaiSuat = 0.002;
		this.laiSuat = this.phanTramLaiSuat * soDu;
	}

	public void napTien(double soTien) {
		if (soTien > 0) {
			soDu += soTien;
			capNhatlaiSuat();
			System.out.println("Nạp tiền thành công.\nSố dư hiện tại: " + soDu);
		} else {
			System.out.println("Số tiền nạp phải lớn hơn 0.");
		}
	}

	public boolean isRutTien() {
		System.out.print("Nhập số tiền cần rút: ");
		double soTien = Double.parseDouble(CauHinh.sc.nextLine());
		if (soTien > 0) {
			if (soTien <= soDu) {
				soDu -= soTien;
				capNhatlaiSuat();
				return true;
			} else {
				System.out.println("Số dư không đủ để rút.");
				return false;
			}
		} else {
			System.out.println("Số tiền rút phải lớn hơn 0.");
			return false;
		}
	}

	public void output() {
		System.out.printf("Số dư: %.1f\nLãi suất: %.2f\n", this.soDu, this.laiSuat);
	}

	public void capNhatlaiSuat() {
		laiSuat = phanTramLaiSuat * soDu;
	}

	public double getLaiSuat() {
		return laiSuat;
	}

	public void setLaiSuat(double laiSuat) {
		this.laiSuat = laiSuat;
	}

	public double getSoDu() {
		return soDu;
	}

	public void setSoDu(double soDu) {
		this.soDu = soDu;
	}

	public double getPhanTramLaiSuat() {
		return phanTramLaiSuat;
	}

	public void setPhanTramLaiSuat(double phanTramLaiSuat) {
		this.phanTramLaiSuat = phanTramLaiSuat;
	}

}
