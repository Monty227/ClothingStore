package clothing.query;
import java.math.BigDecimal;
import clothing.model.SanPham;
import clothing.model.DanhMuc;
import clothing.model.BienTheSanPham;
import clothing.util.Row;
import clothing.data.DataStore;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QueryThu extends BaseQuery{
    public QueryThu(DataStore db) {
        super(db);
    }

    public static final List<String> TITLES = Collections.unmodifiableList(Arrays.asList(
            "Liệt kê tất cả danh mục sản phẩm, gồm mã danh mục, tên danh mục và mô tả.",
            "Liệt kê tất cả sản phẩm, gồm mã sản phẩm, tên sản phẩm, thương hiệu, giới tính và giá bán.",
            "Tìm các sản phẩm có giá bán trong khoảng từ 300.000 đến 700.000 đồng.",
            "Liệt kê các sản phẩm dành cho nam và sắp xếp theo giá bán giảm dần.",
            "Tìm các biến thể sản phẩm có số lượng tồn kho dưới 10 sản phẩm.",
            "Liệt kê sản phẩm cùng với tên danh mục mà sản phẩm đó thuộc về.",
            "Liệt kê sản phẩm và các biến thể tương ứng, gồm tên sản phẩm, kích cỡ, màu sắc và số lượng tồn.",
            "Tìm các sản phẩm thuộc một danh mục cụ thể, ví dụ danh mục “Áo”.",
            "Thống kê số lượng biến thể của từng sản phẩm.",
            "Tìm các sản phẩm có tổng số lượng tồn của tất cả biến thể lớn hơn 20 sản phẩm."
    ));
    @Override
    public List<String> getTitles() {
        return TITLES;
    }

    public static final List<String> HEADERS = Collections.unmodifiableList(Arrays.asList(
            "Mã Danh Mục|Tên Danh Mục|Mô Tả",
            "Mã Sản Phẩm|Tên Sản Phẩm|Thương Hiệu|Giới Tính|Giá Bán",
            "Mã Sản Phẩm|Tên Sản Phẩm|Thương Hiệu|Giới Tính|Giá Bán",
            "Mã Sản Phẩm|Tên Sản Phẩm|Thương Hiệu|Giới Tính|Giá Bán",
            "Mã Biến Thể|Mã Sản Phẩm|Kích Cỡ|Màu Sắc|Số Lượng Tồn",
            "Mã Sản Phẩm|Tên Sản Phẩm|Tên Danh Mục",
            "Mã Sản Phẩm|Tên Sản Phẩm|Kích Cỡ|Màu Sắc|Số Lượng Tồn",
            "Mã Sản Phẩm|Tên Sản Phẩm|Thương Hiệu|Giá Bán",
            "Mã Sản Phẩm|Tên Sản Phẩm|Số Lượng Biến Thể",
            "Mã Sản Phẩm|Tên Sản Phẩm|Tổng Tồn Kho"
    ));
    @Override
    public List<String> getHeaders() {
        return HEADERS;
    }

    public List<Row> execute(int number, Scanner sc) {
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
                return cau05();
            case 6:
                return cau06();
            case 7:
                return cau07();
            case 8:
                System.out.print("Nhập tên danh mục cần tìm (ví dụ: Áo): ");
                String tenDM = sc.nextLine();
                return cau08(tenDM);
            case 9:
                return cau09();
            case 10:
                return cau10();
            default:
                throw new IllegalArgumentException("Số câu không hợp lệ");
        }
    }
    //Liệt kê tất cả danh mục sản phẩm, gồm mã danh mục, tên danh mục và mô tả.
    public List<Row> cau01() {
        return db.getDanhMucs().stream()
                .map(dm -> Row.of(dm.getMaDanhMuc(), dm.getTenDanhMuc(), dm.getMoTa()))
                .collect(Collectors.toList());
    }
    //Liệt kê tất cả sản phẩm, gồm mã sản phẩm, tên sản phẩm, thương hiệu, giới tính và giá bán.
    public List<Row> cau02() {
        return db.getSanPhams().stream()
                .map(sp -> Row.of(sp.getMaSanPham(), sp.getTenSanPham(), sp.getThuongHieu(), sp.getGioiTinh(), sp.getGiaBan()))
                .collect(Collectors.toList());
    }
    // Tìm các sản phẩm có giá bán trong khoảng từ 300.000 đến 700.000 đồng.
    public List<Row> cau03() {
        return db.getSanPhams().stream()
                .filter(sp -> sp.getGiaBan().compareTo(BigDecimal.valueOf(300000)) >= 0
                        && sp.getGiaBan().compareTo(BigDecimal.valueOf(700000)) <= 0)
                .map(sp -> Row.of(sp.getMaSanPham(), sp.getTenSanPham(), sp.getThuongHieu(), sp.getGioiTinh(), sp.getGiaBan()))
                .collect(Collectors.toList());
    }

    // Liệt kê các sản phẩm dành cho nam và sắp xếp theo giá bán giảm dần.
    public List<Row> cau04() {
        return db.getSanPhams().stream()
                .filter(sp -> sp.getGioiTinh() != null && sp.getGioiTinh().toString().equalsIgnoreCase("Nam"))
                .sorted(Comparator.comparing(SanPham::getGiaBan).reversed())
                .map(sp -> Row.of(sp.getMaSanPham(), sp.getTenSanPham(), sp.getThuongHieu(), sp.getGioiTinh(), sp.getGiaBan()))
                .collect(Collectors.toList());
    }
    //Tìm các biến thể sản phẩm có số lượng tồn kho dưới 10 sản phẩm.
    public List<Row> cau05() {
        return db.getBienTheSanPhams().stream()
                .filter(bt -> bt.getSoLuongTon() < 10)
                .map(bt -> Row.of(bt.getMaBienThe(), bt.getMaSanPham(), bt.getKichCo(), bt.getMauSac(), bt.getSoLuongTon()))
                .collect(Collectors.toList());
    }
    //Liệt kê sản phẩm cùng với tên danh mục mà sản phẩm đó thuộc về.
    public List<Row> cau06() {
        return db.getSanPhams().stream()
                .map(sp -> {
                    String tenDM = db.getDanhMucs().stream()
                            .filter(dm -> dm.getMaDanhMuc().equals(sp.getMaDanhMuc()))
                            .map(DanhMuc::getTenDanhMuc)
                            .findFirst().orElse("Khác");
                    return Row.of(sp.getMaSanPham(), sp.getTenSanPham(), tenDM);
                })
                .collect(Collectors.toList());
    }
    //Liệt kê sản phẩm và các biến thể tương ứng, gồm tên sản phẩm, kích cỡ, màu sắc và số lượng tồn.
    public List<Row> cau07() {
        return db.getBienTheSanPhams().stream()
                .map(bt -> {
                    SanPham sp = db.getSanPhams().stream()
                            .filter(s -> s.getMaSanPham().equals(bt.getMaSanPham()))
                            .findFirst().orElse(null);
                    String tenSP = (sp != null) ? sp.getTenSanPham() : "N/A";
                    return Row.of(bt.getMaSanPham(), tenSP, bt.getKichCo(), bt.getMauSac(), bt.getSoLuongTon());
                })
                .collect(Collectors.toList());
    }
    //Tìm các sản phẩm thuộc một danh mục cụ thể, ví dụ danh mục “Áo”.
    public List<Row> cau08(String tenDanhMuc) {
        return db.getSanPhams().stream()
                .filter(sp -> {
                    DanhMuc dm = db.getDanhMucs().stream()
                            .filter(d -> d.getMaDanhMuc().equals(sp.getMaDanhMuc()))
                            .findFirst().orElse(null);
                    return dm != null && dm.getTenDanhMuc().equalsIgnoreCase(tenDanhMuc);
                })
                .map(sp -> Row.of(sp.getMaSanPham(), sp.getTenSanPham(), sp.getThuongHieu(), sp.getGiaBan()))
                .collect(Collectors.toList());
    }
    //Thống kê số lượng biến thể của từng sản phẩm.
    public List<Row> cau09() {
        return db.getSanPhams().stream()
                .map(sp -> {
                    long count = db.getBienTheSanPhams().stream()
                            .filter(bt -> bt.getMaSanPham().equals(sp.getMaSanPham()))
                            .count();
                    return Row.of(sp.getMaSanPham(), sp.getTenSanPham(), count);
                })
                .collect(Collectors.toList());
    }
    //Tìm các sản phẩm có tổng số lượng tồn của tất cả biến thể lớn hơn 20 sản phẩm.
    public List<Row> cau10() {
        return db.getSanPhams().stream()
                .map(sp -> {
                    int totalStock = db.getBienTheSanPhams().stream()
                            .filter(bt -> bt.getMaSanPham().equals(sp.getMaSanPham()))
                            .mapToInt(BienTheSanPham::getSoLuongTon)
                            .sum();
                    return new Object[]{sp, totalStock};
                })
                .filter(arr -> (int) arr[1] > 20)
                .map(arr -> {
                    SanPham sp = (SanPham) arr[0];
                    int totalStock = (int) arr[1];
                    return Row.of(sp.getMaSanPham(), sp.getTenSanPham(), totalStock);
                })
                .collect(Collectors.toList());
    }
}