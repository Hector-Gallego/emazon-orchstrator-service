package com.resourceserver.emazonorchestratorservice.domain.ports.feign;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionOrchestrator;

public interface SupplyTransactionOrchestrationFeignPort {

    void orchestrateSupplyTransaction(SupplyTransactionOrchestrator supplyTransactionOrchestrator);

}
