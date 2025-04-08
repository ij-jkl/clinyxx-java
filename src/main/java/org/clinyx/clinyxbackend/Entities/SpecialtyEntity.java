package org.clinyx.clinyxbackend.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialties")
@NoArgsConstructor
public class SpecialtyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpecialty;

    private String specialtyName;

    @ManyToMany(mappedBy = "specialties")
    @JsonBackReference // Changed from JsonIgnoreProperties
    private Set<DoctorEntity> doctors = new HashSet<>();

    public Long getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(Long idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public Set<DoctorEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<DoctorEntity> doctors) {
        this.doctors = doctors;
    }
}