package com.isft194.gestin.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.isft194.gestin.models.SubjectRegistrations;
import com.isft194.gestin.models.User;

@Repository
public interface ISubjectRegistrations extends IRepository<SubjectRegistrations, Long> {
  List<SubjectRegistrations> findAllByUser(User user);
}
