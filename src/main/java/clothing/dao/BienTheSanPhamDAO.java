package clothing.dao;

import clothing.data.DataStore;
import clothing.model.BienTheSanPham;

import java.util.ArrayList;
import java.util.List;

public class BienTheSanPhamDAO {
    private final DataStore db;

    public BienTheSanPhamDAO(DataStore db) {
        this.db = db;
    }

    // Lấy tất cả biến thể sản phẩm
    public List<BienTheSanPham> layTatCa() {
        return new ArrayList<>(db.getBienTheSanPhams());
    }

    // Tìm biến thể theo mã
    public BienTheSanPham timTheoMa(String maBienThe) {
        if (maBienThe == null) return null;

        for (BienTheSanPham bt : db.getBienTheSanPhams()) {
            if (maBienThe.equals(bt.getMaBienThe())) {
                return bt;
            }
        }
        return null;
    }

    // Thêm biến thể sản phẩm
    public boolean them(BienTheSanPham bt) {
        if (bt == null) return false;

        // Không cho phép trùng mã biến thể
        if (timTheoMa(bt.getMaBienThe()) != null) {
            return false;
        }

        // Kiểm tra sản phẩm mà biến thể tham chiếu có tồn tại không
        boolean coSanPham = db.getSanPhams().stream()
                .anyMatch(sp -> bt.getMaSanPham() != null
                        && bt.getMaSanPham().equals(sp.getMaSanPham()));

        if (!coSanPham) {
            return false;
        }
        return db.getBienTheSanPhams().add(bt);
    }

    // Xóa biến thể sản phẩm
    public boolean xoa(String maBienThe) {
        BienTheSanPham bt = timTheoMa(maBienThe);

        if (bt == null) {
            return false;
        }

        // Không xóa nếu biến thể đã được sử dụng trong chi tiết hóa đơn
        boolean coChiTietHoaDon = db.getChiTietHoaDons().stream()
                .anyMatch(ct -> maBienThe.equals(ct.getMaBienThe()));

        if (coChiTietHoaDon) {
            return false;
        }

        // Không xóa nếu biến thể đã được sử dụng trong chi tiết phiếu nhập
        boolean coChiTietPhieuNhap = db.getChiTietPhieuNhaps().stream()
                .anyMatch(ct -> maBienThe.equals(ct.getMaBienThe()));

        if (coChiTietPhieuNhap) {
            return false;
        }
        return db.getBienTheSanPhams().remove(bt);
    }
}
