package module01.question29.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean2 {

    @Value("3")
    private int taxId;

    @Value("#{${app.tax.value} / 100.0}")
    private float taxValue;

    // this value is not present into application.properties
    // this one will have no property or default value, so the string will be injected
    // we can set it as an environment variable (app.tax.department.name=TaxDepartmentA)
    // or a VM option (-Dapp.tax.department.name=TaxDepartmentA)
    @Value("${app.tax.department.name}")
    private String taxDepartmentName;

    // injecting property or default value if property is missing
    @Value("${app.tax.department.name:no_name}")
    private String taxDepartmentAlternateName;

    public int getTaxId() {
        return taxId;
    }

    public float getTaxValue() {
        return taxValue;
    }

    public String getTaxDepartmentName() {
        return taxDepartmentName;
    }

    public String getTaxDepartmentAlternateName() {
        return taxDepartmentAlternateName;
    }
}
