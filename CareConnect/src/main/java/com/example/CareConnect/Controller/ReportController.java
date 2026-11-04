package com.example.CareConnect.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class ReportController {
    @GetMapping ("/report")
    public String report(Model model){
        model.addAttribute("activePage", "report");
        return "report";
    }
    
}
