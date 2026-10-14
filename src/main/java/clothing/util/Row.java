package clothing.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Row {
    private final List<Object> values;
    private Row(Object... values) {
        this.values = Collections.unmodifiableList(new ArrayList<Object>(Arrays.asList(values)));
    }
    public static Row of(Object... values) {
        return  new Row(values);
    }
    public List<Object> getValues() {
        return values;
    }
}