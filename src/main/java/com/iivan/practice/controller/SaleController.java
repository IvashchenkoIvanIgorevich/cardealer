package com.iivan.practice.controller;

import com.iivan.practice.model.Sale;
import com.iivan.practice.service.SaleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable("id") Long id) {
        return saleService.getSaleById(id);
    }

    @PutMapping("/new")
    public Sale createNewSale(@PathParam("carId") Long carId,
                              @PathParam("managerId") Long managerId,
                              @PathParam("quantity") int quantity) {
        return saleService.addSale(carId, managerId, quantity);
    }

    @PostMapping("/update/{id}")
    public Sale updateSale(@PathVariable("id") Long id,
                           @PathParam("carId") Long carId,
                           @PathParam("managerId") Long managerId,
                           @PathParam("quantity") int quantity) {
        return saleService.updateSale(id, carId, managerId, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteSaleById(@PathVariable("id") Long id) {
        saleService.deleteSaleById(id);
    }

}
