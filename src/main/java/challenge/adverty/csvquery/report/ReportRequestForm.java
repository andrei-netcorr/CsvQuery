package challenge.adverty.csvquery.report;

import challenge.adverty.csvquery.report.validation.CanGroupByField;
import challenge.adverty.csvquery.report.validation.CanOrderByField;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

//TODO validate filters adding extra attributes to fields
public record ReportRequestForm(@NotEmpty Set<ReportField> columns,
                                List<ReportFilter> filterBy,
                                @CanGroupByField Set<ReportField> groupBy,
                                @CanOrderByField Set<ReportField> orderBy) {
}
