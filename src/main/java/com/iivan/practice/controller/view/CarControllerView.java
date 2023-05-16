package com.iivan.practice.controller.view;

import com.iivan.practice.model.Car;
import com.iivan.practice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarControllerView {

    private final CarService carService;

    @Autowired
    public CarControllerView(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public String getAllCars(Model model) {
        model.addAttribute("allCar", carService.getAllCars());
        return "index";
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute("car") Car car) {
        carService.addNewCar(car);
        return "redirect:/car/all";
    }

}
