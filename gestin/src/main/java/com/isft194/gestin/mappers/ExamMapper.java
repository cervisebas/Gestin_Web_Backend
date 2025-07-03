package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.ExamRequest;
import com.isft194.gestin.dtos.response.ExamResponse;
import com.isft194.gestin.interfaces.IMapper;
import com.isft194.gestin.models.Exam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamMapper implements IMapper<Exam, ExamRequest, ExamResponse> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Exam fromRequestToModel(ExamRequest request) {
        return modelMapper.map(request, Exam.class);
    }

    @Override
    public ExamResponse fromModelToResponse(Exam exam) {
        return modelMapper.map(exam, ExamResponse.class);
    }
}
