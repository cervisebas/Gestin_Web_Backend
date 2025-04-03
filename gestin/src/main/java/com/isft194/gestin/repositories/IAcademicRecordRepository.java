package com.isft194.gestin.repositories;

import com.isft194.gestin.models.AcademicRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAcademicRecordRepository extends IRepository<AcademicRecord, Long> {

    @Query("SELECT a FROM AcademicRecord a JOIN a.student st WHERE st.id = :studentId")
    List<AcademicRecord> findAllByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT a FROM AcademicRecord a JOIN a.subject su WHERE su.id = :subjectId")
    List<AcademicRecord> findAllBySubjectId(@Param("subjectId") Long subejctId);

    @Query("SELECT a FROM AcademicRecord a JOIN a.subject su JOIN su.teacher t WHERE su.id = :subjectId AND t.id = :teacherId")
    List<AcademicRecord> findAllBySubjectIdAndTeacherId(@Param("subjectId") Long subjectId, @Param("teacherId") Long teacherId);
}
