package com.resourceserver.emazonorchestratorservice.domain.ports.api;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionOrchestrator;

public interface SupplyTransactionOrchestrationApiPort {

    void orchestrateSupplyTransaction(SupplyTransactionOrchestrator supplyTransactionOrchestrator);
}
