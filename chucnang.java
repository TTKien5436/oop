package doanoop;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Lớp này là public, vì vậy tên file phải là "chucnang.java".
 */
public class chucnang {
    // Bạn có thể thêm các phương thức quản lý chung vào đây, ví dụ:
    // public void themPhongMoi() { ... }
    // public void taoDonDatPhong() { ... }
}

/**
 * Lớp Phong đã được bỏ từ khóa "public".
 * Mọi chức năng (thuộc tính, phương thức) vẫn được giữ nguyên.
 */
class Phong {
    protected String maPhong;
    protected String loaiPhong;
    protected boolean tinhTrang;
    protected double giaPhong;

    public Phong(String maPhong, String loaiPhong, boolean tinhTrang, double giaPhong) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.tinhTrang = tinhTrang;
        this.giaPhong = giaPhong;
    }

    // Getters
    public String getMaPhong() { return maPhong; }
    public String getLoaiPhong() { return loaiPhong; }
    public boolean getTinhTrang() { return tinhTrang; }
    public double getGiaPhong() { return giaPhong; }

    // Setters
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }
    public void setLoaiPhong(String loaiPhong) { this.loaiPhong = loaiPhong; }
    public void setTinhTrang(boolean tinhTrang) { this.tinhTrang = tinhTrang; }
    public void setGiaPhong(double giaPhong) { this.giaPhong = giaPhong; }
}

/**
 * Lớp DatPhong đã được bỏ từ khóa "public".
 * Mọi chức năng vẫn được giữ nguyên.
 */
class DatPhong {
    protected String maDatPhong;
    protected String maKhachHang;
    protected String maPhong;
    protected String ngayNhan;
    protected String ngayTra;
    protected double tongTien;

    public DatPhong(String maDatPhong, String maKhachHang, String maPhong,
                    String ngayNhan, String ngayTra, double tongTien) {
        this.maDatPhong = maDatPhong;
        this.maKhachHang = maKhachHang;
        this.maPhong = maPhong;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.tongTien = tongTien;
    }
    
    // Getters and Setters
    public String getMaDatPhong() { return maDatPhong; }
    public void setMaDatPhong(String maDatPhong) { this.maDatPhong = maDatPhong; }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }
    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }
    public String getNgayNhan() { return ngayNhan; }
    public void setNgayNhan(String ngayNhan) { this.ngayNhan = ngayNhan; }
    public String getNgayTra() { return ngayTra; }
    public void setNgayTra(String ngayTra) { this.ngayTra = ngayTra; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}

/**
 * SỬA 1: Lớp HoaDon đã được đổi tên thành "HoaDonThanhToan" 
 * để tránh xung đột với file HoaDon.java (lớp abstract)
 */
class HoaDonThanhToan { // <<< ĐỔI TÊN
    protected String maHoaDon;
    protected String maDatPhong;
    protected double tongTien;
    protected double thue;
    protected double giamGia;

    // SỬA 2: Đổi tên hàm tạo (constructor)
    public HoaDonThanhToan(String maHoaDon, String maDatPhong, double tongTien, double thue, double giamGia) { // <<< ĐỔI TÊN
        this.maHoaDon = maHoaDon;
        this.maDatPhong = maDatPhong;
        this.tongTien = tongTien;
        this.thue = thue;
        this.giamGia = giamGia;
    }
    
    // Getters and Setters
    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }
    public String getMaDatPhong() { return maDatPhong; }
    public void setMaDatPhong(String maDatPhong) { this.maDatPhong = maDatPhong; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    public double getThue() { return thue; }
    public void setThue(double thue) { this.thue = thue; }
    public double getGiamGia() { return giamGia; }
    public void setGiamGia(double giamGia) { this.giamGia = giamGia; }
}

/**
 * Lớp DichVu đã được bỏ từ khóa "public".
 * Mọi chức năng vẫn được giữ nguyên.
 */
class DichVu {
    protected String maDichVu;
    protected String tenDichVu;
    protected double donGia;

    public DichVu(String maDichVu, String tenDichVu, double donGia) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
    }

    // Getters and Setters
    public String getMaDichVu() { return maDichVu; }
    public void setMaDichVu(String maDichVu) { this.maDichVu = maDichVu; }
    public String getTenDichVu() { return tenDichVu; }
    public void setTenDichVu(String tenDichVu) { this.tenDichVu = tenDichVu; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}
