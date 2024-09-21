package com.resourceserver.emazonorchestratorservice.domain.model;



public class SupplyTransactionDetails {

    private Long articleId;
    private String articleName;
    private Integer quantity;

    public SupplyTransactionDetails() {
    }

    public SupplyTransactionDetails(Long articleId, String articleName, Integer quantity) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.quantity = quantity;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
