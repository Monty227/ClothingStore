package clothing.dao;

import clothing.data.DataStore;
import clothing.model.DanhMuc;

import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {
    private final DataStore dataStore;

    public DanhMucDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả danh mục
    public List<DanhMuc> layTatCa() {
        return new ArrayList<>(dataStore.getDanhMucs());
    }

    // 2. Tìm danh mục theo mã
    public DanhMuc timTheoMa(String ma) {
        for (DanhMuc dm : dataStore.getDanhMucs()) {
            if (ma != null && ma.equals(dm.getMaDanhMuc())) {
                return dm;
            }
        }
        return null;
    }

    // 3. Thêm danh mục
    public boolean them(DanhMuc dm) {
        if (dm == null) return false;

        // Không cho phép trùng mã danh mục
        if (timTheoMa(dm.getMaDanhMuc()) != null) {
            return false;
        }
        return dataStore.getDanhMucs().add(dm);
    }

    // 4. Xóa danh mục theo mã
    public boolean xoa(String ma) {
        DanhMuc dm = timTheoMa(ma);
        if (dm == null) return false;

        // Không xóa nếu còn sản phẩm thuộc danh mục
        boolean coSanPham = dataStore.getSanPhams()
                .stream()
                .anyMatch(sp ->
                        ma != null && ma.equals(sp.getMaDanhMuc()));

        if (coSanPham) {
            return false;
        }
        return dataStore.getDanhMucs().remove(dm);
    }
}
