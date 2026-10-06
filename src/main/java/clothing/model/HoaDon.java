package clothing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HoaDon {
    private final int maHoaDon;
    private final LocalDateTime ngayLap;
    private final int maKhachHang;
    private final int maNhanVien;
    private final int maKhuyenMai;
    private final BigDecimal tongTien;
    private final BigDecimal tienGiam;
    private final BigDecimal thanhTien;
    private final String phuongThucThanhToan;
    private final String trangThai;

    private final KhachHang khachHang;
    private final NhanVien nhanVien;
    private final KhuyenMai khuyenMai;
    private final List<ChiTietHoaDon> chiTietHoaDons;

    public HoaDon(int maHoaDon, LocalDateTime ngayLap, int maKhachHang, int maNhanVien,
                  int maKhuyenMai, BigDecimal tongTien, BigDecimal tienGiam, BigDecimal thanhTien,
                  String phuongThucThanhToan, String trangThai, KhachHang khachHang, NhanVien nhanVien,
                  KhuyenMai khuyenMai, List<ChiTietHoaDon> chiTietHoaDons) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.maKhuyenMai = maKhuyenMai;
        this.tongTien = tongTien;
        this.tienGiam = tienGiam;
        this.thanhTien = thanhTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.khuyenMai = khuyenMai;
        this.chiTietHoaDons = chiTietHoaDons;
    }
}
