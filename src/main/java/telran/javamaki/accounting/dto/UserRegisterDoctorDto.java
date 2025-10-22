package telran.javamaki.accounting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Singular;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserRegisterDoctorDto {
    @NotBlank(message = "firstName is required")
    @Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "lastName is required")
    @Size(min = 2, max = 20, message = "lastName must be between 2 and 20 characters")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 4, max = 20, message = "password must be between 4 and 20 characters")
    private String password;

    @NotBlank(message = "phone is required")
    @Size(min = 10, max = 10, message = "phone must be between 10 and 10 characters")
    private String phone;

    @NotBlank(message = "jobTitle is required")
    @Size(min = 2, max = 20, message = "jobTitle must be between 2 and 20 characters")
    private String jobTitle;

    @NotBlank(message = "hospital is required")
    @Size(min = 2, max = 20, message = "hospital must be between 2 and 20 characters")
    private String hospital;


}
