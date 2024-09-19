package com.resourceserver.emazonorchestratorservice.ports.driving.controller;

import com.resourceserver.emazonorchestratorservice.domain.ports.api.SupplyTransactionOrchestrationApiPort;
import com.resourceserver.emazonorchestratorservice.ports.driving.mapper.SupplyTransactionOrchestratorMapper;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.response.CustomApiResponse;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.request.SupplyTransactionOrchestratorRequestDto;
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


    @PostMapping("/supply")
    public ResponseEntity<CustomApiResponse> orchestrateSupply(@RequestBody SupplyTransactionOrchestratorRequestDto SupplyTransactionOrchestratorRequestDto) {
        orchestrateSupplyPort.orchestrateSupplyTransaction(supplyTransactionOrchestratorMapper.toDomain(SupplyTransactionOrchestratorRequestDto));
        return ResponseEntity.ok(new CustomApiResponse(HttpStatus.OK.value(),
                "Suministro procesado con éxito", LocalDateTime.now()));
    }
}

