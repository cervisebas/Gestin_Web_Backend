package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.SubjectRequest;
import com.isft194.gestin.dtos.request.UserRequest;
import com.isft194.gestin.dtos.response.SubjectResponse;
import com.isft194.gestin.dtos.response.SubjectsResponse;
import com.isft194.gestin.dtos.response.UserNecessaryResponse;
import com.isft194.gestin.dtos.response.UserResponse;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;
import com.isft194.gestin.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectMapper implements IMapper<Subject,SubjectRequest,SubjectResponse>
{

    @Autowired
    private ModelMapper modelMapper;

    //@Autowired
    //private UserMapper userMapper;

    public Subject toSubject(SubjectRequest subjectRequest){
        //Mapeo de subject
        Subject subject = modelMapper.map(subjectRequest, Subject.class);

        //ahora mapeo de teacher de tipo user, dentro de subject
        subject.setTeacher(modelMapper.map(subjectRequest.getTeacher(), User.class));

        return subject;
    }

    public SubjectResponse toSubjectResponse(Subject subject) {
        //mapeo subjectResponse
        SubjectResponse subjectResponse = modelMapper.map(subject, SubjectResponse.class);

        //mapeo de teacherResponse de tipo UserResponse, dentro de SubjectResponse
        subjectResponse.setTeacher(modelMapper.map(subject, UserNecessaryResponse.class));

        return subjectResponse;
    }

    public Subject subjectResponseToSubject(SubjectResponse subjectResponse){
        Subject subject = modelMapper.map(subjectResponse, Subject.class);
        subject.setTeacher(modelMapper.map(subjectResponse, User.class));
        return subject;
    }

    @Override
    public Subject fromRequestToObj(SubjectRequest request) throws Exception {
        //Mapeo de subject
        Subject subject = modelMapper.map(request, Subject.class);

        //ahora mapeo de teacher de tipo user, dentro de subject
        subject.setTeacher(modelMapper.map(request.getTeacher(), User.class));

        return subject;
    }

    @Override
    public SubjectResponse fromObjToResponse(Subject subject) {
        //mapeo subjectResponse
        SubjectResponse subjectResponse = modelMapper.map(subject, SubjectResponse.class);

        //mapeo de teacherResponse de tipo UserResponse, dentro de SubjectResponse
        subjectResponse.setTeacher(modelMapper.map(subject, UserNecessaryResponse.class));

        return subjectResponse;
    }
}
