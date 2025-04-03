package com.isft194.gestin.dtos.response;
import lombok.*;
import java.util.List;

@Data
@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EnrolmentExamsResponse {

    private List<EnrolmentExamResponse> enrolmentExams;
}
