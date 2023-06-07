package com.blas.api.tipocambio.Controller;

import com.blas.api.tipocambio.models.Cambio;
import com.blas.api.tipocambio.models.MonedaDestino;
import com.blas.api.tipocambio.models.MonedaOrigen;
import com.blas.api.tipocambio.models.Rol;
import com.blas.api.tipocambio.service.CambioService;
import com.blas.api.tipocambio.service.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
public class CambioController {
    Logger logger = LoggerFactory.getLogger(CambioController.class);
    @Autowired
    private CambioService service;

    @Autowired
    RolService rolService;
    @PostMapping("/guardar-cambio")
    ResponseEntity<Cambio> guardar(@RequestBody Cambio cambio){

        logger.info(cambio.getMonedaDestino().toString());
            Cambio c= new Cambio();
            c=service.guardar(cambio);
            MonedaOrigen mo = c.getMonedaOrigen();
            MonedaDestino md = c.getMonedaDestino();
            String aux = mo.getDescripcion();
            mo.setDescripcion(md.getDescripcion());
            md.setDescripcion(aux);
            c.setValor(c.getValor()/1);

        return ResponseEntity.ok( c=service.guardar(c));
    }
    @PostMapping("/cambio/guardar")
    ResponseEntity<Cambio> guardarCambio(@RequestBody Cambio cambio, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Rol rol = rolService.buscarPorEmail(principal.getName());
        if(rol.getDescripcion().contains("ADMIN") ){
              return ResponseEntity.ok(
                        service.guardarCambio(
                                cambio.getMonedaOrigen().getDescripcion(),
                                cambio.getMonedaDestino().getDescripcion(),
                                cambio.getValor()));

            }
        return ResponseEntity.status(  HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/cambio/buscar/{monedaOrigen}/{monedaDestino}")
    ResponseEntity<Cambio> buscarTipoCambio(
            @PathVariable("monedaOrigen") Long monedaOrigen,
            @PathVariable("monedaDestino") Long monedaDestino, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Rol rol = rolService.buscarPorEmail(principal.getName());
        if(rol.getDescripcion().contains("ADMIN") || rol.getDescripcion().contains("USER")){
            Optional<Cambio> optional = service.buscar(monedaOrigen,monedaDestino);
            Cambio c = new Cambio();
            if(optional.isPresent()){
                c=optional.get();
                return ResponseEntity.ok(c);
            }
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(  HttpStatus.UNAUTHORIZED).build();

    }
}
