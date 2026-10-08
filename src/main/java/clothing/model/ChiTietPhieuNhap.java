package clothing.model;

import java.math.BigDecimal;

public final class ChiTietPhieuNhap {

    private final int maPhieuNhap;
    private final int maBienThe;
    private final int soLuong;
    private final BigDecimal giaNhap;
    private final PhieuNhap phieuNhap;
    private final BienTheSanPham bienTheSanPham;

    public ChiTietPhieuNhap(
            int maPhieuNhap,
            int maBienThe,
            int soLuong,
            BigDecimal giaNhap,
            PhieuNhap phieuNhap,
            BienTheSanPham bienTheSanPham) {
        this.maPhieuNhap = maPhieuNhap;
        this.maBienThe = maBienThe;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.phieuNhap = phieuNhap;
        this.bienTheSanPham = bienTheSanPham;
    }
    public int getMaPhieuNhap() { return maPhieuNhap; }
    public int getMaBienThe() { return maBienThe; }
    public int getSoLuong() { return soLuong; }
    public BigDecimal getGiaNhap() { return giaNhap; }
    public PhieuNhap getPhieuNhap() { return phieuNhap; }
    public BienTheSanPham getBienTheSanPham() { return bienTheSanPham; }
}
