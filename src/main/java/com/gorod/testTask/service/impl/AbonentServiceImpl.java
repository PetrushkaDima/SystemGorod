package com.gorod.testTask.service.impl;

import com.gorod.testTask.dto.AbonentDTO;
import com.gorod.testTask.entity.Abonent;
import com.gorod.testTask.repository.AbonentRepository;
import com.gorod.testTask.repository.ServiceRepository;
import com.gorod.testTask.service.AbonentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbonentServiceImpl implements AbonentService {

    @Autowired
    private AbonentRepository abonentRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<AbonentDTO> getAbonents(boolean sorted, String filter, Pageable pageable) {
        /**
         * Если filter пустой, то по нему поиск производится не будет
         */
        boolean isFiltered = !filter.trim().isEmpty();
        Page<Abonent> all;
        if (sorted) {
            all = abonentRepository.findAbonentsByFilteredAccountAndSorted(pageable, isFiltered, filter);
        } else {
            all = abonentRepository.findAbonentsByFilteredAccount(pageable, isFiltered, filter);
        }
        List<AbonentDTO> list = new ArrayList<>();
        for (Abonent abonent : all) {
            list.add(new AbonentDTO(abonent, false, serviceRepository));
        }
        return list;
    }

    @Override
    public List<AbonentDTO> getAbonentsByIdService(Long idService, boolean children, Pageable pageable) {
        Page<Abonent> all = abonentRepository.findByIdService_Id(idService, pageable);
        List<AbonentDTO> list = new ArrayList<>();
        for (Abonent abonent : all) {
            list.add(new AbonentDTO(abonent, children, serviceRepository));
        }
        return list;
    }

    @Override
    public Abonent createAbonent(String fio, Long account, Long idService) {
        Abonent abonent = new Abonent();
        abonent.setFio(fio);
        abonent.setAccount(account);
        Optional<com.gorod.testTask.entity.Service> serviceOptional = serviceRepository.findById(idService);
        abonent.setIdService(serviceOptional.get());
        Abonent abonent1 = abonentRepository.save(abonent);
        return abonent1;
    }
}
