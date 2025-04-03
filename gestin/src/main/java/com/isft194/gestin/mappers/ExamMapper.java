package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.ExamRequest;
import com.isft194.gestin.dtos.response.ExamResponse;
import com.isft194.gestin.dtos.response.SubjectResponse;
import com.isft194.gestin.dtos.response.UserResponse;
import com.isft194.gestin.models.AcademicRecord;
import com.isft194.gestin.models.Exam;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamMapper implements IMapper<Exam,ExamRequest,ExamResponse>
{
    @Autowired
    private ModelMapper modelMapper;

    public Exam toExam(ExamRequest request) {
        Exam exam = modelMapper.map(request, Exam.class);

        exam.setTeacher(modelMapper.map(request.getTeacher(), User.class));
        exam.setFirstVocal(modelMapper.map(request.getFirstVocal(), User.class));
        exam.setSecondVocal(modelMapper.map(request.getSecondVocal(), User.class));
        exam.setThirdVocal(modelMapper.map(request.getThirdVocal(), User.class));
        exam.setSubject(modelMapper.map(request.getSubject(), Subject.class));

        return exam;
    }

    public ExamResponse toExamResponse(Exam exam) {
        ExamResponse response = modelMapper.map(exam, ExamResponse.class);


        response.setTeacher(modelMapper.map(exam.getTeacher(), UserResponse.class));
        response.setFirstVocal(modelMapper.map(exam.getFirstVocal(), UserResponse.class));
        response.setSecondVocal(modelMapper.map(exam.getSecondVocal(), UserResponse.class));
        response.setThirdVocal(modelMapper.map(exam.getThirdVocal(), UserResponse.class));
        response.setSubject(modelMapper.map(exam.getSubject(), SubjectResponse.class));

        return response;
    }
    public Exam examResponseToExam (ExamResponse examResponse) {
        Exam exam = modelMapper.map(examResponse, Exam.class);
        exam.setTeacher(modelMapper.map(examResponse, User.class));
        exam.setFirstVocal(modelMapper.map(examResponse, User.class));
        exam.setSecondVocal(modelMapper.map(examResponse, User.class));
        exam.setThirdVocal(modelMapper.map(examResponse, User.class));
        exam.setSubject(modelMapper.map(examResponse, Subject.class));

        return exam;
    }

    @Override
    public Exam fromRequestToObj(ExamRequest request) throws Exception {
        Exam exam = modelMapper.map(request, Exam.class);

        exam.setTeacher(modelMapper.map(request.getTeacher(), User.class));
        exam.setFirstVocal(modelMapper.map(request.getFirstVocal(), User.class));
        exam.setSecondVocal(modelMapper.map(request.getSecondVocal(), User.class));
        exam.setThirdVocal(modelMapper.map(request.getThirdVocal(), User.class));
        exam.setSubject(modelMapper.map(request.getSubject(), Subject.class));

        return exam;
    }

    @Override
    public ExamResponse fromObjToResponse(Exam exam) {
        ExamResponse response = modelMapper.map(exam, ExamResponse.class);


        response.setTeacher(modelMapper.map(exam.getTeacher(), UserResponse.class));
        response.setFirstVocal(modelMapper.map(exam.getFirstVocal(), UserResponse.class));
        response.setSecondVocal(modelMapper.map(exam.getSecondVocal(), UserResponse.class));
        response.setThirdVocal(modelMapper.map(exam.getThirdVocal(), UserResponse.class));
        response.setSubject(modelMapper.map(exam.getSubject(), SubjectResponse.class));

        return response;
    }
}
