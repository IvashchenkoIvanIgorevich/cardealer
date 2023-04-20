package com.iivan.practice.service;

import com.iivan.practice.model.Car;
import com.iivan.practice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new IllegalArgumentException("Car with id " + id + "doesn't exist");
        }

        return optionalCar.get();
    }

    @Override
    public Car addNewCar(Car car) {
        Optional<Car> carOptional = carRepository.findCarByModel(car.getModel());
        if (carOptional.isPresent()) {
            throw new IllegalStateException("Model exists");
        }
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        if (car.getId() != null && carRepository.existsById(car.getId())) {
            return carRepository.save(car);
        } else {
            throw new IllegalStateException("Car with id " + car.getId() + " does not exists");
        }
    }

    @Override
    public void deleteCarById(Long id) {
        boolean exists = carRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Car with id " + id + " does not exists");
        }
        carRepository.deleteById(id);
    }
}
