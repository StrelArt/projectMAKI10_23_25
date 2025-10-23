package telran.javamaki.metrics.model;

import lombok.*;
import org.springframework.data.annotation.Id;
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

    private String patientId;
    private LocalDateTime measurementTime = LocalDateTime.now();
    private int heartRate;
    private int systolicBloodPressure;
    private int diastolicBloodPressure;
    private double bodyTemperature;
    private int oxygenSaturation;
    private List<Alarm> alarms = new ArrayList<>();

    public Metrics(int heartRate, int systolicBloodPressure, int diastolicBloodPressure, double bodyTemperature, int oxygenSaturation) {
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
