package com.blas.api.tipocambio.service;

import com.blas.api.tipocambio.models.Rol;

import java.util.Optional;

public interface RolService{

     Rol buscarPorEmail(String email);
     Optional<Rol> findById(Long idRol);
}
