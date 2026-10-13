package clothing.query;

import clothing.data.DataStore;
import clothing.util.Row;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class BaseQuery {
    protected final DataStore db;

    public BaseQuery(DataStore db) {
        this.db = Objects.requireNonNull(db);
    }

    public abstract List<String> getTitles();
    public abstract List<String> getHeaders();
    protected abstract List<Supplier<List<Row>>> getQueries();

    public List<Row> execute(int number) {
        List<Supplier<List<Row>>> queries = getQueries();

        if (number < 1 || number > 10) throw new IllegalArgumentException("Chọn câu từ 1 đến 10");
        return queries.get(number - 1).get();
    }
}