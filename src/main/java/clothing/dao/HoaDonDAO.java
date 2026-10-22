package clothing.dao;

import clothing.data.DataStore;
import clothing.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private final DataStore dataStore;

    public HoaDonDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả hóa đơn
    public List<HoaDon> layTatCa() {
        return new ArrayList<>(dataStore.getHoaDons());
    }

    // 2. Tìm hóa đơn theo mã
    public HoaDon timTheoMa(String ma) {
        for (HoaDon hd : dataStore.getHoaDons()) {
            if (hd.getMaHoaDon().equals(ma)) {
                return hd;
            }
        }
        return null;
    }

    // 3. Thêm hóa đơn
    public boolean them(HoaDon hd) {
        if (hd == null) return false;

        // Không cho phép trùng mã hóa đơn
        if (timTheoMa(hd.getMaHoaDon()) != null) {
            return false;
        }

        // Kiểm tra khách hàng có tồn tại
        boolean coKhachHang = dataStore.getKhachHangs()
                .stream()
                .anyMatch(kh ->
                        hd.getMaKhachHang() != null && hd.getMaKhachHang().equals(kh.getMaKhachHang()));

        if (!coKhachHang) return false;

        // Kiểm tra nhân viên tồn tại
        boolean coNhanVien = dataStore.getNhanViens()
                .stream()
                .anyMatch(nv -> hd.getMaNhanVien() != null && nv.getMaNhanVien().equals(hd.getMaNhanVien()));

        if (!coNhanVien) return false;

        return dataStore.getHoaDons().add(hd);
    }

    // 4. Xóa hóa đơn theo mã
    public boolean xoa(String ma) {
        HoaDon hd = timTheoMa(ma);
        if (hd == null) return false;

        // Không xóa nếu hóa đơn còn chi tiết hóa đơn
        boolean coChiTiet = dataStore.getChiTietHoaDons()
                .stream()
                .anyMatch(ct ->
                        ct.getMaHoaDon().equals(ma));

        if (coChiTiet) return false;
        return dataStore.getHoaDons().remove(hd);
    }
}
