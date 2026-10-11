package clothing.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class NhanVien {
    private final String maNhanVien;
    private final String hoTen;
    private final String soDienThoai;
    private final String chucVu;
    private final BigDecimal luongCoBan;
    private final LocalDate ngayVaoLam;

    public NhanVien( String maNhanVien, String hoTen, String soDienThoai, String chucVu, BigDecimal luongCoBan, LocalDate ngayVaoLam ) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;
        this.ngayVaoLam = ngayVaoLam;
    }
    public String getMaNhanVien() {
        return maNhanVien;
    }
    public String getHoTen() {
        return hoTen;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public String getChucVu() {
        return chucVu;
    }
    public BigDecimal getLuongCoBan() {
        return luongCoBan;
    }
    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }
}