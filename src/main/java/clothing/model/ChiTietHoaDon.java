package clothing.model;

import java.math.BigDecimal;

public final class ChiTietHoaDon {
    private final String maHoaDon;
    private final String maBienThe;
    private final int soLuong;
    private final BigDecimal donGia;
    private final BigDecimal thanhTien;

    public ChiTietHoaDon(String maHoaDon, String maBienThe, int soLuong,
                         BigDecimal donGia, BigDecimal thanhTien) {
        this.maHoaDon = maHoaDon;
        this.maBienThe = maBienThe;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));
    }

    public String getMaHoaDon() { return maHoaDon; }

    public String getMaBienThe() { return maBienThe; }

    public int getSoLuong() { return soLuong; }

    public BigDecimal getDonGia() { return donGia; }

    public BigDecimal getThanhTien() { return thanhTien; }
}

