package com.blas.api.tipocambio.service;

import com.blas.api.tipocambio.models.Cambio;
import com.blas.api.tipocambio.models.Solicitud;
import com.blas.api.tipocambio.models.Usuario;
import com.blas.api.tipocambio.repository.CambioRepository;
import com.blas.api.tipocambio.repository.RolRepository;
import com.blas.api.tipocambio.repository.SolicitudRepository;
import com.blas.api.tipocambio.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudServiceImpl implements SolicitudService{

    Logger logger = LoggerFactory.getLogger(SolicitudServiceImpl.class);
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    CambioRepository cambioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Solicitud guardar(Solicitud solicitud) {



            Optional<Cambio> cambioOptional =cambioRepository.buscarTipoCambio(
                    solicitud.getCambio().getMonedaOrigen().getIdMonedaOrigen(),
                    solicitud.getCambio().getMonedaDestino().getIdMonedaDestino()
            );

            if(cambioOptional.isPresent()){
                Cambio c = cambioOptional.get();
                solicitud.setCambio(c);
            }
    //buscar Idusuario
            Optional<Usuario> usuarioOptional= usuarioRepository.findOneByEmail(solicitud.getUsuario().getEmail());
            if(usuarioOptional.isPresent()){
                Usuario u =new Usuario();
                u=usuarioOptional.get();
                solicitud.setUsuario(u);
            }
            Double montoInicial= solicitud.getMontoInicial();
            Double montoFinal= montoInicial*solicitud.getCambio().getValor() ;

         Solicitud s= solicitudRepository.save(solicitud);
         s.setMontoFinal(montoFinal);
         s.getUsuario().setPassword("");
        return s;
    }

    @Override
    public List<Solicitud> listar() {
        return null;
    }
}
