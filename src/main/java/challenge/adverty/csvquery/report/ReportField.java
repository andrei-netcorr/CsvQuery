package challenge.adverty.csvquery.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReportField {
    DATASOURCE("Datasource", true, true),
    CAMPAIGN("Campaign", true, true),
    DAY("Day", true, true),
    WEEK("Week", true, true),
    MONTH("Month", true, true),
    CLICKS("Clicks", false, true),
    IMPRESSIONS("Impressions", false, true),
    CLICK_THROUGH_RATE("ClickThroughRate", false, true);

    private final String name;
    private final boolean canFilterBy;
    private final boolean canGroupBy;
}
