package clothing.service;

import clothing.model.ChiTietHoaDon;
import clothing.model.HoaDon;
import clothing.model.KhuyenMai;
import clothing.model.LoaiGiam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class HoaDonService {
    public BigDecimal getTongHDBD(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons) {

        return chiTietHoaDons.stream()
                .filter(cthd -> cthd.getMaHoaDon().equals(hoaDon.getMaHoaDon()))
                .map(ChiTietHoaDon::getThanhTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTienGiam(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons,
                                  List<KhuyenMai> khuyenMais) {

        BigDecimal tongHDBD = getTongHDBD(hoaDon, chiTietHoaDons);

        if (hoaDon.getMaKhuyenMai() == null) {
            return BigDecimal.ZERO;
        }

        return khuyenMais.stream()
                .filter(km -> km.getMaKhuyenMai().equals(hoaDon.getMaKhuyenMai()))
                .filter(km -> tongHDBD.compareTo(km.getDonToiThieu()) >= 0)
                .filter(km -> {
                    LocalDate ngayLap = hoaDon.getNgayLap().toLocalDate();

                    return !ngayLap.isBefore(km.getNgayBatDau()) &&
                            !ngayLap.isAfter(km.getNgayKetThuc());
                })
                .map(km -> {
                    BigDecimal gtriGiam = km.getGiaTriGiam();

                    if (km.getLoaiGiam() == LoaiGiam.PHAN_TRAM) {
                        return tongHDBD.multiply(gtriGiam)
                                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                    }
                    else {
                        return gtriGiam;
                    }
                })
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTongHD(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons,
                                  List<KhuyenMai> khuyenMais) {

        BigDecimal tongHDBD = getTongHDBD(hoaDon, chiTietHoaDons);
        BigDecimal tienGiam = getTienGiam(hoaDon, chiTietHoaDons, khuyenMais);
        if (tienGiam.compareTo(tongHDBD) > 0) {
            tienGiam = tongHDBD;
        }
        return tongHDBD.subtract(tienGiam);
    }
}
