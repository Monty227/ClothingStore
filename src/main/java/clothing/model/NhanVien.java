package clothing.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class NhanVien {
    private final String maNhanVien;
    private final String hoTen;
    private final String soDienThoai;
    private final chucVu chucVu;
    private final BigDecimal luongCoBan;
    private final LocalDate ngayVaoLam;

    public NhanVien( String maNhanVien, String hoTen, String soDienThoai, chucVu chucVu, BigDecimal luongCoBan, LocalDate ngayVaoLam ) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;
        this.ngayVaoLam = ngayVaoLam;
    }
    public String getMaNhanVien() { return maNhanVien; }
    public String getHoTen() { return hoTen; }
    public String getSoDienThoai() { return soDienThoai; }
    public chucVu getChucVu() { return chucVu; }
    public BigDecimal getLuongCoBan() { return luongCoBan; }
    public LocalDate getNgayVaoLam() { return ngayVaoLam; }
    @Override
    public String toString() {
        return "Mã nhân viên: " + maNhanVien
                + "\nHọ tên: " + hoTen
                + "\nSố điện thoại: " + soDienThoai
                + "\nChức vụ: " + chucVu
                + "\nLương cơ bản: " + luongCoBan
                + "\nNgày vào làm: " + ngayVaoLam;
    }
}