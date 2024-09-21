package com.resourceserver.emazonorchestratorservice.domain.ports.api;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;

public interface SupplyTransactionOrchestrationApiPort {

    void orchestrateSupplyTransaction(SupplyTransactionDetails supplyTransactionDetails);
}
