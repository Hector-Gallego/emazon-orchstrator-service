package com.resourceserver.emazonorchestratorservice.ports.driven.feign.interfaces;


import com.resourceserver.emazonorchestratorservice.ports.driven.feign.constants.MicroServicesDataConstants;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.SupplyTransactionRequestDto;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.response.CustomApiResponse;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.request.SupplyTransactionOrchestratorRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = MicroServicesDataConstants.TRANSACTIONS_MICROSERVICE_NAME,
        url = MicroServicesDataConstants.TRANSACTIONS_MICROSERVICE_URL)
public interface TransactionsMicroServiceFeignClient {

    @PostMapping("/api/supply")
    ResponseEntity<CustomApiResponse> registerSupplyTransaction(@RequestBody SupplyTransactionRequestDto supplyTransactionRequestDto);

}
