package clothing.query;

import clothing.data.DataStore;
import clothing.model.BienTheSanPham;
import clothing.model.HangThanhVien;
import clothing.model.SanPham;
import clothing.model.TrangThaiDonHang;
import clothing.util.Row;
import clothing.service.HoaDonService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
            "Tìm các khách hàng chưa từng có hóa đơn đã thanh toán.",
            "Liệt kê các khách hàng chưa sử dụng khuyến mãi trong các đơn hàng của mình."
    ));
    @Override
    public List<String> getTitles() {
        return TITLES;
    }

    public static final List<String> HEADERS = Collections.unmodifiableList(Arrays.asList(
            "Mã KH|"
    ));
    @Override
    public List<String> getHeaders() {
        return HEADERS;
    }

    public final List<Supplier<List<Row>>> QUERIES = Arrays.asList(
            this::cau01,
            this::cau02,
            this::cau03,
            this::cau04,
            this::cau05,
            this::cau06,
            this::cau07,
            this::cau08,
            this::cau09,
            this::cau10
    );
    @Override
    protected List<Supplier<List<Row>>> getQueries() {
            return QUERIES;
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
                .map(kh -> Row.of(
                        kh.getHangThanhVien() == HangThanhVien.VANG ||
                                kh.getHangThanhVien() == HangThanhVien.KIMCUONG
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
                                                        cthd.getMaHoaDon(),
                                                        sp.getTenSanPham(),
                                                        btsp.getKichCo(),
                                                        btsp.getMauSac(),
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
                        km.getDonToiThieu(),
                        km.getNgayBatDau(),
                        km.getNgayKetThuc()
                ))
                .collect(Collectors.toList());
    }

    // Tính tổng doanh thu từ các hóa đơn đã thanh toán.
/*    private List<Row> cau07() {
        BigDecimal doanhthu = BigDecimal.ZERO;
        List<Row> kq = new ArrayList<>();
        db.getHoaDons().stream()
                .filter(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN)
                .forEach(hd ->
                        BigDecimal tongHD = service.getTongHD(hd, db.getChiTietHoaDons(), db.getKhuyenMais())
                )
                .collect(Collectors.toList());
    }
 */
    // Tính tổng doanh thu từ các hóa đơn đã thanh toán.
/*    private List<Row> cau07() {
        BigDecimal[] doanhthu = {BigDecimal.ZERO};
        List<Row> ketQua = new ArrayList<>();

        db.getHoaDons().stream()
                .filter(hd -> hd.getTrangThai() == TrangThaiDonHang.DA_THANH_TOAN)
                .forEach(hd -> {
                    BigDecimal tongHD = service.getTongHD(
                            hd, db.getChiTietHoaDons(), db.getKhuyenMais());

                    doanhthu[0] = doanhthu[0].add(tongHD);

                    ketQua.add(Row.of(
                            hd.getMaHoaDon(),
                            tongHD,
                            hd.getTrangThai()
                    ));
                });

        ketQua.add(Row.of("TỔNG DOANH THU", doanhthu[0], ""));

        return ketQua;
    }
*/
    // Thống kê số lượng hóa đơn và tổng doanh thu theo từng khách hàng.
    private List<Row> cau08() {
        return ;
    }

    // Tìm các khách hàng chưa từng có hóa đơn đã thanh toán.
    private List<Row> cau09() {
        return db.getHoaDons().stream().anyMatch(
                db.getKhachHangs().stream().noneMatch(kh -> kh.getMaKhachHang().equals())
        )
    }

    // Liệt kê các khách hàng chưa sử dụng khuyến mãi trong các đơn hàng của mình.
    private List<Row> cau10() {
        return db.getHoaDons().stream()
                .filter(hd -> hd.getMaKhuyenMai() == null)
                .flatMap(hd ->
                        db.getKhachHangs().stream()
                                .filter(kh -> kh.getMaKhachHang().equals(hd.getMaKhachHang()))
                                .map(kh -> Row.of(
                                        hd.getMaHoaDon(),
                                        kh.getHoTen(),
                                        kh.getSoDienThoai()
                                ))
                )
                .collect(Collectors.toList());
    }
}
