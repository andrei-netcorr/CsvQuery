package challenge.adverty.csvquery.report;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/report")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    //TODO: @ExceptionHandler
    @PostMapping
    public List<ReportRecord> fetchReport(@Valid @RequestBody ReportRequestForm reportRequestForm) {
        return reportService.fetchReport(reportRequestForm);
    }
}
