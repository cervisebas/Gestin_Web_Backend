package com.isft194.gestin.repositories;

import com.isft194.gestin.models.EnrolmentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnrolmentExamRepository extends IRepository<EnrolmentExam, Long>{
}
