package com.resourceserver.emazonorchestratorservice.domain.usecases;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionOrchestrator;
import com.resourceserver.emazonorchestratorservice.domain.ports.api.SupplyTransactionOrchestrationApiPort;
import com.resourceserver.emazonorchestratorservice.domain.ports.feign.SupplyTransactionOrchestrationFeignPort;

public class OrchestrateSupplyTransactionUseCase implements SupplyTransactionOrchestrationApiPort {

    private final SupplyTransactionOrchestrationFeignPort supplyTransactionOrchestrationFeignPort;

    public OrchestrateSupplyTransactionUseCase(SupplyTransactionOrchestrationFeignPort supplyTransactionOrchestrationFeignPort) {
        this.supplyTransactionOrchestrationFeignPort = supplyTransactionOrchestrationFeignPort;
    }

    @Override
    public void orchestrateSupplyTransaction(SupplyTransactionOrchestrator supplyTransactionOrchestrator) {
        supplyTransactionOrchestrationFeignPort.orchestrateSupplyTransaction(supplyTransactionOrchestrator);
    }
}
