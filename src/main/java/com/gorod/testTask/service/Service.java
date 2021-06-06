package com.gorod.testTask.service;

import com.gorod.testTask.dto.ServiceDTO;
import org.springframework.http.ResponseEntity;

public interface Service {
    ServiceDTO getHierarhy();

    ResponseEntity<String> deleteService(Long id, boolean force);
}
