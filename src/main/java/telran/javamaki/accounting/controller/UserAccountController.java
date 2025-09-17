package telran.javamaki.accounting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.javamaki.accounting.dao.UserRepository;
import telran.javamaki.accounting.dto.UserDto;
import telran.javamaki.accounting.dto.UserRegisterDoctorDto;
import telran.javamaki.accounting.dto.UserRegisterPatientDto;
import telran.javamaki.accounting.service.UserAccountService;
import telran.javamaki.security.JwtService;
import telran.javamaki.security.UserDetailsServiceImpl;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController{
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserAccountService userAccountService;


    @PostMapping("/register/doctor")
    public UserDto registerDoctor(@RequestBody @Valid UserRegisterDoctorDto userRegisterDoctorDto) {
        return userAccountService.registerDoctor(userRegisterDoctorDto);
    }

    @PostMapping("/register/patient")
    public UserDto registerPatient(@RequestBody @Valid UserRegisterPatientDto userRegisterPatientDto) {
        return userAccountService.registerPatient(userRegisterPatientDto);
    }


}
