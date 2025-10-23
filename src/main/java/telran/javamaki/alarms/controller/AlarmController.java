package telran.javamaki.alarms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.javamaki.alarms.dto.AlarmDto;
import telran.javamaki.alarms.service.AlarmService;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AlarmController{

    private final AlarmService alarmService;

    @GetMapping("/patients/{patientId}/alarms/last")
    public AlarmDto getLastAlarmByPatientId(@PathVariable String patientId) {
        return alarmService.getLastAlarmByPatientId(patientId);
    }


    @GetMapping("/doctors/{doctorsId}/alarms")
    public Iterable<AlarmDto> getAllAlarmsByDoctorsId(@PathVariable String doctorsId) {
        return alarmService.getAllAlarmsByDoctorsId(doctorsId);
    }


    @GetMapping("/patients/{patientId}/alarms/all")
    public Iterable<AlarmDto> getAllAlarmsByPatientId(@PathVariable String patientId) {
        return alarmService.getAllAlarmsByPatientId(patientId);
    }


    @GetMapping("/patients/{patientId}/alarms/period")
    public Iterable<AlarmDto> getAlarmsByPeriodByPatientId(@PathVariable String patientId, @RequestParam("dateFrom")
    LocalDate startDate, @RequestParam("dateTo") LocalDate endDate) {
        return alarmService.getAlarmsByPeriodByPatientId(patientId, startDate, endDate);
    }


    @DeleteMapping("/patients/{patientId}/alarms/all")
    public Iterable<AlarmDto> deleteAllAlarmsByPatientId(@PathVariable String patientId) {
        return alarmService.deleteAllAlarmsByPatientId(patientId);
    }
}
