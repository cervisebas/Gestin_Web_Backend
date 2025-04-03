package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.EnrolmentExamRequest;
import com.isft194.gestin.dtos.request.ExamRequest;
import com.isft194.gestin.dtos.response.EnrolmentExamResponse;
import com.isft194.gestin.dtos.response.EnrolmentExamsResponse;
import com.isft194.gestin.models.EnrolmentExam;
import com.isft194.gestin.models.Exam;
import com.isft194.gestin.models.User;
import com.isft194.gestin.services.ExamService;
import com.isft194.gestin.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrolmentExamMapper implements IMapper<EnrolmentExam,EnrolmentExamRequest,EnrolmentExamResponse>
{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


        public EnrolmentExam toEnrolmentExam (EnrolmentExamRequest enrolmentExamRequest) throws Exception{
            User student = userService.findUserByMail(enrolmentExamRequest.getStudent().getMail());

            EnrolmentExam enrolmentExam = EnrolmentExam.builder()
                    .student(student)
                    .exams(new ArrayList<>())
                    .build();

            for (ExamRequest examRequest : enrolmentExamRequest.getExamRequest()) {
                Exam exam = examMapper.toExam(examRequest);
                enrolmentExam.getExams().add(exam);
            }

            return enrolmentExam;
        }

        public EnrolmentExamResponse toEnrolmentExamResponse (EnrolmentExam enrolmentExam){
            return EnrolmentExamResponse.builder()
                    .student(userMapper.toUserResponse(enrolmentExam.getStudent()))
                    .exams(enrolmentExam.getExams().stream()
                            .map(examMapper::toExamResponse)
                            .collect(Collectors.toList()))
                    .build();
        }

        public EnrolmentExamsResponse enrolmentExamsListToResponse (List < EnrolmentExam > enrolmentExams) {
            List<EnrolmentExamResponse> enrolmentExamResponseList = enrolmentExams.stream()
                    .map(this::toEnrolmentExamResponse)
                    .collect(Collectors.toList());
            return EnrolmentExamsResponse.builder()
                    .enrolmentExams(enrolmentExamResponseList)
                    .build();
        }

    @Override
    public EnrolmentExam fromRequestToObj(EnrolmentExamRequest request) throws Exception {
        User student = userService.findUserByMail(request.getStudent().getMail());

        EnrolmentExam enrolmentExam = EnrolmentExam.builder()
                .student(student)
                .exams(new ArrayList<>())
                .build();

        for (ExamRequest examRequest : request.getExamRequest()) {
            Exam exam = examMapper.toExam(examRequest);
            enrolmentExam.getExams().add(exam);
        }

        return enrolmentExam;
    }

    @Override
    public EnrolmentExamResponse fromObjToResponse(EnrolmentExam enrolmentExam) {
        return EnrolmentExamResponse.builder()
                .student(userMapper.toUserResponse(enrolmentExam.getStudent()))
                .exams(enrolmentExam.getExams().stream()
                        .map(examMapper::toExamResponse)
                        .collect(Collectors.toList()))
                .build();
    }
}

