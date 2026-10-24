package clothing.dao;

import clothing.data.DataStore;
import clothing.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private final DataStore db;

    public KhachHangDAO(DataStore dataStore) {
        this.db = dataStore;
    }

    // Lấy tất cả khách hàng
    public List<KhachHang> layTatCa() {
        return new ArrayList<>(db.getKhachHangs());
    }

    // Tìm khách hàng theo mã
    public KhachHang timTheoMa(String ma) {
        for (KhachHang kh : db.getKhachHangs()) {
            if (kh.getMaKhachHang().equals(ma)) {
                return kh;
            }
        }
        return null;
    }

    // Thêm khách hàng
    public boolean them(KhachHang kh) {
        if (kh == null) return false;

        if (timTheoMa(kh.getMaKhachHang()) != null) return false;
        return db.getKhachHangs().add(kh);
    }

    // Xóa khách hàng theo mã
    public boolean xoa(String ma) {
        KhachHang kh = timTheoMa(ma);
        if (kh == null)return false;

        // Không xóa khách hàng đang được hóa đơn tham chiếu
        boolean coHoaDon = db.getHoaDons()
                .stream()
                .anyMatch(hd ->
                        ma.equals(hd.getMaKhachHang()));

        if (coHoaDon) return false;
        return db.getKhachHangs().remove(kh);
    }
}
