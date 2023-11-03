package howto.build_sql_query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuildSqlDemo {

    public static void main(String[] args) {

        List<String> paramList = new ArrayList<>();
        paramList.add("true");
        paramList.add("name of agent");
        paramList.add("alipay");
        paramList.add("USD");
        paramList.add("yes");
        paramList.add("no");
        paramList.add(String.valueOf(new Date()));

        BuildDetailedQuery bdq = new BuildDetailedQuery();
        System.out.println(bdq.buildQueryParam(paramList));
        System.out.println(bdq.buildQuery(paramList));
    }
}
