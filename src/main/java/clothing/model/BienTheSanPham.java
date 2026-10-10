package clothing.model;

import java.util.List;

public class BienTheSanPham {

    private final int maBienThe;
    private final int maSanPham;
    private final String kichCo;
    private final String mauSac;
    private final int soLuongTon;
    private final String maVach;

    private final SanPham sanPham;
    private final List<ChiTietHoaDon>  chiTietHoaDons;
    private final List<ChiTietPhieuNhap> chiTietPhieuNhaps;

    public BienTheSanPham( int maBienThe, int maSanPham, String kichCo, String mauSac, int soLuongTon, String maVach,SanPham sanPham, List<ChiTietHoaDon>  chiTietHoaDons, List<ChiTietPhieuNhap> chiTietPhieuNhaps) {
        this.maBienThe = maBienThe;
        this.maSanPham = maSanPham;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.soLuongTon = soLuongTon;
        this.maVach = maVach;
        this.sanPham = sanPham;
        this.chiTietHoaDons = chiTietHoaDons;
        this.chiTietPhieuNhaps = chiTietPhieuNhaps;

    }
    public int getMaBienThe() { return maBienThe; }

    public int getMaSanPham() { return maSanPham; }

    public String getKichCo() { return kichCo; }

    public String getMauSac() { return mauSac; }

    public int getSoLuongTon() { return soLuongTon; }

    public String getMaVach() { return maVach; }

    public SanPham getSanPham() { return sanPham; }

    public List<ChiTietHoaDon> getChiTietHoaDons() {return chiTietHoaDons;}

    public List<ChiTietPhieuNhap> getChiTietPhieuNhaps() { return chiTietPhieuNhaps; }

}
