package challenge.adverty.csvquery.report.validation;

import challenge.adverty.csvquery.report.ReportField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class CanOrderByFieldValidator implements ConstraintValidator<CanOrderByField, Set<ReportField>> {
    @Override
    public void initialize(CanOrderByField constraintAnnotation) {
    }

    @Override
    public boolean isValid(Set<ReportField> reportFields, ConstraintValidatorContext constraintValidatorContext) {
        return reportFields.stream().noneMatch(reportField -> !reportField.isCanFilterBy());
    }
}
