package com.resourceserver.emazonorchestratorservice.domain.exceptions;

import com.resourceserver.emazonorchestratorservice.domain.constants.ErrorMessageConstants;

public class StockUpdateException extends RuntimeException{

    public StockUpdateException(){
        super(ErrorMessageConstants.STOCK_UPDATE_ERROR);
    }
}
