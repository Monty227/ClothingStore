package clothing.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class PhieuNhap {
    private final String maPhieuNhap;
    private final LocalDate ngayNhap;
    private final String maNhaCungCap;
    private final String maNhanVien;
    private final BigDecimal tongTien;

    public PhieuNhap( String maPhieuNhap, LocalDate ngayNhap, String maNhaCungCap, String maNhanVien, BigDecimal tongTien ) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
    }

    public String getMaPhieuNhap() { return maPhieuNhap; }
    public LocalDate getNgayNhap() { return ngayNhap; }
    public String getMaNhaCungCap() { return maNhaCungCap; }
    public String  getMaNhanVien() { return maNhanVien; }
    public BigDecimal getTongTien() { return tongTien; }
    @Override
    public String toString() {
        return "Mã phiếu nhập: " + maPhieuNhap
                + "\nNgày nhập: " + ngayNhap
                + "\nMã nhà cung cấp: " + maNhaCungCap
                + "\nMã nhân viên: " + maNhanVien
                + "\nTổng tiền: " + tongTien;
    }
}
