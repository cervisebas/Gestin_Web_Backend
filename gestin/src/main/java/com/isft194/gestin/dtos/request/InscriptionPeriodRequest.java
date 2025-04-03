package com.isft194.gestin.dtos.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InscriptionPeriodRequest {

    private Long id;
    private Date startPeriod;
    private Date endPeriod;
    private List<ExamRequest> examsRequest;
}
