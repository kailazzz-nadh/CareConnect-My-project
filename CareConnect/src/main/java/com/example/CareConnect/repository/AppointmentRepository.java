package com.example.CareConnect.repository;
import com.example.CareConnect.entity.Appointment;
import com.example.CareConnect.entity.AppointmentStatus;
import com.example.CareConnect.entity.Doctor;
import com.example.CareConnect.entity.User;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
     List<Appointment> findByAppointmentDate(
            LocalDate appointmentDate);
    List<Appointment> findByPatientId(
            Long patientId);


   List<Appointment> findByPatientOrderByAppointmentDateAscAppointmentTimeAsc(
            User patient
    );

    Optional<Appointment> findByIdAndPatient(
            Long id,
            User patient
    );

    boolean existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatus(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime appointmentTime,
            AppointmentStatus status
    );
      
    
} 
