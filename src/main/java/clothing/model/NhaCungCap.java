package clothing.model;

public final class NhaCungCap {
    private final String maNhaCungCap;
    private final String tenNhaCungCap;
    private final String soDienThoai;
    private final String diaChi;

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String soDienThoai, String diaChi) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }
    public String getMaNhaCungCap() { return maNhaCungCap; }
    public String getTenNhaCungCap() { return tenNhaCungCap; }
    public String getSoDienThoai() { return soDienThoai; }
    public String getDiaChi() { return diaChi; }
    @Override
    public String toString() {
        return "Mã nhà cung cấp: " + maNhaCungCap
                + "\nTên nhà cung cấp: " + tenNhaCungCap
                + "\nSố điện thoại: " + soDienThoai
                + "\nĐịa chỉ: " + diaChi;
    }
}