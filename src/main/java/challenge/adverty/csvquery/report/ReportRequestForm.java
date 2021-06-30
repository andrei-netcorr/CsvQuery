package challenge.adverty.csvquery.report;

import org.springframework.data.domain.Sort.Direction;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public record ReportRequestForm(@NotEmpty List<ReportFilter> filterBy,
                                GroupByOption groupBy,
                                OrderByOption orderBy,
                                Direction orderDir) {
}
