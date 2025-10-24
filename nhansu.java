package doanoop;

public class nhansu extends nguoi {
    private String manv;
    private String chucvu;
    private double luong;

    public nhansu(String manv, String hoten, String sodienthoai, String chucvu, double luong) {
        super(hoten, sodienthoai);
        this.manv = manv;
        this.chucvu = chucvu;
        this.luong = luong;
    }

    public void hienthi() {
        System.out.println(this.toString());
    }

    public String getManv() {
        return manv;
    }

    public String getChucvu() {
        return chucvu;
    }

    public double getLuong() {
        return luong;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "Ma nv: " + manv + " | Ho ten: " + hoten + " | So dien thoai: " + sodienthoai + " | Chuc vu: " + chucvu + " | Luong: " + luong;
    }
}