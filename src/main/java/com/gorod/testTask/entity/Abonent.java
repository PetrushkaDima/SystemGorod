package com.gorod.testTask.entity;

import javax.persistence.*;

@Entity
@Table(name = "abonent",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_service", "account"}))
public class Abonent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "account")
    private Long account;

    @OneToOne
    @JoinColumn(name = "id_service", referencedColumnName = "id")
    private Service idService;

    public Abonent() {
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

    public Service getIdService() {
        return idService;
    }

    public void setIdService(Service idService) {
        this.idService = idService;
    }
}
