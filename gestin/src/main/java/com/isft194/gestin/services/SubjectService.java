package com.isft194.gestin.services;

import com.isft194.gestin.exceptions.CareerNotFoundException;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.repositories.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    public ISubjectRepository subjectRepository;
    
    @Autowired
    public CareerService careerService;

    public List<Subject> getByCareerId(Long id_career) throws CareerNotFoundException {
        Career career = careerService.getById(id_career);

        return subjectRepository.getListByCareer(career);
    }
}
