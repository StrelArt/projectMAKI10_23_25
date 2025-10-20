package telran.javamaki.accounting.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.javamaki.accounting.dao.UserRepository;
import telran.javamaki.accounting.dto.UserDto;
import telran.javamaki.accounting.dto.UserRegisterDoctorDto;
import telran.javamaki.accounting.dto.UserRegisterPatientDto;
import telran.javamaki.accounting.dto.exception.UserExistsException;
import telran.javamaki.accounting.dto.exception.UserNotFoundException;
import telran.javamaki.accounting.model.Role;
import telran.javamaki.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerDoctor(UserRegisterDoctorDto userRegisterDoctorDto) {
        if (userRepository.existsByEmail(userRegisterDoctorDto.getEmail())) {
            throw new UserExistsException();
        }
        UserAccount userAccount = modelMapper.map(userRegisterDoctorDto, UserAccount.class);
        userAccount.setRole(Role.DOCTOR);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto registerPatient(UserRegisterPatientDto userRegisterPatientDto) {
        if (userRepository.existsByEmail(userRegisterPatientDto.getEmail())) {
            throw new UserExistsException();
        }
        UserAccount userAccount = modelMapper.map(userRegisterPatientDto, UserAccount.class);
        userAccount.setRole(Role.PATIENT);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        UserAccount userAccount = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String email) {
        UserAccount userAccount = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }
}
