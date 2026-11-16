package com.example.CareConnect.Controller;

import com.example.CareConnect.entity.Appointment;
import com.example.CareConnect.repository.DoctorRepository;
import com.example.CareConnect.service.AppointmentService;
import com.example.CareConnect.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final AppointmentService appointmentService;
    private final DoctorRepository doctorRepository;

    public HomeController(
            UserService userService,
            AppointmentService appointmentService,
            DoctorRepository doctorRepository) {

        this.userService = userService;
        this.appointmentService = appointmentService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/")
    public String home(Model model) {

        LocalDate today = LocalDate.now();

        // Today's appointments
        List<Appointment> todayAppointments =
                appointmentService.getAppointmentsByDate(today);

        model.addAttribute("todayAppointments",
                todayAppointments.size());

        model.addAttribute("appointments",
                todayAppointments);

        // Total patients (excluding Admin)
        model.addAttribute("totalUsers",
                userService.getTotalUsers());

        // Total doctors
        model.addAttribute("totalDoctors",
                doctorRepository.count());

        // This month's bookings
        long monthlyBookings =
                appointmentService.getAllAppointments()
                    .stream()
                    .filter(a ->
                        a.getAppointmentDate() != null
                        && a.getAppointmentDate().getMonth()
                            == today.getMonth()
                        && a.getAppointmentDate().getYear()
                            == today.getYear()
                    )
                    .count();

        model.addAttribute("monthlyBookings", monthlyBookings);

        model.addAttribute("activePage", "dashboard");

        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
