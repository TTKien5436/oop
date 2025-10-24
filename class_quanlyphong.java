package doanoop;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

class ThongTinPhong{
   // private final ThongTinKhachHang KhachHang
    private final String MaPhong;
    private LocalDate NgayBatDau;
    private LocalDate NgayKetThuc;
    private boolean CheckTraPhong;
    private final double DonGia;
    private double TongTien;
    private static int dem=1;
    public ThongTinPhong( String NgayBatDau, String NgayKetThuc,double DonGia){ 
        if(!CheckLimitPhong()) throw new IllegalArgumentException("Da dat gioi han phong cua khach san. Vui long doi trong vai phut !");
        this.MaPhong="P"+String.valueOf(dem);
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
        if (this.NgayBatDau.isAfter(this.NgayKetThuc)){
            System.out.println("Khong hop le !");
            return false;
        }
        return true;
    }
}
class Booking{
    QuanLyPhong qLyPhong=new QuanLyPhong();
    ThongTinPhong thongtin;
    public void NhapThongTin(){
        Scanner input = new Scanner(System.in);
        String nhap=" ";
        System.out.println("Khi muon thoat ban chi can an q");
        while (!nhap.equals("q")){
            System.out.println("Nhap ngay bat dau (dd/MM/yyyy): ");
            String ngaybatdau=input.nextLine();
            System.out.println("Nhap ngay ket thuc (dd/MM/yyyy): ");
            String ngayketthuc=input.nextLine();
            thongtin=new ThongTinPhong(ngaybatdau,ngayketthuc,200);
            qLyPhong.themPhong(thongtin);
            nhap=input.nextLine();
        }
        input.close();
        
    }
    

}
class QuanLyPhong{
    private final Map<String,ThongTinPhong> danhSachPhong;
    public QuanLyPhong(){
        danhSachPhong = new HashMap<>(20);
        for (int i = 1; i <= 20; i++) {
            danhSachPhong.put("P" + i, null);
        }
    }
    public void themPhong(ThongTinPhong phong){
        if (danhSachPhong.containsKey(phong.getMaPhong()) && danhSachPhong.get(phong.getMaPhong()) == null)
            danhSachPhong.put(phong.getMaPhong(),phong);
    }
    public Map<String,ThongTinPhong> getDanhSachPhong(){
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