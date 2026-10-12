package clothing.model;

public enum LoaiGiam {
    PHAN_TRAM("PhanTram"), SO_TIEN("SoTien");
    private final String label;
    LoaiGiam(String label) {
        this.label = label;
    }
    @Override public String toString() {
        return label;
    }
}
