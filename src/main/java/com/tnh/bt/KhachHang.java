package com.tnh.bt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KhachHang implements DoiTuong {
	private static int dem;

	private String maKH;

	private String ten;

	private String gioiTinh;

	private LocalDate ngaySinh;

	private String queQuan;

	private String cccd;

	private TaiKhoanKhongKyHan tkKhongKyHan;

	private List<TaiKhoanCoKyHan> tkCoKyhan = new ArrayList<TaiKhoanCoKyHan>();

	private NguoiDungDangNhap tkDangNhap;

	{
		this.maKH = String.format ("%s%04d", LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy")), ++dem);
	}           

	public KhachHang() {
	}

	public KhachHang(String ten, String gioiTinh, LocalDate ngaySinh, String queQuan, String cccd) {
		super();
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.queQuan = queQuan;
		this.cccd = cccd;
	}
	
	public KhachHang(String ten, String gioiTinh, String ngaySinh, String queQuan, String cccd) {
		super();
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern(CauHinh.PATTERN));
		this.queQuan = queQuan;
		this.cccd = cccd;
	}

	@Override
	public void moTkKyHan() {
		if (this.tkKhongKyHan == null) {
			System.out.println("Chua mo tai khoan khong ky han\n");
			return;
		}
		System.out.println("===MO TAI KHOAN CO KY HAN===");
		System.out.print("Nhap so tien: ");
		double soTien = Double.parseDouble(CauHinh.sc.nextLine());
		if (this.tkKhongKyHan.getSoDu() - soTien > 50000 && soTien >= 100000) {
			kyHanOutput();
			System.out.print("Chon: ");
			int choose;
			do {
				choose = Integer.parseInt(CauHinh.sc.nextLine());
				if (choose < 1 || choose > KyHan.values().length) {
					System.out.print("Nhap sai, nhap lai!");
				}
			} while (choose < 1 || choose > KyHan.values().length);
			TaiKhoanCoKyHan tkc = new TaiKhoanCoKyHan(soTien, KyHan.values()[choose - 1]);
			System.out.printf("Da mo tai khoan co ky han\n");
			tkc.output();
			this.tkCoKyhan.add(tkc);
			this.tkKhongKyHan.soDu -= soTien;
			this.tkKhongKyHan.capNhatlaiSuat();
		} else if (soTien < 100000) {
			System.out.println("So tien toi thieu de mo tai khoan la 100 nghin");
		} else {
			System.out.println("So tien khong du de mo tai khoan");
		}
	}

	@Override
	public void napTien() {
		System.out.println("=====NẠP TIỀN=====");
		tkOutput();
		System.out.println("Bạn muốn nạp tiền vào tài khoản nào?");
		System.out.println("0. Tài khoản không kỳ hạn");
		System.out.println("1 - " + tkCoKyhan.size() + ". Tài khoản có kỳ hạn tương ứng");
		System.out.print("Nhập lựa chọn của bạn: ");
		int choice;
		do {
			choice = Integer.parseInt(CauHinh.sc.nextLine());
			if (choice < 0 || choice > tkCoKyhan.size()) {
				System.out.print("Nhập sai. Vui lòng nhập lại: ");
			}
		} while (choice < 0 || choice > tkCoKyhan.size());
		System.out.print("Nhập số tiền: ");
		double soTien = Double.parseDouble(CauHinh.sc.nextLine());
		if (choice == 0) {
			this.tkKhongKyHan.napTien(soTien);

		} else {
			TaiKhoanCoKyHan tk = tkCoKyhan.get(choice - 1);
			tk.napTien(soTien);
		}
	}

	@Override
	public void rutTien() {
		System.out.println("=====RÚT TIỀN=====");
		tkOutput();
		System.out.println("Bạn muốn rút tiền từ tài khoản nào?");
		System.out.println("0. Tài khoản không kỳ hạn");
		if(tkCoKyhan.size() > 0) {
			System.out.println("1 - " + tkCoKyhan.size() + ". Tài khoản có kỳ hạn tương ứng");
		}
		System.out.print("Nhập lựa chọn của bạn: ");
		int choice;
		do {
			choice = Integer.parseInt(CauHinh.sc.nextLine());
			if (choice < 0 || choice > tkCoKyhan.size()) {
				System.out.print("Nhập sai. Vui lòng nhập lại: ");
			}
		} while (choice < 0 || choice > 2);

		if (choice == 0) {
			if (tkKhongKyHan.isRutTien() == true)
				System.out.println("Rút tiền thành công.");

		} else {
			TaiKhoanCoKyHan tk = tkCoKyhan.get(choice - 1);
			if (tk.isRutTien() == true) {
				System.out.println("Da hoan lai tien vao tai khoan khong ky han");
				this.tkKhongKyHan.setSoDu(this.tkKhongKyHan.getSoDu() + tk.soDu +  tk.laiSuat);
				System.out.printf("Tai khoan khong ky han: %.1f", this.tkKhongKyHan.soDu);
				this.tkCoKyhan.remove(tk);
			}
		}

	}

	@Override
	public void nhapKh() {
		System.out.print("Nhập tên: ");
		this.ten = CauHinh.sc.nextLine();
		System.out.print("Nhập giới tính: ");
		this.gioiTinh = CauHinh.sc.nextLine();
		boolean hopLe = false;
		do {
			System.out.print("Nhập ngày sinh(dd/MM/yyyy): ");
			try {
				ngaySinh = LocalDate.parse(CauHinh.sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				hopLe = true;
			} catch (DateTimeParseException e) {
				System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại.");
			}
		} while (!hopLe);
		System.out.print("Nhập căn cước công dân: ");
		this.cccd = CauHinh.sc.nextLine();
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		System.out.printf("Mã số: %s\nTên: %s\nGiới tính: %s\nNgày sinh: %s\nQuê quán: %s\nCăn cước công dân: %s\n", this.maKH,
				this.ten, this.gioiTinh, this.ngaySinh.format(DateTimeFormatter.ofPattern(CauHinh.PATTERN)), this.queQuan, this.cccd);
		tkKhongKyHanOutput();
		tkCoKyhanOutput();
	}

	public void kyHanOutput() {
		System.out.println("==CÁC LOẠI KỲ HẠN==");
		KyHan kh[] = KyHan.values();
		for (int i = 0; i < kh.length; i++) {
			System.out.println((i + 1) + ". " + kh[i]);
		}
		System.out.println();
	}

	public void tkKhongKyHanOutput() {
		if (this.tkKhongKyHan != null) {
			System.out.println("\nTÀI KHOẢN KHÔNG KỲ HẠN");
			this.tkKhongKyHan.output();
		}
	}

	public void tkCoKyhanOutput() {
		if (this.tkCoKyhan.size() != 0) {
			System.out.println("\nCÁC TÀI KHOÀN CÓ KỲ HẠN:");
			for (int i = 0; i < tkCoKyhan.size(); i++) {
				System.out.println("Tài khoản thứ " + (i + 1));
				this.tkCoKyhan.get(i).output();
			}
		}
	}

	public void tkOutput() {
		tkKhongKyHanOutput();
		tkCoKyhanOutput();
	}

	public void doiMatKhau() {
		if (tkDangNhap != null) {
			System.out.print("Nhập mật khẩu cũ: ");
			String mkc;
			do {
				mkc = CauHinh.sc.nextLine();
				if (mkc.equals(tkDangNhap.getMatKhau())) {
					System.out.print("Nhập mật khẩu mới: ");
					String mkm;
					do {
						mkm = CauHinh.sc.nextLine();
						if (DangNhap.isMatKhauHopLe(mkm)) {
							System.out.print("Nhập lại mật khẩu: ");
							String conf;
							do {
								conf = CauHinh.sc.nextLine();
								if (conf.equals(mkm)) {
									this.tkDangNhap.setMatKhau(mkm);
									System.out.println("Đã đổi mật khẩu");
									break;
								} else
									System.out.println("Mật khẩu không trùng khớp, nhập lại: ");
						
							} while (true);

							break;
						} else {
							System.out.print("Mật khẩu không hợp lệ, nhập lại: ");
						}
					} while (true);
					break;
				} else {
					System.out.print("Nhập sai mật khẩu cũ. Nhập lại: ");
				}
			} while (true);
		}
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public TaiKhoanKhongKyHan getTkKhongKyHan() {
		return tkKhongKyHan;
	}

	public void setTkKhongKyHan(TaiKhoanKhongKyHan tkKhongKyHan) {
		this.tkKhongKyHan = tkKhongKyHan;
	}

	public List<TaiKhoanCoKyHan> getTkCoKyhan() {
		return tkCoKyhan;
	}

	public void setTkCoKyhan(List<TaiKhoanCoKyHan> tkCoKyhan) {
		this.tkCoKyhan = tkCoKyhan;
	}

	public NguoiDungDangNhap getTkDangNhap() {
		return tkDangNhap;
	}

	public void setTkDangNhap(NguoiDungDangNhap tkDangNhap) {
		this.tkDangNhap = tkDangNhap;
	}

}
