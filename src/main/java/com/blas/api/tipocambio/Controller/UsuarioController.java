package com.blas.api.tipocambio.Controller;

import com.blas.api.tipocambio.models.Usuario;
import com.blas.api.tipocambio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @GetMapping("/")
    List<Usuario> listar(){
        List<Usuario> lista= service.listar();
        return lista;

    }
}
