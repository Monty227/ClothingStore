package clothing.model;

import java.math.BigDecimal;
import java.util.List;

public class SanPham {
    private final int maSanPham;
    private final String tenSanPham;
    private final String thuongHieu;
    private final String gioiTinh;
    private final BigDecimal giaBan;
    private final int maDanhMuc;
    private final List<BienTheSanPham> bienTheSanPhams;

    public SanPham(int maSanPham, String tenSanPham,String thuongHieu, String gioiTinh, BigDecimal giaBan,int maDanhMuc, List<BienTheSanPham> bienTheSanPhams ) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.thuongHieu = thuongHieu;
        this.gioiTinh = gioiTinh;
        this.giaBan = giaBan;
        this.maDanhMuc = maDanhMuc;
        this.bienTheSanPhams = bienTheSanPhams;
    }

    public int getMaSanPham() { return maSanPham; }

    public String getTenSanPham() { return tenSanPham; }

    public String getThuongHieu() { return thuongHieu; }

    public String getGioiTinh() { return gioiTinh; }

    public BigDecimal getGiaBan() { return giaBan; }

    public int getMaDanhMuc() { return maDanhMuc; }

    public List<BienTheSanPham> getBienTheSanPhams() { return bienTheSanPhams; }

}
