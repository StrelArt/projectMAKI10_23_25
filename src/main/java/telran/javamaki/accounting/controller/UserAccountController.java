package telran.javamaki.accounting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import telran.javamaki.accounting.dao.UserRepository;
import telran.javamaki.accounting.dto.*;
import telran.javamaki.accounting.service.UserAccountService;
import telran.javamaki.security.UserDetailsServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController{
    private final UserAccountService userAccountService;


    @PostMapping("/register/doctor")
    public UserDto registerDoctor(@RequestBody @Valid UserRegisterDoctorDto userRegisterDoctorDto) {
        return userAccountService.registerDoctor(userRegisterDoctorDto);
    }

    @PostMapping("/register/patient")
    public UserDto registerPatient(@RequestBody @Valid UserRegisterPatientDto userRegisterPatientDto) {
        return userAccountService.registerPatient(userRegisterPatientDto);
    }

    @GetMapping("/me")
    public UserDto getCurrentUser(Principal principal) {
        return userAccountService.getUser(principal.getName());
    }
}
