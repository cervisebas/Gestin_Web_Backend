package com.isft194.gestin.dtos.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InscriptionPeriodResponse {

    private Long id;
    private Date startPeriod;
    private Date endPeriod;
    private ExamsResponse examsResponse;
}
