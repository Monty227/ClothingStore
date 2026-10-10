package clothing.model;

import java.math.BigDecimal;
public class SanPham {
    private final int maSanPham;
    private final String tenSanPham;
    private final String thuongHieu;
    private final String gioiTinh;
    private final BigDecimal giaBan;
    private final int maDanhMuc;

    public SanPham(int maSanPham, String tenSanPham,String thuongHieu, String gioiTinh, BigDecimal giaBan,int maDanhMuc ) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.thuongHieu = thuongHieu;
        this.gioiTinh = gioiTinh;
        this.giaBan = giaBan;
        this.maDanhMuc = maDanhMuc;
    }

    public int getMaSanPham() { return maSanPham; }

    public String getTenSanPham() { return tenSanPham; }

    public String getThuongHieu() { return thuongHieu; }

    public String getGioiTinh() { return gioiTinh; }

    public BigDecimal getGiaBan() { return giaBan; }

    public int getMaDanhMuc() { return maDanhMuc; }

}
