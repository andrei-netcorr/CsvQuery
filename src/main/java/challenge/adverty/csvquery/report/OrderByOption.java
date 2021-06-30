package challenge.adverty.csvquery.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderByOption {
    DATASOURCE("Datasource"),
    CAMPAIGN("Campaign"),
    DAY("Day");

    private final String name;
}
