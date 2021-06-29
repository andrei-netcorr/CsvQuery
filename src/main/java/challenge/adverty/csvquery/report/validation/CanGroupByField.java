package challenge.adverty.csvquery.report.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CanGroupByFieldValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CanGroupByField {
    String message() default "Invalid group by option";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
