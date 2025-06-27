package com.isft194.gestin.services;

import com.isft194.gestin.models.ExamRegistration;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.ExamRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRegistrationRepository examRegistrationRepository;

    public List<ExamRegistration> getRegisteredExams(User user) {
        List<ExamRegistration> examRegistrations = examRegistrationRepository.getAllByStudent(user);

        return examRegistrations;
    }
}
