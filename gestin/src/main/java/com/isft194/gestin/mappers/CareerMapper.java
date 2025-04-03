package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.CareerRequest;
import com.isft194.gestin.dtos.request.SubjectRequest;
import com.isft194.gestin.dtos.response.CareerResponse;
import com.isft194.gestin.dtos.response.SubjectResponse;
import com.isft194.gestin.dtos.response.SubjectsResponse;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CareerMapper implements IMapper<Career,CareerRequest,CareerResponse>
{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    public Career toCareer(CareerRequest careerRequest){
        // Map basic attributes from CareerRequest to Career
        Career career = modelMapper.map(careerRequest, Career.class);

        // Initialize the subjects list as an empty list
        career.setSubjects(new ArrayList<>());

        // Iterate over the list of SubjectRequest and map each one to Subject
        for (SubjectRequest subjectRequest : careerRequest.getSubjectsRequest()){
            Subject subject = subjectMapper.toSubject(subjectRequest);
            career.getSubjects().add(subject);
        }
        return career;
    }

    public CareerResponse toCareerResponse(Career career){

        // Map basic attributes from Career to CareerResponse
        CareerResponse careerResponse = modelMapper.map(career, CareerResponse.class);

        // Initialize SubjectsResponse to hold the list of SubjectResponse
        SubjectsResponse subjectsResponse = new SubjectsResponse(new ArrayList<>());

        // Iterate over the list of Subject and map each one to SubjectResponse
        for(Subject subject : career.getSubjects()){
            SubjectResponse subjectResponse = subjectMapper.toSubjectResponse(subject);
            subjectsResponse.getSubjects().add(subjectResponse);
        }
        careerResponse.setSubjectsResponse(subjectsResponse);
        return careerResponse;
    }

    @Override
    public Career fromRequestToObj(CareerRequest request) throws Exception {
        // Map basic attributes from CareerRequest to Career
        Career career = modelMapper.map(request, Career.class);

        // Initialize the subjects list as an empty list
        career.setSubjects(new ArrayList<>());

        // Iterate over the list of SubjectRequest and map each one to Subject
        for (SubjectRequest subjectRequest : request.getSubjectsRequest()){
            Subject subject = subjectMapper.toSubject(subjectRequest);
            career.getSubjects().add(subject);
        }
        return career;
    }

    @Override
    public CareerResponse fromObjToResponse(Career career) {
        // Map basic attributes from Career to CareerResponse
        CareerResponse careerResponse = modelMapper.map(career, CareerResponse.class);

        // Initialize SubjectsResponse to hold the list of SubjectResponse
        SubjectsResponse subjectsResponse = new SubjectsResponse(new ArrayList<>());

        // Iterate over the list of Subject and map each one to SubjectResponse
        for(Subject subject : career.getSubjects()){
            SubjectResponse subjectResponse = subjectMapper.toSubjectResponse(subject);
            subjectsResponse.getSubjects().add(subjectResponse);
        }
        careerResponse.setSubjectsResponse(subjectsResponse);
        return careerResponse;
    }
}
