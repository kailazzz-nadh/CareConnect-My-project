package com.example.CareConnect.service;
import com.example.CareConnect.entity.Role;
import com.example.CareConnect.entity.User;
import com.example.CareConnect.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
@Service 

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
     public List<User> getAllPatients() {
     return userRepository.findByRole( Role.PATIENT);
    }
      public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow();
    }
  public long getTotalUsers() {
    return userRepository.countByRole(Role.PATIENT);
}
    
}
