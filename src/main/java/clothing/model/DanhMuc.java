package clothing.model;

import java.util.List;

public class DanhMuc {
    private final String maDanhMuc;
    private final String tenDanhMuc;
    private final String moTa;

    public DanhMuc(String maDanhMuc, String tenDanhMuc, String moTa ) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
    }
    public String getMaDanhMuc() {return maDanhMuc;}
    public String getTenDanhMuc() {return tenDanhMuc;}
    public String getMoTa() {return moTa;}

}

