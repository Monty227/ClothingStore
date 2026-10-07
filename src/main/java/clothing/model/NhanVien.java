package clothing.model;

public final class NhanVien {
    private final int maNhanVien;
    private final String hoTen;
    private final String boPhan;

    public NhanVien(int maNhanVien, String hoTen, String boPhan) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.boPhan = boPhan;
    }

    public int getMaNhanVien() { return maNhanVien; }

    public String getHoTen() { return hoTen; }

    public String getBoPhan() { return boPhan; }
}
