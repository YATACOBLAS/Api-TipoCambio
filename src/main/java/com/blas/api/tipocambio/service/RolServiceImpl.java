package com.blas.api.tipocambio.service;

import com.blas.api.tipocambio.models.Rol;
import com.blas.api.tipocambio.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    RolRepository rolRepository;

    @Override
    public Rol buscarPorEmail(String email) {
        return rolRepository.buscarPorEmail(email);
    }

    @Override
    public Optional<Rol> findById(Long idRol) {
        return rolRepository.findById(idRol);
    }
}
