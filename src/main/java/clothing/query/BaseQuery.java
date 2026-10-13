package clothing.query;

import clothing.data.DataStore;
import com.sun.rowset.internal.Row;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class BaseQuery {
    protected final DataStore db;

    public BaseQuery(DataStore db) {
        this.db = Objects.requireNonNull(db);
    }

    public abstract List<String> getTitles();
    public abstract List<String> getHeaders();
    protected abstract List<Supplier<List<Row>>> getQueries();

    public List<Row> execute(int number) {
        List<Supplier<List<Row>>> queries = getQueries();

        if (number < 1 || number > 10) throw new IllegalArgumentException("Chọn câu từ 1 đến 10");
        return queries.get(number - 1).get();
    }
}

/*

public final class StreamExercises {
    private final SalesData db;
    public StreamExercises(SalesData db) { this.db = Objects.requireNonNull(db); }
    private Stream<DonHang> completedOrders() {
        return db.getDonHangs().stream().filter(d -> d.getTrangThai() == TrangThaiDonHang.HOAN_THANH);
    }
    private Stream<ChiTietDonHang> details(DonHang d) {
        return db.getChiTietDonHangs().stream().filter(c -> c.getMaDonHang() == d.getMaDonHang());
    }
    private BigDecimal orderTotal(DonHang d) {
        return details(d).map(ChiTietDonHang::getThanhTien).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private String productName(int id) {
        return db.getSanPhams().stream().filter(s -> s.getMaSanPham() == id)
            .map(SanPham::getTenSanPham).findFirst().orElseThrow(() -> new IllegalStateException("Mã sản phẩm: " + id));
    }
    private String customerName(int id) {
        return db.getKhachHangs().stream().filter(k -> k.getMaKhachHang() == id)
            .map(KhachHang::getHoTen).findFirst().orElseThrow(() -> new IllegalStateException("Mã khách: " + id));
    }
    public static final List<String> TITLES = Collections.unmodifiableList(Arrays.asList(
        "Danh sách sản phẩm, sắp theo mã",
        "Laptop giá từ 15 đến 18 triệu, sắp giá giảm dần",
        "Sản phẩm cần bổ sung: tồn kho dưới 10",
        "Khách hàng ở TP.HCM",
        "Các danh mục sản phẩm không trùng lặp",
        "Đơn hoàn thành trong tháng 02/2026",
        "Đơn hàng kèm tên khách hàng và nhân viên tạo đơn",
        "Chi tiết đơn 1001 kèm tên sản phẩm và thành tiền",
        "Tổng tiền của từng đơn hàng",
        "Tổng doanh thu của các đơn hoàn thành",
        "Doanh thu theo tháng trong năm",
        "Top 3 sản phẩm bán nhiều nhất theo số lượng đã hoàn thành",
        "Doanh thu theo khách hàng, bao gồm khách doanh thu bằng 0",
        "Khách chưa từng đặt bất kỳ đơn hàng nào",
        "Sản phẩm chưa bán trong bất kỳ đơn hoàn thành nào",
        "Số đơn hoàn thành và doanh thu của từng nhân viên",
        "Khách có ít nhất 2 đơn hoàn thành (GROUP BY / HAVING)",
        "LEFT JOIN khách hàng với mọi đơn hàng",
        "Phân trang sản phẩm: trang 2, mỗi trang 5 sản phẩm",
        "Đơn hoàn thành có giá trị trên mức trung bình"
    ));
    public static final List<String> HEADERS = Collections.unmodifiableList(Arrays.asList(
        "Mã SP|Tên|Giá|Tồn",
        "Mã SP|Tên|Giá",
        "Mã SP|Tên|Tồn",
        "Mã KH|Tên|Email",
        "Danh mục",
        "Mã đơn|Ngày|Mã KH",
        "Mã đơn|Khách|Nhân viên|Trạng thái",
        "Sản phẩm|Số lượng|Đơn giá|Tỷ lệ giảm|Thành tiền",
        "Mã đơn|Trạng thái|Tổng tiền",
        "Doanh thu",
        "Năm|Tháng|Doanh thu",
        "Mã SP|Tên|Số lượng",
        "Mã KH|Tên|Doanh thu",
        "Mã KH|Tên",
        "Mã SP|Tên",
        "Mã NV|Tên|Số đơn|Doanh thu",
        "Mã KH|Tên|Số đơn",
        "Mã KH|Tên|Mã đơn|Trạng thái",
        "Mã SP|Tên|Giá",
        "Mã đơn|Tổng tiền|Trung bình"
    ));
    /** Supplier tạo kết quả mới mỗi lần, không tái sử dụng một Stream đã đóng. */
/*public List<Row> execute(int number) {
    if (number < 1 || number > 20) throw new IllegalArgumentException("Chọn câu từ 1 đến 20");
    List<Supplier<List<Row>>> queries = Arrays.asList(
            this::cau01,
            this::cau02,
            this::cau03,
            this::cau04,
            this::cau05,
            this::cau06,
            this::cau07,
            this::cau08,
            this::cau09,
            this::cau10,
            this::cau11,
            this::cau12,
            this::cau13,
            this::cau14,
            this::cau15,
            this::cau16,
            this::cau17,
            this::cau18,
            this::cau19,
            this::cau20);
    return queries.get(number - 1).get();
}

// Danh sách sản phẩm, sắp theo mã
// map tương ứng Select; method reference SanPham::getMaSanPham tương đương s -> s.getMaSanPham().
public List<Row> cau01() {
    return db.getSanPhams().stream()
            .sorted(Comparator.comparingInt(SanPham::getMaSanPham))
            .map(s -> Row.of(s.getMaSanPham(), s.getTenSanPham(), s.getGiaBan(), s.getTonKho()))
            .collect(toList());
}

// Laptop giá từ 15 đến 18 triệu, sắp giá giảm dần
// Predicate<T> biểu diễn điều kiện; and ghép hai điều kiện. BigDecimal so sánh bằng compareTo.
public List<Row> cau02() {
    Predicate<SanPham> laLaptop = s -> "Laptop".equals(s.getDanhMuc());
    Predicate<SanPham> trongKhoangGia = s -> s.getGiaBan().compareTo(new BigDecimal("15000000")) >= 0
            && s.getGiaBan().compareTo(new BigDecimal("18000000")) <= 0;
    return db.getSanPhams().stream().filter(laLaptop.and(trongKhoangGia))
            .sorted(Comparator.comparing(SanPham::getGiaBan).reversed().thenComparingInt(SanPham::getMaSanPham))
            .map(s -> Row.of(s.getMaSanPham(), s.getTenSanPham(), s.getGiaBan())).collect(toList());
}

// Sản phẩm cần bổ sung: tồn kho dưới 10
// filter nhận Predicate; sorted nhận Comparator.
public List<Row> cau03() {
    return db.getSanPhams().stream().filter(s -> s.getTonKho() < 10)
            .sorted(Comparator.comparingInt(SanPham::getTonKho).thenComparingInt(SanPham::getMaSanPham))
            .map(s -> Row.of(s.getMaSanPham(), s.getTenSanPham(), s.getTonKho())).collect(toList());
}

// Khách hàng ở TP.HCM
// Chuỗi dùng equals, không dùng ==.
public List<Row> cau04() {
    return db.getKhachHangs().stream().filter(k -> "TP.HCM".equals(k.getThanhPho()))
            .sorted(Comparator.comparingInt(KhachHang::getMaKhachHang))
            .map(k -> Row.of(k.getMaKhachHang(), k.getHoTen(), k.getEmail())).collect(toList());
}

// Các danh mục sản phẩm không trùng lặp
// distinct bỏ trùng String; sorted dùng thứ tự tự nhiên Unicode, có thể khác culture của .NET.
public List<Row> cau05() {
    return db.getSanPhams().stream().map(SanPham::getDanhMuc).distinct().sorted()
            .map(Row::of).collect(toList());
}

// Đơn hoàn thành trong tháng 02/2026
// Khoảng ngày [đầu tháng, đầu tháng sau); completedOrders luôn trả stream mới.
public List<Row> cau06() {
    LocalDate from = LocalDate.of(2026, 2, 1);
    LocalDate until = LocalDate.of(2026, 3, 1);
    return completedOrders().filter(d -> !d.getNgayDat().isBefore(from) && d.getNgayDat().isBefore(until))
            .sorted(Comparator.comparing(DonHang::getNgayDat).thenComparingInt(DonHang::getMaDonHang))
            .map(d -> Row.of(d.getMaDonHang(), d.getNgayDat(), d.getMaKhachHang())).collect(toList());
}

// Đơn hàng kèm tên khách hàng và nhân viên tạo đơn
// flatMap + filter mô phỏng INNER JOIN; phù hợp bộ dữ liệu nhỏ để học. Dữ liệu lớn nên tạo Map tra cứu khóa.
public List<Row> cau07() {
    return db.getDonHangs().stream().sorted(Comparator.comparingInt(DonHang::getMaDonHang))
            .flatMap(d -> db.getKhachHangs().stream().filter(k -> k.getMaKhachHang() == d.getMaKhachHang())
                    .flatMap(k -> db.getNhanViens().stream().filter(n -> n.getMaNhanVien() == d.getMaNhanVien())
                            .map(n -> Row.of(d.getMaDonHang(), k.getHoTen(), n.getHoTen(), d.getTrangThai()))))
            .collect(toList());
}

// Chi tiết đơn 1001 kèm tên sản phẩm và thành tiền
// Thành tiền lấy đơn giá lịch sử trong chi tiết, không lấy giá hiện tại của sản phẩm.
public List<Row> cau08() {
    return db.getChiTietDonHangs().stream().filter(c -> c.getMaDonHang() == 1001)
            .sorted(Comparator.comparingInt(ChiTietDonHang::getMaSanPham))
            .flatMap(c -> db.getSanPhams().stream().filter(s -> s.getMaSanPham() == c.getMaSanPham())
                    .map(s -> Row.of(s.getTenSanPham(), c.getSoLuong(), c.getDonGia(), c.getTyLeGiamGia(), c.getThanhTien())))
            .collect(toList());
}

// Tổng tiền của từng đơn hàng
// orderTotal dùng map + reduce cộng BigDecimal; bao gồm mọi trạng thái đơn.
public List<Row> cau09() {
    return db.getDonHangs().stream().sorted(Comparator.comparingInt(DonHang::getMaDonHang))
            .map(d -> Row.of(d.getMaDonHang(), d.getTrangThai(), orderTotal(d))).collect(toList());
}

// Tổng doanh thu của các đơn hoàn thành
// reduce với identity ZERO trả 0 khi tập rỗng; không cộng đơn mới hoặc hủy.
public List<Row> cau10() {
    BigDecimal revenue = completedOrders().flatMap(this::details)
            .map(ChiTietDonHang::getThanhTien).reduce(BigDecimal.ZERO, BigDecimal::add);
    return Collections.singletonList(Row.of(revenue));
}

// Doanh thu theo tháng trong năm
// groupingBy nhóm theo YearMonth, TreeMap giữ thứ tự; reducing cộng doanh thu mỗi nhóm.
public List<Row> cau11() {
    Map<YearMonth, BigDecimal> totals = completedOrders().collect(groupingBy(
            d -> YearMonth.from(d.getNgayDat()), TreeMap::new,
            reducing(BigDecimal.ZERO, this::orderTotal, BigDecimal::add)));
    return totals.entrySet().stream()
            .map(e -> Row.of(e.getKey().getYear(), e.getKey().getMonthValue(), e.getValue())).collect(toList());
}

// Top 3 sản phẩm bán nhiều nhất theo số lượng đã hoàn thành
// groupingBy + summingInt tính số lượng; limit lấy 3 dòng, phá hòa bằng mã.
public List<Row> cau12() {
    Map<Integer, Integer> quantities = completedOrders().flatMap(this::details)
            .collect(groupingBy(ChiTietDonHang::getMaSanPham, summingInt(ChiTietDonHang::getSoLuong)));
    return quantities.entrySet().stream()
            .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
            .limit(3).map(e -> Row.of(e.getKey(), productName(e.getKey()), e.getValue())).collect(toList());
}

// Doanh thu theo khách hàng, bao gồm khách doanh thu bằng 0
// Function<T,R> tính/tra một giá trị từ đối tượng; getOrDefault giữ khách doanh thu 0.
public List<Row> cau13() {
    Map<Integer, BigDecimal> revenue = completedOrders().collect(groupingBy(DonHang::getMaKhachHang,
            reducing(BigDecimal.ZERO, this::orderTotal, BigDecimal::add)));
    Function<KhachHang, BigDecimal> total = k -> revenue.getOrDefault(k.getMaKhachHang(), BigDecimal.ZERO);
    return db.getKhachHangs().stream()
            .sorted(Comparator.comparing(total).reversed().thenComparingInt(KhachHang::getMaKhachHang))
            .map(k -> Row.of(k.getMaKhachHang(), k.getHoTen(), total.apply(k))).collect(toList());
}

// Khách chưa từng đặt bất kỳ đơn hàng nào
// noneMatch tương đương !Any; khách chỉ có đơn hủy vẫn đã từng đặt đơn.
public List<Row> cau14() {
    return db.getKhachHangs().stream()
            .filter(k -> db.getDonHangs().stream().noneMatch(d -> d.getMaKhachHang() == k.getMaKhachHang()))
            .sorted(Comparator.comparingInt(KhachHang::getMaKhachHang))
            .map(k -> Row.of(k.getMaKhachHang(), k.getHoTen())).collect(toList());
}

// Sản phẩm chưa bán trong bất kỳ đơn hoàn thành nào
// Set lưu mã đã bán để kiểm tra nhanh; sản phẩm chỉ có trong đơn hủy chưa được coi là đã bán.
public List<Row> cau15() {
    Set<Integer> soldIds = completedOrders().flatMap(this::details)
            .map(ChiTietDonHang::getMaSanPham).collect(toSet());
    return db.getSanPhams().stream().filter(s -> !soldIds.contains(s.getMaSanPham()))
            .sorted(Comparator.comparingInt(SanPham::getMaSanPham))
            .map(s -> Row.of(s.getMaSanPham(), s.getTenSanPham())).collect(toList());
}

// Số đơn hoàn thành và doanh thu của từng nhân viên
// Đếm số đơn trước khi nối chi tiết để tránh đếm lặp; nhân viên chưa có đơn vẫn xuất hiện.
public List<Row> cau16() {
    Map<Integer, List<DonHang>> byStaff = completedOrders().collect(groupingBy(DonHang::getMaNhanVien));
    Function<NhanVien, List<DonHang>> orders = n -> byStaff.getOrDefault(n.getMaNhanVien(), Collections.emptyList());
    Function<NhanVien, BigDecimal> total = n -> orders.apply(n).stream()
            .map(this::orderTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    return db.getNhanViens().stream()
            .sorted(Comparator.comparing(total).reversed().thenComparingInt(NhanVien::getMaNhanVien))
            .map(n -> Row.of(n.getMaNhanVien(), n.getHoTen(), orders.apply(n).size(), total.apply(n))).collect(toList());
}

// Khách có ít nhất 2 đơn hoàn thành (GROUP BY / HAVING)
// filter sau groupingBy tương đương HAVING; counting trả Long.
public List<Row> cau17() {
    Map<Integer, Long> counts = completedOrders().collect(groupingBy(DonHang::getMaKhachHang, counting()));
    return counts.entrySet().stream().filter(e -> e.getValue() >= 2)
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
            .map(e -> Row.of(e.getKey(), customerName(e.getKey()), e.getValue())).collect(toList());
}

// LEFT JOIN khách hàng với mọi đơn hàng
// Java 8 Stream không có leftJoin sẵn; tự tạo một dòng null khi phía phải rỗng.
public List<Row> cau18() {
    return db.getKhachHangs().stream().sorted(Comparator.comparingInt(KhachHang::getMaKhachHang))
            .flatMap(k -> {
                List<DonHang> orders = db.getDonHangs().stream()
                        .filter(d -> d.getMaKhachHang() == k.getMaKhachHang())
                        .sorted(Comparator.comparingInt(DonHang::getMaDonHang)).collect(toList());
                if (orders.isEmpty())
                    return Stream.of(Row.of(k.getMaKhachHang(), k.getHoTen(), null, "Chưa có đơn"));
                return orders.stream().map(d -> Row.of(k.getMaKhachHang(), k.getHoTen(), d.getMaDonHang(), d.getTrangThai()));
            }).collect(toList());
}

// Phân trang sản phẩm: trang 2, mỗi trang 5 sản phẩm
// Trang bắt đầu từ 1; sắp xếp ổn định trước skip và limit.
public List<Row> cau19() {
    int page = 2;
    int pageSize = 5;
    return db.getSanPhams().stream().sorted(Comparator.comparingInt(SanPham::getMaSanPham))
            .skip((long) (page - 1) * pageSize).limit(pageSize)
            .map(s -> Row.of(s.getMaSanPham(), s.getTenSanPham(), s.getGiaBan())).collect(toList());
}

// Đơn hoàn thành có giá trị trên mức trung bình
// Materialize bằng collect(toList) để duyệt nhiều lần; chỉ làm tròn số trung bình hiển thị, không làm tròn khi lọc.
public List<Row> cau20() {
    List<DonHang> orders = completedOrders().collect(toList());
    BigDecimal total = orders.stream().map(this::orderTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal average = orders.isEmpty() ? BigDecimal.ZERO :
            total.divide(BigDecimal.valueOf(orders.size()), 2, RoundingMode.HALF_UP);
    return orders.stream()
            // So sánh chính xác với trung bình chưa làm tròn bằng phép nhân chéo.
            .filter(d -> orderTotal(d).multiply(BigDecimal.valueOf(orders.size())).compareTo(total) > 0)
            .sorted(Comparator.comparing(this::orderTotal).reversed().thenComparingInt(DonHang::getMaDonHang))
            .map(d -> Row.of(d.getMaDonHang(), orderTotal(d), average)).collect(toList());
}
}
 */