package telran.javamaki.alarms.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.javamaki.alarms.model.Alarm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlarmsRepository extends MongoRepository<Alarm, String> {
   Optional<Alarm> findFirstByPatientIdOrderByAlarmTimeDesc(String patientId);

   List<Alarm> findByPatientId(String patientId);

   List<Alarm> findByPatientIdAndAlarmTimeBetween(String patientId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
