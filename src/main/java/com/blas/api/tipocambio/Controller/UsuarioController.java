package com.blas.api.tipocambio.Controller;

import com.blas.api.tipocambio.models.Rol;
import com.blas.api.tipocambio.models.Usuario;
import com.blas.api.tipocambio.service.RolService;
import com.blas.api.tipocambio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;  

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    RolService rolService;
    @GetMapping("/")
    ResponseEntity<List<Usuario>> listar(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Rol rol = rolService.buscarPorEmail(principal.getName());
        if(rol.getDescripcion().contains("ADMIN")){
            List<Usuario> lista= service.listar();
            return ResponseEntity.ok(lista);
        }
        return ResponseEntity.status(  HttpStatus.UNAUTHORIZED).build();
    }
}
