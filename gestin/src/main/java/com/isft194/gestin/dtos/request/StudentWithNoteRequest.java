package com.isft194.gestin.dtos.request;
import lombok.*;

import java.util.List;

@Data
public class StudentWithNoteRequest {
    private Long idTeacher;
    private String nameCareer;
    private String nameSubject;
    List<StudentsRequest> studentsRequestList;
}
