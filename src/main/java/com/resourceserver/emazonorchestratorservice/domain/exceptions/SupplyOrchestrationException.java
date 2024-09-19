package com.resourceserver.emazonorchestratorservice.domain.exceptions;

import com.resourceserver.emazonorchestratorservice.domain.constants.ErrorMessageConstants;

public class SupplyOrchestrationException extends RuntimeException{

    public SupplyOrchestrationException(){
        super(ErrorMessageConstants.SUPPLY_ORCHESTRATION_ERROR);
    }
}
