package clothing.dao;

import clothing.data.DataStore;
import clothing.model.NhaCungCap;

import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {
    private final DataStore dataStore;

    public NhaCungCapDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả nhà cung cấp
    public List<NhaCungCap> layTatCa() {
        return new ArrayList<>(dataStore.getNhaCungCaps());
    }

    // 2. Tìm nhà cung cấp theo mã
    public NhaCungCap timTheoMa(String ma) {
        for (NhaCungCap ncc : dataStore.getNhaCungCaps()) {
            if (ma != null && ma.equals(ncc.getMaNhaCungCap())) {
                return ncc;
            }
        }
        return null;
    }

    // 3. Thêm nhà cung cấp
    public boolean them(NhaCungCap ncc) {
        if (ncc == null) {
            return false;
        }

        // Không cho phép trùng mã nhà cung cấp
        if (timTheoMa(ncc.getMaNhaCungCap()) != null) {
            return false;
        }

        return dataStore.getNhaCungCaps().add(ncc);
    }

    // 4. Xóa nhà cung cấp theo mã
    public boolean xoa(String ma) {
        NhaCungCap ncc = timTheoMa(ma);

        if (ncc == null) {
            return false;
        }

        // Không xóa nếu nhà cung cấp đã có phiếu nhập
        boolean coPhieuNhap = dataStore.getPhieuNhaps()
                .stream()
                .anyMatch(pn ->
                        ma != null && ma.equals(pn.getMaNhaCungCap()));

        if (coPhieuNhap) {
            return false;
        }

        return dataStore.getNhaCungCaps().remove(ncc);
    }
}
