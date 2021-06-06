package com.gorod.testTask.entity;

import javax.persistence.*;

@Table(name = "service")
@Entity
public class Service {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "id_parent", referencedColumnName = "id")
    private Service idParent;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service getIdParent() {
        return idParent;
    }

    public void setIdParent(Service idParent) {
        this.idParent = idParent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}