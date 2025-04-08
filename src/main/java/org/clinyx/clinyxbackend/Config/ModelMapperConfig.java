package org.clinyx.clinyxbackend.Config;

import org.clinyx.clinyxbackend.Dtos.*;
import org.clinyx.clinyxbackend.Entities.DoctorEntity;
import org.clinyx.clinyxbackend.Entities.AppointmentEntity;
import org.clinyx.clinyxbackend.Entities.RoleEntity;
import org.clinyx.clinyxbackend.Entities.UserEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(DoctorEntity.class, DoctorDto.class)
                .addMappings(mapper -> {
                    mapper.map(DoctorEntity::getIdDoctor, DoctorDto::setIdDoctor);
                    mapper.map(DoctorEntity::getFirstName, DoctorDto::setFirstName);
                    mapper.map(DoctorEntity::getLastName, DoctorDto::setLastName);
                    mapper.map(DoctorEntity::getEmail, DoctorDto::setEmail);
                    mapper.map(DoctorEntity::getPhone, DoctorDto::setPhone);
                    mapper.map(DoctorEntity::getConsultationPrice, DoctorDto::setConsultationPrice);
                    mapper.map(DoctorEntity::getDescription, DoctorDto::setDescription);
                    mapper.map(DoctorEntity::getSpecialties, DoctorDto::setSpecialties);
                    mapper.map(DoctorEntity::getHealthInsurances, DoctorDto::setHealthInsurances);
                });

        modelMapper.typeMap(AppointmentCreateDto.class, AppointmentEntity.class)
                .addMappings(mapper -> mapper.skip(AppointmentEntity::setIdAppointment));

        modelMapper.typeMap(UserCreateDto.class, UserEntity.class)
                .addMappings(mapper -> mapper.skip(UserEntity::setIdUser));

        modelMapper.typeMap(RoleCreateDto.class, RoleEntity.class)
                .addMappings(mapper -> mapper.skip(RoleEntity::setIdRole));

        modelMapper.typeMap(RoleEntity.class, RoleDto.class)
                .addMappings(mapper -> mapper.skip(RoleDto::setIdRole));

        //User mapping
        modelMapper.typeMap(UserEntity.class, UserDto.class)
                .addMappings(mapper -> {
                    mapper.map(UserEntity::getIdUser, UserDto::setIdUser);
                    mapper.map(UserEntity::getFirstName, UserDto::setFirstName);
                    mapper.map(UserEntity::getLastName, UserDto::setLastName);
                    mapper.map(UserEntity::getEmail, UserDto::setEmail);
                    mapper.map(UserEntity::getRegistrationDate, UserDto::setRegistrationDate);
                }).setPostConverter(context -> {
                    UserEntity source = context.getSource();
                    UserDto destination = context.getDestination();
                    if (source.getRoles() != null) {
                        destination.setRoleIds(source.getRoles().stream().map(RoleEntity::getIdRole).collect(Collectors.toSet()));
                    }
                    return destination;
                });

        modelMapper.addMappings(new PropertyMap<AppointmentEntity, AppointmentDto>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUser().getIdUser());
                map().setDoctorId(source.getDoctor().getIdDoctor());
                map().setAppointmentDate(source.getAppointmentDate());
                map().setConsultationReason(source.getConsultationReason());
                using(context -> context.getSource() != null ? context.getSource().toString() : null)
                        .map(source.getStatus(), destination.getStatus());
                map().setIdAppointment(source.getIdAppointment());
            }
        });

        return modelMapper;
    }
}