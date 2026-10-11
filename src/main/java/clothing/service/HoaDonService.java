package clothing.service;

import clothing.model.ChiTietHoaDon;
import clothing.model.HoaDon;
import clothing.model.KhuyenMai;
import clothing.model.LoaiGiam;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                .map(km -> {
                    BigDecimal gtriGiam = km.getGiaTriGiam();

                    if (km.getLoaiGiam() == LoaiGiam.PHAN_TRAM) {
                        return tongTienBD.multiply(gtriGiam)
                                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                    }
                    else {
                        return gtriGiam;
                    }
                })
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
