package com.iivan.practice.service;

import com.iivan.practice.model.Manager;

import java.util.List;

public interface ManagerService {

    List<Manager> getAllManagers();

    Manager getManagerById(Long id);

    Manager addNewManager(Manager car);

    Manager updateManager(Manager manager);

    void deleteManagerById(Long id);
}
