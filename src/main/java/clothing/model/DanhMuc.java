package clothing.model;

import java.util.List;

public class DanhMuc {
    private final String maDanhMuc;
    private final String tenDanhMuc;
    private final String moTa;

    private final List<SanPham>  sanphams;

    public DanhMuc(String maDanhMuc, String tenDanhMuc, String moTa, List<SanPham> sanphams ) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
        this.sanphams = sanphams;
    }
    public String getMaDanhMuc() {return maDanhMuc;}
    public String getTenDanhMuc() {return tenDanhMuc;}
    public String getMoTa() {return moTa;}
    public List<SanPham> getSanphams() {return sanphams;}
}

