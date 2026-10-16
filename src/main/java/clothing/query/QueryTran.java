package clothing.query;

import clothing.data.DataStore;
import clothing.model.*;
import clothing.util.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class QueryTran extends BaseQuery {
    public QueryTran(DataStore db) {
        super(db);
    }
    @Override
    public List<String> getTitles() {
        return Arrays.asList(
                "Liệt kê tất cả nhân viên",
                "Tìm nhân viên theo chức vụ",
                "Liệt kê tất cả nhà cung cấp",
                "Liệt kê phiếu nhập kèm tên nhà cung cấp và nhân viên lập phiếu",
                "Xem chi tiết một phiếu nhập cụ thể",
                "Tính tổng tiền nhập của từng phiếu nhập",
                "Tính tổng số tiền hàng đã nhập từ mỗi nhà cung cấp",
                "Thống kê số lượng phiếu nhập và tổng tiền nhập do từng nhân viên lập",
                "Tìm biến thể sản phẩm đã từng nhập nhưng tồn kho bằng 0",
                "Tìm nhà cung cấp có tổng giá trị nhập hàng lớn hơn trung bình"
        );
    }
    @Override
    public List<String> getHeaders() {
        return Arrays.asList(
                "Mã NV|Họ tên|SĐT|Chức vụ|Lương cơ bản",
                "Mã NV|Họ tên|SĐT|Chức vụ|Lương cơ bản",
                "Mã NCC|Tên nhà cung cấp|SĐT|Địa chỉ",
                "Mã PN|Ngày nhập|Tên NCC|Tên nhân viên|Tổng tiền",
                "Mã PN|Tên sản phẩm|Mã biến thế|Số lượng|Giá nhập",
                "Mã PN|Tổng tiền nhập",
                "Mã NCC|Tên nhà cung cấp|Tổng tiền hàng đã nhập",
                "M NV|Tên nhân viên|Số lượng phiếu nhập|Tổng tiền nhập",
                "Mã biến thể|Mã sản phẩm|Kích cỡ|Màu sắc|Số lượng tồn|Barcode",
                "Mã NCC|tên nhà cung cấp|Tổng giá trị nhập"
        );
    }
    @Override
    protected List<Supplier<List<Row>>> getQueries() {
        return Arrays.asList(
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
    }
    private List<Row> cau01() {
        return db.getNhanViens().stream()
                .sorted(Comparator.comparing(NhanVien::getMaNhanVien))
                .map(nv -> Row.of(nv.getMaNhanVien(), nv.getHoTen(), nv.getSoDienThoai(), nv.getChucVu(), nv.getLuongCoBan()))
                .collect(Collectors.toList());
    }
    private List<Row> cau02() {
        return db.getNhanViens().stream()
                .filter(nv -> nv.getChucVu() == chucVu.BAN_HANG)
                .sorted(Comparator.comparing(NhanVien::getMaNhanVien))
                .map(nv -> Row.of(nv.getMaNhanVien(), nv.getHoTen(), nv.getSoDienThoai(), nv.getChucVu(), nv.getLuongCoBan()))
                .collect(Collectors.toList());
    }
    private List<Row> cau03() {
        return db.getNhaCungCaps().stream()
                .sorted(Comparator.comparing(NhaCungCap::getMaNhaCungCap))
                .map(ncc -> Row.of(ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSoDienThoai(), ncc.getDiaChi()))
                .collect(Collectors.toList());
    }
    private List<Row> cau04() {
        return Collections.emptyList();
    }
    private List<Row> cau05() {
        return Collections.emptyList();
    }
    private List<Row> cau06() {
        return Collections.emptyList();
    }
    private List<Row> cau07() {
        return Collections.emptyList();
    }
    private List<Row> cau08() {
        return Collections.emptyList();
    }
    private List<Row> cau09() {
        return Collections.emptyList();
    }
    private List<Row> cau10() {
        return Collections.emptyList();
    }
}
