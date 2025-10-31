package telran.javamaki.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.javamaki.alarms.model.Alarm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetricsDto {
    private String metricsId;
    private String patientId;
    private LocalDateTime measurementTime;
    private Integer heartRate;
    private Integer systolicBloodPressure;
    private Integer diastolicBloodPressure;
    private Double bodyTemperature;
    private Integer oxygenSaturation;
    private List<Alarm> alarms = new ArrayList<>();
}
