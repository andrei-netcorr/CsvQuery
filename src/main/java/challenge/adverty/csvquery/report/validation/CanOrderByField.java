package challenge.adverty.csvquery.report.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CanOrderByFieldValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CanOrderByField {
    String message() default "Invalid order by option";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
