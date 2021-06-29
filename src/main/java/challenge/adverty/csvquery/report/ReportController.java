package challenge.adverty.csvquery.report;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {

    //TODO: @ExceptionHandler
    @PostMapping
    public List<ReportRecord> fetchReport(@Valid @RequestBody ReportRequestForm reportRequestForm) {
        System.out.println(reportRequestForm);
        return null;
    }
}
