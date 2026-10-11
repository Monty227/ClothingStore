package clothing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class HoaDon {
    private final String maHoaDon;
    private final LocalDateTime ngayLap;
    private final String maKhachHang;
    private final String maNhanVien;
    private final String maKhuyenMai;
    private final String phuongThucThanhToan;
    private final String trangThai;

    public HoaDon(String maHoaDon, LocalDateTime ngayLap, String maKhachHang, String maNhanVien,
                  String maKhuyenMai, String phuongThucThanhToan, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.maKhuyenMai = maKhuyenMai;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() { return maHoaDon; }

    public LocalDateTime getNgayLap() { return ngayLap; }

    public String getMaKhachHang() { return maKhachHang; }

    public String getMaNhanVien() { return maNhanVien; }

    public String getMaKhuyenMai() { return maKhuyenMai; }

    public String getPhuongThucThanhToan() { return phuongThucThanhToan; }

    public String getTrangThai() { return trangThai; }

//    public BigDecimal getTongTienBD() {
//        return ;
//    }
//
//    public BigDecimal getTienGiam() {
//
//    }
}