package telran.javamaki.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.javamaki.accounting.dao.UserRepository;
import telran.javamaki.accounting.model.UserAccount;
import telran.javamaki.metrics.dao.MetricsRepository;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {
    private final UserRepository userRepository;
    private final MetricsRepository metricsRepository;

    public boolean isLoginId (String login, String id){
        UserAccount user = userRepository.findByEmail(login).orElse(null);
        return user != null && user.getId().equals(id);
    }

    public boolean isLoginMetrics(String login, String metricsId) {
        UserAccount user = userRepository.findByEmail(login).orElse(null);
        return user != null &&
                metricsRepository.findByMetricsId(metricsId)
                        .map(m -> m.getPatientId().equals(user.getId()))
                        .orElse(false);

//        return user != null && metricsRepository.findByMetricsId(metricsId).isPresent() &&
//                metricsRepository.findByMetricsId(metricsId).get().getPatientId().equals(user.getId());
    }

    public boolean isDoctor(String login, String patientsId) {
        UserAccount doctor = userRepository.findByEmail(login).orElse(null);
        return doctor != null && doctor.getPatientIDs().contains(patientsId);
    }

}
