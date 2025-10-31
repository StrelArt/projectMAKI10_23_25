package telran.javamaki.alarms.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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
