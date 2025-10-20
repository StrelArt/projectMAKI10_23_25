package telran.javamaki.accounting.dto;

import lombok.Data;

// класс остался с времен экспериментов с токенами
@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
