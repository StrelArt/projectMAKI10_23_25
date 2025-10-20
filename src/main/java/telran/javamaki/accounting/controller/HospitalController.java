package telran.javamaki.accounting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    @GetMapping
    public List<String> getHospitals() {
        return List.of("Clinic1", "Clinic2", "Clinic3");
    }
}
