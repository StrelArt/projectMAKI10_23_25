package telran.javamaki.metrics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import telran.javamaki.metrics.dto.MetricsDto;
import telran.javamaki.metrics.dto.NewMetricsDto;
import telran.javamaki.metrics.service.MetricsService;

import java.time.LocalDate;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/account")
public class MetricsController {

    private final MetricsService metricsService;

    @PostMapping("/measurements/{patientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MetricsDto addNewMetrics(@PathVariable String patientId, @RequestBody @Valid NewMetricsDto newMetricsDto) {
        return metricsService.addNewMetrics(patientId, newMetricsDto);
    }


    @DeleteMapping("/measurements/byid/{metricsId}")
    public MetricsDto deleteMetricsById(@PathVariable String metricsId) {
        return metricsService.deleteMetricsById(metricsId);
    }

    @DeleteMapping("/measurements/bypatient/{patientId}")
    public Iterable<MetricsDto> deleteMetricsByPatientId(@PathVariable String patientId) {
        return metricsService.deleteMetricsByPatientId(patientId);
    }

    @GetMapping("/measurements/{patientId}/last")
    public MetricsDto getLastMetricsByPatientId(@PathVariable String patientId) {
        return metricsService.getLastMetricsByPatientId(patientId);
    }

    @GetMapping("/measurements/{patientId}/period")
    public Iterable<MetricsDto> getAllMetricsByPeriodByPatientId(@PathVariable String patientId,
                                                                 @RequestParam("dateFrom") LocalDate startDate, @RequestParam("dateTo") LocalDate endDate) {
        return metricsService.getAllMetricsByPeriodByPatientId(patientId, startDate, endDate);
    }


    @GetMapping("/measurements/{patientId}/all")
    public Iterable<MetricsDto> getAllMetricsByPatientId(@PathVariable String patientId) {
        return metricsService.getAllMetricsByPatientId(patientId);
    }
}
