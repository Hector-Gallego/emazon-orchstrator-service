package com.resourceserver.emazonorchestratorservice.ports.driving.constants;

public class OpenApiConstants {

    public static final String OPENAPI_TITTLE = "Orchestration microservice";
    public static final String OPENAPI_VERSION = "1.0";
    public static final String OPENAPI_DESCRIPTION = "Service for manager distributed transactions";

    private OpenApiConstants(){
        throw new IllegalStateException();
    }

    public static final String OPENAPI_ORCHESTRATION_SUPPLY_SUMMARY = "Begin orchestration supply transaction";
    public static final String OPENAPI_ORCHESTRATION_SUPPLY_DESCRIPTION = "Endpoint to orchestrate a new supply transaction, updating the stock of the specified article and registering the transaction.";
    public static final String ORCHESTRATION_SUPPLY_SUCCESS = "The supply has been successfully added, and the article stock has been updated.";
    public static final String OPENAPI_MEDIA_TYPE_JSON = "application/json";
    public static final String INVALID_INPUT = "The provided input is invalid. Please verify the article ID, quantity and article name.";
    public static final String OPENAPI_INTERNAL_SERVER_ERROR = "An unexpected error occurred. Please try again later or contact support.";
    public static final String OPENAPI_CODE_500 = "500";
    public static final String OPENAPI_CODE_400 = "400";
    public static final String OPENAPI_CODE_200 = "200";
}
