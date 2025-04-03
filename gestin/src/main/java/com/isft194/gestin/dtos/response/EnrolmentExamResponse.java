package com.isft194.gestin.dtos.response;
import lombok.*;

import java.util.List;

@Data
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EnrolmentExamResponse {

    private Long id;
    private UserResponse student;
    private List<ExamResponse> exams;


}
