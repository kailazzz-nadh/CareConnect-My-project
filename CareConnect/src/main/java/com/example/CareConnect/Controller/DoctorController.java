
package com.example.CareConnect.Controller;

import com.example.CareConnect.entity.Doctor;
import com.example.CareConnect.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // DISPLAY ALL DOCTORS
    @GetMapping
    public String doctor(Model model) {

        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );

        return "doctor";
    }

    // OPEN ADD DOCTOR FORM
    @GetMapping("/add")
    public String addDoctorForm(Model model) {

        model.addAttribute("doctor", new Doctor());

        return "form";
    }

    // SAVE NEW DOCTOR
    @PostMapping("/add")
    public String addDoctor(
            @Valid @ModelAttribute("doctor") Doctor doctor,
            BindingResult result) {

        if (result.hasErrors()) {
            return "form";
        }

        doctorService.saveDoctor(doctor);

        return "redirect:/doctor";
    }

    // OPEN EDIT DOCTOR FORM
    @GetMapping("/edit/{id}")
    public String editDoctorForm(
            @PathVariable Long id,
            Model model) {

        Doctor doctor = doctorService
                .getDoctorById(id)
                .orElseThrow();

        model.addAttribute("doctor", doctor);

        return "form";
    }

    // UPDATE EXISTING DOCTOR
    @PostMapping("/edit/{id}")
    public String editDoctor(
            @PathVariable Long id,
            @Valid @ModelAttribute("doctor") Doctor doctor,
            BindingResult result) {

        // CHECK VALIDATION ERRORS
        if (result.hasErrors()) {
            return "form";
        }

        // FIND EXISTING DOCTOR
        Doctor existingDoctor = doctorService
                .getDoctorById(id)
                .orElseThrow();

        // UPDATE DOCTOR DETAILS
        existingDoctor.setName(doctor.getName());

        existingDoctor.setSpecialization(
                doctor.getSpecialization()
        );

        existingDoctor.setQualification(
                doctor.getQualification()
        );

        existingDoctor.setExperience(
                doctor.getExperience()
        );

        existingDoctor.setPhoneNumber(
                doctor.getPhoneNumber()
        );

        existingDoctor.setEmail(
                doctor.getEmail()
        );

        existingDoctor.setConsultationFee(
                doctor.getConsultationFee()
        );

        existingDoctor.setIsActive(
                doctor.getIsActive()
        );

        // SAVE UPDATED DOCTOR
        doctorService.saveDoctor(existingDoctor);

        return "redirect:/doctor";
    }
    @GetMapping("/delete/{id}")
public String deleteDoctor(
        @PathVariable Long id) {

    doctorService.deleteDoctor(id);

    return "redirect:/doctor";
}

}
