package com.sofka.Crudjavascript.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "nombre" , nullable = false)
    private String nombre;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "prioridad" , nullable = false)
    private Integer prioridad;
    public UserModel(String nombre, String email, Integer prioridad) {
        this.nombre = nombre;
        this.email = email;
        this.prioridad = prioridad;
    }
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }



    public UserModel() {

    }

    public void setEmail(String email) {
        this.email = email;
    }
}
