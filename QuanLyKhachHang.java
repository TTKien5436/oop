package doanoop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lớp QuanLyKhachHang: Định nghĩa đối tượng khách hàng
 * Kế thừa từ lớp 'nguoi'
 */
public class QuanLyKhachHang extends nguoi {
    
    // ================= THUỘC TÍNH =================
    private static int dem = 1;
    private String maKH;
    private ThongTinPhong phongDat;
    private long tongChiPhiDichVu;
    private boolean daThanhToan;

    // ================= HÀM TẠO (CONSTRUCTOR) =================
    public QuanLyKhachHang(String hoTen, String soDienThoai, ThongTinPhong phongDat) {
        super(hoTen, soDienThoai);
        if (phongDat == null) {
            throw new IllegalArgumentException("Phong dat khong duoc null!");
        }
        this.maKH = taoMaKH();
        this.phongDat = phongDat;
        this.tongChiPhiDichVu = 0;
        this.daThanhToan = false;
    }

    // ================= PHƯƠNG THỨC LOGIC =================
    private String taoMaKH() {
        return String.format("KH%03d", dem++);
    }

    public void themDichVu(long chiPhi) {
        if (chiPhi > 0) {
            this.tongChiPhiDichVu += chiPhi;
        }
    }

    public long tinhTien() {
        if (phongDat == null) return tongChiPhiDichVu;
        return (long) phongDat.getTongTien() + tongChiPhiDichVu;
    }

    public void thanhToan() {
        this.daThanhToan = true;
    }

    public void huyDatPhong() {
        if (!daThanhToan && phongDat != null) {
            this.phongDat = null;
            this.tongChiPhiDichVu = 0;
            System.out.println("Khach hang " + hoten + " da huy dat phong.");
        } else if (daThanhToan) {
            System.out.println("Khong the huy vi da thanh toan!");
        } else {
            System.out.println("Khach hang chua dat phong.");
        }
    }
    
    // ================= PHƯƠNG THỨC XUẤT THÔNG TIN / GHI FILE =================

    public void xuatThongTin() {
        System.out.println("=== Thong Tin Khach Hang ===");
        System.out.println("Ma KH        : " + maKH);
        System.out.println("Ho Ten       : " + hoten);
        System.out.println("SDT          : " + sodienthoai);
        if (phongDat != null) {
            System.out.println("Phong da dat : " + phongDat.getMaPhong());
            System.out.println("Ngay BD      : " + phongDat.getNgayBatDau().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Ngay KT      : " + phongDat.getNgayKetThuc().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Don gia      : " + phongDat.getDonGia());
        } else {
            System.out.println("Khach hang da tra phong hoac huy dat.");
        }
        System.out.println("Chi phi DV   : " + tongChiPhiDichVu);
        System.out.println("Tong Tien    : " + tinhTien());
        System.out.println("Tinh Trang   : " + (daThanhToan ? "Da Thanh Toan" : "Chua Thanh Toan"));
        System.out.println("============================");
    }
    
    /**
     * Trả về một chuỗi định dạng để ghi thông tin khách hàng ra file text.
     * Định dạng: hoten;sodienthoai;maKH;maPhong;tongChiPhiDichVu;daThanhToan
     * @return Chuỗi thông tin khách hàng.
     */
    public String ghiFile() {
        // 1. Lấy mã phòng:
        String maPhong = (this.phongDat != null) ? this.phongDat.getMaPhong() : "NULL";
        
        // 2. Lấy trạng thái thanh toán:
        String trangThaiThanhToan = this.daThanhToan ? "Có" : "Không";
        
        // 3. Nối các thông tin lại bằng dấu chấm phẩy (;)
        return String.join(";", 
            this.hoten,                 // hoten
            this.sodienthoai,           // sodienthoai
            this.maKH,                  // maKH
            maPhong,                    // phongDat (lấy mã phòng)
            String.valueOf(this.tongChiPhiDichVu), // tongChiPhiDichVu
            trangThaiThanhToan          // daThanhToan (Có hoặc Không)
        );
    }

    // ================= GETTERS =================
    public String getMaKH() { return maKH; }
    public long getTongTien() { return tinhTien(); }
    public boolean isThanhToan() { return daThanhToan; }
    public ThongTinPhong getPhongDat() { return phongDat; }
}

/**
 * Lớp DanhSachKhachHang: Quản lý danh sách các đối tượng QuanLyKhachHang
 */
class DanhSachKhachHang {
    private Map<String, QuanLyKhachHang> dsKhachHang = new HashMap<>();

    // ================= CHỨC NĂNG THÊM, SỬA, XÓA (CRUD) =================

    public boolean themKhachHang(QuanLyKhachHang kh) {
        for (QuanLyKhachHang k : dsKhachHang.values()) {
            if (k.getsodienthoai().equals(kh.getsodienthoai())) {
                System.out.println("Loi: So dien thoai da ton tai!");
                return false;
            }
            if (k.getPhongDat() != null && kh.getPhongDat() != null &&
                k.getPhongDat().getMaPhong().equals(kh.getPhongDat().getMaPhong())) {
                System.out.println("Loi: Phong nay da duoc dat boi khach hang khac!");
                return false;
            }
        }
        dsKhachHang.put(kh.getMaKH(), kh);
        return true;
    }

    public boolean suaKhachHang(String maKH, String tenMoi, String sdtMoi) {
        QuanLyKhachHang khCanSua = timTheoMaKH(maKH);
        if (khCanSua == null) {
            System.out.println("Loi: Khong tim thay khach hang voi ma " + maKH);
            return false;
        }

        if (sdtMoi != null && !sdtMoi.trim().isEmpty()) {
            for (QuanLyKhachHang kh : dsKhachHang.values()) {
                if (kh.getsodienthoai().equals(sdtMoi) && !kh.getMaKH().equals(maKH)) {
                    System.out.println("Loi: So dien thoai " + sdtMoi + " da duoc su dung.");
                    return false;
                }
            }
            khCanSua.setsodienthoai(sdtMoi);
        }

        if (tenMoi != null && !tenMoi.trim().isEmpty()) {
            khCanSua.sethoten(tenMoi);
        }
        System.out.println("Cap nhat thong tin cho khach hang " + maKH + " thanh cong!");
        return true;
    }

    public boolean xoaKhachHang(String maKH) {
        QuanLyKhachHang kh = timTheoMaKH(maKH);
        if (kh == null) {
            System.out.println("Loi: Khong tim thay khach hang voi ma " + maKH);
            return false;
        }

        if (kh.getPhongDat() != null && !kh.isThanhToan()) {
            System.out.println("Loi: Khong the xoa khach hang nay vi ho con don dat phong chua thanh toan.");
            return false;
        }

        dsKhachHang.remove(maKH);
        System.out.println("Da xoa khach hang " + kh.gethoten() + " (Ma: " + maKH + ") thanh cong.");
        return true;
    }

    // ================= CHỨC NĂNG TÌM KIẾM =================

    public QuanLyKhachHang timTheoMaKH(String maKH) {
        return dsKhachHang.get(maKH);
    }

    public QuanLyKhachHang timTheoSDT(String sdt) {
        for (QuanLyKhachHang kh : dsKhachHang.values()) {
            if (kh.getsodienthoai().equals(sdt)) {
                return kh;
            }
        }
        return null; // Không tìm thấy
    }

    public List<QuanLyKhachHang> timTheoTen(String ten) {
        List<QuanLyKhachHang> ketQua = new ArrayList<>();
        for (QuanLyKhachHang kh : dsKhachHang.values()) {
            if (kh.gethoten().toLowerCase().contains(ten.toLowerCase())) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }

    // ================= CHỨC NĂNG HIỂN THỊ VÀ THỐNG KÊ =================
    
    public void xuatDanhSach() {
        System.out.println("\n--- DANH SACH TOAN BO KHACH HANG ---");
        if (dsKhachHang.isEmpty()) {
            System.out.println("Chua co khach hang nao trong danh sach.");
            return;
        }
        for (QuanLyKhachHang k : dsKhachHang.values()) {
            k.xuatThongTin();
        }
        System.out.println("------------------------------------");
    }
    
    public long tinhTongDoanhThu() {
        long tong = 0;
        for (QuanLyKhachHang k : dsKhachHang.values()) {
            if (k.isThanhToan()) {
                tong += k.getTongTien();
            }   
        }
        return tong;
    }
    
    public List<QuanLyKhachHang> lietKeChuaThanhToan() {
        List<QuanLyKhachHang> ketQua = new ArrayList<>();
        for (QuanLyKhachHang k : dsKhachHang.values()) {
            if (!k.isThanhToan()) {
                ketQua.add(k);
            }
        }
        return ketQua;
    }

    public List<QuanLyKhachHang> sapXepTheoTongTien() {
        List<QuanLyKhachHang> ketQua = new ArrayList<>(dsKhachHang.values());
        ketQua.sort((a, b) -> Long.compare(b.getTongTien(), a.getTongTien()));
        return ketQua;
    }
    
    // ================= CHỨC NĂNG GHI FILE =================
    
    /**
     * Ghi toàn bộ danh sách khách hàng ra file.
     * @param tenFile Tên file để ghi (ví dụ: "khachhang.txt")
     */
    public void ghiDanhSachRaFile(String tenFile) {
        // Sử dụng try-with-resources để tự động đóng file khi xong
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            
            // Ghi dòng tiêu đề
            writer.write("HoTen;SoDienThoai;MaKH;MaPhong;TongChiPhiDV;DaThanhToan");
            writer.newLine(); // Xuống dòng mới
            
            // Duyệt qua từng khách hàng trong danh sách
            for (QuanLyKhachHang kh : dsKhachHang.values()) {
                // Lấy chuỗi định dạng từ phương thức ghiFile() của đối tượng khách hàng
                String line = kh.ghiFile();
                
                // Ghi chuỗi đó vào file
                writer.write(line);
                
                // Xuống dòng mới cho khách hàng tiếp theo
                writer.newLine();
            }
            
            System.out.println("Ghi file " + tenFile + " thanh cong!");
            
        } catch (IOException e) {
            // Xử lý nếu có lỗi khi ghi file
            System.err.println("Loi khi ghi file: " + e.getMessage());
        }
    }
}