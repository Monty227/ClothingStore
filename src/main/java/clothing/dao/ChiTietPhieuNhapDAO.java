package clothing.dao;

import clothing.data.DataStore;
import clothing.model.ChiTietPhieuNhap;

import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapDAO {
    private final DataStore dataStore;

    public ChiTietPhieuNhapDAO(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // 1. Lấy tất cả chi tiết phiếu nhập
    public List<ChiTietPhieuNhap> layTatCa() {
        return new ArrayList<>(dataStore.getChiTietPhieuNhaps());
    }

    // 2. Tìm chi tiết theo mã phiếu nhập và mã biến thể
    public ChiTietPhieuNhap timTheoMa(String maPhieuNhap, String maBienThe) {
        for (ChiTietPhieuNhap ctpn : dataStore.getChiTietPhieuNhaps()) {
            if (maPhieuNhap != null
                    && maPhieuNhap.equals(ctpn.getMaPhieuNhap())
                    && maBienThe != null
                    && maBienThe.equals(ctpn.getMaBienThe())) {
                return ctpn;
            }
        }
        return null;
    }

    // 3. Thêm chi tiết phiếu nhập
    public boolean them(ChiTietPhieuNhap ctpn) {
        if (ctpn == null) return false;

        // Kiểm tra phiếu nhập có tồn tại
        boolean coPhieuNhap = dataStore.getPhieuNhaps()
                .stream()
                .anyMatch(pn ->
                        ctpn.getMaPhieuNhap() != null
                                && ctpn.getMaPhieuNhap().equals(pn.getMaPhieuNhap()));

        if (!coPhieuNhap) {
            return false;
        }

        // Kiểm tra biến thể sản phẩm có tồn tại
        boolean coBienThe = dataStore.getBienTheSanPhams()
                .stream()
                .anyMatch(bt ->
                        ctpn.getMaBienThe() != null
                                && ctpn.getMaBienThe().equals(bt.getMaBienThe()));

        if (!coBienThe) {
            return false;
        }

        // Không cho phép trùng cặp mã phiếu nhập và mã biến thể
        if (timTheoMa(ctpn.getMaPhieuNhap(), ctpn.getMaBienThe()) != null) {
            return false;
        }
        return dataStore.getChiTietPhieuNhaps().add(ctpn);
    }

    // 4. Xóa chi tiết phiếu nhập
    public boolean xoa(String maPhieuNhap, String maBienThe) {
        ChiTietPhieuNhap ctpn = timTheoMa(maPhieuNhap, maBienThe);

        if (ctpn == null) return false;
        return dataStore.getChiTietPhieuNhaps().remove(ctpn);
    }
}
