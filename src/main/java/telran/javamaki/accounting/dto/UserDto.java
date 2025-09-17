package telran.javamaki.accounting.dto;

import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Singular
    private Set<String> roles;
    private String name;
    private String lastName;
    private String email;

}
