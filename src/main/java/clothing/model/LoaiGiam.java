package clothing.model;

public enum LoaiGiam {
    PHAN_TRAM("PhanTram"), SO_TIEN("SoTien");
    public final String label;
    LoaiGiam(String label) {
        this.label = label;
    }
    @Override public String toString() {
        return label;
    }
}
