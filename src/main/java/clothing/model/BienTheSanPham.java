package clothing.model;

import java.util.List;

public class BienTheSanPham {

    private final String maBienThe;
    private final String maSanPham;
    private final String kichCo;
    private final String mauSac;
    private final int soLuongTon;
    private final String maVach;

    public BienTheSanPham( String maBienThe, String maSanPham, String kichCo, String mauSac, int soLuongTon, String maVach,SanPham sanPham, List<ChiTietHoaDon>  chiTietHoaDons, List<ChiTietPhieuNhap> chiTietPhieuNhaps) {
        this.maBienThe = maBienThe;
        this.maSanPham = maSanPham;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.soLuongTon = soLuongTon;
        this.maVach = maVach;

    }
    public String getMaBienThe() { return maBienThe; }

    public String getMaSanPham() { return maSanPham; }

    public String getKichCo() { return kichCo; }

    public String getMauSac() { return mauSac; }

    public int getSoLuongTon() { return soLuongTon; }

    public String getMaVach() { return maVach; }

}
