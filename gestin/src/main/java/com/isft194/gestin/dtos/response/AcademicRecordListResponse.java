package com.isft194.gestin.dtos.response;

import lombok.*;

import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
public class AcademicRecordListResponse {

    private List<AcademicRecordResponse> listAcademicRecordResponse;
}
