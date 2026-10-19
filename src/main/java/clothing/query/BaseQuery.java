package clothing.query;

import clothing.data.DataStore;
import clothing.model.*;
import clothing.util.Row;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class BaseQuery {
    protected final DataStore db;

    public BaseQuery(DataStore db) {
        this.db = Objects.requireNonNull(db);
    }

    public abstract List<String> getTitles();
    public abstract List<String> getHeaders();
    public abstract List<Row> execute(int number, Scanner sc);

    protected String timTenKH(String maKH) {
        return db.getKhachHangs().stream()
                .filter(kh -> kh.getMaKhachHang().equals(maKH))
                .map(KhachHang::getHoTen)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Mã khách hàng: " + maKH));
    }

    protected String timTenNV(String maNV) {
        return db.getNhanViens().stream()
                .filter(nv -> nv.getMaNhanVien().equals(maNV))
                .map(NhanVien::getHoTen)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Mã nhân viên: " + maNV));
    }

    protected BigDecimal getTongHDBD(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons) {

        return chiTietHoaDons.stream()
                .filter(cthd -> cthd.getMaHoaDon().equals(hoaDon.getMaHoaDon()))
                .map(ChiTietHoaDon::getThanhTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected BigDecimal getTienGiam(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons,
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

    protected BigDecimal getTongHD(HoaDon hoaDon, List<ChiTietHoaDon> chiTietHoaDons,
                                List<KhuyenMai> khuyenMais) {

        BigDecimal tongHDBD = getTongHDBD(hoaDon, chiTietHoaDons);
        BigDecimal tienGiam = getTienGiam(hoaDon, chiTietHoaDons, khuyenMais);
        if (tienGiam.compareTo(tongHDBD) > 0) {
            tienGiam = tongHDBD;
        }
        return tongHDBD.subtract(tienGiam);
    }
}