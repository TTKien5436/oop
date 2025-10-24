package doanoop;

public class nguoi {
    protected String hoten;
    protected String sodienthoai;

    public nguoi(String hoten , String sodienthoai){
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
    }
    public String gethoten(){
        return hoten;
    }
    public String getsodienthoai(){
        return sodienthoai;
    }
    public void sethoten(String hoten){
        this.hoten = hoten;
    }
    public void setsodienthoai(String sodienthoai){
        this.sodienthoai = sodienthoai;
    }
}
