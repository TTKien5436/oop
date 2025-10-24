package doanoop;

import java.util.Scanner;
import java.util.ArrayList;

public class quanlynhansu {
    private ArrayList<nhansu> danhsach = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public quanlynhansu() {
        danhsach.add(new nhansu("NV001", "Nguyen Van A", "0957483947", "Nhan vien", 5000));
        danhsach.add(new nhansu("NV002", "Thai Minh Tam", "05738592045", "Nhan vien", 5000));
        danhsach.add(new nhansu("QL001", "Tran Minh Tien", "0867384957", "Quan ly", 10000));
    }

    private boolean existMa(String ma) {
        for (nhansu ns : danhsach) {
            if (ns.getManv().equalsIgnoreCase(ma)) return true;
        }
        return false;
    }

    public void themnhansu() {
        String ma;
        while (true) {
            System.out.print("Nhap ma nv: ");
            ma = sc.nextLine();
            if (ma.isEmpty()) {
                System.out.println("Ma NV khong duoc de trong.");
            } else if (existMa(ma)) {
                System.out.println("Ma NV da ton tai, vui long nhap lai.");
            } else {
                break;
            }
        }

        System.out.print("Nhap ho ten: ");
        String ten = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String sodienthoai = sc.nextLine();
        System.out.print("Nhap chuc vu: ");
        String cv = sc.nextLine();
        double luong;
        while (true) {
            System.out.print("Nhap luong: ");
            try {
                luong = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Luong khong hop le, vui long nhap lai.");
            }
        }

        nhansu ns = new nhansu(ma, ten, sodienthoai, cv, luong);
        danhsach.add(ns);
        System.out.println("Da them nhan su thanh cong!");
    }

    public void hienthids() {
        if (danhsach.isEmpty()) {
            System.out.println("Danh sach rong!");
        } else {
            System.out.println("\n--- DANH SACH NHAN SU ---");
            for (nhansu ns : danhsach) {
                ns.hienthi();
            }
            System.out.println("-------------------------");
        }
    }

    public void xoanhansu() {
        System.out.print("Nhap ma NV can xoa: ");
        String ma = sc.nextLine();
        boolean removed = danhsach.removeIf(ns -> ns.getManv().equalsIgnoreCase(ma));
        if (removed) {
            System.out.println("Da xoa nhan su (ma=" + ma + ") thanh cong.");
        } else {
            System.out.println("Khong tim thay nhan su ma: " + ma);
        }
    }
    
    public void timkiemtheoma() {
        System.out.print("Nhap ma nhan vien can tim: ");
        String ma = sc.nextLine();
        boolean found = false;
        for (nhansu ns : danhsach) {
            if (ns.getManv().equalsIgnoreCase(ma)) {
                System.out.println("Tim thay nhan vien: ");
                ns.hienthi();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay nhan vien ma: " + ma);
        }
    }

    public void menu() {
        int chon;
        do {
            System.out.println("\n===== QUAN LY NHAN SU =====");
            System.out.println("1. Them nhan su");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Xoa nhan su");
            System.out.println("4. Tim kiem nhan su theo ma");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
                chon = -1;
            }

            switch (chon) {
                case 1 -> themnhansu();
                case 2 -> hienthids();
                case 3 -> xoanhansu();
                case 4 -> timkiemtheoma();
                case 0 -> System.out.println("Thoat chuong trinh!");
                default -> System.out.println("Chon sai, vui long chon lai!");
            }
        } while (chon != 0);
    }

    public static void main(String[] args) {
        quanlynhansu ql = new quanlynhansu();
        ql.menu();
    }
}