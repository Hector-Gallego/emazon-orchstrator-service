package com.resourceserver.emazonorchestratorservice.ports.driven.feign.mapper;

import com.resourceserver.emazonorchestratorservice.domain.model.SupplyTransactionDetails;
import com.resourceserver.emazonorchestratorservice.ports.driven.feign.dtos.StockRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockRequestDtoMapper {

    SupplyTransactionDetails toDomain(StockRequestDto stockRequestDto);
    StockRequestDto toDto(SupplyTransactionDetails supplyTransactionDetails);
}
