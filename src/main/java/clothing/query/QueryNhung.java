package clothing.query;

import clothing.data.DataStore;
import clothing.model.*;
import clothing.util.Row;
import clothing.service.HoaDonService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

public class QueryNhung extends BaseQuery{
    private final HoaDonService service = new HoaDonService();
    public QueryNhung(DataStore db) {
        super(db);
    }

    public static final List<String> TITLES = Collections.unmodifiableList(Arrays.asList(
            "Liệt kê tất cả khách hàng, gồm mã khách hàng, họ tên, số điện thoại, email và hạng thành viên.",
            "Tìm các khách hàng thuộc hạng thành viên Vàng hoặc Kim cương.",
            "Liệt kê các hóa đơn đã thanh toán, gồm mã hóa đơn, ngày lập, tổng tiền, tiền giảm và thành tiền.",
            "Liệt kê hóa đơn cùng với tên khách hàng đã mua và nhân viên lập hóa đơn.",
            "Xem chi tiết một hóa đơn cụ thể, gồm mã hóa đơn, tên sản phẩm, kích cỡ, màu sắc, số lượng và đơn giá.",
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
            "Mã KH|Tên KH|Số Điện Thoại|Email|Hạng Thành Viên",
            "Mã KH|Tên KH|Số Điện Thoại|Hạng Thành Viên",
            "Mã HD|Ngày lập|Tổng Tiền Ban Đầu|Tiền Giảm|Tổng HD",
            "Mã HD|Tên KH|Tên NV",
            "Tên SP|Kích Cỡ|Màu Sắc|Số Lượng|Đơn Giá",
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
                System.out.println("Nhập mã hóa đơn cần xem: ");
                String maHD = sc.nextLine();
                return cau05(maHD);
            case 6:
                System.out.println("Nhập ngày cần xét (yyyy-mm-dd): ");
                String ngay = sc.nextLine();
                return cau06(LocalDate.parse(ngay));
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

    // Liệt kê tất cả khách hàng, gồm mã khách hàng, họ tên, số điện thoại, email và hạng thành viên.
    private List<Row> cau01() {
        return db.getKhachHangs().stream()
                .map(kh -> Row.of(
                        kh.getMaKhachHang(),
                        kh.getHoTen(),
                        kh.getSoDienThoai(),
                        kh.getEmail(),
                        kh.getHangThanhVien()
                ))
                .collect(Collectors.toList());
    }

    // Tìm các khách hàng thuộc hạng thành viên Vàng hoặc Kim cương.
    private List<Row> cau02() {
        return db.getKhachHangs().stream()
                .filter(kh -> kh.getHangThanhVien() == HangThanhVien.VANG ||
                        kh.getHangThanhVien() == HangThanhVien.KIMCUONG)
                .map(kh -> Row.of(
                        kh.getMaKhachHang(),
                        kh.getHoTen(),
                        kh.getSoDienThoai(),
                        kh.getHangThanhVien()
                ))
                .collect(Collectors.toList());
    }

    // Liệt kê các hóa đơn đã thanh toán, gồm mã hóa đơn, ngày lập, tổng tiền ban đầu, tiền giảm và tổng hóa đơn.
    private List<Row> cau03() {
        return db.getHoaDons().stream()
                .filter(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN)
                .map(hd -> Row.of(
                        hd.getMaHoaDon(),
                        hd.getNgayLap(),
                        service.getTongHDBD(hd, db.getChiTietHoaDons()),
                        service.getTienGiam(hd, db.getChiTietHoaDons(), db.getKhuyenMais()),
                        service.getTongHD(hd, db.getChiTietHoaDons(), db.getKhuyenMais())
                ))
                .collect(Collectors.toList());
    }

    // Liệt kê hóa đơn cùng với tên khách hàng đã mua và nhân viên lập hóa đơn.
    private List<Row> cau04() {
        return db.getHoaDons().stream()
                .map(hd -> Row.of(
                        hd.getMaHoaDon(),
                        timTenKH(hd.getMaKhachHang()),
                        timTenNV(hd.getMaNhanVien())
                ))
                .collect(Collectors.toList());
    }

    // Xem chi tiết một hóa đơn cụ thể, gồm mã hóa đơn, tên sản phẩm, kích cỡ, màu sắc, số lượng và đơn giá.
    private List<Row> cau05(String maHD) {
        return db.getChiTietHoaDons().stream()
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
                                                        cthd.getDonGia()
                                                ))
                                )
                )
                .collect(Collectors.toList());
    }

    // Liệt kê các chương trình khuyến mãi đang có hiệu lực ở một thời điểm cụ thể.
    private List<Row> cau06(LocalDate ngay) {
        return db.getKhuyenMais().stream()
                .filter(km -> km.getNgayBatDau().isBefore(ngay) && km.getNgayKetThuc().isAfter(ngay))
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
    private List<Row> cau07() {
        List<Row> ketQua = db.getHoaDons().stream()
                .filter(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN)
                .map(hd -> Row.of(
                        hd.getMaHoaDon(),
                        service.getTongHD(hd, db.getChiTietHoaDons(), db.getKhuyenMais())
                ))
                .collect(Collectors.toList());

        BigDecimal doanhThu = ketQua.stream()
                .map(row -> (BigDecimal) row.getValues().get(1))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        ketQua.add(Row.of("Tổng doanh thu: ", doanhThu));
        return ketQua;
    }

    // Thống kê số lượng hóa đơn và tổng doanh thu theo từng khách hàng.
    private List<Row> cau08() {
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
                                .map(hd -> service.getTongHD(hd, db.getChiTietHoaDons(), db.getKhuyenMais()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .collect(Collectors.toList());
    }

    // Tìm các khách hàng đã từng lập hóa đơn, nhưng chưa có hóa đơn nào đã thanh toán.
    private List<Row> cau09() {
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
    private List<Row> cau10() {
        List<Row> ketQua = new ArrayList<>();

        db.getHoaDons().stream()
                .map(HoaDon::getMaKhachHang)
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