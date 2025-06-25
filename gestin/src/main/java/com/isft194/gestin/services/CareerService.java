package com.isft194.gestin.services;

import com.isft194.gestin.exceptions.CareerNotFound;
import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.SubjectRegistrations;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.ICareerRepository;
import com.isft194.gestin.repositories.ISubjectRegistrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CareerService {

    @Autowired
    private ICareerRepository careerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ISubjectRegistrations subjectRegistrationsRespository;

    public List<Career> getAllByUser() throws NotAuthenticatedException {
        User currentUser = userService.getCurrent();
        List<SubjectRegistrations> subjects = subjectRegistrationsRespository.findAllByUser(currentUser);

        List<Career> careers = new ArrayList<Career>();

        for (SubjectRegistrations subject : subjects) {
            Career career = subject.getSubject().getCarrer();

            if (!careers.contains(career)) {
                careers.add(career);
            }
        }

        return careers;
    }

    public Career getById(Long id) throws CareerNotFound {
        Optional<Career> career = careerRepository.findById(id);

        if (career.isEmpty()) {
            throw new CareerNotFound("Carrera no encontrada.");
        }

        return career.get();
    }
}

