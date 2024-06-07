package module01.question10.stereotypes.dao;

import module01.question10.stereotypes.ds.Article;
import module01.question10.stereotypes.ds.TaxRate;
import org.springframework.stereotype.Repository;

@Repository
public class TaxRateDao {

    public TaxRate getTaxRate(Article article) {
        return new TaxRate();
    }
}
