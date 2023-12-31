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
			System.out.println("Nap tien thanh cong.\nSo du hien tai: " + soDu);
		} else {
			System.out.println("So tien nap phai lon hon 0.");
		}
	}

	@Override
	public boolean isRutTien() {
		System.out.print("Nhap so tien can rut: ");
		double soTien = Double.parseDouble(CauHinh.sc.nextLine());
		if (soTien > 0) {
			if (soTien <= soDu) {
				soDu -= soTien;
				capNhatlaiSuat();
				return true;
			} else {
				System.out.println("So du khong du de rut");
				return false;
			}
		} else {
			System.out.println("So tien rut phai lon hon 0");
			return false;
		}
	}

	public void output() {
		System.out.printf("So du : %.1f\nLai suat: %.2f\n", this.soDu, this.laiSuat);
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
