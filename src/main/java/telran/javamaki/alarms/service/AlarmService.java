package telran.javamaki.alarms.service;

import telran.javamaki.alarms.dto.AlarmDto;

import java.time.LocalDate;

public interface AlarmService {

    AlarmDto getLastAlarmByPatientId(String patientId);

    Iterable<AlarmDto> getAllAlarmsByDoctorsId(String doctorsId);

    Iterable<AlarmDto> getAllAlarmsByPatientId(String patientId);

    Iterable<AlarmDto> getAlarmsByPeriodByPatientId(String patientId, LocalDate startDate, LocalDate endDate);

    Iterable<AlarmDto> deleteAllAlarmsByPatientId(String patientId);


}
