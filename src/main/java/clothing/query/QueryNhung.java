package clothing.query;

import clothing.data.DataStore;
import com.sun.rowset.internal.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class QueryNhung extends BaseQuery{
    public QueryNhung(DataStore db) {
        super(db);
    }

    public static final List<String> TITLES = Collections.unmodifiableList(Arrays.asList(
            "Liệt kê tất cả khách hàng, gồm mã khách hàng, họ tên, số điện thoại, email và hạng thành viên.",
            "Tìm các khách hàng thuộc hạng thành viên Vàng hoặc Kim cương.",
            "Liệt kê các hóa đơn đã thanh toán, gồm mã hóa đơn, ngày lập, tổng tiền, tiền giảm và thành tiền.",
            "Liệt kê hóa đơn cùng với tên khách hàng đã mua và nhân viên lập hóa đơn.",
            "Xem chi tiết một hóa đơn cụ thể, gồm mã hóa đơn, tên sản phẩm, kích cỡ, màu sắc, số lượng và đơn giá.",
            "Liệt kê các chương trình khuyến mãi đang có hiệu lực ở một thời điểm cụ thể.",
            "Tính tổng doanh thu từ các hóa đơn đã thanh toán.",
            "Thống kê số lượng hóa đơn và tổng doanh thu theo từng khách hàng.",
            "Tìm các khách hàng chưa từng có hóa đơn đã thanh toán.",
            "Liệt kê các khách hàng chưa sử dụng khuyến mãi trong các đơn hàng của mình."
    ));
    @Override
    public List<String> getTitles() {
        return TITLES;
    }

    public static final List<String> HEADERS = Collections.unmodifiableList(Arrays.asList(

    ));
    @Override
    public List<String> getHeaders() {
        return HEADERS;
    }

    public final List<Supplier<List<Row>>> QUERIES = Arrays.asList(
            this::cau01,
            this::cau02,
            this::cau03,
            this::cau04,
            this::cau05,
            this::cau06,
            this::cau07,
            this::cau08,
            this::cau09,
            this::cau10
    );
    @Override
    protected List<Supplier<List<Row>>> getQueries() {
            return QUERIES;
    }

    private List<Row> cau01() {
        return ;
    }

    private List<Row> cau02() {
        return ;
    }

    private List<Row> cau03() {
        return ;
    }

    private List<Row> cau04() {
        return ;
    }

    private List<Row> cau05() {
        return ;
    }

    private List<Row> cau06() {
        return ;
    }

    private List<Row> cau07() {
        return ;
    }

    private List<Row> cau08() {
        return ;
    }

    private List<Row> cau09() {
        return ;
    }

    private List<Row> cau10() {
        return ;
    }
}
