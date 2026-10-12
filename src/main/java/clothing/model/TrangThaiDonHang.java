package clothing.model;

public enum TrangThaiDonHang {
    DA_THANH_TOAN("DaThanhToan"), DA_HUY("DaHuy"), DOI_TRA("DoiTra");
    private final String label;
    TrangThaiDonHang(String label) {
        this.label = label;
    }
    @Override public String toString() {
        return label;
    }
}