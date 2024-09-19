package com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SupplyTransactionRequestDto {

    private Long articleId;
    private Integer quantity;
    private String articleName;

}
