package com.isft194.gestin.repositories;

import com.isft194.gestin.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamRepository extends IRepository<Exam, Long> {

}
