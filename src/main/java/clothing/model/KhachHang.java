package clothing.model;

import java.time.LocalDate;
import java.util.List;

public final class KhachHang {
    private final int maKhachHang;
    private final String hoTen;
    private final String soDienThoai;
    private final String email;
    private final LocalDate ngaySinh;
    private final int diemTichLuy;
    private final String hangThanhVien;

    public KhachHang(int maKhachHang, String hoTen, String soDienThoai, String email,
                     LocalDate ngaySinh, int diemTichLuy, String hangThanhVien) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.diemTichLuy = diemTichLuy;
        this.hangThanhVien = hangThanhVien;
    }

    public int getMaKhachHang() { return maKhachHang; }

    public String getHoTen() { return hoTen; }

    public String getSoDienThoai() { return soDienThoai; }

    public String getEmail() { return email; }

    public LocalDate getNgaySinh() { return ngaySinh; }

    public int getDiemTichLuy() { return diemTichLuy; }

    public String getHangThanhVien() { return hangThanhVien; }
}