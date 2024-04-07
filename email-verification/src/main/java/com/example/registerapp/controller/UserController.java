package com.example.registerapp.controller;

import com.example.registerapp.dto.LoginDto;
import com.example.registerapp.dto.RegisterDto;
import com.example.registerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
    return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
  }

  @GetMapping("/verify-account")
  public ResponseEntity<String> verifyAccount(@RequestParam String email,
      @RequestParam String otp) {
    return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
  }
  @GetMapping("/regenerate-otp")
  public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
    return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
  }
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
    return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
  }
  
  @GetMapping("/forgot-password")
  public ResponseEntity<String> forgotpassword(@RequestParam String email){
	  return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);
  }
  
  @PostMapping("/set-password")
  public ResponseEntity<String> setPassword(@RequestParam String email, @RequestParam String newPassword){
  return new ResponseEntity<>(userService.setPassword(email,newPassword),HttpStatus.OK);
  }
}