package module01.question10.stereotypes.service;

import module01.question10.stereotypes.components.TaxCalculationComponent;
import module01.question10.stereotypes.dao.TaxRateDao;
import module01.question10.stereotypes.ds.Article;
import module01.question10.stereotypes.ds.TaxCalculation;
import module01.question10.stereotypes.ds.TaxInformation;
import module01.question10.stereotypes.ds.TaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxInformationService {

    @Autowired
    private TaxRateDao taxRateDao;
    @Autowired
    private TaxCalculationComponent taxCalculationComponent;

    public TaxInformation getTaxInformation(Article article) {
        TaxRate taxRate = taxRateDao.getTaxRate(article);

        TaxCalculation taxCalculation = taxCalculationComponent.calculateTax(article, taxRate);

        return new TaxInformation(article, taxCalculation);
    }
}
