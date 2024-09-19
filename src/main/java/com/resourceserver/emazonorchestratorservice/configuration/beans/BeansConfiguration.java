package com.resourceserver.emazonorchestratorservice.configuration.beans;

import com.resourceserver.emazonorchestratorservice.domain.ports.api.SupplyTransactionOrchestrationApiPort;
import com.resourceserver.emazonorchestratorservice.domain.ports.feign.SupplyTransactionOrchestrationFeignPort;
import com.resourceserver.emazonorchestratorservice.domain.usecases.OrchestrateSupplyTransactionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    SupplyTransactionOrchestrationApiPort supplyTransactionOrchestrationApiPort(SupplyTransactionOrchestrationFeignPort supplyTransactionOrchestrationFeignPort){
        return new OrchestrateSupplyTransactionUseCase(supplyTransactionOrchestrationFeignPort);
    }

}
