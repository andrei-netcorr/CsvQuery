package challenge.adverty.csvquery.report;

import java.util.Set;

public record ReportFilter(ReportField field,
                           String value,
                           Set<String> values,
                           String intervalBegin,
                           String intervalEnd) {
}
