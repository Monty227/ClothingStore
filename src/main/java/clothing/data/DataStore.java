package clothing.data;

import clothing.model.*;

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
                new SanPham()
        ));

        danhMucs = Collections.unmodifiableList(Arrays.asList(
                new DanhMuc()
        ));

        bienTheSanPhams = Collections.unmodifiableList(Arrays.asList(
                new BienTheSanPham()
        ));

        khachHangs = Collections.unmodifiableList(Arrays.asList(
                new KhachHang()
        ));

        hoaDons = Collections.unmodifiableList(Arrays.asList(
                new HoaDon()
        ));

        chiTietHoaDons = Collections.unmodifiableList(Arrays.asList(
                new ChiTietHoaDon()
        ));

        khuyenMais = Collections.unmodifiableList(Arrays.asList(
                new KhuyenMai()
        ));

        nhanViens = Collections.unmodifiableList(Arrays.asList(
                new NhanVien()
        ));

        nhaCungCaps = Collections.unmodifiableList(Arrays.asList(
                new NhaCungCap()
        ));

        phieuNhaps = Collections.unmodifiableList(Arrays.asList(
                new PhieuNhap()
        ));

        chiTietPhieuNhaps = Collections.unmodifiableList(Arrays.asList(
                new ChiTietPhieuNhap()
        ));
    }

    

    public static <T, K> void unique(List<T> data, Function<T, K> key) {
        if (data.stream().map(key).distinct().count() != data.size()) {
            throw new IllegalStateException("Trùng khóa");
        }
    }
}
