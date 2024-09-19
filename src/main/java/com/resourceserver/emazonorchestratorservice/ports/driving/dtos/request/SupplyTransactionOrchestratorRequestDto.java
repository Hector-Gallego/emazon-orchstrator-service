package com.resourceserver.emazonorchestratorservice.ports.driving.dtos.request;

import com.resourceserver.emazonorchestratorservice.domain.constants.ErrorMessageConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SupplyTransactionOrchestratorRequestDto {

    @NotBlank(message = ErrorMessageConstants.ARTICLE_ID_MESSAGE_ERROR)
    @NotNull(message = ErrorMessageConstants.ARTICLE_ID_MESSAGE_ERROR)
    private Long articleId;
    @NotBlank(message = ErrorMessageConstants.ARTICLE_QUANTITY_MESSAGE_ERROR)
    @NotNull(message = ErrorMessageConstants.ARTICLE_QUANTITY_MESSAGE_ERROR)
    private Integer quantity;
    @NotBlank(message = ErrorMessageConstants.ARTICLE_NAME_MESSAGE_ERROR)
    @NotNull(message = ErrorMessageConstants.ARTICLE_NAME_MESSAGE_ERROR)
    private String articleName;

}
