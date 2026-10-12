package clothing.model;

public enum HangThanhVien {
    THUONG("Thuong"), BAC("Bac"), VANG("Vang"), KIMCUONG("KimCuong");
    private final String label;
    HangThanhVien(String label) {
        this.label = label;
    }
    @Override public String toString() {
        return label;
    }
}
