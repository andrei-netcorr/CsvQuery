package challenge.adverty.csvquery.report;

import java.time.LocalDate;

public record ReportRecord(String datasource,
                           String campaign,
                           LocalDate day,
                           Long clicks,
                           Long impressions) {
    @SuppressWarnings("unused")
    public Double getClickThroughRate() {
        return impressions > 0 ? (double) clicks / impressions : null;
    }
}
