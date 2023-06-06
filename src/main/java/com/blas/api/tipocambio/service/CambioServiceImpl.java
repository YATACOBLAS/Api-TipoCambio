package com.blas.api.tipocambio.service;

import com.blas.api.tipocambio.models.Cambio;
import com.blas.api.tipocambio.models.MonedaDestino;
import com.blas.api.tipocambio.models.MonedaOrigen;
import com.blas.api.tipocambio.repository.CambioRepository;
import com.blas.api.tipocambio.repository.MonedaDestinoRepository;
import com.blas.api.tipocambio.repository.MonedaOrigenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CambioServiceImpl implements CambioService{

    @Autowired
    CambioRepository repository;
    @Autowired
    MonedaDestinoRepository mdRepository;
    @Autowired
    MonedaOrigenRepository moRepository;

    @Override
    public Cambio guardarCambio(String Origen, String Destino, double valor) {
        Optional<Cambio> optional=repository.guardarCambio(Origen,Destino,valor);
        if(optional.isPresent()){
            return optional.get();
        }
        return new Cambio();
    }

    @Override
    public Cambio guardar(Cambio cambio) {

        MonedaOrigen mo = cambio.getMonedaOrigen();
                    Optional<MonedaOrigen> mo_optional = moRepository.BuscarPorDescripcion(mo.getDescripcion());
                    if (mo_optional.isPresent()) {
                        mo.setIdMonedaOrigen(mo_optional.get().getIdMonedaOrigen());
                        cambio.setMonedaOrigen(mo);
                    } else {
                        cambio.setMonedaOrigen(moRepository.save(mo));
                    }

                    MonedaDestino md = cambio.getMonedaDestino();
                    Optional<MonedaDestino> md_optional = mdRepository.BuscarPorDescripcion(md.getDescripcion());
                    if (md_optional.isPresent()) {
                        md.setIdMonedaDestino(md_optional.get().getIdMonedaDestino());
                        cambio.setMonedaDestino(md);
                    } else {
                        cambio.setMonedaDestino(mdRepository.save(md));
                    }

        return repository.save(cambio);
    }


    @Override
    public Optional<Cambio> buscar(String monedaOrigen, String monedaDestino) {
        return repository.buscarTipoCambio(monedaOrigen,monedaDestino);
    }
}
