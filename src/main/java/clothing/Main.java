package clothing;

import clothing.dao.*;
import clothing.data.DataStore;
import clothing.model.*;
import clothing.query.BaseQuery;
import clothing.query.QueryTran;
import clothing.query.QueryNhung;
import clothing.query.QueryThu;
import clothing.util.ConsoleTable;
import clothing.util.Row;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataStore db = new DataStore();

        // QueryThu queryThu = new QueryThu(db);
        QueryNhung queryNhung = new QueryNhung(db);
        // QueryTran queryTran = new QueryTran(db);

        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("============== MENU CHỨC NĂNG ==============");
            System.out.println("1. Truy vấn về sản phẩm của Minh Thư");
            System.out.println("2. Truy vấn về hóa đơn của Hồng Nhung");
            System.out.println("3. Truy vấn về phiếu nhập của Huyền Trân");
            System.out.println("4. Quản lý dữ liệu DAO");
            System.out.println("0. Thoát chương trình");
            System.out.println("=============================================");
            System.out.print("Nhập lựa chọn: ");
            int luaChon = Integer.parseInt(sc.nextLine());

            switch (luaChon) {
                case 1:
                    // chayMenuTruyVan(queryThu);
                    break;
                case 2:
                    chayMenuTruyVan(queryNhung, sc);
                    break;
                case 3:
                    // chayMenuTruyVan(queryTran);
                    break;
                case 4:
                    chayMenuDAO(db, sc);
                    break;
                case 0:
                    tiepTuc = false;
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    choNhanEnter(sc);
            }
        }

        sc.close();
    }

    private static void inMenuTruyVan(BaseQuery query) {
        List<String> titles = query.getTitles();

        System.out.println("\n========== MENU TRUY VẤN ==========");

        for (int i = 0; i < titles.size(); i++) {
            System.out.println((i + 1) + ". " + titles.get(i));
        }

        System.out.println("-1. Quay lại menu chức năng");
        System.out.println("0. Thoát chương trình");
        System.out.println("===================================");
    }

    private static void chayMenuTruyVan(BaseQuery query, Scanner sc) {
        boolean tiepTuc = true;

        while (tiepTuc) {
            inMenuTruyVan(query);
            System.out.print("Nhập câu truy vấn: ");
            int luaChon = Integer.parseInt(sc.nextLine());

            if (luaChon == 0) {
                System.out.println("Đã thoát chương trình.");
                System.exit(0);
            }
            else if (luaChon == -1) {
                tiepTuc = false;
            }
            else if (luaChon >= 1 && luaChon <= query.getTitles().size()) {
                try {
                    List<Row> result = query.execute(luaChon, sc);
                    String header = query.getHeaders().get(luaChon - 1);

                    System.out.println("\n" + query.getTitles().get(luaChon - 1));
                    ConsoleTable.print(header, result);
                    choNhanEnter(sc);

                } catch (Exception e) {
                    System.out.println("Lỗi khi thực hiện truy vấn: " + e.getMessage());
                    choNhanEnter(sc);
                }
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
                choNhanEnter(sc);
            }
        }
    }

    private static void inMenuDAO() {
        System.out.println("\n========== MENU QUẢN LÝ DỮ LIỆU DAO ==========");
        System.out.println("1. Quản lý sản phẩm");
        System.out.println("2. Quản lý biến thể sản phảm");
        System.out.println("3. Quản lý danh mục");
        System.out.println("4. Quản lý khách hàng");
        System.out.println("5. Quản lý hóa đơn");
        System.out.println("6. Quản lý chi tiết hóa đơn");
        System.out.println("7. Quản lý khuyến mãi");
        System.out.println("8. Quản lý nhà cung cấp");
        System.out.println("9. Quản lý nhân viên");
        System.out.println("10. Quản lý phiếu nhập");
        System.out.println("11. Quản lý chi tiết phiếu nhập");
        System.out.println("-1. Quay lại menu chức năng");
        System.out.println("0. Thoát chương trình");
        System.out.println("================================================");
    }

    private static void chayMenuDAO(DataStore db, Scanner sc) {
        boolean tiepTuc = true;

        while (tiepTuc) {
            inMenuDAO();
            System.out.print("Nhập lựa chọn: ");
            int luaChon = Integer.parseInt(sc.nextLine());;

            switch (luaChon) {
                case 1:
                    chayMenuDonGian("sản phẩm", sc, 1, db);
                    break;
                case 2:
                    chayMenuDonGian("biến thể sản phẩm", sc, 2, db);
                    break;
                case 3:
                    chayMenuDonGian("danh mục", sc, 3, db);
                    break;
                case 4:
                    chayMenuDonGian("khách hàng", sc, 4, db);
                    break;
                case 5:
                    chayMenuDonGian("hóa đơn", sc, 5, db);
                    break;
                case 6:
                    chayMenuDonGian("chi tiết hóa đơn", sc, 6, db);
                    break;
                case 7:
                    chayMenuDonGian("khuyến mãi", sc, 7, db);
                    break;
                case 8:
                    chayMenuDonGian("nhà cung cấp", sc, 8, db);
                    break;
                case 9:
                    chayMenuDonGian("nhân viên", sc, 9, db);
                    break;
                case 10:
                    chayMenuDonGian("phiếu nhập", sc, 10, db);
                    break;
                case 11:
                    chayMenuDonGian("chi tiết phiếu nhập", sc, 11, db);
                    break;
                case -1:
                    tiepTuc = false;
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    choNhanEnter(sc);
            }
        }
    }

    private static void chayMenuDonGian(String tenDuLieu, Scanner sc, int loaiDAO, DataStore db) {
        boolean tiepTuc = true;

        while (tiepTuc) {
            System.out.println("\n========== MENU QUẢN LÝ " + tenDuLieu.toUpperCase() + " ==========");
            System.out.println("1. Xem tất cả " + tenDuLieu);
            System.out.println("2. Tìm theo mã " + tenDuLieu);
            System.out.println("3. Thêm " + tenDuLieu);
            System.out.println("4. Xóa " + tenDuLieu);
            System.out.println("-1. Quay lại");
            System.out.println("0. Thoát chương trình");
            System.out.println("========================================");
            System.out.print("Nhập lựa chọn: ");
            int luaChon = Integer.parseInt(sc.nextLine());

            switch (luaChon) {
                case 1:
                    xemDuLieu(loaiDAO, db);
                    choNhanEnter(sc);
                    break;
                case 2:
                    timTheoMa(loaiDAO, sc, db);
                    choNhanEnter(sc);
                    break;
                case 3:
                    themDuLieu(loaiDAO, sc, db);
                    choNhanEnter(sc);
                    break;
                case 4:
                    xoaDuLieu(loaiDAO, sc, db);
                    choNhanEnter(sc);
                    break;
                case -1:
                    tiepTuc = false;
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    choNhanEnter(sc);
            }
        }
    }

    private static void choNhanEnter(Scanner sc) {
        System.out.println("\nNhấn Enter để quay lại menu...");
        sc.nextLine();
    }

    private static void xemDuLieu(int loaiDAO, DataStore db) {
        switch (loaiDAO) {
            case 1:
                xemSanPham(db);
                break;
            case 2:
                xemBienTheSanPham(db);
                break;
            case 3:
                xemDanhMuc(db);
                break;
            case 4:
                xemKhachHang(db);
                break;
            case 5:
                xemHoaDon(db);
                break;
            case 6:
                xemChiTietHoaDon(db);
                break;
            case 7:
                xemKhuyenMai(db);
                break;
            case 8:
                xemNhaCungCap(db);
                break;
            case 9:
                xemNhanVien(db);
                break;
            case 10:
                xemPhieuNhap(db);
                break;
            case 11:
                xemChiTietPhieuNhap(db);
                break;
            default:
                System.out.println("Loại dữ liệu không hợp lệ!");
        }
    }

    private static void xemSanPham(DataStore db) {
        SanPhamDAO dao = new SanPhamDAO(db);
        List<SanPham> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH SẢN PHẨM ==========");
        for (SanPham sp : danhSach) {
            System.out.println(sp);
            System.out.println("----------------------------------------");
        }
        System.out.println("Tổng số sản phẩm: " + danhSach.size());
    }

    private static void xemBienTheSanPham(DataStore db) {
        BienTheSanPhamDAO dao = new BienTheSanPhamDAO(db);
        List<BienTheSanPham> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách biến thể sản phẩm trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH BIẾN THỂ SẢN PHẨM ==========");
        for (BienTheSanPham btsp : danhSach) {
            System.out.println(btsp);
            System.out.println("--------------------------------------------------");
        }
        System.out.println("Tổng số biến thể: " + danhSach.size());
    }

    private static void xemDanhMuc(DataStore db) {
        DanhMucDAO dao = new DanhMucDAO(db);
        List<DanhMuc> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách danh mục trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH DANH MỤC ==========");
        for (DanhMuc dm : danhSach) {
            System.out.println(dm);
            System.out.println("----------------------------------------");
        }
        System.out.println("Tổng số danh mục: " + danhSach.size());
    }

    private static void xemKhachHang(DataStore db) {
        KhachHangDAO dao = new KhachHangDAO(db);
        List<KhachHang> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách khách hàng trống!");
            return;
        }

        for (KhachHang kh : danhSach) {
            System.out.println(kh);
            System.out.println("------------------------------------");
        }
        System.out.println("Tổng số khách hàng: " + danhSach.size());
    }

    private static void xemHoaDon(DataStore db) {
        HoaDonDAO dao = new HoaDonDAO(db);
        List<HoaDon> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH HÓA ĐƠN ==========");
        for (HoaDon hd : danhSach) {
            System.out.println(hd);
            System.out.println("---------------------------------------");
        }
        System.out.println("Tổng số hóa đơn: " + danhSach.size());
    }

    private static void xemChiTietHoaDon(DataStore db) {
        ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO(db);
        List<ChiTietHoaDon> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách chi tiết hóa đơn trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH CHI TIẾT HÓA ĐƠN ==========");
        for (ChiTietHoaDon cthd : danhSach) {
            System.out.println(cthd);
            System.out.println("-----------------------------------------------");
        }
        System.out.println("Tổng số chi tiết hóa đơn: " + danhSach.size());
    }

    private static void xemKhuyenMai(DataStore db) {
        KhuyenMaiDAO dao = new KhuyenMaiDAO(db);
        List<KhuyenMai> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách khuyến mãi trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH KHUYẾN MÃI ==========");
        for (KhuyenMai km : danhSach) {
            System.out.println(km);
            System.out.println("------------------------------------------");
        }
        System.out.println("Tổng số khuyến mãi: " + danhSach.size());
    }

    private static void xemNhaCungCap(DataStore db) {
        NhaCungCapDAO dao = new NhaCungCapDAO(db);
        List<NhaCungCap> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách nhà cung cấp trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH NHÀ CUNG CẤP ==========");
        for (NhaCungCap ncc : danhSach) {
            System.out.println(ncc);
            System.out.println("--------------------------------------------");
        }
        System.out.println("Tổng số nhà cung cấp: " + danhSach.size());
    }

    private static void xemNhanVien(DataStore db) {
        NhanVienDAO dao = new NhanVienDAO(db);
        List<NhanVien> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH NHÂN VIÊN ==========");
        for (NhanVien nv : danhSach) {
            System.out.println(nv);
            System.out.println("-----------------------------------------");
        }
        System.out.println("Tổng số nhân viên: " + danhSach.size());
    }

    private static void xemPhieuNhap(DataStore db) {
        PhieuNhapDAO dao = new PhieuNhapDAO(db);
        List<PhieuNhap> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách phiếu nhập trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH PHIẾU NHẬP ==========");
        for (PhieuNhap pn : danhSach) {
            System.out.println(pn);
            System.out.println("------------------------------------------");
        }
        System.out.println("Tổng số phiếu nhập: " + danhSach.size());
    }

    private static void xemChiTietPhieuNhap(DataStore db) {
        ChiTietPhieuNhapDAO dao = new ChiTietPhieuNhapDAO(db);
        List<ChiTietPhieuNhap> danhSach = dao.layTatCa();

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách chi tiết phiếu nhập trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH CHI TIẾT PHIẾU NHẬP ==========");
        for (ChiTietPhieuNhap ctpn : danhSach) {
            System.out.println(ctpn);
            System.out.println("---------------------------------------------------");
        }
        System.out.println("Tổng số chi tiết phiếu nhập: " + danhSach.size());
    }

    private static void timTheoMa(int loaiDAO, Scanner sc, DataStore db) {
        System.out.print("Nhập mã cần tìm: ");
        String ma = sc.nextLine().trim();

        switch (loaiDAO) {
            case 1: {
                SanPhamDAO dao = new SanPhamDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy sản phẩm!");
                break;
            }
            case 2: {
                BienTheSanPhamDAO dao = new BienTheSanPhamDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy biến thể sản phẩm!");
                break;
            }
            case 3: {
                DanhMucDAO dao = new DanhMucDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy danh mục!");
                break;
            }
            case 4: {
                KhachHangDAO dao = new KhachHangDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy khách hàng!");
                break;
            }
            case 5: {
                HoaDonDAO dao = new HoaDonDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy hóa đơn!");
                break;
            }
            case 6: {
                System.out.print("Nhập mã biến thể sản phẩm: ");
                String maBienThe = sc.nextLine().trim();

                ChiTietHoaDonDAO dao = new ChiTietHoaDonDAO(db);
                System.out.println(dao.timTheoMa(ma, maBienThe) != null
                        ? dao.timTheoMa(ma, maBienThe)
                        : "Không tìm thấy chi tiết hóa đơn!");
                break;
            }
            case 7: {
                KhuyenMaiDAO dao = new KhuyenMaiDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy khuyến mãi!");
                break;
            }
            case 8: {
                NhaCungCapDAO dao = new NhaCungCapDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy nhà cung cấp!");
                break;
            }
            case 9: {
                NhanVienDAO dao = new NhanVienDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy nhân viên!");
                break;
            }
            case 10: {
                PhieuNhapDAO dao = new PhieuNhapDAO(db);
                System.out.println(dao.timTheoMa(ma) != null
                        ? dao.timTheoMa(ma) : "Không tìm thấy phiếu nhập!");
                break;
            }
            case 11: {
                System.out.print("Nhập mã biến thể sản phẩm: ");
                String maBienThe = sc.nextLine().trim();

                ChiTietPhieuNhapDAO dao = new ChiTietPhieuNhapDAO(db);
                System.out.println(dao.timTheoMa(ma, maBienThe) != null
                        ? dao.timTheoMa(ma, maBienThe)
                        : "Không tìm thấy chi tiết phiếu nhập!");
                break;
            }
            default:
                System.out.println("Loại dữ liệu không hợp lệ!");
        }
    }

    private static void themDuLieu(int loaiDAO, Scanner sc, DataStore db) {
        switch (loaiDAO) {
            case 1:
                themSanPham(sc, db);
                break;
            case 2:
                themBienTheSanPham(sc, db);
                break;
            case 3:
                themDanhMuc(sc, db);
                break;
            case 4:
                themKhachHang(sc, db);
                break;
            case 5:
                themHoaDon(sc, db);
                break;
            case 6:
                themChiTietHoaDon(sc, db);
                break;
            case 7:
                themKhuyenMai(sc, db);
                break;
            case 8:
                themNhaCungCap(sc, db);
                break;
            case 9:
                themNhanVien(sc, db);
                break;
            case 10:
                themPhieuNhap(sc, db);
                break;
            case 11:
                themChiTietPhieuNhap(sc, db);
                break;
            default:
                System.out.println("Loại dữ liệu không hợp lệ!");
        }
    }

    private static void themSanPham(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã sản phẩm: ");
            String ma = sc.nextLine().trim();

            System.out.print("Tên sản phẩm: ");
            String ten = sc.nextLine().trim();

            System.out.print("Thương hiệu: ");
            String thuongHieu = sc.nextLine().trim();

            System.out.print("Giới tính (UNISEX, NAM, NU, TRE_EM): ");
            GioiTinh gioiTinh = GioiTinh.valueOf(sc.nextLine().trim().toUpperCase());

            System.out.print("Giá bán: ");
            BigDecimal giaBan = new BigDecimal(sc.nextLine().trim());

            System.out.print("Mã danh mục: ");
            String maDanhMuc = sc.nextLine().trim();

            SanPham sp = new SanPham(ma, ten, thuongHieu, gioiTinh, giaBan, maDanhMuc);

            boolean kq = new SanPhamDAO(db).them(sp);
            System.out.println(kq ? "Thêm sản phẩm thành công!"
                    : "Thêm thất bại! Kiểm tra mã hoặc danh mục.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themBienTheSanPham(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã biến thể: ");
            String ma = sc.nextLine().trim();

            System.out.print("Mã sản phẩm: ");
            String maSanPham = sc.nextLine().trim();

            System.out.print("Kích cỡ (S, M, L, XL): ");
            KichCo kichCo = KichCo.valueOf(sc.nextLine().trim().toUpperCase());

            System.out.print("Màu sắc: ");
            String mauSac = sc.nextLine().trim();

            System.out.print("Số lượng tồn: ");
            int soLuong = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Mã vạch: ");
            String maVach = sc.nextLine().trim();

            BienTheSanPham bt = new BienTheSanPham(ma, maSanPham, kichCo, mauSac, soLuong, maVach);

            boolean kq = new BienTheSanPhamDAO(db).them(bt);
            System.out.println(kq ? "Thêm biến thể thành công!"
                    : "Thêm thất bại! Kiểm tra mã hoặc sản phẩm.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themDanhMuc(Scanner sc, DataStore db) {
        System.out.print("Mã danh mục: ");
        String ma = sc.nextLine().trim();

        System.out.print("Tên danh mục: ");
        String ten = sc.nextLine().trim();

        System.out.print("Mô tả: ");
        String moTa = sc.nextLine().trim();

        DanhMuc dm = new DanhMuc(ma, ten, moTa);

        boolean kq = new DanhMucDAO(db).them(dm);
        System.out.println(kq ? "Thêm danh mục thành công!"
                : "Thêm thất bại! Mã danh mục có thể đã tồn tại.");
    }

    private static void themKhachHang(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã khách hàng: ");
            String ma = sc.nextLine().trim();

            System.out.print("Họ tên: ");
            String hoTen = sc.nextLine().trim();

            System.out.print("Số điện thoại: ");
            String sdt = sc.nextLine().trim();

            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Ngày sinh (yyyy-MM-dd): ");
            LocalDate ngaySinh =
                    LocalDate.parse(sc.nextLine().trim());

            System.out.print("Điểm tích lũy: ");
            int diem = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Hạng thành viên (KIMCUONG, VANG, BAC, THUONG): ");
            HangThanhVien hang =
                    HangThanhVien.valueOf(sc.nextLine().trim().toUpperCase());

            KhachHang kh = new KhachHang(
                    ma, hoTen, sdt, email, ngaySinh, diem, hang);

            boolean kq = new KhachHangDAO(db).them(kh);
            System.out.println(kq ? "Thêm khách hàng thành công!"
                    : "Thêm khách hàng thất bại!");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themHoaDon(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã hóa đơn: ");
            String ma = sc.nextLine().trim();

            System.out.print("Ngày giờ lập (yyyy-MM-ddTHH:mm): ");
            LocalDateTime ngayLap =
                    LocalDateTime.parse(sc.nextLine().trim());

            System.out.print("Mã khách hàng: ");
            String maKhachHang = sc.nextLine().trim();

            System.out.print("Mã nhân viên: ");
            String maNhanVien = sc.nextLine().trim();

            System.out.print("Mã khuyến mãi (nhập 0 nếu không có): ");
            String maKhuyenMai = sc.nextLine().trim();
            if (maKhuyenMai.equals("0")) {
                maKhuyenMai = null;
            }

            System.out.print("Phương thức thanh toán "
                    + "(CHUYEN_KHOAN, TIEN_MAT, THE): ");
            PhuongThucThanhToan pttt =
                    PhuongThucThanhToan.valueOf(
                            sc.nextLine().trim().toUpperCase());

            System.out.print("Trạng thái (DA_THANH_TOAN, DA_HUY, DOI_TRA): ");
            TrangThaiDonHang trangThai =
                    TrangThaiDonHang.valueOf(
                            sc.nextLine().trim().toUpperCase());

            HoaDon hd = new HoaDon(
                    ma, ngayLap, maKhachHang, maNhanVien,
                    maKhuyenMai, pttt, trangThai);

            boolean kq = new HoaDonDAO(db).them(hd);
            System.out.println(kq ? "Thêm hóa đơn thành công!"
                    : "Thêm hóa đơn thất bại! Kiểm tra mã liên quan.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themChiTietHoaDon(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã hóa đơn: ");
            String maHoaDon = sc.nextLine().trim();

            System.out.print("Mã biến thể sản phẩm: ");
            String maBienThe = sc.nextLine().trim();

            System.out.print("Số lượng: ");
            int soLuong = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Đơn giá: ");
            BigDecimal donGia = new BigDecimal(sc.nextLine().trim());

            ChiTietHoaDon ct = new ChiTietHoaDon(
                    maHoaDon, maBienThe, soLuong, donGia);

            boolean kq = new ChiTietHoaDonDAO(db).them(ct);
            System.out.println(kq ? "Thêm chi tiết hóa đơn thành công!"
                    : "Thêm thất bại! Kiểm tra mã hoặc dữ liệu.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themKhuyenMai(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã khuyến mãi: ");
            String ma = sc.nextLine().trim();

            System.out.print("Tên khuyến mãi: ");
            String ten = sc.nextLine().trim();

            System.out.print("Loại giảm (PHAN_TRAM, SO_TIEN): ");
            LoaiGiam loai =
                    LoaiGiam.valueOf(sc.nextLine().trim().toUpperCase());

            System.out.print("Giá trị giảm: ");
            BigDecimal giaTri = new BigDecimal(sc.nextLine().trim());

            System.out.print("Ngày bắt đầu (yyyy-MM-dd): ");
            LocalDate ngayBD = LocalDate.parse(sc.nextLine().trim());

            System.out.print("Ngày kết thúc (yyyy-MM-dd): ");
            LocalDate ngayKT = LocalDate.parse(sc.nextLine().trim());

            System.out.print("Đơn hàng tối thiểu: ");
            BigDecimal donToiThieu =
                    new BigDecimal(sc.nextLine().trim());

            KhuyenMai km = new KhuyenMai(
                    ma, ten, loai, giaTri, ngayBD, ngayKT, donToiThieu);

            boolean kq = new KhuyenMaiDAO(db).them(km);
            System.out.println(kq ? "Thêm khuyến mãi thành công!"
                    : "Thêm khuyến mãi thất bại!");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themNhaCungCap(Scanner sc, DataStore db) {
        System.out.print("Mã nhà cung cấp: ");
        String ma = sc.nextLine().trim();

        System.out.print("Tên nhà cung cấp: ");
        String ten = sc.nextLine().trim();

        System.out.print("Số điện thoại: ");
        String sdt = sc.nextLine().trim();

        System.out.print("Địa chỉ: ");
        String diaChi = sc.nextLine().trim();

        NhaCungCap ncc = new NhaCungCap(ma, ten, sdt, diaChi);

        boolean kq = new NhaCungCapDAO(db).them(ncc);
        System.out.println(kq ? "Thêm nhà cung cấp thành công!"
                : "Thêm nhà cung cấp thất bại!");
    }

    private static void themNhanVien(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã nhân viên: ");
            String ma = sc.nextLine().trim();

            System.out.print("Họ tên: ");
            String hoTen = sc.nextLine().trim();

            System.out.print("Số điện thoại: ");
            String sdt = sc.nextLine().trim();

            System.out.print("Chức vụ (QUAN_LY, BAN_HANG, THU_KHO): ");
            chucVu chucvu =
                    chucVu.valueOf(sc.nextLine().trim().toUpperCase());

            System.out.print("Lương cơ bản: ");
            BigDecimal luong =
                    new BigDecimal(sc.nextLine().trim());

            System.out.print("Ngày vào làm (yyyy-MM-dd): ");
            LocalDate ngayVaoLam =
                    LocalDate.parse(sc.nextLine().trim());

            NhanVien nv = new NhanVien(
                    ma, hoTen, sdt, chucvu, luong, ngayVaoLam);

            boolean kq = new NhanVienDAO(db).them(nv);
            System.out.println(kq ? "Thêm nhân viên thành công!"
                    : "Thêm nhân viên thất bại!");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themPhieuNhap(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã phiếu nhập: ");
            String ma = sc.nextLine().trim();

            System.out.print("Ngày nhập (yyyy-MM-dd): ");
            LocalDate ngayNhap =
                    LocalDate.parse(sc.nextLine().trim());

            System.out.print("Mã nhà cung cấp: ");
            String maNCC = sc.nextLine().trim();

            System.out.print("Mã nhân viên: ");
            String maNhanVien = sc.nextLine().trim();

            System.out.print("Tổng tiền: ");
            BigDecimal tongTien =
                    new BigDecimal(sc.nextLine().trim());

            PhieuNhap pn = new PhieuNhap(
                    ma, ngayNhap, maNCC, maNhanVien, tongTien);

            boolean kq = new PhieuNhapDAO(db).them(pn);
            System.out.println(kq ? "Thêm phiếu nhập thành công!"
                    : "Thêm phiếu nhập thất bại! Kiểm tra mã liên quan.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void themChiTietPhieuNhap(Scanner sc, DataStore db) {
        try {
            System.out.print("Mã phiếu nhập: ");
            String maPhieuNhap = sc.nextLine().trim();

            System.out.print("Mã biến thể sản phẩm: ");
            String maBienThe = sc.nextLine().trim();

            System.out.print("Số lượng nhập: ");
            int soLuong = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Giá nhập: ");
            BigDecimal giaNhap =
                    new BigDecimal(sc.nextLine().trim());

            ChiTietPhieuNhap ct = new ChiTietPhieuNhap(
                    maPhieuNhap, maBienThe, soLuong, giaNhap);

            boolean kq = new ChiTietPhieuNhapDAO(db).them(ct);
            System.out.println(kq ? "Thêm chi tiết phiếu nhập thành công!"
                    : "Thêm thất bại! Kiểm tra mã hoặc dữ liệu.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    private static void xoaDuLieu(int loaiDAO, Scanner sc, DataStore db) {
        System.out.print("Nhập mã cần xóa: ");
        String ma = sc.nextLine().trim();

        boolean ketQua = false;

        switch (loaiDAO) {
            case 1:
                ketQua = new SanPhamDAO(db).xoa(ma);
                break;
            case 2:
                ketQua = new BienTheSanPhamDAO(db).xoa(ma);
                break;
            case 3:
                ketQua = new DanhMucDAO(db).xoa(ma);
                break;
            case 4:
                ketQua = new KhachHangDAO(db).xoa(ma);
                break;
            case 5:
                ketQua = new HoaDonDAO(db).xoa(ma);
                break;
            case 7:
                ketQua = new KhuyenMaiDAO(db).xoa(ma);
                break;
            case 8:
                ketQua = new NhaCungCapDAO(db).xoa(ma);
                break;
            case 9:
                ketQua = new NhanVienDAO(db).xoa(ma);
                break;
            case 10:
                ketQua = new PhieuNhapDAO(db).xoa(ma);
                break;

            case 6: {
                System.out.print("Nhập mã biến thể sản phẩm: ");
                String maBienThe = sc.nextLine().trim();

                ketQua = new ChiTietHoaDonDAO(db)
                        .xoa(ma, maBienThe);
                break;
            }
            case 11: {
                System.out.print("Nhập mã biến thể sản phẩm: ");
                String maBienThe = sc.nextLine().trim();

                ketQua = new ChiTietPhieuNhapDAO(db)
                        .xoa(ma, maBienThe);
                break;
            }
            default:
                System.out.println("Loại dữ liệu không hợp lệ!");
                return;
        }

        if (ketQua) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Không thể xóa! Hãy kiểm tra mã hoặc các dữ liệu liên quan.");
        }
    }
}