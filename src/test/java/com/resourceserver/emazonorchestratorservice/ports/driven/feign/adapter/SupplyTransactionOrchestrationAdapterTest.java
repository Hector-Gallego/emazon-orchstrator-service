package com.resourceserver.emazonorchestratorservice.ports.driven.feign.adapter;

import com.resourceserver.emazonorchestratorservice.domain.constants.ErrorMessageConstants;
import com.resourceserver.emazonorchestratorservice.domain.exceptions.SupplyOrchestrationException;
import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.StockRequestDto;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.SupplyTransactionRequestDto;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.interfaces.StockMicroServiceFeignClient;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.interfaces.TransactionsMicroServiceFeignClient;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.mapper.StockRequestDtoMapper;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.mapper.SupplyTransactionRequestDtoMapper;
import com.resourceserver.emazonorchestratorservice.testdata.DataTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplyTransactionOrchestrationAdapterTest {


    @Mock
    private TransactionsMicroServiceFeignClient transactionsFeignClient;

    @Mock
    private StockMicroServiceFeignClient stockMicroServiceFeignClient;

    @Mock
    private StockRequestDtoMapper stockRequestDtoMapper;

    @Mock
    private SupplyTransactionRequestDtoMapper supplyTransactionRequestDtoMapper;

    @InjectMocks
    private SupplyTransactionOrchestrationAdapter supplyTransactionOrchestrationAdapter;



    @Test
    void shouldOrchestrateSupplyTransactionSuccessfully() {

        SupplyTransactionDetails supplyTransactionDetails = DataTestFactory.createSupplyTransactionDetails();
        StockRequestDto stockRequestDto = DataTestFactory.createStockRequestDto();
        SupplyTransactionRequestDto supplyTransactionRequestDto = DataTestFactory.createSupplyTransactionRequestDto();

        when(stockRequestDtoMapper.toDto(supplyTransactionDetails)).thenReturn(stockRequestDto);
        when(supplyTransactionRequestDtoMapper.toDto(supplyTransactionDetails)).thenReturn(supplyTransactionRequestDto);
        when(stockMicroServiceFeignClient.updateArticleStock(stockRequestDto)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(transactionsFeignClient.registerSupplyTransaction(supplyTransactionRequestDto)).thenReturn(new ResponseEntity<>(HttpStatus.OK));


        supplyTransactionOrchestrationAdapter.orchestrateSupplyTransaction(supplyTransactionDetails);

        verify(stockMicroServiceFeignClient, times(1)).updateArticleStock(stockRequestDto);
        verify(transactionsFeignClient, times(1)).registerSupplyTransaction(supplyTransactionRequestDto);
    }

    @Test
    void shouldThrowStockUpdateExceptionWhenStockUpdateFails() {

        SupplyTransactionDetails supplyTransactionDetails = DataTestFactory.createSupplyTransactionDetails();
        StockRequestDto stockRequestDto = DataTestFactory.createStockRequestDto();
        SupplyTransactionRequestDto supplyTransactionRequestDto = DataTestFactory.createSupplyTransactionRequestDto();

        when(stockRequestDtoMapper.toDto(supplyTransactionDetails)).thenReturn(stockRequestDto);
        when(stockMicroServiceFeignClient.updateArticleStock(stockRequestDto)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));


        SupplyOrchestrationException exception = assertThrows(SupplyOrchestrationException.class, () ->
                supplyTransactionOrchestrationAdapter.orchestrateSupplyTransaction(supplyTransactionDetails)
        );

        assertEquals(ErrorMessageConstants.SUPPLY_ORCHESTRATION_ERROR, exception.getMessage());

        verify(stockMicroServiceFeignClient, times(1)).updateArticleStock(stockRequestDto);
        verify(transactionsFeignClient, never()).registerSupplyTransaction(any());
    }

    @Test
    void shouldCompensateTransactionWhenTransactionFails() {

        SupplyTransactionDetails supplyTransactionDetails = DataTestFactory.createSupplyTransactionDetails();
        StockRequestDto stockRequestDto = DataTestFactory.createStockRequestDto();
        SupplyTransactionRequestDto supplyTransactionRequestDto = DataTestFactory.createSupplyTransactionRequestDto();

        when(stockRequestDtoMapper.toDto(supplyTransactionDetails)).thenReturn(stockRequestDto);
        when(supplyTransactionRequestDtoMapper.toDto(supplyTransactionDetails)).thenReturn(supplyTransactionRequestDto);
        when(stockMicroServiceFeignClient.updateArticleStock(stockRequestDto)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(transactionsFeignClient.registerSupplyTransaction(supplyTransactionRequestDto)).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        SupplyOrchestrationException exception = assertThrows(SupplyOrchestrationException.class, () ->
                supplyTransactionOrchestrationAdapter.orchestrateSupplyTransaction(supplyTransactionDetails)
        );
        assertEquals(ErrorMessageConstants.SUPPLY_ORCHESTRATION_ERROR, exception.getMessage());


        verify(stockMicroServiceFeignClient, times(1)).updateArticleStock(stockRequestDto);
        verify(transactionsFeignClient, times(1)).registerSupplyTransaction(supplyTransactionRequestDto);

        stockRequestDto.setQuantity(-stockRequestDto.getQuantity());
        verify(stockMicroServiceFeignClient, times(1)).updateArticleStock(stockRequestDto);
    }
}