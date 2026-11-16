package com.example.CareConnect.service;
import com.example.CareConnect.dto.DoctorReport;
import com.example.CareConnect.entity.Doctor;
import com.example.CareConnect.repository.DoctorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service 
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
    }
    public List<Doctor> getAllDoctors(){
         return doctorRepository.findAll();
    }
    public Optional<Doctor> getDoctorById(Long id){
        return doctorRepository.findById(id);
    }
    public Doctor saveDoctor(Doctor doctor){
         return doctorRepository.save(doctor);
    }
    public void deleteDoctor(Long id) {

        doctorRepository.deleteById(id);
    }
    public List<DoctorReport> getPopularDoctors() {

    return doctorRepository
            .getPopularDoctors();
}
public List<Doctor> getActiveDoctors() {

    return doctorRepository
            .findByIsActiveTrue();
}
public Optional<Doctor> getActiveDoctorById(
        Long id) {

    return doctorRepository
            .findByIdAndIsActiveTrue(id);
}

    
}
