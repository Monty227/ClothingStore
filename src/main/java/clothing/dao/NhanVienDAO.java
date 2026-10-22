package clothing.dao;

import clothing.data.DataStore;
import clothing.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private final DataStore dataStore;

    public NhanVienDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả nhân viên
    public List<NhanVien> layTatCa() {
        return new ArrayList<>(dataStore.getNhanViens());
    }

    // 2. Tìm nhân viên theo mã
    public NhanVien timTheoMa(String ma) {
        for (NhanVien nv : dataStore.getNhanViens()) {
            if (ma != null && ma.equals(nv.getMaNhanVien())) {
                return nv;
            }
        }
        return null;
    }

    // 3. Thêm nhân viên
    public boolean them(NhanVien nv) {
        if (nv == null) return false;

        // Không cho phép trùng mã nhân viên
        if (timTheoMa(nv.getMaNhanVien()) != null) {
            return false;
        }
        return dataStore.getNhanViens().add(nv);
    }

    // 4. Xóa nhân viên theo mã
    public boolean xoa(String ma) {
        NhanVien nv = timTheoMa(ma);

        if (nv == null) return false;

        // Không xóa nếu nhân viên đã lập hóa đơn
        boolean coHoaDon = dataStore.getHoaDons()
                .stream()
                .anyMatch(hd ->
                        ma != null && ma.equals(hd.getMaNhanVien()));

        if (coHoaDon) {
            return false;
        }

        // Không xóa nếu nhân viên đã lập phiếu nhập
        boolean coPhieuNhap = dataStore.getPhieuNhaps()
                .stream()
                .anyMatch(pn ->
                        ma != null && ma.equals(pn.getMaNhanVien()));

        if (coPhieuNhap) {
            return false;
        }

        return dataStore.getNhanViens().remove(nv);
    }
}
