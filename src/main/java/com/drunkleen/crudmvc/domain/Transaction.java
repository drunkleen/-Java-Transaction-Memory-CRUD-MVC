package com.drunkleen.crudmvc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "recipient_name", nullable = true)
    @NotNull(message = "Field is required")
    @Size(min = 1, message = "Field is required")
    private String recipientName;

    @Column(name = "product_name", nullable = false)
    @NotNull(message = "Field is required")
    @Size(min = 1, message = "Field is required")
    private String productName;

    @Column(name = "product_price", nullable = false)
    @NotNull(message = "Field is required")
    private Double productPrice;

    @Column(name = "product_quantity", nullable = false)
    @NotNull(message = "Field is required")
    private Integer quantity;

    public Transaction() {
    }

    public Transaction(String recipientName, String productName, Double productPrice, Integer quantity) {
        this.recipientName = recipientName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

}
