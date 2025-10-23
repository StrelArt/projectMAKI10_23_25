package telran.javamaki.alarms.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "alarmId")
@NoArgsConstructor
@Builder
@Document(collection = "alarms")
public class Alarm {
    @Id
    private String alarmId;
    private String patientId;
    private String metricsId;
    private String name;
    private String lastname;
    private String hospital;
    private LocalDateTime alarmTime = LocalDateTime.now();
    private String problem;


    public Alarm(String patientId, String metricsId, String problem) {
        this.patientId = patientId;
        this.metricsId = metricsId;
        this.problem = problem;
    }


}
//    public Alarm(String patientId, String name, String lastname, String hospital, String problem) {
//        this.patientId = patientId;
////        this.name = name;
////        this.lastname = lastname;
////        this.hospital = hospital;
//        this.problem = problem;
//    }