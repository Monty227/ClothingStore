package clothing.dao;

import clothing.data.DataStore;
import clothing.model.ChiTietHoaDon;

import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    private final DataStore dataStore;

    public ChiTietHoaDonDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // Lấy tất cả chi tiết hóa đơn
    public List<ChiTietHoaDon> layTatCa() {
        return new ArrayList<>(dataStore.getChiTietHoaDons());
    }

    // Tìm chi tiết theo mã hóa đơn và mã biến thể
    public ChiTietHoaDon timTheoMa(String maHoaDon, String maBienThe) {
        for (ChiTietHoaDon cthd : dataStore.getChiTietHoaDons()) {
            if (maHoaDon != null
                    && maHoaDon.equals(cthd.getMaHoaDon())
                    && maBienThe != null
                    && maBienThe.equals(cthd.getMaBienThe())) {
                return cthd;
            }
        }

        return null;
    }

    // Thêm chi tiết hóa đơn
    public boolean them(ChiTietHoaDon cthd) {
        if (cthd == null) {
            return false;
        }

        // Kiểm tra hóa đơn có tồn tại không
        boolean coHoaDon = dataStore.getHoaDons().stream()
                .anyMatch(hd -> cthd.getMaHoaDon() != null
                        && cthd.getMaHoaDon().equals(hd.getMaHoaDon()));

        if (!coHoaDon) {
            return false;
        }

        // Kiểm tra biến thể sản phẩm có tồn tại không
        boolean coBienThe = dataStore.getBienTheSanPhams().stream()
                .anyMatch(bt -> cthd.getMaBienThe() != null
                        && cthd.getMaBienThe().equals(bt.getMaBienThe()));

        if (!coBienThe) {
            return false;
        }

        // Không cho phép trùng mã hóa đơn và mã biến thể
        if (timTheoMa(cthd.getMaHoaDon(), cthd.getMaBienThe()) != null) {
            return false;
        }

        return dataStore.getChiTietHoaDons().add(cthd);
    }

    // Xóa chi tiết hóa đơn
    public boolean xoa(String maHoaDon, String maBienThe) {
        ChiTietHoaDon cthd = timTheoMa(maHoaDon, maBienThe);

        if (cthd == null) return false;
        return dataStore.getChiTietHoaDons().remove(cthd);
    }
}
