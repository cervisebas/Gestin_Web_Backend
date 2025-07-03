package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.AcademicRecordRequest;
import com.isft194.gestin.dtos.response.AcademicRecordResponse;
import com.isft194.gestin.interfaces.IMapper;
import com.isft194.gestin.models.AcademicRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicRecordMapper implements IMapper<AcademicRecord, AcademicRecordRequest, AcademicRecordResponse> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AcademicRecord fromRequestToModel(AcademicRecordRequest request) {
        return modelMapper.map(request, AcademicRecord.class);
    }

    @Override
    public AcademicRecordResponse fromModelToResponse(AcademicRecord academicRecord) {
        return modelMapper.map(academicRecord, AcademicRecordResponse.class);
    }
}
