package com.blas.api.tipocambio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "moneda_destino")
@NoArgsConstructor

public class MonedaDestino {
    @Id
    @Column(name = "id_moneda_destino")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMonedaDestino;

    private String descripcion;
}
