package clothing.model;

public enum PhuongThucThanhToan {
    TIEN_MAT("TienMat"), CHUYEN_KHOAN("ChuyenKhoan"), THE("The");
    private final String label;
    PhuongThucThanhToan(String label) {
        this.label = label;
    }
    @Override public String toString() {
        return label;
    }
}
