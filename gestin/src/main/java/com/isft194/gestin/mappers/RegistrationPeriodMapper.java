package com.isft194.gestin.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isft194.gestin.dtos.request.RegistrationPeriodRequest;
import com.isft194.gestin.dtos.response.RegistrationPeriodResponse;
import com.isft194.gestin.interfaces.IArrayMapper;
import com.isft194.gestin.models.RegistrationPeriod;

@Service
public class RegistrationPeriodMapper implements IArrayMapper<RegistrationPeriod, RegistrationPeriodRequest, RegistrationPeriodResponse> {
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<RegistrationPeriod> fromRequestListToModelList(List<RegistrationPeriodRequest> request) {
    return modelMapper.map(request, new TypeToken<List<RegistrationPeriod>>() {}.getType());
  }

  @Override
  public List<RegistrationPeriodResponse> fromModelListToResponseList(List<RegistrationPeriod> model) {
    return modelMapper.map(model, new TypeToken<List<RegistrationPeriodResponse>>() {}.getType());
  }

}
