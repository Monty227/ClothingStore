package clothing.model;

import java.time.LocalDate;
import java.util.List;

public final class NhanVien {
    private final int maNhanVien;
    private final String hoTen;
    private final String soDienThoai;
    private final String chucVu;
    private final double luongCoBan;
    private final LocalDate ngayVaoLam;

    private final List<HoaDon> danhSachHoaDon;
    private final List<PhieuNhap> danhSachPhieuNhap;

    public NhanVien(
            int maNhanVien,
            String hoTen,
            String soDienThoai,
            String chucVu,
            double luongCoBan,
            LocalDate ngayVaoLam,
            List<HoaDon> danhSachHoaDon,
            List<PhieuNhap> danhSachPhieuNhap ) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;
        this.ngayVaoLam = ngayVaoLam;
        this.danhSachHoaDon = danhSachHoaDon;
        this.danhSachPhieuNhap = danhSachPhieuNhap;
    }
    public int getMaNhanVien() {
        return maNhanVien;
    }
    public String getHoTen() {
        return hoTen;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public String getChucVu() {
        return chucVu;
    }
    public double getLuongCoBan() {
        return luongCoBan;
    }
    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }
    public List<HoaDon> getDanhSachHoaDon() {
        return danhSachHoaDon;
    }
    public List<PhieuNhap> getDanhSachPhieuNhap() {
        return danhSachPhieuNhap;
    }
}