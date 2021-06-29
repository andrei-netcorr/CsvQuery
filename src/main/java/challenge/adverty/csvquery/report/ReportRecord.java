package challenge.adverty.csvquery.report;

public record ReportRecord(String datasource,
                           String campaign,
                           int day,
                           int week,
                           int month,
                           long clicks,
                           long impressions,
                           double clickThroughRate) {
}
