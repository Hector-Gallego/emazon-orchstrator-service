package com.resourceserver.emazonorchestratorservice.domain.usecases;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;
import com.resourceserver.emazonorchestratorservice.domain.ports.feign.SupplyTransactionOrchestrationFeignPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrchestrateSupplyTransactionUseCaseTest {

    @Mock
    private SupplyTransactionOrchestrationFeignPort supplyTransactionOrchestrationFeignPort;



    @InjectMocks
    private OrchestrateSupplyTransactionUseCase orchestrateSupplyTransactionUseCase;


    @Test
    void shouldOrchestrateSupplyTransaction() {

        SupplyTransactionDetails supplyTransactionDetails = new SupplyTransactionDetails(
                1L, "Camisa corta", 10
        );

        orchestrateSupplyTransactionUseCase.orchestrateSupplyTransaction(supplyTransactionDetails);
        verify(supplyTransactionOrchestrationFeignPort, times(1)).orchestrateSupplyTransaction(supplyTransactionDetails);
    }

}