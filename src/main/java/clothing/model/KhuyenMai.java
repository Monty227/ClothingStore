package clothing.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class KhuyenMai {
    private final int maKhuyenMai;
    private final String tenKhuyenMai;
    private final String loaiGiam;

    private final LocalDate ngayBatDau;
    private final LocalDate ngayKetThuc;
    private final BigDecimal donToiThieu;

    private final List<HoaDon> hoaDons;

    public KhuyenMai(int maKhuyenMai, String tenKhuyenMai, String loaiGiam, LocalDate ngayBatDau,
                     LocalDate ngayKetThuc, BigDecimal donToiThieu, List<HoaDon> hoaDons) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiGiam = loaiGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.donToiThieu = donToiThieu;
        this.hoaDons = hoaDons;
    }

    public int getMaKhuyenMai() { return maKhuyenMai; }

    public String getTenKhuyenMai() { return tenKhuyenMai; }

    public String getLoaiGiam() { return loaiGiam; }

    public LocalDate getNgayBatDau() { return ngayBatDau; }

    public LocalDate getNgayKetThuc() { return ngayKetThuc; }

    public BigDecimal getDonToiThieu() { return donToiThieu; }

    public List<HoaDon> getHoaDons() { return hoaDons; }
}

