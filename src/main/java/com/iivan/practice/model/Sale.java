package com.iivan.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    private Long id;

    private Car car;

    private Manager manager;

    private int quantity;
}
