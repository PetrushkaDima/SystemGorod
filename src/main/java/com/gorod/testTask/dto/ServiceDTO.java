package com.gorod.testTask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gorod.testTask.entity.Service;
import com.gorod.testTask.repository.ServiceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
@JsonInclude(Include.NON_NULL)
public class ServiceDTO {

    private Long id;
    private String name;
    private List<ServiceDTO> childs = new ArrayList<>();
    @JsonIgnore
    private boolean children;
    @JsonIgnore
    private ServiceRepository serviceRepository;

    public ServiceDTO(Service idService, boolean children, ServiceRepository serviceRepository) {
        this.id = idService.getId();
        this.name = idService.getName();
        this.children = children;
        this.serviceRepository = serviceRepository;
    }

    public void setChilds(Service childs) {
        /**
         * Если children == true, тогда находим у данного сервиса всех его детей и добавляем их в массив
         */
        if (children) {
            List<Service> list = serviceRepository.findByIdParent_Id(childs.getId());
            if (list.size() > 0) {
                for (Service service : list) {
                    ServiceDTO serviceDTO = new ServiceDTO(service, children, serviceRepository);
                    serviceDTO.setChilds(service);
                    this.childs.add(serviceDTO);
                }
                /**
                 * Если детей нет, тогда зануляем массив, чтобы он не отображался в Json
                 */
            } else {
                this.childs = null;
            }
            /**
             * Если children == false, тогда зануляем массив, чтобы он не отображался в Json
             */
        } else {
            this.childs = null;
        }
    }

    public List<ServiceDTO> getChilds() {
        return childs;
    }

    public ServiceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }
}
