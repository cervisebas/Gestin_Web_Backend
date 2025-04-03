package com.isft194.gestin.dtos.response;

import lombok.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class SubjectsResponse {
    private List<SubjectResponse> subjects;
}
