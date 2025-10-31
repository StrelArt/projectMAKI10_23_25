package telran.javamaki.metrics.dto;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class NewMetricsDto {

    @Min(30) @Max(250)
    private Integer heartRate;

    @Min(50) @Max(250)
    private Integer systolicBloodPressure;

    @Min(30) @Max(150)
    private Integer diastolicBloodPressure;

    @DecimalMin("30.0") @DecimalMax("45.0")
    private Double bodyTemperature;

    @Min(50) @Max(100)
    private Integer oxygenSaturation;
}
