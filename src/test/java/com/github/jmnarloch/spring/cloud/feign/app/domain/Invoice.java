package com.github.jmnarloch.spring.cloud.feign.app.domain;

import java.math.BigDecimal;

/**
 *
 */
public class Invoice {

    private String title;

    private BigDecimal amount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
