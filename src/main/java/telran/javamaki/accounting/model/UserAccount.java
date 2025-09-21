package telran.javamaki.accounting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@Document(collection = "users_maki")
public class UserAccount {
    @Id
    private String id;
    @Setter
    private String password;
    private LocalDate passwordUpdated = LocalDate.now();
    @Setter
    private String name;
    @Setter
    private String lastName;
    @Setter
    private String email;
    @Setter
    private Role role;

//    public boolean addRole(String role) {
//        return roles.add(Role.valueOf(role.toUpperCase()));
//    }
//
//    public boolean removeRole(String role) {
//        return roles.remove(Role.valueOf(role.toUpperCase()));
//    }
}
