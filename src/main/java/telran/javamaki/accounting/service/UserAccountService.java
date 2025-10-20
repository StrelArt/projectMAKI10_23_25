package telran.javamaki.accounting.service;

import telran.javamaki.accounting.dto.UserDto;
import telran.javamaki.accounting.dto.UserRegisterDoctorDto;
import telran.javamaki.accounting.dto.UserRegisterPatientDto;

public interface UserAccountService {
    UserDto registerDoctor(UserRegisterDoctorDto userRegisterDoctorDto);

    UserDto registerPatient(UserRegisterPatientDto userRegisterPatientDto);

    UserDto getUser(String email);

    UserDto deleteUser(String email);
}
