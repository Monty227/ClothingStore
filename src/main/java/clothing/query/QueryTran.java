package clothing.query;

import clothing.data.DataStore;
import clothing.model.*;
import clothing.util.Row;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class QueryTran extends BaseQuery {
    public QueryTran(DataStore db) {
        super(db);
    }
    public static final List<String> TITLES = Collections.unmodifiableList(Arrays.asList(
            "Liệt kê tất cả nhân viên, gồm mã nhân viên, họ tên, số điện thoại, chức vụ và lương cơ bản.",
            "Tìm các nhân viên thuộc một chức vụ cụ thể.",
            "Liệt kê tất cả nhà cung cấp, gồm mã nhà cung cấp, tên nhà cung cấp, số điện thoại và địa chỉ.",
            "Liệt kê các phiếu nhập cùng với tên nhà cung cấp và nhân viên lập phiếu.",
            "Xem chi tiết một phiếu nhập cụ thể, gồm mã phiếu nhập, sản phẩm, biến thể, số lượng và giá nhập.",
            "Tính tổng tiền nhập của từng phiếu nhập dựa trên số lượng và giá nhập trong chi tiết phiếu nhập.",
            "Tính tổng số tiền hàng đã nhập từ mỗi nhà cung cấp.",
            "Thống kê số lượng phiếu nhập và tổng tiền nhập do từng nhân viên lập.",
            "Tìm các biến thể sản phẩm đã từng được nhập nhưng hiện tại có số lượng tồn bằng 0.",
            "Tìm nhà cung cấp có tổng giá trị nhập hàng lớn hơn giá trị nhập hàng trung bình của tất cả nhà cung cấp."
    ));

    @Override
    public List<String> getTitles() {
        return TITLES;
    }

    public static final List<String> HEADERS = Collections.unmodifiableList(Arrays.asList(
            "Mã NV|Họ Tên|Số Điện Thoại|Chức Vụ|Lương Cơ Bản",
            "Mã NV|Họ Tên|Số Điện Thoại|Chức Vụ|Lương Cơ Bản",
            "Mã NCC|Tên Nhà Cung Cấp|Số Điện Thoại|Địa Chỉ",
            "Mã PN|Ngày Nhập|Tên NCC|Tên Nhân Viên|Tổng Tiền",
            "Mã PN|Tên Sản Phẩm|Mã Biến Thể|Số Lượng|Giá Nhập",
            "Mã PN|Tổng Tiền Nhập",
            "Mã NCC|Tên Nhà Cung Cấp|Tổng Tiền Đã Nhập",
            "Mã NV|Tên Nhân Viên|Số Lượng Phiếu Nhập|Tổng Tiền Nhập",
            "Mã Biến Thể|Mã Sản Phẩm|Kích Cỡ|Màu Sắc|Số Lượng Tồn|Mã Vạch",
            "Mã NCC|Tên Nhà Cung Cấp|Tổng Giá Trị Nhập"
    ));

    @Override
    public List<String> getHeaders() {
        return HEADERS;
    }

    @Override
    public List<Row> execute(int number, Scanner sc) {
        switch (number) {
            case 1:
                return cau01();
            case 2:
                System.out.println("Nhập chức vụ cần tìm (QUAN_LY, BAN_HANG, THU_KHO): ");
                String cvStr = sc.nextLine();
                return cau02(chucVu.valueOf(cvStr.trim()));
            case 3:
                return cau03();
            case 4:
                return cau04();
            case 5:
                System.out.println("Nhập mã phiếu nhập cần xem (VD: PN01): ");
                String maPN = sc.nextLine();
                return cau05(maPN.trim());
            case 6:
                return cau06();
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

    public List<Row> cau01() {
        return db.getNhanViens().stream()
                .sorted(Comparator.comparing(NhanVien::getMaNhanVien))
                .map(nv -> Row.of(nv.getMaNhanVien(), nv.getHoTen(), nv.getSoDienThoai(), nv.getChucVu(), nv.getLuongCoBan()))
                .collect(Collectors.toList());
    }

    public List<Row> cau02(chucVu cv) {
        return db.getNhanViens().stream()
                .filter(nv -> nv.getChucVu() == cv)
                .sorted(Comparator.comparing(NhanVien::getMaNhanVien))
                .map(nv -> Row.of(nv.getMaNhanVien(), nv.getHoTen(), nv.getSoDienThoai(), nv.getChucVu(), nv.getLuongCoBan()))
                .collect(Collectors.toList());
    }

    public List<Row> cau03() {
        return db.getNhaCungCaps().stream()
                .sorted(Comparator.comparing(NhaCungCap::getMaNhaCungCap))
                .map(ncc -> Row.of(ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSoDienThoai(), ncc.getDiaChi()))
                .collect(Collectors.toList());
    }

    public List<Row> cau04() {
        return db.getPhieuNhaps().stream()
                .sorted(Comparator.comparing(PhieuNhap::getMaPhieuNhap))
                .map(pn -> {
                    String tenNCC = db.getNhaCungCaps().stream()
                            .filter(ncc -> ncc.getMaNhaCungCap().equals(pn.getMaNhaCungCap()))
                            .map(NhaCungCap::getTenNhaCungCap)
                            .findFirst().orElse("Không rõ");
                    String tenNV = timTenNV(pn.getMaNhanVien());
                    return Row.of(pn.getMaPhieuNhap(), pn.getNgayNhap(), tenNCC, tenNV, pn.getTongTien());
                })
                .collect(Collectors.toList());
    }

    public List<Row> cau05(String maPN) {
        return db.getChiTietPhieuNhaps().stream()
                .filter(ct -> ct.getMaPhieuNhap().equals(maPN))
                .map(ct -> {
                    BienTheSanPham bt = db.getBienTheSanPhams().stream()
                            .filter(b -> b.getMaBienThe().equals(ct.getMaBienThe()))
                            .findFirst().orElse(null);
                    String maSP = bt != null ? bt.getMaSanPham() : "";
                    String tenSP = db.getSanPhams().stream()
                            .filter(s -> s.getMaSanPham().equals(maSP))
                            .map(SanPham::getTenSanPham)
                            .findFirst().orElse("Không rõ");
                    return Row.of(ct.getMaPhieuNhap(), tenSP, ct.getMaBienThe(), ct.getSoLuong(), ct.getGiaNhap());
                })
                .collect(Collectors.toList());
    }

    public List<Row> cau06() {
        return db.getPhieuNhaps().stream()
                .sorted(Comparator.comparing(PhieuNhap::getMaPhieuNhap))
                .map(pn -> {
                    BigDecimal tongTienChiTiet = db.getChiTietPhieuNhaps().stream()
                            .filter(ct -> ct.getMaPhieuNhap().equals(pn.getMaPhieuNhap()))
                            .map(ct -> ct.getGiaNhap().multiply(new BigDecimal(ct.getSoLuong())))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return Row.of(pn.getMaPhieuNhap(), tongTienChiTiet);
                })
                .collect(Collectors.toList());
    }

    public List<Row> cau07() {
        return db.getNhaCungCaps().stream()
                .sorted(Comparator.comparing(NhaCungCap::getMaNhaCungCap))
                .map(ncc -> {
                    BigDecimal tongTienNCC = db.getPhieuNhaps().stream()
                            .filter(pn -> pn.getMaNhaCungCap().equals(ncc.getMaNhaCungCap()))
                            .map(pn -> db.getChiTietPhieuNhaps().stream()
                                    .filter(ct -> ct.getMaPhieuNhap().equals(pn.getMaPhieuNhap()))
                                    .map(ct -> ct.getGiaNhap().multiply(new BigDecimal(ct.getSoLuong())))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return Row.of(ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), tongTienNCC);
                })
                .collect(Collectors.toList());
    }

    public List<Row> cau08() {
        return db.getNhanViens().stream()
                .sorted(Comparator.comparing(NhanVien::getMaNhanVien))
                .map(nv -> {
                    List<PhieuNhap> phieuNvs = db.getPhieuNhaps().stream()
                            .filter(pn -> pn.getMaNhanVien().equals(nv.getMaNhanVien()))
                            .collect(Collectors.toList());
                    int soLuongPN = phieuNvs.size();
                    BigDecimal tongTien = phieuNvs.stream()
                            .map(PhieuNhap::getTongTien)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return Row.of(nv.getMaNhanVien(), nv.getHoTen(), soLuongPN, tongTien);
                })
                .collect(Collectors.toList());
    }

    public List<Row> cau09() {
        Set<String> maBienTheDaNhap = db.getChiTietPhieuNhaps().stream()
                .map(ChiTietPhieuNhap::getMaBienThe)
                .collect(Collectors.toSet());

        return db.getBienTheSanPhams().stream()
                .filter(bt -> maBienTheDaNhap.contains(bt.getMaBienThe()) && bt.getSoLuongTon() == 0)
                .sorted(Comparator.comparing(BienTheSanPham::getMaBienThe))
                .map(bt -> Row.of(bt.getMaBienThe(), bt.getMaSanPham(), bt.getKichCo(), bt.getMauSac(), bt.getSoLuongTon(), bt.getMaVach()))
                .collect(Collectors.toList());
    }

    public List<Row> cau10() {
        Map<String, BigDecimal> tongTienTheoNCC = new HashMap<>();
        BigDecimal tongTatCa = BigDecimal.ZERO;
        List<NhaCungCap> nccs = db.getNhaCungCaps();

        for (NhaCungCap ncc : nccs) {
            BigDecimal total = db.getPhieuNhaps().stream()
                    .filter(pn -> pn.getMaNhaCungCap().equals(ncc.getMaNhaCungCap()))
                    .map(PhieuNhap::getTongTien)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            tongTienTheoNCC.put(ncc.getMaNhaCungCap(), total);
            tongTatCa = tongTatCa.add(total);
        }

        BigDecimal trungBinh = nccs.isEmpty() ? BigDecimal.ZERO
                : tongTatCa.divide(new BigDecimal(nccs.size()), 2, RoundingMode.HALF_UP);

        return nccs.stream()
                .filter(ncc -> tongTienTheoNCC.get(ncc.getMaNhaCungCap()).compareTo(trungBinh) > 0)
                .sorted(Comparator.comparing(NhaCungCap::getMaNhaCungCap))
                .map(ncc -> Row.of(ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), tongTienTheoNCC.get(ncc.getMaNhaCungCap())))
                .collect(Collectors.toList());
    }
}