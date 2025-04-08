package org.clinyx.clinyxbackend.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

public class DoctorCreateDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    private String phone;

    @Positive(message = "Consultation price must be positive")
    private double consultationPrice;

    private String description;

    private List<Integer> specialtyIds;
    private List<Integer> healthInsuranceIds;

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
    public double getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(double consultationPrice) {
        this.consultationPrice = consultationPrice;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<Integer> getSpecialtyIds() {
        return specialtyIds;
    }

    public void setSpecialtyIds(List<Integer> specialtyIds) {
        this.specialtyIds = specialtyIds;
    }
    public List<Integer> getHealthInsuranceIds() {
        return healthInsuranceIds;
    }

    public void setHealthInsuranceIds(List<Integer> healthInsuranceIds) {
        this.healthInsuranceIds = healthInsuranceIds;
    }
}