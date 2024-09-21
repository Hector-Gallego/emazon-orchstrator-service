package com.resourceserver.emazonorchestratorservice.testdata;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.StockRequestDto;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.SupplyTransactionRequestDto;

public class DataTestFactory {

    private DataTestFactory(){
        throw new AssertionError();
    }

    public static SupplyTransactionDetails createSupplyTransactionDetails() {
        return new SupplyTransactionDetails(1L, "camisa", 10);
    }

    public static StockRequestDto createStockRequestDto() {
        return new StockRequestDto(1L, 10);
    }

    public static SupplyTransactionRequestDto createSupplyTransactionRequestDto() {
        return new SupplyTransactionRequestDto(1L, 10, "camisa");
    }
}
