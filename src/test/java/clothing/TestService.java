package clothing;

import clothing.util.ConsoleTable;
import clothing.util.Row;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class TestService {
    private TestService() {
    }

    // Đọc file kết quả chuẩn
    public static List<String> docFile(String tenFile) throws IOException {
        return Files.readAllLines(
                Paths.get("src/test/resources", tenFile),
                StandardCharsets.UTF_8
        );
    }

    // Lấy các dòng thuộc một mã câu truy vấn
    public static List<String> layDongTheoMa(List<String> expected, String maCau) {

        List<String> ketQua = new ArrayList<String>();
        String prefix = maCau + "\t";

        for (String dong : expected) {
            if (dong.startsWith(prefix)) {
                ketQua.add(dong.substring(prefix.length()));
            }
        }
        return ketQua;
    }

    // Chuyển kết quả truy vấn thành chuỗi TSV
    public static List<String> chuyenThanhTsv(List<Row> rows) {
        List<String> ketQua = new ArrayList<String>();

        for (Row row : rows) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < row.getValues().size(); i++) {
                if (i > 0) {
                    sb.append('\t');
                }
                sb.append(ConsoleTable.raw(row.getValues().get(i)));
            }
            ketQua.add(sb.toString());
        }
        return ketQua;
    }

    // So sánh kết quả chuẩn với kết quả thực tế
    public static boolean soSanh(String maCau, List<String> chuan, List<String> thucTe) {

        if (chuan.equals(thucTe)) {
            System.out.println("PASS " + maCau);
            return true;
        }

        System.out.println("FAIL " + maCau);
        inKhacBiet(chuan, thucTe);
        return false;
    }

    // In các dòng bị khác nhau
    public static void inKhacBiet(List<String> chuan, List<String> thucTe) {
        if (chuan.size() != thucTe.size()) {
            System.out.println("  So dong chuan: " + chuan.size() + ", so dong thuc te: " + thucTe.size());
        }

        int max = Math.max(chuan.size(), thucTe.size());
        for (int i = 0; i < max; i++) {
            String dongChuan = i < chuan.size() ? chuan.get(i) : "<khong co dong>";
            String dongThucTe = i < thucTe.size() ? thucTe.get(i) : "<khong co dong>";

            if (!dongChuan.equals(dongThucTe)) {
                System.out.println("  Dong " + (i + 1));
                System.out.println("    Chuan: " + dongChuan);
                System.out.println("    Thuc te: " + dongThucTe);
            }
        }
    }

    // So sánh một ô dữ liệu:
    // Nếu cả hai là số thì so sánh giá trị số.
    // Nếu không phải số thì so sánh chuỗi.
    public static boolean cungGiaTri(String a, String b) {
        try {
            return new BigDecimal(a).compareTo(new BigDecimal(b)) == 0;
        } catch (NumberFormatException | NullPointerException ex) {
            return java.util.Objects.equals(a, b);
        }
    }

    // Kiểm tra số dòng, số cột và từng ô dữ liệu
    public static boolean kiemTraChiTiet(String maCau, List<String> chuan, List<String> thucTe) {
        boolean dung = true;

        if (chuan.size() != thucTe.size()) {
            System.out.println("FAIL " + maCau + ": sai so dong. Chuan = " + chuan.size() + ", thuc te = " + thucTe.size());
            dung = false;
        }

        int soDong = Math.min(chuan.size(), thucTe.size());
        for (int i = 0; i < soDong; i++) {
            String[] e = chuan.get(i).split("\t", -1);
            String[] a = thucTe.get(i).split("\t", -1);

            if (e.length != a.length) {
                System.out.println("FAIL " + maCau
                        + ", dong " + (i + 1)
                        + ": sai so cot. Chuan = " + e.length
                        + ", thuc te = " + a.length);
                dung = false;
                continue;
            }

            for (int j = 0; j < e.length; j++) {
                if (!cungGiaTri(e[j], a[j])) {
                    System.out.println("FAIL " + maCau
                            + ", dong " + (i + 1)
                            + ", cot " + (j + 1)
                            + ": chuan = [" + e[j]
                            + "], thuc te = [" + a[j] + "]");
                    dung = false;
                }
            }
        }
        if (dung) System.out.println("PASS " + maCau + ": " + thucTe.size() + " dong");
        return dung;
    }



    // Kiểm tra một truy vấn có thể chạy lại ổn định hay không
    public static void testChayLaiTruyVan(Supplier<List<Row>> truyVan) {
        List<String> lanDau = chuyenThanhTsv(truyVan.get());
        List<String> lanSau = chuyenThanhTsv(truyVan.get());
        check(lanDau.equals(lanSau), "Ket qua khac nhau khi chay lai");
        System.out.println("PASS: Truy van co the chay lai.");
    }

    // Kiểm tra số câu truy vấn không hợp lệ có bị từ chối hay không
    public static void testCauKhongHopLe(Runnable thaoTac) {
        boolean tuChoi = false;
        try {
            thaoTac.run();
        } catch (IllegalArgumentException ex) {
            tuChoi = true;
        }
        check(tuChoi, "Phai tu choi cau truy van khong hop le");
        System.out.println("PASS: Tu choi cau truy van khong hop le.");
    }

    // Kiểm tra điều kiện; dừng ngay nếu không đạt
    public static void check(boolean dieuKien, String thongBao) {
        if (!dieuKien) {
            throw new AssertionError(thongBao);
        }
    }
}
