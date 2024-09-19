package com.resourceserver.emazonorchestratorservice.ports.driven.feign.interfaces;


import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.response.CustomApiResponse;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.StockRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock-service", url = "http://localhost:8080")
public interface StockMicroServiceFeignClient {

    @PutMapping("/api/article/stock")
    ResponseEntity<CustomApiResponse> updateArticleStock(@RequestBody StockRequestDto stockRequestDto);
}
