package com.blas.api.tipocambio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @Column(name = "id_solicitud")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;
    private Double monto;
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
