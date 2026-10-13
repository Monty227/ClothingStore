package clothing.util;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        return value.toString();
    }
    public static void print(String header, List<Row> rows) {
        System.out.println(header.replace("|", " | "));
        NumberFormat money = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        money.setMaximumFractionDigits(2);
        Consumer<Row> printRow = row -> System.out.println(row.getValues().stream()
                .map(v -> v instanceof BigDecimal ? money.format(v) : raw(v))
                .collect(Collectors.joining(" | ")));
        rows.forEach(printRow);
        if (rows.isEmpty()) System.out.println("(Không có dữ liệu)");
        System.out.println("Số dòng: " + rows.size());
    }
}
