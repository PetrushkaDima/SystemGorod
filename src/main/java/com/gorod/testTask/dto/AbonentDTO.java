package com.gorod.testTask.dto;

import com.gorod.testTask.entity.Abonent;
import com.gorod.testTask.repository.ServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class AbonentDTO {
    private Long id;
    private String fio;
    private Long account;
    private ServiceDTO service;

    public AbonentDTO(Abonent abonent, boolean children, ServiceRepository serviceRepository) {
        this.id = abonent.getId();
        this.fio = abonent.getFio();
        this.account = abonent.getAccount();
        ServiceDTO service = new ServiceDTO(abonent.getIdService(), children, serviceRepository);
        service.setChilds(abonent.getIdService());
        this.service = service;
    }

    public AbonentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }
}
