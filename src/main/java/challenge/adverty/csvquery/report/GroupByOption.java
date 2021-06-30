package challenge.adverty.csvquery.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GroupByOption {
    DATASOURCE("Datasource"),
    CAMPAIGN("Campaign"),
    DATASOURCE_CAMPAIGN("Datasource and Campaign"),
    DAY("Day");

    private final String name;
}
