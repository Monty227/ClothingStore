package clothing.dao;

import clothing.data.DataStore;
import clothing.model.PhieuNhap;

import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    private final DataStore dataStore;

    public PhieuNhapDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả phiếu nhập
    public List<PhieuNhap> layTatCa() {
        return new ArrayList<>(dataStore.getPhieuNhaps());
    }

    // 2. Tìm phiếu nhập theo mã
    public PhieuNhap timTheoMa(String ma) {
        for (PhieuNhap pn : dataStore.getPhieuNhaps()) {
            if (ma != null && ma.equals(pn.getMaPhieuNhap())) {
                return pn;
            }
        }
        return null;
    }

    // 3. Thêm phiếu nhập
    public boolean them(PhieuNhap pn) {
        if (pn == null) return false;

        // Không cho phép trùng mã phiếu nhập
        if (timTheoMa(pn.getMaPhieuNhap()) != null) {
            return false;
        }

        // Kiểm tra nhà cung cấp tồn tại
        boolean coNhaCungCap = dataStore.getNhaCungCaps()
                .stream()
                .anyMatch(ncc ->
                        pn.getMaNhaCungCap() != null
                                && pn.getMaNhaCungCap().equals(ncc.getMaNhaCungCap()));

        if (!coNhaCungCap) {
            return false;
        }

        // Kiểm tra nhân viên tồn tại
        boolean coNhanVien = dataStore.getNhanViens()
                .stream()
                .anyMatch(nv ->
                        pn.getMaNhanVien() != null
                                && pn.getMaNhanVien().equals(nv.getMaNhanVien()));

        if (!coNhanVien) {
            return false;
        }
        return dataStore.getPhieuNhaps().add(pn);
    }

    // 4. Xóa phiếu nhập theo mã
    public boolean xoa(String ma) {
        PhieuNhap pn = timTheoMa(ma);
        if (pn == null) return false;

        // Không xóa nếu còn chi tiết phiếu nhập
        boolean coChiTiet = dataStore.getChiTietPhieuNhaps()
                .stream()
                .anyMatch(ct -> ma.equals(ct.getMaPhieuNhap()));

        if (coChiTiet) {
            return false;
        }
        return dataStore.getPhieuNhaps().remove(pn);
    }
}
