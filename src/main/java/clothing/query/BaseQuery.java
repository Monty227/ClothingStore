package clothing.query;

import clothing.data.DataStore;
import clothing.model.KhachHang;
import clothing.model.KhuyenMai;
import clothing.model.NhanVien;
import clothing.model.SanPham;
import clothing.util.Row;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class BaseQuery {
    protected final DataStore db;

    public BaseQuery(DataStore db) {
        this.db = Objects.requireNonNull(db);
    }

    public abstract List<String> getTitles();
    public abstract List<String> getHeaders();
    protected abstract List<Supplier<List<Row>>> getQueries();

    public List<Row> execute(int number) {
        List<Supplier<List<Row>>> queries = getQueries();

        if (number < 1 || number > 10) throw new IllegalArgumentException("Chọn câu từ 1 đến 10");
        return queries.get(number - 1).get();
    }

    protected String timTenKH(String maKH) {
        return db.getKhachHangs().stream()
                .filter(kh -> kh.getMaKhachHang().equals(maKH))
                .map(KhachHang::getHoTen)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Mã khách hàng: " + maKH));
    }

    protected String timTenNV(String maNV) {
        return db.getNhanViens().stream()
                .filter(nv -> nv.getMaNhanVien().equals(maNV))
                .map(NhanVien::getHoTen)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Mã nhân viên: " + maNV));
    }
}