package challenge.adverty.csvquery.report;

import challenge.adverty.csvquery.csv.CsvRecord;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReportService {

    private final MongoTemplate mongoTemplate;

    public List<ReportRecord> fetchReport(ReportRequestForm reportRequestForm) {
        Aggregation aggregation = getAggregation(reportRequestForm);
        return mongoTemplate.aggregate(aggregation, CsvRecord.class, ReportRecord.class).getMappedResults();
    }

    private Aggregation getAggregation(ReportRequestForm reportRequestForm) {
        System.out.println(reportRequestForm);
        List<AggregationOperation> operations = new ArrayList<>();
        addFiltersToOperations(reportRequestForm, operations);
        addGroupByToOperations(reportRequestForm, operations);
        addOrderByToOperations(reportRequestForm, operations);
        return Aggregation.newAggregation(operations);
    }

    private void addOrderByToOperations(ReportRequestForm reportRequestForm, List<AggregationOperation> operations) {
        OrderByOption orderBy = reportRequestForm.orderBy();
        if (null == orderBy) {
            return;
        }
        Direction orderDir = Optional.ofNullable(reportRequestForm.orderDir()).orElse(Direction.ASC);
        SortOperation sortOperation = switch (orderBy) {
            case DATASOURCE -> Aggregation.sort(orderDir, "datasource");
            case CAMPAIGN -> Aggregation.sort(orderDir, "campaign");
            case DAY -> Aggregation.sort(orderDir, "day");
        };
        operations.add(sortOperation);
    }

    private void addGroupByToOperations(ReportRequestForm reportRequestForm, List<AggregationOperation> operations) {
        GroupByOption groupByOption = reportRequestForm.groupBy();
        if (null == groupByOption) {
            return;
        }

        GroupOperation groupOperation = switch (groupByOption) {
            case DATASOURCE -> Aggregation.group("datasource")
                    .first("datasource").as("datasource")
                    .sum("clicks").as("clicks")
                    .sum("impressions").as("impressions");
            case CAMPAIGN -> Aggregation.group("campaign")
                    .first("campaign").as("campaign")
                    .sum("clicks").as("clicks")
                    .sum("impressions").as("impressions");
            case DATASOURCE_CAMPAIGN -> Aggregation.group("datasource", "campaign")
                    .first("datasource").as("datasource")
                    .first("campaign").as("campaign")
                    .sum("clicks").as("clicks")
                    .sum("impressions").as("impressions");
            case DAY -> Aggregation.group("day")
                    .first("day").as("day")
                    .sum("clicks").as("clicks")
                    .sum("impressions").as("impressions");
        };
        operations.add(groupOperation);
    }

    private void addFiltersToOperations(ReportRequestForm reportRequestForm, List<AggregationOperation> operations) {
        //TODO: date filters
        if (null == reportRequestForm.filterBy()) {
            return;
        }
        reportRequestForm.filterBy().forEach(reportFilter -> {
            if (!reportFilter.values().isEmpty()) {
                operations.add(Aggregation.match(Criteria.where(
                        reportFilter.field().getName()).in(reportFilter.values())
                ));
            } else if (null != reportFilter.between() && null != reportFilter.and()) {
                operations.add(Aggregation.match(Criteria.where(
                        reportFilter.field().getName()).gte(reportFilter.between()).lte(reportFilter.and())
                ));
            }
        });
    }
}
