package clothing.query;

import clothing.data.DataStore;
import clothing.model.*;
import clothing.util.Row;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

public class QueryNhung extends BaseQuery{
    public QueryNhung(DataStore db) {
        super(db);
    }

    public static final List<String> TITLES = Collections.unmodifiableList(Arrays.asList(
            "Liệt kê tất cả khách hàng.",
            "Tìm các khách hàng thuộc hạng thành viên Vàng hoặc Kim cương.",
            "Liệt kê các hóa đơn đã thanh toán.",
            "Liệt kê hóa đơn cùng với tên khách hàng đã mua và nhân viên lập hóa đơn.",
            "Xem chi tiết một hóa đơn cụ thể.",
            "Liệt kê các chương trình khuyến mãi đang có hiệu lực ở một thời điểm cụ thể.",
            "Tính tổng doanh thu từ các hóa đơn đã thanh toán.",
            "Thống kê số lượng hóa đơn và tổng doanh thu theo từng khách hàng.",
            "Tìm các khách hàng đã từng lập hóa đơn, nhưng chưa có hóa đơn nào đã thanh toán.",
            "Liệt kê các khách hàng chưa sử dụng khuyến mãi trong các đơn hàng của mình."
    ));
    @Override
    public List<String> getTitles() {
        return TITLES;
    }

    public static final List<String> HEADERS = Collections.unmodifiableList(Arrays.asList(
            "Mã KH|Tên KH|Ngày Sinh|Số Điện Thoại|Email|Điểm Tích Lũy|Hạng Thành Viên",
            "Mã KH|Tên KH|Ngày Sinh|Số Điện Thoại|Hạng Thành Viên",
            "Mã HD|Ngày lập|Mã KH|Mã KM|Phương Thức Thanh Toán|Tổng HD",
            "Mã HD|Tên KH|Tên NV",
            "Tên SP|Kích Cỡ|Màu Sắc|Số Lượng|Đơn Giá|Thành Tiền",
            "Mã KM|Tên KM|Loại Giảm|Giá Trị Giảm|Đơn Tối Thiểu",
            "Mã HD|Tổng HD",
            "Mã KH|Số lượng HD|Tổng Doanh Thu",
            "Mã KH|Tên KH|Số Điện Thoại",
            "Mã KH|Tên KH"
    ));
    @Override
    public List<String> getHeaders() {
        return HEADERS;
    }

    @Override
    public List<Row>  execute(int number, Scanner sc) {
        switch (number) {
            case 1:
                return cau01();
            case 2:
                return cau02();
            case 3:
                return cau03();
            case 4:
                return cau04();
            case 5:
                System.out.print("Nhập mã hóa đơn cần xem: ");
                String maHD = sc.nextLine();
                return cau05(maHD);
            case 6:
                System.out.print("Nhập ngày cần xét (yyyy-mm-dd): ");
                DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-M-d");
                LocalDate ngay = LocalDate.parse(sc.nextLine(), format);
                return cau06(ngay);
            case 7:
                return cau07();
            case 8:
                return cau08();
            case 9:
                return cau09();
            case 10:
                return cau10();
            default:
                throw new IllegalArgumentException("Số câu không hợp lệ");
        }
    }

    // Liệt kê tất cả khách hàng.
    public List<Row> cau01() {
        return db.getKhachHangs().stream()
                .map(kh -> Row.of(
                        kh.getMaKhachHang(),
                        kh.getHoTen(),
                        kh.getNgaySinh(),
                        kh.getSoDienThoai(),
                        kh.getEmail(),
                        kh.getDiemTichLuy(),
                        kh.getHangThanhVien()
                ))
                .collect(Collectors.toList());
    }

    // Tìm các khách hàng thuộc hạng thành viên Vàng hoặc Kim cương.
    public List<Row> cau02() {
        return db.getKhachHangs().stream()
                .filter(kh -> kh.getHangThanhVien() == HangThanhVien.VANG ||
                        kh.getHangThanhVien() == HangThanhVien.KIMCUONG)
                .map(kh -> Row.of(
                        kh.getMaKhachHang(),
                        kh.getHoTen(),
                        kh.getNgaySinh(),
                        kh.getSoDienThoai(),
                        kh.getHangThanhVien()
                ))
                .collect(Collectors.toList());
    }

    // Liệt kê các hóa đơn đã thanh toán.
    public List<Row> cau03() {
        return db.getHoaDons().stream()
                .filter(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN)
                .map(hd -> Row.of(
                        hd.getMaHoaDon(),
                        hd.getNgayLap(),
                        hd.getMaKhachHang(),
                        hd.getMaNhanVien(),
                        hd.getMaKhuyenMai(),
                        hd.getPhuongThucThanhToan(),
                        getTongHD(hd, db.getChiTietHoaDons(), db.getKhuyenMais())
                ))
                .collect(Collectors.toList());
    }

    // Liệt kê hóa đơn cùng với tên khách hàng đã mua và nhân viên lập hóa đơn.
    public List<Row> cau04() {
        return db.getHoaDons().stream()
                .map(hd -> Row.of(
                        hd.getMaHoaDon(),
                        timTenKH(hd.getMaKhachHang()),
                        timTenNV(hd.getMaNhanVien())
                ))
                .collect(Collectors.toList());
    }

    // Xem chi tiết một hóa đơn cụ thể.
    public List<Row> cau05(String maHD) {
        boolean t = db.getHoaDons().stream()
                .noneMatch(hd -> hd.getMaHoaDon().equals(maHD));
        if (t) throw new IllegalStateException("Mã hóa đơn " + maHD + " không tồn tại!");
        List<Row> ketQua = db.getChiTietHoaDons().stream()
                .filter(cthd -> cthd.getMaHoaDon().equals(maHD))
                .flatMap(cthd ->
                        db.getBienTheSanPhams().stream()
                                .filter(btsp -> btsp.getMaBienThe().equals(cthd.getMaBienThe()))
                                .flatMap(btsp ->
                                        db.getSanPhams().stream()
                                                .filter(sp -> sp.getMaSanPham().equals(btsp.getMaSanPham()))
                                                .map(sp -> Row.of(
                                                        sp.getTenSanPham(),
                                                        btsp.getKichCo(),
                                                        btsp.getMauSac(),
                                                        cthd.getSoLuong(),
                                                        cthd.getDonGia(),
                                                        cthd.getThanhTien()
                                                ))
                                )
                )
                .collect(Collectors.toList());

        BigDecimal tongHDBD = ketQua.stream()
                .map(row -> (BigDecimal) row.getValues().get(5))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        HoaDon hoaDon = db.getHoaDons().stream()
                .filter(hd -> hd.getMaHoaDon().equals(maHD))
                .findFirst()
                .orElse(null);
        BigDecimal tienGiam = getTienGiam(hoaDon, db.getChiTietHoaDons(), db.getKhuyenMais());
        ketQua.add(Row.of("Tổng hóa đơn ban đầu: ", tongHDBD));
        ketQua.add(Row.of("Tiền giảm: ", tienGiam));
        ketQua.add(Row.of("Tổng hóa đơn: ", tongHDBD.subtract(tienGiam)));
        return ketQua;
    }

    // Liệt kê các chương trình khuyến mãi đang có hiệu lực ở một thời điểm cụ thể.
    public List<Row> cau06(LocalDate ngay) {
        return db.getKhuyenMais().stream()
                .filter(km -> !km.getNgayBatDau().isAfter(ngay) && !km.getNgayKetThuc().isBefore(ngay))
                .map(km -> Row.of(
                        km.getMaKhuyenMai(),
                        km.getTenKhuyenMai(),
                        km.getLoaiGiam(),
                        km.getGiaTriGiam(),
                        km.getDonToiThieu()
                ))
                .collect(Collectors.toList());
    }

    // Tính tổng doanh thu từ các hóa đơn đã thanh toán.
    public List<Row> cau07() {
        List<Row> ketQua = db.getHoaDons().stream()
                .filter(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN)
                .map(hd -> Row.of(
                        hd.getMaHoaDon(),
                        getTongHD(hd, db.getChiTietHoaDons(), db.getKhuyenMais())
                ))
                .collect(Collectors.toList());

        BigDecimal doanhThu = ketQua.stream()
                .map(row -> (BigDecimal) row.getValues().get(1))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        ketQua.add(Row.of("Tổng doanh thu: ", doanhThu));
        return ketQua;
    }

    // Thống kê số lượng hóa đơn và tổng doanh thu theo từng khách hàng.
    public List<Row> cau08() {
        return db.getHoaDons().stream()
                .map(HoaDon::getMaKhachHang).distinct()
                .map(maKH -> Row.of(
                        maKH,
                        db.getHoaDons().stream()
                                .filter(hd -> hd.getMaKhachHang().equals(maKH))
                                .count(),
                        db.getHoaDons().stream()
                                .filter(hd -> hd.getMaKhachHang().equals(maKH))
                                .filter(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN)
                                .map(hd -> getTongHD(hd, db.getChiTietHoaDons(), db.getKhuyenMais()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .collect(Collectors.toList());
    }

    // Tìm các khách hàng đã từng lập hóa đơn, nhưng chưa có hóa đơn nào đã thanh toán.
    public List<Row> cau09() {
        List<Row> ketQua = new ArrayList<>();

        db.getHoaDons().stream()
                .map(HoaDon::getMaKhachHang).distinct()
                .forEach(maKH -> {
                    if (db.getHoaDons().stream()
                            .filter(hd -> hd.getMaKhachHang().equals(maKH))
                            .noneMatch(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN))
                        ketQua.add(Row.of(
                                maKH,
                                timTenKH(maKH),
                                db.getKhachHangs().stream()
                                        .filter(kh -> kh.getMaKhachHang().equals(maKH))
                                        .map(KhachHang::getSoDienThoai)
                                        .findFirst()
                                        .orElse("Không có")
                        ));
                });
        return ketQua;
    }

    // Liệt kê các khách hàng chưa sử dụng khuyến mãi trong các đơn hàng của mình.
    public List<Row> cau10() {
        List<Row> ketQua = new ArrayList<>();

        db.getHoaDons().stream()
                .map(HoaDon::getMaKhachHang).distinct()
                .forEach(maKH -> {
                    if (db.getHoaDons().stream()
                            .filter(hd -> hd.getMaKhachHang().equals(maKH))
                            .noneMatch(hd -> hd.getMaKhuyenMai() != null))
                        ketQua.add(Row.of(
                                maKH,
                                timTenKH(maKH)
                        ));
                });
        return ketQua;
    }
}