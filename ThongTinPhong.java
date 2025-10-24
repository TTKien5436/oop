package doanoop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ThongTinPhong {
    private String maPhong;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private double donGia;
    private double tongTien;
    private static int dem = 1;

    public ThongTinPhong(String maPhong, String ngayBatDauStr, String ngayKetThucStr, double donGia) {
        this.maPhong = maPhong;
        this.donGia = donGia;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            this.ngayBatDau = LocalDate.parse(ngayBatDauStr, dtf);
            this.ngayKetThuc = LocalDate.parse(ngayKetThucStr, dtf);
            if (this.ngayBatDau.isAfter(this.ngayKetThuc)) {
                throw new IllegalArgumentException("Ngay bat dau khong the sau ngay ket thuc.");
            }
            long soNgayO = ChronoUnit.DAYS.between(this.ngayBatDau, this.ngayKetThuc);
            if (soNgayO == 0) soNgayO = 1; // Ở ít nhất 1 ngày
            this.tongTien = soNgayO * donGia;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Loi dinh dang ngay thang. Vui long nhap lai theo dinh dang dd/MM/yyyy.");
        }
    }

    // Getters
    public String getMaPhong() {
        return maPhong;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getTongTien() {
        return this.tongTien;
    }
}