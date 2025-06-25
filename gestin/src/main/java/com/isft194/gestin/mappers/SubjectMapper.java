package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.SubjectRequest;
import com.isft194.gestin.dtos.response.SubjectResponse;
import com.isft194.gestin.interfaces.IArrayMapper;
import com.isft194.gestin.interfaces.IMapper;
import com.isft194.gestin.models.Subject;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectMapper
    implements
        IMapper<Subject, SubjectRequest, SubjectResponse>,
        IArrayMapper<Subject, SubjectRequest, SubjectResponse> {
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Subject fromRequestToModel(SubjectRequest request) {
        return modelMapper.map(request, Subject.class);
    }

    @Override
    public SubjectResponse fromModelToResponse(Subject subject) {
        return modelMapper.map(subject, SubjectResponse.class);
    }


    @Override
    public List<Subject> fromRequestListToModelList(List<SubjectRequest> request) {
        return modelMapper.map(request, new TypeToken<List<Subject>>() {}.getType());
    }

    @Override
    public List<SubjectResponse> fromModelListToResponseList(List<Subject> model) {
        return modelMapper.map(model, new TypeToken<List<Subject>>() {}.getType());
    }
}
