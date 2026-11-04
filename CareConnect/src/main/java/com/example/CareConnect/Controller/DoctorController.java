package com.example.CareConnect.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class DoctorController {
    @GetMapping ("/doctor")
    public String doctor(Model model){
        model.addAttribute("activePage", "doctors");
        return "doctor";
    }
    
}
