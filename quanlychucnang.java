package doanoop;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

enum TienDo {
    CHUA_HOAN_THANH,
    HOAN_THANH
}

/**
 * SỬA 1: Lớp Phong đã được đổi tên thành "PhongKhachSan" 
 * để tránh xung đột với class Phong trong file chucnang.java
 */
class PhongKhachSan { // <<< ĐỔI TÊN
    private String maPhong;
    private boolean daDat;
    private double giaPhong;

    // SỬA 2: Đổi tên hàm tạo
    public PhongKhachSan(String maPhong, double giaPhong) { // <<< ĐỔI TÊN
        this.maPhong = maPhong;
        this.giaPhong = giaPhong;
        this.daDat = false;
    }

    public String getMaPhong() { return maPhong; }
    public boolean isDaDat() { return daDat; }
    public double getGiaPhong() { return giaPhong; }

    public void datPhong() { this.daDat = true; }
    public void traPhong() { this.daDat = false; }

    @Override
    public String toString() {
        return "+ Phong: " + maPhong +
               " | Gia: " + giaPhong +
               " | Trang thai: " + (daDat ? "Da dat" : "Trong");
    }
}

/**
 * SỬA 3: Lớp DatPhong đã được đổi tên thành "DatPhongKhachSan" 
 * để tránh xung đột với class DatPhong trong file chucnang.java
 */
class DatPhongKhachSan { // <<< ĐỔI TÊN
    private String maPhong;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private TienDo tienDo;
    private double tongTien;

    // SỬA 4: Đổi tên hàm tạo
    public DatPhongKhachSan(String maPhong, LocalDate batDau, LocalDate ketThuc, double giaPhong) { // <<< ĐỔI TÊN
        this.maPhong = maPhong;
        this.ngayBatDau = batDau;
        this.ngayKetThuc = ketThuc;
        this.tienDo = TienDo.CHUA_HOAN_THANH;
        this.tongTien = tinhTien(giaPhong);
    }

    private double tinhTien(double giaPhong) {
        long soNgay = ChronoUnit.DAYS.between(ngayBatDau, ngayKetThuc);
        if (soNgay <= 0) soNgay = 1;
        return soNgay * giaPhong;
    }

    public String getMaPhong() { return maPhong; }
    public TienDo getTienDo() { return tienDo; }
    public void capNhatTienDo(TienDo td) { this.tienDo = td; }
    public double getTongTien() { return tongTien; }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "| Phong: " + maPhong +
               " | Ngay: " + ngayBatDau.format(dtf) + " - " + ngayKetThuc.format(dtf) +
               " | Tien do: " + tienDo +
               " | Tong tien: " + tongTien + " VND |";
    }
}

class ThongTinDichVu {
    protected String maDichVu;
    protected String tenDichVu;
    protected double giaDichVu;

    public ThongTinDichVu(String maDichVu, String tenDichVu, double giaDichVu) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
    }

    public void xuatThongTin() {
        System.out.println("Ma DV: " + maDichVu + " | Ten: " + tenDichVu + " | Gia: " + giaDichVu + " VND");
    }
}

class DatLich extends ThongTinDichVu {
    private LocalDate ngayThucHien;
    private String maPhong;
    private TienDo tienDo;

    public DatLich(String maDichVu, String tenDichVu, double giaDichVu,
                   String maPhong, LocalDate ngayThucHien) {
        super(maDichVu, tenDichVu, giaDichVu);
        this.maPhong = maPhong;
        this.ngayThucHien = ngayThucHien;
        this.tienDo = TienDo.CHUA_HOAN_THANH;
    }

    public void capNhatTienDo(TienDo td) { this.tienDo = td; }
    public double getGiaDichVu() { return giaDichVu; }

    @Override
    public void xuatThongTin() {
        System.out.println("| MaDV: " + maDichVu + 
                           " | TenDV: " + tenDichVu +
                           " | MaPhong: " + maPhong +
                           " | Ngay: " + ngayThucHien +
                           " | Gia: " + giaDichVu +
                           " | Tien do: " + tienDo + " |");
    }
}

class QuanLyChucNang {
    // SỬA 5: Cập nhật kiểu dữ liệu của List
    private List<PhongKhachSan> dsPhong = new ArrayList<>();
    private List<DatPhongKhachSan> dsDatPhong = new ArrayList<>();
    private List<DatLich> dsDatLich = new ArrayList<>();

    // SỬA 6: Cập nhật tham số
    public void themPhong(PhongKhachSan p) { dsPhong.add(p); } 
    public void hienThiPhong() {
        System.out.println("\n+---------------- DANH SACH PHONG ----------------+");
        // SỬA 7: Cập nhật kiểu
        for (PhongKhachSan p : dsPhong) System.out.println(p); 
        System.out.println("+------------------------------------------------+");
    }

    public boolean kiemTraPhongTrong(String maPhong) {
        // SỬA 8: Cập nhật kiểu
        for (PhongKhachSan p : dsPhong) 
            if (p.getMaPhong().equalsIgnoreCase(maPhong))
                return !p.isDaDat();
        return false;
    }

    public void datPhong(String maPhong, LocalDate batDau, LocalDate ketThuc) {
        // SỬA 9: Cập nhật kiểu
        for (PhongKhachSan p : dsPhong) { 
            if (p.getMaPhong().equalsIgnoreCase(maPhong)) {
                if (p.isDaDat()) {
                    System.out.println(">>> Phong da duoc dat!");
                    return;
                }
                p.datPhong();
                // SỬA 10: Gọi hàm tạo của class đã đổi tên
                dsDatPhong.add(new DatPhongKhachSan(maPhong, batDau, ketThuc, p.getGiaPhong()));
                System.out.println(">>> Dat phong thanh cong!");
                return;
            }
        }
        System.out.println(">>> Khong tim thay phong!");
    }

    public void datLichDichVu(DatLich lich) {
        dsDatLich.add(lich);
        System.out.println(">>> Dat dich vu thanh cong!");
    }

    public void hienThiDichVu() {
        System.out.println("\n+------------- DANH SACH DAT DICH VU ------------+");
        if (dsDatLich.isEmpty()) System.out.println("| Chua co dich vu nao!                          |");
        else for (DatLich dl : dsDatLich) dl.xuatThongTin();
        System.out.println("+------------------------------------------------+");
    }

    public void capNhatTienDoPhong(String maPhong, TienDo td) {
        // SỬA 11: Cập nhật kiểu
        for (DatPhongKhachSan dp : dsDatPhong) 
            if (dp.getMaPhong().equalsIgnoreCase(maPhong)) {
                dp.capNhatTienDo(td);
                if (td == TienDo.HOAN_THANH)
                    // SỬA 12: Cập nhật kiểu
                    for (PhongKhachSan p : dsPhong) 
                        if (p.getMaPhong().equalsIgnoreCase(maPhong))
                            p.traPhong();
                System.out.println(">>> Da cap nhat tien do phong " + maPhong);
                return;
            }
        System.out.println(">>> Khong tim thay don dat phong!");
    }

    public double tinhTongDoanhThu() {
        double tong = 0;
        // SỬA 13: Cập nhật kiểu
        for (DatPhongKhachSan dp : dsDatPhong) 
            if (dp.getTienDo() == TienDo.HOAN_THANH)
                tong += dp.getTongTien();
        for (DatLich dl : dsDatLich)
            if (dl != null)
                tong += dl.getGiaDichVu();
        return tong;
    }

    public void hienThiDatPhong() {
        System.out.println("\n+------------- DANH SACH DAT PHONG --------------+");
        if (dsDatPhong.isEmpty())
            System.out.println("| Chua co don dat phong nao!                    |");
        else
            // SỬA 14: Cập nhật kiểu
            for (DatPhongKhachSan dp : dsDatPhong) 
                System.out.println(dp);
        System.out.println("+------------------------------------------------+");
    }
}

public class quanlychucnang {
    public static void main(String[] args) {
        QuanLyChucNang ql = new QuanLyChucNang();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // SỬA 15: Gọi hàm tạo của class đã đổi tên
        ql.themPhong(new PhongKhachSan("P101", 500000));
        ql.themPhong(new PhongKhachSan("P102", 600000));
        ql.themPhong(new PhongKhachSan("P103", 700000));

        while (true) {
            System.out.println("\n==============================================");
            System.out.println("   MENU QUAN LY CHUC NANG KHACH SAN");
            System.out.println("==============================================");
            System.out.println("1. Xem danh sach phong");
            System.out.println("2. Kiem tra phong trong");
            System.out.println("3. Dat phong");
            System.out.println("4. Cap nhat tien do phong");
            System.out.println("5. Xem danh sach dat phong");
            System.out.println("6. Dat dich vu");
            System.out.println("7. Xem danh sach dich vu");
            System.out.println("8. Tinh tong doanh thu");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chon = sc.nextInt(); sc.nextLine();

            if (chon == 0) { System.out.println(">>> Ket thuc chuong trinh!"); break; }

            switch (chon) {
                case 1 -> ql.hienThiPhong();
                case 2 -> {
                    System.out.print("Nhap ma phong can kiem tra: ");
                    String ma = sc.nextLine();
                    System.out.println(ql.kiemTraPhongTrong(ma)
                            ? ">>> Phong con trong." : ">>> Phong da duoc dat.");
                }
                case 3 -> {
                    System.out.print("Nhap ma phong: ");
                    String maP = sc.nextLine();
                    System.out.print("Ngay bat dau (dd/MM/yyyy): ");
                    String ngayBD = sc.nextLine();
                    System.out.print("Ngay ket thuc (dd/MM/yyyy): ");
                    String ngayKT = sc.nextLine();
                    try {
                        LocalDate bd = LocalDate.parse(ngayBD, dtf);
                        LocalDate kt = LocalDate.parse(ngayKT, dtf);
                        ql.datPhong(maP, bd, kt);
                    } catch (DateTimeParseException e) {
                        System.out.println(">>> Loi dinh dang ngay!");
                    }
                }
                case 4 -> {
                    System.out.print("Nhap ma phong can cap nhat tien do: ");
                    String maCN = sc.nextLine();
                    ql.capNhatTienDoPhong(maCN, TienDo.HOAN_THANH);
                }
                case 5 -> ql.hienThiDatPhong();
                case 6 -> {
                    System.out.print("Nhap ma dich vu: ");
                    String maDV = sc.nextLine();
                    System.out.print("Nhap ten dich vu: ");
                    String tenDV = sc.nextLine();
                    System.out.print("Nhap gia dich vu: ");
                    double gia = sc.nextDouble(); sc.nextLine();
                    System.out.print("Nhap ma phong su dung dich vu: ");
                    String maPhong = sc.nextLine();
                    System.out.print("Nhap ngay thuc hien (dd/MM/yyyy): ");
                    String ngay = sc.nextLine();
                    try {
                        LocalDate ngayTH = LocalDate.parse(ngay, dtf);
                        ql.datLichDichVu(new DatLich(maDV, tenDV, gia, maPhong, ngayTH));
                    } catch (Exception e) {
                        System.out.println(">>> Loi dinh dang ngay!");
                    }
                }
                case 7 -> ql.hienThiDichVu();
                case 8 -> System.out.println("\n>>> Tong doanh thu: " + ql.tinhTongDoanhThu() + " VND");
                default -> System.out.println(">>> Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
