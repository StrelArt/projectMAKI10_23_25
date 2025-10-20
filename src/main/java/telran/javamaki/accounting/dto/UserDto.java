package telran.javamaki.accounting.dto;

import lombok.*;
import telran.javamaki.accounting.model.Role;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Setter
    private Role role;
    private String name;
    private String lastName;
    private String email;

}
