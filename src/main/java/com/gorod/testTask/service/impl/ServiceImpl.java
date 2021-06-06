package com.gorod.testTask.service.impl;

import com.gorod.testTask.dto.ServiceDTO;
import com.gorod.testTask.repository.AbonentRepository;
import com.gorod.testTask.repository.ServiceRepository;
import com.gorod.testTask.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AbonentRepository abonentRepository;

    @Override
    public ServiceDTO getHierarhy() {
        Optional<com.gorod.testTask.entity.Service> byId = serviceRepository.findById(1L);
        com.gorod.testTask.entity.Service idService = byId.get();
        ServiceDTO serviceDTO = new ServiceDTO(idService, true, serviceRepository);
        serviceDTO.setChilds(idService);
        return serviceDTO;
    }

    @Override
    public ResponseEntity<String> deleteService(Long id, boolean force) {
        boolean exists = serviceRepository.existsById(id);
        if (exists) {
            if (force) {
                serviceRepository.deleteById(id);
                return ResponseEntity.ok().body("Удалено");
            } else {
                if (serviceRepository.findByIdParent_Id(id).size() > 0 || abonentRepository.findAbonentsByIdService_Id(id).size() > 0) {
                    return ResponseEntity.badRequest().body("Нарушение ограничений");
                } else {
                    serviceRepository.deleteById(id);
                    return ResponseEntity.ok().body("Удалено");
                }
            }
        } else {
            return ResponseEntity.badRequest().body(String.format("Услуги с id = %s, не существует", id));
        }
    }
}
