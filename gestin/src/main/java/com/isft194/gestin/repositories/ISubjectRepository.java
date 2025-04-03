package com.isft194.gestin.repositories;

import com.isft194.gestin.models.Subject;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ISubjectRepository extends IRepository<Subject, Long>{

    List<Subject> findAllSubjectByTeacherId(Long teacherId);
    List<Subject> findAllSubjectByTeacherIdAndName(Long teacherId, String subjectName);

}
