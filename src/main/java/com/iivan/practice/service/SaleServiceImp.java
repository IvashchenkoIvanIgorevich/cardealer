package com.iivan.practice.service;

import com.iivan.practice.model.Car;
import com.iivan.practice.model.Manager;
import com.iivan.practice.model.Sale;
import com.iivan.practice.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImp implements SaleService {

    private final CarService carService;
    private final ManagerService managerService;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImp(CarService carService, ManagerService managerService, SaleRepository saleRepository) {
        this.carService = carService;
        this.managerService = managerService;
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale getSaleById(Long id) {
        return getSaleIfExistById(id);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    @Transactional
    public Sale addSale(Long carId, Long managerId, int quantity) {
        Car car = carService.getCarById(carId);
        Manager manager = managerService.getManagerById(managerId);
        Sale sale = new Sale(car.getQuantity(), manager.getSales(), quantity);

        if (car.getQuantity() < quantity) {
            throw new IllegalStateException("Manager can't sell more cars than are available");
        } else {
            car.setQuantity(car.getQuantity() - quantity);
            manager.setSales(manager.getSales() + quantity);
        }

        sale.setCar(car);
        sale.setManager(manager);
        return saleRepository.save(sale);
    }

    @Override
    @Transactional
    public Sale updateSale(Long id, Long carId, Long managerId, int quantity) {
        Sale sale = getSaleIfExistById(id);
        Car car = sale.getCar();
        Manager manager = sale.getManager();

        if (carId != null) {
            Car newCar = carService.getCarById(carId);

            car.setQuantity(car.getQuantity() + sale.getQuantity());
            carService.updateCar(car);

            if (newCar.getQuantity() < quantity) {
                throw new IllegalStateException("Manager can't sell more cars than are available");
            }

            newCar.setQuantity(newCar.getQuantity() - quantity);
            carService.updateCar(newCar);
            sale.setCar(newCar);
        } else {
            throw new IllegalStateException("Car id can't be null");
        }

        if (managerId != null) {
            Manager newManager = managerService.getManagerById(managerId);

            manager.setSales(manager.getSales() - sale.getQuantity());
            managerService.updateManager(manager);

            newManager.setSales(newManager.getSales() + quantity);
            managerService.updateManager(newManager);
            sale.setManager(newManager);
        } else {
            throw new IllegalStateException("Manager id can't be null");
        }

        sale.setQuantity(quantity);
        return saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void deleteSaleById(Long id) {
        Sale sale = getSaleIfExistById(id);
        Car car = sale.getCar();
        Manager manager = sale.getManager();

        car.setQuantity(car.getQuantity() + sale.getQuantity());
        manager.setSales(manager.getSales() - sale.getQuantity());

        carService.updateCar(car);
        managerService.updateManager(manager);
        saleRepository.deleteById(id);
    }

    private Sale getSaleIfExistById(Long id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isEmpty()) {
            throw new IllegalStateException("Sale with id " + id + " does not exists");
        }
        return saleOptional.get();
    }
}
