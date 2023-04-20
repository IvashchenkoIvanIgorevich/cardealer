package com.iivan.practice.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private int originalCarQuantity;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

    private int originalManagerSaleQuantity;

    private int quantity;

    public Sale(int originalCarQuantity, int originalManagerSaleQuantity, int quantity) {
        this.originalCarQuantity = originalCarQuantity;
        this.originalManagerSaleQuantity = originalManagerSaleQuantity;
        this.quantity = quantity;
    }
}
