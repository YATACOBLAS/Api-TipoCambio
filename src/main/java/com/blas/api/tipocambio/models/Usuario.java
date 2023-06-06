package com.blas.api.tipocambio.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id_usuario;
    private String nombre;
    private String email;
    private String password;

}
