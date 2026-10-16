package clothing;

import clothing.data.DataStore;
import clothing.query.BaseQuery;
import clothing.query.QueryNhung;
import clothing.query.QueryThu;
import clothing.query.QueryTran;
import clothing.util.ConsoleTable;
import clothing.util.Row;

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

        System.out.println("-1. Quay lại menu thành viên");
        System.out.println("0. Thoát chương trình");
        System.out.println("===================================");
    }

    private static void chayMenuTruyVan(BaseQuery query, Scanner sc) {
        boolean tiepTuc = true;

        while (tiepTuc) {
            inMenuTruyVan(query);
            System.out.print("Nhập câu truy vấn: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 0) {
                tiepTuc = false;
            } else if (choice >= 1 && choice <= query.getTitles().size()) {
                try {
                    List<Row> result = query.execute(choice, sc);
                    String header = query.getHeaders().get(choice - 1);

                    System.out.println("\n" + query.getTitles().get(choice - 1));
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

    private static void choNhanEnter(Scanner sc) {
        System.out.println("\nNhan Enter de quay lai menu...");
        sc.nextLine();
    }
}