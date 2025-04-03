package com.isft194.gestin.repositories;

import com.isft194.gestin.models.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICareerRepository extends IRepository<Career, Long> {

    @Query("SELECT c FROM Career c JOIN c.subjects s WHERE s.id = :subjectId")
    Career findBySubjectId(@Param("subjectId") Long idSubject);

    @Query("SELECT c FROM Career c JOIN c.subjects s WHERE s.id = :subjectId AND c.name = :name")
    Career findBySubjectIdAndName(@Param("subjectId") Long idSubject, @Param("name") String name);
}
