package clothing.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class ConsoleTable {
    private ConsoleTable() { }
    public static String raw(Object value) {
        if (value == null) return "NULL";
        if (value instanceof BigDecimal) return ((BigDecimal)value).stripTrailingZeros().toPlainString();
        if (value instanceof LocalDate) return ((LocalDate)value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (value instanceof LocalDateTime) return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return value.toString();
    }
    public static void print(String header, List<Row> rows) {
        NumberFormat money = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        money.setMaximumFractionDigits(2);

        // Tách tên các cột từ header
        String[] headers = header.split("\\|");

        // Chuyển dữ liệu thành chuỗi trước khi in
        List<List<String>> data = new ArrayList<>();

        for (Row row : rows) {
            List<String> values = new ArrayList<>();

            for (Object value : row.getValues()) {
                if (value instanceof BigDecimal) {
                    values.add(money.format(value));
                } else {
                    values.add(raw(value));
                }
            }
            data.add(values);
        }

        // Tính độ rộng tối đa của từng cột
        int columnCount = headers.length;
        int[] widths = new int[columnCount];

        for (int i = 0; i < columnCount; i++) {
            widths[i] = headers[i].trim().length();
        }
        for (List<String> row : data) {
            for (int i = 0; i < columnCount; i++) {
                if (i < row.size()) {
                    widths[i] = Math.max(widths[i], row.get(i).length());
                }
            }
        }

        // In header
        printLine(headers, widths);

        // In đường phân cách
        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < widths[i] + 2; j++) {
                System.out.print("-");
            }
            if (i < columnCount - 1) {
                System.out.print("+");
            }
        }
        System.out.println();

        // In dữ liệu
        for (List<String> row : data) {
            for (int i = 0; i < columnCount; i++) {
                String value = i < row.size() ? row.get(i) : "";
                System.out.printf(" %-" + widths[i] + "s ", value);

                if (i < columnCount - 1) System.out.print("|");
            }
            System.out.println();
        }
        if (rows.isEmpty()) System.out.println("(Không có dữ liệu)");

        System.out.println("Số dòng: " + rows.size());
    }

    // In một dòng header với độ rộng đã tính
    private static void printLine(String[] headers, int[] widths) {
        for (int i = 0; i < headers.length; i++) {
            System.out.printf(
                    " %-" + widths[i] + "s ",
                    headers[i].trim()
            );
            if (i < headers.length - 1) System.out.print("|");
        }
        System.out.println();
    }
}
