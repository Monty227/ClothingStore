package clothing.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public final class PhieuNhap {
    private final int maPhieuNhap;
    private final LocalDate ngayNhap;
    private final int maNhaCungCap;
    private final int maNhanVien;
    private final BigDecimal tongTien;
    private final NhaCungCap nhaCungCap;
    private final NhanVien nhanVien;
    private final List<ChiTietPhieuNhap> danhSachChiTiet;

    public PhieuNhap(
            int maPhieuNhap,
            LocalDate ngayNhap,
            int maNhaCungCap,
            int maNhanVien,
            BigDecimal tongTien,
            NhaCungCap nhaCungCap,
            NhanVien nhanVien,
            List<ChiTietPhieuNhap> danhSachChiTiet) {

        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;
        this.danhSachChiTiet = danhSachChiTiet;
    }

    public int getMaPhieuNhap() { return maPhieuNhap; }
    public LocalDate getNgayNhap() { return ngayNhap; }
    public int getMaNhaCungCap() { return maNhaCungCap; }
    public int getMaNhanVien() { return maNhanVien; }
    public BigDecimal getTongTien() { return tongTien; }
    public NhaCungCap getNhaCungCap() { return nhaCungCap; }
    public NhanVien getNhanVien() { return nhanVien; }
    public List<ChiTietPhieuNhap> getDanhSachChiTiet() { return danhSachChiTiet; }
}
