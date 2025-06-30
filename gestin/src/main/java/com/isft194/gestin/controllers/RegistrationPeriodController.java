package com.isft194.gestin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.mappers.RegistrationPeriodMapper;
import com.isft194.gestin.models.RegistrationPeriod;
import com.isft194.gestin.services.RegistrationPeriodService;
import com.isft194.gestin.services.UserService;

@RestController
@RequestMapping(path = "/registration_periods")
public class RegistrationPeriodController {
  @Autowired
  private RegistrationPeriodService registrationPeriodService;

  @Autowired
  private UserService userService;

  @Autowired
  private RegistrationPeriodMapper registrationPeriodMapper;

  @GetMapping("")
  public ResponseEntity<?> getPeriods() {
    try {
      userService.getCurrent();

      List<RegistrationPeriod> registrationPeriods = registrationPeriodService.getPeriods();

      return ResponseEntity
        .ok(
          registrationPeriodMapper.fromModelListToResponseList(registrationPeriods)
        );

    } catch (NotAuthenticatedException e) {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(e.getMessage());
    }
  }

}
