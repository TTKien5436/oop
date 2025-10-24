package doanoop; // Sửa 1: Thêm package

import java.util.*;

public class HoaDonXuat extends HoaDon {
    private String maKhachHang; // Sửa 2: Đổi sang mã KH

    public HoaDonXuat() {}

    // Sửa 2 & 3: Cập nhật hàm tạo
    public HoaDonXuat(String maHoaDon, String maNhanVienLap, Date ngayLap, String maKhachHang) {
        super(maHoaDon, maNhanVienLap, ngayLap); // Bỏ thoiGianLap
        this.maKhachHang = maKhachHang;
    }

    // Sửa 2: Cập nhật Getter/Setter
    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }


    @Override
    // Sửa 4: Nhận Scanner và gọi super.nhap(sc)
    public void nhap(Scanner sc) {
        super.nhap(sc); // Gọi hàm nhập của cha
        // Không tạo new Scanner(System.in)
        System.out.print("Nhap ma khach hang: "); // Sửa 2
        maKhachHang = sc.nextLine();
    }

    @Override
    public void xuat() {
        super.xuat(); // Gọi hàm xuất của cha
        System.out.println("Ma khach hang: " + maKhachHang); // Sửa 2
    }
}