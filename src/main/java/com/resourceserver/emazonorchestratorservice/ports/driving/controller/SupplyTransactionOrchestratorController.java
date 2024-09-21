package com.resourceserver.emazonorchestratorservice.ports.driving.controller;

import com.resourceserver.emazonorchestratorservice.configuration.exceptionhandler.CustomErrorResponse;
import com.resourceserver.emazonorchestratorservice.domain.constants.SuccessMessagesConstants;
import com.resourceserver.emazonorchestratorservice.domain.ports.api.SupplyTransactionOrchestrationApiPort;
import com.resourceserver.emazonorchestratorservice.ports.driving.constants.OpenApiConstants;
import com.resourceserver.emazonorchestratorservice.ports.driving.mapper.SupplyTransactionOrchestratorMapper;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.response.CustomApiResponse;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.request.SupplyTransactionOrchestratorRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/orchestrator")
public class SupplyTransactionOrchestratorController {



    private final SupplyTransactionOrchestrationApiPort orchestrateSupplyPort;
    private final SupplyTransactionOrchestratorMapper supplyTransactionOrchestratorMapper;

    public SupplyTransactionOrchestratorController(SupplyTransactionOrchestrationApiPort orchestrateSupplyPort, SupplyTransactionOrchestratorMapper supplyTransactionOrchestratorMapper) {
        this.orchestrateSupplyPort = orchestrateSupplyPort;
        this.supplyTransactionOrchestratorMapper = supplyTransactionOrchestratorMapper;
    }


    @Operation(summary = OpenApiConstants.OPENAPI_ORCHESTRATION_SUPPLY_SUMMARY,
            description = OpenApiConstants.OPENAPI_ORCHESTRATION_SUPPLY_DESCRIPTION)

    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_200,
                    description = OpenApiConstants.ORCHESTRATION_SUPPLY_SUCCESS,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_400,
                    description = OpenApiConstants.INVALID_INPUT,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_500,
                    description = OpenApiConstants.OPENAPI_INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @PostMapping("/supply")
    public ResponseEntity<CustomApiResponse> orchestrateSupply(@RequestBody SupplyTransactionOrchestratorRequestDto SupplyTransactionOrchestratorRequestDto) {
        orchestrateSupplyPort.orchestrateSupplyTransaction(supplyTransactionOrchestratorMapper.toDomain(SupplyTransactionOrchestratorRequestDto));

        CustomApiResponse response = new CustomApiResponse(
                HttpStatus.OK.value(),
                SuccessMessagesConstants.ADD_STOCK_AND_SUPPLY_TRANSACTION_SUCCESS,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

}

