package org.clinyx.clinyxbackend.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "health_insurances")
@Getter
@Setter
@NoArgsConstructor
public class HealthInsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHealthInsurance;

    private String insuranceName;

    @ManyToMany(mappedBy = "healthInsurances", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<DoctorEntity> doctors = new HashSet<>();
}