package com.resourceserver.emazonorchestratorservice.ports.driving.mapper;


import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionOrchestrator;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.request.SupplyTransactionOrchestratorRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplyTransactionOrchestratorMapper {

    SupplyTransactionOrchestrator toDomain(SupplyTransactionOrchestratorRequestDto SupplyTransactionOrchestratorRequestDto);
    SupplyTransactionOrchestratorRequestDto toDto(SupplyTransactionOrchestrator supplyTransactionOrchestrator);
}
