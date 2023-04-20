package com.iivan.practice.service;

import com.iivan.practice.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long id);

    Car addNewCar(Car car);

    Car updateCar(Car car);

    void deleteCarById(Long id);
}
