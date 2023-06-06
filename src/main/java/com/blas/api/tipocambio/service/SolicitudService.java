package com.blas.api.tipocambio.service;

import com.blas.api.tipocambio.models.Solicitud;

import java.security.Provider;
import java.util.List;

public interface SolicitudService {

    Solicitud guardar(Solicitud solicitud);

    List<Solicitud> listar();


}
