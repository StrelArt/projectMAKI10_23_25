package telran.javamaki.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    private final CustomWebSecurity webSecurity;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/account/register/**", "/hospitals/**", "/account/doctors").permitAll()


                        .requestMatchers(HttpMethod.GET, "/account/patients/{patientId}/alarms/**")
                        .access((authentication, context) ->
                                new AuthorizationDecision(webSecurity.isLoginId(authentication.get().getName(), context.getVariables().get("patientId").toString()) &&
                                        webSecurity.isDoctor(authentication.get().getName(), context.getVariables().get("patientId").toString())))


                        .requestMatchers(HttpMethod.DELETE, "/account/patients/{patientId}/alarms/**")
                        .access((authentication, context) ->
                                new AuthorizationDecision(webSecurity.isLoginId(authentication.get().getName(), context.getVariables().get("patientId").toString()) &&
                                        webSecurity.isDoctor(authentication.get().getName(), context.getVariables().get("patientId").toString())))

                        .requestMatchers(HttpMethod.GET, "/account/doctors/{doctorsId}/alarms")
                        .access((authentication, context) ->
                                new AuthorizationDecision(webSecurity.isLoginId(authentication.get().getName(), context.getVariables().get("doctorsId").toString())))

                        .requestMatchers(HttpMethod.POST, "/account/measurements/{patientId}")
                        .access((authentication, context) ->
                                new AuthorizationDecision(webSecurity.isLoginId(authentication.get().getName(), context.getVariables().get("patientId").toString())))

                        .requestMatchers(HttpMethod.DELETE, "/account/measurements/{patientId}")
                        .access((authentication, context) ->
                                new AuthorizationDecision(webSecurity.isLoginId(authentication.get().getName(), context.getVariables().get("patientId").toString())))

                        .requestMatchers(HttpMethod.DELETE, "/account/measurements/{metricsId}")
                        .access((authentication, context) ->
                                new AuthorizationDecision(webSecurity.isLoginMetrics(authentication.get().getName(), context.getVariables().get("metricsId").toString())))

                        .requestMatchers(HttpMethod.GET, "/account/measurements/{patientId}/**")
                        .access((authentication, context) ->
                                new AuthorizationDecision(webSecurity.isLoginId(authentication.get().getName(), context.getVariables().get("patientId").toString()) &&
                                        webSecurity.isDoctor(authentication.get().getName(), context.getVariables().get("patientId").toString())))


                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // 🔹 Basic Auth
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
