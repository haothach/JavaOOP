package com.tnh.bt;

import java.time.LocalDate;

public enum KyHan {
	MOT_TUAN(7, 0.02) {

		@Override
		public LocalDate tinhNgayDaoHan(LocalDate ngayDaoHan) {
			return ngayDaoHan = ngayDaoHan.plusDays(this.khoangTg);
		}

		@Override
		public double tinhLaiSuat(double soTien) {
			return soTien * laiSuat * khoangTg / 365;
		}

		@Override
		public double phanTramLai() {
			return this.laiSuat;
		}

	},
	MOT_THANG(1, 0.055) {

		@Override
		public LocalDate tinhNgayDaoHan(LocalDate ngayDaoHan) {
			return ngayDaoHan = ngayDaoHan.plusMonths(this.khoangTg);
		}

		@Override
		public double tinhLaiSuat(double soTien) {
			return soTien * laiSuat * khoangTg / 12;
		}

		@Override
		public double phanTramLai() {
			return this.laiSuat;
		}

	},
	SAU_THANG(6, 0.075) {

		@Override
		public LocalDate tinhNgayDaoHan(LocalDate ngayDaoHan) {
			return ngayDaoHan = ngayDaoHan.plusMonths(this.khoangTg);
		}

		@Override
		public double tinhLaiSuat(double soTien) {
			return soTien * laiSuat * khoangTg / 12;
		}

		@Override
		public double phanTramLai() {
			return this.laiSuat;
		}
	},
	MOT_NAM(1, 0.079) {

		@Override
		public LocalDate tinhNgayDaoHan(LocalDate ngayDaoHan) {
			return ngayDaoHan = ngayDaoHan.plusYears(this.khoangTg);
		}

		@Override
		public double tinhLaiSuat(double soTien) {
			return (soTien * this.laiSuat);
		}

		@Override
		public double phanTramLai() {
			return this.laiSuat;
		}
	};

	protected int khoangTg;
	protected double laiSuat;

	private KyHan(int khoangTg, double laiSuat) {
		this.khoangTg = khoangTg;
		this.laiSuat = laiSuat;
	}

	public abstract LocalDate tinhNgayDaoHan(LocalDate ngayDaoHan);

	public abstract double tinhLaiSuat(double soTien);

	public abstract double phanTramLai();

	public int getKhoangTg() {
		return khoangTg;
	}

	public void setKhoangTg(int khoangTg) {
		this.khoangTg = khoangTg;
	}

	public double getLaiSuat() {
		return laiSuat;
	}

	public void setLaiSuat(double laiSuat) {
		this.laiSuat = laiSuat;
	}
	
}
