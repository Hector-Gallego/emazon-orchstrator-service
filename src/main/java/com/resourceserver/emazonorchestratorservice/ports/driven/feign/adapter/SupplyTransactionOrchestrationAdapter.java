package com.resourceserver.emazonorchestratorservice.ports.driven.feign.adapter;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;
import com.resourceserver.emazonorchestratorservice.domain.ports.feign.SupplyTransactionOrchestrationFeignPort;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.SupplyTransactionRequestDto;
import com.resourceserver.emazonorchestratorservice.domain.exceptions.StockUpdateException;
import com.resourceserver.emazonorchestratorservice.domain.exceptions.SupplyOrchestrationException;
import com.resourceserver.emazonorchestratorservice.domain.exceptions.TransactionCreationException;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.interfaces.StockMicroServiceFeignClient;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.interfaces.TransactionsMicroServiceFeignClient;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.mapper.StockRequestDtoMapper;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.mapper.SupplyTransactionRequestDtoMapper;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.response.CustomApiResponse;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.StockRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * The SupplyTransactionOrchestrationAdapter class orchestrates the supply transactions by coordinating
 * the stock update and the transaction registration through microservices.
 * It communicates with two microservices:
 * - Emazon Stock MicroService: to handle the stock updates.
 * - Emazon Transactions MicroService: to register the supply transaction.
 * In case of a failure during the transaction registration, the stock update is rolled back
 * to ensure data consistency.
 * Custom exceptions are used to handle errors during the orchestration process.
 *
 */
@Service
public class SupplyTransactionOrchestrationAdapter implements SupplyTransactionOrchestrationFeignPort {

    private final TransactionsMicroServiceFeignClient transactionsFeignClient;
    private final StockMicroServiceFeignClient stockMicroServiceFeignClient;
    private final StockRequestDtoMapper stockRequestDtoMapper;
    private final SupplyTransactionRequestDtoMapper supplyTransactionRequestDtoMapper;

    public SupplyTransactionOrchestrationAdapter(TransactionsMicroServiceFeignClient transactionsFeignClient,
                                                 StockMicroServiceFeignClient stockMicroServiceFeignClient,
                                                 StockRequestDtoMapper stockRequestDtoMapper,
                                                 SupplyTransactionRequestDtoMapper supplyTransactionRequestDtoMapper) {
        this.transactionsFeignClient = transactionsFeignClient;
        this.stockMicroServiceFeignClient = stockMicroServiceFeignClient;
        this.stockRequestDtoMapper = stockRequestDtoMapper;
        this.supplyTransactionRequestDtoMapper = supplyTransactionRequestDtoMapper;
    }

    @Override
    public void orchestrateSupplyTransaction(SupplyTransactionDetails supplyTransactionDetails) {


        SupplyTransactionRequestDto supplyTransactionRequestDto =
                supplyTransactionRequestDtoMapper.toDto(supplyTransactionDetails);

        StockRequestDto stockRequestDto =
                stockRequestDtoMapper.toDto(supplyTransactionDetails);

        boolean stockUpdated = false;

        try {

            ResponseEntity<CustomApiResponse> stockResponse = stockMicroServiceFeignClient.updateArticleStock(stockRequestDto);
            if (!stockResponse.getStatusCode().is2xxSuccessful()) {
                throw new StockUpdateException();
            }

            stockUpdated = true;

            ResponseEntity<CustomApiResponse> transactionResponse = transactionsFeignClient.registerSupplyTransaction(supplyTransactionRequestDto);
            if (!transactionResponse.getStatusCode().is2xxSuccessful()) {
                throw new TransactionCreationException();
            }


        } catch (TransactionCreationException e) {
            if (stockUpdated) {
                compensateSupplyTransaction(stockRequestDto);
            }
            throw new SupplyOrchestrationException();

        } catch (StockUpdateException e) {
            throw new SupplyOrchestrationException();
        }
    }


    private void compensateSupplyTransaction(StockRequestDto stockRequestDto) {
        StockRequestDto compensationStockRequest = new StockRequestDto(stockRequestDto.getArticleId(), -stockRequestDto.getQuantity());
        stockMicroServiceFeignClient.updateArticleStock(compensationStockRequest);
    }

}
