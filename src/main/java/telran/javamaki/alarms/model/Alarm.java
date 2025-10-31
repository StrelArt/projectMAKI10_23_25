package telran.javamaki.alarms.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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

    @Indexed
    private String patientId;
    @Indexed
    private String metricsId;

    private String name;
    private String lastname;
    private String hospital;

    @Indexed
    @CreatedDate
    private LocalDateTime alarmTime;
    private String problem;


    public Alarm(String patientId, String metricsId, String problem) {
        this.patientId = patientId;
        this.metricsId = metricsId;
        this.problem = problem;
    }


}
