package com.blas.api.tipocambio.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "solicitud")
@ToString
public class Solicitud {

    @Id
    @Column(name = "id_solicitud")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;
    @Column(name = "monto_inicial")
    private Double montoInicial;
    @Column(name = "monto_final")
    private Double montoFinal;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_cambio")
    private Cambio cambio;

    public Solicitud(){
        this.usuario = new Usuario();
        this.cambio= new Cambio();
    }

}
