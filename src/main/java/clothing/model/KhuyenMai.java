package clothing.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public final class KhuyenMai {
    private final String maKhuyenMai;
    private final String tenKhuyenMai;
    private final LoaiGiam loaiGiam;
    private final BigDecimal giaTriGiam;    // giam bao nhieu %
    private final LocalDate ngayBatDau;
    private final LocalDate ngayKetThuc;
    private final BigDecimal donToiThieu;

    public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, LoaiGiam loaiGiam, BigDecimal giaTriGiam,
                     LocalDate ngayBatDau, LocalDate ngayKetThuc, BigDecimal donToiThieu) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiGiam = loaiGiam;
        this.giaTriGiam = giaTriGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.donToiThieu = donToiThieu;
    }

    public String getMaKhuyenMai() { return maKhuyenMai; }

    public String getTenKhuyenMai() { return tenKhuyenMai; }

    public LoaiGiam getLoaiGiam() { return loaiGiam; }

    public BigDecimal getGiaTriGiam() { return giaTriGiam; }

    public LocalDate getNgayBatDau() { return ngayBatDau; }

    public LocalDate getNgayKetThuc() { return ngayKetThuc; }

    public BigDecimal getDonToiThieu() { return donToiThieu; }
}

