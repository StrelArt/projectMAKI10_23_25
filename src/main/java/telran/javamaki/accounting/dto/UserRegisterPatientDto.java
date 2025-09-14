package telran.javamaki.accounting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
public class UserRegisterPatientDto {
    @NotBlank(message = "firstName is required")
    @Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "firstName is required")
    @Size(min = 2, max = 20, message = "lastName must be between 2 and 20 characters")
    private String lastname;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 4, max = 20, message = "password must be between 4 and 20 characters")
    private String password;

    @NotBlank(message = "bloodGroup is required")
    @Size(min = 1, max = 1, message = "bloodGroup must be between 1 and 3 characters")
    private String bloodGroup;

    @NotBlank(message = "phone is required")
    @Size(min = 10, max = 10, message = "phone must be between 10 and 10 characters")
    private String phone;

    @NotBlank(message = "hospital is required")
    @Size(min = 2, max = 20, message = "hospital must be between 2 and 20 characters")
    private String hospital;

    @NotBlank(message = "allergic is required")
    @Size(min = 2, max = 20, message = "allergic must be between 2 and 20 characters")
    private String allergic;
}
