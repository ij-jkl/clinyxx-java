package org.clinyx.clinyxbackend.Dtos.DoctorDtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.clinyx.clinyxbackend.Dtos.HealthInsuranceDtos.HealthInsuranceDto;
import org.clinyx.clinyxbackend.Dtos.SpecialtyDtos.SpecialtyDto;

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