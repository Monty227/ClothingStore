package clothing.dao;

import clothing.data.DataStore;
import clothing.model.KhuyenMai;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO {
    private final DataStore dataStore;

    public KhuyenMaiDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả khuyến mãi
    public List<KhuyenMai> layTatCa() {
        return new ArrayList<>(dataStore.getKhuyenMais());
    }

    // 2. Tìm khuyến mãi theo mã
    public KhuyenMai timTheoMa(String ma) {
        for (KhuyenMai km : dataStore.getKhuyenMais()) {
            if (ma != null && ma.equals(km.getMaKhuyenMai())) {
                return km;
            }
        }
        return null;
    }

    // 3. Thêm khuyến mãi
    public boolean them(KhuyenMai km) {
        if (km == null) return false;

        // Không cho phép trùng mã khuyến mãi
        if (timTheoMa(km.getMaKhuyenMai()) != null) {
            return false;
        }
        return dataStore.getKhuyenMais().add(km);
    }

    // 4. Xóa khuyến mãi theo mã
    public boolean xoa(String ma) {
        KhuyenMai km = timTheoMa(ma);
        if (km == null) return false;

        // Không xóa nếu khuyến mãi đã được sử dụng trong hóa đơn
        boolean dangDuocSuDung = dataStore.getHoaDons()
                .stream()
                .anyMatch(hd ->
                        ma != null && ma.equals(hd.getMaKhuyenMai()));

        if (dangDuocSuDung) return false;
        return dataStore.getKhuyenMais().remove(km);
    }
}
