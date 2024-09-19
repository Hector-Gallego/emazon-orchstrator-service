package com.resourceserver.emazonorchestratorservice.ports.driven.feign.mapper;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionOrchestrator;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.SupplyTransactionRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplyTransactionRequestDtoMapper {
    SupplyTransactionOrchestrator toDomain(SupplyTransactionRequestDto supplyTransactionRequestDto);
    SupplyTransactionRequestDto toDto(SupplyTransactionOrchestrator supplyTransactionOrchestrator);
}
