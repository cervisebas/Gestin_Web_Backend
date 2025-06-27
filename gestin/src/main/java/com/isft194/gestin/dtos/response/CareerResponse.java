package com.isft194.gestin.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CareerResponse {
    private String name;
    private String turn;
}
