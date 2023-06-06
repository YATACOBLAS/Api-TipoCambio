package com.blas.api.tipocambio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cambio")
public class Cambio {
    @Id
    @Column(name = "id_cambio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCambio;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_moneda_destino")
    private MonedaDestino monedaDestino;

    @ManyToOne
    @JoinColumn(name = "id_moneda_origen ")
    private  MonedaOrigen monedaOrigen;

    public Cambio(){
        this.monedaOrigen = new MonedaOrigen();
        this.monedaDestino= new MonedaDestino();
    }


}
