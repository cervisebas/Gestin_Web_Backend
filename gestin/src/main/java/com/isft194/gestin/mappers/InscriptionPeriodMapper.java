package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.ExamRequest;
import com.isft194.gestin.dtos.request.InscriptionPeriodRequest;
import com.isft194.gestin.dtos.response.ExamResponse;
import com.isft194.gestin.dtos.response.ExamsResponse;
import com.isft194.gestin.dtos.response.InscriptionPeriodResponse;
import com.isft194.gestin.models.Exam;
import com.isft194.gestin.models.InscriptionPeriod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InscriptionPeriodMapper implements IMapper<InscriptionPeriod,InscriptionPeriodRequest,InscriptionPeriodResponse>
{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamMapper examMapper;

    public InscriptionPeriod toInscriptionPeriod(InscriptionPeriodRequest inscriptionPeriodRequestrequest) {
        InscriptionPeriod inscriptionPeriod = InscriptionPeriod.builder()
                .startPeriod(inscriptionPeriodRequestrequest.getStartPeriod())
                .endPeriod(inscriptionPeriodRequestrequest.getEndPeriod())
                .build();

        inscriptionPeriod.setExams(new ArrayList<>());

        for (ExamRequest examRequest : inscriptionPeriodRequestrequest.getExamsRequest()) {
            Exam exam = examMapper.toExam(examRequest);
            inscriptionPeriod.getExams().add(exam);
        }
        return inscriptionPeriod;
    }

    public InscriptionPeriodResponse toInscriptionPeriodResponse(InscriptionPeriod inscriptionPeriod) {
        InscriptionPeriodResponse inscriptionPeriodResponse = modelMapper.map(inscriptionPeriod, InscriptionPeriodResponse.class);

        ExamsResponse examsResponse = new ExamsResponse( new ArrayList<>());

        for (Exam exam : inscriptionPeriod.getExams()) {
            ExamResponse examResponse = examMapper.toExamResponse(exam);
            examsResponse.getExams().add(examResponse);
        }

        inscriptionPeriodResponse.setExamsResponse(examsResponse);

        return inscriptionPeriodResponse;
    }

    @Override
    public InscriptionPeriod fromRequestToObj(InscriptionPeriodRequest request) throws Exception {
        InscriptionPeriod inscriptionPeriod = InscriptionPeriod.builder()
                .startPeriod(request.getStartPeriod())
                .endPeriod(request.getEndPeriod())
                .build();

        inscriptionPeriod.setExams(new ArrayList<>());

        for (ExamRequest examRequest : request.getExamsRequest()) {
            Exam exam = examMapper.toExam(examRequest);
            inscriptionPeriod.getExams().add(exam);
        }
        return inscriptionPeriod;
    }

    @Override
    public InscriptionPeriodResponse fromObjToResponse(InscriptionPeriod inscriptionPeriod) {
        InscriptionPeriodResponse inscriptionPeriodResponse = modelMapper.map(inscriptionPeriod, InscriptionPeriodResponse.class);

        ExamsResponse examsResponse = new ExamsResponse( new ArrayList<>());

        for (Exam exam : inscriptionPeriod.getExams()) {
            ExamResponse examResponse = examMapper.toExamResponse(exam);
            examsResponse.getExams().add(examResponse);
        }

        inscriptionPeriodResponse.setExamsResponse(examsResponse);

        return inscriptionPeriodResponse;
    }
}
