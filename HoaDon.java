package doanoop; // Sửa 1: Thêm package

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class HoaDon {
    protected String maHoaDon;
    protected String maNhanVienLap; // Sửa 2: Đổi sang mã NV
    protected Date ngayLap;
    // Sửa 3: Bỏ thoiGianLap

    public HoaDon() {}

    // Sửa 2 & 3: Cập nhật hàm tạo
    public HoaDon(String maHoaDon, String maNhanVienLap, Date ngayLap) {
        this.maHoaDon = maHoaDon;
        this.maNhanVienLap = maNhanVienLap;
        this.ngayLap = ngayLap;
    }

    // Getters/Setters đã cập nhật
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNhanVienLap() {
        return maNhanVienLap;
    }

    public void setMaNhanVienLap(String maNhanVienLap) {
        this.maNhanVienLap = maNhanVienLap;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    // Sửa 4: Nhận Scanner từ bên ngoài
    public void nhap(Scanner sc) {
        // Không tạo new Scanner(System.in)
        System.out.print("Nhap ma hoa don: ");
        maHoaDon = sc.nextLine();
        
        // Sửa 2: Nhập mã NV
        System.out.print("Nhap ma nhan vien lap hoa don: ");
        maNhanVienLap = sc.nextLine();
        
        ngayLap = new Date();  // Lấy ngày giờ hiện tại
        // Sửa 3: Không cần gán thoiGianLap
    }

    public void xuat() {
        // Sửa 3: Gộp ngày và giờ
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("Ma hoa don: " + maHoaDon);
        System.out.println("Ma nhan vien lap: " + maNhanVienLap); // Sửa 2
        System.out.println("Thoi gian lap: " + sdf.format(ngayLap)); // Sửa 3
    }
}