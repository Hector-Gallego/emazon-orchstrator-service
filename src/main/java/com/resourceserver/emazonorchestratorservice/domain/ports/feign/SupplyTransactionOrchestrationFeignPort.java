package com.resourceserver.emazonorchestratorservice.domain.ports.feign;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;

public interface SupplyTransactionOrchestrationFeignPort {

    void orchestrateSupplyTransaction(SupplyTransactionDetails supplyTransactionDetails);

}
