package com.iivan.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    private Long id;

    private String firstName;

    private String lastName;

    private String passportId;

    private int experience;

    private int sales;
}
