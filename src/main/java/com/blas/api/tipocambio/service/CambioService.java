package com.blas.api.tipocambio.service;

import com.blas.api.tipocambio.models.Cambio;
import java.util.Optional;

public interface CambioService {

   Cambio guardarCambio(String Origen,String Destino,double valor);
    Cambio guardar(Cambio cambio);

    Optional<Cambio> buscar(Long idMonedaOrigen,Long idMonedaDestino);

}
