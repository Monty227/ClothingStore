package clothing;

import clothing.data.DataStore;
import clothing.query.QueryTran;
import clothing.util.ConsoleTable;
import clothing.util.Row;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestQueryTran {
    public static void main(String[] args) throws IOException {
        DataStore db = new DataStore();
        QueryTran query = new QueryTran(db);

        // Đọc toàn bộ file expectedTran
        List<String> expected = Files.readAllLines(
                Paths.get("src/test/resources/expectedTran"),
                StandardCharsets.UTF_8
        );
        int cells = 0;
        int soCauPass = 0;

        for (int i = 1; i <= 10; i++) {
            String prefix = String.format("%02d\t", i);

            // Lấy ra các dòng kết quả kỳ vọng cho câu thứ i
            List<String> want = expected.stream()
                    .filter(s -> s.startsWith(prefix))
                    .map(s -> s.substring(prefix.length()))
                    .collect(Collectors.toList());

            // Giả lập Scanner nhập liệu sẵn cho Câu 2 (BAN_HANG) và Câu 5 (PN01)
            String inputMock = "BAN_HANG\nPN01\n";
            Scanner sc = new Scanner(inputMock);

            // Gọi hàm execute và chuyển kết quả thành chuỗi tab-separated
            List<String> actual = query.execute(i, sc).stream()
                    .map(row -> row.getValues().stream()
                            .map(ConsoleTable::raw)
                            .collect(Collectors.joining("\t")))
                    .collect(Collectors.toList());

            // Nếu là câu 5, sắp xếp để tránh lệch thứ tự hiển thị
            if (i == 5) {
                Collections.sort(want);
                Collections.sort(actual);
            }
            // Kiểm tra số lượng dòng
            check(want.size() == actual.size(), "Câu " + i + ": Lệch số dòng (Kỳ vọng " + want.size() + ", Thực tế " + actual.size() + ")");

            // So sánh từng dòng, từng cột
            for (int j = 0; j < want.size(); j++) {
                String[] a = actual.get(j).split("\t", -1);
                String[] e = want.get(j).split("\t", -1);

                check(a.length == e.length, "Câu " + i + ", Dòng " + (j + 1) + ": Lệch số cột");

                for (int k = 0; k < e.length; k++) {
                    boolean same;
                    try {
                        same = new BigDecimal(a[k]).compareTo(new BigDecimal(e[k])) == 0;
                    } catch (NumberFormatException ex) {
                        same = a[k].equals(e[k]);
                    }
                    check(same, "Câu " + i + ", Dòng " + (j + 1) + ", Cột " + (k + 1) + ": " + a[k] + " != " + e[k]);
                    cells++;
                }
            }
            soCauPass++;
            System.out.println("PASS câu " + String.format("%02d", i) + ": " + actual.size() + " dòng");
        }
        System.out.println("\n--------------------------------------------------");
        System.out.println("ĐÃ PASS TẤT CẢ " + soCauPass + "/10 CÂU TRUY VẤN! (" + cells + " ô dữ liệu khớp hoàn toàn)");
    }
    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}