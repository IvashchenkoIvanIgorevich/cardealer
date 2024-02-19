package com.iivan.practice.service;

import com.iivan.practice.model.Manager;
import com.iivan.practice.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImp implements ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerServiceImp(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager getManagerById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manager with id " + id + "doesn't exist"));
    }

    @Override
    public Manager addNewManager(Manager manager) {
        Optional<Manager> carOptional = managerRepository.getManagerByPassportId(manager.getPassportId());
        if (carOptional.isPresent()) {
            throw new IllegalStateException("Manager with passport id " + manager.getPassportId() + " exists");
        }
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(Manager manager) {
        if (manager.getId() != null && managerRepository.existsById(manager.getId())) {
            return managerRepository.save(manager);
        } else {
            throw new IllegalStateException("Manager with id " + manager.getId() + " does not exists");
        }
    }

    @Override
    public void deleteManagerById(Long id) {
        boolean exists = managerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Manager with id " + id + " does not exists");
        }
        managerRepository.deleteById(id);
    }
}
