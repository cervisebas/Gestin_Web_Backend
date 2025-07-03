package com.isft194.gestin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isft194.gestin.models.RegistrationPeriod;
import com.isft194.gestin.repositories.RegistrationPeriodRepository;

@Service
public class RegistrationPeriodService {
  @Autowired
  private RegistrationPeriodRepository registrationPeriodRepository;


  public List<RegistrationPeriod> getPeriods() {
    return registrationPeriodRepository.findAll();
  }
}
