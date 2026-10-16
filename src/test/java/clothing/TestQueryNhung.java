package clothing;

import clothing.data.DataStore;
import clothing.query.QueryNhung;
import clothing.util.ConsoleTable;
import clothing.util.Row;

import java.util.List;
import java.util.Scanner;

public class TestQueryNhung {
    public static void main(String[] args) {
        DataStore db = new DataStore();
        QueryNhung query = new QueryNhung(db);
        Scanner sc = new Scanner(System.in);

        System.out.print("Chon cau truy van: ");
        int number = Integer.parseInt(sc.nextLine());

        List<Row> ketQua = query.execute(number, sc);
        String header = query.getHeaders().get(number - 1);

        ConsoleTable.print(header, ketQua);
        sc.close();
    }

}
/*
package vn.edu.sales;
import vn.edu.sales.data.SalesData;
import vn.edu.sales.query.StreamExercises;
import vn.edu.sales.util.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
// Chạy trực tiếp bằng main, không cần JUnit/dependency. Oracle lấy từ bảng kết quả C#.
public final class RegressionTest {
    public static void main(String[] args) throws Exception {
        StreamExercises query = new StreamExercises(new SalesData());
        List<String> expected = Files.readAllLines(Paths.get("src/test/resources/expected.tsv"), StandardCharsets.UTF_8);
        int cells = 0;
        for (int i = 1; i <= 20; i++) {
            String prefix = String.format("%02d\t", i);
            List<String> want = expected.stream().filter(s -> s.startsWith(prefix))
                    .map(s -> s.substring(prefix.length())).collect(Collectors.toList());
            List<String> actual = query.execute(i).stream().map(row -> row.getValues().stream()
                    .map(ConsoleTable::raw).collect(Collectors.joining("\t"))).collect(Collectors.toList());
            // Câu 5: .NET culture sort và Java Unicode sort có thể khác nhau.
            if (i == 5) { Collections.sort(want); Collections.sort(actual); }
            check(want.size() == actual.size(), "Câu " + i + ": số dòng");
            for (int j = 0; j < want.size(); j++) {
                String[] a = actual.get(j).split("\t", -1), e = want.get(j).split("\t", -1);
                check(a.length == e.length, "Số cột");
                for (int k = 0; k < e.length; k++) {
                    boolean same;
                    try { same = new BigDecimal(a[k]).compareTo(new BigDecimal(e[k])) == 0; }
                    catch (NumberFormatException ex) { same = a[k].equals(e[k]); }
                    check(same, "Câu " + i + ", dòng " + j + ", cột " + k + ": " + a[k] + " != " + e[k]);
                    cells++;
                }
            }
            System.out.println("PASS câu " + i + ": " + actual.size() + " dòng");
        }
        // Chạy lại để bắt lỗi tái sử dụng Stream đã terminal.
        check(query.execute(10).size() == 1, "Không tái sử dụng Stream");
        check(query.execute(14).get(0).getValues().get(0).equals(8), "Khách chưa đặt đơn");
        check(query.execute(18).stream().anyMatch(row -> row.getValues().get(2) == null), "LEFT JOIN thiếu null");
        boolean rejected = false;
        try { query.execute(21); } catch (IllegalArgumentException ex) { rejected = true; }
        check(rejected, "Phải từ chối câu 21");
        System.out.println("PASS tất cả 20 câu; " + cells + " ô dữ liệu; edge cases PASS.");
    }
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}

 */