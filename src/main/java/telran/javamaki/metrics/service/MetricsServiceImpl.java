package telran.javamaki.metrics.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.javamaki.alarms.dao.AlarmsRepository;
import telran.javamaki.alarms.model.Alarm;
import telran.javamaki.metrics.dao.MetricsRepository;
import telran.javamaki.metrics.dto.MetricsDto;
import telran.javamaki.metrics.dto.NewMetricsDto;
import telran.javamaki.metrics.dto.exception.MetricsNotFoundException;
import telran.javamaki.metrics.model.Metrics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService{
    private final MetricsRepository metricsRepository;
    private final AlarmsRepository alarmsRepository;
    private final ModelMapper modelMapper;


    @Override
    public MetricsDto addNewMetrics(String patientId, NewMetricsDto newMetricsDto) {
        Metrics metrics = modelMapper.map(newMetricsDto, Metrics.class);
        metrics.setPatientId(patientId);

        metricsRepository.save(metrics);

        List<Alarm> alarms = new ArrayList<>();

        if (metrics.getBodyTemperature() > 37.5) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Body temperature is too high" + metrics.getBodyTemperature()));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Body temperature:" + metrics.getBodyTemperature() + ""));
        }
        if (metrics.getBodyTemperature() < 35.0) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Body temperature is too low"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Body temperature:" + metrics.getBodyTemperature() + ""));
        }
        if (metrics.getHeartRate() > 150) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Heart rate is too high"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Heart rate:" + metrics.getHeartRate() + ""));

        }
        if (metrics.getHeartRate() < 45) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Heart rate is too low"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Heart rate:" + metrics.getHeartRate() + ""));
        }
        if (metrics.getDiastolicBloodPressure() > 90) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Diastolic blood pressure is too high"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Diastolic blood pressure:" + metrics.getDiastolicBloodPressure() + ""));
        }
        if (metrics.getDiastolicBloodPressure() < 60) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Diastolic blood pressure is too low"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Diastolic blood pressure:" + metrics.getDiastolicBloodPressure() + ""));
        }
        if (metrics.getSystolicBloodPressure() > 120) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Systolic blood pressure is too high"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Systolic blood pressure:" + metrics.getSystolicBloodPressure() + ""));
        }
        if (metrics.getSystolicBloodPressure() < 80) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Systolic blood pressure is too low"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Systolic blood pressure:" + metrics.getSystolicBloodPressure() + ""));
        }
        if (metrics.getOxygenSaturation() < 85) {
            //alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Systolic blood pressure is too low"));
            alarms.add(new Alarm(patientId, metrics.getMetricsId(), "Oxygen Saturation:" + metrics.getOxygenSaturation() + ""));
        }

        if(!alarms.isEmpty()){
            alarmsRepository.saveAll(alarms);
            metrics.getAlarms().addAll(alarms);
            metricsRepository.save(metrics);
        }

            return modelMapper.map(metrics, MetricsDto.class);
        }


    @Override
    @Transactional
    public MetricsDto deleteMetricsById(String metricsId) {
        Metrics metrics = metricsRepository.findByMetricsId(metricsId).orElseThrow(MetricsNotFoundException::new);
        alarmsRepository.deleteAll(metrics.getAlarms());
        MetricsDto dto = modelMapper.map(metrics, MetricsDto.class);
        metricsRepository.delete(metrics);
        return dto;
    }

    @Override
    @Transactional
    public Iterable<MetricsDto> deleteMetricsByPatientId(String patientId) {
        List<Metrics> metricsList = metricsRepository.findByPatientId(patientId);

        if(metricsList.isEmpty()){
            throw new MetricsNotFoundException();
        }

        metricsList.forEach(m -> alarmsRepository.deleteAll(m.getAlarms()));

        List<MetricsDto> metricsDtos = metricsList.stream()
                .map(m -> modelMapper.map(m, MetricsDto.class))
                .toList();

        metricsRepository.deleteAll(metricsList);
        return metricsDtos;
    }

    @Override
    public MetricsDto getLastMetricsByPatientId(String patientId) {
        Metrics metrics = metricsRepository.findFirstByPatientIdOrderByMeasurementTimeDesc(patientId).orElseThrow(MetricsNotFoundException::new);
        return modelMapper.map(metrics, MetricsDto.class);
    }

    @Override
    public Iterable<MetricsDto> getAllMetricsByPeriodByPatientId(String patientId, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        List<Metrics> metricsList = metricsRepository.findByPatientIdAndMeasurementTimeBetween(patientId, startDateTime, endDateTime);

        if(metricsList.isEmpty()){
            throw new MetricsNotFoundException();
        }

        return metricsList.stream()
                .map(m -> modelMapper.map(m, MetricsDto.class))
                .toList();
    }

    @Override
    public Iterable<MetricsDto> getAllMetricsByPatientId(String patientId) {
        List<Metrics> metricsList = metricsRepository.findByPatientId(patientId);

        if(metricsList.isEmpty()){
            throw new MetricsNotFoundException();
        }

        List<MetricsDto> metricsDtos = metricsList.stream()
                .map(m -> modelMapper.map(m, MetricsDto.class))
                .toList();

        return metricsDtos;
    }
}
