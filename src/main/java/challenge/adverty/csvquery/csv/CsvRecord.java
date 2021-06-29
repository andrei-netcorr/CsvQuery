package challenge.adverty.csvquery.csv;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@JsonPropertyOrder({"datasource", "campaign", "day", "clicks", "impressions"})
public class CsvRecord {
    @Id
    private String id;
    private String datasource;
    private String campaign;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yy", timezone="UTC")
    private Date day;
    private Long clicks;
    private Long impressions;
}
