package org.clinyx.clinyxbackend.Dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto {
    private Long idDoctor;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal consultationPrice;
    private String description;
    private Set<SpecialtyDto> specialties;
    private Set<HealthInsuranceDto> healthInsurances;
}