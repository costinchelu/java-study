package howto.build_sql_query;

import java.util.List;
import java.util.stream.Collectors;

public class BuildDetailedQuery {

    public String buildQuery(List<String> listParam) {
        String aListOfItems = buildQueryParam(listParam);
        return "SELECT some_row FROM some_table WHERE cond_1=? AND cond_2=? "
                + "AND cond_3 IN (?,?) AND cond_4 IN (" + aListOfItems + ")";
    }

    public String buildQueryParam(List<String> activeMid) {
        return activeMid.stream().map(str -> "?").collect(Collectors.joining(","));
    }
}
