package clothing.model;

import java.math.BigDecimal;

public final class ChiTietPhieuNhap {
    private final String maPhieuNhap;
    private final String maBienThe;
    private final int soLuong;
    private final BigDecimal giaNhap;
    private final BigDecimal thanhTien;

    public ChiTietPhieuNhap( String maPhieuNhap, String maBienThe, int soLuong, BigDecimal giaNhap ) {
        this.maPhieuNhap = maPhieuNhap;
        this.maBienThe = maBienThe;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        // tính thành tiền = số lượng * giá nhập
        this.thanhTien = giaNhap.multiply(BigDecimal.valueOf(soLuong));
    }
    public String getMaPhieuNhap() { return maPhieuNhap; }
    public String getMaBienThe() { return maBienThe; }
    public int getSoLuong() { return soLuong; }
    public BigDecimal getGiaNhap() { return giaNhap; }
    public BigDecimal getThanhTien() { return thanhTien; }
    @Override
    public String toString() {
        return "Mã phiếu nhập: " + maPhieuNhap
                + "\nMã biến thể sản phẩm: " + maBienThe
                + "\nSố lượng nhập: " + soLuong
                + "\nGiá nhập: " + giaNhap;
    }
}
