package org.clinyx.clinyxbackend.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "doctors")
@NoArgsConstructor
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    private BigDecimal consultationPrice;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctors_specialties",
            joinColumns = @JoinColumn(name = "id_doctor"),
            inverseJoinColumns = @JoinColumn(name = "id_specialty")
    )
    @JsonManagedReference
    private Set<SpecialtyEntity> specialties = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctors_health_insurances",
            joinColumns = @JoinColumn(name = "id_doctor"),
            inverseJoinColumns = @JoinColumn(name = "id_health_insurance")
    )
    @JsonBackReference
    private Set<HealthInsuranceEntity> healthInsurances = new HashSet<>();

    public Long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Long  idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(BigDecimal consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SpecialtyEntity> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<SpecialtyEntity> specialties) {
        this.specialties = specialties;
    }

    public Set<HealthInsuranceEntity> getHealthInsurances() {
        return healthInsurances;
    }

    public void setHealthInsurances(Set<HealthInsuranceEntity> healthInsurances) {
        this.healthInsurances = healthInsurances;
    }
}