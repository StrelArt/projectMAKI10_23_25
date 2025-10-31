package telran.javamaki.metrics.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.javamaki.alarms.model.Alarm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "metricsId")
@NoArgsConstructor
@Builder
@Document(collection = "metrics")
public class Metrics {
    @Id
    private String metricsId;

    @Indexed
    private String patientId;

    @Indexed
    @CreatedDate
    private LocalDateTime measurementTime;
    
    private Integer heartRate;
    private Integer systolicBloodPressure;
    private Integer diastolicBloodPressure;
    private Double bodyTemperature;
    private Integer oxygenSaturation;
    private List<Alarm> alarms = new ArrayList<>();

    public Metrics(Integer heartRate, Integer systolicBloodPressure, Integer diastolicBloodPressure, Double bodyTemperature, Integer oxygenSaturation) {
        this.heartRate = heartRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.bodyTemperature = bodyTemperature;
        this.oxygenSaturation = oxygenSaturation;

    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

}
