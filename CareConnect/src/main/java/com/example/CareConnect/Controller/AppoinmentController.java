package com.example.CareConnect.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller 
public class AppoinmentController {
    @GetMapping ("/appoinment")
    public String appoinment(Model model){
          model.addAttribute("activePage", "appointment");
        return "appoinment";
    }

    
}
