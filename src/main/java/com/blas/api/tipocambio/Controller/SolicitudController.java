package com.blas.api.tipocambio.Controller;

import com.blas.api.tipocambio.models.Rol;
import com.blas.api.tipocambio.models.Solicitud;
import com.blas.api.tipocambio.models.Usuario;
import com.blas.api.tipocambio.repository.RolRepository;
import com.blas.api.tipocambio.security.AuthCredential;
import com.blas.api.tipocambio.service.RolService;
import com.blas.api.tipocambio.service.SolicitudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;

@RestController
public class SolicitudController {

    Logger logger = LoggerFactory.getLogger(SolicitudController.class);

    @Autowired
    SolicitudService service;

    @Autowired
    RolService rolService;
    @PostMapping("/solicitud/guardar")
    public ResponseEntity<?> guardar( @RequestBody Solicitud solicitud,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Rol rol = rolService.buscarPorEmail(principal.getName());
           if(rol.getDescripcion().contains("ADMIN") || rol.getDescripcion().contains("USER")){
            Usuario usuario = new Usuario();
            usuario.setEmail(principal.getName());

            solicitud.setUsuario(usuario);
            return ResponseEntity.ok(service.guardar(solicitud));

        }
        return ResponseEntity.status(  HttpStatus.UNAUTHORIZED).build();

    }


}
