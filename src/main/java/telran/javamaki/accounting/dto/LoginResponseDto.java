package telran.javamaki.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// класс остался с времен экспериментов с токенами
@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private String role;
    private String refreshToken;
}
