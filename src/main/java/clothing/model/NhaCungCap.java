package clothing.model;

import java.util.List;

public final class NhaCungCap {
    private final int maNhaCungCap;
    private final String tenNhaCungCap;
    private final String soDienThoai;
    private final String diaChi;
    private final List<PhieuNhap> danhSachPhieuNhap;

    public NhaCungCap(
            int maNhaCungCap,
            String tenNhaCungCap,
            String soDienThoai,
            String diaChi,
            List<PhieuNhap> danhSachPhieuNhap
    ) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.danhSachPhieuNhap = danhSachPhieuNhap;
    }
    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }
    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public List<PhieuNhap> getDanhSachPhieuNhap() {
        return danhSachPhieuNhap;
    }
}
