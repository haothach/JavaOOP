package com.tnh.bt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

public class KhachHang implements DoiTuong {

	private static int dem;

	private String maKH;

	private String ten;

	private String gioiTinh;

	private LocalDate ngaySinh;

	private String queQuan;

	private String cccd;

	private TaiKhoanKhongKyHan tkKhongKyHan;

	private List<TaiKhoanCoKyHan> tkCoKyhan = new ArrayList<>();

	private NguoiDungDangNhap tkDangNhap;

	{
		this.maKH = String.format("%s%04d", LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")), ++dem);
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
	public void moTk() {
//		nhapKh();
		double soTien;
		TaiKhoanKhongKyHan tk = new TaiKhoanKhongKyHan();
		do {
			System.out.print("\nNhap so tien gui: ");
			soTien = Double.parseDouble(CauHinh.sc.nextLine());
			if (soTien < tk.getSoTienToiThieu()) {
				System.out.println("So tien khong hop le");
			}
		} while (soTien < tk.getSoTienToiThieu());
		tk.setSoDu(soTien);
		tk.capNhatlaiSuat();
		this.tkKhongKyHan = tk;
		System.out.println("Mo tai khoan thanh cong");
		String user = this.maKH;

		Random random = new Random();
		String kq = "";
		for (int i = 0; i < 6; i++) {
			int number = random.nextInt(10);
			kq += number;
		}
		String mk = kq.substring(0);
		this.tkDangNhap = new NguoiDungDangNhap(user, mk);
		System.out.println("So tai khoan: " + this.tkDangNhap.getTenDangNhap());
		System.out.println("Mat khau: " + this.tkDangNhap.getMatKhau());
		System.out.println();
	}

	@Override
	public void moTkKyHan() {
		System.out.printf("\nSo du: %.1f\n\n", this.tkKhongKyHan.getSoDu());
		if (this.tkKhongKyHan == null) {
			System.out.println("Chua mo tai khoan khong ky han\n");
			return;
		}
		System.out.println("===MO TAI KHOAN CO KY HAN===");
		System.out.print("Nhap so tien: ");
		double soTien = Double.parseDouble(CauHinh.sc.nextLine());
		TaiKhoanCoKyHan tmp = new TaiKhoanCoKyHan();
		if (this.tkKhongKyHan.getSoDu() - soTien >= this.tkKhongKyHan.getSoTienToiThieu()
				&& soTien >= tmp.soTienToiThieu) {
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
		} else if (soTien < tmp.getSoTienToiThieu()) {
			System.out.println("So tien toi thieu de mo tai khoan la 100 nghin.");
		} else {
			System.out.println("So du con lai se duoi muc toi thieu, nen khong the mo tai khoan!");
		}
	}

	@Override
	public void napTien() {
		
		int choice;
		do {
			choice = Integer.parseInt(CauHinh.sc.nextLine());
			if (choice < 0 || choice > tkCoKyhan.size()) {
				System.out.print("Nhap sai! Vui long nhap lai: ");
			}
		} while (choice < 0 || choice > tkCoKyhan.size());
		System.out.print("Nhap so tien: ");
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
		int choice;
		do {
			choice = Integer.parseInt(CauHinh.sc.nextLine());
			if (choice < 0 || choice > tkCoKyhan.size()) {
				System.out.print("Nhap sai ! Vui long nhap lai: ");
			}
		} while (choice < 0 || choice > 2);

		if (choice == 0) {
			if (tkKhongKyHan.isRutTien() == true) {
				System.out.println("Rut tien thanh cong!.");
			}

		} else {
			TaiKhoanCoKyHan tk = tkCoKyhan.get(choice - 1);
			if (tk.isRutTien() == true) {
				System.out.println("Da hoan lai tien vao tai khoan khong ky han");
				this.tkKhongKyHan.setSoDu(this.tkKhongKyHan.getSoDu() + tk.soDu + tk.laiSuat);
				System.out.printf("Tai khoan khong ky han: %.1f\n", this.tkKhongKyHan.soDu);
				this.tkCoKyhan.remove(tk);
			}
		}

	}

	@Override
	public void nhapKh() {
		System.out.println("\n=========NHAP THONG TIN==========");
		System.out.print("Nhap ten: ");
		this.ten = CauHinh.sc.nextLine();
		int choose;
		do {
			System.out.print("Nhap gioi tinh(0.Nam/1.Nu): ");
			try {
				choose = Integer.parseInt(CauHinh.sc.nextLine());
				if (choose < 0 || choose > 1) {
					System.out.println("Nhap sai. Vui long nhap lai!");
					choose = -1; 
				}
			} catch (NumberFormatException e) {
				System.out.println("Nhap sai. Vui long nhap lai!");
				choose = -1; 
			}
		} while (choose < 0 || choose > 1);
		if (choose == 0)
			this.gioiTinh = "Nam";
		else
			this.gioiTinh = "Nu";
		boolean hopLe = false;
		do {
			System.out.print("Nhap ngay sinh(dd/MM/yyyy): ");
			try {
				ngaySinh = LocalDate.parse(CauHinh.sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				hopLe = true;
			} catch (DateTimeParseException e) {
				System.out.println("Ngay sinh khong hop le. Vui long nhap lai!");
			}
		} while (!hopLe);
		System.out.print("Nhap que quan: ");
		this.queQuan = CauHinh.sc.nextLine();
		System.out.print("Nhap can cuoc cong dan: ");
		this.cccd = CauHinh.sc.nextLine();
	}

	@Override
	public void output() {
		System.out.println("\n=====THONG TIN KHACH HANG=====");
		System.out.printf("Ma so: %s\nTen: %s\nGioi tinh: %s\nNgay sinh: %s\nQue quan: %s\nCan cuoc cong dan: %s\n",
				this.maKH, this.ten, this.gioiTinh, this.ngaySinh.format(DateTimeFormatter.ofPattern(CauHinh.PATTERN)),
				this.queQuan, this.cccd);
		tkKhongKyHanOutput();
		tkCoKyhanOutput();
	}

	public void kyHanOutput() {
		System.out.println("==CAC LOAI KY HAN==");
		KyHan kh[] = KyHan.values();
		int i = 1;
		for (KyHan k : kh) {
		  System.out.println(i + ". " + k);
		  i++;
		}
		System.out.println();
	}

	public void tkKhongKyHanOutput() {
		if (this.tkKhongKyHan != null) {
			System.out.println("\nTAI KHOAN KHONG KY HAN");
			this.tkKhongKyHan.output();
		}
	}

	public void tkCoKyhanOutput() {
		if (this.tkCoKyhan.size() != 0) {
			System.out.println("\nCAC TAI KHOAN CO KY HAN");
			int i = 1;
			for (TaiKhoan tk : tkCoKyhan) {
			  System.out.println("Tai khoan thu " + i);
			  tk.output();
			  i++;
			}
		}
	}

	public void tkOutput() {
		tkKhongKyHanOutput();
		tkCoKyhanOutput();
	}

	public void doiMatKhau() {
		if (tkDangNhap != null) {
			System.out.print("Nhap mat khau cu: ");
			String mkc;
			do {
				mkc = CauHinh.sc.nextLine();
				if (mkc.equals(tkDangNhap.getMatKhau())) {
					System.out.print("Nhap mat khau moi: ");
					String mkm;
					do {
						mkm = CauHinh.sc.nextLine();
						if (DangNhap.isMatKhauHopLe(mkm)) {
							System.out.print("Nhap lai mat khau: ");
							String conf;
							do {
								conf = CauHinh.sc.nextLine();
								if (conf.equals(mkm)) {
									this.tkDangNhap.setMatKhau(mkm);
									System.out.println("Da doi mat khau");
									break;
								} else {
									System.out.println("Mat khau khong trung khớp, nhap lai: ");
								}

							} while (true);

							break;
						} else {
							System.out.print("Mat khau khong hop le, nhap lai: ");
						}
					} while (true);
					break;
				} else {
					System.out.print("Nhap sai mat khau cu. Nhap lai: ");
				}
			} while (true);
		}
	}

	public double tinhTong() {
		return this.tkKhongKyHan.soDu + this.tkCoKyhan.stream().flatMapToDouble(t -> DoubleStream.of(t.soDu)).sum();
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
