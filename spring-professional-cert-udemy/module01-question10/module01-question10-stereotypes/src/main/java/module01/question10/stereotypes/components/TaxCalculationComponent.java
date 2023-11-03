package module01.question10.stereotypes.components;

import module01.question10.stereotypes.ds.Article;
import module01.question10.stereotypes.ds.TaxCalculation;
import module01.question10.stereotypes.ds.TaxRate;
import org.springframework.stereotype.Component;

@Component
public class TaxCalculationComponent {

    public TaxCalculation calculateTax(Article article, TaxRate taxRate) {
        return new TaxCalculation();
    }
}
