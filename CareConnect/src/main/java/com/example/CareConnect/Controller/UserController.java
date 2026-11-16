package com.example.CareConnect.Controller;
import com.example.CareConnect.entity.User;
import com.example.CareConnect.service.AppointmentService;
import com.example.CareConnect.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller 
@RequestMapping("/user")
public class UserController {
   private final UserService userService;
   private final AppointmentService appointmentService;

   public  UserController(UserService userService,AppointmentService appointmentService){
    this.userService=userService;
    this.appointmentService=appointmentService;
   }
    @GetMapping
    public String listUsers(Model model) {

        model.addAttribute("users",userService.getAllPatients());
        return "user";
    }
    @GetMapping("/{id}")
    public String viewUser(@PathVariable Long id,Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
         model.addAttribute("appointments",appointmentService.getAppointmentsByPatient(id)
        );
        return "view";
    }
}






    

