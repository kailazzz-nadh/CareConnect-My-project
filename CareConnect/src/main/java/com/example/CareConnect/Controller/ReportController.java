package com.example.CareConnect.Controller;
import com.example.CareConnect.dto.DoctorReport;
import com.example.CareConnect.service.DoctorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    private final DoctorService doctorService;

    public ReportController(DoctorService doctorService) {

        this.doctorService = doctorService;
    }
    
   @GetMapping
public String popularDoctors(Model model) {

    List<DoctorReport> doctors = doctorService.getPopularDoctors();

    model.addAttribute("doctors", doctors);

    long totalBookings = doctors.stream()
            .mapToLong(doctor -> doctor.getTotalBookings())
            .sum();

    model.addAttribute("totalBookings", totalBookings);

    model.addAttribute("totalDoctors", doctors.size());

    model.addAttribute("topDoctor",
            doctors.isEmpty() ? null : doctors.get(0));

    model.addAttribute("activePage", "report");

    return "report";
}
   
    
}
