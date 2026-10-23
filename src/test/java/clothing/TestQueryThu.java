package clothing;

import clothing.data.DataStore;
import clothing.query.QueryThu;
import clothing.util.Row;

import java.io.IOException;
import java.util.List;

public class TestQueryThu {
    public static void main(String[] args) throws IOException {
        DataStore db = new DataStore();
        QueryThu query = new QueryThu(db);

        List<String> expected = TestService.docFile("expectedThu");

        int soPass = 0;
        int tongO = 0;
        for (int i = 1; i <= 10; i++) {
            String maCau = String.format("%02d", i);

            List<String> want = TestService.layDongTheoMa(expected, maCau);
            List<String> actual = TestService.chuyenThanhTsv(chayTruyVan(query, i));

            boolean dat = TestService.kiemTraChiTiet(maCau, want, actual);
            if (dat) soPass++;

            // Đếm số ô được so sánh ở những dòng có cùng số cột.
            int soDong = Math.min(want.size(), actual.size());
            for (int j = 0; j < soDong; j++) {
                String[] e = want.get(j).split("\t", -1);
                String[] a = actual.get(j).split("\t", -1);

                if (e.length == a.length) {
                    tongO += e.length;
                }
            }
        }
        TestService.testCauKhongHopLe(() -> chayTruyVan(query, 11));
        TestService.testChayLaiTruyVan(() -> chayTruyVan(query, 1));

        System.out.println("\n===== KET QUA =====");
        System.out.println("So cau PASS: " + soPass + "/10");
        System.out.println("So o da so sanh: " + tongO);
    }

    public static List<Row> chayTruyVan(QueryThu query, int soCau) {
        switch (soCau) {
            case 1:
                return query.cau01();
            case 2:
                return query.cau02();
            case 3:
                return query.cau03();
            case 4:
                return query.cau04();
            case 5:
                return query.cau05();
            case 6:
                return query.cau06();
            case 7:
                return query.cau07();
            case 8:
                return query.cau08("Áo");
            case 9:
                return query.cau09();
            case 10:
                return query.cau10();
            default:
                throw new IllegalArgumentException("Số câu không hợp lệ: " + soCau);
        }
    }
}

