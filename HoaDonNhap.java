package doanoop; // Sửa 1: Thêm package

import java.util.*;

public class HoaDonNhap extends HoaDon {
    private String nhaCungCap;

    public HoaDonNhap() {}

    // Sửa 2 & 3: Cập nhật hàm tạo
    public HoaDonNhap(String maHoaDon, String maNhanVienLap, Date ngayLap, String nhaCungCap) {
        super(maHoaDon, maNhanVienLap, ngayLap); // Bỏ thoiGianLap
        this.nhaCungCap = nhaCungCap;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    @Override
    // Sửa 4: Nhận Scanner và gọi super.nhap(sc)
    public void nhap(Scanner sc) {
        super.nhap(sc); // Gọi hàm nhập của cha
        // Không tạo new Scanner(System.in)
        System.out.print("Nhap nha cung cap: ");
        nhaCungCap = sc.nextLine();
    }

    @Override
    public void xuat() {
        super.xuat(); // Gọi hàm xuất của cha
        System.out.println("Nha cung cap: " + nhaCungCap);
    }
}