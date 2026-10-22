package clothing.model;

public class BienTheSanPham {

    private final String maBienThe;
    private final String maSanPham;
    private final KichCo kichCo;
    private final String mauSac;
    private final int soLuongTon;
    private final String maVach;

    public BienTheSanPham( String maBienThe, String maSanPham, KichCo kichCo, String mauSac, int soLuongTon, String maVach) {
        this.maBienThe = maBienThe;
        this.maSanPham = maSanPham;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.soLuongTon = soLuongTon;
        this.maVach = maVach;

    }
    public String getMaBienThe() { return maBienThe; }

    public String getMaSanPham() { return maSanPham; }

    public KichCo getKichCo() { return kichCo; }

    public String getMauSac() { return mauSac; }

    public int getSoLuongTon() { return soLuongTon; }

    public String getMaVach() { return maVach; }

    @Override
    public String toString() {
        return "Mã biến thể: " + maBienThe
                + "\nMã sản phẩm: " + maSanPham
                + "\nKích cỡ: " + kichCo
                + "\nMàu sắc: " + mauSac
                + "\nSố lượng tồn: " + soLuongTon
                + "\nMã vạch: " + maVach;
    }
}
