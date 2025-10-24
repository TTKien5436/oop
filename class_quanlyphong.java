package doanoop;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * SỬA 1: Đổi tên class 'ThongTinPhong' thành 'ThongTinPhong_Test' 
 * để tránh xung đột với file public ThongTinPhong.java
 */
class ThongTinPhong_Test {
   // private final ThongTinKhachHang KhachHang
    private final String MaPhong;
    private LocalDate NgayBatDau;
    private LocalDate NgayKetThuc;
    private boolean CheckTraPhong;
    private final double DonGia;
    private double TongTien;
    private static int dem=1;

    // SỬA 2: Đổi tên hàm tạo (constructor)
    public ThongTinPhong_Test( String NgayBatDau, String NgayKetThuc,double DonGia){ 
        if(!CheckLimitPhong()) throw new IllegalArgumentException("Da dat gioi han phong cua khach san. Vui long doi trong vai phut !");
        
        // SỬA 3 (Lỗi logic): Tăng biến dem (dem++) để mã phòng tiếp theo là P2, P3...
        this.MaPhong="P"+String.valueOf(dem++);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            this.NgayBatDau = LocalDate.parse(NgayBatDau, dtf);
            this.NgayKetThuc = LocalDate.parse(NgayKetThuc, dtf);
        } catch (DateTimeParseException e) {
            System.out.println("Loi dinh dang ngay thang. Vui long nhap lai theo dinh dang dd/MM/yyyy.");
            this.NgayBatDau = null;
            this.NgayKetThuc = null;
        }
        if (!CheckNgayDatPhong()) throw new IllegalArgumentException("Khong the tao phong: du lieu khong hop le !");
        this.CheckTraPhong=false;
        this.DonGia=DonGia;
        if(this.NgayBatDau != null && this.NgayKetThuc != null){
            long soNgayO = ChronoUnit.DAYS.between(this.NgayBatDau, this.NgayKetThuc);
            
            // SỬA 4 (Lỗi logic): Đảm bảo tính ít nhất 1 ngày
            if (soNgayO <= 0) soNgayO = 1; 
            
            this.TongTien=soNgayO * DonGia;
        }
        else this.TongTien=0;
    }
    public String getMaPhong(){
        return MaPhong;
    }
    public LocalDate getNgayBatDau(){
        return this.NgayBatDau;
    }
    public LocalDate getNgayKetThuc(){
        return this.NgayKetThuc;
    }
    public double getDonGia(){
        return this.DonGia;
    }
    public double getTongTien(){
      return this.TongTien;
    }
    public boolean CheckLimitPhong(){
        if (dem>20) return false;
        return true;
    }
    public boolean isCheckTraPhong(){
        return this.CheckTraPhong;
    }
    private boolean CheckNgayDatPhong(){
        // Kiểm tra null trước khi gọi isAfter
        if (this.NgayBatDau == null || this.NgayKetThuc == null) return false;
        
        if (this.NgayBatDau.isAfter(this.NgayKetThuc)){
            System.out.println("Ngay bat dau khong the sau ngay ket thuc!");
            return false;
        }
        return true;
    }
}
class Booking{
    QuanLyPhong qLyPhong=new QuanLyPhong();
    
    // SỬA 5: Sử dụng class 'ThongTinPhong_Test' đã đổi tên
    ThongTinPhong_Test thongtin;
    
    public void NhapThongTin(){
        Scanner input = new Scanner(System.in);
        String nhap=" ";
        System.out.println("Khi muon thoat ban chi can an q");
        while (!nhap.equals("q")){
            System.out.println("Nhap ngay bat dau (dd/MM/yyyy): ");
            String ngaybatdau=input.nextLine();
            System.out.println("Nhap ngay ket thuc (dd/MM/yyyy): ");
            String ngayketthuc=input.nextLine();
            
            try {
                // SỬA 6: Gọi hàm tạo 'ThongTinPhong_Test'
                thongtin = new ThongTinPhong_Test(ngaybatdau, ngayketthuc, 200);
                // Chỉ thêm phòng nếu ngày hợp lệ
                if (thongtin.getNgayBatDau() != null) { 
                    qLyPhong.themPhong(thongtin);
                    System.out.println("Da them phong: " + thongtin.getMaPhong());
                } else {
                    System.out.println("Them phong khong thanh cong do loi ngay.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Loi: " + e.getMessage());
            }

            System.out.println("Nhan 'q' de thoat, nhan phim bat ky de tiep tuc:");
            nhap=input.nextLine();
        }
        // input.close(); // Không nên đóng System.in
    }
    

}
class QuanLyPhong{
    // SỬA 7: Sử dụng class 'ThongTinPhong_Test' trong Map
    private final Map<String,ThongTinPhong_Test> danhSachPhong;
    
    public QuanLyPhong(){
        danhSachPhong = new HashMap<>(20);
        // Khởi tạo 20 slot phòng trống (P1 đến P20)
        for (int i = 1; i <= 20; i++) {
            danhSachPhong.put("P" + i, null);
        }
    }

    // SỬA 8: Sử dụng class 'ThongTinPhong_Test' làm tham số
    public void themPhong(ThongTinPhong_Test phong){
        String maPhongMoi = phong.getMaPhong();
        if (danhSachPhong.containsKey(maPhongMoi)) {
            if(danhSachPhong.get(maPhongMoi) == null) {
                danhSachPhong.put(maPhongMoi, phong);
            } else {
                System.out.println("Loi: Phong " + maPhongMoi + " da duoc dat roi!");
            }
        } else {
             System.out.println("Loi: Ma phong " + maPhongMoi + " khong hop le (Vuot qua 20 phong).");
        }
    }
    
    // SỬA 9: Sử dụng class 'ThongTinPhong_Test' làm kiểu trả về
    public Map<String,ThongTinPhong_Test> getDanhSachPhong(){
        if (danhSachPhong!=null)return danhSachPhong;
        return null;
    }
} 
public class class_quanlyphong {
    public static void main(String args[]){
        Booking book=new Booking();
        book.NhapThongTin();
    }   
}
