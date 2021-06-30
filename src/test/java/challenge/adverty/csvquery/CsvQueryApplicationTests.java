package challenge.adverty.csvquery;

import challenge.adverty.csvquery.report.ReportField;
import challenge.adverty.csvquery.report.ReportFilter;
import challenge.adverty.csvquery.report.ReportRecord;
import challenge.adverty.csvquery.report.ReportRequestForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CsvQueryApplicationTests {

    @LocalServerPort
    private int localServerPort;
    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getRequestShouldReturnAnHttpError() {
        String serviceUrl = "http://localhost:" + localServerPort + "/api/v1/report";
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(serviceUrl,
                HttpMethod.GET,
                null,
                ErrorResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    void atLeastOneFilterShouldBeRequired() {
        ReportRequestForm reportRequestForm = new ReportRequestForm(null, null, null, null);
        ResponseEntity<ErrorResponse> responseEntity = geErrorResponseEntity(reportRequestForm);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void valuesFiltersShouldWork() {
        List<ReportFilter> reportFilters = List.of(
                new ReportFilter(ReportField.DATASOURCE, Set.of("Facebook Ads"), null, null)
        );
        ReportRequestForm reportRequestForm = new ReportRequestForm(reportFilters, null, null, null);
        ResponseEntity<List<ReportRecord>> responseEntity = getOkayResponseEntity(reportRequestForm);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @SneakyThrows
    private ResponseEntity<ErrorResponse> geErrorResponseEntity(ReportRequestForm reportRequestForm) {
        String serviceUrl = "http://localhost:" + localServerPort + "/api/v1/report";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = objectMapper.writeValueAsString(reportRequestForm);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(serviceUrl, HttpMethod.POST, requestEntity, ErrorResponse.class);
    }

    @SneakyThrows
    private ResponseEntity<List<ReportRecord>> getOkayResponseEntity(ReportRequestForm reportRequestForm) {
        String serviceUrl = "http://localhost:" + localServerPort + "/api/v1/report";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = objectMapper.writeValueAsString(reportRequestForm);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(serviceUrl, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {});
    }

    record ErrorResponse(String timestamp, Integer status, String error) {
    }

}
