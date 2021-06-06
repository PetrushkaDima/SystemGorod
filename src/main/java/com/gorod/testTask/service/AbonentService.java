package com.gorod.testTask.service;

import com.gorod.testTask.dto.AbonentDTO;
import com.gorod.testTask.entity.Abonent;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbonentService {
    List<AbonentDTO> getAbonents(boolean sorted, String filter, Pageable pageable);

    List<AbonentDTO> getAbonentsByIdService(Long idService, boolean children, Pageable pageable);

    Abonent createAbonent(String fio, Long account, Long idService);
}
