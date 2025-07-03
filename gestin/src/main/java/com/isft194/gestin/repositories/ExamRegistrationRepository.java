package com.isft194.gestin.repositories;

import java.util.List;

import com.isft194.gestin.models.ExamRegistration;
import com.isft194.gestin.models.User;

public interface ExamRegistrationRepository extends IRepository<ExamRegistration, Long> {
  List<ExamRegistration> getAllByStudent(User student);
}
