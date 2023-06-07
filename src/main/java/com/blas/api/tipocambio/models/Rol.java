package com.blas.api.tipocambio.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "rol")
@Data
@ToString
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    private String descripcion;

    public Rol(){

    }

}
