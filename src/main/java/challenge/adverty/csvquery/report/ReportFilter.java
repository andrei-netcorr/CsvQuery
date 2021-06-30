package challenge.adverty.csvquery.report;

import java.util.Set;

public record ReportFilter(ReportField field, Set<String> values, String between, String and) {
}
