package com.resourceserver.emazonorchestratorservice.ports.driving.mapper;


import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;
import com.resourceserver.emazonorchestratorservice.ports.driving.dtos.request.SupplyTransactionOrchestratorRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplyTransactionOrchestratorMapper {

    SupplyTransactionDetails toDomain(SupplyTransactionOrchestratorRequestDto SupplyTransactionOrchestratorRequestDto);
    SupplyTransactionOrchestratorRequestDto toDto(SupplyTransactionDetails supplyTransactionDetails);
}
