package module01.question10.stereotypes.controller;

import module01.question10.stereotypes.ds.Article;
import module01.question10.stereotypes.ds.TaxInformation;
import module01.question10.stereotypes.service.TaxInformationService;
import org.springframework.stereotype.Controller;

@Controller
public class TaxInformationController {

    private final TaxInformationService taxInformationService;

    public TaxInformationController(TaxInformationService taxInformationService) {
        this.taxInformationService = taxInformationService;
    }

    public void printTaxInformation(Article article) {
        TaxInformation taxInformation = taxInformationService.getTaxInformation(article);

        String formattedTaxInformation = String.format("%s %s",
                taxInformation.article().getName(),
                taxInformation.taxCalculation().getTaxValue()
        );

        System.out.println(formattedTaxInformation);
    }
}
