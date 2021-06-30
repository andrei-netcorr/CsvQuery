package challenge.adverty.csvquery.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReportField {
    DATASOURCE("Datasource", true),
    CAMPAIGN("Campaign", true),
    DAY("Day", true),
    CLICKS("Clicks", false),
    IMPRESSIONS("Impressions", false),
    CLICK_THROUGH_RATE("ClickThroughRate", false);

    private final String name;
    private final boolean canFilterBy;
}
