package com.gorod.testTask.repository;

import com.gorod.testTask.entity.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
    List<Service> findByIdParent_Id(Long id);
}
