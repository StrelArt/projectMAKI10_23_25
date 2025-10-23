package telran.javamaki.metrics.service;

import telran.javamaki.metrics.dto.MetricsDto;
import telran.javamaki.metrics.dto.NewMetricsDto;

import java.time.LocalDate;

public interface MetricsService {
    MetricsDto addNewMetrics(String patientId, NewMetricsDto newMetricsDto);

    MetricsDto deleteMetricsById(String metricsId);

    Iterable<MetricsDto> deleteMetricsByPatientId(String patientId);

    MetricsDto getLastMetricsByPatientId(String patientId);

    Iterable<MetricsDto> getAllMetricsByPeriodByPatientId(String patientId, LocalDate startDate, LocalDate endDate);

    Iterable<MetricsDto> getAllMetricsByPatientId(String patientId);

}
