package clothing.data;

import clothing.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class DataStore {
    private final List<SanPham> sanPhams;
    private final List<DanhMuc> danhMucs;
    private final List<BienTheSanPham> bienTheSanPhams;
    private final List<KhachHang> khachHangs;
    private final List<HoaDon> hoaDons;
    private final List<ChiTietHoaDon> chiTietHoaDons;
    private final List<KhuyenMai> khuyenMais;
    private final List<NhanVien> nhanViens;
    private final List<NhaCungCap> nhaCungCaps;
    private final List<PhieuNhap> phieuNhaps;
    private final List<ChiTietPhieuNhap> chiTietPhieuNhaps;

    public DataStore() {
        sanPhams = Collections.unmodifiableList(Arrays.asList(
                new SanPham("SP01", "Áo thun Basic", "Mây", GioiTinh.UNISEX, new BigDecimal("250000"), "DM01"),
                new SanPham("SP02", "Áo sơ mi Oxford", "Urban", GioiTinh.NAM, new BigDecimal("300000"), "DM01"),
                new SanPham("SP03", "Áo polo Classic", "Urban", GioiTinh.NAM, new BigDecimal("450000"), "DM01"),
                new SanPham("SP04", "Áo kiểu Linen", "Luna", GioiTinh.NU, new BigDecimal("700000"), "DM01"),
                new SanPham("SP05", "Áo khoác Denim", "North", GioiTinh.UNISEX, new BigDecimal("950000"), "DM01"),
                new SanPham("SP06", "Quần jeans Slim", "North", GioiTinh.NAM, new BigDecimal("650000"), "DM02"),
                new SanPham("SP07", "Quần tây nữ", "Luna", GioiTinh.NU, new BigDecimal("520000"), "DM02"),
                new SanPham("SP08", "Quần short thể thao", "Move", GioiTinh.UNISEX, new BigDecimal("280000"), "DM04"),
                new SanPham("SP09", "Váy midi", "Luna", GioiTinh.NU, new BigDecimal("720000"), "DM03"),
                new SanPham("SP10", "Đầm dự tiệc", "Luna", GioiTinh.NU, new BigDecimal("1250000"), "DM03"),
                new SanPham("SP11", "Áo thể thao", "Move", GioiTinh.TRE_EM, new BigDecimal("390000"), "DM04"),
                new SanPham("SP12", "Thắt lưng da", "Craft", GioiTinh.UNISEX, new BigDecimal("180000"), "DM05")
        ));

        danhMucs = Collections.unmodifiableList(Arrays.asList(
                new DanhMuc("DM01", "Áo", "Áo thun, áo sơ mi và áo khoác"),
                new DanhMuc("DM02", "Quần", "Quần dài và quần short"),
                new DanhMuc("DM03", "Váy", "Váy và đầm"),
                new DanhMuc("DM04", "Đồ thể thao", "Trang phục vận động"),
                new DanhMuc("DM05", "Phụ kiện", "Phụ kiện thời trang")
        ));

        bienTheSanPhams = Collections.unmodifiableList(Arrays.asList(
                new BienTheSanPham("BT01", "SP01", KichCo.S, "Trắng", 5, "893000000001"),
                new BienTheSanPham("BT02", "SP01", KichCo.M, "Đen", 8, "893000000002"),
                new BienTheSanPham("BT03", "SP02", KichCo.M, "Trắng", 10, "893000000003"),
                new BienTheSanPham("BT04", "SP02", KichCo.L, "Xanh", 15, "893000000004"),
                new BienTheSanPham("BT05", "SP03", KichCo.S, "Đen", 0, "893000000005"),
                new BienTheSanPham("BT06", "SP03", KichCo.M, "Đen", 22, "893000000006"),
                new BienTheSanPham("BT07", "SP03", KichCo.L, "Trắng", 7, "893000000007"),
                new BienTheSanPham("BT08", "SP04", KichCo.S, "Be", 4, "893000000008"),
                new BienTheSanPham("BT09", "SP04", KichCo.M, "Be", 12, "893000000009"),
                new BienTheSanPham("BT10", "SP05", KichCo.L, "Xanh", 3, "893000000010"),
                new BienTheSanPham("BT11", "SP05", KichCo.XL, "Đen", 0, "893000000011"),
                new BienTheSanPham("BT12", "SP06", KichCo.M, "Xanh", 18, "893000000012"),
                new BienTheSanPham("BT13", "SP06", KichCo.L, "Đen", 9, "893000000013"),
                new BienTheSanPham("BT14", "SP07", KichCo.S, "Đen", 6, "893000000014"),
                new BienTheSanPham("BT15", "SP07", KichCo.M, "Be", 14, "893000000015"),
                new BienTheSanPham("BT16", "SP08", KichCo.S, "Xám", 25, "893000000016"),
                new BienTheSanPham("BT17", "SP08", KichCo.M, "Xám", 0, "893000000017"),
                new BienTheSanPham("BT18", "SP09", KichCo.S, "Đỏ", 2, "893000000018"),
                new BienTheSanPham("BT19", "SP09", KichCo.M, "Đỏ", 11, "893000000019"),
                new BienTheSanPham("BT20", "SP10", KichCo.M, "Đen", 8, "893000000020"),
                new BienTheSanPham("BT21", "SP10", KichCo.L, "Đen", 5, "893000000021"),
                new BienTheSanPham("BT22", "SP11", KichCo.S, "Xanh", 16, "893000000022"),
                new BienTheSanPham("BT23", "SP11", KichCo.M, "Xanh", 4, "893000000023"),
                new BienTheSanPham("BT24", "SP12", KichCo.M, "Nâu", 30, "893000000024")
        ));

        khachHangs = Collections.unmodifiableList(Arrays.asList(
                new KhachHang("KH01", "Nguyễn Minh Lan", "0901000001", "minhlan@example.com", LocalDate.of(2002, 3, 14), 1250, HangThanhVien.KIMCUONG),
                new KhachHang("KH02", "Trần Quốc Bảo", "0901000002", "quocbao@example.com", LocalDate.of(2001, 7, 22), 820, HangThanhVien.VANG),
                new KhachHang("KH03", "Lê Thu Hà", "0901000003", "thuha@example.com", LocalDate.of(2003, 1, 9), 450, HangThanhVien.BAC),
                new KhachHang("KH04", "Phạm Gia Huy", "0901000004", "giahuy@example.com", LocalDate.of(2000, 11, 30), 80, HangThanhVien.THUONG),
                new KhachHang("KH05", "Võ Ngọc Lan", "0901000005", "ngoclan@example.com", LocalDate.of(2002, 5, 18), 990, HangThanhVien.VANG),
                new KhachHang("KH06", "Đặng Hoàng Nam", "0901000006", "hoangnam@example.com", LocalDate.of(2001, 9, 5), 1300, HangThanhVien.KIMCUONG),
                new KhachHang("KH07", "Bùi Khánh Linh", "0901000007", "khanhlinh@example.com", LocalDate.of(2004, 2, 14), 120, HangThanhVien.THUONG),
                new KhachHang("KH08", "Đỗ Đức Long", "0901000008", "duclong@example.com", LocalDate.of(1999, 12, 25), 560, HangThanhVien.BAC),
                new KhachHang("KH09", "Phan Mai Chi", "0901000009", "maichi@example.com", LocalDate.of(2003, 8, 17), 0, HangThanhVien.THUONG),
                new KhachHang("KH10", "Hoàng Nhật Vy", "0901000010", "nhatvy@example.com", LocalDate.of(1998, 6, 6), 300, HangThanhVien.BAC)
        ));

        hoaDons = Collections.unmodifiableList(Arrays.asList(
                new HoaDon("HD01", LocalDateTime.of(2026, 10, 2,10, 15, 0), "KH01", "NV02", "KM01", PhuongThucThanhToan.CHUYEN_KHOAN, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD02", LocalDateTime.of(2026, 10, 3, 11, 20, 0), "KH02", "NV03", "KM02", PhuongThucThanhToan.TIEN_MAT, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD03", LocalDateTime.of(2026, 10, 4, 14, 5, 0), "KH03", "NV02", null, PhuongThucThanhToan.THE, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD04", LocalDateTime.of(2026, 10, 5, 16, 40, 0), "KH04", "NV06", "KM05", PhuongThucThanhToan.CHUYEN_KHOAN, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD05", LocalDateTime.of(2026, 10, 6, 9, 30, 0), "KH01", "NV03", "KM03", PhuongThucThanhToan.TIEN_MAT, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD06", LocalDateTime.of(2026, 10, 7, 13, 10, 0), "KH05", "NV02", null, PhuongThucThanhToan.THE, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD07", LocalDateTime.of(2026, 10, 8, 15, 0, 0), "KH06", "NV06", "KM01", PhuongThucThanhToan.CHUYEN_KHOAN, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD08", LocalDateTime.of(2026, 10, 9, 10, 0, 0), "KH02", "NV03", null, PhuongThucThanhToan.TIEN_MAT, TrangThaiDonHang.DA_HUY),
                new HoaDon("HD09", LocalDateTime.of(2026, 10, 10, 17, 20, 0), "KH07", "NV02", null, PhuongThucThanhToan.THE, TrangThaiDonHang.DA_HUY),
                new HoaDon("HD10", LocalDateTime.of(2026, 10, 11, 12, 25, 0), "KH08", "NV06", "KM04", PhuongThucThanhToan.CHUYEN_KHOAN, TrangThaiDonHang.DA_THANH_TOAN),
                new HoaDon("HD11", LocalDateTime.of(2026, 10, 12, 18, 0, 0), "KH03", "NV03", "KM02", PhuongThucThanhToan.TIEN_MAT, TrangThaiDonHang.DOI_TRA),
                new HoaDon("HD12", LocalDateTime.of(2026, 10, 12, 19, 0, 0), "KH10", "NV02", null, PhuongThucThanhToan.THE, TrangThaiDonHang.DA_THANH_TOAN)
        ));

        chiTietHoaDons = Collections.unmodifiableList(Arrays.asList(
                new ChiTietHoaDon("HD01", "BT03", 2, new BigDecimal("300000")),
                new ChiTietHoaDon("HD01", "BT06", 1, new BigDecimal("450000")),
                new ChiTietHoaDon("HD02", "BT12", 1, new BigDecimal("650000")),
                new ChiTietHoaDon("HD02", "BT14", 1, new BigDecimal("520000")),
                new ChiTietHoaDon("HD03", "BT01", 2, new BigDecimal("250000")),
                new ChiTietHoaDon("HD03", "BT24", 1, new BigDecimal("180000")),
                new ChiTietHoaDon("HD04", "BT16", 2, new BigDecimal("280000")),
                new ChiTietHoaDon("HD04", "BT22", 1, new BigDecimal("390000")),
                new ChiTietHoaDon("HD05", "BT20", 1, new BigDecimal("1250000")),
                new ChiTietHoaDon("HD06", "BT09", 1, new BigDecimal("700000")),
                new ChiTietHoaDon("HD06", "BT15", 1, new BigDecimal("520000")),
                new ChiTietHoaDon("HD07", "BT10", 1, new BigDecimal("950000")),
                new ChiTietHoaDon("HD07", "BT21", 1, new BigDecimal("1250000")),
                new ChiTietHoaDon("HD08", "BT04", 1, new BigDecimal("300000")),
                new ChiTietHoaDon("HD09", "BT17", 1, new BigDecimal("280000")),
                new ChiTietHoaDon("HD10", "BT13", 1, new BigDecimal("650000")),
                new ChiTietHoaDon("HD11", "BT18", 1, new BigDecimal("720000")),
                new ChiTietHoaDon("HD12", "BT23", 1, new BigDecimal("390000"))
        ));

        khuyenMais = Collections.unmodifiableList(Arrays.asList(
                new KhuyenMai("KM01", "Giảm 10 phần trăm", LoaiGiam.PHAN_TRAM, new BigDecimal("10"), LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 31), new BigDecimal("100000")),
                new KhuyenMai("KM02", "Giảm trực tiếp 30.000", LoaiGiam.SO_TIEN, new BigDecimal("30000"), LocalDate.of(2026, 9, 1), LocalDate.of(2026, 12, 31), new BigDecimal("150000")),
                new KhuyenMai("KM03", "Ưu đãi mùa hè", LoaiGiam.PHAN_TRAM, new BigDecimal("20"), LocalDate.of(2026, 6, 1), LocalDate.of(2026, 8, 31), new BigDecimal("500000")),
                new KhuyenMai("KM04", "Ưu đãi sắp diễn ra", LoaiGiam.SO_TIEN, new BigDecimal("50000"), LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 30), new BigDecimal("200000")),
                new KhuyenMai("KM05", "Giảm 5 phần trăm", LoaiGiam.PHAN_TRAM, new BigDecimal("5"), LocalDate.of(2026, 10, 10), LocalDate.of(2026, 10,20), new BigDecimal("0"))
        ));

        nhanViens = Collections.unmodifiableList(Arrays.asList(
                new NhanVien("NV01", "Nguyễn Thành Đạt", "0912000001", chucVu.QUAN_LY, new BigDecimal("15000000"), LocalDate.of(2021, 4, 12)),
                new NhanVien("NV02", "Trần Mỹ Duyên", "0912000002", chucVu.BAN_HANG, new BigDecimal("8500000"), LocalDate.of(2022, 6, 1)),
                new NhanVien("NV03", "Lê Anh Tuấn", "0912000003", chucVu.BAN_HANG, new BigDecimal("9000000"), LocalDate.of(2023, 2, 15)),
                new NhanVien("NV04", "Phạm Ngọc Yến", "0912000004", chucVu.THU_KHO, new BigDecimal("9500000"), LocalDate.of(2021, 10, 20)),
                new NhanVien("NV05", "Võ Minh Khang", "0912000005", chucVu.THU_KHO, new BigDecimal("9200000"), LocalDate.of(2024, 1, 8)),
                new NhanVien("NV06", "Đặng Thảo My", "0912000006", chucVu.BAN_HANG, new BigDecimal("8700000"), LocalDate.of(2026, 1, 2))
        ));

        nhaCungCaps = Collections.unmodifiableList(Arrays.asList(
                new NhaCungCap("NCC01", "May Việt", "0283000001", "TP. Hồ Chí Minh"),
                new NhaCungCap("NCC02", "Vải Thành Công", "0283000002", "Bình Dương"),
                new NhaCungCap("NCC03", "Thời trang An Phú", "0283000003", "Đồng Nai"),
                new NhaCungCap("NCC04", "Phụ liệu Minh Châu", "0283000004", "TP. Hồ Chí Minh"),
                new NhaCungCap("NCC05", "Xưởng may Hoa Sen", "0283000005", "Long An")
        ));

        phieuNhaps = Collections.unmodifiableList(Arrays.asList(
                new PhieuNhap("PN01", LocalDate.of(2026, 8, 1), "NCC01", "NV04", new BigDecimal("1600000")),
                new PhieuNhap("PN02", LocalDate.of(2026, 8,5), "NCC02", "NV05", new BigDecimal("2100000")),
                new PhieuNhap("PN03", LocalDate.of(2026, 8, 12), "NCC03", "NV04", new BigDecimal("1750000")),
                new PhieuNhap("PN04", LocalDate.of(2026, 8, 29), "NCC01", "NV05", new BigDecimal("900000")),
                new PhieuNhap("PN05", LocalDate.of(2026, 9, 2), "NCC04", "NV04", new BigDecimal("540000")),
                new PhieuNhap("PN06", LocalDate.of(2026, 9, 10), "NCC05", "NV05", new BigDecimal("2600000")),
                new PhieuNhap("PN07", LocalDate.of(2026, 9, 18), "NCC02", "NV04", new BigDecimal("800000")),
                new PhieuNhap("PN08", LocalDate.of(2026,9, 25), "NCC03", "NV05", new BigDecimal("1200000"))
        ));

        chiTietPhieuNhaps = Collections.unmodifiableList(Arrays.asList(
                new ChiTietPhieuNhap("PN01", "BT01", 10, new BigDecimal("80000")),
                new ChiTietPhieuNhap("PN01", "BT02", 10, new BigDecimal("80000")),
                new ChiTietPhieuNhap("PN02", "BT03", 10, new BigDecimal("120000")),
                new ChiTietPhieuNhap("PN02", "BT04", 10, new BigDecimal("90000")),
                new ChiTietPhieuNhap("PN03", "BT05", 5, new BigDecimal("200000")),
                new ChiTietPhieuNhap("PN03", "BT06", 5, new BigDecimal("150000")),
                new ChiTietPhieuNhap("PN04", "BT07", 10, new BigDecimal("90000")),
                new ChiTietPhieuNhap("PN05", "BT08", 3, new BigDecimal("180000")),
                new ChiTietPhieuNhap("PN06", "BT10", 10, new BigDecimal("180000")),
                new ChiTietPhieuNhap("PN06", "BT11", 5, new BigDecimal("160000")),
                new ChiTietPhieuNhap("PN07", "BT12", 5, new BigDecimal("100000")),
                new ChiTietPhieuNhap("PN07", "BT13", 5, new BigDecimal("60000")),
                new ChiTietPhieuNhap("PN08", "BT16", 5, new BigDecimal("100000")),
                new ChiTietPhieuNhap("PN08", "BT17", 5, new BigDecimal("80000")),
                new ChiTietPhieuNhap("PN08", "BT24", 5, new BigDecimal("60000"))
        ));
        validate();
    }

    public List<SanPham> getSanPhams() {return sanPhams;}
    public List<DanhMuc> getDanhMucs() {return danhMucs;}
    public List<BienTheSanPham> getBienTheSanPhams() {return bienTheSanPhams;}
    public List<KhachHang> getKhachHangs() {return khachHangs;}
    public List<HoaDon> getHoaDons() {return hoaDons;}
    public List<ChiTietHoaDon> getChiTietHoaDons() {return chiTietHoaDons;}
    public List<KhuyenMai> getKhuyenMais() {return khuyenMais;}
    public List<NhaCungCap> getNhaCungCaps() {return nhaCungCaps;}
    public List<NhanVien> getNhanViens() {return nhanViens;}
    public List<PhieuNhap> getPhieuNhaps() {return phieuNhaps;}
    public List<ChiTietPhieuNhap> getChiTietPhieuNhaps() {return chiTietPhieuNhaps;}

    public void validate() {
        unique(sanPhams, SanPham::getMaSanPham);
        unique(danhMucs, DanhMuc::getMaDanhMuc);
        unique(bienTheSanPhams, BienTheSanPham::getMaBienThe);
        unique(khachHangs, KhachHang::getMaKhachHang);
        unique(hoaDons, HoaDon::getMaHoaDon);
        unique(chiTietHoaDons, c -> c.getMaHoaDon() + ":" + c.getMaBienThe());
        unique(khuyenMais, KhuyenMai::getMaKhuyenMai);
        unique(nhaCungCaps, NhaCungCap::getMaNhaCungCap);
        unique(nhanViens, NhanVien::getMaNhanVien);
        unique(phieuNhaps, PhieuNhap::getMaPhieuNhap);
        unique(chiTietPhieuNhaps, c -> c.getMaPhieuNhap() + ":" + c.getMaBienThe());

        boolean hoaDonSai = hoaDons.stream().anyMatch(hd ->
                khachHangs.stream().noneMatch(kh -> kh.getMaKhachHang().equals(hd.getMaKhachHang())) ||
                nhanViens.stream().noneMatch(nv -> nv.getMaNhanVien().equals(hd.getMaNhanVien())) ||
                (hd.getMaKhuyenMai() != null &&
                khuyenMais.stream().noneMatch(km -> km.getMaKhuyenMai().equals(hd.getMaKhuyenMai())))
        );
        boolean chiTietHDSai = chiTietHoaDons.stream().anyMatch(cthd ->
                hoaDons.stream().noneMatch(hd -> hd.getMaHoaDon().equals(cthd.getMaHoaDon())) ||
                bienTheSanPhams.stream().noneMatch(btsp -> btsp.getMaBienThe().equals(cthd.getMaBienThe()))
        );
        boolean phieuNhapSai = phieuNhaps.stream().anyMatch(pn ->
                nhaCungCaps.stream().noneMatch(ncc -> ncc.getMaNhaCungCap().equals(pn.getMaNhaCungCap())) ||
                nhanViens.stream().noneMatch(nv -> nv.getMaNhanVien().equals(pn.getMaNhanVien()))
        );
        boolean chiTietPNSai = chiTietPhieuNhaps.stream().anyMatch(ctpn ->
                phieuNhaps.stream().noneMatch(pn -> pn.getMaPhieuNhap().equals(ctpn.getMaPhieuNhap())) ||
                bienTheSanPhams.stream().noneMatch(btsp -> btsp.getMaBienThe().equals(ctpn.getMaBienThe()))
        );
        boolean badReference = hoaDonSai || chiTietHDSai || phieuNhapSai || chiTietPNSai;
        if (badReference) throw new IllegalStateException("Khóa ngoại không hợp lệ");

        if (chiTietHoaDons.stream().anyMatch(cthd ->
                cthd.getSoLuong() <= 0 || cthd.getDonGia().signum() < 0) ||
            chiTietPhieuNhaps.stream().anyMatch(ctpn ->
                ctpn.getSoLuong() <= 0 || ctpn.getGiaNhap().signum() < 0) ||
            khuyenMais.stream().anyMatch(km ->
                (km.getLoaiGiam().equals(LoaiGiam.PHAN_TRAM) &&
                    (km.getGiaTriGiam().signum() < 0 || km.getGiaTriGiam().compareTo(new BigDecimal("100")) > 0)) ||
                (km.getLoaiGiam().equals(LoaiGiam.SO_TIEN) && km.getGiaTriGiam().signum() < 0) ||
                km.getDonToiThieu().signum() < 0)
        ) throw new IllegalStateException("Chi tiết không hợp lệ");
    }

    public static <T, K> void unique(List<T> data, Function<T, K> key) {
        if (data.stream().map(key).distinct().count() != data.size()) {
            throw new IllegalStateException("Trùng khóa");
        }
    }
}