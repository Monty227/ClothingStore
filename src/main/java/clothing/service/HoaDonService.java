package clothing.service;

import clothing.model.ChiTietHoaDon;
import clothing.model.HoaDon;
import clothing.model.KhuyenMai;

import java.math.BigDecimal;
import java.util.List;

public class HoaDonService {
    // Tong tien truoc khi tru tienGiam
    public BigDecimal getTongTienBD(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons) {

        return chiTietHoaDons.stream()
                .filter(cthd -> cthd.getMaHoaDon().equals(hoaDon.getMaHoaDon()))
                .map(ChiTietHoaDon::getThanhTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTienGiam(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons,
                                  List<KhuyenMai> khuyenMais) {

        BigDecimal tongTienBD = getTongTienBD(hoaDon, chiTietHoaDons);

        return khuyenMais.stream()
                .filter(km -> km.getMaKhuyenMai().equals(hoaDon.getMaKhuyenMai()))
                .map(KhuyenMai::getGiaTriGiam)
                .map(gtriGiam -> tongTienBD.multiply(BigDecimal.valueOf(gtriGiam)))
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTongTien(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons,
                                  List<KhuyenMai> khuyenMais) {

        BigDecimal tongTienBD = getTongTienBD(hoaDon, chiTietHoaDons);
        BigDecimal tienGiam = getTienGiam(hoaDon, chiTietHoaDons, khuyenMais);
        return tongTienBD.subtract(tienGiam);
    }
}
