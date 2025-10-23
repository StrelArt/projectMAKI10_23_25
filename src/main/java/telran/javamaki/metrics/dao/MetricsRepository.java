package telran.javamaki.metrics.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.javamaki.metrics.model.Metrics;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MetricsRepository extends MongoRepository<Metrics, String> {
    Optional<Metrics> findByMetricsId(String metricsId);

    List<Metrics> findByPatientId(String patientId);

    Optional<Metrics> findFirstByPatientIdOrderByMeasurementTimeDesc(String patientId);

    List<Metrics> findByPatientIdAndMeasurementTimeBetween(String patientId, LocalDateTime start, LocalDateTime end);


}
