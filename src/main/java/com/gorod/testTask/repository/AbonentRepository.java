package com.gorod.testTask.repository;

import com.gorod.testTask.entity.Abonent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonentRepository extends CrudRepository<Abonent, Long> {
    @Query("SELECT a FROM Abonent a " +
            "WHERE :isFiltered = FALSE " +
            "OR a.account LIKE CONCAT('%', :filter, '%') " +
            "ORDER BY a.account")
    Page<Abonent> findAbonentsByFilteredAccountAndSorted(Pageable pageable, boolean isFiltered, String filter);

    @Query("SELECT a FROM Abonent a " +
            "WHERE :isFiltered = FALSE " +
            "OR a.account LIKE CONCAT('%', :filter, '%')")
    Page<Abonent> findAbonentsByFilteredAccount(Pageable pageable, boolean isFiltered, String filter);

    Page<Abonent> findByIdService_Id(Long idService, Pageable pageable);

    List<Abonent> findAbonentsByIdService_Id(Long idService);

}
