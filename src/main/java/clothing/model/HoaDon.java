package clothing.model;

import java.time.LocalDateTime;

public final class HoaDon {
    private final String maHoaDon;
    private final LocalDateTime ngayLap;
    private final String maKhachHang;
    private final String maNhanVien;
    private final String maKhuyenMai;
    private final PhuongThucThanhToan phuongThucThanhToan;
    private final TrangThaiDonHang trangThai;

    public HoaDon(String maHoaDon, LocalDateTime ngayLap, String maKhachHang, String maNhanVien,
                  String maKhuyenMai, PhuongThucThanhToan phuongThucThanhToan, TrangThaiDonHang trangThai) {
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

    public PhuongThucThanhToan getPhuongThucThanhToan() { return phuongThucThanhToan; }

    public TrangThaiDonHang getTrangThai() { return trangThai; }

    @Override
    public String toString() {
        return "Mã hóa đơn: " + maHoaDon
                + "\nNgày lập: " + ngayLap
                + "\nMã khách hàng: " + maKhachHang
                + "\nMã nhân viên: " + maNhanVien
                + "\nMã khuyến mãi: "
                + (maKhuyenMai == null ? "Không áp dụng" : maKhuyenMai)
                + "\nPhương thức thanh toán: " + phuongThucThanhToan
                + "\nTrạng thái: " + trangThai;
    }
}