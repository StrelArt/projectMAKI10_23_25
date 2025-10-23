package telran.javamaki.metrics.dto;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewMetricsDto {

    @Min(30) @Max(250)
    private int heartRate;

    @Min(50) @Max(250)
    private int systolicBloodPressure;

    @Min(30) @Max(150)
    private int diastolicBloodPressure;

    @DecimalMin("30.0") @DecimalMax("45.0")
    private double bodyTemperature;

    @Min(50) @Max(100)
    private int oxygenSaturation;
}
