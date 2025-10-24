package doanoop;

import java.util.Scanner;

public class TestKhachSan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DanhSachKhachHang ds = new DanhSachKhachHang();
        int chon;

        do {
            System.out.println("\n===== MENU QUAN LY KHACH SAN =====");
            System.out.println("1. Nhap khach hang moi");
            System.out.println("2. Hien thi danh sach khach hang");
            System.out.println("3. Them dich vu cho khach hang");
            System.out.println("4. Thanh toan cho khach hang");
            System.out.println("5. Huy dat phong");
            System.out.println("6. Thong ke doanh thu");
            System.out.println("7. Liet ke khach chua thanh toan");
            System.out.println("8. Sap xep khach theo tong tien");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so!");
                chon = -1; // Đặt giá trị không hợp lệ để lặp lại
            }


            switch (chon) {
                case 1 -> {
                    try {
                        System.out.print("Nhap ho ten: ");
                        String hoTen = sc.nextLine();
                        System.out.print("Nhap so dien thoai: ");
                        String sdt = sc.nextLine();
                        System.out.print("Nhap ma phong (VD: P101): ");
                        String maPhong = sc.nextLine();
                        System.out.print("Nhap ngay bat dau (dd/MM/yyyy): ");
                        String ngayBD = sc.nextLine();
                        System.out.print("Nhap ngay ket thuc (dd/MM/yyyy): ");
                        String ngayKT = sc.nextLine();
                        System.out.print("Nhap don gia phong: ");
                        double gia = Double.parseDouble(sc.nextLine());

                        ThongTinPhong phong = new ThongTinPhong(maPhong, ngayBD, ngayKT, gia);
                        QuanLyKhachHang kh = new QuanLyKhachHang(hoTen, sdt, phong);
                        if (ds.themKhachHang(kh)) {
                            System.out.println("Them khach hang thanh cong! Ma KH: " + kh.getMaKH());
                        }
                    } catch (Exception e) {
                        System.out.println("Loi: " + e.getMessage());
                    }
                }
                case 2 -> ds.xuatDanhSach();
                case 3 -> {
                    System.out.print("Nhap ma khach hang: ");
                    String ma = sc.nextLine();
                    QuanLyKhachHang kh = ds.timTheoMaKH(ma);
                    if (kh != null) {
                        System.out.print("Nhap chi phi dich vu them: ");
                        long cp = Long.parseLong(sc.nextLine());
                        kh.themDichVu(cp);
                        System.out.println("Da them dich vu cho KH " + kh.gethoten());
                    } else {
                        System.out.println("Khong tim thay khach hang!");
                    }
                }
                case 4 -> {
                    System.out.print("Nhap ma khach hang: ");
                    String ma = sc.nextLine();
                    QuanLyKhachHang kh = ds.timTheoMaKH(ma);
                    if (kh != null) {
                        kh.thanhToan();
                        System.out.println("Khach hang " + kh.gethoten() + " da thanh toan.");
                    } else {
                        System.out.println("Khong tim thay khach hang!");
                    }
                }
                case 5 -> {
                    System.out.print("Nhap ma khach hang: ");
                    String ma = sc.nextLine();
                    QuanLyKhachHang kh = ds.timTheoMaKH(ma);
                    if (kh != null) {
                        kh.huyDatPhong();
                    } else {
                        System.out.println("Khong tim thay khach hang!");
                    }
                }
                case 6 -> System.out.println("Tong doanh thu (tu cac hoa don da thanh toan): " + ds.tinhTongDoanhThu());
                case 7 -> {
                    System.out.println("Khach chua thanh toan:");
                    var listChuaThanhToan = ds.lietKeChuaThanhToan();
                    if(listChuaThanhToan.isEmpty()){
                        System.out.println("Tat ca khach hang da thanh toan.");
                    } else {
                        for (QuanLyKhachHang k : listChuaThanhToan) {
                            System.out.println(" - " + k.getMaKH() + " | " + k.gethoten());
                        }
                    }
                }
                case 8 -> {
                    System.out.println("Danh sach khach theo tong tien giam dan:");
                    for (QuanLyKhachHang k : ds.sapXepTheoTongTien()) {
                        System.out.println(k.getMaKH() + " | " + k.gethoten() + " | " + k.getTongTien());
                    }
                }
                case 0 -> System.out.println("Thoat chuong trinh...");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);

        sc.close();
    }
}