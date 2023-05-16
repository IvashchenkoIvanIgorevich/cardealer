package com.iivan.practice.controller.rest;

import com.iivan.practice.model.Manager;
import com.iivan.practice.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public List<Manager> getAllCars() {
        return managerService.getAllManagers();
    }

    @GetMapping("/{id}")
    public Manager getCarById(@PathVariable("id") Long id) {
        return managerService.getManagerById(id);
    }

    @PutMapping("/create")
    public Manager createCar(@RequestBody Manager manager) {
        return managerService.addNewManager(manager);
    }

    @PostMapping("/update")
    public Manager updateCar(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable("id") Long id) {
        managerService.deleteManagerById(id);
    }

}
