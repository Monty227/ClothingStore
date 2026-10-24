package clothing.dao;

import clothing.data.DataStore;
import clothing.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private final DataStore dataStore;

    public SanPhamDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả sản phẩm
    public List<SanPham> layTatCa() {
        return new ArrayList<>(dataStore.getSanPhams());
    }

    // 2. Tìm sản phẩm theo mã
    public SanPham timTheoMa(String ma) {
        for (SanPham sp : dataStore.getSanPhams()) {
            if (ma != null && ma.equals(sp.getMaSanPham())) {
                return sp;
            }
        }
        return null;
    }

    // 3. Thêm sản phẩm
    public boolean them(SanPham sp) {
        if (sp == null) return false;

        // Không cho phép trùng mã sản phẩm
        if (timTheoMa(sp.getMaSanPham()) != null) {
            return false;
        }

        // Kiểm tra danh mục có tồn tại
        boolean coDanhMuc = dataStore.getDanhMucs()
                .stream()
                .anyMatch(dm ->
                        sp.getMaDanhMuc() != null
                                && sp.getMaDanhMuc().equals(dm.getMaDanhMuc()));

        if (!coDanhMuc) {
            return false;
        }

        return dataStore.getSanPhams().add(sp);
    }

    // 4. Xóa sản phẩm theo mã
    public boolean xoa(String ma) {
        SanPham sp = timTheoMa(ma);
        if (sp == null) return false;

        // Không xóa nếu sản phẩm còn biến thể
        boolean coBienThe = dataStore.getBienTheSanPhams()
                .stream()
                .anyMatch(bt ->
                        ma != null && ma.equals(bt.getMaSanPham()));

        if (coBienThe) {
            return false;
        }

        return dataStore.getSanPhams().remove(sp);
    }
}
