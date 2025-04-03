package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.AcademicRecordRequest;
import com.isft194.gestin.dtos.response.AcademicRecordResponse;
import com.isft194.gestin.dtos.response.SubjectResponse;
import com.isft194.gestin.dtos.response.UserNecessaryResponse;
import com.isft194.gestin.dtos.response.UserResponse;
import com.isft194.gestin.models.AcademicRecord;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicRecordMapper implements IMapper<AcademicRecord,AcademicRecordRequest,AcademicRecordResponse>
{

    @Autowired
    private ModelMapper modelMapper;

    //Metodo que toma un request
//    public AcademicRecord toAcademicRecord(AcademicRecordRequest academicRecordRequest){
//        //mapeo el registro academico
//        AcademicRecord academicRecord = modelMapper.map(academicRecordRequest, AcademicRecord.class);
//
//        academicRecord.setStudent(modelMapper.map(academicRecordRequest.getUserRequestId().getId(), User.class));
//
//        academicRecord.setSubject(modelMapper.map(academicRecordRequest.getSubjectRequestId().getId(), Subject.class));
//        return academicRecord;
//    }
//
//    //Metodo que devuelve un response
//    public AcademicRecordResponse toAcademicRecordResponse(AcademicRecord academicRecord){
//        //mapeo academicRecordResponse
//        AcademicRecordResponse academicRecordResponse = modelMapper.map(academicRecord, AcademicRecordResponse.class);
//
//        academicRecordResponse.setUserResponse(modelMapper.map(academicRecord.getStudent(), UserNecessaryResponse.class));
//
//        academicRecordResponse.setSubjectResponse(modelMapper.map(academicRecord.getSubject(), SubjectResponse.class));
//
//        return academicRecordResponse;
//    }

    @Override
    public AcademicRecord fromRequestToObj(AcademicRecordRequest request) throws Exception {
        //mapeo el registro academico
        AcademicRecord academicRecord = modelMapper.map(request, AcademicRecord.class);

        academicRecord.setStudent(modelMapper.map(request.getUserRequestId().getId(), User.class));

        academicRecord.setSubject(modelMapper.map(request.getSubjectRequestId().getId(), Subject.class));
        return academicRecord;
    }

    @Override
    public AcademicRecordResponse fromObjToResponse(AcademicRecord academicRecord) {
        //mapeo academicRecordResponse
        AcademicRecordResponse academicRecordResponse = modelMapper.map(academicRecord, AcademicRecordResponse.class);

        academicRecordResponse.setStudent(modelMapper.map(academicRecord.getStudent(), UserNecessaryResponse.class));

        academicRecordResponse.setSubject(modelMapper.map(academicRecord.getSubject(), SubjectResponse.class));

        return academicRecordResponse;
    }
}
