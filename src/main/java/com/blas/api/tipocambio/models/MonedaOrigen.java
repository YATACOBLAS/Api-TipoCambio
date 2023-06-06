package com.blas.api.tipocambio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "moneda_origen")
@NoArgsConstructor

public class MonedaOrigen {
    @Id
    @Column(name = "id_moneda_origen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMonedaOrigen;

    private String descripcion;

}
