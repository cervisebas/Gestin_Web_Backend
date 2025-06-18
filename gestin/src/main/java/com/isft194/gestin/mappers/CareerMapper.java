package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.CareerRequest;
import com.isft194.gestin.dtos.response.CareerResponse;
import com.isft194.gestin.interfaces.IArrayMapper;
import com.isft194.gestin.interfaces.IMapper;
import com.isft194.gestin.models.Career;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerMapper
    implements
        IMapper<Career, CareerRequest, CareerResponse>,
        IArrayMapper<Career, CareerRequest, CareerResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Career fromRequestToModel(CareerRequest request) {
        return modelMapper.map(request, Career.class);
    }

    @Override
    public CareerResponse fromModelToResponse(Career model) {
        return modelMapper.map(model, CareerResponse.class);
    }

    @Override
    public List<Career> fromRequestListToModelList(List<CareerRequest> request) {
        return modelMapper.map(request, new TypeToken<List<Career>>() {}.getType());
    }

    @Override
    public List<CareerResponse> fromModelListToResponseList(List<Career> model) {
        return modelMapper.map(model, new TypeToken<List<Career>>() {}.getType());
    }
}
