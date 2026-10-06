package clothing.model;

import java.math.BigDecimal;

public class ChiTietHoaDon {
    private final int maHoaDon;
    private final int maBienThe;
    private final int soLuong;
    private final BigDecimal donGia;

    private final HoaDon hoaDon;
    private final BienTheSanPham bienTheSanPham;

    public ChiTietHoaDon(int maHoaDon, int maBienThe, int soLuong, BigDecimal donGia,
                         HoaDon hoaDon, BienTheSanPham bienTheSanPham) {
        this.maHoaDon = maHoaDon;
        this.maBienThe = maBienThe;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.hoaDon = hoaDon;
        this.bienTheSanPham = bienTheSanPham;
    }
}
