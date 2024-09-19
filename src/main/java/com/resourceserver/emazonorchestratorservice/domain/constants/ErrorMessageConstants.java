package com.resourceserver.emazonorchestratorservice.domain.constants;

public class ErrorMessageConstants {

    private ErrorMessageConstants() {
        throw new IllegalStateException();
    }
    public static final String STOCK_UPDATE_ERROR = "Failed to update stock on the stock server.";
    public static final String SUPPLY_ORCHESTRATION_ERROR = "Error occurred during supply orchestration process.";
    public static final String TRANSACTION_CREATION_ERROR = "Failed to create transaction in the transactions server.";

    public static final String INVALID_FIELDS = "One or more fields are invalid.";
    public static final String ARTICLE_NAME_MESSAGE_ERROR = "Article name cannot be empty or null.";
    public static final String ARTICLE_ID_MESSAGE_ERROR = "Article id cannot be empty or null.";
    public static final String ARTICLE_QUANTITY_MESSAGE_ERROR = "Article quantity cannot be empty or null.";

}
