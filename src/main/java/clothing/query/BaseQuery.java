package clothing.query;

import clothing.data.DataStore;
import clothing.model.KhachHang;
import clothing.model.NhanVien;
import clothing.util.Row;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class BaseQuery {
    protected final DataStore db;

    public BaseQuery(DataStore db) {
        this.db = Objects.requireNonNull(db);
    }

    public abstract List<String> getTitles();
    public abstract List<String> getHeaders();
    public abstract List<Row> execute(int number, Scanner sc);

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