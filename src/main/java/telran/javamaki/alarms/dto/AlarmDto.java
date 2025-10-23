package telran.javamaki.alarms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmDto {
     private String patientId;
     private String metricsId;
     private String name;
     private String lastname;
     private String hospital;
     private LocalDateTime alarmTime;
     private String problem;
}
