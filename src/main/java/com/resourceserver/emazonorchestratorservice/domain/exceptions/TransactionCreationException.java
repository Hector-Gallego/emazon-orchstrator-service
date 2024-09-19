package com.resourceserver.emazonorchestratorservice.domain.exceptions;

import com.resourceserver.emazonorchestratorservice.domain.constants.ErrorMessageConstants;

public class TransactionCreationException extends RuntimeException{

    public TransactionCreationException() {
        super(ErrorMessageConstants.TRANSACTION_CREATION_ERROR);
    }
}
