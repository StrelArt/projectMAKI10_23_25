package telran.javamaki.alarms.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.javamaki.accounting.dao.UserRepository;
import telran.javamaki.accounting.model.Role;
import telran.javamaki.accounting.model.UserAccount;
import telran.javamaki.alarms.dao.AlarmsRepository;
import telran.javamaki.alarms.dto.AlarmDto;
import telran.javamaki.alarms.dto.exception.AlarmNotFoundException;
import telran.javamaki.alarms.model.Alarm;
import telran.javamaki.metrics.dao.MetricsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    private final AlarmsRepository alarmsRepository;
    private final MetricsRepository metricsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public AlarmDto getLastAlarmByPatientId(String patientId) {
        Alarm alarm = alarmsRepository.findFirstByPatientIdOrderByAlarmTimeDesc(patientId).orElseThrow(AlarmNotFoundException::new);
        Optional<UserAccount> userAccount = userRepository.findByIdAndRoleContaining(patientId, Role.PATIENT);
        alarm.setName(userAccount.get().getName());
        alarm.setLastname(userAccount.get().getLastName());
        alarm.setHospital(userAccount.get().getHospital());
        return modelMapper.map(alarm, AlarmDto.class);
    }

    @Override
    public Iterable<AlarmDto> getAllAlarmsByDoctorsId(String doctorsId) {
        List<String> patientIDs = userRepository.findById(doctorsId)
            .orElseThrow(() -> new RuntimeException("Doctor not found"))
            .getPatientIDs();

        List<Alarm> alarmsList = new ArrayList<>();

        for (String patientId : patientIDs) {

            List<Alarm> patientsAlarms = alarmsRepository.findByPatientId(patientId);

            if (patientsAlarms.isEmpty()) {
                continue;
            }

            userRepository.findByIdAndRoleContaining(patientId, Role.PATIENT)
                    .ifPresent(userAccount -> {
                        patientsAlarms.forEach(a -> {
                            a.setName(userAccount.getName());
                            a.setLastname(userAccount.getLastName());
                            a.setHospital(userAccount.getHospital());
                        });

                    });
            alarmsList.addAll(patientsAlarms);
        }
        return alarmsList.stream()
                .map(a -> modelMapper.map(a, AlarmDto.class))
                .toList();
    }

    @Override
    public Iterable<AlarmDto> getAllAlarmsByPatientId(String patientId) {

        List<Alarm> patientAlarms = alarmsRepository.findByPatientId(patientId);

        if(patientAlarms.isEmpty()){
            throw new AlarmNotFoundException();
        }

        userRepository.findByIdAndRoleContaining(patientId, Role.PATIENT)
                .ifPresent(userAccount -> {
                    patientAlarms.forEach(a -> {
                        a.setName(userAccount.getName());
                        a.setLastname(userAccount.getLastName());
                        a.setHospital(userAccount.getHospital());
                    });
                });

        return patientAlarms.stream()
                .map(a -> modelMapper.map(a, AlarmDto.class))
                .toList();
    }

    @Override
    public Iterable<AlarmDto> getAlarmsByPeriodByPatientId(String patientId, LocalDate startDate, LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        List <Alarm> alarmsList = alarmsRepository.findByPatientIdAndAlarmTimeBetween(patientId, startDateTime, endDateTime);

        if(alarmsList.isEmpty()){
            throw new AlarmNotFoundException();
        }

        userRepository.findByIdAndRoleContaining(patientId, Role.PATIENT)
                .ifPresent(userAccount -> {
                    alarmsList.forEach(a -> {
                        a.setName(userAccount.getName());
                        a.setLastname(userAccount.getLastName());
                        a.setHospital(userAccount.getHospital());
                    });
                });


        return alarmsList.stream()
                .map(m -> modelMapper.map(m, AlarmDto.class))
                .toList();
    }

    @Override
    @Transactional
    public Iterable<AlarmDto> deleteAllAlarmsByPatientId(String patientId) {
        List<Alarm> alarmsList = alarmsRepository.findByPatientId(patientId);

        if(alarmsList.isEmpty()){
            throw new AlarmNotFoundException();
        }

        alarmsList.forEach(alarm -> {
            metricsRepository.findByMetricsId(alarm.getMetricsId())
                    .ifPresent(metrics -> {
                metrics.getAlarms().removeIf(a -> a.getAlarmId().equals(alarm.getAlarmId()));
                metricsRepository.save(metrics);
            });
        });
            alarmsRepository.deleteAll(alarmsList);

        return alarmsList.stream()
                .map(a -> modelMapper.map(a, AlarmDto.class))
                .toList();
    }
}
