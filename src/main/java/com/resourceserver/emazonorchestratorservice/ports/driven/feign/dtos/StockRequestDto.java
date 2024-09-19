package com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockRequestDto {

    private Long articleId;
    private Integer quantity;
}
