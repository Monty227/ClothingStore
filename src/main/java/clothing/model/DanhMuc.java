package clothing.model;

import java.util.List;

public class DanhMuc {
    private final int maDanhMuc;
    private final String tenDanhMuc;
    private final String moTa;

    private final List<SanPham>  sanphams;

    public DanhMuc(int maDanhMuc, String tenDanhMuc, String moTa, List<SanPham> sanphams ) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
        this.sanphams = sanphams;
    }
    public int getMaDanhMuc() {return maDanhMuc;}
    public String getTenDanhMuc() {return tenDanhMuc;}
    public String getMoTa() {return moTa;}
    public List<SanPham> getSanphams() {return sanphams;}
}
